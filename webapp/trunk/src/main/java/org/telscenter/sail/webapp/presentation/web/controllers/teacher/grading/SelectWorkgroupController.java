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

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * A Controller for selecting workgroup to grade for
 * GradeByWorkgroup functionality
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class SelectWorkgroupController extends AbstractController {

	private OfferingService offeringService;
	
	protected static final String RUN_ID_PARAM_NAME = "runId";
	
	protected static final String WORKGROUPS_PARAM_NAME = "workgroups";
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String runIdString = request.getParameter(RUN_ID_PARAM_NAME);
		Long runId = new Long(runIdString);
		
		Set<Workgroup> workgroups = offeringService.getWorkgroupsForOffering(runId);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(RUN_ID_PARAM_NAME, runId);
		modelAndView.addObject(WORKGROUPS_PARAM_NAME, workgroups);
		return modelAndView;
	}

	/**
	 * @param offeringService the offeringService to set
	 */
	public void setOfferingService(OfferingService offeringService) {
		this.offeringService = offeringService;
	}

}
