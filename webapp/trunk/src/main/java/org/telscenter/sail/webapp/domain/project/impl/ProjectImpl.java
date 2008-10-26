/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.domain.project.impl;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.impl.JnlpImpl;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.hibernate.annotations.Cascade;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = ProjectImpl.DATA_STORE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
public class ProjectImpl implements Project {

    @Transient
    public static final String SHARED_OWNERS_JOIN_COLUMN_NAME = "shared_owners_fk";
    
	@Transient
    public static final String SHARED_OWNERS = "shared_owners";
    
    @Transient
    public static final String SHARED_OWNERS_JOIN_TABLE_NAME = "projects_related_to_shared_owners";


	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	public static final String COLUMN_NAME_FAMILYTAG = "familytag";
	
	@Transient
	public static final String COLUMN_NAME_PROJECTINFOTAG = "projectinfotag";
	
	@Transient
	public static final String COLUMN_NAME_ISCURRENT = "iscurrent";
	
	@Transient
	public static final String DATA_STORE_NAME = "projects";
	
	@Transient
	public static final String COLUMN_NAME_CURNIT_FK = "curnit_fk";

	@Transient
	public static final String COLUMN_NAME_JNLP_FK = "jnlp_fk";
	
	@Transient
	public static final String COLUMN_NAME_PREVIEWOFFERING_FK = "run_fk";

	@Transient
	private static final String COLUMN_NAME_PROJECTTYPE = "projecttype";

	@Transient
	private static final String COLUMN_NAME_PROJECT_NAME = "name";

	@Transient
	private static final String OWNERS_JOIN_TABLE_NAME = "projects_related_to_owners";
	
    @Transient
    public static final String OWNERS_JOIN_COLUMN_NAME = "owners_fk";

    @Transient
	private static final String PROJECTS_JOIN_COLUMN_NAME = "projects_fk";

	@Transient
	public ProjectInfo projectinfo = new ProjectInfoImpl();
	
	@Column(name = COLUMN_NAME_PROJECT_NAME)
	protected String name;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = CurnitImpl.class)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = COLUMN_NAME_CURNIT_FK, nullable = true, unique = false)
	protected Curnit curnit;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = JnlpImpl.class)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	@JoinColumn(name = COLUMN_NAME_JNLP_FK, nullable = true, unique = false)
	protected Jnlp jnlp;
	
	@OneToOne(targetEntity = RunImpl.class, fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_NAME_PREVIEWOFFERING_FK, unique = true)
	protected Run previewRun;
	
	@ManyToMany(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = OWNERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name =  PROJECTS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = OWNERS_JOIN_COLUMN_NAME, nullable = false))
    private Set<User> owners = new TreeSet<User>();
	
    @ManyToMany(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = SHARED_OWNERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name =  PROJECTS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = SHARED_OWNERS_JOIN_COLUMN_NAME, nullable = false))
    private Set<User> sharedowners = new TreeSet<User>();
    
    @Column(name = ProjectImpl.COLUMN_NAME_FAMILYTAG, nullable = true)
	protected FamilyTag familytag;
    
    @Column(name = ProjectImpl.COLUMN_NAME_ISCURRENT, nullable = true)
    protected boolean isCurrent;
    
    @Column(name = ProjectImpl.COLUMN_NAME_PROJECTTYPE, nullable = true)
    protected ProjectType projectType;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id = null;

    @Version
    @Column(name = "OPTLOCK")
    protected Integer version = null;
	
    
	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getCurnit()
	 */
	public Curnit getCurnit() {
		return curnit;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getJnlp()
	 */
	public Jnlp getJnlp() {
		return jnlp;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setCurnit(net.sf.sail.webapp.domain.Curnit)
	 */
	public void setCurnit(Curnit curnit) {
		this.curnit = curnit;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setJnlp(net.sf.sail.webapp.domain.Jnlp)
	 */
	public void setJnlp(Jnlp jnlp) {
		this.jnlp = jnlp;
	}

    /**
     * @see net.sf.sail.webapp.domain.Curnit#getId()
     */
    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

	/**
	 * @return the previewOffering
	 */
	public Run getPreviewRun() {
		return previewRun;
	}

	/**
	 * @param previewOffering the previewOffering to set
	 */
	public void setPreviewRun(Run previewRun) {
		this.previewRun = previewRun;
	}
    
    @SuppressWarnings("unused")
    private Integer getVersion() {
        return this.version;
    }

    @SuppressWarnings("unused")
    private void setVersion(Integer version) {
        this.version = version;
    }

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curnit == null) ? 0 : curnit.hashCode());
		result = prime * result + ((jnlp == null) ? 0 : jnlp.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProjectImpl other = (ProjectImpl) obj;
		if (curnit == null) {
			if (other.curnit != null)
				return false;
		} else if (!curnit.equals(other.curnit))
			return false;
		if (jnlp == null) {
			if (other.jnlp != null)
				return false;
		} else if (!jnlp.equals(other.jnlp))
			return false;
		return true;
	}

	/**
	 * @return the familyTag
	 */
	public FamilyTag getFamilytag() {
		return familytag;
	}

	/**
	 * @param familyTag the familyTag to set
	 */
	public void setFamilytag(FamilyTag familytag) {
		this.familytag = familytag;
		this.projectinfo.setFamilyTag(familytag);
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
		this.projectinfo.setCurrent(isCurrent);
	}


	/**
	 * @return the projectInfo
	 */
	public ProjectInfo getProjectInfo() {
		return projectinfo;
	}

	/**
	 * @param projectInfoTag the projectInfoTag to set
	 */
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectinfo = projectInfo;
		this.isCurrent = projectInfo.isCurrent();
		this.familytag = projectInfo.getFamilyTag();
	}
	
	public Set<User> getSharedowners() {
		return sharedowners;
	}

	public void setSharedowners(Set<User> sharedowners) {
		this.sharedowners = sharedowners;
	}

	public Set<User> getOwners() {
		return owners;
	}

	public void setOwners(Set<User> owners) {
		this.owners = owners;
	}

	/**
	 * @return the projectType
	 */
	public ProjectType getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#populateProjectInfo()
	 */
	public void populateProjectInfo() {
	}
 }
