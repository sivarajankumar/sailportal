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
package org.telscenter.sail.webapp.presentation.web.controllers.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.run.StudentRunInfo;
import org.telscenter.sail.webapp.presentation.web.controllers.student.StartProjectController;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;

/**
 * Controller for Student's index page
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentIndexController extends AbstractController {

	private RunService runService;
	
	private StudentService studentService;

	private HttpRestTransport httpRestTransport;
	
	protected final static String CURRENT_STUDENTRUNINFO_LIST_KEY = "current_run_list";

	protected final static String ENDED_STUDENTRUNINFO_LIST_KEY = "ended_run_list";

	protected final static String HTTP_TRANSPORT_KEY = "http_transport";

	protected final static String WORKGROUP_MAP_KEY = "workgroup_map";
	
	private static final String VIEW_NAME = "student/index";

	static final String DEFAULT_PREVIEW_WORKGROUP_NAME = "Your test workgroup";

	/** 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
    	ControllerUtil.addUserToModelAndView(request, modelAndView);
    	User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
    	
		List<Run> runlist = runService.getRunList(user);
		List<StudentRunInfo> current_run_list = new ArrayList<StudentRunInfo>();
		List<StudentRunInfo> ended_run_list = new ArrayList<StudentRunInfo>();
		
		for (Run run : runlist) {
			StudentRunInfo studentRunInfo = studentService.getStudentRunInfo(user, run);

			// if student is in a workgroup for this run, get the url
			// that will be used to start the project
			if (studentRunInfo.getWorkgroup() != null) {
				String startProjectUrl = 				
					StartProjectController.generateStartProjectUrlString(
							httpRestTransport, request, run, 
							studentRunInfo.getWorkgroup(), 
							StartProjectController.retrieveAnnotationBundleUrl);
				studentRunInfo.setStartProjectUrl(startProjectUrl);
			}

			if (run.isEnded()) {
				ended_run_list.add(studentRunInfo);
			} else {
				current_run_list.add(studentRunInfo);
			}
		}
		
		Collections.sort(current_run_list);
		Collections.sort(ended_run_list);
		
		modelAndView.addObject(CURRENT_STUDENTRUNINFO_LIST_KEY, current_run_list);
		modelAndView.addObject(ENDED_STUDENTRUNINFO_LIST_KEY, ended_run_list);
		modelAndView.addObject(HTTP_TRANSPORT_KEY, this.httpRestTransport);

        return modelAndView;
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
}
