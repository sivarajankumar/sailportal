package net.sf.sail.webapp.presentation.validators;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserDetailsValidator implements Validator {

  public static final int MAX_USERNAME_LENGTH = 50;

  public boolean supports(Class clazz) {
    return MutableUserDetails.class.isAssignableFrom(clazz);
  }

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