package org.telscenter.sail.webapp.domain.project.cmsImpl;



import java.net.URI;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;

import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;

import roolo.curnit.client.basicProxy.CurnitProxy;

public class RooloProjectImpl implements Project {


    private static final long serialVersionUID = 1L;

    private URI id;
	
    private Curnit curnit;
	
    private Jnlp jnlp;
	
    private Run previewRun;
	
    private FamilyTag familytag;
    
    private boolean isCurrent;
    
    private ProjectInfo projectInfo;

    private CurnitProxy proxy;
    
    /**
     * @return the curnit
     */
    public Curnit getCurnit() {
    	return curnit;
    }

    /**
     * @param curnit the curnit to set
     */
    public void setCurnit(Curnit curnit) {
	this.curnit = curnit;
    }

	/**
	 * @return the jnlp
	 */
	public Jnlp getJnlp() {
		return jnlp;
	}

	/**
	 * @param jnlp the jnlp to set
	 */
	public void setJnlp(Jnlp jnlp) {
		this.jnlp = jnlp;
	}

	/**
	 * @return the previewRun
	 */
	public Run getPreviewRun() {
		return previewRun;
	}

	/**
	 * @param previewRun the previewRun to set
	 */
	public void setPreviewRun(Run previewRun) {
		this.previewRun = previewRun;
	}

	/**
	 * @return the familytag
	 */
	public FamilyTag getFamilytag() {
		return familytag;
	}

	/**
	 * @param familytag the familytag to set
	 */
	public void setFamilytag(FamilyTag familytag) {
		this.familytag = familytag;
	}

	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public URI getId() {
		return this.id;
	}

	public ProjectInfo getProjectInfo() {
	    return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
	    this.projectInfo = projectInfo;
	    
	}

	/**
	 * Set the id
	 * @param id the id to set
	 */
	public void setId(URI id) {
	    this.id = id;
	}

	/**
	 * Get the proxy
	 * @return the proxy
	 */
	public CurnitProxy getProxy() {
	    return proxy;
	}

	/**
	 * Set the proxy
	 * @param proxy the proxy to set
	 */
	public void setProxy(CurnitProxy proxy) {
	    this.proxy = proxy;
	}
	


}
