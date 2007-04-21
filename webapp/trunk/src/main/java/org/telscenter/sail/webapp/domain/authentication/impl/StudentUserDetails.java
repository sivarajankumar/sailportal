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

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;

import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;

/**
 * Implementation class of <code>MutableUserDetails</code> that uses an EJB3
 * compliant object persistence mechanism.
 * 
 * UserDetails for a student in TELS Portal
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = StudentUserDetails.DATA_STORE_NAME)
public class StudentUserDetails extends PersistentUserDetails implements
		MutableUserDetails {

	@Transient
	public static final String DATA_STORE_NAME = "student_user_details";
	
	@Transient
	public static final String COLUMN_NAME_FIRSTNAME = "firstname";

	@Transient
	public static final String COLUMN_NAME_LASTNAME = "lastname";

	@Transient
	private static final String COLUMN_NAME_SIGNUPDATE = "signupdate";

	@Transient
	public static final String COLUMN_NAME_GENDER = "gender";
	
	@Transient
	public static final String COLUMN_NAME_BIRTHDAY = "birthday";

    @Transient
    private static final long serialVersionUID = 1L;

    @Column(name = StudentUserDetails.COLUMN_NAME_FIRSTNAME, nullable = false)
	private String firstname;
    
    @Column(name = StudentUserDetails.COLUMN_NAME_LASTNAME, nullable = false)
	private String lastname;
    
    @Column(name = StudentUserDetails.COLUMN_NAME_SIGNUPDATE, nullable = false)
    private Date signupdate;
    
    @Column(name = StudentUserDetails.COLUMN_NAME_GENDER, nullable = false)
	private Gender gender;
    
    @Column(name = StudentUserDetails.COLUMN_NAME_BIRTHDAY, nullable = false)
	private Date birthday;
	
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
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
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
	public Date getSignupdate() {
		return signupdate;
	}
	public void setSignupdate(Date signupdate) {
		this.signupdate = signupdate;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @see org.telscenter.sail.webapp.domain.authenticationMutableUserDetails.getCoreUsername()
	 */
	public String getCoreUsername() {
		String firstname        = getFirstname();
		String lastnameInitial  = getLastname().substring(0, 1);

		Calendar birthday       = Calendar.getInstance();
		birthday.set(0, 6, 19);		
		//setBirthday(birthday.getTime());  // TODO HT: don't hard-code this. get it from form
		
		int birthmonth          = birthday.get(Calendar.MONTH);
		int birthdate           = birthday.get(Calendar.DATE);

		return firstname + lastnameInitial +
   		       birthmonth + birthdate;
	}
	/**
	 * @see org.telscenter.sail.webapp.domain.authenticationMutableUserDetails.getUsernameSuffixes()
	 */
	public String[] getUsernameSuffixes() {
		return new String[] {"", "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p"};
	}
}
