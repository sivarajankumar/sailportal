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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.EList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EProject;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.service.grading.GradingService;

/**
 * The actual gradingTool.
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradingToolController extends AbstractController {

	private static final String STEP = "step";
	public static final String PODUUID = "podUUID";
	public static final String STEP_AGGREGATE = "stepAggregate";
	public static final String GRADE_TYPE = "GRADE_TYPE";
	private GradingService gradingService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		ModelAndView modelAndView = new ModelAndView();

		String gradingType = request.getParameter(GRADE_TYPE);
		String runId = request.getParameter(GradeByStepController.RUN_ID);
		String podUUID = request.getParameter(PODUUID);
		
		
		ECurnitmap curnitMap = this.gradingService.getCurnitmap(new Long(runId));
		EProject project = curnitMap.getProject();
		
		EList activities = project.getActivity();
		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
			EActivity activity = (EActivity) iterator.next();
			
			EList steps = activity.getStep();
			
			for (Iterator iterator2 = steps.iterator(); iterator2.hasNext();) {
				EStep step = (EStep) iterator2.next();
				if( step.getPodUUID().toString().equals(podUUID)) {
					System.out.println("we got it!");
					
					GradeWorkByStepAggregate gradeWorkByStepAggregate = this.gradingService.getGradeWorkByStepAggregate(new Long(runId), step);
					modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregate);
					modelAndView.addObject(STEP,step);
					
					Map<String, String> periods = new HashMap<String, String>();
					
					periods.put("Period3", "ok");
					periods.put("Period2", "ok");
					periods.put("Period1", "ok");
					
					modelAndView.addObject("periods",periods);
					System.out.println("KeySet: " + gradeWorkByStepAggregate.getSessionBundles().keySet());
					System.out.println("values: " + gradeWorkByStepAggregate.getSessionBundles().values());
					return modelAndView;
				}// if
			}// for
			
		}
		
//		getGradeWorkByStepAggregate(Long runId,
//				EStep step)

		
        return modelAndView;
	}

	public GradingService getGradingService() {
		return gradingService;
	}

	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

}