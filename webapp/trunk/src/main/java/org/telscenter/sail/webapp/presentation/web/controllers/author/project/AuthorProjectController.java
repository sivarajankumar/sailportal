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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.file.impl.AuthoringJNLPModifier;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for users with author privileges to author projects
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class AuthorProjectController extends AbstractController {

	private static final String PROJECT_ID_PARAM_NAME = "projectId";

	private AuthoringJNLPModifier modifier;

	private ProjectService projectService;
	
	private HttpRestTransport httpRestTransport;
	
	public static final String JNLP_CONTENT_TYPE = "application/x-java-jnlp-file";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String projectIdStr = request.getParameter(PROJECT_ID_PARAM_NAME);
		Long projectId = Long.valueOf(projectIdStr);
		Project project = projectService.getById(projectId);
		
		String jnlpUrl = project.getJnlp().getSdsJnlp().getUrl();
		String curnitUrl = project.getCurnit().getSdsCurnit().getUrl();
		
		URL jnlpURL = new URL(jnlpUrl);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(jnlpURL.openStream()));
		
		String jnlpString = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			jnlpString += inputLine;
		}
		
		String outputJNLPString = modifier.modifyJnlp(jnlpString, curnitUrl);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);

		String fileName = request.getServletPath();
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		fileName = fileName.substring(0, fileName.indexOf(".")) + ".jnlp";
		response.addHeader("Content-Disposition", "Inline; fileName=" + fileName);

		response.setContentType(JNLP_CONTENT_TYPE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(outputJNLPString);

		return null;
		/*
		AuthorProjectParameters params = new AuthorProjectParameters();
		params.setProject(project);
		params.setHttpServletRequest(request);
		params.setHttpRestTransport(httpRestTransport);

		return (ModelAndView) projectService.authorProject(params);
		*/
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
	 * @param modifier the modifier to set
	 */
	@Required
	public void setModifier(AuthoringJNLPModifier modifier) {
		this.modifier = modifier;
	}

}
