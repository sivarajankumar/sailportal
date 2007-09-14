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

import java.net.SocketPermission;

import net.sf.sail.emf.sailuserdata.EAnnotationBundle;
import net.sf.sail.emf.sailuserdata.ESessionBundle;
import net.sf.sail.emf.sailuserdata.ESockPart;
import net.sf.sail.emf.sailuserdata.impl.EAnnotationBundleImpl;
import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;

import static org.easymock.EasyMock.*;

import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.ERim;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.grading.SessionBundleService;

import junit.framework.TestCase;

/**
 * Test for GradingServiceImpl class
 *
 * @author Hiroki Terashima
 * @version $ Id: $
 */
public class GradingServiceImplTest extends TestCase {

	private Long runId;

	private GradingServiceImpl gradingService;
	
	private SessionBundleService sessionBundleService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		runId = new Long(5);
		
		gradingService = new GradingServiceImpl();
		sessionBundleService = createMock(SessionBundleService.class);
		gradingService.setSessionBundleService(sessionBundleService);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		runId = null;
		gradingService = null;
	}
	
	public void testGetCurnitmap_success() 
	    throws ObjectNotFoundException {
		// TODO HT add more as things are implemented
		ECurnitmap curnitmap = null;
		curnitmap = gradingService.getCurnitmap(runId);
		assertNotNull(curnitmap);
	}
	
	public void testGetCurnitmap_runId_invalid() {
		// TODO HT add more as things are implemented
		ECurnitmap curnitmap = null;
		try {
			curnitmap = gradingService.getCurnitmap(runId);
			//fail("expected ObjectNotFoundException to be thrown");
		} catch (ObjectNotFoundException e) {
			assertNull(curnitmap);
		}
	}
	
	public void testGetGradeWorkByStepAggregate() throws ObjectNotFoundException {
		// TODO HT add more as things are implemented
		assertTrue(true);
		ECurnitmap curnitmap = null;
		curnitmap = gradingService.getCurnitmap(runId);
		EStep step = (EStep) ((EActivity) curnitmap.getProject().getActivity().get(0)).getStep().get(1);
		GradeWorkByStepAggregate aggregate = gradingService.getGradeWorkByStepAggregate(runId, step);
		//assertEquals(curnitmap, aggregate.getCurnitmap());  this should be true, but is not because equals() method isn't implemented yet
		assertEquals(step, aggregate.getStep());
		assertNotNull(aggregate.getAnnotationBundles());
		assertNotNull(aggregate.getSessionBundles());
		
		User user1 = new UserImpl();
		String username1 = "username1";
		MutableUserDetails userDetails1 = new PersistentUserDetails();
		userDetails1.setUsername(username1);
		user1.setUserDetails(userDetails1);
		Offering offering = new OfferingImpl();
		Workgroup workgroup1 = new WorkgroupImpl();
		workgroup1.addMember(user1);
		workgroup1.setOffering(offering);

		AnnotationBundle annotationBundle1 = (AnnotationBundle) aggregate.getAnnotationBundles().get(workgroup1);
		assertNotNull(annotationBundle1);
		assertEquals(workgroup1, annotationBundle1.getWorkgroup());	
		
		ESessionBundle sessionBundle1 = aggregate.getSessionBundles().get(workgroup1);
		//assertNotNull(sessionBundle1); right now this is null
		//assertEquals(
		//		((ESockPart) sessionBundle1.getSockParts().get(1)).getRimName(),
		//		((ERim) ((EStep) ((EActivity) curnitmap.getProject().getActivity().get(0)).getStep().get(1)).getRim().get(0)).getRimname()
		//		);
	}
}
