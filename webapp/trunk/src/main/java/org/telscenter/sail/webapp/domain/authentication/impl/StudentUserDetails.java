/**
 * Copyright Me
 */
package org.telscenter.sail.webapp.domain.authentication.impl;

import java.util.Date;

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
 * @version $Id: $
 */
@Entity
@Table(name = StudentUserDetails.DATA_STORE_NAME)
public class StudentUserDetails extends PersistentUserDetails implements
		MutableUserDetails {

	private static final long serialVersionUID = 1L;

	@Transient
	public static final String DATA_STORE_NAME = "student_user_details";
	
	@Transient
	public static final String COLUMN_NAME_FIRSTNAME = "firstname";

	@Transient
	public static final String COLUMN_NAME_LASTNAME = "lastname";

	@Transient
	public static final String COLUMN_NAME_GENDER = "gender";
	
	@Transient
	public static final String COLUMN_NAME_BIRTHDAY = "birthday";
	
	private String firstname;
	private String lastname;
	private Gender gender;
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


}
