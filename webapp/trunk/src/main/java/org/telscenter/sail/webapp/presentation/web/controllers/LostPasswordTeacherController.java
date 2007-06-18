/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for lost password teacher
 * 
 * @author Anthony Perritano
 * @version $Id: $
 */
public class LostPasswordTeacherController extends SimpleFormController {

	protected UserService userService = null;

	/**
	 * gets the information by username or email
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

			User user = userService.retrieveUser(userDetails);

			if (user == null) {
				errors.rejectValue("username", "error.duplicate-username",
						new Object[] { userDetails.getUsername() },
						"username not found try a different");
				return showForm(request, response, errors);
			} else {

				// generate a new password
				// set it on the userobject
				user.getUserDetails().setPassword(generateRandomPassword());

				userService.updateUser(user);
				// update the user in the db
				// send an email

			}

			// get the fields
			// call user service getUserDetails
		} catch (Exception e) {
			errors.rejectValue("username", "error.duplicate-username",
					new Object[] { userDetails.getUsername() },
					"Duplicate Username.");
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

	public static String generateRandomPassword() {
		Random rnd = new Random();
		return Integer.toString( rnd.nextInt(), 27 );
	}

	public static void main(String[] args) {
		System.out.println("New Password: " + generateRandomPassword());
	}

}
