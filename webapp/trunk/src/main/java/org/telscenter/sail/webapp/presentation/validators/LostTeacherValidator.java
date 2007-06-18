package org.telscenter.sail.webapp.presentation.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;

public class LostTeacherValidator extends TeacherUserDetailsValidator {
	

	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object userDetailsIn, Errors errors) {
		TeacherUserDetails userDetails = (TeacherUserDetails) userDetailsIn;

		
		String username = StringUtils.trimToNull(userDetails.getUsername());
		String email = StringUtils.trimToNull(userDetails.getEmailAddress());
		
		
		if (username == null
				&& email == null) {
			errors.reject("error.no-email-username");
			return;
	
		} else if( username != null && email != null ) {
			errors.reject("error.both-email-username");
			return;
		}// if

	}
}
