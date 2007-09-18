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
package net.sf.sail.webapp.service.annotation.impl;

import static org.easymock.EasyMock.*;

import net.sf.sail.webapp.dao.annotation.AnnotationBundleDao;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.annotation.impl.AnnotationBundleImpl;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;
import junit.framework.TestCase;

/**
 * @author Hiroki Terashima
 * @version $ Id: $
 */
public class AnnotationBundleServiceImplTest extends TestCase {
	
	private AnnotationBundleServiceImpl annotationBundleService;

	private AnnotationBundleDao<AnnotationBundle> mockAnnotationBundleDao;
	
	private AnnotationBundle annotationBundle;
	
	private Workgroup workgroup;

	private String annotationBundleXMLString = "<?xml version=\"1.0\" encoding=\"ASCII\"?>" +
	"<sailuserdata:EAnnotationBundle xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:sailuserdata=\"sailuserdata\">" +
	"<annotationGroups annotationSource=\"http://sail.sf.net/annotations/test\">" +                               
    "<annotations entityUUID=\"dddddddd-6004-0002-0000-000000000000\" entityName=\"undefined6\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined6\"/>" +
    "<annotations entityUUID=\"dddddddd-6004-0003-0000-000000000000\" entityName=\"undefined7\" contentType=\"text/plain\" contents=\"Test rim annotation for rim with name undefined7\"/>" +
    "</annotationGroups></sailuserdata:EAnnotationBundle>";
; 
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() {
		annotationBundleService = new AnnotationBundleServiceImpl();
		mockAnnotationBundleDao = createMock(AnnotationBundleDao.class);
		annotationBundleService.setAnnotationBundleDao(mockAnnotationBundleDao);
		
		// TODO HT replace with bean context if possible
		annotationBundle = new AnnotationBundleImpl();
		annotationBundle.setBundle(annotationBundleXMLString);
		workgroup = new WorkgroupImpl();
		annotationBundle.setWorkgroup(workgroup);
	}

	@Override
	protected void tearDown() {
		annotationBundleService = null;
		mockAnnotationBundleDao = null;
		annotationBundle = null;
	}
	
	public void testSaveAnnotationBundle() {
		mockAnnotationBundleDao.save(annotationBundle);
		expectLastCall();
		annotationBundleService.saveAnnotationBundle(annotationBundle);
		assertTrue(true);
	}
}
