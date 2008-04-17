package net.sf.sail.cms.curnit.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.ImportUUIDBehavior;
import javax.jcr.InvalidSerializedDataException;
import javax.jcr.ItemExistsException;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;

import net.sf.sail.cms.curnit.CurnitManagement;
import net.sf.sail.cms.curnit.CurnitManagementResponse;
import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.jcrom.JcrDataProvider;
import org.jcrom.JcrDataProviderImpl;
import org.jcrom.JcrFile;
import org.jcrom.Jcrom;
import org.jcrom.JcrDataProvider.TYPE;
import org.jcrom.util.PathUtils;

public class CurnitManagementImpl implements CurnitManagement {
	
	private static Repository repository = null; 
	// All the classes which need to be added to Jcrom which will be persisted
	private List<Class> persistentClasses = new ArrayList<Class>();
	
	// name of the parent node to all curnit nodes
	private final static String CURNITSNODE = "curnits";
	
	/*
	 * Create a curnit in jackrabbit, given the user and password. If the curnit already exists,
	 * it will be specified in the CurnitManagementResponse object, returned.
	 * @param curnit The curnit object to be persisted
	 * @param user Used to log into jackrabbit
	 * @param password Used to log into jackrabbit
	 * @return CurnitManagementResponse
	 */
	public CurnitManagementResponse createCurnit(CurnitOtmlImpl curnit, String user, String password)
			throws CreateCurnitException {
		
		List<Class> persistentClasses = new ArrayList<Class>();
		persistentClasses.add(CurnitOtmlImpl.class);
		
		Session session = getSession(user, password);		
		Jcrom jcrom = getJcrom(persistentClasses);
		
		CurnitDao curnitDao = getCurnitDao(session, jcrom);
		
		try {
			
			// Get the parent node of all curnits
			Node curnitsNode = null;
			if (session.getRootNode().hasNode(CURNITSNODE)){
				curnitsNode = session.getRootNode().getNode(CURNITSNODE);
			}else{
				curnitsNode = session.getRootNode().addNode(CURNITSNODE, "nt:unstructured");
			}			
			
			// check to see if we have a curnits node. This node is the parent of all curnit nodes.
			NodeIterator iter = curnitsNode.getNodes();
			while (iter.hasNext()){
				Node curCurnit = (Node)iter.next();
				if (curCurnit.getProperty("uniqueKey").getString().equals(curnit.getUniqueKey())){
					session.logout();
					//TODO return the correct response message -- the error message should be clear
					return null;
				}				
			}
			
			// set curnit's properties
			curnit.setPath(curnitsNode.getPath());
			curnit.setName(curnit.getUniqueKey());
			curnit.setCreatedTime(new Date());
			
			// Creating a jcrFile object from a java.io.File object. We need to convert the object type to 
			// jcrFile since that is how Jcrom can persist the data.
			//TODO Need to figure this problem out -- uncomment the 5 lines below
			File curnitOtmlFile = curnit.getOtmlFile();
			JcrFile jcrOtmlFile = JcrFile.fromFile(curnitOtmlFile.getName(), curnitOtmlFile, JcrDataProvider.TYPE.FILE.toString());
			curnit.setJcrOtml(jcrOtmlFile);
			
			Node jcrNode = curnitDao.create(curnit);
			curnit.setCurnitPath(jcrNode.getPath());
			curnit.setCurnitUuid(jcrNode.getUUID());
			// add the otml node to the curnit node in jackrabbit
			Node otmlNode = jcrNode.addNode("otmlNode", "nt:unstructured");
			
			// map the otml file into jackrabbit node structure
			mapOtmlFile(session, curnit);	
			curnitDao.update(curnit);
			
			//TODO return successful message
			return null;
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateCurnitException();
		}finally {
			session.logout();
		}
	}


	/*
	 * Delete the specified curnit object from the Jackrabbit repository 
	 * If this node has more than one version, all versions are removed
	 * @param curnit The desired curnit object to be removed
	 * @param user Used to log into the Jackrabbit repository
	 * @param password Used to log into the Jackrabbit repository
	 * @return CurnitManagementResponse
	 * @see net.sf.sail.cms.curnit.CurnitManagement#deleteCurnit(net.sf.sail.cms.curnit.impl.CurnitOtmlImpl, java.lang.String, java.lang.String)
	 */
	public CurnitManagementResponse deleteCurnit(CurnitOtmlImpl curnit, String user, String password)
			throws DeleteCurnitException {
		
		List<Class> persistentClasses = new ArrayList<Class>();
		persistentClasses.add(CurnitOtmlImpl.class);
		
		Session session = getSession(user, password);		
		Jcrom jcrom = getJcrom(persistentClasses);
		
		CurnitDao curnitDao = getCurnitDao(session, jcrom);
			
		try {
			String curnitPath = "/curnits/" + PathUtils.createValidName(curnit.getUniqueKey());
			curnitDao.delete(curnitPath);
			
			//TODO return successful message
			return null;
		
		} catch (Exception e) {
			throw new DeleteCurnitException();
		}finally {
			session.logout();
		}	
	}

