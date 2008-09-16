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
package org.telscenter.sail.webapp.presentation.web.controllers.student.brainstorm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * Displays Brainstorm step for students
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentBrainstormController extends AbstractController {

	private static final String BRAINSTORM_KEY = "brainstorm";
	
	private static final String WORKGROUP = "workgroup";
	
	private static final String BRAINSTORMID_PARAM = "brainstormId";

	private static final String RESTRICTED_VIEW = "student/brainstorm/restricted";

	private static final String NOT_IN_WKGP_MSG = 
		"You cannot see this brainstorm because you are not in a workgroup for this run. Please go back.";

	private static final Object BRAINSTORM_CLOSED_MSG = 
		"This Brainstorm Step has not started yet. Please come back later.";

	private static final String CANNOT_SEE_RESPONSES = "cannotseeresponses";

	private BrainstormService brainstormService;

	private WISEWorkgroupService workgroupService;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);

		String brainstormId = request.getParameter(BRAINSTORMID_PARAM);
		Brainstorm brainstorm = brainstormService.getBrainstormById(brainstormId);
		
		List<Workgroup> workgroupListByOfferingAndUser 
		    = workgroupService.getWorkgroupListByOfferingAndUser(brainstorm.getRun(), user);
		
		// if the user is not in a workgroup for this run, he/she cannot see this brainstorm.
		if (workgroupListByOfferingAndUser.isEmpty()) {
			ModelAndView modelAndView = new ModelAndView(RESTRICTED_VIEW);
			modelAndView.addObject("msg", NOT_IN_WKGP_MSG);
			return modelAndView;
		}
		
		// otherwise let the user see this brainstorm.
		WISEWorkgroup workgroup = (WISEWorkgroup) workgroupListByOfferingAndUser.get(0);
		
		
		// if this brainstorm session hasn't started yet, student cannot see this brainstorm.
		if (!brainstorm.isSessionStarted()) {
			ModelAndView modelAndView = new ModelAndView(RESTRICTED_VIEW);
			modelAndView.addObject("msg", BRAINSTORM_CLOSED_MSG);
			return modelAndView;
		}
		
		// if this brainstorm is gated, and the student has not posted yet,
		// he/she needs to post an answer first.
		if (brainstorm.isGated() && !brainstorm.hasWorkgroupPosted(workgroup)) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(BRAINSTORM_KEY, brainstorm);
			modelAndView.addObject(CANNOT_SEE_RESPONSES, true);
			modelAndView.addObject(WORKGROUP, workgroup);
			return modelAndView;
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(BRAINSTORM_KEY, brainstorm);
		modelAndView.addObject(WORKGROUP, workgroup);
		return modelAndView;
	}

	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

}
