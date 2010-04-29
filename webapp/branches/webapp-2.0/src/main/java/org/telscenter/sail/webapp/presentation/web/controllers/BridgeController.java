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
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.presentation.util.json.JSONArray;
import org.telscenter.sail.webapp.presentation.util.json.JSONException;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;
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
		if (ControllerUtil.getSignedInUser() == null) {
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
		User signedInUser = ControllerUtil.getSignedInUser();
		GrantedAuthority[] authorities = signedInUser.getUserDetails().getAuthorities();
		Long signedInUserId = null;
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(UserDetailsService.ADMIN_ROLE)) {
				return true;
			} else if(authority.getAuthority().equals(UserDetailsService.TEACHER_ROLE)) {
				//the signed in user is a teacher
				
				Run run = null;
				try {
					//get the run object
					run = runService.retrieveById(new Long(request.getParameter("runId")));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
				}
				
				if(run == null) {
					//we could not find the run
					return false;
				} else if(this.runService.hasRunPermission(run, signedInUser, BasePermission.WRITE)) {
					//the teacher has write permission for the run so we will allow authorization
					return true;
				} else if(this.runService.hasRunPermission(run, signedInUser, BasePermission.READ)) {
					//the teacher only has read permission for the run
					
					if(method.equals("GET")) {
						//we will allow authorization for GET requests
						return true;
					} else if(method.equals("POST")) {
						//we will deny authorization for POST requests since the teacher only has READ permissions
						return false;
					}
				}
			}
		}
		if (method.equals("GET")) {
			String workgroupIdStr = "";
			
			//only used for annotations
			String fromWorkgroupIdStr = "";
			
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
			
			
			if(runId != null) {
				try {
					//get the run
					Run offering = runService.retrieveById(runId);
					
					//get the workgroup for the signed in user
					List<Workgroup> workgroupListByOfferingAndUser = workgroupService.getWorkgroupListByOfferingAndUser(offering, signedInUser);
					
					//get the workgroup
					Workgroup workgroup = workgroupListByOfferingAndUser.get(0);
					
					//get the workgroup id
					signedInUserId = workgroup.getId();
				} catch (ObjectNotFoundException e1) {
					e1.printStackTrace();
				}
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
				
				//get the fromWorkgroup id
				fromWorkgroupIdStr = request.getParameter("fromWorkgroup");
				canAccessOtherWorkgroups = true;
			} else if(type.equals("brainstorm")) {
				workgroupIdStr = request.getParameter("userId");
				canAccessOtherWorkgroups = true;
			} else if (type.equals("journal")) {
				workgroupIdStr = request.getParameter("workgroupId");
			} else if(type.equals("peerreview")) {
				//return true for now until logic is implemented
				return true;
			} else if(type.equals("xlsexport")) {
				//TODO: need to check user permissions
				return true;
			} else {
				// this should never happen
			}

			if (workgroupIdStr == null || workgroupIdStr.equals("")) {
				return false;
			}
			//split up all the workgroup ids
			String[] workgroupIds = workgroupIdStr.split(":");
			
			//check if this GET request can access other workgroups
			if(canAccessOtherWorkgroups) {
				//this GET request is allowed to access other workgroup work
				try {
					if(fromWorkgroupIdStr != null && !fromWorkgroupIdStr.equals("") &&
							fromWorkgroupIdStr.equals(signedInUserId)) {
						/*
						 * the signed in user id is the same as the from workgroup id so 
						 * we will allow it. this basically means the current user is
						 * requesting the annotations that he/she wrote.
						 */
						return true;
					} else {
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
					}
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
		User user = ControllerUtil.getSignedInUser();
		CredentialManager.setRequestCredentials(request, user);
		
		//get the run id
		String runIdString = request.getParameter("runId");
		Long runId = null;
		
		if(runIdString != null) {
			runId = Long.parseLong(runIdString);
		}
		
		Run run = null;
		try {
			if(runId != null) {
				//get the run object
				run = runService.retrieveById(runId);				
			}
		} catch (ObjectNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (type == null) {
			// get student data
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/getdata.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("brainstorm")){
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/getdata.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("flag") || type.equals("annotation")){			// get flags
			/*
			 * set the user info JSONObjects into the request so the vlewrapper servlet
			 * has access to the teacher and classmate info
			 */
			setUserInfos(run, request);
			
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/annotations.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("journal")) {
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/journaldata.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("peerreview")) {
			//get the period id
			String periodString = request.getParameter("periodId");
			Long period = null;
			if(periodString != null) {
				period = Long.parseLong(periodString);	
			}
			
			try {
				/*
				 * set the number of students in the class period for when we need
				 * to calculate peer review opening
				 */
				Set<Workgroup> classmateWorkgroups = runService.getWorkgroups(runId, period);
				request.setAttribute("numWorkgroups", classmateWorkgroups.size());
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/peerreview.html");
			requestDispatcher.forward(request, response);
		} else if (type.equals("xlsexport")) {
			setUserInfos(run, request);
			
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/getxls.html");
			requestDispatcher.forward(request, response);
		}
		return null;
	}

	private ModelAndView handlePost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		ServletContext servletContext2 = this.getServletContext();
		ServletContext vlewrappercontext = servletContext2.getContext("/vlewrapper");
		User user = ControllerUtil.getSignedInUser();
		CredentialManager.setRequestCredentials(request, user);
		
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
		} else if (type.equals("peerreview")) {
			RequestDispatcher requestDispatcher = vlewrappercontext.getRequestDispatcher("/peerreview.html");
			requestDispatcher.forward(request, response);
		}
		return null;
	}
	
	/**
	 * Sets the classmate, teacher and shared teacher user infos
	 * into the request object so they can be retrieved by the
	 * vlewrapper servlets
	 * @param run
	 * @param request
	 */
	private void setUserInfos(Run run, HttpServletRequest request) {
		//get the classmate user infos
		JSONArray classmateUserInfosJSONArray = getClassmateUserInfos(run);
		
		//get the teacher info
		JSONObject teacherUserInfoJSONObject = getTeacherUserInfo(run);
		
		//get the shared teacher infos
		JSONArray sharedTeacherUserInfosJSONArray = getSharedTeacherUserInfos(run);
		
		//set the JSON objects to request attributes so the vlewrapper servlet can access them
		request.setAttribute("classmateUserInfos", classmateUserInfosJSONArray.toString());
		request.setAttribute("teacherUserInfo", teacherUserInfoJSONObject.toString());
		request.setAttribute("sharedTeacherUserInfos", sharedTeacherUserInfosJSONArray.toString());
	}
	
	/**
	 * Get the classmate user info in a JSONArray
	 * @param run
	 * @return a JSONArray containing classmate info
	 */
	private JSONArray getClassmateUserInfos(Run run) {
		JSONArray classmateUserInfosJSONArray = new JSONArray();
		
		//get the workgroups in the run
		Set<Workgroup> workgroups = null;
		try {
			workgroups = runService.getWorkgroups(run.getId());
		} catch (ObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(workgroups != null) {
			//loop through all the workgroups in the run and add all the classmate workgroups
			for(Workgroup workgroup : workgroups) {
				try {
					JSONObject classmateJSONObject = new JSONObject();
					
					if(!((WISEWorkgroup) workgroup).isTeacherWorkgroup()) {
						//the workgroup is a student workgroup
						classmateJSONObject.put("workgroupId", ((WISEWorkgroup) workgroup).getId());
						
						if(((WISEWorkgroup) workgroup).getPeriod() != null) {
							classmateJSONObject.put("periodId", ((WISEWorkgroup) workgroup).getPeriod().getId());	
						} else {
							classmateJSONObject.put("periodId", JSONObject.NULL);
						}
						
						//add the student to the list of classmates array
						classmateUserInfosJSONArray.put(classmateJSONObject);	
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return classmateUserInfosJSONArray;
	}
	
	/**
	 * Get the teacher user info in a JSONObject
	 * @param run the run object
	 * @return a JSONObject containing the teacher user info such as workgroup id
	 * and name
	 */
	private JSONObject getTeacherUserInfo(Run run) {
		//the JSONObject that will hold the owner teacher user info
		JSONObject teacherUserInfo = new JSONObject();
		
		if(run != null) {
			//get the owners of the run (there should only be one)
			Iterator<User> ownersIterator = run.getOwners().iterator();
			
			//loop through the owners (there should only be one)
			while(ownersIterator.hasNext()) {
				//get an owner
				User owner = ownersIterator.next();
				
				//get the workgroups
				List<Workgroup> teacherWorkgroups = workgroupService.getWorkgroupListByOfferingAndUser(run, owner);
				
				//there should only be one workgroup for the owner
				Workgroup teacherWorkgroup = teacherWorkgroups.get(0);
				
				try {
					//set the values into the owner JSONObject
					teacherUserInfo.put("workgroupId", teacherWorkgroup.getId());
					teacherUserInfo.put("userName", teacherWorkgroup.generateWorkgroupName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return teacherUserInfo;
	}
	
	/**
	 * Get an array of shared teacher user infos in a JSONArray
	 * @param run the run object
	 * @return a JSONArray containing shared teacher user infos
	 */
	private JSONArray getSharedTeacherUserInfos(Run run) {

		//the JSONArray that will hold the shared teacher user infos
		JSONArray sharedTeacherUserInfos = new JSONArray();
		
		if(run != null) {
			//get the shared owners
			Iterator<User> sharedOwnersIterator = run.getSharedowners().iterator();
			
			//loop through the shared owners
			while(sharedOwnersIterator.hasNext()) {
				//get a shared owner
				User sharedOwner = sharedOwnersIterator.next();
				
				//get the workgroups
				List<Workgroup> sharedTeacherWorkgroups = workgroupService.getWorkgroupListByOfferingAndUser(run, sharedOwner);
				
				//there should only be one workgroup for the shared owner
				Workgroup sharedTeacherWorkgroup = sharedTeacherWorkgroups.get(0);
				
				//make a JSONObject for this shared owner
				JSONObject sharedTeacherUserInfo = new JSONObject();
				
				try {
					//set the values into the shared owner JSONObject
					sharedTeacherUserInfo.put("workgroupId", sharedTeacherWorkgroup.getId());
					sharedTeacherUserInfo.put("userName", sharedTeacherWorkgroup.generateWorkgroupName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//add the shared owner to the array
				sharedTeacherUserInfos.put(sharedTeacherUserInfo);
			}
		}

		return sharedTeacherUserInfos;
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
