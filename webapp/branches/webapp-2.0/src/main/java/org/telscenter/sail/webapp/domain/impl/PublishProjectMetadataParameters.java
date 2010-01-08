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
package org.telscenter.sail.webapp.domain.impl;

import java.io.Serializable;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class PublishProjectMetadataParameters implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String projectId;
	
	private String title;
	
	private String author;
	
	private String subject;
	
	private String summary;
	
	private String graderange;

	private String contact;
	
	private String techreqs;
	
	private String totaltime;
	
	private String comptime;

	private String lessonplan;
	
	/**
	 * @return <code>String</code> the id to get
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param <code>String</code> the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return <code>String</code> the title to get
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param <code>String</code> the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return <code>String</code> the author to get
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param <code>String</code> the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return <code>String</code> the subject to get
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param <code>String</code> the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return <code>String</code> the summary to get
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param <code>String</code> the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * @return <code>String</code> the grade range to get
	 */
	public String getGraderange() {
		return graderange;
	}

	/**
	 * @param <code>String</code> the grade range to set
	 */
	public void setGraderange(String graderange) {
		this.graderange = graderange;
	}

	/**
	 * @return <code>String</code> the contact to get
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param <code>String</code> the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return <code>String</code> the technical requirements to get
	 */
	public String getTechreqs() {
		return techreqs;
	}

	/**
	 * @param <code>String</code> the technical requirements to set
	 */
	public void setTechreqs(String techreqs) {
		this.techreqs = techreqs;
	}

	/**
	 * @return <code>String</code> the total time to get
	 */
	public String getTotaltime() {
		return totaltime;
	}

	/**
	 * @param <code>String</code> the total time to set
	 */
	public void setTotaltime(String totaltime) {
		this.totaltime = totaltime;
	}

	/**
	 * @return <code>String</code> the computer time to get
	 */
	public String getComptime() {
		return comptime;
	}

	/**
	 * @param <code>String</code> the computer time to set
	 */
	public void setComptime(String comptime) {
		this.comptime = comptime;
	}

	/**
	 * @return <code>String</code> the lessonplan
	 */
	public String getLessonplan() {
		return lessonplan;
	}

	/**
	 * @param <code>String</code> lessonplan the lessonplan to set
	 */
	public void setLessonplan(String lessonplan) {
		this.lessonplan = lessonplan;
	}
}
