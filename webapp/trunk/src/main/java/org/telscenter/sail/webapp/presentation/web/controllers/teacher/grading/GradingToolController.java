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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
import org.telscenter.pas.emf.pas.ERim;
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

	private static final String PREVIOUS_STEP = "previousStep";
	private static final String ACTIVITY_NUMBER = "activityNumber";
	private static final String TAB_INDEX = "tabIndex";
	private static final String NEXT_STEP = "nextStep";
	public static final String CURNIT_ID = "curnitId";
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_TITLE = "projectTitle";
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
		String tabIndex = request.getParameter(TAB_INDEX);
		String activityNumber = request.getParameter(ACTIVITY_NUMBER);
		
		//get the stuff from the run
		Run aRun = runService.retrieveById(new Long(runId));
		System.out.println("OBJECT ID: " + aRun.getSdsOffering().getSdsCurnit().getSdsObjectId() );
		String curnitId = aRun.getSdsOffering().getSdsCurnit().getSdsObjectId().toString();
		String projectId = aRun.getProject().getId().toString();
		
		ECurnitmap curnitMap = this.gradingService.getCurnitmap(new Long(runId));
		EProject project = curnitMap.getProject();
		
		EList activities = project.getActivity();
		List<EStep> gradableSteps = new LinkedList<EStep>();
		
		HashMap<EStep,EActivity> stepToActivityMap = new HashMap<EStep,EActivity>();
		//make a list of all the steps and a map
		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
			EActivity foundActivity = (EActivity) iterator.next();
				EList stepList = foundActivity.getStep();
				//find the gradable steps
				
				for (Iterator stepListIt = stepList.iterator(); stepListIt
						.hasNext();) {
					EStep step = (EStep) stepListIt.next();
					if(isGradable(step.getType()) ) {
						gradableSteps.add(step);
						stepToActivityMap.put(step, foundActivity);
					}// if
				
				}// for
		}// for
		
		//now go through the list of gradable steps
		ListIterator<EStep> listIterator = gradableSteps.listIterator();
	    while (listIterator.hasNext()) {
	    	EStep previousStep = null;
	    	EStep nextStep =  null;
	    	
	    	int previousIndex = listIterator.previousIndex();
	    	
	    	EStep currentStep =  listIterator.next();
	    	
	    	int nextIndex = listIterator.nextIndex();
	    	
	    	if( previousIndex < 0)
	    		previousStep = null;
	    	else
	    		previousStep = gradableSteps.get(previousIndex);
	    		
	    	
	    	if( nextIndex == gradableSteps.size() )
	    		nextStep = null;
	    	else
	    		nextStep = gradableSteps.get(nextIndex);
	    	
			//get the current step
			if( currentStep.getPodUUID().toString().equals(podUUID)) {
				
				//TODO: Hiroki use stepId instead of EStep object as param
				Map<Group, Set<GradeWorkByWorkgroupAggregate>> gradeWorkByStepAggregateAllPeriods 
				= this.gradingService.getGradeWorkByStepAggregateAllPeriods(new Long( runId ), currentStep);
				
				modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregateAllPeriods);
				this.stripHTMLFromBody(currentStep);
				modelAndView.addObject(STEP, currentStep);
		    	
				modelAndView.addObject(PREVIOUS_STEP,previousStep);
				modelAndView.addObject(NEXT_STEP,nextStep);
				
				modelAndView.addObject(ACTIVITY,stepToActivityMap.get(currentStep));
				modelAndView.addObject(PROJECT_TITLE,project.getTitle());
				modelAndView.addObject(CURNIT_ID,curnitId);
				modelAndView.addObject(PROJECT_ID,projectId);
				modelAndView.addObject(TAB_INDEX, tabIndex);
				
				modelAndView.addObject(GradeByStepController.RUN_ID, runId);
				return modelAndView;
			}// if
			
			
		}// for
