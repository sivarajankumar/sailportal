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

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;

import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.project.ExternalProject;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.ProjectCommunicator;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.service.project.ExternalProjectService;

/**
 * Project stored in external locations
 * @author Hiroki Terashima
 * @author Scott Cytacki
 * @version $Id$
 */
@Entity
@Table(name = ExternalProjectImpl.DATA_STORE_NAME)
public class ExternalProjectImpl implements ExternalProject {

	@Transient
	private static final String DATA_STORE_NAME = "externalprojects";
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	private String name;
	
	@Transient
	private Serializable externalId;
	
	private ProjectCommunicator projectCommunicator;
	
	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getCurnit()
	 */
	public Curnit getCurnit() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getFamilytag()
	 */
	public FamilyTag getFamilytag() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getId()
	 */
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getJnlp()
	 */
	public Jnlp getJnlp() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getPreviewRun()
	 */
	public Run getPreviewRun() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getProjectInfo()
	 */
	public ProjectInfo getProjectInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#isCurrent()
	 */
	public boolean isCurrent() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setCurnit(net.sf.sail.webapp.domain.Curnit)
	 */
	public void setCurnit(Curnit curnit) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setCurrent(boolean)
	 */
	public void setCurrent(boolean isCurrent) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setFamilytag(org.telscenter.sail.webapp.domain.project.FamilyTag)
	 */
	public void setFamilytag(FamilyTag familytag) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setJnlp(net.sf.sail.webapp.domain.Jnlp)
	 */
	public void setJnlp(Jnlp jnlp) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setPreviewRun(org.telscenter.sail.webapp.domain.Run)
	 */
	public void setPreviewRun(Run run) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setProjectInfo(org.telscenter.sail.webapp.domain.project.ProjectInfo)
	 */
	public void setProjectInfo(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub

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
	 * @return the externalDIYId
	 */
	public Serializable getExternalId() {
		return externalId;
	}

	/**
	 * @param externalDIYId the externalDIYId to set
	 */
	public void setExternalId(Serializable externalDIYId) {
		this.externalId = externalDIYId;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#getProjectType()
	 */
	public ProjectType getProjectType() {
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.project.Project#setProjectType(org.telscenter.sail.webapp.domain.project.impl.ProjectType)
	 */
	public void setProjectType(ProjectType projectType) {
	}

	/**
	 * @return the projectCommunicator
	 */
	public ProjectCommunicator getProjectCommunicator() {
		return projectCommunicator;
	}

	/**
	 * @param projectCommunicator the projectCommunicator to set
	 */
	public void setProjectCommunicator(ProjectCommunicator projectCommunicator) {
		this.projectCommunicator = projectCommunicator;
	}
	
}
