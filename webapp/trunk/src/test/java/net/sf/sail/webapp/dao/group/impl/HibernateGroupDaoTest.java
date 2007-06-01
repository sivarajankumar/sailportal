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
package net.sf.sail.webapp.dao.group.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test class for HibernateGroupDao
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateGroupDaoTest extends AbstractTransactionalDbTests {
	
	private static final String ROOT_GROUP_NAME = "My Class";

	private HibernateGroupDao groupDao;
	
	private Group rootGroup;
	
	private Set<Group> intermediateGroups;
	
	private final String[] INTERMEDIATE_GROUP_NAMES = {"Period 1", "Period 2", "Period Sunflower"};
			
	/**
	 * @param groupDao 
	 *             the groupDao to set
	 */
	public void setGroupDao(HibernateGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/**
	 * @param rootGroup 
	 *              the rootGroup to set
	 */
	public void setRootGroup(Group rootGroup) {
		this.rootGroup = rootGroup;
	}

	/**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
    	super.onSetUpBeforeTransaction();
    	this.rootGroup.setName(ROOT_GROUP_NAME);
    	this.intermediateGroups = new HashSet<Group>();
    	for (String group_name : INTERMEDIATE_GROUP_NAMES) {
    		Group group = new PersistentGroup();
    		group.setName(group_name);
    		group.setParent(this.rootGroup);
        	this.intermediateGroups.add(group);    		
    	}
    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
    }

    /**
     * Test root Group node 
     */
	public void testSave_NoMembers_NoParent() {
		verifyDataStoreGroupListIsEmpty();

		this.groupDao.save(this.rootGroup);
		List actualList = retrieveGroupListFromDb();
		assertEquals(1, actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			Map actualGroupMap = (Map) actualList.get(i);
			// * NOTE * the keys in the map are all in UPPERCASE!
			System.out.println("Map:" + actualGroupMap.toString());
			String group_name_actual = (String) actualGroupMap
			        .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
			assertEquals(ROOT_GROUP_NAME, group_name_actual);

		}
	}

	/**
	 * Test intermediate Group node
	 */
	public void testSave_NoMembers_Parent() {
		verifyDataStoreGroupListIsEmpty();

		// allGroupNames contains names of all groups: root, intrmdte, leaf
		List<String> allGroupNames = new ArrayList<String>();
		allGroupNames.add(this.rootGroup.getName());
		
		this.groupDao.save(this.rootGroup);
		for (Group group : this.intermediateGroups) {
			allGroupNames.add(group.getName());
			this.groupDao.save(group);
		}

		List actualList = retrieveGroupListFromDb();
		assertEquals(INTERMEDIATE_GROUP_NAMES.length + 1, actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			Map actualGroupMap = (Map) actualList.get(i);
			// * NOTE * the keys in the map are all in UPPERCASE!
			String group_name_actual = (String) actualGroupMap
			        .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
			assertTrue(allGroupNames.contains(group_name_actual));
			
			List parentGroupList = 
				getParentGroupGivenImmediateChildGroupName(group_name_actual);
			if (group_name_actual != ROOT_GROUP_NAME) {
				assertEquals(1, parentGroupList.size());
			}
		}
	}
	
	
	// TEST DELETE 
	
	// TEST GETTING ALL STUDENTS UNDER PARTICULAR PATH...Not just for the parent node
	// but also for all of the children nodes...this means that the user needs to have
	// permission
	
	private void verifyDataStoreGroupListIsEmpty() {
		assertTrue(retrieveGroupListFromDb().isEmpty());
	}
	
	/*
	 * SELECT * FROM groups
	 */
	private static final String RETRIEVE_GROUP_LIST_SQL = "SELECT * FROM "
		+ PersistentGroup.DATA_STORE_NAME;
	
	/*
	 * SELECT * FROM groups g1 WHERE g1.id = 
	 * SELECT g2.parent_fk FROM groups g2 WHERE g2.name = :childname
	 */
	private List getParentGroupGivenImmediateChildGroupName(String childName) {
		String sqlQuery = 
			RETRIEVE_GROUP_LIST_SQL + " g1 WHERE g1.id = "
			 + "SELECT g2." + PersistentGroup.COLUMN_NAME_PARENT_FK
		     + " FROM " + PersistentGroup.DATA_STORE_NAME + " g2 WHERE g2." 
		     + PersistentGroup.COLUMN_NAME_NAME + " = '" + childName + "'";
		
		return retrieveGroupListFromDb(sqlQuery);
	}
			
	private List retrieveGroupListFromDb() {
		return this.jdbcTemplate.queryForList(RETRIEVE_GROUP_LIST_SQL, 
				(Object[]) null);
	}
	
	private List retrieveGroupListFromDb(String sqlQuery) {
		return this.jdbcTemplate.queryForList(sqlQuery, 
				(Object[]) null);
	}

}
