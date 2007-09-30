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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.annotation.impl.AnnotationBundleImpl;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;

import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.pas.emf.pas.util.CurnitmapLoader;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;
import org.telscenter.sail.webapp.domain.grading.impl.GradeWorkByStepAggregateImpl;
import org.telscenter.sail.webapp.domain.grading.impl.GradeWorkByWorkgroupAggregateImpl;
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
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getCurnitmap(java.lang.Long)
	 */
	public ECurnitmap getCurnitmapMock(Long runId) throws ObjectNotFoundException {
		// TODO REPLACE MOCK BELOW WITH ACTUAL CODE WHEN READY	
		// ALSO ADD LOGIC TO RETRIEVE RUN USING PROVIDED runId PARAMETER
		String curnitmapXMLString = "<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
			"<pas:ECurnitmap xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:pas=\"pas\" xsi:schemaLocation=\"pas pas.ecore\">" +
			"<project podUUID=\"cccccccc-0002-3878-0000-000000000000\"	title=\"Global Warming: Virtual Earth\">" +
			"<activity podUUID=\"dddddddd-6004-0000-0000-000000000000\"	title=\"Identifying the Problem\" number=\"0\">" +
			"<step podUUID=\"dddddddd-6004-0001-0000-000000000000\"	title=\"1. Global Warming is happening\" number=\"0\" type=\"Display\"		classname=\"org.telscenter.pas.steps.Display\" />" +
			"<step podUUID=\"dddddddd-6004-0002-0000-000000000000\"	title=\"2. Take notes on the Science behind Global Warming part 1\" number=\"1\"			type=\"Note\" classname=\"org.telscenter.pas.steps.Note\" ><rim rimname=\"undefined6\" prompt=\"html-stylized prompt for step 2 goes here\"/><rim rimname=\"undefined6a\" prompt=\"chocie prompt\"/></step>" +
			"<step podUUID=\"dddddddd-6004-0003-0000-000000000000\"	title=\"3. Take notes on the Science behind Global Warming part 2\" number=\"2\"			type=\"Note\" classname=\"org.telscenter.pas.steps.Note\" ><rim rimname=\"undefined7\" prompt=\"html-stylized prompt for step 3 goes here\"/></step>" +
			"</activity></project></pas:ECurnitmap>";	
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
			AnnotationBundle annotationBundle = annotationBundleService.getAnnotationBundle(runId, workgroup);
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
		
		// TODO HT: replace with actual code when ready
		String annotationBundleString = "<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
		"<sailuserdata:EAnnotationBundle xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sailuserdata=\"sailuserdata\">" +
		"<annotationGroups annotationSource=\"http://sail.sf.net/annotations/test\">" +                               
        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined6\"/>" +
        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6a\" contentType=\"text/plain\" contents=\"Test rim annotation CHOCIE for rim with name undefined6a\"/>" +
        "<annotations entityUUID=\"dddddddd-6004-0003-0000-000000000000\" entityName=\"undefined7\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined7\"/>" +
        "</annotationGroups></sailuserdata:EAnnotationBundle>";
		
		AnnotationBundle annotationBundle = new AnnotationBundleImpl();
		annotationBundle.setBundle(annotationBundleString);
		annotationBundle.setWorkgroup(workgroup);		
		aggregate.setAnnotationBundle(annotationBundle);
		
		SessionBundle sessionBundle = sessionBundleService.getSessionBundle(runId, workgroup);
		aggregate.setSessionBundle(sessionBundle);
		
		return aggregate;
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

	/**
	 * TODO HT: make this work (need to add WorkgroupService.retrieveById)
	 */
	public GradeWorkByWorkgroupAggregate getGradeWorkByWorkgroupAggregate(
			Long runId, Long workgroupId) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
