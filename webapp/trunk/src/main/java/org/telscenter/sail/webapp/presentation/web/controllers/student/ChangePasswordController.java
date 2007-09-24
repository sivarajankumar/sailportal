package org.telscenter.sail.webapp.presentation.web.controllers.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.PeriodNotFoundException;
import org.telscenter.sail.webapp.domain.StudentUserAlreadyAssociatedWithRunException;
import org.telscenter.sail.webapp.domain.impl.AddProjectParameters;
import org.telscenter.sail.webapp.domain.impl.ChangePasswordParameters;
import org.telscenter.sail.webapp.domain.impl.Projectcode;
import org.telscenter.sail.webapp.service.student.StudentService;
import org.telscenter.sail.webapp.service.impl.UserServiceImpl;

public class ChangePasswordController  extends SimpleFormController{


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
   			UserServiceImpl userService = new UserServiceImpl();
   			userService.updateUserPassword(user, params.getPasswd1());
    		modelAndView = new ModelAndView(getSuccessView());
    	} catch (Exception e) {
    		errors.rejectValue("changepassword", "error.illegal-password");
    		return showForm(request, response, errors);
    	}
    	
		return modelAndView;
    }

}
