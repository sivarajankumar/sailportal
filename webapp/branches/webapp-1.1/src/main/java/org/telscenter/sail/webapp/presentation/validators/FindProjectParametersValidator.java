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

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.telscenter.sail.webapp.domain.impl.FindProjectParameters;
import org.telscenter.sail.webapp.service.project.ProjectService;
import org.springframework.validation.ValidationUtils;

/**
 * @author patrick lawler
 *
 */
public class FindProjectParametersValidator implements Validator{
	
	private ProjectService projectService;

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return FindProjectParameters.class.isAssignableFrom(clazz);
	}
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object paramsIn, Errors errors) {
		FindProjectParameters param = (FindProjectParameters) paramsIn;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectId", "error.projectId-not-supplied");
		
		if(errors.hasErrors())
			return;
		
		if(!StringUtils.isNumeric(param.getProjectId())){
			errors.reject("error.projectId-not-numeric", "Project ID must be numeric.");
			return;
		}
		
		try{
			projectService.getById(Long.parseLong(param.getProjectId()));
		} catch (Exception e){
			errors.reject("error.projectId-not-found", "Project ID not found.");
			return;
		}
	}

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
