/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project.customized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author MattFish
 * @version $Id:$
 */
public class ShareProjectController extends SimpleFormController {

	private ProjectService projectService;
	
	private UserService userService;
	
	protected static final String PROJECTID_PARAM_NAME = "projectId";

	protected static final String PROJECT_PARAM_NAME = "project";
	
	/**
	 * Adds the AddSharedTeacherParameters object as a form-backing
	 * object. This object will be filled out and submitted for adding
	 * new teachers to the shared teachers list.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		AddSharedTeacherParameters params = new AddSharedTeacherParameters();
		params.setProject(projectService.getById(Long.parseLong(request.getParameter(PROJECTID_PARAM_NAME))));
		params.setPermission(UserDetailsService.PROJECT_READ_ROLE);
		return params;
	}
	
    /**
     * Adds the existing shared teachers and their permissions for
     * the project requested to the page.
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) 
	    throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Project project = projectService.getById(Long.parseLong(request.getParameter(PROJECTID_PARAM_NAME)));
		Set<User> sharedowners = project.getSharedowners();

		for (User sharedowner : sharedowners) {
			String sharedTeacherRole = projectService.getSharedTeacherRole(project, sharedowner);
			AddSharedTeacherParameters addSharedTeacherParameters = 
				new AddSharedTeacherParameters();
			addSharedTeacherParameters.setPermission(sharedTeacherRole);
			addSharedTeacherParameters.setProject(project);
			addSharedTeacherParameters.setSharedOwnerUsername(
					sharedowner.getUserDetails().getUsername());
			model.put(sharedowner.getUserDetails().getUsername(), 
					addSharedTeacherParameters);
		}
		model.put(PROJECT_PARAM_NAME, project);
		
		return model;
	}

	/**
     * On submission of the AddSharedTeacherParameters, the specified
     * teacher is granted the specified permission to the specified project.
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) {
    	AddSharedTeacherParameters params = (AddSharedTeacherParameters) command;
    	User retrievedUser = userService.retrieveUserByUsername(params.getSharedOwnerUsername());
    	ModelAndView modelAndView;

    	if (retrievedUser == null) {
    		modelAndView = new ModelAndView(new RedirectView("shareproject.html"));
	    	modelAndView.addObject(PROJECTID_PARAM_NAME, params.getProject().getId());
	    	modelAndView.addObject("message", "Username not recognized. Make sure to use the exact spelling of the username.");
	    	return modelAndView;
    	} else {
    	try {
			projectService.addSharedTeacherToProject(params);
		} catch (ObjectNotFoundException e) {
			modelAndView = new ModelAndView(new RedirectView(getFormView()));
	    	modelAndView.addObject(PROJECTID_PARAM_NAME, params.getProject().getId());
	    	return modelAndView;
		}
    	modelAndView = new ModelAndView(new RedirectView(getSuccessView()));
    	modelAndView.addObject(PROJECTID_PARAM_NAME, params.getProject().getId());
    	return modelAndView;
    	}
    }
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
