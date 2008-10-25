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

import java.io.Serializable;
import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;

import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;
import org.telscenter.sail.webapp.service.project.ProjectService;
import org.telscenter.sail.webapp.service.project.ProjectServiceFactory;

/**
 * TELS Portal can offer multiple types of projects, including:
 *  POD Projects
 *  POTrunk Projects
 *  OTrunk Projects
 *  
 * There is a service for handling each type of project. ProjectServiceImpl
 * uses a factory pattern to determine which projectservice to use at runtime.
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class ProjectServiceImpl implements ProjectService {

	private ProjectServiceFactory projectServiceFactory;
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#authorProject(org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters)
	 */
	public Object authorProject(AuthorProjectParameters authorProjectParameters)
			throws Exception {
		ProjectService projectService = projectServiceFactory.getProjectService(authorProjectParameters.getProject());
		return projectService.authorProject(authorProjectParameters);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#createProject(org.telscenter.sail.webapp.domain.impl.ProjectParameters)
	 */
	public Project createProject(ProjectParameters projectParameters)
			throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(projectParameters.getProjectType());
		return projectService.createProject(projectParameters);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getById(java.lang.Long)
	 */
    @Transactional(readOnly = true)
	public Project getById(Serializable projectId) throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getById(projectId);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList()
	 */
	public List<Project> getProjectList() {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getProjectList();
	}

public List<Project> getProjectList(User user) {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getProjectList(user);
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByInfo(org.telscenter.sail.webapp.domain.project.ProjectInfo)
	 */
	public List<Project> getProjectListByInfo(ProjectInfo info)
			throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getProjectListByInfo(info);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(org.telscenter.sail.webapp.domain.project.FamilyTag)
	 */
	public List<Project> getProjectListByTag(FamilyTag tag)
			throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getProjectListByTag(tag);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(java.lang.String)
	 */
	public List<Project> getProjectListByTag(String tag)
			throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(new ProjectImpl());
		return projectService.getProjectListByTag(tag);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#launchProject(org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters)
	 */
	public Object launchProject(LaunchProjectParameters launchProjectParameters)
			throws Exception {
		ProjectService projectService = projectServiceFactory.getProjectService(launchProjectParameters.getRun().getProject());
		return projectService.launchProject(launchProjectParameters);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#previewProject(org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters)
	 */
	public Object previewProject(
			PreviewProjectParameters previewProjectParameters) throws Exception {
		ProjectService projectService = projectServiceFactory.getProjectService(previewProjectParameters.getProject());
		return projectService.previewProject(previewProjectParameters);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#updateProject(org.telscenter.sail.webapp.domain.project.Project)
	 */
	public void updateProject(Project project) {
		ProjectService projectService = projectServiceFactory.getProjectService(project);
		projectService.updateProject(project);
	}

	/**
	 * @param projectServiceFactory the projectServiceFactory to set
	 */
	public void setProjectServiceFactory(ProjectServiceFactory projectServiceFactory) {
		this.projectServiceFactory = projectServiceFactory;
	}

	/**
	 * @throws ObjectNotFoundException 
	 * @override @see org.telscenter.sail.webapp.service.offering.RunService#addSharedTeacherToRun(org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters)
	 */
	public void addSharedTeacherToProject(
			AddSharedTeacherParameters addSharedTeacherParameters) throws ObjectNotFoundException {
		ProjectService projectService = projectServiceFactory.getProjectService(addSharedTeacherParameters.getProject());
		projectService.addSharedTeacherToProject(addSharedTeacherParameters);
	}
	
	public String getSharedTeacherRole(Project project, User user) {
		ProjectService projectService = projectServiceFactory.getProjectService(project);
		return projectService.getSharedTeacherRole(project, user);
	}


}
