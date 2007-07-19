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
package net.sf.sail.webapp.service.group.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.sail.webapp.dao.group.GroupDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.GroupParameters;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.group.CyclicalGroupException;
import net.sf.sail.webapp.service.group.GroupService;

import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.NotFoundException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

/**
 * A class to provide services for Group objects.
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class GroupServiceImpl implements GroupService {

    private GroupDao<Group> groupDao;
    
    private UserDao<User> userDao;

    private AclService<Group> groupAclService;
    
    /**
	 * @param groupAclService the groupAclService to set
	 */
    @Required
	public void setGroupAclService(AclService<Group> groupAclService) {
		this.groupAclService = groupAclService;
	}

	/**
     * @see net.sf.sail.webapp.service.group.GroupService#changeGroupName(net.sf.sail.webapp.domain.group.Group,
     *      java.lang.String)
     */
    @Transactional()
    public void changeGroupName(Group group, String newName) {
        group.setName(newName);
        this.groupDao.save(group);
    }

    /**
     * @see net.sf.sail.webapp.service.group.GroupService#createGroup(net.sf.sail.webapp.domain.group.impl.GroupParameters)
     */
    @Transactional(rollbackFor = { AlreadyExistsException.class,
            NotFoundException.class, DataIntegrityViolationException.class })
    public Group createGroup(GroupParameters groupParameters) {
        Group group = new PersistentGroup();
        group.setName(groupParameters.getName());
        
        Long parentId = groupParameters.getParentId();
        if (parentId != 0) {
            Group parentGroup = this.groupDao.getById(parentId);
            group.setParent(parentGroup);
        }
        
        for (Long memberId : groupParameters.getMemberIds()) {
        	group.addMember(this.userDao.getById(memberId));
        }
        
        this.groupDao.save(group);
        this.groupAclService.createAcl(group);
        return group;
    }
    
    //TODO - LAW - if we put in delete group remember to put in deletes for ACL entries

    /**
     * @see net.sf.sail.webapp.service.group.GroupService#moveGroup(net.sf.sail.webapp.domain.group.Group,
     *      net.sf.sail.webapp.domain.group.Group)
     */
    @Transactional()
    public void moveGroup(Group newParent, Group groupToBeMoved)
            throws CyclicalGroupException {
        Group previousParent = groupToBeMoved.getParent();
        groupToBeMoved.setParent(newParent);
        if (cycleExists(groupToBeMoved)) {
            // if cycle exists, reset groupToBeMoved's parent
            groupToBeMoved.setParent(previousParent);
            throw new CyclicalGroupException("Cycle will be created"
                    + " when this group is moved.");
        }
        this.groupDao.save(groupToBeMoved);
    }

    /**
     * @see net.sf.sail.webapp.service.group.GroupService#addMembers(net.sf.sail.webapp.domain.group.Group,
     *      java.util.Set)
     */
    @Transactional()
    public void addMembers(Group group, Set<User> membersToAdd) {
        for (User member : membersToAdd) {
            group.addMember(member);
        }
        this.groupDao.save(group);
    }

    /**
     * Checks to see if the given group contains a cycle
     * 
     * @param group
     *                <code>Group</code> group to be checked for cycles
     * @return boolean true iff the given group contains a cycle
     */
    private boolean cycleExists(Group group) {
        if (group.getParent().equals(group))
            return true;

        // traverse up the parent until null (no cycle) or
        // until group is reached again (cycle)
        List<Group> visitedSoFar = new ArrayList<Group>();
        Group toCheck = group;
        while ((toCheck = toCheck.getParent()) != null) {
            if (visitedSoFar.contains(toCheck)) {
                // use the equals() to check for equality
                // don't use TreeSet&hashCode, or it will
                // go into infinite loop
                return true;
            } else {
                visitedSoFar.add(toCheck);
            }
        }
        return false;
    }

    /**
     * @see net.sf.sail.webapp.service.group.GroupService#getGroups()
     */
    @Transactional(readOnly = true)
    public List<Group> getGroups() {
        return this.groupDao.getList();
    }

    /**
     * @param mutableAclService
     *                the mutableAclService to set
     */
//    @Required
//    public void setMutableAclService(MutableAclService mutableAclService) {
//        this.mutableAclService = mutableAclService;
//    }

    /**
     * @param groupDao
     *                the groupDao to set
     */
    @Required
    public void setGroupDao(GroupDao<Group> groupDao) {
        this.groupDao = groupDao;
    }

	/**
	 * @param userDao the userDao to set
	 */
    @Required
	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}
    
    
}