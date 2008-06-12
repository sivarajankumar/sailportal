package org.telscenter.sail.webapp.domain.project;


/**
 * 
 * @author Carlos
 *
 */
public interface ProjectInfo {

	
	/**
	 * @return the author
	 */
	public String getAuthor();

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author);
	
	/**
	 * @return the gradeLevel
	 */
	public String getGradeLevel();
	
	/**
	 * @param gradeLevel the gradeLevel to set
	 */
	public void setGradeLevel(String gradeLevel);
	
	/**
	 * @return the subject
	 */
	public String getSubject();
	
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject);
	
	/**
	 * @return the keywords
	 */
	public String getKeywords();
	
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords);
	
	/**
	 * @return the projectLiveCycle
	 */
	public String getProjectLiveCycle();
	
	/**
	 * @param projectLiveCycle the projectLiveCycle to set
	 */
	public void setProjectLiveCycle(String projectLiveCycle);
	
	/**
	 * @return the familyTag
	 */
	public FamilyTag getFamilyTag();
	
	/**
	 * @param familyTag the familyTag to set
	 */
	public void setFamilyTag(FamilyTag familyTag);
	
	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent();

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent);
	
}
