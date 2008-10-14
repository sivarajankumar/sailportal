/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.dao.project.ProjectCommunicatorDao;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectCommunicator;
import org.telscenter.sail.webapp.domain.project.impl.ExternalProjectImpl;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.presentation.util.Util;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for previewing a specific project
 * 
 * @author Matt Fishbach
 * @author Hiroki Terashima
 * @version $Id:$
 */
public class PreviewProjectController extends AbstractController {
	
	private static final String PROJECT_COMMUNICATOR_ID_PARAM = "projectCommunicatorId";

	private static final String PROJECT_ID_PARAM_NAME = "projectId";

	private static final String DIY_EXTERNAL_ID_PARAM_NAME = "externalId";
	
	private ProjectService projectService;
	
	private ProjectCommunicatorDao<ProjectCommunicator> diyProjectCommunicator;
	
	private HttpRestTransport httpRestTransport;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String projectCommunicatorId = request.getParameter(PROJECT_COMMUNICATOR_ID_PARAM);
		if (projectCommunicatorId != null) {
			// we are trying to preview an external project
			String diyExternalIdStr = request.getParameter(DIY_EXTERNAL_ID_PARAM_NAME);
			PreviewProjectParameters params = new PreviewProjectParameters();
			ExternalProjectImpl diyProject = new ExternalProjectImpl();
			diyProject.setExternalId(diyExternalIdStr);
			diyProject.setProjectCommunicator(diyProjectCommunicator.getById(projectCommunicatorId));
			params.setProject(diyProject);
			return (ModelAndView) projectService.previewProject(params);
		}
		
		String projectIdStr = request.getParameter(PROJECT_ID_PARAM_NAME);
		Project project = projectService.getById(projectIdStr);
		
		PreviewProjectParameters params = new PreviewProjectParameters();
		params.setProject(project);
		params.setHttpServletRequest(request);
		params.setHttpRestTransport(httpRestTransport);
		params.setPortalUrl(Util.getPortalUrl(request));
		return (ModelAndView) projectService.previewProject(params);
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
	 * @param diyProjectCommunicator the diyProjectCommunicator to set
	 */
	public void setDiyProjectCommunicator(
			ProjectCommunicatorDao<ProjectCommunicator> diyProjectCommunicator) {
		this.diyProjectCommunicator = diyProjectCommunicator;
	}

}
