/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;

/**
 * Controller for the TELS account signup page
 *
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class SignupController extends SimpleFormController {

	  private UserService userService = null;

	  /**
	   * On submission of the signup form, a user is created and saved to the data
	   * store.
	   * 
	   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	   *      org.springframework.validation.BindException)
	   */
	  @Override
	  protected ModelAndView onSubmit(HttpServletRequest request,
	      HttpServletResponse response, Object command, BindException errors)
	      throws Exception {
	    MutableUserDetails userDetails = (MutableUserDetails) command;

	    try {
	      userService.createUser(userDetails);
	    }
	    catch (DuplicateUsernameException e) {
	      errors.rejectValue("username", "error.duplicate-username",
	          new Object[] { userDetails.getUsername() }, "Duplicate Username.");
	      return showForm(request, response, errors);
	    }
	    return new ModelAndView(new RedirectView(getSuccessView()));
	  }
	  
	  /**
	   * Sets the userDetailsService object.
	   * 
	   * @param userDetailsService
	   */
	  public void setUserService(UserService userService) {
	    this.userService = userService;
	  }
}
