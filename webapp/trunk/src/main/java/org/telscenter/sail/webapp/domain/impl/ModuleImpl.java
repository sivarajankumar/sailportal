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
package org.telscenter.sail.webapp.domain.impl;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.telscenter.sail.webapp.domain.Module;

/**
 * WISE "Project" domain object. A WISE Project is a Curnit with more
 * information.
 *
 * @author Hiroki Terashima
 * @author Sally
 * 
 * @version $Id: $
 */
@Entity
@Table(name = ModuleImpl.DATA_STORE_NAME)
public class ModuleImpl extends CurnitImpl implements Module {

	@Transient
	public static final String DATA_STORE_NAME = "projects";
	
	@Transient
	private static final long serialVersionUID = 1L;
	
    @Transient
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    @Transient
    public static final String COLUMN_NAME_GRADES = "grades";
    
    @Transient
    public static final String COLUMN_NAME_TOPICKEYWORDS = "topicKeywords";

    @Transient
    public static final String COLUMN_NAME_COMPUTERTIME = "computer_time";
    
    @Transient
    public static final String COLUMN_NAME_TOTALTIME = "total_time";
    
    @Transient
    public static final String COLUMN_NAME_TECHREQS = "tech_reqs";
    
    @Transient
    public static final String PROJECTS_JOIN_COLUMN_NAME = "projects_fk";

    @Transient
    public static final String OWNERS_JOIN_TABLE_NAME = "projects_related_to_owners";
    
    @Transient
    public static final String OWNERS_JOIN_COLUMN_NAME = "owners_fk";

    @Column(name = ModuleImpl.COLUMN_NAME_DESCRIPTION, nullable = false)
	private String description;

    @Column(name = ModuleImpl.COLUMN_NAME_GRADES, nullable = false)
	private Set<Integer> grades;
    
    @Column(name = ModuleImpl.COLUMN_NAME_TOPICKEYWORDS, nullable = false)
	private Set<String> topicKeywords;
	
    @Column(name = ModuleImpl.COLUMN_NAME_TOTALTIME, nullable = false)
	private Long totalTime;
	
    @Column(name = ModuleImpl.COLUMN_NAME_COMPUTERTIME, nullable = false)
	private Long computerTime;
	
    @Column(name = ModuleImpl.COLUMN_NAME_TECHREQS, nullable = false)
	private String techReqs;
	
    @ManyToMany(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = OWNERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = PROJECTS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = OWNERS_JOIN_COLUMN_NAME, nullable = false))
	private Set<User> owners;
		
	public Set<Integer> getGrades() {
		return grades;
	}

	public void setGrades(Set<Integer> grades) {
		this.grades = grades;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#getComputerTime()
	 */
	public Long getComputerTime() {
		return computerTime	;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#getTotalTime()
	 */
	public Long getTotalTime() {
		return totalTime;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#setComputerTime()
	 */
	public void setComputerTime(Long computerTime) {
		this.computerTime = computerTime;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#setTotalTime()
	 */
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#getOWNERS()
	 */
	public Set<User> getOwners() {
		return owners;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#getTechReqs()
	 */
	public String getTechReqs() {
		return techReqs;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#setOWNERS(java.util.Set)
	 */
	public void setOwners(Set<User> owners) {
		this.owners = owners;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#setTechReqs(java.lang.String)
	 */
	public void setTechReqs(String techReqs) {
		this.techReqs = techReqs;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#getTopicKeywords()
	 */
	public Set<String> getTopicKeywords() {
		return topicKeywords;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.domain.Project#setTopicKeywords(java.util.Set)
	 */
	public void setTopicKeywords(Set<String> topicKeywords) {
		this.topicKeywords = topicKeywords;
	}
	
	

}
