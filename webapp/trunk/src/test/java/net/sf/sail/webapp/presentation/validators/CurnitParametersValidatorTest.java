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
package net.sf.sail.webapp.presentation.validators;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.sf.sail.webapp.domain.impl.CurnitParameters;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class for CurnitParametersValidator
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class CurnitParametersValidatorTest {
	
	private static final String LEGAL_CURNIT_NAME = "Airbags";

	private static final String LEGAL_CURNIT_URL = "http://legalcurniturl.jar";

	private static final String EMPTY_STRING = "";
	private CurnitParameters curnitParameters;
	
	private Validator curnitParametersValidator;
	
	private Errors errors;
	
	@Before
	public void setUp() {
		curnitParameters = new CurnitParameters();
		curnitParameters.setName(LEGAL_CURNIT_NAME);
		curnitParameters.setUrl(LEGAL_CURNIT_URL);
		errors = new BeanPropertyBindingResult(curnitParameters, "");
		curnitParametersValidator = new CurnitParametersValidator();
	}
	
	@Test
	public void testAllLegal() {
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertFalse(errors.hasErrors());
		assertEquals(0, errors.getErrorCount());
		assertNull(errors.getFieldError("name"));
		assertNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testEmptyCurnitName() {
		curnitParameters.setName(EMPTY_STRING);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));
		assertNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testNullCurnitName() {
		curnitParameters.setName(null);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));
		assertNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testEmptyCurnitUrl() {
		curnitParameters.setUrl(EMPTY_STRING);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertNull(errors.getFieldError("name"));
		assertNotNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testEmptyNullUrl() {
		curnitParameters.setUrl(null);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertNull(errors.getFieldError("name"));
		assertNotNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testCurnitNameCurnitUrlEmpty() {
		curnitParameters.setName(EMPTY_STRING);
		curnitParameters.setUrl(EMPTY_STRING);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(2, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));
		assertNotNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testCurnitNameCurnitUrlNull() {
		curnitParameters.setName(null);
		curnitParameters.setUrl(null);
		
		curnitParametersValidator.validate(curnitParameters, errors);
		
		assertTrue(errors.hasErrors());
		assertEquals(2, errors.getErrorCount());
		assertNotNull(errors.getFieldError("name"));
		assertNotNull(errors.getFieldError("url"));
	}
	
	@Test
	public void testCurnitUrlInvalid() {
		// TODO Hiroki implement url format validation
		// in controller and then fill in this method
		assertTrue(true);
	}
	
	@After
	public void tearDown() {
		curnitParameters = null;
		errors = null;
		curnitParametersValidator = null;
	}
	
}
