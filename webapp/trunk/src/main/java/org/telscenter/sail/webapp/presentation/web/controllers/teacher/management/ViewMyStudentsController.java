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
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import org.telscenter.sail.webapp.service.workgroup.*;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.workgroup.*;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.domain.Run;
import net.sf.sail.webapp.domain.group.*;

import java.util.*;


/**
 * @author patricklawler
 * $Id:$
 */
public class ViewMyStudentsController extends AbstractController{

	protected static final String FALSE = "FALSE";

	private RunService runService;

	private WorkgroupService workgroupService;

	private HttpRestTransport httpRestTransport;

	/**
	 * @param httpRestTransport
	 *            the httpRestTransport to set
	 */
	@Required
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}

	protected final static String HTTP_TRANSPORT_KEY = "http_transport";

	protected final static String CURRENT_RUN_LIST_KEY = "current_run_list";

	protected final static String WORKGROUP_MAP_KEY = "workgroup_map";
	
	protected final static String GRADING_PARAM = "gradingParam";
	
	protected final static String NON_WORKGROUP_STUDENT_LIST = "grouplessStudents";

	static final String DEFAULT_PREVIEW_WORKGROUP_NAME = "Preview";
	
	private static final String VIEW_NAME = "teacher/management/viewmystudents";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws Exception {
		
    	ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
    	ControllerUtil.addUserToModelAndView(servletRequest, modelAndView);
 
		User user = (User) modelAndView.getModel().get(ControllerUtil.USER_KEY);
		List<Run> runList = this.runService.getRunList();
		// this is a temporary solution to filtering out runs that the logged-in user owns.
		// when the ACL entry permissions is figured out, we shouldn't have to do this filtering
		// start temporary code
		List<Run> runList2 = new ArrayList<Run>();
		for (Run run : runList) {
			if (run.getOwners().contains(user)) {
				runList2.add(run);
			}
		}
		// end temporary code
		Map<Group, Set<User>> grouplessStudents = new HashMap<Group, Set<User>>();
		Map<Group, List<Workgroup>> workgroupMap = new HashMap<Group, List<Workgroup>>();
		List<Run> current_run_list = new ArrayList<Run>();
		for (Run run : runList2) {
			
			Set<Workgroup> workgroups = this.runService.getWorkgroups(run.getId());
			for(Workgroup workgroup : workgroups){
				if (workgroup.getMembers().size() > 0){
					Group period = run.getPeriodOfStudent(workgroup.getMembers().iterator().next());
					if (workgroupMap.containsKey(period)){
						workgroupMap.get(period).add(workgroup);
					} else {
						ArrayList<Workgroup> newList = new ArrayList<Workgroup>();
						newList.add(workgroup);
						workgroupMap.put(period, newList);
					}
				}
			}
			
			for(Group period : run.getPeriods()){
				Set<User> periodStudents = new HashSet<User>();
				periodStudents.addAll(period.getMembers());
				for(Workgroup workgroup : workgroups){
					periodStudents.removeAll(workgroup.getMembers());
				}
				grouplessStudents.put(period, periodStudents);
			}
			
			if (!run.isEnded()) {
				current_run_list.add(run);
			}
		}

		modelAndView.addObject(NON_WORKGROUP_STUDENT_LIST, grouplessStudents);
		modelAndView.addObject(CURRENT_RUN_LIST_KEY, current_run_list);
		modelAndView.addObject(WORKGROUP_MAP_KEY, workgroupMap);
		modelAndView.addObject(HTTP_TRANSPORT_KEY, this.httpRestTransport);
		return modelAndView;
	}

	/**
	 * @param workgroupService
	 *            the workgroupService to set
	 */
	@Required
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param offeringService
	 *            the offeringService to set
	 */
	@Required
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
}
