/**
 * 
 */
package org.telscenter.sail.webapp.presentation.validators.student;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.impl.ChangePasswordParameters;
import org.telscenter.sail.webapp.domain.impl.Passwords;

/**
 * @author patricklawler
 * $Id:$
 */
public class ChangePasswordParametersValidator implements Validator{
	
	protected static final int MAX_PASSWORD_LENGTH = 20;
	
	public boolean supports(Class clazz) {
		return ChangePasswordParameters.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object paramsIn, Errors errors) {
		ChangePasswordParameters params = (ChangePasswordParameters) paramsIn;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd1",
				"error.no-password");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd2",
				"error.no-password");		
		
		if (params.getPasswd1().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("passwd1", "error.password-too-long");
			return;
		}

		
		if (params.getPasswd2().length() > MAX_PASSWORD_LENGTH) {
			errors.rejectValue("passwd2", "error.password-too-long");
			return;
		}
		
		if (!StringUtils.isAlphanumeric(params.getPasswd1())) {
			errors.rejectValue("passwd1", "error.password-illegal-characters");
			return;
		}
		
		if (!StringUtils.isAlphanumeric(params.getPasswd2())) {
			errors.rejectValue("passwd2", "error.password-illegal-characters");
			return;
		}
		
		if (errors.getErrorCount() != 0) {
			return;
		}
		
		Passwords passwords = new Passwords(params.getPasswd1(), params.getPasswd2());

		if(!passwords.match()){
			errors.rejectValue("passwd1", "error.passwords-mismatch");
		}
		
	}


}
