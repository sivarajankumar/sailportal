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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.mail.IMailFacade;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Module;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.Schoollevel;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;
import org.telscenter.sail.webapp.domain.impl.DefaultPeriodNames;
import org.telscenter.sail.webapp.domain.impl.RunParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.module.ModuleService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.project.ProjectService;

import org.apache.commons.lang.StringUtils;

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
 * invoked, and steps 1-3 are bypassed.
 *
 * @author Hiroki Terashima
 * @version $Id: CreateRunController.java 941 2007-08-16 14:03:11Z laurel $
 */
public class CreateRunController extends AbstractWizardFormController {
	
	private RunService runService = null;
	
	private ModuleService moduleService = null;

	private ProjectService projectService = null;

	private static final String COMPLETE_VIEW_NAME = "teacher/run/create/setuprunconfirm";
	
	private static final String CANCEL_VIEW_NAME = "../../teacher/index.html";

	private static final String RUN_KEY = "run";
	
	private IMailFacade javaMail = null;
	
	private Properties emaillisteners = null;
	
	/**
	 * Constructor
	 *  - Specify the pages in the wizard
	 *  - Specify the command name
	 */
	public CreateRunController() {
		setBindOnNewForm(true);
		setPages(new String[]{"teacher/run/create/setuprun1", "teacher/run/create/setuprun2", "teacher/run/create/setuprun3", 
				"teacher/run/create/setuprun4", "teacher/run/create/setuprun5", "teacher/run/create/setuprun6"});
		setSessionForm(true);
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected void onBind(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		// TODO HT: implement me
	    super.onBind(request, command, errors);
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#onBindAndValidate(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.BindException, int)
	 */
	@Override
	protected void onBindAndValidate(HttpServletRequest request,
            Object command,
            BindException errors,
            int page) throws Exception {
		// TODO HT: implement me
	    super.onBindAndValidate(request, command, errors, page);
	}
	
	/**
	 * This method is called after the onBind and onBindAndValidate method. It acts 
	 * in the same way as the validator
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#validatePage(java.lang.Object, org.springframework.validation.Errors, int)
	 */
	@Override
	protected void validatePage(Object command, Errors errors, int page) {
	    super.validatePage(command, errors, page);
		RunParameters runParameters = (RunParameters) command;

	    switch (page) {
	    case 0:
	    	break;
	    case 1:
	    	break;
	    case 2:
	    	if (runParameters.getPeriodNames() == null || 
	    		runParameters.getPeriodNames().size() == 0) {
	    		if (runParameters.getManuallyEnteredPeriods() == ""){
	    			errors.rejectValue("periodNames", "setuprun.error.selectperiods", "You must select one or more periods or manually" +
	    				" create your period names.");
	    		} else {
	    			String[] parsed = StringUtils.split(runParameters.getManuallyEnteredPeriods(), ",");
	    			Set<String> parsedAndTrimmed = new TreeSet<String>();
	    			for(String current : parsed){
	    				String trimmed = StringUtils.trim(current);
	    				if(trimmed == "" || StringUtils.contains(trimmed, " ") || !StringUtils.isAlphanumeric(trimmed)){
	    					errors.rejectValue("periodNames", "setuprun.error.whitespaceornonalphanumeric", "Manually entered" +
	    							" periods cannot contain whitespace or non-alphanumeric characters.");
	    				} else {
	    					parsedAndTrimmed.add(trimmed);
	    				}
	    			}
	    			runParameters.setPeriodNames(parsedAndTrimmed);
	    			runParameters.setManuallyEnteredPeriods("");
	    		} 
	    	} else if (runParameters.getManuallyEnteredPeriods() != "") {
    			errors.rejectValue("periodNames", "setuprun.error.notsupported", "Selecting both periods AND" +
				" manually entering periods is not supported.");	    			
    		}
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
	}
	
	/**
	 * This method is called right before the view is rendered to the user
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#referenceData(javax.servlet.http.HttpServletRequest, int)
	 */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request, 
			Object command, Errors errors, int page) {
		String projectIdStr = request.getParameter("projectId");
		Long projectId = Long.valueOf(projectIdStr);
		RunParameters runParameters = (RunParameters) command;
		Module module = null;
		Project project = null;
		Map<String, Object> model = new HashMap<String, Object>();
		switch(page) {
		case 0:
			try {
				project = (Project) this.projectService.getById(projectId);
			} catch (ObjectNotFoundException e) {
				// TODO HT: what should happen when the project id is invalid?
				e.printStackTrace();
			}
			model.put("project", project);

			// set curnitId and jnlpId to use for this run
			runParameters.setCurnitId(project.getCurnit().getId());
			runParameters.setJnlpId(project.getJnlp().getId());
			// add the current user as an owner of the run
			User user = (User) request.getSession().getAttribute(
					User.CURRENT_USER_SESSION_KEY);
			Set<User> owners = new HashSet<User>();
			owners.add(user);
			runParameters.setOwners(owners);
			runParameters.setProject(project);
			break;
		case 1:
			// for page 2 of the wizard, display existing runs for this user
			List<Run> allRuns = runService.getRunList();
			user = (User) request.getSession().getAttribute(
					User.CURRENT_USER_SESSION_KEY);
			
			// this is a temporary solution to filtering out runs that the logged-in user owns.
			// when the ACL entry permissions is figured out, we shouldn't have to do this filtering
			// start temporary code
			List<Run> currentRuns = new ArrayList<Run>();
			for (Run run : allRuns) {
				if (run.getOwners().contains(user) &&
						!run.isEnded()) {
					currentRuns.add(run);
				}
			}
			// end temporary code
			
			model.put("existingRunList", currentRuns);

			// TODO HT: talk with Matt on how to set/change run name
			try {
				module = (Module) this.moduleService.getById(runParameters.getCurnitId());
			} catch (ObjectNotFoundException e) {
				// TODO HT: what should happen when the project id is invalid?
				e.printStackTrace();
			}
			runParameters.setName("Run: " + module.getSdsCurnit().getName());
			break;
		case 2:
			// for page 3 of the wizard, display available period names to the user
			model.put("periodNames", DefaultPeriodNames.values());
			break;
		case 3:
		case 4:
		case 5:
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
		Run run = null;
    	try {
			run = this.runService.createRun(runParameters);
		} catch (ObjectNotFoundException e) {
			errors.rejectValue("curnitId", "error.curnit-not_found",
					new Object[] { runParameters.getCurnitId() }, 
					"Curnit Not Found.");
			return showForm(request, response, errors);
		}
		ModelAndView modelAndView = new ModelAndView(COMPLETE_VIEW_NAME);
		modelAndView.addObject(RUN_KEY, run);

		sendEmail(request, command);
		
    	return modelAndView;
	}
	
	/*
	 * sends an email to individuals to notify them of a new project run
	 * having been set up by a teacher
	 */
	private void sendEmail(HttpServletRequest request, Object command)
			throws Exception {
		RunParameters runParameters = (RunParameters) command;
		String teacherName = null;
		String teacherEmail = null;
		String projectName = null;
		Long projectID = null;
		String schoolName = null;
		String schoolCity = null;
		String schoolState = null;
		
		//tries to retrieve the user from the session
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);


		TeacherUserDetails teacherUserDetails = 
			(TeacherUserDetails) user.getUserDetails();
			
		teacherName = teacherUserDetails.getFirstname() + " " + 
				teacherUserDetails.getLastname();
		teacherEmail = teacherUserDetails.getEmailAddress();

		schoolName = teacherUserDetails.getSchoolname();
		schoolCity = teacherUserDetails.getCity();
		schoolState = teacherUserDetails.getState();
		projectName = runParameters.getName();
		projectID = runParameters.getProject().getId();
		
		String[] recipients = {emaillisteners.getProperty("project_setup")};

		String subject = "[WISE 3.0 Run Setup]";
		String message = "This is an automated email generated by the WISE " +
			"3 Teacher's PET to let you know that a WISE teacher " + 
			"has finished preparations to run a WISE project.\n\n" + 
			
			"Teacher Name: " + teacherName + "\n" +
			"Teacher Email: " + teacherEmail + "\n" +
			"School Name: " + schoolName + "\n" +
			"School Location: " + schoolCity + ", " + schoolState + "\n" + 
			"Project Name: " + projectName + "\n" + 
			"Project ID: "+ projectID + "\n" +

			"\n\nThis does not guarantee that the project is actually going to " +
			"be run in the classroom, only that the teacher has gone " + 
			"through all the setup steps.";
		
		String fromEmail = teacherEmail;
		
		//sends the email to the recipients
		javaMail.postMail(recipients, subject, message, fromEmail);
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
		return new ModelAndView(new RedirectView(CANCEL_VIEW_NAME));
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param moduleService the projectService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}


	/**
	 * @return the javaMail
	 */
	public IMailFacade getJavaMail() {
		return javaMail;
	}

	/**
	 * @param javaMail the javaMail to set
	 */
	public void setJavaMail(IMailFacade javaMail) {
		this.javaMail = javaMail;
	}

	/**
	 * @return the emaillisteners
	 */
	public Properties getEmaillisteners() {
		return emaillisteners;
	}

	/**
	 * @param emaillisteners the emaillisteners to set
	 */
	public void setEmaillisteners(Properties emaillisteners) {
		this.emaillisteners = emaillisteners;
	}


	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}


}
