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
package org.telscenter.sail.webapp.presentation.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.impl.ChangePasswordParameters;
import org.telscenter.sail.webapp.domain.impl.Passwords;

/**
 * Validator for student's ChangePasswordParameters
 * 
 * @author Patrick Lawler
 * @author Sally Ahn
 * $Id:$
 */
public class ChangePasswordParametersValidator implements Validator {

	protected static final int MAX_PASSWORD_LENGTH = 20;

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return ChangePasswordParameters.class.isAssignableFrom(clazz);
	}

	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object paramsIn, Errors errors) {
		ChangePasswordParameters params = (ChangePasswordParameters) paramsIn;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd1",
				"error.no-password");
		
		if (errors.getErrorCount() != 0) {
			return;
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd2",
				"error.no-password");
		
		if (errors.getErrorCount() != 0) {
			return;
		}

		if (params.getPasswd1().length() > MAX_PASSWORD_LENGTH || 
				params.getPasswd2().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("passwd1", "error.password-too-long");
			return;
		}

		if (!StringUtils.isAlphanumeric(params.getPasswd1()) || 
				!StringUtils.isAlphanumeric(params.getPasswd2())) {
			errors.rejectValue("passwd1", "error.password-illegal-characters");
			return;
		}

		Passwords passwords = new Passwords(params.getPasswd1(), params.getPasswd2());

		if (!passwords.match()) {
			errors.rejectValue("passwd1", "error.passwords-mismatch");
		}

	}

}
