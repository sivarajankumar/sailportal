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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.sail.emf.sailuserdata.EAnnotationBundle;
import net.sf.sail.webapp.dao.annotation.AnnotationBundleDao;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.annotation.impl.AnnotationBundleImpl;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class AnnotationBundleServiceImpl implements AnnotationBundleService {

	private AnnotationBundleDao<AnnotationBundle> annotationBundleDao;
	
	/**
	 * @see net.sf.sail.webapp.service.annotation.AnnotationBundleService#saveAnnotationBundle(net.sf.sail.webapp.domain.annotation.AnnotationBundle)
	 */
	public void saveAnnotationBundle(AnnotationBundle annotationBundle) {
		// save annotation as into stream, then convert the stream into String
		EAnnotationBundle eAnnotationBundle = annotationBundle.getEAnnotationBundle();

		ByteArrayOutputStream annotationOutStream = 
			new ByteArrayOutputStream();
		byte [] annotationBytes = null;				

		try {
			eAnnotationBundle.eResource().save(annotationOutStream, null);
			annotationBytes = annotationOutStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String annotationBundleXMLString = new String(annotationBytes);
		annotationBundle.setBundle(annotationBundleXMLString);
		annotationBundleDao.save(annotationBundle);
	}

	/**
	 * @see net.sf.sail.webapp.service.annotation.AnnotationBundleService#getAnnotationBundle(Long, Workgroup)
	 */
	public AnnotationBundle getAnnotationBundle(Long runId, Workgroup workgroup) {
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

		return annotationBundle;
	}

	/**
	 * @param annotationBundleDao the annotationBundleDao to set
	 */
	public void setAnnotationBundleDao(
			AnnotationBundleDao<AnnotationBundle> annotationBundleDao) {
		this.annotationBundleDao = annotationBundleDao;
	}

}
