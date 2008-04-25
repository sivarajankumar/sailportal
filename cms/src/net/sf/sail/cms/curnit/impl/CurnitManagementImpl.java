package net.sf.sail.cms.curnit.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
import javax.jcr.PathNotFoundException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import net.sf.sail.cms.curnit.CurnitManagement;
import net.sf.sail.cms.curnit.CurnitManagementResponse;
import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

import org.apache.jackrabbit.core.TransientRepository;
import org.jcrom.JcrDataProvider;
import org.jcrom.JcrFile;
import org.jcrom.Jcrom;

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
			
			// Make sure we do not already have this curnit in the repository
			if (curnitDao.exists("/curnits/" + curnit.getUniqueKey())){
				session.logout();
				//TODO return the correct response message -- the error message should be clear
				return null;
			}
			
			// set curnit's properties
			curnit.setPath(curnitsNode.getPath());
			curnit.setName(curnit.getUniqueKey());
			curnit.setCreatedTime(new Date());
			
			// Creating a jcrFile object from a java.io.File object. We need to convert the object type to 
			// jcrFile since that is how Jcrom can persist the data.
			File curnitOtmlFile = curnit.getOtmlFile();
			JcrFile jcrOtmlFile = JcrFile.fromFile(curnitOtmlFile.getName(), curnitOtmlFile, JcrDataProvider.TYPE.FILE.toString());
			curnit.setJcrOtml(jcrOtmlFile);
			
			curnitDao.create(curnit);
//			curnit.setCurnitUuid(jcrNode.getUUID());
//			
//			jcrNode.checkout();
//			// add the otml node to the curnit node in jackrabbit
//			Node otmlNode = jcrNode.addNode("otmlNode", "nt:unstructured");
//			otmlNode.addMixin("mix:versionable");
//			otmlNode.checkout();
//			
//			// map the otml file into jackrabbit node structure
//			mapOtmlFile(session, curnit);
//			session.save();
//			otmlNode.checkin();
//			jcrNode.checkin();
			
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
			String curnitPath = "/curnits/" + curnit.getUniqueKey();
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
				String curnitPath = "/curnits/" + nextItem;
				curnit = curnitDao.get(curnitPath);
				
				versionList = curnitDao.getCurnitVersions(session, curnit.getUniqueKey(), curnitMap.get(nextItem));				
				requestedCurnits.put(nextItem, versionList);				
			}	
			
			session.logout();		
			return requestedCurnits;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public CurnitManagementResponse updateCurnit(CurnitOtmlImpl curnit, String user, String password)
			throws UpdateCurnitException {
		
		List<Class> persistentClasses = new ArrayList<Class>();
		persistentClasses.add(CurnitOtmlImpl.class);
		
		Session session = getSession(user, password);		
		Jcrom jcrom = getJcrom(persistentClasses);
		
		CurnitDao curnitDao = getCurnitDao(session, jcrom);		
//		CurnitOtmlImpl retrievedCurnit = curnitDao.getLatestVersion(session, curnit.getUniqueKey());
		
		curnit.setPath("/curnits/" + curnit.getUniqueKey());
		
		try {
			curnitDao.update(curnit);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public CurnitManagementResponse purgeCurnit(CurnitOtmlImpl curnit) {

		return null;
	}

	public CurnitManagementResponse restoreCurnit(CurnitOtmlImpl curnit) {

		return null;
	}

//	/*
//	 * Map the given xml file onto jackrabbit repository
//	 */
//	private void mapOtmlFile(Session session, CurnitOtmlImpl curnit) {
//		InputStream xmlOtml;
//		try {
//			xmlOtml = new FileInputStream(curnit.getOtmlFile());
//			session.importXML(curnit.getPath() + "/otmlNode", xmlOtml, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
//			xmlOtml.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (PathNotFoundException e) {
//			e.printStackTrace();
//		} catch (ItemExistsException e) {
//			e.printStackTrace();
//		} catch (ConstraintViolationException e) {
//			e.printStackTrace();
//		} catch (VersionException e) {
//			e.printStackTrace();
//		} catch (InvalidSerializedDataException e) {
//			e.printStackTrace();
//		} catch (LockException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (RepositoryException e) {
//			e.printStackTrace();
//		}		
//	}
	
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
	
	// Get a Jcrom object with the desired classes to be persisted in jackrabbit added to it.
	private Jcrom getJcrom (List<Class> persistentClasses){
		
		this.setPersistentClasses(persistentClasses);
		
		Jcrom jcrom = new Jcrom(false, true);
		
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
}
