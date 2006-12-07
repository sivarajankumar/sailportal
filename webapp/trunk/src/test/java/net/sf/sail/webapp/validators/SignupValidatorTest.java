/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.validators;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Laurel Williams
 *
 * @version $Id$
 */
public class SignupValidatorTest extends TestCase {
	
	private MutableUserDetails userDetails;
	private Errors errors;
	private static final String USERNAME = "NAME";
	private static final String PASSWORD = "PASS";
	private static final String EMPTY = "";
	private static final String SPACES = "    ";
	private Validator userDetailsValidator;
	
	protected void setUp() throws Exception {
		super.setUp();
		userDetails = new HibernateUserDetails();
		userDetails.setUsername(USERNAME);
		userDetails.setPassword(PASSWORD);
		errors = new BeanPropertyBindingResult(userDetails, "");
		userDetailsValidator = new UserDetailsValidator();
	}

	public void testUsernameNullValidate() {
		userDetails.setUsername(null);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNotNull(errors.getFieldError("username"));
		assertNull(errors.getFieldError("password"));		
	}
	
	public void testUsernameEmptyValidate() {
		userDetails.setUsername(EMPTY);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNotNull(errors.getFieldError("username"));
		assertNull(errors.getFieldError("password"));		
	}

	public void testUsernameSpacesValidate() {
		userDetails.setUsername(SPACES);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNotNull(errors.getFieldError("username"));
		assertNull(errors.getFieldError("password"));		
	}

	public void testPasswordNullValidate() {
		userDetails.setPassword(null);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNull(errors.getFieldError("username"));
		assertNotNull(errors.getFieldError("password"));		
	}
	
	public void testPasswordEmptyValidate() {
		userDetails.setPassword(EMPTY);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNull(errors.getFieldError("username"));
		assertNotNull(errors.getFieldError("password"));		
	}

	public void testPasswordSpacesValidate() {
		userDetails.setPassword(SPACES);
		
		userDetailsValidator.validate(userDetails, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(errors.getErrorCount(), 1);
		assertNull(errors.getFieldError("username"));
		assertNotNull(errors.getFieldError("password"));		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		userDetails = null;
		errors = null;
	}

}
