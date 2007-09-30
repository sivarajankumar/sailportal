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
package org.telscenter.sail.webapp.domain.grading.impl;

import net.sf.sail.emf.sailuserdata.ESessionBundle;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;

import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class GradeWorkByWorkgroupAggregateImpl extends GradeWorkAggregateImpl
		implements GradeWorkByWorkgroupAggregate {
	
	protected Workgroup workgroup;
	
	protected SessionBundle sessionBundle;
	
	protected AnnotationBundle annotationBundle;

	/**
	 * @see GradeWorkByWorkgroupAggregate#getAnnotationBundle()
	 */
	public AnnotationBundle getAnnotationBundle() {
		return annotationBundle;
	}

	/**
	 * @see GradeWorkByWorkgroupAggregate#getSessionBundle()
	 */
	public SessionBundle getSessionBundle() {
		return sessionBundle;
	}

	/**
	 * @return the workgroup
	 */
	public Workgroup getWorkgroup() {
		return workgroup;
	}

	/**
	 * @see GradeWorkByWorkgroupAggregate#setAnnotationBundle(AnnotationBundle)
	 */
	public void setAnnotationBundle(AnnotationBundle annotationBundle) {
		this.annotationBundle = annotationBundle;
	}
	
	/**
	 * @see GradeWorkByWorkgroupAggregate#setSessionBundle(ESessionBundle)
	 */
	public void setSessionBundle(SessionBundle sessionBundle) {
		this.sessionBundle = sessionBundle;
	}

	/**
	 * @param workgroup the workgroup to set
	 */
	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}
}
