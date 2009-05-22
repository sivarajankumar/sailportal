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
package org.telscenter.sail.webapp.presentation.web.controllers.author.project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.domain.impl.CurnitParameters;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.curnit.CurnitService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.impl.CreateUrlModuleParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ProjectType;
import org.telscenter.sail.webapp.presentation.util.Util;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for users with author privileges to author projects
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class AuthorProjectController extends AbstractController {

	private static final String PROJECT_ID_PARAM_NAME = "projectId";
	
	private static final String COMMAND = "command";

	private ProjectService projectService;
	
	private HttpRestTransport httpRestTransport;
	
	private CurnitService curnitService;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String projectIdStr = request.getParameter(PROJECT_ID_PARAM_NAME);
		
		Project project;
		if(projectIdStr != null && projectIdStr != ""){
			project = projectService.getById(projectIdStr);
		} else {
			project = null;
		}
		
		AuthorProjectParameters params = new AuthorProjectParameters();
		params.setProject(project);
		params.setHttpServletRequest(request);
		params.setHttpServletResponse(response);
		params.setHttpRestTransport(httpRestTransport);
		params.setPortalUrl(Util.getPortalUrl(request));
		
		String command = request.getParameter(COMMAND);
		if(command != null && command != ""){
			if(command.equals("launchAuthoring")){
				return (ModelAndView) projectService.authorProject(params);
			} else if(command.equals("createProject")){
				return handleCreateProject(request, response);
			} else if(command.equals("projectList")){
				return handleProjectList(request, response);
			}
		}
		
		return (ModelAndView) projectService.authorProject(params);
	}

	private ModelAndView handleCreateProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = request.getParameter("param1");
		String name = request.getParameter("param2");
		User user = (User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY);
		Set<User> owners = new HashSet<User>();
		owners.add(user);
		
		CreateUrlModuleParameters cParams = new CreateUrlModuleParameters();
		cParams.setUrl(path);
		Curnit curnit = curnitService.createCurnit(cParams);
		
		ProjectParameters pParams = new ProjectParameters();
		
		pParams.setCurnitId(curnit.getId());
		pParams.setOwners(owners);
		pParams.setProjectname(name);
		pParams.setProjectType(ProjectType.LD);
		
		Project project = projectService.createProject(pParams);
		response.getWriter().write(project.getId().toString());
		return null;
	}
	
	private ModelAndView handleProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Project> projects = projectService.getProjectList((User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY));
		
		String xmlList = "<projects>";
		for(Project project : projects){
			if(project.getProjectType()==ProjectType.LD){
				xmlList += "<path>" + project.getCurnit().accept(new CurnitGetCurnitUrlVisitor()) + "</path>";
			}
		}
		xmlList += "</projects>";
		
		response.getWriter().write(xmlList);
		return null;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/**
	 * @param httpRestTransport the httpRestTransport to set
	 */
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}

	/**
	 * @param curnitService the curnitService to set
	 */
	public void setCurnitService(CurnitService curnitService) {
		this.curnitService = curnitService;
	}
}
