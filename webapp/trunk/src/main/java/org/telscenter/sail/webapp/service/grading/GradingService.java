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
package org.telscenter.sail.webapp.service.grading;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.domain.group.Group;

import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByWorkgroupAggregate;

/**
 * Services for WISE teachers for grading student work. Grading involves
 * working with <code>Curnitmap</code>, <code>AnnotationBundle</code>,
 * <code>SessionBundle</code> objects and services that are available for
 * those objects.
 * 
 * @author Hiroki Terashima
 * @version $ Id: $
 */
public interface GradingService {
	
	/**
	 * Returns a <code>ECurnitMap</code> representation of the 
	 * <code>Curnit</code> that is associated with a <code>Run</code>
	 * with the specified runId.
	 * 
	 * If the runId is not associated with any existing <code>Run</code>, 
	 * <code>ObjectNotFoundException</code> is thrown.
	 * 
	 * @param runId id of the run that the teacher wants to grade
	 * @return <code>List</code> of grade-able steps
	 * @throws <code>ObjectNotFoundException</code> when the provided runId
	 *     does not key to an existing <code>Run</code>
	 */
	public ECurnitmap getCurnitmap(Long runId) throws ObjectNotFoundException;
	
	/**
	 * Returns an aggregate object to allow WISE teachers to grade student
	 * work for a particular <code>Step</code> of a particular 
	 * <code>Project</code> that is being used in a <code>Run</code> indicated
	 * by the runId.
	 * 
	 * All of the workgroups' work is retrieved along with their respective
	 * <code>AnnotationBundle</code> for the particular step.
	 * 
	 * @param runId id of the run that the teacher wants to grade
	 * @param step particular step that the teacher wants to grade
	 * @return <code>GradeWorkAggregate</code> containing all of the students'
	 *     work for the specified step.
	 * @throws ObjectNotFoundException when the provided runId
	 *     does not key to an existing <code>Run</code>
	 */
	public GradeWorkByStepAggregate getGradeWorkByStepAggregate(Long runId, EStep step) 
	    throws ObjectNotFoundException;
	
	
	/**
	 * Returns an aggregate object to allow WISE teachers to grade student
	 * work for a particular <code>Step</code> of a particular 
	 * <code>Project</code> that is being used in a <code>Run</code> indicated
	 * by the runId.
	 * 
	 * All of the workgroups' work is retrieved along with their respective
	 * <code>AnnotationBundle</code> for the particular step.
	 * 
	 * @param runId id of the run that the teacher wants to grade
	 * @param stepId id of a particular step that the teacher wants to grade
	 * @return <code>GradeWorkAggregate</code> containing all of the students'
	 *     work for the specified step.
	 * @throws ObjectNotFoundException when the provided runId
	 *     does not key to an existing <code>Run</code>
	 */
	public GradeWorkByStepAggregate getGradeWorkByStepAggregate(Long runId, String stepId) 
	    throws ObjectNotFoundException;
	
	
	/**
	 * Returns an aggregate object to allow WISE teachers to grade student
	 * work for a particular <code>Step</code> of a particular 
	 * <code>Project</code> that is being used in a <code>Run</code> indicated
	 * by the runId. The aggregate objects are separated into Periods that the workgroups
	 * belong to using a Map.
	 * 
	 * All of the workgroups' work is retrieved along with their respective
	 * <code>AnnotationBundle</code> for the particular step.
	 * 
	 * @param runId id of the run that the teacher wants to grade
	 * @param step particular step that the teacher wants to grade
	 * @return a Map from <code>Group</code> to <code>GradeWorkAggregate</code> containing all of the students'
	 *     work for the specified step.
	 * @throws ObjectNotFoundException when the provided runId
	 *     does not key to an existing <code>Run</code>
	 */
	public Map<Group,GradeWorkByStepAggregate> getGradeWorkByStepAggregateAllPeriods(Long runId, EStep step) 
	    throws ObjectNotFoundException;
	
	/**
	 * Returns an aggregate object to allow WISE teachers to grade student work
	 * of a particular <code>Workgroup</code> of a particular 
	 * <code>Project</code> that is being used in a <code>Run</code> indicated
	 * by the runId.
	 * 
	 * All of the specified workgroup's work is retrieved along with its
	 * <code>AnnotationBundle</code> for the entire project.
	 * 
	 * @param runId id of the run that the teacher wants to grade
	 * @param workgroupId particular workgroup that the teacher wants to grade
	 * @return <code>gradeWorkAggregate</code> containing all of the workgroup's
	 *     work for the entire project.
	 */
	public GradeWorkByWorkgroupAggregate getGradeWorkByStepAggregate(Long runId, Long workgroupId);
	
	/**
	 * Saves the grades and comments
	 * 
	 * @param annotationBundles all of the annotationbundles to save
	 */
	public void saveGrades(List<AnnotationBundle> annotationBundles);
	
	/**
	 * @param sessionBundleService <code>SessionBundleService</code> to set
	 */
	public void setSessionBundleService(SessionBundleService sessionBundleService);
}