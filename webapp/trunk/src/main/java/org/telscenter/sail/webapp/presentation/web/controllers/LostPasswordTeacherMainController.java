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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;

/**
 * Controller for lost password teacher username and email lookup
 * 
 * @author Anthony Perritano
 * @version
 */
public class LostPasswordTeacherMainController extends SimpleFormController {

	private static final String NEW_PASSWORD = "newPassword";
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

		String username = null;
		String emailAddress = null;
		try {

			username = StringUtils.trimToNull(userDetails.getUsername());
			emailAddress = StringUtils
					.trimToNull(userDetails.getEmailAddress());
			if (username != null) {

				User user = userService.retrieveUserByUsername(userDetails
						.getUsername());
				
				// generate a new password
				// set it on the userobject
				String generateRandomPassword = generateRandomPassword();
				user.getUserDetails().setPassword(generateRandomPassword);
			
				userService.updateUser(user);
				
				//send password in the email here
				
				Map model = new HashMap();
			    model.put(NEW_PASSWORD, generateRandomPassword);
				return new ModelAndView(getSuccessView(), model);
			} else if (emailAddress != null) {

				User user = userService
						.retrieveUserByEmailAddress(emailAddress);
				
				String generateRandomPassword = generateRandomPassword();
				user.getUserDetails().setPassword(generateRandomPassword);
			
				userService.updateUser(user);
			
				//send password in the email here
				
				Map model = new HashMap();
			    model.put(NEW_PASSWORD, generateRandomPassword);
				return new ModelAndView(getSuccessView(), model);
			}
			
		} catch (EmptyResultDataAccessException e) {

			if (username != null) {
				ModelAndView modelAndView = new ModelAndView(
						"lostpasswordteachererror");
				modelAndView.addObject("someValue", userDetails.getUsername());
				return modelAndView;
			} else if (emailAddress != null) {
				ModelAndView modelAndView = new ModelAndView(
						"lostpasswordteachererror");
				modelAndView.addObject("someValue", userDetails
						.getEmailAddress());
				return modelAndView;
			}// if

		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * generate random password
	 * 
	 * @return
	 */
	public static String generateRandomPassword() {
		//return RandomStringUtils.random(8);
		Random rnd = new Random();
		return Integer.toString(rnd.nextInt(), 27);
	}

	public static void main(String[] args) {
		System.out.println("New Password: " + generateRandomPassword());
	}

}
