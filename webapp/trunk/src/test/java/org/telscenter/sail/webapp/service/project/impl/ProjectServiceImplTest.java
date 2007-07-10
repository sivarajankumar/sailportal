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
package org.telscenter.sail.webapp.service.project.impl;

import org.easymock.EasyMock;

import net.sf.sail.webapp.dao.curnit.CurnitDao;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.Curnit;
import junit.framework.TestCase;

/**
 * Test class for TELS's ProjectServiceImpl
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class ProjectServiceImplTest extends TestCase {

    private SdsCurnitDao mockSdsCurnitDao;
    private CurnitDao<Curnit> mockCurnitDao;
 
	private ProjectServiceImpl projectServiceImpl;
	
    /**
     * @see junit.framework.TestCase#setUp()
     */
    @SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		this.projectServiceImpl = new ProjectServiceImpl();
		
        this.mockSdsCurnitDao = EasyMock.createMock(SdsCurnitDao.class);
        this.projectServiceImpl.setSdsCurnitDao(this.mockSdsCurnitDao);

        this.mockCurnitDao = EasyMock.createMock(CurnitDao.class);
        this.projectServiceImpl.setCurnitDao(this.mockCurnitDao);
	}
    
    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.projectServiceImpl = null;
        this.mockSdsCurnitDao = null;
        this.mockCurnitDao = null;
    }
    
    // TODO: HT: make CurnitDao extendable by ProjectDao first, before writing all these tests
    
    public void testGetProjectList() throws Exception {
    	// TODO HT: IMPLEMENT ME
    }
    
    public void testCreateProject() throws Exception {
    	// TODO HT: IMPLEMENT ME
    }
    
    public void testGetById() throws Exception {
    	// TODO HT: IMPLEMENT ME
    }
}
