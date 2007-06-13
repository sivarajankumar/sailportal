package net.sf.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 * A controller to put some basic info into the model for display on most jsp
 * pages. Currently puts the user log in name into the model.
 */
public abstract class BasicInfoController extends AbstractController {

	protected final static String USER_KEY = "user";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		addUserToModelAndView(request, modelAndView);
		return modelAndView;
	}

	private void addUserToModelAndView(HttpServletRequest request,
			ModelAndView modelAndView) {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
		modelAndView.addObject(USER_KEY, user);
	}
}
