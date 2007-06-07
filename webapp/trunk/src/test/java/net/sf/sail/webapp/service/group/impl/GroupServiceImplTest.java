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

import org.easymock.EasyMock;

import net.sf.sail.webapp.dao.group.GroupDao;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.service.group.CyclicalGroupException;
import junit.framework.TestCase;

/**
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class GroupServiceImplTest extends TestCase {

	private GroupDao<Group> mockGroupDao;
	
	private Group group;
	
	private GroupServiceImpl groupServiceImpl;
	
	private static final String[] DEFAULT_GROUP_NAMES = 
	   {"Period 1", "Period 2", "My Science Class"};
	
    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.groupServiceImpl = new GroupServiceImpl();

        this.mockGroupDao = EasyMock.createMock(GroupDao.class);
        this.groupServiceImpl.setGroupDao(this.mockGroupDao);
        
        this.group = new PersistentGroup();
    }
    
    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.groupServiceImpl = null;
        this.mockGroupDao = null;
        this.group = null;
    }
    
    public void testCreateGroup() {

    	// create group (saves automatically)
    	this.group.setName(DEFAULT_GROUP_NAMES[0]);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);
    	
    	Group actualGroup = this.groupServiceImpl.createGroup(DEFAULT_GROUP_NAMES[0]);
    	EasyMock.verify(this.mockGroupDao);
    	assertEquals(0, actualGroup.getMembers().size());
    	assertEquals(DEFAULT_GROUP_NAMES[0], actualGroup.getName());
    	assertNull(actualGroup.getParent());

    	// create another group with the same name
    	EasyMock.reset(this.mockGroupDao);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);

    	Group anotherGroup = this.groupServiceImpl.createGroup(DEFAULT_GROUP_NAMES[0]);
    	assertEquals(0, anotherGroup.getMembers().size());
    	assertEquals(DEFAULT_GROUP_NAMES[0], anotherGroup.getName());
    	assertEquals(anotherGroup.getName(), actualGroup.getName());
    	assertNull(anotherGroup.getParent());

    	EasyMock.verify(this.mockGroupDao);    	
    }
    
    public void testChangeGroupName() {
    	
    	this.group.setName(DEFAULT_GROUP_NAMES[0]);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);
    	
    	this.group = this.groupServiceImpl.createGroup(DEFAULT_GROUP_NAMES[0]);
    	EasyMock.verify(this.mockGroupDao);
    	assertEquals(DEFAULT_GROUP_NAMES[0], this.group.getName());

    	this.group.setName(DEFAULT_GROUP_NAMES[1]);
    	EasyMock.reset(this.mockGroupDao);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);
    	
    	this.groupServiceImpl.changeGroupName(this.group, DEFAULT_GROUP_NAMES[1]);
    	EasyMock.verify(this.mockGroupDao);
    	assertEquals(DEFAULT_GROUP_NAMES[1], this.group.getName());
    }

    // test creating a sub group. Creating this new group
    // will not create any cycles
    public void testCreateSubGroup_NoCycle() {
    	
    	this.group.setName(DEFAULT_GROUP_NAMES[2]);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);
    	
    	Group parentGroup = this.groupServiceImpl.createGroup(DEFAULT_GROUP_NAMES[2]);
    	EasyMock.verify(this.mockGroupDao);
    	assertEquals(DEFAULT_GROUP_NAMES[2], parentGroup.getName());
    	
    	EasyMock.reset(this.mockGroupDao);
    	this.group.setName(DEFAULT_GROUP_NAMES[0]);
    	this.group.setParent(parentGroup);
    	this.mockGroupDao.save(this.group);
    	EasyMock.expectLastCall();
    	EasyMock.replay(this.mockGroupDao);
    	
    	Group subGroup1 = null;
		try {
			subGroup1 = this.groupServiceImpl.createGroup(parentGroup, DEFAULT_GROUP_NAMES[0]);
			EasyMock.verify(this.mockGroupDao);
		} catch (CyclicalGroupException e) {
			fail("CyclicalGroupException NOT expected");
		}
		assertEquals(DEFAULT_GROUP_NAMES[0], subGroup1.getName());
		assertEquals(subGroup1.getParent(), parentGroup);
    }
    
    // test creating a sub group. Creating this new group
    // will create a cycle
    public void testCreateSubGroup_Cycle() {
    
    }
}
