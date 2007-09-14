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

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.emf.sailuserdata.ESessionBundle;

import org.eclipse.emf.common.util.EList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EProject;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.grading.impl.GradingServiceImpl;

/**
 * The actual gradingTool.
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradingToolController extends AbstractController {

	public static final String PODUUID = "podUUID";
	public static final String STEP_AGGREGATE = "stepAggregate";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		ModelAndView modelAndView = new ModelAndView();

		
//		String runId = request.getParameter(GradeByStepController.RUN_ID);
//		String podUUID = request.getParameter(PODUUID);
//		
//		GradingService gs = new GradingServiceImpl();
//		ECurnitmap curnitMap = gs.getCurnitmap(new Long(runId));
//		ESessionBundle
//		EProject project = curnitMap.getProject();
//		
//		EList activities = project.getActivity();
//		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
//			EActivity activity = (EActivity) iterator.next();
//			
//			EList steps = activity.getStep();
//			
//			for (Iterator iterator2 = steps.iterator(); iterator2.hasNext();) {
//				EStep step = (EStep) iterator2.next();
//				if( step.getPodUUID().equals(podUUID)) {
//					System.out.println("we got it!");
//					
//					GradeWorkByStepAggregate gradeWorkByStepAggregate = gs.getGradeWorkByStepAggregate(new Long(runId), step);
//					modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregate);
//				}// if
//			}// for
//			
//		}
		
//		getGradeWorkByStepAggregate(Long runId,
//				EStep step)

		
        return modelAndView;
	}

}