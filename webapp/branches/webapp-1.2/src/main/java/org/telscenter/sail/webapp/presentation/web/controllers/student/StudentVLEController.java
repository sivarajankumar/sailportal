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
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * Controller for handling student VLE-portal interactions.
 * - launch vle, pass in contentbaseurl, load student data url, etc.
 * 
 * @author hirokiterashima
 * @version $Id$
 */
public class StudentVLEController extends AbstractController {

	private RunService runService;
	
	private Properties portalProperties = null;

	private WorkgroupService workgroupService;
	
	protected final static String CURRENT_STUDENTRUNINFO_LIST_KEY = "current_run_list";

	protected final static String ENDED_STUDENTRUNINFO_LIST_KEY = "ended_run_list";

	protected final static String HTTP_TRANSPORT_KEY = "http_transport";

	protected final static String WORKGROUP_MAP_KEY = "workgroup_map";
	
	static final String DEFAULT_PREVIEW_WORKGROUP_NAME = "Your test workgroup";

	private static final String RUNID = "runId";
	
	private static final String WORKGROUP_ID_PARAM = "workgroupId";
	
	private static final String PREVIEW = "preview";

	private static final String GET_RUNINFO_REQUEST_INTERVAL = "-1";   // how long the VLE should wait
	// between each getRunInfo request, in milliseconds 10000=10 seconds, -1=never

	/** 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	Long runId = Long.parseLong(request.getParameter(RUNID));
		Run run = this.runService.retrieveById(runId);
		
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("getUserInfo")) {
				// if getUserInfo is requested, return xmlString instead in the response
				return handlePrintUserInfo(request, response, run);
			} else if (action.equals("getData")) {
				return handleGetData(request, response, run);
			} else  if (action.equals("postData")) {
				return handlePostData(request, response, run);
			} else if (action.equals("getVLEConfig")) {
				return handleGetVLEConfig(request, response, run);
			} else if (action.equals("getRunInfo")) {
				return handleGetRunInfo(request, response, run);
			} else {
				// shouldn't get here
				throw new RuntimeException("should not get here");
			}
		} else {
			return handleLaunchVLE(request, run);
		}
	}

	/**
	 * Retrns the RunInfo XML containing information like whether the run
	 * is paused or messages that teacher wants to send to students.
	 * @param request
	 * @param response
	 * @param run
	 * @return
	 * @throws IOException 
	 */
	private ModelAndView handleGetRunInfo(HttpServletRequest request,
			HttpServletResponse response, Run run) throws IOException {
		String runInfoString = "<RunInfo>" + run.getInfo() + "</RunInfo>";
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("text/xml");
		response.getWriter().print(runInfoString);
		return null;
	}

