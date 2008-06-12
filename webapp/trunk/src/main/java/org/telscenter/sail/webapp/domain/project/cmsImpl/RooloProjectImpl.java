package org.telscenter.sail.webapp.domain.project.cmsImpl;



import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;

import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;

public class RooloProjectImpl implements Project {


    private static final long serialVersionUID = 1L;

    private Long id;
	
    private Curnit curnit;
	
    private Jnlp jnlp;
	
    private Run previewRun;
	
    private FamilyTag familytag;
    
    private boolean isCurrent;
    
    private ProjectInfo projectInfo;

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

	public Long getId() {
		return this.id;
	}

	public ProjectInfo getProjectInfo() {
	    return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
	    this.projectInfo = projectInfo;
	    
	}
	


}
