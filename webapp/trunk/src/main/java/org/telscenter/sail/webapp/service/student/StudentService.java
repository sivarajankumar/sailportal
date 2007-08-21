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
package org.telscenter.sail.webapp.service.student;

import org.telscenter.sail.webapp.domain.PeriodNotFoundException;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.Projectcode;
import org.telscenter.sail.webapp.service.offering.RunService;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.group.GroupService;

/**
 * Represents the set of operations on a WISE Student user.
 *
 * @author Hiroki Terashima
 * @version $ Id: $
 */
public interface StudentService {

	/**
	 * Given a User object of a student and a <code>Projectcode</code>,
	 * associates the student with the <code>Run</code> by adding the 
	 * student to the period that belongs to the <code>Run</code>, 
	 * as indicated by the periodname portion of the <code>Projectcode</code>.
	 * 
	 * @param studentUser a <code>User</code> object of a WISE student
	 * @param projectcode a <code>Projectcode</code>
	 * @throws ObjectNotFoundException when the runcode portion of 
	 *      <code>Projectcode</code> could not be used to retrieve 
	 *      an existing <code>Run</code>
	 * @throws PeriodNotFoundException when the periodname portion of
	 *      <code>Projectcode</code> could not be used to retrieve
	 *      an existing period associated with the <code>Run</code>
	 */
	public void addStudentToRun(User studentUser, Projectcode projectcode) 
	     throws ObjectNotFoundException, PeriodNotFoundException;
	
	/**
	 * @param runService <code>RunService</code> to set
	 */
	public void setRunService(RunService runService);

	/**
	 * @param groupService <code>GroupService</code> to set
	 */
	public void setGroupService(GroupService groupService);
	
	/**
	 * @param aclService <code>AclService</code> to set
	 */
	public void setAclService(AclService<Run> aclService);
}
