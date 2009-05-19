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
package org.telscenter.sail.webapp.presentation.web.controllers.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.file.impl.AuthoringJNLPModifier;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.announcement.Announcement;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.impl.RooloOtmlModuleImpl;
import org.telscenter.sail.webapp.domain.impl.UrlModuleImpl;
import org.telscenter.sail.webapp.domain.run.StudentRunInfo;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;
import org.telscenter.sail.webapp.service.module.ModuleService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

import roolo.elo.api.IELO;

/**
 * Controller for handling student VLE-portal interactions.
 * - launch vle, pass in contentbaseurl, load student data url, etc.
 * 
 * @author hirokiterashima
 * @version $Id$
 */
public class StudentVLEController extends AbstractController {

	private RunService runService;
	
	private StudentService studentService;
	
	private WorkgroupService workgroupService;
	
	private ModuleService moduleService;
	
	private BrainstormService brainstormService;

	private HttpRestTransport httpRestTransport;
	
	protected final static String CURRENT_STUDENTRUNINFO_LIST_KEY = "current_run_list";

	protected final static String ENDED_STUDENTRUNINFO_LIST_KEY = "ended_run_list";

	protected final static String HTTP_TRANSPORT_KEY = "http_transport";

	protected final static String WORKGROUP_MAP_KEY = "workgroup_map";
	
	private static final String VIEW_NAME = "student/vle/vle";
	
	private final static String CURRENT_DATE = "current_date";

	static final String DEFAULT_PREVIEW_WORKGROUP_NAME = "Your test workgroup";

	private static final String RUNID = "runId";
	
	private static final String RUN_STATUS_ID = "runSettingsId";

	private static final String SHOW_NEW_ANNOUNCEMENTS = "showNewAnnouncements";
	
	private static final String SUMMARY = "summary";

	private static final String WORKGROUP_ID_PARAM = "workgroupId";


