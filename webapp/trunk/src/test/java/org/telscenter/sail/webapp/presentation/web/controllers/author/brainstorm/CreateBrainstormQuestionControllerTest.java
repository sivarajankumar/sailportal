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
package org.telscenter.sail.webapp.presentation.web.controllers.author.brainstorm;

import static org.junit.Assert.*;

import java.io.Serializable;

import static org.easymock.EasyMock.*;
import net.sf.sail.webapp.dao.ObjectNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.TestClassRunner;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author hirokiterashima
 * @version $Id: UtilTest.java 2113 2008-09-22 22:54:04Z hiroki $
 */
@RunWith(TestClassRunner.class)
public class CreateBrainstormQuestionControllerTest {

	private static final Serializable NONEXISTING_PROJECTID = 5;

	private CreateBrainstormQuestionController controller;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	private ProjectService projectService;
	
	@Before
	public void runBeforeEveryTest() {
		controller = new CreateBrainstormQuestionController();
		request = new MockHttpServletRequest();	
		response = new MockHttpServletResponse();
		projectService = createMock(ProjectService.class);
		controller.setProjectService(projectService);
	}
	
	@After
	public void runAfterEveryTest() {
		controller = null;
		request = null;
		response = null;
	}
	
	@Test
	public void projectNotFound() throws ObjectNotFoundException {
		expect(projectService.getById(NONEXISTING_PROJECTID.toString())).andThrow(new ObjectNotFoundException(NONEXISTING_PROJECTID, Project.class));
		replay(projectService);
		try {
			request.setParameter(CreateBrainstormQuestionController.PROJECTID_PARAM, NONEXISTING_PROJECTID.toString());
			controller.handleRequestInternal(request, response);
			fail("exception expected, but was not thrown.");
		} catch (Exception e) {
		}
		verify(projectService);
	}
}