	/**
	 * Gets the workgroup for the currently-logged in user so that she may
	 * view the VLE.
	 * @param request
	 * @param run
	 * @param user
	 * @return
	 * @throws ObjectNotFoundException
	 */
	private Workgroup getWorkgroup(HttpServletRequest request, Run run)
	throws ObjectNotFoundException {
		Workgroup workgroup = null;
		User user = (User) request.getSession().getAttribute(
    			User.CURRENT_USER_SESSION_KEY);
		List<Workgroup> workgroupListByOfferingAndUser 
		= workgroupService.getWorkgroupListByOfferingAndUser(run, user);

		if (workgroupListByOfferingAndUser.size() > 0) {
			workgroup = workgroupListByOfferingAndUser.get(0);
		} else {
			String previewRequest = request.getParameter(PREVIEW);
			if (previewRequest != null && Boolean.valueOf(previewRequest)) {
				// if this is a preview, workgroupId should be specified, so use
				// that
				String workgroupIdStr = request
						.getParameter(WORKGROUP_ID_PARAM);
				if (workgroupIdStr != null) {
					workgroup = workgroupService.retrieveById(Long
							.parseLong(workgroupIdStr));
				} else {
					workgroup = workgroupService
							.getPreviewWorkgroupForRooloOffering(run, user);
				}
			}
		}
		return workgroup;
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
			HttpServletResponse response, Run run) {
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
			HttpServletResponse response, Run run) {
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
	 * @throws ObjectNotFoundException 
	 */
	private ModelAndView handleLaunchVLE(HttpServletRequest request,
			Run run) throws ObjectNotFoundException {
		String portalurl = ControllerUtil.getBaseUrlString(request);
		String portalVLEControllerUrl = portalurl + "/webapp/student/vle/vle.html?runId=" + run.getId();

		String vleurl = portalurl + "/vlewrapper/vle/vle.html";
		String vleConfigUrl = portalVLEControllerUrl + "&action=getVLEConfig";

		String previewRequest = request.getParameter(PREVIEW);
		if (previewRequest != null && Boolean.valueOf(previewRequest)) {
			vleConfigUrl += "&preview=true";
		}

		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("run", run);
    	modelAndView.addObject("vleurl",vleurl);
    	modelAndView.addObject("vleConfigUrl", vleConfigUrl);
		return modelAndView;
	}

	/**
	 * Prints out VLE configuration
	 * @param request
	 * @param response
	 * @return
	 * @throws ObjectNotFoundException 
	 * @throws IOException 
	 */
	private ModelAndView handleGetVLEConfig(HttpServletRequest request,
			HttpServletResponse response, Run run) throws ObjectNotFoundException, IOException {
		String portalurl = ControllerUtil.getBaseUrlString(request);
		String curriculumBaseWWW = portalProperties.getProperty("curriculum_base_www");

		String contentUrl = (String) run.getProject().getCurnit().accept(new CurnitGetCurnitUrlVisitor());
		contentUrl = curriculumBaseWWW + contentUrl;
		int lastIndexOfSlash = contentUrl.lastIndexOf("/");
		if(lastIndexOfSlash==-1){
			lastIndexOfSlash = contentUrl.lastIndexOf("\\");
		}
		String contentBaseUrl = contentUrl.substring(0, lastIndexOfSlash) + "/";
		String portalVLEControllerUrl = portalurl + "/webapp/student/vle/vle.html?runId=" + run.getId();
		String userInfoUrl = portalVLEControllerUrl + "&action=getUserInfo";
		String getRunInfoUrl = portalVLEControllerUrl + "&action=getRunInfo";

		String previewRequest = request.getParameter(PREVIEW);
		boolean isPreview = false;
		if (previewRequest != null && Boolean.valueOf(previewRequest)) {
			isPreview = true;
		}
		
		if (isPreview) {
			userInfoUrl += "&preview=true";
		}
		
		//String getDataUrl = portalurl + "/vlewrapper/getdata.html";
		//String postDataUrl = portalurl + "/vlewrapper/postdata.html";
		//String getFlagsUrl = portalurl + "/vlewrapper/getflag.html?runId=" + run.getId().toString();
		String getDataUrl = portalurl + "/webapp/bridge/getdata.html";
		String postDataUrl = portalurl + "/webapp/bridge/postdata.html";
		String getFlagsUrl = portalurl + "/webapp/bridge/getdata.html?type=flag&runId=" + run.getId().toString();
    	String annotationsUrl = portalurl + "/webapp/bridge/request.html?type=annotation&runId=" + run.getId().toString();
    	//String annotationsUrl = portalurl + "/vlewrapper/annotations.html?&runId=" + run.getId().toString();
		
    	String postJournalDataUrl = portalurl + "/webapp/bridge/postdata.html?type=journal";
    	//String postJournalDataUrl = portalurl + "/vlewrapper/journaldata.html";
		
		String getJournalDataUrl = portalurl + "/webapp/bridge/getdata.html?type=journal";
		//String getJournalDataUrl = portalurl + "/vlewrapper/journaldata.html";
		
		String vleConfigString = "<VLEConfig>";
		if (isPreview) {
			vleConfigString += "<mode>preview</mode>";
		} else {
			vleConfigString += "<mode>run</mode>";
		}
		vleConfigString += "<runId>" + run.getId().toString() + "</runId>";
		vleConfigString += "<getFlagsUrl>" + StringEscapeUtils.escapeHtml(getFlagsUrl) + "</getFlagsUrl>";
		vleConfigString += "<annotationsUrl>" + StringEscapeUtils.escapeHtml(annotationsUrl) + "</annotationsUrl>";
		vleConfigString += "<userInfoUrl>" + StringEscapeUtils.escapeHtml(userInfoUrl) + "</userInfoUrl>";
		vleConfigString += "<contentUrl>" + StringEscapeUtils.escapeHtml(contentUrl) + "</contentUrl>";
		vleConfigString += "<contentBaseUrl>" + StringEscapeUtils.escapeHtml(contentBaseUrl) + "</contentBaseUrl>";
		vleConfigString += "<getDataUrl>" + StringEscapeUtils.escapeHtml(getDataUrl) + "</getDataUrl>";
		vleConfigString += "<postDataUrl>" + StringEscapeUtils.escapeHtml(postDataUrl) + "</postDataUrl>";
		vleConfigString += "<runInfoUrl>" + StringEscapeUtils.escapeHtml(getRunInfoUrl) + "</runInfoUrl>";
		vleConfigString += "<runInfoRequestInterval>" + GET_RUNINFO_REQUEST_INTERVAL + "</runInfoRequestInterval>";
		vleConfigString += "<theme>WISE</theme>";
		vleConfigString += "<enableAudio>false</enableAudio>";
		vleConfigString += "<getJournalDataUrl>" + StringEscapeUtils.escapeHtml(getJournalDataUrl) + "</getJournalDataUrl>";
		vleConfigString += "<postJournalDataUrl>" + StringEscapeUtils.escapeHtml(postJournalDataUrl) + "</postJournalDataUrl>";
		vleConfigString += "</VLEConfig>";

		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);
		
		response.setContentType("text/xml");
		response.getWriter().print(vleConfigString);
		return null;	
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
			HttpServletResponse response, Run run) throws ObjectNotFoundException, IOException {

		Workgroup workgroup = getWorkgroup(request, run);
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
		
		StringBuffer userInfoString = new StringBuffer("<userInfo>");

		/*
		 * the group id of the period, this is the id in the db which is not
		 * the same as the period number
		 */
		String periodId = "";
		
		//the period number that you would regularly think of as a period
		String periodName = "";
		
		//get the period
		if(workgroup instanceof WISEWorkgroup && !((WISEWorkgroup) workgroup).isTeacherWorkgroup()) {
			//the logged in user is a student
			Group periodGroup = ((WISEWorkgroup) workgroup).getPeriod();
			periodName = periodGroup.getName();
			periodId = periodGroup.getId().toString();
		} else if(((WISEWorkgroup) workgroup).isTeacherWorkgroup()) {
			//the logged in user is a teacher
			
			//string buffers to maintain : delimited values
			StringBuffer periodIds = new StringBuffer();
			StringBuffer periodNames = new StringBuffer();
			
			//get the periods
			Set<Group> periods = run.getPeriods();
			Iterator<Group> periodIter = periods.iterator();
			
			//loop through all the periods
			while(periodIter.hasNext()) {
				Group next = periodIter.next();
				
				//if this is not the first period add a :
				if(periodIds.length() != 0) {
					periodIds.append(":");
				}
				
				//if this is not the first period add a :
				if(periodNames.length() != 0) {
					periodNames.append(":");
				}
				
				//append the values
				periodIds.append(next.getId());	
				periodNames.append(next.getName());
			}
			
			//get the string values
			periodId = periodIds.toString();
			periodName = periodNames.toString();
		}
		
		// add this user's info:
		userInfoString.append("<myUserInfo><workgroupId>" + workgroup.getId() + "</workgroupId><userName>" + workgroup.getGroup().getName().trim() + "</userName><periodId>" + periodId + "</periodId><periodName>" + periodName + "</periodName></myUserInfo>");
		
		// add the class info:
		userInfoString.append("<myClassInfo>");
		    		
		// now add classmates
		Set<Workgroup> workgroups = runService.getWorkgroups(run.getId());
			
		String requestedWorkgroupIdsStr = request.getParameter("workgroupIds");
		if (requestedWorkgroupIdsStr != null) {
			// specific workgroups are requested
			String[] requestedWorkgroupIds = requestedWorkgroupIdsStr.split(",");
			for (Workgroup classmateWorkgroup : workgroups) {
				if (classmateWorkgroup.getMembers().size() > 0 && classmateWorkgroup.getId() != workgroup.getId() && !((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
					for (String requestedWorkgroupId : requestedWorkgroupIds) {
						if (requestedWorkgroupId.equals(classmateWorkgroup.getId().toString())) {
							//get the xml for the classmate and append it
							userInfoString.append(getClassmateUserInfoXML(classmateWorkgroup));
						}
					}
				}
			}
		} else {
			// otherwise get all classmates (excluding teacher)
			for (Workgroup classmateWorkgroup : workgroups) {
				if (classmateWorkgroup.getMembers().size() > 0 && classmateWorkgroup.getId() != workgroup.getId() && !((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
					//get the xml for the classmate and append it
					userInfoString.append(getClassmateUserInfoXML(classmateWorkgroup));
				}
			}

		}
		
		// add teacher info
		for (Workgroup classmateWorkgroup : workgroups) {
			if (((WISEWorkgroup) classmateWorkgroup).isTeacherWorkgroup()) {   // only include classmates, not yourself.
				// inside, add teacher info
				Set<User> owners = run.getOwners();
				User teacher = null;
				if (owners.size() > 0) {
					teacher = owners.iterator().next();
					userInfoString.append("<teacherUserInfo><workgroupId>" + classmateWorkgroup.getId() + "</workgroupId><userName>" + teacher.getUserDetails().getUsername() + "</userName></teacherUserInfo>");
				} else {
					userInfoString.append("<teacherUserInfo><workgroupId>" + classmateWorkgroup.getId() + "</workgroupId><userName>" + classmateWorkgroup.generateWorkgroupName() + "</userName></teacherUserInfo>");
				}
			}
		}

		userInfoString.append("</myClassInfo>");

		userInfoString.append("</userInfo>");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);
		
		response.setContentType("text/xml");
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().print(userInfoString);
		return null;
	}
	
	/**
	 * Get the xml for the classmate user info
	 * @param classmateWorkgroup the workgroup of the classmate
	 * @return an xml string containing the info for the classmate
	 */
	private String getClassmateUserInfoXML(Workgroup classmateWorkgroup) {
		StringBuffer userInfoString = new StringBuffer();
		
		//open tag
		userInfoString.append("<classmateUserInfo>");
		
		//get the workgroup id
		userInfoString.append("<workgroupId>" + classmateWorkgroup.getId() + "</workgroupId>");
		
		//get the user name
		userInfoString.append("<userName>" + classmateWorkgroup.generateWorkgroupName().trim() + "</userName>");
		
		if(classmateWorkgroup instanceof WISEWorkgroup) {
			//get the period id and period name
			userInfoString.append("<periodId>" + ((WISEWorkgroup) classmateWorkgroup).getPeriod().getId() + "</periodId>");
			userInfoString.append("<periodName>" + ((WISEWorkgroup) classmateWorkgroup).getPeriod().getName() + "</periodName>");
		}
		
		//close tag
		userInfoString.append("</classmateUserInfo>");
		
		//return the xml string
		return userInfoString.toString();
	}
	
	/**
	 * @param runService the runService to set
	 */
	@Required
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	@Required
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param portalProperties the portalProperties to set
	 */
	@Required
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}
}
