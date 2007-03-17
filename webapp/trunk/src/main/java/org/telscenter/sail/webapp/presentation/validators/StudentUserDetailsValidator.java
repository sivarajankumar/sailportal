/**
 * TODO: replace with TELS PORTAL copyright information
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
