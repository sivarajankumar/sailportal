package org.telscenter.sail.webapp.presentation.validators;

import junit.framework.TestCase;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;

public class LostPasswordDetailsValidatorTest extends
		TestCase {
	
	protected TeacherUserDetails userDetails;
	protected Validator userDetailsValidator;
	protected Errors errors;
	
    protected void setUp() throws Exception {
        userDetails = new TeacherUserDetails();
       
        errors = new BeanPropertyBindingResult(userDetails, "");
        userDetailsValidator = new TeacherUserDetailsValidator();
    }

    public void testBothUsernameAndEmailNullValidate() {
    	userDetails.setEmailAddress(null);
    	userDetails.setUsername(null);
    	
    	userDetailsValidator.validate(userDetails, errors);
    	
    	assertTrue(errors.hasErrors());   	
    }
    
    public void testBothUsernameAndEmailNotNullValidate() {
    	userDetails.setEmailAddress("username");
    	userDetails.setUsername("email");
    	
    	userDetailsValidator.validate(userDetails, errors);
    	
    	assertTrue(errors.hasErrors());   	
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        userDetails = null;
        errors = null;
    }
}
