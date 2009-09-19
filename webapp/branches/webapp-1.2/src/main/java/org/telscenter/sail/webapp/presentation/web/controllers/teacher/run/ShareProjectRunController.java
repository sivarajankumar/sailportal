/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * Controller for handling requests to grant/modify permissions on
 * Project runs.
 * 
 * @author Hiroki Terashima
 * @author Patrick Lawler
 * @author Matt Fishbach
 * @version $Id:$
 */
public class ShareProjectRunController extends SimpleFormController {
	
	private RunService runService;
	
	private WISEWorkgroupService workgroupService = null;

	private UserService userService;
	
	protected static final String RUNID_PARAM_NAME = "runId";

	protected static final String RUN_PARAM_NAME = "run";
	
	/**
	 * Adds the AddSharedTeacherParameters object as a form-backing
	 * object. This object will be filled out and submitted for adding
	 * new teachers to the shared teachers list.
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		AddSharedTeacherParameters params = new AddSharedTeacherParameters();
		params.setRun(runService.retrieveById(Long.parseLong(request.getParameter(RUNID_PARAM_NAME))));
		params.setPermission(UserDetailsService.RUN_READ_ROLE);
		return params;
	}
	
    /**
     * Adds the existing shared teachers and their permissions for
     * the run requested to the page.
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) 
	    throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Run run = runService.retrieveById(Long.parseLong(request.getParameter(RUNID_PARAM_NAME)));
		Set<User> sharedowners = run.getSharedowners();

		for (User sharedowner : sharedowners) {
			String sharedTeacherRole = runService.getSharedTeacherRole(run, sharedowner);
			AddSharedTeacherParameters addSharedTeacherParameters = 
				new AddSharedTeacherParameters();
			addSharedTeacherParameters.setPermission(sharedTeacherRole);
			addSharedTeacherParameters.setRun(run);
			addSharedTeacherParameters.setSharedOwnerUsername(
					sharedowner.getUserDetails().getUsername());
			model.put(sharedowner.getUserDetails().getUsername(), 
					addSharedTeacherParameters);
		}
		model.put(RUN_PARAM_NAME, run);
		
		return model;
	}

	/**
     * On submission of the AddSharedTeacherParameters, the specified
     * teacher is granted the specified permission to the specified run.
     * Only teachers can be added as a shared teacher to a run.
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) {
    	AddSharedTeacherParameters params = (AddSharedTeacherParameters) command;
    	User retrievedUser = userService.retrieveUserByUsername(params.getSharedOwnerUsername());
    	ModelAndView modelAndView;
    	
    	if (retrievedUser == null) {
    		modelAndView = new ModelAndView(new RedirectView("shareprojectrun.html"));
	    	modelAndView.addObject(RUNID_PARAM_NAME, params.getRun().getId());
	    	modelAndView.addObject("message", "Username not recognized. Make sure to use the exact spelling of the username.");
	    	return modelAndView;
    	} else if (!retrievedUser.getUserDetails().hasGrantedAuthority(UserDetailsService.TEACHER_ROLE)) {
    		modelAndView = new ModelAndView(new RedirectView("shareprojectrun.html"));
	    	modelAndView.addObject(RUNID_PARAM_NAME, params.getRun().getId());
	    	modelAndView.addObject("message", "The user is not a teacher and thus cannot be added as a shared teacher.");
	    	return modelAndView;
    	} else {
    	try {
			runService.addSharedTeacherToRun(params);
			
			// make a workgroup for this shared teacher for this run
			String sharedOwnerUsername = params.getSharedOwnerUsername();
			User sharedOwner = userService.retrieveUserByUsername(sharedOwnerUsername);
			Set<User> sharedOwners = new HashSet<User>();
			sharedOwners.add(sharedOwner);
			workgroupService.createWISEWorkgroup("teacher", sharedOwners, params.getRun(), null);
		} catch (ObjectNotFoundException e) {
			modelAndView = new ModelAndView(new RedirectView(getFormView()));
	    	modelAndView.addObject(RUNID_PARAM_NAME, params.getRun().getId());
	    	return modelAndView;
		}
    	modelAndView = new ModelAndView(new RedirectView(getSuccessView()));
    	modelAndView.addObject(RUNID_PARAM_NAME, params.getRun().getId());
    	return modelAndView;
    	}
    }

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}

