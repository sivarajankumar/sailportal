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
package org.telscenter.sail.webapp.service.student.impl;

import java.util.HashSet;
import java.util.Set;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.group.GroupService;

import org.telscenter.sail.webapp.domain.PeriodNotFoundException;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.Projectcode;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;

/**
 * @author Hiroki Terashima
 * @version $ Id: $
 */
public class StudentServiceImpl implements StudentService {

	private RunService runService;
	
	private GroupService groupService;

    private AclService<Run> aclService;

	/**
	 * @see org.telscenter.sail.webapp.service.student.StudentService#addStudentToRun(net.sf.sail.webapp.domain.User, org.telscenter.sail.webapp.domain.impl.Projectcode)
	 */
	public void addStudentToRun(User studentUser, Projectcode projectcode) 
	    throws ObjectNotFoundException, PeriodNotFoundException {
		// TODO HT: figure out if we need a Transactional annotation for this method
		// possible problem: groupService.addMembers is transactional
		// we probably need a rollback though
		String runcode = projectcode.getRuncode();
    	String periodName = projectcode.getRunPeriod();

		Run run = this.runService.retrieveRunByRuncode(runcode);
		// this.aclService.addPermission(run, BasePermission.READ); TODO HT: uncomment when this is figured out
    	Group period = run.getPeriodByName(periodName);
    	Set<User> membersToAdd = new HashSet<User>();
    	membersToAdd.add(studentUser);
    	this.groupService.addMembers(period, membersToAdd);
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param groupService the groupService to set
	 */
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * @param aclService the aclService to set
	 */
	public void setAclService(AclService<Run> aclService) {
		this.aclService = aclService;
	}

}
