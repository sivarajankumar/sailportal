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
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.group.Group;

import org.eclipse.emf.common.util.EList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EProject;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * The actual gradingTool.
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradingToolController extends AbstractController {

	private static final String NEXT_STEP = "nextStep";
	private static final String CURNIT_ID = "curnitId";
	private static final String PROJECT_TITLE = "projectTitle";
	private static final String ACTIVITY = "activity";
	private static final String STEP = "step";
	public static final String PODUUID = "podUUID";
	public static final String STEP_AGGREGATE = "stepAggregate";
	public static final String GRADE_TYPE = "GRADE_TYPE";
	private GradingService gradingService;
	private RunService runService;

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
		
		
		Run aRun = runService.retrieveById(new Long(runId));
		
		System.out.println("OBJECT ID: " + aRun.getSdsOffering().getSdsCurnit().getSdsObjectId() );
		
		String curnitId = aRun.getSdsOffering().getSdsCurnit().getSdsObjectId().toString();
		
		ECurnitmap curnitMap = this.gradingService.getCurnitmapMock(new Long(runId));
		EProject project = curnitMap.getProject();
		
		EList activities = project.getActivity();
		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
			EActivity activity = (EActivity) iterator.next();
			
			EList steps = activity.getStep();
			
			for (Iterator iterator2 = steps.iterator(); iterator2.hasNext();) {
				EStep step = (EStep) iterator2.next();
				if( step.getPodUUID().toString().equals(podUUID)) {
					System.out.println("we got it!");
					
					for (Iterator iterator3 = iterator2; iterator3.hasNext();) {
						EStep nextStep = (EStep) iterator3.next();
						if( this.isGradable(nextStep.getType()) ){
							modelAndView.addObject(NEXT_STEP, nextStep);
							break;
						} else {
							modelAndView.addObject(NEXT_STEP, null);
						}
					}// for
					//TODO: Hiroki use stepId instead of EStep object as param
					Map<Group, Set<GradeWorkByWorkgroupAggregate>> gradeWorkByStepAggregateAllPeriods = this.gradingService.getGradeWorkByStepAggregateAllPeriods(new Long( runId ), step);
					modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregateAllPeriods);
					modelAndView.addObject(STEP, step);
					modelAndView.addObject(ACTIVITY,activity);
					modelAndView.addObject(PROJECT_TITLE,project.getTitle());
					modelAndView.addObject(CURNIT_ID,curnitId);
					
					modelAndView.addObject(GradeByStepController.RUN_ID, runId);
					return modelAndView;
				}// if
			}// for
			
		}
		
//		getGradeWorkByStepAggregate(Long runId,
//				EStep step)

		
        return modelAndView;
	}

	protected boolean isGradable(String stepType) {
		if( stepType.equals("Note")) {
			return true;
		} else if(stepType.equals("Student Assessment" )) {
			return true;
		} else if(stepType.equals("CCDraw")) {
			return true;
		}// if
		
		return false;
	}
	public GradingService getGradingService() {
		return gradingService;
	}

	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

}