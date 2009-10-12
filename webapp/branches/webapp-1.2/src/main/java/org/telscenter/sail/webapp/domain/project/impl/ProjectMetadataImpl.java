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
package org.telscenter.sail.webapp.domain.project.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.telscenter.sail.webapp.domain.project.ProjectMetadata;

/**
 * @author patrick lawler
 * @version $Id:$
 */
@Entity
@Table(name = ProjectMetadataImpl.DATA_STORE_NAME)
public class ProjectMetadataImpl implements ProjectMetadata, Serializable{
	
	public final static String DATA_STORE_NAME = "project_metadata";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
	
	@Transient
	public final static String COLUMN_NAME_TITLE = "title";
	
	@Transient
	public final static String COLUMN_NAME_AUTHOR = "author";
	
	@Transient
	public final static String COLUMN_NAME_SUBJECT = "subject";
	
	@Transient
	public final static String COLUMN_NAME_DURATION = "duration";
	
	@Transient
	public final static String COLUMN_NAME_SUMMARY = "summary";

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Column(name = COLUMN_NAME_TITLE)
	private String title;

	@Column(name = COLUMN_NAME_AUTHOR)
	private String author;
	
	@Column(name = COLUMN_NAME_SUBJECT)
	private String subject;
	
	@Column(name = COLUMN_NAME_DURATION)
	private String duration;
	
	@Column(name = COLUMN_NAME_SUMMARY)
	private String summary;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
