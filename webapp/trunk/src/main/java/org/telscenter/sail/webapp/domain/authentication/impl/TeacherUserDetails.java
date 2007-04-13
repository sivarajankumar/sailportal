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

	private static final long serialVersionUID = 1L;
	
	@Transient
	public static final String DATA_STORE_NAME = "teacher_user_details";

	@Transient
	public static final String COLUMN_NAME_FIRSTNAME = "firstname";

	@Transient
	public static final String COLUMN_NAME_LASTNAME = "lastname";
	
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

	
	private String firstname;
	private String lastname;
	private String city;
	private String state;
	private String country;
	private String schoolname;
	private String[] curriculumsubjects;
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
		// TODO Auto-generated method stub
		return null;
	}
	public String[] getUsernameSuffixes() {
		// TODO Auto-generated method stub
		return null;
	}
}
