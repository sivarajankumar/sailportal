package net.sf.sail.cms.curnit.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.ImportUUIDBehavior;
import javax.jcr.InvalidSerializedDataException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;

import org.jcrom.JcrMappingException;
import org.jcrom.Jcrom;
import org.jcrom.dao.AbstractJcrDAO;
import org.jcrom.util.PathUtils;

public class CurnitDao extends AbstractJcrDAO<CurnitOtmlImpl> {
		
		private static final String[] MIXIN_TYPES = {"mix:versionable"};
	
        public CurnitDao( Session session, Jcrom jcrom ) {
                super(CurnitOtmlImpl.class, session, jcrom, MIXIN_TYPES);
        }        
        
        /*
         * Create a CurnitOtmlImpl object in Jackrabbit. This method is overridden
         * since we'd like to expand the otml file onto jackrabbit's tree structure.
         * In Jcrom 1.3, the method signature will have to return a
         * CurnitOtmlImpl instead of a Node object (currently Jcrom 1.2 is used)
         * @see org.jcrom.dao.AbstractJcrDAO#create(java.lang.Object)
         */
        public Node create( CurnitOtmlImpl curnit ) throws Exception {
    		String curnitName = jcrom.getName(curnit);
    		if ( curnitName == null || curnitName.equals("") ) {
    			throw new JcrMappingException("The name of the entity being created is empty!");
    		}
    		String parentPath = jcrom.getPath(curnit);
    		if ( parentPath == null || parentPath.equals("") ) {
    			throw new JcrMappingException("The parent path of the entity being created is empty!");
    		}
    		
    		Node parentNode = session.getRootNode().getNode(relativePath(parentPath));
    		Node newNode = jcrom.addNode(parentNode, curnit, mixinTypes);
    		
    		
    		// mapping the otml file onto jackrabbit
    		// add the otml node to the curnit node in jackrabbit
			Node otmlNode = newNode.addNode("otmlNode", "nt:unstructured");
			otmlNode.addMixin("mix:versionable");
			otmlNode.checkout();
			
			// map the otml file into jackrabbit node structure
			mapOtmlFile(session, curnit);
    		
    		
    		session.save();
    		if ( isVersionable ) {
    			newNode.checkin();
    		}
    		return newNode;
    	}
        
        
//    	protected String update( Node node, CurnitOtmlImpl curnit, String childNodeFilter, int maxDepth ) throws Exception {
////    		if ( isVersionable ) {
////    			node.checkout();
////    		}
//    		
//    		System.out.println(node.getName());
//    		System.out.println(node.getPath());
//    		
//    		return null;
//    		
////    		node.getNode("otmlNode").remove();
////
////    		// mapping the otml file onto jackrabbit
////    		// add the otml node to the curnit node in jackrabbit
////			Node otmlNode = node.addNode("otmlNode", "nt:unstructured");
////			otmlNode.addMixin("mix:versionable");
////			otmlNode.checkout();
////			
////			// map the otml file into jackrabbit node structure
////			mapOtmlFile(session, curnit);
////    		
////    		String name = jcrom.updateNode(node, curnit, childNodeFilter, maxDepth);
////    		session.save();
////    		if ( isVersionable ) {
////    			node.checkin();
////    		}
////    		return name;
//    	}

