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
package org.telscenter.sail.webapp.presentation.validators.teacher;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.telscenter.sail.webapp.domain.impl.ChangeWorkgroupParameters;
import org.telscenter.sail.webapp.domain.impl.StartRunParameters;

/**
 * Validator for ChangeWorkgroupParamters object used to change workgroups
 * 
 * @author Sally Ahn
 * @version $Id: $
 */
public class ChangeWorkgroupParametersValidator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return StartRunParameters.class.isAssignableFrom(clazz);
	}

	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object paramsIn, Errors errors) {
		ChangeWorkgroupParameters params = (ChangeWorkgroupParameters) paramsIn;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "student", "error.no-student");
		
		if (errors.getErrorCount() != 0) {
			return;
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workgroupFrom", "error.no-workgroupFrom");
		
		if (errors.getErrorCount() != 0) {
			return;
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "workgroupTo", "error.no-workgroupTo");
		
		if (errors.getErrorCount() != 0) {
			return;
		}
		
		User student = params.getStudent();
		Workgroup workgroupFrom = params.getWorkgroupFrom();
		
		if (!workgroupFrom.getMembers().contains(student)) {
			errors.rejectValue("student", "error.student-not-found");
		}


	}
	
	
}
