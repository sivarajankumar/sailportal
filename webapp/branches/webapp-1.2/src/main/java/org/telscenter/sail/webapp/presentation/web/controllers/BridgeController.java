/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
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

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.security.GrantedAuthority;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * Controller to bridge GET/POST access to the vlewrapper webapp. Validates
 * logged in user, makes sure they're logged in and has the right
 * permissions, etc, before forwarding the request to the appropriate
 * servlet in the vlewrapper webapp.
 * @author hirokiterashima
 * @version $Id:$
 */
public class BridgeController extends AbstractController {

	private WISEWorkgroupService workgroupService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean authorized = authorize(request);
		if (!authorized) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized to access this page");
			return null;
		}
		String method = request.getMethod();
		if (method.equals("GET")) {
			return handleGet(request, response);
		} else if (method.equals("POST")) {
			return handlePost(request, response);
		}
		
		// we only handle GET and POST requests at this point.
		return null;
	}

	private boolean authorize(HttpServletRequest request) {
		String method = request.getMethod();
		User signedInUser = ControllerUtil.getSignedInUser(request);
		GrantedAuthority[] authorities = signedInUser.getUserDetails().getAuthorities();
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(UserDetailsService.TEACHER_ROLE)
					|| authority.getAuthority().equals(UserDetailsService.ADMIN_ROLE)) {
				return true;
			}
		}
		if (method.equals("GET")) {
			String workgroupIdStr = "";
			String type = request.getParameter("type");
			if (type == null || type.equals("flag")) {
				workgroupIdStr = request.getParameter("userId");
			} else if (type.equals("annotation")) {
				workgroupIdStr = request.getParameter("toWorkgroup");
			} else if (type.equals("journal")) {
				workgroupIdStr = request.getParameter("workgroupId");
			}
			List<Workgroup> workgroupsForUser = workgroupService.getWorkgroupsForUser(signedInUser);
			for (Workgroup workgroup : workgroupsForUser) {
				if (workgroup.getId().toString().equals(workgroupIdStr)) {
					return true;
				}
			}
			return false;
		} else if (method.equals("POST")) {
			return true;
		}
		// other request methods are not authorized at this point
		return false;
	}

	private ModelAndView handleGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ServletContext servletContext2 = this.getServletContext();
		ServletContext vlewrappercontext = servletContext2.getContext("/vlewrapper");
		
		if (type == null) {
			// get student data
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/getdata.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("flag") || type.equals("annotation")){			// get flags
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/annotations.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("journal")) {
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/journaldata.html");
			requestDispatcher.forward(request, response);
		}
		return null;
	}

	private ModelAndView handlePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ServletContext servletContext2 = this.getServletContext();
		ServletContext vlewrappercontext = servletContext2.getContext("/vlewrapper");
		
		if (type == null) {
			// post student data
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/postdata.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("flag") || type.equals("annotation")){
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/annotations.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("journal")) {
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/journaldata.html");
			requestDispatcher.forward(request, response);
		}
		return null;
	}

	/**
	 * @return the workgroupService
	 */
	public WISEWorkgroupService getWorkgroupService() {
		return workgroupService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}
