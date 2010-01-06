/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project.customized;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for customized projects index page
 * @author Hiroki Terashima
 * @author MattFish
 */
public class CustomizedIndexController extends AbstractController {

	private ProjectService projectService;
	
	private UserService userService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {		 
		 ModelAndView modelAndView = new ModelAndView();
		 SecurityContext context = SecurityContextHolder.getContext();
		 UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
		 User user = userService.retrieveUser(userDetails);

		 List<Project> ownedProjectsList = this.projectService.getProjectList(user);
	     modelAndView.addObject("ownedProjectsList", ownedProjectsList);
	     List<Project> sharedProjectsList = this.projectService.getSharedProjectList(user);
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
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