	/*
	 * Rerturn a list of all the requested curnits
	 * @param curnitMap A map containing the unique key for each curnit as well as a list of all its requird versions - Note the version numbers must be floats
	 * @param user to be connected to jackrabbit
	 * @param password to be connected to jackrabbit
	 * @return Map<String, List<CurnitOtmlImpl>>
	 * @see net.sf.sail.cms.curnit.CurnitManagement#retrieveCurnit(java.util.Map, java.lang.String, java.lang.String)
	 */
	public Map<String, List<CurnitOtmlImpl>> retrieveCurnit(Map<String, List<Float>> curnitMap, String user, String password)
			throws RetrieveCurnitException {
		
		
		List<Class> persistentClasses = new ArrayList<Class>();
		persistentClasses.add(CurnitOtmlImpl.class);
		
		Session session = getSession(user, password);		
		Jcrom jcrom = getJcrom(persistentClasses);
		
		CurnitDao curnitDao = getCurnitDao(session, jcrom);

		// the map of Curnits and their corresponding versions, requested
		Map<String, List<CurnitOtmlImpl>> requestedCurnits = new HashMap<String, List<CurnitOtmlImpl>>();
		CurnitOtmlImpl curnit = null;
		String nextItem = null;
		List<CurnitOtmlImpl> versionList = new ArrayList<CurnitOtmlImpl>();
		
		// for every curnit, get all its requested versions
		try{
			Iterator<String> iter = curnitMap.keySet().iterator();
			while(iter.hasNext()){				
				nextItem = iter.next();
				String curnitPath = "/curnits/" + PathUtils.createValidName(nextItem);
				curnit = curnitDao.get(curnitPath);
				curnit.setCurnitPath(curnitPath);
				
				// return the latest version
				if (curnitMap.get(nextItem) == null){ // return all versions of the node
					//versionList = curnitDao.getVersionList(curnit.getPath());
					//versionList = curnitDao.getVersionListByUUID(curnit.getCurnitUuid());
					
					
					//List<T> versionList = new ArrayList<T>();
					Node node = session.getRootNode().getNode(curnitPath.substring(1));
					VersionHistory versionHistory = node.getVersionHistory();
					VersionIterator versionIterator = versionHistory.getAllVersions();
					//versionIterator.skip(1);
					while ( versionIterator.hasNext() ) {
						Version version = versionIterator.nextVersion();
						NodeIterator nodeIterator = version.getNodes();
						while ( nodeIterator.hasNext() ) {
							CurnitOtmlImpl entityVersion = (CurnitOtmlImpl)jcrom.fromNode(CurnitOtmlImpl.class, nodeIterator.nextNode(), "*", -1);
							jcrom.setBaseVersionInfo(entityVersion, node.getBaseVersion().getName(), node.getBaseVersion().getCreated());
							versionList.add(entityVersion);
						}
					}
					// versionList;
					
					
				}else if (curnitMap.get(nextItem).get(0).equals(new Float("-1"))){ // return the latest version of the curnit
					//Version latestVersion = (Version)ocm.getObject("/" + nextItem);
					versionList.add(curnit);								
				}else { // return the requested versions
					versionList = getRequestedVersions(curnitDao, curnit, curnitMap.get(nextItem));
				}				
				requestedCurnits.put(nextItem, versionList);				
			}	
			
			session.logout();		
			return requestedCurnits;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	/*
//	 * Given the unique key of a curnit, fetch it from the repository and return it.
//	 * This is an inefficient way of getting to the curnit but since jcrom modifies
//	 * curnit names (eg If a curnit name is set to be "a-b-c" jcrom will modify
//	 * that name to "a_b_c") for retrieval purposes we must iterate through all
//	 * available curnits and fetch the one with the desired uniquKey
//	 */
//	private CurnitOtmlImpl getCurnitFromCMS(Session session, CurnitDao curnitDao, String nextItem) {
//
//		try {
//			NodeIterator curnitIter = session.getRootNode().getNode("curnits").getNodes();
//			while (curnitIter.hasNext()){
//				CurnitOtmlImpl curCurnit = curnitDao.get(curnitIter.nextNode().getPath());
//				if ((curCurnit.getUniqueKey().equals(nextItem))){
//					return curCurnit;
//				}
//			}
//			return null;
//		} catch (PathNotFoundException e) {
//			e.printStackTrace();
//		} catch (RepositoryException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}


	// Get a Jcrom object with the desired classes to be persisted in jackrabbit added to it.
	private Jcrom getJcrom (List<Class> persistentClasses){
		
		this.setPersistentClasses(persistentClasses);
		
		Jcrom jcrom = new Jcrom(true, true);
		
		// register all classes which need to be persisted by jackrabbit
		Iterator<Class> persistenceClassIter = getPersistentClasses().iterator();
		while (persistenceClassIter.hasNext()){
			jcrom.map(persistenceClassIter.next());
		}		
		return jcrom;
	}
	
	/*
	 * Return a session to the jackrabbit repository
	 */
	private Session getSession(String user, String password) {
		Session session = null;		
		
		try {
			if (repository == null) {
				repository = new TransientRepository();
			}
			session = repository.login(new SimpleCredentials(user,
					password.toCharArray()));
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	
		return session;
	}
	
	
	// Get a curnitDao to be able to manipulate the curnit object
	private CurnitDao getCurnitDao(Session session, Jcrom jcrom){
		
		return new CurnitDao(session, jcrom);
	}
	

	// Get all the versions for the given curnit
	private List<CurnitOtmlImpl> getAllVersions(ObjectContentManager ocm, String curnit) {
		// The list of all of the requested versions
		List<CurnitOtmlImpl> curnitList = new ArrayList<CurnitOtmlImpl>();
		
		// The current curnit -- current version we're at
		CurnitOtmlImpl curCurnit = null;
		
		// version we're currently at
		Version curVersion = null;
		Float fltCurVersion = null;
		
		try {
			org.apache.jackrabbit.ocm.version.VersionIterator versionIter = ocm.getAllVersions(curnit);
			while (versionIter.hasNext()){
				curVersion = (Version) versionIter.next();	
				fltCurVersion = new Float(curVersion.getName());								
				curCurnit = (CurnitOtmlImpl) ocm.getObject(curnit, fltCurVersion.toString());
				// add the version + timestamp of the curnit
				curnitList.add(curCurnit);
				
			}
		} catch (VersionException e) {
			e.printStackTrace();
		} catch (ObjectContentManagerException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}finally {
			return curnitList;
		}
	}


	/*
	 * Get all the requested versions for the given curnit
	 */
	private List<CurnitOtmlImpl> getRequestedVersions(CurnitDao curnitDao, CurnitOtmlImpl curnit, List<Float> versions) {
		
		// The list of all of the requested versions
		List<CurnitOtmlImpl> curnitList = new ArrayList<CurnitOtmlImpl>();
				
		// version we're currently at	
		Float fltCurVersion = null;
		
		try{
			Iterator<Float> versionIter = versions.iterator();
			while (versionIter.hasNext()){
				curnitList.add(curnitDao.getVersion(curnit.getPath(), versionIter.next().toString()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			return curnitList;
		}
	}


	public CurnitManagementResponse updateCurnit(CurnitOtmlImpl curnit)
			throws UpdateCurnitException {

		return null;
	}

	public CurnitManagementResponse purgeCurnit(CurnitOtmlImpl curnit) {

		return null;
	}

	public CurnitManagementResponse restoreCurnit(CurnitOtmlImpl curnit) {

		return null;
	}


	public List<Class> getPersistentClasses() {
		return persistentClasses;
	}


	public void setPersistentClasses(List<Class> persistentClasses) {
		
		Iterator<Class> iter = persistentClasses.iterator();
		while (iter.hasNext()){
			Class curClass = iter.next();
			if (!this.persistentClasses.contains(curClass)){
				this.persistentClasses.add(curClass);
			}
		}
	}
	
	public void setPersistentClasses(Class persistentClass){
		if (!this.persistentClasses.contains(persistentClass)){
			this.persistentClasses.add(persistentClass);
		}
	}
	
	
	/** Recursively outputs the contents of the given node. */
	private static void dump(Node node) throws RepositoryException {
		// First output the node path
		System.out.println(node.getPath());
		// Skip the virtual (and large!) jcr:system subtree
		if (node.getName().equals("jcr:system")) {
			return;
		}

		// Then output the properties
		PropertyIterator properties = node.getProperties();
		while (properties.hasNext()) {
			Property property = properties.nextProperty();
			if (property.getDefinition().isMultiple()) {
				// A multi-valued property, print all values
				Value[] values = property.getValues();
				for (int i = 0; i < values.length; i++) {
					System.out.println(property.getPath() + " = "
							+ values[i].getString());
				}
			} else {
				// A single-valued property
				System.out.println(property.getPath() + " = "
						+ property.getString());
			}
		}

		// Finally output all the child nodes recursively
		NodeIterator nodes = node.getNodes();
		while (nodes.hasNext()) {
			dump(nodes.nextNode());
		}
	}
	
	/*
	 * Map the given xml file onto jackrabbit repository
	 */
	private void mapOtmlFile(Session session, CurnitOtmlImpl curnit) {
		InputStream xmlOtml;
		try {
			xmlOtml = new FileInputStream(curnit.getOtmlFile());
			session.importXML(curnit.getCurnitPath() + "/otmlNode", xmlOtml, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
			xmlOtml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (ItemExistsException e) {
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (VersionException e) {
			e.printStackTrace();
		} catch (InvalidSerializedDataException e) {
			e.printStackTrace();
		} catch (LockException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}		
	}


}
