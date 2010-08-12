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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.presentation.util.json.JSONException;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;

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
	public final static String COLUMN_NAME_SUMMARY = "summary";

	@Transient
	public final static String COLUMN_NAME_GRADE_RANGE = "grade_range";
	
	@Transient
	public final static String COLUMN_NAME_TOTAL_TIME = "total_time";
	
	@Transient
	public final static String COLUMN_NAME_COMP_TIME = "comp_time";
	
	@Transient
	public final static String COLUMN_NAME_CONTACT = "contact";
	
	@Transient
	public final static String COLUMN_NAME_TECH_REQS = "tech_reqs";
	
	@Transient
	public final static String COLUMN_NAME_LESSON_PLAN = "lesson_plan";
	
	@Transient
	public final static String COLUMN_NAME_KEYWORDS = "keywords";
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	public final static String COLUMN_NAME_PROJECT_FK = "project_fk";
	
	@Transient
	public final static String COLUMN_NAME_VERSION_ID = "version_id";
	
	@Transient
	public final static String COLUMN_NAME_LAST_CLEANED = "last_cleaned";
	
	@Transient
	public final static String COLUMN_NAME_LAST_EDITED = "last_edited";
	
	@Transient
	public final static String COLUMN_NAME_POST_LEVEL = "post_level";
	
	@Column(name = COLUMN_NAME_TITLE)
	private String title;

	@Column(name = COLUMN_NAME_AUTHOR)
	private String author;
	
	@Column(name = COLUMN_NAME_SUBJECT)
	private String subject;
	
	@Column(name = COLUMN_NAME_SUMMARY)
	private String summary;
	
	@Column(name = COLUMN_NAME_GRADE_RANGE)
	private String gradeRange;
	
	@Column(name = COLUMN_NAME_TOTAL_TIME)
	private Long totalTime;
	
	@Column(name = COLUMN_NAME_COMP_TIME)
	private Long compTime;
	
	@Column(name = COLUMN_NAME_CONTACT)
	private String contact;
	
	@Column(name = COLUMN_NAME_TECH_REQS)
	private String techReqs;
	
	@Column(name = COLUMN_NAME_LESSON_PLAN)
	private String lessonPlan;
	
	@Column(name = COLUMN_NAME_KEYWORDS)
	private String keywords;
	
	@Column(name = COLUMN_NAME_PROJECT_FK)
	private Long projectId;
	
	@Column(name = COLUMN_NAME_VERSION_ID)
	private String versionId;
	
	@Column(name = COLUMN_NAME_LAST_CLEANED)
	private Date lastCleaned;
	
	@Column(name = COLUMN_NAME_LAST_EDITED)
	private Date lastEdited;
	
	@Column(name = COLUMN_NAME_POST_LEVEL)
	private Long postLevel;
	
	public ProjectMetadataImpl() {
		
	}

	public ProjectMetadataImpl(JSONObject metadataJSON) {
		this.populateFromJSON(metadataJSON);
	}

	public String getGradeRange() {
		return gradeRange;
	}

	public void setGradeRange(String gradeRange) {
		this.gradeRange = gradeRange;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Long getCompTime() {
		return compTime;
	}

	public void setCompTime(Long compTime) {
		this.compTime = compTime;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTechReqs() {
		return techReqs;
	}

	public void setTechReqs(String techReqs) {
		this.techReqs = techReqs;
	}

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

	/**
	 * @return the lessonPlan
	 */
	public String getLessonPlan() {
		return lessonPlan;
	}

	/**
	 * @param lessonPlan the lessonPlan to set
	 */
	public void setLessonPlan(String lessonPlan) {
		this.lessonPlan = lessonPlan;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the versionId
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * @param versionId the versionId to set
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	/**
	 * @return the projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	public void setLastCleaned(Date lastCleaned) {
		this.lastCleaned = lastCleaned;
	}

	public Date getLastCleaned() {
		return lastCleaned;
	}

	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}

	public Date getLastEdited() {
		return lastEdited;
	}
	
	public Long getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(Long postLevel) {
		this.postLevel = postLevel;
	}
	
	public void populateFromJSON(JSONObject metadataJSON) {
		try {
			if(metadataJSON.has("title")) {
				setTitle(metadataJSON.getString("title"));	
			}
			
			if(metadataJSON.has("author")) {
				setAuthor(metadataJSON.getString("author"));
			}
			
			if(metadataJSON.has("subject")) {
				setSubject(metadataJSON.getString("subject"));	
			}
			
			if(metadataJSON.has("summary")) {
				setSummary(metadataJSON.getString("summary"));	
			}
			
			if(metadataJSON.has("grade_range")) {
				setGradeRange(metadataJSON.getString("grade_range"));	
			}
			
			if(metadataJSON.has("total_time")) {
				setTotalTime(metadataJSON.getLong("total_time"));	
			}
			
			if(metadataJSON.has("comp_time")) {
				setCompTime(metadataJSON.getLong("comp_time"));	
			}
			
			if(metadataJSON.has("contact")) {
				setContact(metadataJSON.getString("contact"));	
			}
			
			if(metadataJSON.has("tech_reqs")) {
				setTechReqs(metadataJSON.getString("tech_reqs"));	
			}
			
			if(metadataJSON.has("lesson_plan")) {
				setLessonPlan(metadataJSON.getString("lesson_plan"));				
			}

			if(metadataJSON.has("keywords")) {
				setKeywords(metadataJSON.getString("keywords"));			
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public String toJSONString() {
		JSONObject metadata = new JSONObject(this);
		
		return metadata.toString();
	}
}
