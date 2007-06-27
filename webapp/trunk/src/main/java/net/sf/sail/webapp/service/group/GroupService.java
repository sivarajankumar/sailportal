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
package net.sf.sail.webapp.service.group;

import java.util.List;
import java.util.Set;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.GroupParameters;

/**
 * @author Hiroki Terashima
 * @version $Id$
 * 
 * Performs actions on groups which should be transactional.
 */
public interface GroupService {

	/**
	 * Given a <code>GroupParameters</code> object, create a group and save it
	 * in the data store. If groupParameters contains a name and a null parent
	 * id, then a root type group is created. If groupParameters has both a name
	 * and a parent group id then an intermediate type group is created. If the
	 * parent group id given is not in the data store then a root group type is
	 * created.
	 * 
	 * @param groupParameters
	 * 
	 * @return The Group that was created
	 */
	public Group createGroup(GroupParameters groupParameters);

	/**
	 * Change an existing group name.
	 * 
	 * @param group
	 *            an existing <code>Group</code> that should have its name
	 *            changed
	 * @param name
	 *            <code>String</code> name of new group
	 */
	public void changeGroupName(Group group, String newName);

	/**
	 * Makes a group into a child of another group
	 * 
	 * @throws CyclicalGroupException
	 *             when this action creates a cycle
	 */
	public void moveGroup(Group newParent, Group groupToBeMoved)
			throws CyclicalGroupException;

	/**
	 * Adds members to an already-existing group If a member already exists in
	 * the group, do not add again
	 * 
	 * @param group
	 *            and existing <code>Group</code> that the members should be
	 *            added to
	 * @param membersToAdd
	 *            <code>Set</code> of users to add to the group
	 */
	public void addMembers(Group group, Set<User> membersToAdd);

	/**
	 * Gets all the groups available.
	 * 
	 * @return <code>List</code> of <code>Group</code>
	 */
	public List<Group> getGroups();
}