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
package org.telscenter.sail.webapp.presentation.web.controllers.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.ExternalProject;
import org.telscenter.sail.webapp.domain.project.impl.ExternalProjectImpl;
import org.telscenter.sail.webapp.service.project.ExternalProjectService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Imports an external project to the portal.
 * @author hirokiterashima
 * @author scott cytacki
 * @version $Id$
 */
public class ImportExternalProjectController extends AbstractController {

	private static final String PROJECT_TYPE_PARAM = "projectType";
	
	private static final String EXTERNAL_ID_PARAM = "externalId";
	
	private ExternalProjectService projectService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String projectType = request.getParameter(PROJECT_TYPE_PARAM);
		String externalId = request.getParameter(EXTERNAL_ID_PARAM);
		
		// we want a service method to import the project, given those params
		ExternalProject project = new ExternalProjectImpl();
		project.setExternalId(externalId);
		projectService.importProject(project);
		return null;
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ExternalProjectService projectService) {
		this.projectService = projectService;
	}

}
