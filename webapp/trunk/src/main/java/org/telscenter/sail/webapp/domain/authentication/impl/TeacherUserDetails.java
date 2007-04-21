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
package org.telscenter.sail.webapp.domain.authentication.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;

import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;

/**
 * Implementation class of <code>MutableUserDetails</code> that uses an EJB3
 * compliant object persistence mechanism.
 * 
 * UserDetails for a teacher in TELS Portal
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = TeacherUserDetails.DATA_STORE_NAME)
public class TeacherUserDetails extends PersistentUserDetails implements
		MutableUserDetails {

	@Transient
	public static final String DATA_STORE_NAME = "teacher_user_details";

	@Transient
	public static final String COLUMN_NAME_FIRSTNAME = "firstname";

	@Transient
	public static final String COLUMN_NAME_LASTNAME = "lastname";

	@Transient
	private static final String COLUMN_NAME_SIGNUPDATE = "signupdate";
	
	@Transient
	public static final String COLUMN_NAME_CITY = "city";
	
	@Transient
	public static final String COLUMN_NAME_STATE = "state";

	@Transient
	public static final String COLUMN_NAME_COUNTRY = "country";

	@Transient
	public static final String COLUMN_NAME_SCHOOLNAME = "schoolname";
	
	@Transient
	public static final String COLUMN_NAME_CURRICULUMSUBJECTS = "curriculumsubjects";

	@Transient
	public static final String COLUMN_NAME_SCHOOLLEVEL = "schoollevel";


    @Transient
    private static final long serialVersionUID = 1L;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_FIRSTNAME, nullable = false)
	private String firstname;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_LASTNAME, nullable = false)
	private String lastname;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_SIGNUPDATE, nullable = false)
    private Date signupdate;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_CITY, nullable = false)
	private String city;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_STATE, nullable = false)
	private String state;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_COUNTRY, nullable = false)
	private String country;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_SCHOOLNAME, nullable = false)
	private String schoolname;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_CURRICULUMSUBJECTS, nullable = false)
	private String[] curriculumsubjects;
    
    @Column(name = TeacherUserDetails.COLUMN_NAME_SCHOOLLEVEL, nullable = false)
	private String schoollevel;
	
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the signupdate
	 */
	public Date getSignupdate() {
		return signupdate;
	}
	/**
	 * @param signupdate the signupdate to set
	 */
	public void setSignupdate(Date signupdate) {
		this.signupdate = signupdate;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the curriculumsubjects
	 */
	public String[] getCurriculumsubjects() {
		return curriculumsubjects;
	}
	/**
	 * @param curriculumsubjects the curriculumsubjects to set
	 */
	public void setCurriculumsubjects(String[] curriculumsubjects) {
		this.curriculumsubjects = curriculumsubjects;
	}
	/**
	 * @return the schoollevel
	 */
	public String getSchoollevel() {
		return schoollevel;
	}
	/**
	 * @param schoollevel the schoollevel to set
	 */
	public void setSchoollevel(String schoollevel) {
		this.schoollevel = schoollevel;
	}
	/**
	 * @return the schoolname
	 */
	public String getSchoolname() {
		return schoolname;
	}
	/**
	 * @param schoolname the schoolname to set
	 */
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @see org.telscenter.sail.webapp.domain.authenticationMutableUserDetails.getCoreUsername()
	 */
	public String getCoreUsername() {
		return firstname + " " + lastname;
	}
	/**
	 * @see org.telscenter.sail.webapp.domain.authenticationMutableUserDetails.getUsernameSuffixes()
	 */
	public String[] getUsernameSuffixes() {
		return new String[] {"", "a", "b", "c", "d", "e", "f", "g", "h",
	            "i", "j", "k", "l", "m", "n", "o", "p"};
	}
}
