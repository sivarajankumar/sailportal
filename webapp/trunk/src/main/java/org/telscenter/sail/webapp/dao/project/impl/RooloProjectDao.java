package org.telscenter.sail.webapp.dao.project.impl;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;

import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.cmsImpl.RooloProjectImpl;
import org.telscenter.sail.webapp.domain.project.impl.ProjectInfoImpl;

import roolo.curnit.client.IClientRepository;

import roolo.curnit.client.basicProxy.CurnitContentProxy;
import roolo.curnit.client.basicProxy.CurnitMetadataProxy;
import roolo.curnit.client.basicProxy.CurnitProxy;
import roolo.curnit.client.basicProxy.MetadataKeyProxy;
import roolo.curnit.client.basicProxy.MetadataValueProxy;
import roolo.curnit.client.basicProxy.SimpleQueryMetadata;
import roolo.curnit.client.impl.ClientCurnitRepository;

/**
 * Project DAO that use Roolo
 * @author ccelorrio
 *
 */
public class RooloProjectDao implements ProjectDao<RooloProjectImpl> {

    private static String URL = "http://roolo.server.com/webdav/get?uri=";
    
    // Roolo client
    private IClientRepository rooloClient = new ClientCurnitRepository();
    
    
    public RooloProjectImpl createEmptyProject() {
	System.out.println("RooloDAO createEmptyProject");
	RooloProjectImpl project = new RooloProjectImpl();
	return project;
    }

    public List<RooloProjectImpl> retrieveListByInfo(ProjectInfo projectinfo)
	    throws ObjectNotFoundException {
System.out.println("RooloDAO retrieveListByInfo");
	List<RooloProjectImpl> projects = new ArrayList<RooloProjectImpl>();
	SimpleQueryMetadata query = new SimpleQueryMetadata();
	CurnitMetadataProxy metadata = createMetadata( projectinfo);
	query.setMetadataPattern(metadata);
	List<URI> curnitIds = rooloClient.search(query);
	for(URI id : curnitIds) {
	    try {
		projects.add( this.getById(id));
	    } catch (ObjectNotFoundException e) {
	    }
	}
	return projects;
    }


    /**
     * @see org.telscenter.sail.webapp.dao.project.ProjectDao#retrieveListByTag(org.telscenter.sail.webapp.domain.project.FamilyTag)
     */
    public List<RooloProjectImpl> retrieveListByTag(FamilyTag familytag)
	    throws ObjectNotFoundException {
	System.out.println("RooloDAO retrieveListByTag(familytag)");
	ProjectInfo projectInfo = new ProjectInfoImpl();
	projectInfo.setFamilyTag(familytag);
	List<RooloProjectImpl> projects = new ArrayList<RooloProjectImpl>();
	SimpleQueryMetadata query = new SimpleQueryMetadata();
	CurnitMetadataProxy metadata = createMetadata(projectInfo);
	query.setMetadataPattern(metadata);
	List<URI> curnitIds = rooloClient.search(query);
	for(URI id : curnitIds) {
	    try {
		projects.add( this.getById(id));
	    } catch (ObjectNotFoundException e) {
	    }
	}
	return projects;
    }

    public List<RooloProjectImpl> retrieveListByTag(String projectinfotag)
	    throws ObjectNotFoundException {
	System.out.println("RooloDAO retrieveListByTag(projectinfotag)");
	return null;
    }

    public void delete(RooloProjectImpl project) {
	System.out.println("RooloDAO delete");
	try {
	    URI uri = new URI(project.getId().toString());
	    rooloClient.deleteELO( uri );
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	}
	

    }

    public RooloProjectImpl getById(Serializable id) throws ObjectNotFoundException {
	System.out.println("RooloDAO getById");
	RooloProjectImpl project = null;
	try {
	    URI uri = new URI(id.toString());
	    CurnitProxy curnit = rooloClient.retrieveELO(uri);
	    project = createProject(curnit);
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	}
	
	return project;
    }

    public RooloProjectImpl getById(URI uri) throws ObjectNotFoundException {
	System.out.println("RooloDAO getById");
	CurnitProxy curnit = rooloClient.retrieveELO(uri);
	RooloProjectImpl project = createProject(curnit);
	return project;
    }

