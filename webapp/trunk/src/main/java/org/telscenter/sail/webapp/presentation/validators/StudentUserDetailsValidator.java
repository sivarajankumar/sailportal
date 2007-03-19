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

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.presentation.validators.UserDetailsValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for the TELS Student User Details upon registering
 *
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class StudentUserDetailsValidator extends UserDetailsValidator implements Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class clazz) {
		return super.supports(clazz);
	}

	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object userDetailsIn, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		"error.password-not-specified");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
		"error.username-not-specified");
		if (errors.getFieldErrorCount("username") > 0) {
			return;
		}
		MutableUserDetails userDetails = (MutableUserDetails) userDetailsIn;
		if (!StringUtils.isAlphanumeric(userDetails.getUsername())) {
			errors.rejectValue("username", "error.illegal-characters");
		}
		if (userDetails.getUsername().length() > MAX_USERNAME_LENGTH) {
			errors.rejectValue("username", "error.too-long");
		}
		if (errors.hasErrors())
			userDetails.setPassword("");
	}

}
