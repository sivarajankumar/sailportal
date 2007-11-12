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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.impl.ChangeWorkgroupParameters;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

/**
 * @author Sally Ahn
 * @version $Id: $
 */
public class ChangeWorkgroupController extends SimpleFormController {

	private WorkgroupService workgroupService;
	
	private static final String STUDENT_PARAM_NAME = "student";
	
	private static final String WORKGROUPFROM_PARAM_NAME = "workgroupFrom";
	
	private static final String WORKGROUPTO_PARAM_NAME = "workgroupTo";
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		ChangeWorkgroupParameters params = new ChangeWorkgroupParameters();
		params.setStudent((User) request.getAttribute(STUDENT_PARAM_NAME));
		params.setWorkgroupFrom((Workgroup) request.getAttribute(WORKGROUPFROM_PARAM_NAME));
		params.setWorkgroupTo((Workgroup) request.getAttribute(WORKGROUPTO_PARAM_NAME));
		return params;
	}
	
	@Override
    protected ModelAndView onSubmit(HttpServletRequest request, 
    		HttpServletResponse response, Object command, BindException errors){
    	ChangeWorkgroupParameters params = (ChangeWorkgroupParameters) command;

    	ModelAndView modelAndView = null;

   		workgroupService.updateWorkgroupMembership(params);
    	modelAndView = new ModelAndView(getSuccessView());

    	return modelAndView;
    }

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
	
	
	
}
