package org.telscenter.sail.webapp.presentation.web.controllers.teacher.management;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.service.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.impl.ChangeStudentPasswordParameters;

/**
 * @author patricklawler
 * $Id:$
 */
public class ChangeUserPasswordController extends SimpleFormController {

	private UserService userService;
	
	private final static String USER_NAME = "userName";
	
    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        ChangeStudentPasswordParameters params = new ChangeStudentPasswordParameters();
        params.setUser(userService.retrieveUserByUsername(request.getParameter(USER_NAME)));
        return params;
    }
	
	/**
     * On submission of the Change Student's Password form, the associated student's password
     * in the database gets changed to the submitted password
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors){

    	ChangeStudentPasswordParameters params = (ChangeStudentPasswordParameters) command;

    	ModelAndView modelAndView = null;

   		userService.updateUserPassword(params.getUser(), params.getPasswd1());
    	modelAndView = new ModelAndView(getSuccessView());

    	return modelAndView;
    }
    
	/**
	 * @param userService the userService to set
	 */
    public void setUserService(UserService userService) {
    	this.userService = userService;
    }
    
	/**
	 * @param userService the userService to get
	 */
    public UserService getUserService() {
    	return this.userService;
    }
}
