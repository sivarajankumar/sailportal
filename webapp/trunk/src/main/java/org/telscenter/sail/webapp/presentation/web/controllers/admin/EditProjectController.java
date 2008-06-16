/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
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
package org.telscenter.sail.webapp.presentation.web.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.impl.ProjectInfoParameters;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author Sally Ahn
 * @version $Id: $
 */
public class EditProjectController extends SimpleFormController{
	
	public EditProjectController() {
		setSessionForm(true);
	}
	
	/**
	 * @override @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("project", projectService.getById(request.getParameter("projectId")));
		model.put("familytags", FamilyTag.values());
		return model;
	}

	private ProjectService projectService;
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		Project project = projectService.getById(request.getParameter("projectId"));
		ProjectInfoParameters params = new ProjectInfoParameters();
		params.setCurrent(project.getProjectInfo().isCurrent());
		params.setFamilytag(project.getProjectInfo().getFamilyTag());
		params.setId(project.getId());
		return params;
	}

	/**
	 * @override @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ProjectInfoParameters params = (ProjectInfoParameters) command;
		
		Project project = projectService.getById(params.getId());
		project.getProjectInfo().setCurrent(params.isCurrent());
		project.getProjectInfo().setFamilyTag(params.getFamilytag());
		projectService.updateProject(project);
		ModelAndView modelAndView = new ModelAndView(new RedirectView(getSuccessView()));		
		return modelAndView;
	}

	/**
	 * @return the projectService
	 */
	public ProjectService getProjectService() {
		return projectService;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	

}
