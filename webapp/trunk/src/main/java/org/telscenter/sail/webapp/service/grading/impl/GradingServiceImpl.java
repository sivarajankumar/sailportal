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
package org.telscenter.sail.webapp.service.grading.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.sail.emf.sailuserdata.EAnnotation;
import net.sf.sail.emf.sailuserdata.EAnnotationGroup;
import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.pas.emf.pas.util.CurnitmapLoader;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;
import org.telscenter.sail.webapp.domain.grading.IndividualScore;
import org.telscenter.sail.webapp.domain.grading.StudentScore;
import org.telscenter.sail.webapp.domain.grading.impl.GradeWorkByStepAggregateImpl;
import org.telscenter.sail.webapp.domain.grading.impl.GradeWorkByWorkgroupAggregateImpl;
import org.telscenter.sail.webapp.domain.grading.impl.IndividualScoreNumericImpl;
import org.telscenter.sail.webapp.domain.grading.impl.StudentScoreImpl;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.grading.SessionBundleService;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class GradingServiceImpl implements GradingService {

	private SessionBundleService sessionBundleService;
	
	private AnnotationBundleService annotationBundleService;
	
	private RunService runService;
	
	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getCurnitmap(java.lang.Long)
	 */
	public ECurnitmap getCurnitmap(Long runId) throws ObjectNotFoundException {

		Run run = runService.retrieveById(runId);
		String curnitmapXMLString = run.getSdsOffering().getSdsCurnitMap();
		ECurnitmap curnitmap = CurnitmapLoader.loadCurnitmap(curnitmapXMLString);
		return curnitmap;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getGradeWorkByStepAggregate(java.lang.Long, org.telscenter.pas.emf.pas.EStep)
	 */
	public GradeWorkByStepAggregate getGradeWorkByStepAggregate(Long runId,
			EStep step) throws ObjectNotFoundException {
		
		Set<Workgroup> workgroups = runService.getWorkgroupsForOffering(runId);
		
		GradeWorkByStepAggregate aggregate = new GradeWorkByStepAggregateImpl();
		aggregate.setRunId(runId);
		aggregate.setCurnitmap(getCurnitmap(runId));
		aggregate.setStep(step);

		Map<Workgroup, AnnotationBundle> annotationBundles = new HashMap<Workgroup, AnnotationBundle>();
		Map<Workgroup, SessionBundle> sessionBundles = new HashMap<Workgroup, SessionBundle>();

		for (Workgroup workgroup : workgroups) {
			SessionBundle sessionBundle = sessionBundleService.getSessionBundle(runId, workgroup);
			sessionBundles.put(workgroup, sessionBundle);
			AnnotationBundle annotationBundle = annotationBundleService.getAnnotationBundle(workgroup);
			annotationBundles.put(workgroup, annotationBundle);
		}

		aggregate.setAnnotationBundles(annotationBundles);
		aggregate.setSessionBundles(sessionBundles);
		return aggregate;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getGradeWorkByStepAggregateAllPeriods(Long, EStep)
	 */
	public Map<Group, Set<GradeWorkByWorkgroupAggregate>> getGradeWorkByStepAggregateAllPeriods(
			Long runId, EStep step) throws ObjectNotFoundException {

		Run run = runService.retrieveById(runId);
		Set<Group> periods = run.getPeriods();

		Map<Group, Set<GradeWorkByWorkgroupAggregate>> period_to_aggregate =
			new HashMap<Group, Set<GradeWorkByWorkgroupAggregate>>();

		// instantiate the List in period_to_aggregate for each period
		for (Group period : periods) {
			period_to_aggregate.put(period, 
					new HashSet<GradeWorkByWorkgroupAggregate>());
		}

		// go through all of the workgroups in this run and sort their work
		// into periods
		Set<Workgroup> workgroups = runService.getWorkgroups(runId);

		for (Workgroup workgroup : workgroups) {
			Group period = ((WISEWorkgroup) workgroup).getPeriod();
			GradeWorkByWorkgroupAggregate gradeWorkByWorkgroupAggregate =
				getGradeWorkByWorkgroupAggregate(runId, workgroup);
			period_to_aggregate.get(period).add(gradeWorkByWorkgroupAggregate);
		}

		return period_to_aggregate;		
	}
	
	public GradeWorkByStepAggregate getGradeWorkByStepAggregate(Long runId,
			String stepId) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getGradeWorkByStepAggregate(java.lang.Long, java.lang.Long)
	 */
	public GradeWorkByWorkgroupAggregate getGradeWorkByWorkgroupAggregate(
			Long runId, Workgroup workgroup) throws ObjectNotFoundException {
		GradeWorkByWorkgroupAggregate aggregate = 
			new GradeWorkByWorkgroupAggregateImpl();

		aggregate.setWorkgroup(workgroup);
		aggregate.setRunId(runId);
		aggregate.setCurnitmap(getCurnitmap(runId));		
		aggregate.setAnnotationBundle(annotationBundleService.getAnnotationBundle(workgroup));
		aggregate.setSessionBundle(sessionBundleService.getSessionBundle(runId, workgroup));
		
		return aggregate;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getIndividualScore(net.sf.sail.webapp.domain.Workgroup)
	 */
	public List<IndividualScore> getIndividualScores(GradeWorkByWorkgroupAggregate gradeWorkByWorkgroupAggregate,HashMap<String, EStep> gradableSteps) {
		List<IndividualScore> individualScores = new ArrayList<IndividualScore>();
		
		// get gradeworkbyworkgroupaggregate for this workgroup <- mock it for now or populate individualScore objects with random data
		// do some parsing of the annotationbundle to get what you need to populate IndividualScore object
		Workgroup workgroup = gradeWorkByWorkgroupAggregate.getWorkgroup();
		
		//go through all the workgroup members
		for (User user : workgroup.getMembers()) {
			IndividualScore individualScore = new StudentScoreImpl();
			individualScore.setUsername(user.getUserDetails().getUsername());
			((StudentScore)individualScore).setFirstName(user.getSdsUser().getFirstName());
			((StudentScore)individualScore).setLastName(user.getSdsUser().getLastName());
			individualScore.setWorkgroup(workgroup);
			individualScore.setOfferingId(gradeWorkByWorkgroupAggregate.getRunId());
			individualScore.setGroup(((WISEWorkgroup)workgroup).getPeriod());
			individualScore.setTotalGradableSteps(gradableSteps.size());
			
			EList annotationGroups = gradeWorkByWorkgroupAggregate.getAnnotationBundle().getEAnnotationBundle().getAnnotationGroups();
			
			for (Iterator iterator = annotationGroups.iterator(); iterator
					.hasNext();) {
				EAnnotationGroup annotationGroup = (EAnnotationGroup) iterator.next();
				if( annotationGroup.getAnnotationSource().equals("http://telscenter.org/annotation/score")) {
					
					EList annotations = annotationGroup.getAnnotations();
					
					for (Iterator ai = annotations.iterator(); ai
							.hasNext();) {
						EAnnotation a = (EAnnotation) ai.next();
						
						//if this step is gradable
						if( gradableSteps.containsKey(a.getEntityUUID().toString())) {
							
							//check null also
							if( !a.getContents().equals("unscored") && StringUtils.trimToNull(a.getContents()) != null ) {
								individualScore.setAccmulatedScore(a.getEntityUUID().toString(), a.getContents());
							}// if
						
							//look up the possible score for the step
							String possScore = gradableSteps.get(a.getEntityUUID().toString()).getPossibleScore().toString();
							
							if( possScore == null)
								possScore = "20";
							
							individualScore.setPossibleScore(a.getEntityUUID().toString(), possScore);
						}// if
					}// for
					
				}// if
				
			}// for
			
			// the score object the list
			individualScores.add(individualScore);			
		}//for
		
		return individualScores;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#saveGrades(java.util.List)
	 */
	public void saveGrades(List<AnnotationBundle> annotationBundles) {
		for (AnnotationBundle annotationBundle : annotationBundles) {
			annotationBundleService.saveAnnotationBundle(annotationBundle);
		}
	}

	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#setSessionService(SessionBundleService)
	 */
	public void setSessionBundleService(SessionBundleService sessionBundleService) {
		this.sessionBundleService = sessionBundleService;
	}
	
	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public AnnotationBundleService getAnnotationBundleService() {
		return annotationBundleService;
	}

	public void setAnnotationBundleService(
			AnnotationBundleService annotationBundleService) {
		this.annotationBundleService = annotationBundleService;
	}

}
