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
package org.telscenter.sail.webapp.service.project.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.ExternalProject;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectCommunicator;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ExternalProjectImpl;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.service.project.ExternalProjectService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * DIY Project Service implementation. Speaks to the DIY portal remotely.
 * 
 * @author Hiroki Terashima
 * @author Scott Cytacki
 * @version $Id$
 */
public class ExternalProjectServiceImpl implements ExternalProjectService {

	private ProjectCommunicator projectCommunicator;

	protected ProjectDao<Project> projectDao;
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#authorProject(org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters)
	 */
	public Object authorProject(AuthorProjectParameters authorProjectParameters)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#createProject(org.telscenter.sail.webapp.domain.impl.ProjectParameters)
	 */
	public Project createProject(ProjectParameters projectParameters)
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getById(java.io.Serializable)
	 */
	public Project getById(Serializable projectId)
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ExternalProjectService#getExternalProjectList()
	 */
	public List<ExternalProject> getExternalProjectList() {
		return this.projectCommunicator.getProjectList();
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ExternalProjectService#importProject(org.telscenter.sail.webapp.domain.project.ExternalProject)
	 */
	public void importProject(ExternalProject project) {
		//this.projectCommunicator.importProject();
		Serializable externalId = project.getExternalId();
		
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList()
	 */
	public List<Project> getProjectList() {
		// look in the internal database
    	List<Project> projectList = this.projectDao.getList();
		
		return projectList;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByInfo(org.telscenter.sail.webapp.domain.project.ProjectInfo)
	 */
	public List<Project> getProjectListByInfo(ProjectInfo info)
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(org.telscenter.sail.webapp.domain.project.FamilyTag)
	 */
	public List<Project> getProjectListByTag(FamilyTag tag)
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(java.lang.String)
	 */
	public List<Project> getProjectListByTag(String tag)
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#launchProject(org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters)
	 */
	public Object launchProject(LaunchProjectParameters launchProjectParameters)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#previewProject(org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters)
	 */
	public Object previewProject(
			PreviewProjectParameters previewProjectParameters) throws Exception {
		return this.projectCommunicator.previewProject((ExternalProject) previewProjectParameters.getProject());
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#updateProject(org.telscenter.sail.webapp.domain.project.Project)
	 */
	public void updateProject(Project project) {
		// TODO Auto-generated method stub

	}

	public ProjectCommunicator getProjectCommunicator() {
		return projectCommunicator;
	}

	public void setProjectCommunicator(ProjectCommunicator projectCommunicator) {
		this.projectCommunicator = projectCommunicator;
	}

	/**
	 * @return the projectDao
	 */
	public ProjectDao<Project> getProjectDao() {
		return projectDao;
	}

	/**
	 * @param projectDao the projectDao to set
	 */
	public void setProjectDao(ProjectDao<Project> projectDao) {
		this.projectDao = projectDao;
	}

}
