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
package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.service.curnit.CurnitNotFoundException;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.impl.RunParameters;

/**
 * Controller for the wizard to "create a run"
 * 
 * The default getTargetPage() method is used to find out which page to navigate to, so
 * the controller looks for a request parameter starting with "_target" and ending with
 * a number (e.g. "_target1"). The jsp pages should provide these parameters.
 *
 * General method invocation flow (when user clicks on "prev" and "next"): 
 *  1) onBind
 *  2) onBindAndValidate
 *  3) validatePage
 *  4) referenceData
 * Note that on user's first visit to the first page of the wizard, only referenceData will be
 * invoked, and steps 1-4 are bypassed.
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class CreateRunController extends AbstractWizardFormController {
	
	protected OfferingService runService = null;
	
	/**
	 * Constructor
	 *  - Specify the pages in the wizard
	 *  - Specify the command name
	 */
	public CreateRunController() {
		setBindOnNewForm(true);
		setPages(new String[]{"setuprun1", "setuprun2", "setuprun3", 
				"setuprun4", "setuprun5", "setuprun6"});
		setSessionForm(true);
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBind(HttpServletRequest request,
			Object command, BindException errors) {
		// TODO HT: implement me
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#onBindAndValidate(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.BindException, int)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request,
            Object command,
            BindException errors,
            int page) {
		// TODO HT: implement me
	}
	
	/**
	 * This method is called after the onBind and onBindAndValidate method. It acts 
	 * in the same way as the validator
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#validatePage(java.lang.Object, org.springframework.validation.Errors, int)
	 */
	@Override
	protected void validatePage(Object command, Errors errors, int page) {
		// TODO HT: implement me
	}
	
	/**
	 * This method is called right before the view is rendered to the user
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#referenceData(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request, 
			Object command, Errors errors, int page) {
		RunParameters runParameters = (RunParameters) command;	
		Long curnitId = runParameters.getCurnitId();
		System.out.println(curnitId);
		Map<String, Object> model = new HashMap<String, Object>();
		switch(page) {
		case 0:
			model.put("project", "hello1");
			break;
		case 1:
			model.put("project", "hello2");
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		default:
			break;
		}

		return model;
	}
	
	/**
	 * Creates a run.
	 * 
	 * This method is called if there is a submit that validates and contains the "_finish"
	 * request parameter.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processFinish(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processFinish(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		RunParameters runParameters = (RunParameters) command;

    	// TODO: LAW or HT: shouldn't createOffering throw exception?
    	// e.g. CurnitNotFoundException and JNLPNotFoundException
    	// answer: yes
    	try {
			this.runService.createOffering(runParameters);
		} catch (CurnitNotFoundException e) {
			errors.rejectValue("curnitId", "error.curnit-not_found",
					new Object[] { runParameters.getCurnitId() }, 
					"Curnit Not Found.");
			return showForm(request, response, errors);
		}
    	return new ModelAndView("index.html");
	}
	
	/**
	 * This method is called if there is a submit that contains the "_cancel"
	 * request parameter.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#processCancel(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView processCancel(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) {
		return new ModelAndView(new RedirectView("index.html"));
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(OfferingService runService) {
		this.runService = runService;
	}

}
