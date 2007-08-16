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

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.group.GroupService;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.PeriodNotFoundException;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddProjectParameters;
import org.telscenter.sail.webapp.domain.impl.Projectcode;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * The Controller for Add a Project page for WISE students
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class AddProjectController extends SimpleFormController {

	private RunService runService;
	
	private GroupService groupService;

    private AclService<Run> aclService;

	/**
     * On submission of the Add a Project form, the logged-in user is added to 
     * the project run.
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
		// command contains a legally-formatted projectcode.
    	AddProjectParameters params = (AddProjectParameters) command;

    	ModelAndView modelAndView = null;
    	Run run = null;
    	Projectcode projectcode = new Projectcode(params.getProjectcode());
    	String runcode = projectcode.getRuncode();
    	String periodName = projectcode.getRunPeriod();
    	try {
    		run = this.runService.retrieveRunByRuncode(runcode);
			this.aclService.createAcl(run);
        	Group period = run.getPeriodByName(periodName);
        	Set<User> membersToAdd = new HashSet<User>();
        	membersToAdd.add(user);
        	this.groupService.addMembers(period, membersToAdd);
        	modelAndView = new ModelAndView(getSuccessView());
    	} catch (ObjectNotFoundException e) {
    		errors.rejectValue("projectcode", "error.illegal-projectcode");
    		return showForm(request, response, errors);
    	} catch (PeriodNotFoundException e) {
    		errors.rejectValue("projectcode", "error.illegal-projectcode");
    		return showForm(request, response, errors);
    	}
		return modelAndView;
    }
    
	/**
	 * @param groupService the groupService to set
	 */
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param aclService the aclService to set
	 */
	public void setAclService(AclService<Run> aclService) {
		this.aclService = aclService;
	}
}
