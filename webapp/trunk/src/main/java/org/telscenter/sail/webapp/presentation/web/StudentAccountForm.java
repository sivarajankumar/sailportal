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
package org.telscenter.sail.webapp.presentation.web;

import java.io.Serializable;

import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;

/**
 * TODO: Description of file
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentAccountForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private StudentUserDetails studentUserDetails;
	
	private boolean newAccount;

	private String repeatedPassword;
	
	public StudentAccountForm() {
		studentUserDetails = new StudentUserDetails();
		newAccount = true;
	}
	
	public StudentAccountForm(StudentUserDetails studentUserDetails) {
		this.studentUserDetails = studentUserDetails;
		newAccount = false;
	}

	/**
	 * @return the newAccount
	 */
	public boolean isNewAccount() {
		return newAccount;
	}

	/**
	 * @param newAccount the newAccount to set
	 */
	public void setNewAccount(boolean newAccount) {
		this.newAccount = newAccount;
	}

	/**
	 * @return the repeatedPassword
	 */
	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	/**
	 * @param repeatedPassword the repeatedPassword to set
	 */
	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	/**
	 * @return the studentUserDetails
	 */
	public StudentUserDetails getUserDetails() {
		return studentUserDetails;
	}

	/**
	 * @param studentUserDetails the studentUserDetails to set
	 */
	public void setUserDetails(StudentUserDetails studentUserDetails) {
		this.studentUserDetails = studentUserDetails;
	}

}
