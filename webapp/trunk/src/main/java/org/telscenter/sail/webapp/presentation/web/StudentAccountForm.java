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

import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;

/**
 * StudentAccountForm encapsulates all of the data necessary to register/update 
 * a student account
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentAccountForm extends UserAccountForm {

	private static final long serialVersionUID = 1L;
	
	private String projectCode;
	private String accountQuestion;
	private String accountAnswer;
	
	public StudentAccountForm() {
		userDetails = new StudentUserDetails();
		newAccount = true;
	}
	
	public StudentAccountForm(StudentUserDetails studentUserDetails) {
		userDetails = studentUserDetails;
		newAccount = false;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the accountQuestion
	 */
	public String getAccountQuestion() {
		return accountQuestion;
	}

	/**
	 * @param accountQuestion the accountQuestion to set
	 */
	public void setAccountQuestion(String accountQuestion) {
		this.accountQuestion = accountQuestion;
	}

	/**
	 * @return the accountAnswer
	 */
	public String getAccountAnswer() {
		return accountAnswer;
	}

	/**
	 * @param accoiuntAnswer the accountAnswer to set
	 */
	public void setAccountAnswer(String accountAnswer) {
		this.accountAnswer = accountAnswer;
	}

}
