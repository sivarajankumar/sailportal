package net.sf.sail.webapp.validators;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserDetailsValidator implements Validator {

	public UserDetailsValidator() {
		super();
	}

	public boolean supports(Class clazz) {
		return clazz.equals(HibernateUserDetails.class);
	}

	public void validate(Object userDetailsIn, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","error.password-not-specified");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username-not-specified");
		MutableUserDetails userDetails = (MutableUserDetails) userDetailsIn;
		if (errors.hasErrors())
			userDetails.setPassword("");
	}

}