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

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;

import net.sf.sail.webapp.presentation.validators.UserDetailsValidatorTest;

/**
 * Test class for TeacherUserDetailsValidator
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class TeacherUserDetailsValidatorTest extends UserDetailsValidatorTest {
	private MutableUserDetails userDetails;

    private Errors errors;

    private static final String USERNAME = "NAME";

    private static final String PASSWORD = "PASS";
    
    private static final String FIRSTNAME = "firstname";
    
    private static final String LASTNAME = "lastname";

    private static final String EMPTY = "";

    private static final String SPACES = "    ";

	private static final String PASSWORD_TOO_LONG = "abcdefghijklmnopqrstuvwxyz";

    private Validator userDetailsValidator;

    protected void setUp() throws Exception {
        super.setUp();
        userDetails = new TeacherUserDetails();
        userDetails.setUsername(USERNAME);
        userDetails.setPassword(PASSWORD);
        userDetails.setFirstname(FIRSTNAME);
        userDetails.setLastname(LASTNAME);
        errors = new BeanPropertyBindingResult(userDetails, "");
        userDetailsValidator = new TeacherUserDetailsValidator();
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
    
    public void testPasswordTooLongValidate() {
    	assertTrue(PASSWORD_TOO_LONG.length() > TeacherUserDetailsValidator.MAX_PASSWORD_LENGTH);
    	userDetails.setPassword(PASSWORD_TOO_LONG);
    	
    	userDetailsValidator.validate(userDetails, errors);
    	assertTrue(errors.hasErrors());
    	assertEquals(1, errors.getErrorCount());
    	assertNull(errors.getFieldError("username"));
    	assertNotNull(errors.getFieldError("password"));
    }
    
    public void testPasswrdIllegalChars1Validate() {
        userDetails.setPassword(ILLEGAL1);
        userDetailsValidator.validate(userDetails, errors);
        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNull(errors.getFieldError("username"));
        assertNotNull(errors.getFieldError("password"));
    }

    public void testEmailEmptyValidate() {
        userDetails.setEmailAddress(EMPTY);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("emailAddress"));
    }

    public void testEmailSpacesValidate() {
        userDetails.setEmailAddress(SPACES);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNotNull(errors.getFieldError("emailAddress"));
    }
    
    /* TODO: 
     * 1) Add email validation to TeacherUserDetailsValidator
     * 2) uncomment this code
    public void testEmailIllegalFormatValidate() {
        userDetails.setEmailAddress(SPACES);

        userDetailsValidator.validate(userDetails, errors);

        assertTrue(errors.hasErrors());
        assertEquals(1, errors.getErrorCount());
        assertNull(errors.getFieldError("username"));
        assertNotNull(errors.getFieldError("password"));
    }
    */
    
    protected void tearDown() throws Exception {
        super.tearDown();
        userDetails = null;
        errors = null;
    }


}