    	/*
    	 * Map the given xml file onto jackrabbit repository
    	 */
    	private void mapOtmlFile(Session session, CurnitOtmlImpl curnit) {
    		InputStream xmlOtml;
    		try {
    			xmlOtml = new FileInputStream(curnit.getOtmlFile());
    			session.importXML(curnit.getPath() + "/otmlNode", xmlOtml, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
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
        
        public List<CurnitOtmlImpl> getCurnitVersions (Session session, String uniqueKey, List versions) {
        	
        	List<CurnitOtmlImpl> versionedCurnitList = new ArrayList<CurnitOtmlImpl>();
        	        	
        	try{
        		CurnitOtmlImpl curVersionOfCurnit;
        		Version curVersion = null;
        		VersionHistory versionHis = session.getRootNode().getNode("curnits").getNode(uniqueKey).getVersionHistory();
        		
	        	if (versions == null){ // get all the versions for the requested curnit  	
					
        			VersionIterator verIterator = versionHis.getAllVersions();
        			verIterator.skip(1);
					while (verIterator.hasNext()){
						curVersion = verIterator.nextVersion();
						curVersionOfCurnit = super.getVersion("/curnits/" + uniqueKey, curVersion.getName());	
						if (curVersionOfCurnit.getJcrOtml() != null){ 
							versionedCurnitList.add(createCurnitWithVersion(curVersionOfCurnit, curVersion.getName()));
						}
					}
	        	}else if (versions.size() == 1 && versions.contains(new Float("-1"))){ // return the latest version of the curnit
	        		//TODO need to figure out how I can get the latest version of the node
	        		VersionIterator verIterator = versionHis.getAllVersions();
        			verIterator.skip(1);
					while (verIterator.hasNext()){
						curVersion = verIterator.nextVersion();
					}
					
					curVersionOfCurnit = super.getVersion("/curnits/" + uniqueKey, curVersion.getName());	
					if (curVersionOfCurnit.getJcrOtml() != null){ 
						versionedCurnitList.add(createCurnitWithVersion(curVersionOfCurnit, curVersion.getName()));
					}
	        		
	        	}else { // return the requested versions of the curnit
	        		Iterator<Float> verIterator = versions.iterator();
	        		while (verIterator.hasNext()){
	        			String nextVersion = verIterator.next().toString();
	        			curVersionOfCurnit = super.getVersion("/curnits/" + uniqueKey, nextVersion);
	        			if (curVersionOfCurnit.getJcrOtml() != null){ 
	        				versionedCurnitList.add(createCurnitWithVersion(curVersionOfCurnit, nextVersion));
	        			}
	        		}
	        	}
				
			} catch (UnsupportedRepositoryOperationException e) {
				e.printStackTrace();
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	return versionedCurnitList;
        }
        
        /**
         * Get the latest version of the given curnit
         * @param session Session for Jackrabbit repository
         * @param uniqueKey The unique string identifying each curnit
         * @return CurnitOtmlImp
         */
        public CurnitOtmlImpl getLatestVersion (Session session, String uniqueKey){
        	
        	List<Float> versionList = new ArrayList<Float>();
        	versionList.add(new Float("-1"));
        	
        	List<CurnitOtmlImpl> curnitList = this.getCurnitVersions(session, uniqueKey, versionList);
        	
        	return curnitList.get(0);        	
        }

        /*
         * Given a CurnitOtmlImpl object and a series of versions for it,
         * return all the requested versions while adding the otml file
         * to the returned objects
         */
		private CurnitOtmlImpl createCurnitWithVersion(CurnitOtmlImpl curVersionOfCurnit, String curVersion)
				throws FileNotFoundException, IOException, RepositoryException {
			// need to read the otml file content from jackrabbit and create a java.io.File object to return
			
			InputStream otmlStream = curVersionOfCurnit.getJcrOtml().getDataProvider().getInputStream();
			
			File otmlFile = new File(curVersionOfCurnit.getUniqueKey() + ".otml");
			FileOutputStream fos = new FileOutputStream(otmlFile);
			
			byte buf[]=new byte[1024];
		    int len;
		    while((len=otmlStream.read(buf))>0){
		    	fos.write(buf,0,len);
		    }
		    
		    otmlStream.close();
		    fos.close();
		    
		    // Setting some of the Curnit's properties. Some properties are set
		    // to null since those properties are only used by the CMS side.
		    // Deleting those content will reduce the amount of contenet being
		    // transfered over the wire.
		    curVersionOfCurnit.setOtmlFile(otmlFile);
		    curVersionOfCurnit.setCurnitVersion(curVersion);
		    curVersionOfCurnit.setJcrOtml(null);
		    curVersionOfCurnit.setPath(null);
		    curVersionOfCurnit.setName(null);			    
			return curVersionOfCurnit;
		}
}