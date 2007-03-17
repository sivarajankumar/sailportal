/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Student Signup controller for the TELS Portal
 *
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class RegisterStudentController extends AbstractController {

	private String redirectView;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView(new RedirectView(getRedirectView()));
	}

	/**
	 * @return the redirectView
	 */
	public String getRedirectView() {
		return redirectView;
	}

	/**
	 * @param redirectView the redirectView to set
	 */
	public void setRedirectView(String redirectView) {
		this.redirectView = redirectView;
	}

}
