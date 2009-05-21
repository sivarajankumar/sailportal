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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.project.impl.ProjectTypeVisitor;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * A Controller for TELS's grade by step
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradeByStepController extends AbstractController {

	public static final String RUN_ID = "runId";
	
	public static final String CURNIT_MAP = "curnitMap";

	private static final String GRADE_BY_STEP_URL = "gradeByStepUrl";

	private static final String CONTENT_URL = "contentUrl";
	
	private static final String USER_INFO_URL = "userInfoUrl";

	private static final String GET_DATA_URL = "getDataUrl";

	private static final String WORKGROUP = "workgroup";

	private GradingService gradingService;
	
	private RunService runService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String runId = request.getParameter(RUN_ID);
		Run run = runService.retrieveById(new Long(runId));
		ProjectTypeVisitor typeVisitor = new ProjectTypeVisitor();
		String result = (String) run.getProject().accept(typeVisitor);
		
		if (result.equals("LDProject")) {
			// LDProject, get the .project file
			String portalurl = ControllerUtil.getBaseUrlString(request);

	    	String gradebystepurl = portalurl + "/vlewrapper/vle/gradebystep.html";
	    	
	    	//String contentUrl = portalurl + "/vlewrapper/vle/tim2.otml";
	    	String contentBaseUrl = portalurl + "/vlewrapper/curriculum/unit4/lesson22";
	    	String contentUrl = contentBaseUrl + "/lesson22.project.xml";
	    	
	    	String userInfoUrl = portalurl + "/webapp/student/vle/vle.html?action=getUserInfo&runId=" + run.getId();
	    	String getDataUrl = portalurl + "/vlewrapper/getdata.html";
	    	
	    	String getAnnotationsUrl = portalurl + "/vlewrapper/getannotations.html?&runId=" + runId;
	    	String postAnnotationsUrl = portalurl + "/vlewrapper/postannotations.html";

	    	ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(RUN_ID, runId);
			modelAndView.addObject(GRADE_BY_STEP_URL, gradebystepurl);
			modelAndView.addObject(CONTENT_URL, contentUrl);
			modelAndView.addObject(USER_INFO_URL, userInfoUrl);
			modelAndView.addObject(GET_DATA_URL, getDataUrl);
			modelAndView.addObject("contentBaseUrl", contentBaseUrl);
			modelAndView.addObject("getAnnotationsUrl", getAnnotationsUrl);
			modelAndView.addObject("postAnnotationsUrl", postAnnotationsUrl);
			modelAndView.addObject("runId", runId);
			
			return modelAndView;
		}
		else 
		if( runId != null ) {
			ECurnitmap curnitMap = gradingService.getCurnitmap(new Long(runId));
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(RUN_ID, runId);
			modelAndView.addObject(CURNIT_MAP, curnitMap);
			
			return modelAndView;
		} else {
			
			//throw error
			
		}// if
		
		ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
	}

	/**
	 * @param gradingService the gradingService to set
	 */
	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
}