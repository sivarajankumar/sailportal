package org.telscenter.sail.webapp.domain.project.impl;

import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;


/**
 * 
 * @author Carlos
 *
 */
public class ProjectInfoImpl implements ProjectInfo {

	private String author;
	private String gradeLevel;
	private String subject;
	private String keywords;
	private String projectLiveCycle;
	private FamilyTag familyTag;
	private boolean isCurrent;
	
	
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the gradeLevel
	 */
	public String getGradeLevel() {
		return gradeLevel;
	}
	/**
	 * @param gradeLevel the gradeLevel to set
	 */
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * @return the projectLiveCycle
	 */
	public String getProjectLiveCycle() {
		return projectLiveCycle;
	}
	/**
	 * @param projectLiveCycle the projectLiveCycle to set
	 */
	public void setProjectLiveCycle(String projectLiveCycle) {
		this.projectLiveCycle = projectLiveCycle;
	}
	/**
	 * @return the familyTag
	 */
	public FamilyTag getFamilyTag() {
		return familyTag;
	}
	/**
	 * @param familyTag the familyTag to set
	 */
	public void setFamilyTag(FamilyTag familyTag) {
		this.familyTag = familyTag;
	}
	
	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
}
