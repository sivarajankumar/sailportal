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

import net.sf.sail.webapp.domain.impl.OfferingParameters;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * The validator for offering paramter objects
 * e.g. when creating new offerings
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class OfferingParametersValidator implements Validator {

	public boolean supports(Class clazz) {
		return OfferingParameters.class.isAssignableFrom(clazz);
	}

	public void validate(Object offeringParametersIn, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
        "error.offeringname-not-specified");
	    
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "curnitId",
	    		"error.offeringcurnitid-not-specified");

	    // TODO: LAW & HT
	    // what are other restrictions that we should check for here?
	}

}
