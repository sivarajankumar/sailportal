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
package org.telscenter.sail.webapp.service.project.impl;

import net.sf.sail.webapp.dao.ObjectNotFoundException;

import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;

import junit.framework.TestCase;

import static org.easymock.EasyMock.*;


/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class ProjectServiceImplTest extends TestCase {

	private ProjectServiceImpl projectServiceImpl;

	private ProjectDao<Project> mockProjectDao;
	
	private static final Long EXISTING_PROJECT_ID = new Long(10);

	private static final Long NONEXISTING_PROJECT_ID = new Long(103);

	
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		this.projectServiceImpl = new ProjectServiceImpl();
		this.mockProjectDao = createMock(ProjectDao.class);
		this.projectServiceImpl.setProjectDao(mockProjectDao);
	}
	public void testGetById() throws Exception {
		Project expectedProject = new ProjectImpl();
		expect(mockProjectDao.getById(EXISTING_PROJECT_ID)).andReturn(expectedProject);
    	replay(mockProjectDao);
    	assertEquals(expectedProject, projectServiceImpl.getById(EXISTING_PROJECT_ID));
    	verify(mockProjectDao);
    	reset(mockProjectDao);

    	// now check when curnit is not found
    	expect(mockProjectDao.getById(NONEXISTING_PROJECT_ID)).andThrow(new ObjectNotFoundException(NONEXISTING_PROJECT_ID, Project.class));
    	replay(mockProjectDao);
    	try {
    		projectServiceImpl.getById(NONEXISTING_PROJECT_ID);
    		fail("ObjectNotFoundException expected but was not thrown");
    	} catch (ObjectNotFoundException e) {
    	}
    	verify(mockProjectDao);

	}
}
