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
package org.telscenter.sail.webapp.domain.run;


import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.group.Group;

import org.telscenter.sail.webapp.domain.Run;

/**
 * Stores information about a WISE student on a particular
 * <code>Run</code>:
 * * <code>User</code> student user. Must not be null
 * * <code>Run</code> which Run the student is in. Must not be null
 * * <code>Workgroup</code> which workgroup the student is in for
 *     this run. Can be null, if student is not in a workgroup yet
 *     for this run
 * * <code>Group</code> which period the student is in for this
 *     run. Must not be null
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentRunInfo implements Comparable<StudentRunInfo>{

	private User studentUser;
	
	private Run run;
	
	private Workgroup workgroup;
	
	private Group group;
	
	private String startProjectUrl;

	/**
	 * @return the studentUser
	 */
	public User getStudentUser() {
		return studentUser;
	}

	/**
	 * @param studentUser the studentUser to set
	 */
	public void setStudentUser(User studentUser) {
		this.studentUser = studentUser;
	}
	
	/**
	 * @return the run
	 */
	public Run getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(Run run) {
		this.run = run;
	}

	/**
	 * @return the workgroup
	 */
	public Workgroup getWorkgroup() {
		return workgroup;
	}

	/**
	 * @param workgroup the workgroup to set
	 */
	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * @return the startProjectUrl
	 */
	public String getStartProjectUrl() {
		return startProjectUrl;
	}

	/**
	 * @param startProjectUrl the startProjectUrl to set
	 */
	public void setStartProjectUrl(String startProjectUrl) {
		this.startProjectUrl = startProjectUrl;
	}
	
	public int compareTo(StudentRunInfo o){
		
		User bestTeacher = this.run.getOwners().iterator().next();
		User incomingBestTeacher = o.run.getOwners().iterator().next();

		if(!bestTeacher.getSdsUser().getLastName().equals(incomingBestTeacher.getSdsUser().getLastName())){
			return bestTeacher.getSdsUser().getLastName().compareTo(incomingBestTeacher.getSdsUser().getLastName());
		} else if(!bestTeacher.getSdsUser().getFirstName().equals(incomingBestTeacher.getSdsUser().getFirstName())){
			return bestTeacher.getSdsUser().getFirstName().compareTo(incomingBestTeacher.getSdsUser().getFirstName());
		} else {
		// TODO: HIROKI fix below code to work with when the name is not set in projectinfo.
			//return this.run.getProject().getProjectInfo().getName().compareTo(o.run.getProject().getProjectInfo().getName());
			return -1;
		}

	}

}
