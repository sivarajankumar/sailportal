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
package org.telscenter.sail.webapp.presentation.web.controllers.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.presentation.web.listeners.PasSessionListener;
import net.sf.sail.webapp.service.UserService;

import org.springframework.security.GrantedAuthority;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Sally Ahn
 * @version $Id: $
 */
public class ViewAllUsersController extends AbstractController{

	private UserService userService;

	protected static final String VIEW_NAME = "admin/manageusers";

	protected static final String TEACHERS = "teachers";

	protected static final String STUDENTS = "students";

	protected static final String ADMINS = "admins";

	protected static final String OTHER = "other";

	private static final String USERNAMES = "usernames";
	
	private static final String LOGGED_IN_USERNAMES = "loggedInUsernames";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws Exception {

		ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
		ControllerUtil.addUserToModelAndView(servletRequest, modelAndView);

		String onlyShowLoggedInUser = servletRequest.getParameter("onlyShowLoggedInUser");
		if (onlyShowLoggedInUser != null && onlyShowLoggedInUser.equals("true")) {
			// get logged in users from servlet context
			HashMap<String, User> allLoggedInUsers = 
				(HashMap<String, User>) servletRequest.getSession()
					.getServletContext().getAttribute(PasSessionListener.ALL_LOGGED_IN_USERS);
			ArrayList<String> loggedInUsernames = new ArrayList<String>();
			for (User loggedInUser : allLoggedInUsers.values()) {
				loggedInUsernames.add(loggedInUser.getUserDetails().getUsername());
			}
			modelAndView.addObject(LOGGED_IN_USERNAMES, loggedInUsernames);
		} else {
			//List<User> allUsers = this.userService.retrieveAllUsers();
			List<String> allUsernames = this.userService.retrieveAllUsernames();
			modelAndView.addObject(USERNAMES, allUsernames);
		}
		return modelAndView;
	}

	private boolean isAdmin(GrantedAuthority[] authorities){
		return isRole(authorities, "ROLE_ADMINISTRATOR");
	}

	private boolean isTeacher(GrantedAuthority[] authorities){
		return isRole(authorities, "ROLE_TEACHER");
	}

	private boolean isStudent(GrantedAuthority[] authorities){
		return isRole(authorities, "ROLE_STUDENT");
	}

	private boolean isRole(GrantedAuthority[] authorities, String role){
		for(GrantedAuthority authority : authorities){
			if(authority.getAuthority().equals(role))
				return true;
		}
		return false;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
