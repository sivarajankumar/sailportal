/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project.customized;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author MattFish
 *
 */
public class CustomizedIndexController extends AbstractController {

	private ProjectService projectService;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 List<Project> projectList = this.projectService.getProjectList();
		 ModelAndView modelAndView = new ModelAndView();
	     modelAndView.addObject("projectList", projectList);
		 return modelAndView;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