    public List<RooloProjectImpl> getList() {
	System.out.println("RooloDAO getList");
	List<RooloProjectImpl> projects = new ArrayList<RooloProjectImpl>();
	SimpleQueryMetadata query = new SimpleQueryMetadata();
	CurnitMetadataProxy metadata = new CurnitMetadataProxy();
	metadata.setMetadataValue(MetadataKeyProxy.URI, new MetadataValueProxy("%"));
	query.setMetadataPattern(metadata);
	List<URI> curnitIds = rooloClient.search(query);
	for(URI id : curnitIds) {
	    try {
		projects.add( this.getById(id));
	    } catch (ObjectNotFoundException e) {
	    }
	}
	return projects;
    }

    public void save(RooloProjectImpl project) {
	System.out.println("RooloDAO save");
	CurnitProxy curnit = createCurnitProxy(project);
	//rooloClient.addELO(curnit);
	rooloClient.updateELO(curnit);

    }

    
    private RooloProjectImpl createProject(CurnitProxy proxy) {
	RooloProjectImpl project = new RooloProjectImpl();
	ProjectInfo info = createProjectInfo( proxy.getMetaData());
	project.setProjectInfo(info);
	project.setFamilytag( info.getFamilyTag());
	project.setCurrent( info.isCurrent());
	project.setId( proxy.getUri() );
	project.setProxy(proxy);
	
	Curnit c = new CurnitImpl();
	SdsCurnit sdsCurnit = new SdsCurnit();
	sdsCurnit.setName(proxy.getMetaData().getMetadataValue(MetadataKeyProxy.TITLE).getStringValue());
	String url = URL + proxy.getMetaData().getMetadataValue(MetadataKeyProxy.URI).getStringValue();
	sdsCurnit.setUrl(url);
	c.setSdsCurnit(sdsCurnit);
	project.setCurnit(c);
	
	return project;
    }
    
    
    private ProjectInfo createProjectInfo(CurnitMetadataProxy metadata) {
	ProjectInfo info = new ProjectInfoImpl();
	String author = metadata.getMetadataValue(MetadataKeyProxy.AUTHOR).getStringValue();
	info.setAuthor(author);
	// TODO Add the rest of the metadata info
//	String gradeLevel = metadata.getMetadataValue(MetadataKeyProxy.GRADELEVEL).getStringValue();
//	String subject = metadata.getMetadataValue(MetadataKeyProxy.SUBJECT).getStringValue();
//	String keywords = metadata.getMetadataValue(MetadataKeyProxy.KEYWORDS).getStringValue();
//	String projectLiveCycle = metadata.getMetadataValue(MetadataKeyProxy.LIVECYCLE).getStringValue();
	String familyTag = metadata.getMetadataValue(MetadataKeyProxy.FAMILYTAG).getStringValue();
	List<FamilyTag> possibleValues = Arrays.asList( FamilyTag.values());
	if( familyTag != null && possibleValues.contains( FamilyTag.valueOf(familyTag))) {
	    info.setFamilyTag(FamilyTag.valueOf(familyTag));
	}
	else {
	    info.setFamilyTag(FamilyTag.OTHER);
	}
	String isCurrent = metadata.getMetadataValue(MetadataKeyProxy.CURRENT).getStringValue();
	info.setCurrent("yes".equals(isCurrent));
	return info;
    }

    private CurnitProxy createCurnitProxy(RooloProjectImpl project) {
	
	CurnitProxy proxy = project.getProxy();
	
	CurnitMetadataProxy metadataProxy = proxy.getMetaData();
	FamilyTag familytag = project.getFamilytag();
	metadataProxy.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy(familytag.toString()));
	if(project.isCurrent())
	    metadataProxy.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("yes"));
	else
	    metadataProxy.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy("no"));
	return proxy;
    }
    
    /**
     * @param projectinfo
     * @return
     */
    private CurnitMetadataProxy createMetadata(ProjectInfo projectinfo) {
    	CurnitMetadataProxy curnitMetadataProxy = new CurnitMetadataProxy();
    	curnitMetadataProxy.setMetadataValue(MetadataKeyProxy.FAMILYTAG, new MetadataValueProxy(projectinfo.getFamilyTag().toString()));
    	// TODO: CC or HT: more info e.g. author
	    return curnitMetadataProxy;
    }

}
