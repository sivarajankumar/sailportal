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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.impl.RooloOtmlModuleImpl;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for handling an HTTP-POSTed Project Content
 * 
 * The project is POSTed to this controller, and the controller
 * saves it and returns a success view if save is successful.
 * If save is not success, returns a failed view.
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class PostProjectController extends AbstractController {

	private static final String PROJECT_ID_PARAM = "projectId";
	
	private static final String OTML_CONTENT_PARAM = "otmlcontent";
	
	private ProjectService projectService;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String projectId = request.getParameter(PROJECT_ID_PARAM);
		String otmlString = request.getParameter(OTML_CONTENT_PARAM);
		
		Project project = projectService.getById(projectId);
		
//		File oldOtmlFile = ((RooloOtmlModuleImpl) project.getCurnit()).getProxy().getContent().getOtmlFile();
//
//		// need to generate new file path, otherwise won't save in jackrabbit...
//		// need to fix this in jackrabbit.
//		Random rand = new Random();
//		File otmlFile = new File(oldOtmlFile.getPath() + rand.nextInt());
//		FileWriter out = new FileWriter(otmlFile);
//		out.write(otmlString);
//		out.close();
		byte [] oldOtmlData = ((RooloOtmlModuleImpl) project.getCurnit()).getProxy().getContent().getBytes();
		((RooloOtmlModuleImpl) project.getCurnit()).getProxy().getContent().setBytes(oldOtmlData);		
		projectService.updateProject(project);		
		
		return null;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

}
