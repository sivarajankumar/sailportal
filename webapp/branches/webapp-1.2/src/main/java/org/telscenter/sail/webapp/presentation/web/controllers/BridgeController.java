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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.security.GrantedAuthority;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

import edu.emory.mathcs.backport.java.util.Arrays;

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
	private RunService runService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// check if user is logged in
		if (ControllerUtil.getSignedInUser(request) == null) {
			response.sendRedirect("/webapp/login.html");
			return null;
		}
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
			
			String runIdString = request.getParameter("runId");
			Long runId = null;
			
			if(runIdString != null) {
				runId = Long.parseLong(runIdString);
			}
			
			String periodString = request.getParameter("periodId");
			Long period = null;
			if(periodString != null) {
				period = Long.parseLong(periodString);	
			}
			
			//whether this GET request can access other workgroup's data
			boolean canAccessOtherWorkgroups = false;
			
			if (type == null) {
				workgroupIdStr = request.getParameter("userId");
			} else if(type.equals("flag")) {
				workgroupIdStr = request.getParameter("userId");
				canAccessOtherWorkgroups = true;
			} else if (type.equals("annotation")) {
				workgroupIdStr = request.getParameter("toWorkgroup");
				canAccessOtherWorkgroups = true;
			} else if(type.equals("brainstorm")) {
				workgroupIdStr = request.getParameter("userId");
				canAccessOtherWorkgroups = true;
			} else if (type.equals("journal")) {
				workgroupIdStr = request.getParameter("workgroupId");
			}

			//split up all the workgroup ids
			String[] workgroupIds = workgroupIdStr.split(":");
			
			//check if this GET request can access other workgroups
			if(canAccessOtherWorkgroups) {
				//this GET request is allowed to access other workgroup work
				try {
					//obtain all the workgroups of the classmates of the current user
					Set<Workgroup> classmateWorkgroups = runService.getWorkgroups(runId, period);

					/*
					 * see if the workgroupIds the user is trying to access is
					 * in the above set of classmate workgroups, if all the 
					 * workgroupIds beingaccessed are allowed, it will return 
					 * true and allow it, otherwise it will return false and 
					 * deny access
					 */
					return elementsInCollection(workgroupIds, classmateWorkgroups);
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				/*
				 * this GET request is not allowed to access other workgroup work
				 * it can only access the workgroup the current user is in
				 */
				
				//obtain all the workgroups that the current user is in
				List<Workgroup> workgroupsForUser = workgroupService.getWorkgroupsForUser(signedInUser);
				
				/*
				 * see if the workgroupIds the user is trying to access is in
				 * the above list of workgroups, if all the workgroupIds being
				 * accessed are allowed, it will return true and allow it,
				 * otherwise it will return false and deny access
				 */
				return elementsInCollection(workgroupIds, workgroupsForUser);
			}
			
			return false;
		} else if (method.equals("POST")) {
			return true;
		}
		// other request methods are not authorized at this point
		return false;
	}
	
	/**
	 * Checks whether all the elements in the idsAccessing array are
	 * found in the idsAllowed Collection
	 * @param idsAccessing the ids the user is trying to access
	 * @param idsAllowed the ids the user is allowed to access
	 * @return whether all the elements are in the Collection
	 */
	private boolean elementsInCollection(String[] idsAccessing, Collection<Workgroup> idsAllowed) {
		//convert the accessing array to a list
		List<String> idsAccessingList = Arrays.asList(idsAccessing);
		
		//convert the allowed Collection to a list
		List<String> idsAllowedList = new ArrayList<String>();

		//obtain an iterator for the Collection
		Iterator<Workgroup> idsAllowedIter = idsAllowed.iterator();
		
		//loop through all the Workgroups in the Collection
		while(idsAllowedIter.hasNext()) {
			//obtain the workgroup id from the Workgroup
			String idAllowed = idsAllowedIter.next().getId().toString();
			
			//add the workgroup id string to the list of allowed ids
			idsAllowedList.add(idAllowed);
		}
		
		//see if all the elements in the accessing list are in the allowed list
		return idsAllowedList.containsAll(idsAccessingList);
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
		} else if (type.equals("brainstorm")){
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
	
	public RunService getRunService() {
		return runService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}
}
