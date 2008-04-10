package net.sf.sail.cms.curnit;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionIterator;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl;
import org.pdfbox.pdfviewer.MapEntry;

import com.sun.java_cup.internal.version;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

public class CurnitManagementImpl implements CurnitManagement {	
	
	// All the classes which need to be added to Jackrabbit-ocm which will be persisted
	private List<Class> persistentClasses = new ArrayList<Class>();
	
	/**
	 * Create a curnit, given the user and password
	 */
	public CurnitManagementResponse createCurnit(CurnitOtmlImpl curnit, String user, String password)
			throws CreateCurnitException {
		
		Session session = this.getSession(user, password);		
		if (session == null){
			// return an error message
			session.logout();
			return null;
		}
		this.persistentClasses.add(curnit.getClass());
		ObjectContentManager ocm = init(session);
		
		// check to see if the object already exists or not
		if (ocm.getObject(curnit.getName()) != null){
			// return error indicating the object already exists
			ocm.logout();
			session.logout();
			return null;
		}
		ocm.insert(curnit);
		ocm.save();			
		ocm.logout();
		session.logout();
	
		return null;
	}


	public CurnitManagementResponse deleteCurnit(CurnitOtmlImpl curnit, String user, String password)
			throws DeleteCurnitException {
		
		Session session = this.getSession(user, password);
		if (session == null){
			// return an error message
			return null;
		}
		this.persistentClasses.add(curnit.getClass());
		ObjectContentManager ocm = init(session);
		ocm.remove(curnit);
		ocm.save();		
		
		session.logout();
	
		return null;
	}

	public Map<String, List<CurnitOtmlImpl>> retrieveCurnit(Map<String, List<Float>> curnitMap, String user, String password)
			throws RetrieveCurnitException {
		
		// the map of Curnits and their corresponding versions, requested
		Map<String, List<CurnitOtmlImpl>> requestedCurnits = new HashMap<String, List<CurnitOtmlImpl>>();
		CurnitOtmlImpl curnit = null;
		String nextItem = null;
		List<CurnitOtmlImpl> versionList = new ArrayList<CurnitOtmlImpl>();
		
		Session session = this.getSession(user, password);
		if (session == null){
			// return an error message
			return null;
		}
		ObjectContentManager ocm = init(session);
		Iterator<String> iter = curnitMap.keySet().iterator();
		while(iter.hasNext()){
			nextItem = iter.next();
			
			// return the latest version
			if (curnitMap.get(nextItem).get(0).equals(new Float("-1"))){
				//Version latestVersion = (Version)ocm.getObject("/" + nextItem);
				curnit = (CurnitOtmlImpl)ocm.getObject("/" + nextItem);	
				// add version + timestamp to curnit
				versionList.add(curnit);
				
			}else if (curnitMap.get(nextItem) == null){ // return all versions of the node
				versionList = getAllVersions(ocm, nextItem);
				
			}else { // return the requested versions
				versionList = getRequestedVersions(ocm, nextItem, curnitMap.get(nextItem));
			}				
			requestedCurnits.put(nextItem, versionList);			
			
		}		
		session.logout();		
		return requestedCurnits;
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
	private List<CurnitOtmlImpl> getRequestedVersions(ObjectContentManager ocm, String curnit, List<Float> versions) {
		
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
				if (versions.contains(fltCurVersion)){				
					curCurnit = (CurnitOtmlImpl) ocm.getObject(curnit, ((Version)versionIter.next()).getName());
					// add the version + timestamp of the curnit
					curnitList.add(curCurnit);
				}
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

	
	/*
	 * Initialize a connect to allow Jackrabbit-ocm operations
	 */
	private ObjectContentManager init(Session session) {
		
		ObjectContentManager ocm = null;
					
		Mapper mapper = new AnnotationMapperImpl(this.getPersistentClasses());
		ocm =  new ObjectContentManagerImpl(session, mapper);			
		
		return ocm;
		
	}


	/*
	 * Return a session to the jackrabbit repository
	 */
	private Session getSession(String user, String password) {
		Repository repository;
		Session session = null;		
		
		try {
			repository = new TransientRepository();
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


	public List<Class> getPersistentClasses() {
		return persistentClasses;
	}


	public void setPersistentClasses(List<Class> persistentClasses) {
		this.persistentClasses = persistentClasses;
	}

}
