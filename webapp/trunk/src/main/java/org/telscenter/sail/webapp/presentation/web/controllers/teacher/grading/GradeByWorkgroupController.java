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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;
import org.telscenter.sail.webapp.domain.project.impl.ProjectTypeVisitor;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * Controller to display the teacher's interface for grading
 * students' work by workgroup
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class GradeByWorkgroupController extends AbstractController {

	private static final String PARAMNAME_RUNID = "runId";

	private static final String PARAMNAME_WORKGROUPID = "workgroupId";

	private static final String MODELNAME_AGGREGATE = "aggregate";

	private static final String MODELNAME_CURNITMAP = "curnitmap";
	
	private static final String GRADE_BY_WORKGROUP_URL = "gradeByWorkgroupUrl";

	private static final String CONTENT_URL = "contentUrl";
	
	private static final String USER_INFO_URL = "userInfoUrl";

	private static final String GET_DATA_URL = "getDataUrl";

	private static final String WORKGROUP = "workgroup";
	private RunService runService;
	
	private GradingService gradingService;
	
	private WISEWorkgroupService workgroupService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String runIdStr = request.getParameter(PARAMNAME_RUNID);
		String workgroupIdStr = request.getParameter(PARAMNAME_WORKGROUPID);
		Long runId = new Long(runIdStr);
		Run run = runService.retrieveById(runId);

		ProjectTypeVisitor typeVisitor = new ProjectTypeVisitor();
		String result = (String) run.getProject().accept(typeVisitor);
		if (result.equals("LDProject")) {
			// LDProject, get the .project file
			String portalurl = ControllerUtil.getBaseUrlString(request);

			
	    	String gradebyworkgroupurl = portalurl + "/vlewrapper/vle/gradebyworkgroup.html";

	    	String vleurl = portalurl + "/vlewrapper/vle/vle_ld_project.html";
	    	//String contentUrl = portalurl + "/vlewrapper/vle/tim2.otml";
	    	String contentBaseUrl = portalurl + "/vlewrapper/curriculum/unit4/lesson22";
	    	String contentUrl = contentBaseUrl + "/lesson22.project";
	    	
	    	String userInfoUrl = portalurl + "/webapp/student/vle/vle.html?getUserInfo=true&runId=" + run.getId() + "&workgroupIds=" + workgroupIdStr;
	    	String getDataUrl = portalurl + "/vlewrapper/getdata.html?dataId=" + workgroupIdStr;
	    	
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(PARAMNAME_RUNID, runId);
			modelAndView.addObject(GRADE_BY_WORKGROUP_URL, gradebyworkgroupurl);
			modelAndView.addObject("vleurl", vleurl);
	    	modelAndView.addObject("contentBaseUrl", contentBaseUrl);
			modelAndView.addObject(CONTENT_URL, contentUrl);
			modelAndView.addObject(USER_INFO_URL, userInfoUrl);
			modelAndView.addObject(GET_DATA_URL, getDataUrl);
			
			return modelAndView;
		} else {
			Long workgroupId = new Long(workgroupIdStr);
			WISEWorkgroup workgroup = (WISEWorkgroup) workgroupService.retrieveById(workgroupId);

			GradeWorkByWorkgroupAggregate gradeWorkByWorkgroupAggregate =
				gradingService.getGradeWorkByWorkgroupAggregate(runId, workgroup);
			ECurnitmap curnitmap = gradingService.getCurnitmap(runId);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(PARAMNAME_RUNID, runId);
			modelAndView.addObject(MODELNAME_AGGREGATE, gradeWorkByWorkgroupAggregate);
			modelAndView.addObject(MODELNAME_CURNITMAP, curnitmap);
			return modelAndView;
		}
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param gradingService the gradingService to set
	 */
	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}
	
	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}