	/** 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	ModelAndView modelAndView = new ModelAndView();
    	
    	Long runId = Long.parseLong(request.getParameter(RUNID));
		Run run = this.runService.retrieveById(runId);
		
    	String runIdStr = request.getParameter(RUNID);

    	modelAndView = new ModelAndView(VIEW_NAME);
    	ControllerUtil.addUserToModelAndView(request, modelAndView);
    	User user = (User) request.getSession().getAttribute(
    			User.CURRENT_USER_SESSION_KEY);

    	List<Workgroup> workgroupListByOfferingAndUser 
	    = workgroupService.getWorkgroupListByOfferingAndUser(run, user);
	
    	Workgroup workgroup = workgroupListByOfferingAndUser.get(0);
    	
    	modelAndView.addObject(RUNID, runIdStr);
    	modelAndView.addObject("run", run);
    	modelAndView.addObject("workgroup", workgroup);
    	modelAndView.addObject("user", user);

		String action = request.getParameter("action");
    	if (action != null && action.equals("getUserInfo")) {
        	// if getUserInfo is requested, return xmlString instead in the response
        	return handlePrintUserInfo(request, response, run, user, workgroup);
    	} else if (action != null && action.equals("getData")) {
    		return handleGetData(request, response, run, user, workgroup);
    	} else  if (action != null && action.equals("postData")) {
    		return handlePostData(request, response, run, user, workgroup);
    	} else {
        	return handleLaunchVLE(request, modelAndView, run, workgroup);
    	}
	}

	/**
	 * @param request
	 * @param response
	 * @param run
	 * @param user
	 * @param workgroup
	 * @return
	 */
	private ModelAndView handlePostData(HttpServletRequest request,
			HttpServletResponse response, Run run, User user,
			Workgroup workgroup) {
		String baseurl = ControllerUtil.getBaseUrlString(request);
		ModelAndView modelAndView = new ModelAndView("forward:" + baseurl + "/vlewrapper/postdata.html");
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @param run
	 * @param user
	 * @param workgroup
	 * @return
	 */
	private ModelAndView handleGetData(HttpServletRequest request,
			HttpServletResponse response, Run run, User user,
			Workgroup workgroup) {
		String baseurl = ControllerUtil.getBaseUrlString(request);
		ModelAndView modelAndView = new ModelAndView("forward:" + baseurl + "/vlewrapper/getdata.html");
		return modelAndView;
	}

	/**
	 * @param request
	 * @param modelAndView
	 * @param run
	 * @param workgroup
	 * @return
	 */
	private ModelAndView handleLaunchVLE(HttpServletRequest request,
			ModelAndView modelAndView, Run run, Workgroup workgroup) {
		String portalurl = ControllerUtil.getBaseUrlString(request);

		String vleurl = portalurl + "/vlewrapper/vle.html";
		
		String contentUrl = (String) run.getProject().getCurnit().accept(new CurnitGetCurnitUrlVisitor());
		
		int lastIndexOfSlash = contentUrl.lastIndexOf("/");
		String contentBaseUrl = contentUrl.substring(0, lastIndexOfSlash);
		String portalVLEControllerUrl = portalurl + "/webapp/student/vle/vle.html?runId=" + run.getId();
		String userInfoUrl = portalVLEControllerUrl + "&action=getUserInfo";
		String getDataUrl = portalurl + "/vlewrapper/getdata.html";
		String postDataUrl = portalurl + "/vlewrapper/postdata.html";
		modelAndView.addObject("vleurl",vleurl);
		modelAndView.addObject("userInfoUrl", userInfoUrl);
		modelAndView.addObject("contentUrl", contentUrl);
		modelAndView.addObject("contentBaseUrl", contentBaseUrl);
		modelAndView.addObject("getDataUrl", getDataUrl);
		modelAndView.addObject("postDataUrl", postDataUrl);
		
		return modelAndView;
	}

	/**
	 * If the workgroupId is specified in the request, then look up userInfo for that specified workgroup.
	 * Otherwise, lookup userInfo for the currently-logged-in user.
	 * @param request
	 * @param response
	 * @param run Which run to look up.
	 * @param user Currently-logged in user
	 * @param workgroup workgroup that the currently-logged in user is in for the run
	 * @return
	 * @throws ObjectNotFoundException
	 * @throws IOException
	 */
	private ModelAndView handlePrintUserInfo(HttpServletRequest request,
			HttpServletResponse response, Run run, User user,
			Workgroup workgroup) throws ObjectNotFoundException, IOException {
		
		String workgroupIdStr = request.getParameter(WORKGROUP_ID_PARAM);
		if (workgroupIdStr != null && workgroupIdStr != "") {
			workgroup = workgroupService.retrieveById(new Long(workgroupIdStr));
			// if a workgroup was specified that was not for this run, return BAD_REQUEST
			if (workgroup.getOffering().getId() != run.getId()) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}
		} else {
		}
		
		
		User teacher = run.getOwners().iterator().next();

		String userInfoString = "<userInfo>";
		
		// add this user's info:
		userInfoString += "<myUserInfo><workgroupId>" + workgroup.getId() + "</workgroupId><userName>" + workgroup.getGroup().getName() + "</userName></myUserInfo>";
		
		// add the class info:
		userInfoString += "<myClassInfo>";
		    		
		// now add classmates
		Set<Workgroup> workgroups = runService.getWorkgroups(run.getId());
		
		String requestedWorkgroupIdsStr = request.getParameter("workgroupIds");
		if (requestedWorkgroupIdsStr != null) {
			// specific workgroups are requested
			String[] requestedWorkgroupIds = requestedWorkgroupIdsStr.split(",");
			for (Workgroup classmateWorkgroup : workgroups) {
				if (classmateWorkgroup.getId() != workgroup.getId() && !((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
					for (String requestedWorkgroupId : requestedWorkgroupIds) {
						if (requestedWorkgroupId.equals(classmateWorkgroup.getId().toString())) {
							userInfoString += "<classmateUserInfo>";
							userInfoString += "<workgroupId>" + classmateWorkgroup.getId() + "</workgroupId>";
							userInfoString += "<userName>" + classmateWorkgroup.generateWorkgroupName() + "</userName>";
							userInfoString += "</classmateUserInfo>";
						}
					}
				}
			}
		} else {
			// otherwise get all classmates (excluding teacher)
			for (Workgroup classmateWorkgroup : workgroups) {
				if (classmateWorkgroup.getId() != workgroup.getId() && !((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
					userInfoString += "<classmateUserInfo>";
					userInfoString += "<workgroupId>" + classmateWorkgroup.getId() + "</workgroupId>";
					userInfoString += "<userName>" + classmateWorkgroup.generateWorkgroupName() + "</userName>";
					userInfoString += "</classmateUserInfo>";
				}
			}

		}
		
		// add teacher info
		for (Workgroup classmateWorkgroup : workgroups) {
			if (((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
				// inside, add teacher info
				userInfoString += "<teacherUserInfo><workgroupId>" + classmateWorkgroup.getId() + "</workgroupId><userName>" + teacher.getUserDetails().getUsername() + "</userName></teacherUserInfo>";
			}
		}

		userInfoString += "</myClassInfo>";

		userInfoString += "</userInfo>";
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);
		
		response.setContentType("text/xml");
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().print(userInfoString);
		return null;
	}
	
	/**
	 * @param runService the runService to set
	 */
	@Required
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	@Required
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @param httpRestTransport
	 *            the httpRestTransport to set
	 */
	@Required
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	@Required
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}
	
	/**
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
}
