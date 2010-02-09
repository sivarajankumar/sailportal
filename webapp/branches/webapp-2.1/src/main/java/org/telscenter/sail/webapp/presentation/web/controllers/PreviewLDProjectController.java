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
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.presentation.util.json.JSONException;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for handling student VLE-portal interactions for Preview Mode
 * 
 * @author hirokiterashima
 * @version $Id: StudentVLEController.java 2414 2009-07-24 18:34:41Z geoffreykwan $
 */
public class PreviewLDProjectController extends AbstractController {

	private ProjectService projectService;
	
	private Properties portalProperties = null;

	/** 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("getVLEConfig")) {
				return handleGetVLEConfig(request, response);
			} else if (action.equals("getUserInfo")) {
				return handleGetUserInfo(request, response);
			} else {
				// shouldn't get here
				throw new RuntimeException("should not get here");
			}
		} else {
			return handleLaunchVLEPreview(request);
		}
	}

	/**
	 * @param request
	 * @param modelAndView
	 * @param workgroup
	 * @return
	 * @throws ObjectNotFoundException 
	 */
	private ModelAndView handleLaunchVLEPreview(HttpServletRequest request) throws ObjectNotFoundException {
		String portalurl = ControllerUtil.getBaseUrlString(request);
		String portalVLEControllerUrl = portalurl + "/webapp/vle/preview.html";

		String vleConfigUrl = portalVLEControllerUrl + "?projectId=" + request.getParameter("projectId") + "&action=getVLEConfig";

		String vleurl = portalurl + "/vlewrapper/vle/vle.html";

		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("vleurl",vleurl);
    	modelAndView.addObject("vleConfigUrl", vleConfigUrl);
		return modelAndView;
	}

	private ModelAndView handleGetUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws ObjectNotFoundException, IOException {
		
		JSONObject userInfo = new JSONObject();
		
		/*
		JSONObject myUserInfo = new JSONObject();

		try {
			userInfo.put("myUserInfo", myUserInfo);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		try {
			myUserInfo.put("workgroupId", -1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		*/
		
		response.getWriter().print(userInfo);
		
		return null;
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
			HttpServletResponse response) throws ObjectNotFoundException, IOException {

		String projectIdStr = request.getParameter("projectId");
		Project project = projectService.getById(projectIdStr);
		
		String portalurl = ControllerUtil.getBaseUrlString(request);
		
		String curriculumBaseWWW = portalProperties.getProperty("curriculum_base_www");

		String getContentUrl = (String) project.getCurnit().accept(new CurnitGetCurnitUrlVisitor());
		getContentUrl = curriculumBaseWWW + getContentUrl;
		int lastIndexOfSlash = getContentUrl.lastIndexOf("/");
		if(lastIndexOfSlash==-1){
			lastIndexOfSlash = getContentUrl.lastIndexOf("\\");
		}
		
		String getContentBaseUrl = getContentUrl.substring(0, lastIndexOfSlash) + "/";
		String portalVLEControllerUrl = portalurl + "/webapp/vle/preview.html";
		String getUserInfoUrl = portalVLEControllerUrl + "?action=getUserInfo";
		
		JSONObject config = new JSONObject();
		try {
			config.put("mode", "preview");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			config.put("getUserInfoUrl", StringEscapeUtils.escapeHtml(getUserInfoUrl));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			config.put("getContentUrl", StringEscapeUtils.escapeHtml(getContentUrl));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			config.put("getContentBaseUrl", StringEscapeUtils.escapeHtml(getContentBaseUrl));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			config.put("theme", "WISE");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			config.put("enableAudio", "false");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		/*
		String vleConfigString = "<VLEConfig>";
		vleConfigString += "<mode>preview</mode>";
		vleConfigString += "<userInfoUrl>" + StringEscapeUtils.escapeHtml(getUserInfoUrl) + "</userInfoUrl>";
		vleConfigString += "<contentUrl>" + StringEscapeUtils.escapeHtml(getContentUrl) + "</contentUrl>";
		vleConfigString += "<contentBaseUrl>" + StringEscapeUtils.escapeHtml(getContentBaseUrl) + "</contentBaseUrl>";
		vleConfigString += "<theme>WISE</theme>";
		vleConfigString += "<enableAudio>false</enableAudio>";
		vleConfigString += "</VLEConfig>";
		*/
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);
		
		response.setContentType("text/xml");
		response.getWriter().print(config);
		return null;	
	}
	
	/**
	 * Prints out VLE configuration
	 * @param request
	 * @param response
	 * @return
	 * @throws ObjectNotFoundException 
	 * @throws IOException 
	 */
	private ModelAndView handleGetVLEConfig_old(HttpServletRequest request,
			HttpServletResponse response) throws ObjectNotFoundException, IOException {

		String projectIdStr = request.getParameter("projectId");
		Project project = projectService.getById(projectIdStr);
		
		String portalurl = ControllerUtil.getBaseUrlString(request);
		
		String curriculumBaseWWW = portalProperties.getProperty("curriculum_base_www");

		String contentUrl = (String) project.getCurnit().accept(new CurnitGetCurnitUrlVisitor());
		contentUrl = curriculumBaseWWW + contentUrl;
		int lastIndexOfSlash = contentUrl.lastIndexOf("/");
		if(lastIndexOfSlash==-1){
			lastIndexOfSlash = contentUrl.lastIndexOf("\\");
		}
		
		String contentBaseUrl = contentUrl.substring(0, lastIndexOfSlash) + "/";
		String portalVLEControllerUrl = portalurl + "/webapp/vle/preview.html";
		String userInfoUrl = portalVLEControllerUrl + "?action=getUserInfo";
		
		String vleConfigString = "<VLEConfig>";
		vleConfigString += "<mode>preview</mode>";
		vleConfigString += "<userInfoUrl>" + StringEscapeUtils.escapeHtml(userInfoUrl) + "</userInfoUrl>";
		vleConfigString += "<contentUrl>" + StringEscapeUtils.escapeHtml(contentUrl) + "</contentUrl>";
		vleConfigString += "<contentBaseUrl>" + StringEscapeUtils.escapeHtml(contentBaseUrl) + "</contentBaseUrl>";
		vleConfigString += "<theme>WISE</theme>";
		vleConfigString += "<enableAudio>false</enableAudio>";
		vleConfigString += "</VLEConfig>";
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader ("Expires", 0);
		
		response.setContentType("text/xml");
		response.getWriter().print(vleConfigString);
		return null;	
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/**
	 * @param portalProperties the portalProperties to set
	 */
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}
}