//		for (ListIterator<EStep> listIterator =  gradableSteps.listIterator(); listIterator.hasNext();) {
//			EStep step =  listIterator.next();
//			
//			//get the current step
//			if( step.getPodUUID().toString().equals(podUUID)) {
//				
//				//TODO: Hiroki use stepId instead of EStep object as param
//				Map<Group, Set<GradeWorkByWorkgroupAggregate>> gradeWorkByStepAggregateAllPeriods = this.gradingService.getGradeWorkByStepAggregateAllPeriods(new Long( runId ), step);
//				
//				modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregateAllPeriods);
//				this.stripHTMLFromBody(step);
//				modelAndView.addObject(STEP, step);
//				
//				
//				if( listIterator.hasPrevious()) {
//					modelAndView.addObject(PREVIOUS_STEP,listIterator.previous());
//				} else {
//					
//				}// if
//				modelAndView.addObject(NEXT_STEP, listIterator.next());
//				
//				modelAndView.addObject(ACTIVITY,foundActivity);
//				modelAndView.addObject(PROJECT_TITLE,project.getTitle());
//				modelAndView.addObject(CURNIT_ID,curnitId);
//				modelAndView.addObject(TAB_INDEX, tabIndex);
//				
//				modelAndView.addObject(GradeByStepController.RUN_ID, runId);
//			}// if
//			
//		}// for
		
//		for (Iterator iterator = activities.iterator(); iterator.hasNext();) {
//			EActivity activity = (EActivity) iterator.next();
//			
//			EList steps = activity.getStep();
//			
//			for (Iterator iterator2 = steps.iterator(); iterator2.hasNext();) {
//				EStep step = (EStep) iterator2.next();
//				
//				//this is nextstep
//				if( foundCurrentStep == true && stepIsGradable(step)) {
//					modelAndView.addObject(NEXT_STEP, step);
//					return modelAndView;
//				}
//				
//				if( step.getPodUUID().toString().equals(podUUID)) {
//					System.out.println("we got it!");
//					
//					if( foundCurrentStep == false) {
//						foundCurrentStep = true;
//					} else {
//						foundCurrentStep = false;
//					}
//					//TODO: Hiroki use stepId instead of EStep object as param
//					Map<Group, Set<GradeWorkByWorkgroupAggregate>> gradeWorkByStepAggregateAllPeriods = this.gradingService.getGradeWorkByStepAggregateAllPeriods(new Long( runId ), step);
//					
//					modelAndView.addObject(STEP_AGGREGATE, gradeWorkByStepAggregateAllPeriods);
//					this.stripHTMLFromBody(step);
//					modelAndView.addObject(STEP, step);
//					modelAndView.addObject(NEXT_STEP, null);
//					modelAndView.addObject(ACTIVITY,activity);
//					modelAndView.addObject(PROJECT_TITLE,project.getTitle());
//					modelAndView.addObject(CURNIT_ID,curnitId);
//					modelAndView.addObject(TAB_INDEX, tabIndex);
//					
//					modelAndView.addObject(GradeByStepController.RUN_ID, runId);
//				}// if
//			}// for
//		}

		
        return modelAndView;
	}

	/**
	 * Grabs the html within the middle of the html body
	 * 
	 * @param someStep - the current step
	 */
	public void stripHTMLFromBody(EStep someStep) {
		EList rim = someStep.getRim();
		for (Iterator iterator = rim.iterator(); iterator.hasNext();) {
			ERim someRim = (ERim) iterator.next();
			someRim.setPrompt(this.extractBody(someRim.getPrompt()));
		}
	}
	
	/**
	 * extract the html from the body
	 * 
	 * @param prompt
	 * @return
	 */
	public String extractBody(String prompt) {
		  int start = prompt.indexOf("<body>");
		  int end = prompt.indexOf("</body>");
		  String extractedBody = "";
		  
		  if (start != -1 && end != -1) {
		   extractedBody = prompt.substring(start+6, end);
		   return extractedBody;
		  }   
		  return prompt;
	}

	/**
	 * checks if this step is gradable
	 * 
	 * @param stepType
	 * @return
	 */
	public static boolean isGradable(String stepType) {
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