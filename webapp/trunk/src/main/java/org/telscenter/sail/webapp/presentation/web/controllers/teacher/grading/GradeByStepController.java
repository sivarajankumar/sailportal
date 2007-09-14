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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.grading.impl.GradingServiceImpl;
import org.telscenter.sail.webapp.service.gradingtool.CurnitMapService;

/**
 * A Controller for TELS's grade by step
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradeByStepController extends AbstractController {

	public static final String RUN_ID = "runId";
	public static final String CURNIT_MAP = "curnitMap";
	private GradingService gradingService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String runId = request.getParameter(RUN_ID);
		
		if( runId != null ) {
			System.out.println("The runId is "+runId);
			
			GradingService gs = new GradingServiceImpl();
			ECurnitmap curnitMap = gs.getCurnitmap(new Long(runId));
//			EStep step = new EStep();
//			step.getPodUUID()
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

	public GradingService getGradingService() {
		return gradingService;
	}

	

}