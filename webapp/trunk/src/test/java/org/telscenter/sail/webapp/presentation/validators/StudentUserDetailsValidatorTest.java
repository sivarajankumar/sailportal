/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.presentation.validators;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;


import junit.framework.TestCase;

/**
 * @author Hiroki Terashima
 *
 * @version $Id: $
 */
public class StudentUserDetailsValidatorTest extends TestCase {
	
	private MutableUserDetails userDetails;

    private Errors errors;

    private static final String USERNAME = "NAME";

    private static final String PASSWORD = "PASS";

    private static final String EMPTY = "";

    private static final String SPACES = "    ";

    private static final String ILLEGAL1 = "<a>";

    private static final String ILLEGAL2 = "'";

    private static final String ILLEGAL3 = "\"";

    private static final String ILLEGAL4 = ";";

    private static final String LONG = "abcdefghijhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Validator userDetailsValidator;

    protected void setUp() throws Exception {
        super.setUp();
        userDetails = new StudentUserDetails();
        userDetails.setUsername(USERNAME);
        userDetails.setPassword(PASSWORD);
        errors = new BeanPropertyBindingResult(userDetails, "");
        userDetailsValidator = new StudentUserDetailsValidator();
    }

    public void testUsernameNullValidate() {
        userDetails.setUsername(null);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));
    }

    public void testUsernameEmptyValidate() {
        userDetails.setUsername(EMPTY);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));
    }

    public void testUsernameSpacesValidate() {
        userDetails.setUsername(SPACES);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));
    }

    public void testPasswordNullValidate() {
        userDetails.setPassword(null);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNull(errors.getFieldError("username"));
        assertNotNull(errors.getFieldError("password"));
    }

    public void testPasswordEmptyValidate() {
        userDetails.setPassword(EMPTY);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNull(errors.getFieldError("username"));
        assertNotNull(errors.getFieldError("password"));
    }

    public void testPasswordSpacesValidate() {
        userDetails.setPassword(SPACES);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNull(errors.getFieldError("username"));
        assertNotNull(errors.getFieldError("password"));
    }

    public void testUsernameIllegalChars1Validate() {
        userDetails.setUsername(ILLEGAL1);
        userDetailsValidator.validate(userDetails, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));

    }

    public void testUsernameIllegalChars2Validate() {
        userDetails.setUsername(ILLEGAL2);
        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));

    }

    public void testUsernameIllegalChars3Validate() {
        userDetails.setUsername(ILLEGAL3);
        userDetailsValidator.validate(userDetails, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));

    }

    public void testUsernameIllegalChars4Validate() {
        userDetails.setUsername(ILLEGAL4);
        userDetailsValidator.validate(userDetails, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));
    }

    public void testUsernameLongValidate() {
        assertTrue(LONG.length() > StudentUserDetailsValidator.MAX_USERNAME_LENGTH);
        userDetails.setUsername(LONG);
        userDetailsValidator.validate(userDetails, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("username"));
        assertNull(errors.getFieldError("password"));

    }

    protected void tearDown() throws Exception {
        super.tearDown();
        userDetails = null;
        errors = null;
    }


}
