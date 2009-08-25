/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;

import net.sf.sail.webapp.domain.User;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Laurel Williams
 *
 * @version $Id$
 * 
 * A utility class for use by all controllers.
 * 
 */
public class ControllerUtil {

	public final static String USER_KEY = "user";

	public static void addUserToModelAndView(HttpServletRequest request,
			ModelAndView modelAndView) {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
		modelAndView.addObject(USER_KEY, user);
	}
	
	public static User getSignedInUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
	}
	
	/*
	 * ex: http://128.32.xxx.11:8080
	 * or, http://wise3.telscenter.org if request.header is wise.telscenter.org
	 */
	public static String getBaseUrlString(HttpServletRequest request) {
		String host = request.getHeader("Host");
		String portalUrl = request.getScheme() + "://" + request.getServerName() + ":" +
		request.getServerPort();
		
		if (host != null) {
			portalUrl = request.getScheme() + "://" + host;
		}
		
		return portalUrl;
	}

	/*
	 * ex: http://128.32.xxx.11:8080/webapp
	 */
	public static String getPortalUrlString(HttpServletRequest request) {
		String portalUrl = ControllerUtil.getBaseUrlString(request) + request.getContextPath();
		
		return portalUrl;
	}
}
