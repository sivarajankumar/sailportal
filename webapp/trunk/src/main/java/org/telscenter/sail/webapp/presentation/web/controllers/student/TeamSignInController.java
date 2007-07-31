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
package org.telscenter.sail.webapp.presentation.web.controllers.student;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.presentation.web.TeamSignInForm;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * Controller for handling team sign-ins before students start the project
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class TeamSignInController extends SimpleFormController {

	private UserService userService;
	
	private WorkgroupService workgroupService;
	
	private RunService runService;
	
	private HttpRestTransport httpRestTransport;

	public TeamSignInController() {
		setSessionForm(true);
	}
	
	/**
	 * On submission of the Team Sign In form, the workgroup is updated
	 * Assume that the usernames are valid usernames that exist in the data store
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
	throws Exception {

		TeamSignInForm teamSignInForm = (TeamSignInForm) command;
		User user1 = userService.retrieveUserByUsername(teamSignInForm.getUsername1());
		User user2 = userService.retrieveUserByUsername(teamSignInForm.getUsername2());
		User user3 = userService.retrieveUserByUsername(teamSignInForm.getUsername3());
		
		Run run = runService.retrieveById(teamSignInForm.getRunId());
		//List<Workgroup> workgrouplist = workgroupService.getWorkgroupListByOfferingAndUser(run, user1);

		Set<User> members = new HashSet<User>();
		String workgroupname = user1.getUserDetails().getUsername();
		members.add(user1);
		if (user2 != null) {
			members.add(user2);
			workgroupname += user2.getUserDetails().getUsername();
		}
		if (user3 != null) {
			members.add(user3);
			workgroupname += user3.getUserDetails().getUsername();
		}
	
		Workgroup workgroup = workgroupService.createWorkgroup(workgroupname, members, run);
		
		ModelAndView modelAndView = 
			new ModelAndView(new RedirectView(this.httpRestTransport.getBaseUrl() + "/offering/" + 
					run.getSdsOffering().getSdsObjectId() + "/jnlp/" +
					workgroup.getSdsWorkgroup().getSdsObjectId()));
		
		return modelAndView;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);

		TeamSignInForm form = new TeamSignInForm();
		form.setUsername1(user.getUserDetails().getUsername());
		form.setRunId(Long.valueOf(request.getParameter("runId")));

		return form;
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
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param httpRestTransport the httpRestTransport to set
	 */
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}
}
