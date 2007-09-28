package org.telscenter.sail.webapp.presentation.web.controllers.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.impl.ChangePasswordParameters;

/*
 * TODO: Add class comments, make new file ChangePasswordControllerTest
 */


public class ChangePasswordController  extends SimpleFormController{

	private UserService userService;
	
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
    	ChangePasswordParameters params = (ChangePasswordParameters) command;

    	
    	// write the rest of the code here
    	ModelAndView modelAndView = null;

   	try {
   			userService.updateUserPassword(user, params.getPasswd1());
    		modelAndView = new ModelAndView(getSuccessView());
    	} catch (Exception e) {
    		errors.rejectValue("changepassword", "error.illegal-password");
    		return showForm(request, response, errors);
    	}
    	
		return modelAndView;
    }
    
    public void setUserService(UserService userService) {
    	this.userService = userService;
    }
    
    public UserService getUserService() {
    	return this.userService;
    }

}
