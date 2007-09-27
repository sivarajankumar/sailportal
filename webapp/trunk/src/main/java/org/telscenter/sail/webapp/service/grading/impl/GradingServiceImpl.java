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
import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.annotation.impl.AnnotationBundleImpl;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;

import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.pas.emf.pas.util.CurnitmapLoader;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;
import org.telscenter.sail.webapp.domain.grading.impl.GradeWorkByStepAggregateImpl;
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
		// TODO REPLACE MOCK BELOW WITH ACTUAL CODE WHEN READY	
		String annotationBundleString1 = "<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
				"<sailuserdata:EAnnotationBundle xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sailuserdata=\"sailuserdata\">" +
				"<annotationGroups annotationSource=\"http://sail.sf.net/annotations/test\">" +                               
		        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined6\"/>" +
		        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6a\" contentType=\"text/plain\" contents=\"Test rim annotation CHOCIE for rim with name undefined6a\"/>" +
		        "<annotations entityUUID=\"dddddddd-6004-0003-0000-000000000000\" entityName=\"undefined7\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined7\"/>" +
		        "</annotationGroups></sailuserdata:EAnnotationBundle>";
		User user1 = new UserImpl();
		User user2 = new UserImpl();
		//create user1
		String username1 = "Tony";
		MutableUserDetails userDetails1 = new PersistentUserDetails();
		userDetails1.setUsername(username1);
		user1.setUserDetails(userDetails1);
		
		String username2 = "Hiroki";
		MutableUserDetails userDetails2 = new PersistentUserDetails();
		userDetails2.setUsername(username2);
		user2.setUserDetails(userDetails2);
		
		
		Offering offering = new OfferingImpl();
		Workgroup workgroup1 = new WorkgroupImpl();
		workgroup1.addMember(user1);
		workgroup1.addMember(user2);
		workgroup1.setOffering(offering);
		
		AnnotationBundle annotationBundle1 = new AnnotationBundleImpl();
		annotationBundle1.setBundle(annotationBundleString1);
		annotationBundle1.setWorkgroup(workgroup1);
		Map<Workgroup, AnnotationBundle> annotationBundles = new HashMap<Workgroup, AnnotationBundle>();
		annotationBundles.put(workgroup1, annotationBundle1);
		
		SessionBundle sessionBundle1 = sessionBundleService.getSessionBundle(runId, workgroup1);
		Map<Workgroup, SessionBundle> sessionBundles = new HashMap<Workgroup, SessionBundle>();
		sessionBundles.put(workgroup1, sessionBundle1);
		
		GradeWorkByStepAggregate aggregate = new GradeWorkByStepAggregateImpl();
		aggregate.setRunId(runId);
		aggregate.setCurnitmap(getCurnitmap(runId));
		aggregate.setStep(step);
		aggregate.setAnnotationBundles(annotationBundles);
		aggregate.setSessionBundles(sessionBundles);
		return aggregate;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.grading.GradingService#getGradeWorkByStepAggregateAllPeriods(Long, EStep)
	 */
	public Map<Group, GradeWorkByStepAggregate> getGradeWorkByStepAggregateAllPeriods(
			Long runId, EStep step) throws ObjectNotFoundException {
		// TODO REPLACE MOCK BELOW WITH ACTUAL CODE WHEN READY	
		String annotationBundleString1 = "<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
				"<sailuserdata:EAnnotationBundle xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sailuserdata=\"sailuserdata\">" +
				"<annotationGroups annotationSource=\"http://sail.sf.net/annotations/test\">" +                               
		        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined6\"/>" +
		        "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6a\" contentType=\"text/plain\" contents=\"Test rim annotation CHOCIE for rim with name undefined6a\"/>" +
		        "<annotations entityUUID=\"dddddddd-6004-0003-0000-000000000000\" entityName=\"undefined7\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined7\"/>" +
		        "</annotationGroups></sailuserdata:EAnnotationBundle>";

		//create user1
		User user1 = new UserImpl();
		String username1 = "Tony";
		MutableUserDetails userDetails1 = new PersistentUserDetails();
		userDetails1.setUsername(username1);
		user1.setUserDetails(userDetails1);
		
		//create user2
		User user2 = new UserImpl();
		String username2 = "Hiroki";
		MutableUserDetails userDetails2 = new PersistentUserDetails();
		userDetails2.setUsername(username2);
		user2.setUserDetails(userDetails2);
		
		//create user3
		User user3 = new UserImpl();
		String username3 = "Freda";
		MutableUserDetails userDetails3 = new PersistentUserDetails();
		userDetails3.setUsername(username3);
		user3.setUserDetails(userDetails3);
		
		//create user4
		User user4 = new UserImpl();
		String username4 = "Doug";
		MutableUserDetails userDetails4 = new PersistentUserDetails();
		userDetails4.setUsername(username4);
		user4.setUserDetails(userDetails4);
		
		Offering offering = new OfferingImpl();
		Workgroup workgroup1 = new WorkgroupImpl();
		workgroup1.addMember(user1);
		workgroup1.addMember(user2);
		workgroup1.setOffering(offering);

		Workgroup workgroup2 = new WorkgroupImpl();
		workgroup2.addMember(user3);
		workgroup2.addMember(user4);
		workgroup2.setOffering(offering);

		
		AnnotationBundle annotationBundle1 = new AnnotationBundleImpl();
		annotationBundle1.setBundle(annotationBundleString1);
		annotationBundle1.setWorkgroup(workgroup1);
		Map<Workgroup, AnnotationBundle> annotationBundles1 = new HashMap<Workgroup, AnnotationBundle>();
		annotationBundles1.put(workgroup1, annotationBundle1);
		
		SessionBundle sessionBundle1 = sessionBundleService.getSessionBundle(runId, workgroup1);
		Map<Workgroup, SessionBundle> sessionBundles1 = new HashMap<Workgroup, SessionBundle>();
		sessionBundles1.put(workgroup1, sessionBundle1);
		
		GradeWorkByStepAggregate aggregate1 = new GradeWorkByStepAggregateImpl();
		aggregate1.setRunId(runId);
		aggregate1.setCurnitmap(getCurnitmap(runId));
		aggregate1.setStep(step);
		aggregate1.setAnnotationBundles(annotationBundles1);
		aggregate1.setSessionBundles(sessionBundles1);
		
		AnnotationBundle annotationBundle2 = new AnnotationBundleImpl();
		annotationBundle2.setBundle(annotationBundleString1);
		annotationBundle2.setWorkgroup(workgroup2);
		Map<Workgroup, AnnotationBundle> annotationBundles2 = new HashMap<Workgroup, AnnotationBundle>();
		annotationBundles2.put(workgroup2, annotationBundle2);
		annotationBundles2.put(workgroup1, annotationBundle1);
		
		SessionBundle sessionBundle2 = sessionBundleService.getSessionBundle(runId, workgroup2);
		Map<Workgroup, SessionBundle> sessionBundles2 = new HashMap<Workgroup, SessionBundle>();
		sessionBundles2.put(workgroup2, sessionBundle2);
		sessionBundles2.put(workgroup1, sessionBundle1);
		
		GradeWorkByStepAggregate aggregate2 = new GradeWorkByStepAggregateImpl();
		aggregate2.setRunId(runId);
		aggregate2.setCurnitmap(getCurnitmap(runId));
		aggregate2.setStep(step);
		aggregate2.setAnnotationBundles(annotationBundles2);
		aggregate2.setSessionBundles(sessionBundles2);
		
		Group period1 = new PersistentGroup();
		period1.setName("period one");
		Group period2 = new PersistentGroup();
		period2.setName("period two");
		Map<Group, GradeWorkByStepAggregate> period_to_aggregate =
			new HashMap<Group, GradeWorkByStepAggregate>();
		period_to_aggregate.put(period1, aggregate1);
		period_to_aggregate.put(period2, aggregate2);
		
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
	public GradeWorkByWorkgroupAggregate getGradeWorkByStepAggregate(
			Long runId, Long workgroupId) {
		// TODO Auto-generated method stub UNIMPLEMENTED
		return null;
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
}
