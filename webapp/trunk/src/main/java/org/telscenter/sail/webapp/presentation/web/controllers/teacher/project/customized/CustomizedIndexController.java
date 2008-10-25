/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project.customized;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;

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
		 ModelAndView modelAndView = new ModelAndView();
		 User user = (User) request.getSession().getAttribute(
					User.CURRENT_USER_SESSION_KEY);
		 List<Project> ownedProjectsList = this.projectService.getProjectList(user);
	     modelAndView.addObject("ownedProjectsList", ownedProjectsList);
	     List<Project> sharedProjectsList = this.projectService.getProjectList();
	     sharedProjectsList.removeAll(ownedProjectsList);
	     modelAndView.addObject("sharedProjectsList", sharedProjectsList);
		 return modelAndView;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
