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
import java.util.List;
import java.util.Map;

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

	private static String ROOT_GROUP_NAME = "My Science Classes";

	private HibernateGroupDao groupDao;

	private Group rootGroup;

	private List<Group> intermediateGroups;

	private final String[] INTERMEDIATE_GROUP_NAMES = { "Period 1", "Period 2", "Another Group" };

	private final String[] LEAF_GROUP_NAMES = { "" };

	/**
	 * @param groupDao
	 *            the groupDao to set
	 */
	public void setGroupDao(HibernateGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/**
	 * @param rootGroup
	 *            the rootGroup to set
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
		this.intermediateGroups = new ArrayList<Group>();
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
		List actualGroupList = retrieveAllGroupsListFromDb();
		assertEquals(1, actualGroupList.size());
		
		checkRootGroupIsValid();

		confirmNoMembersInAnyGroup();
	}

	/**
	 * Test intermediate Group nodes 
	 */
	public void testSave_NoMembers_Parent() {
		verifyDataStoreGroupListIsEmpty();

		// when a root node doesn't exist and you save an intermediate node, 
		// the root node will be saved along with the intermediate node
		Group intermediateGroup1 = intermediateGroups.get(0);
		this.groupDao.save(intermediateGroup1);

		List actualList = this.retrieveAllGroupsListFromDb();
		assertEquals(2, actualList.size());
		checkRootGroupIsValid();
		checkIntermediateGroupsAreValid(intermediateGroup1);
		confirmNoMembersInAnyGroup();

		
		Group intermediateGroup2 = intermediateGroups.get(1);
		this.groupDao.save(intermediateGroup2);
		
		actualList = this.retrieveAllGroupsListFromDb();
		assertEquals(3, actualList.size());
		checkRootGroupIsValid();
		checkIntermediateGroupsAreValid(intermediateGroup1, intermediateGroup2);
		confirmNoMembersInAnyGroup();
		
		
		//change rootParentGroup3 (ie. change rootGroup)
		
		// if you change the rootGroup's name, intermediateGroup's parent_fk should
		// stay the same
		Group intermediateGroup3 = this.intermediateGroups.get(2);
		Group rootParentGroup3 = intermediateGroup3.getParent();
		ROOT_GROUP_NAME = "My Science and Math Classes";
		rootParentGroup3.setName(ROOT_GROUP_NAME);
		
		this.groupDao.save(intermediateGroup3);
		this.toilet.flush();     // need to flush the toilet to force cascade
		actualList = this.retrieveAllGroupsListFromDb();
		assertEquals(4, actualList.size());
		checkRootGroupIsValid();
		checkIntermediateGroupsAreValid(intermediateGroup1, intermediateGroup2, intermediateGroup3);
		confirmNoMembersInAnyGroup();
		
		//retrieve each group and check - ensure root changes propagate
		
	}

	private void checkIntermediateGroupsAreValid(Group... intermediateGroups) {
		for (Group intermediateGroup : intermediateGroups) {
			//retrieve intermediate group and check it
			List actualIntermediateGroupList =
				retrieveGroupsListGivenGroupName(intermediateGroup.getName());
			assertEquals(1, actualIntermediateGroupList.size());

			// confirm intermediate group name is correct
			Map actualIntermediateGroupMap = (Map) actualIntermediateGroupList.get(0);
			String intermediate_group_name_actual = (String) actualIntermediateGroupMap
			.get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
			assertEquals(intermediateGroup.getName(), intermediate_group_name_actual);

			// confirm intermediate group's parent is the root group
			Long actualIdOfParentOfIntermediate = (Long) actualIntermediateGroupMap
			.get(PersistentGroup.COLUMN_NAME_PARENT_FK.toUpperCase());
			assertNotNull(actualIdOfParentOfIntermediate);
			Long actualIdOfRootGroup = getActualIdOfRootGroup();
			assertEquals(actualIdOfParentOfIntermediate, actualIdOfRootGroup);
		}
	}

	private Map checkRootGroupIsValid() {
		List actualRootGroupList = retrieveGroupsListGivenGroupName(ROOT_GROUP_NAME);
		assertEquals(1, actualRootGroupList.size());
	
		// confirm root group name is correct
		Map actualRootGroupMap = (Map) actualRootGroupList.get(0);
		String root_group_name_actual = (String) actualRootGroupMap
	            .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
		assertEquals(ROOT_GROUP_NAME, root_group_name_actual);
		
		// confirm root group has no parent
		Long actualRootParentGroupId = (Long) actualRootGroupMap
         		.get(PersistentGroup.COLUMN_NAME_PARENT_FK.toUpperCase());
		assertNull(actualRootParentGroupId);
		return actualRootGroupMap;
	}

	private void confirmNoMembersInAnyGroup() {
		List actualGroupMembersList = retrieveGroupsWithMemberKeys();
		assertEquals(0, actualGroupMembersList.size());
	}
	
	private Long getActualIdOfRootGroup() {
		List actualRootGroupList = retrieveGroupsListGivenGroupName(ROOT_GROUP_NAME);
		Map actualRootGroupMap = (Map) actualRootGroupList.get(0);
		return (Long) actualRootGroupMap.get("ID");
	}

	// // allGroupNames contains names of all groups: root, intrmdte, leaf
	// List<String> allGroupNames = new ArrayList<String>();
	// allGroupNames.add(this.rootGroup.getName());
	//
	// this.groupDao.save(this.rootGroup);
	// for (Group group : this.intermediateGroups) {
	// allGroupNames.add(group.getName());
	// this.groupDao.save(group);
	// }
	//
	// List actualList = retrieveGroupListFromDb();
	// assertEquals(INTERMEDIATE_GROUP_NAMES.length + 1, actualList.size());
	// for (int i = 0; i < actualList.size(); i++) {
	// Map actualGroupMap = (Map) actualList.get(i);
	// // * NOTE * the keys in the map are all in UPPERCASE!
	// String group_name_actual = (String) actualGroupMap
	// .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
	// assertTrue(allGroupNames.contains(group_name_actual));
	//
	// List parentGroupList =
	// getParentGroupGivenImmediateChildGroupName(group_name_actual);
	// if (group_name_actual != ROOT_GROUP_NAME) {
	// assertEquals(1, parentGroupList.size());
	// }
	// }
	// }

	// public void testSave_Members_Parent() {
	// verifyDataStoreGroupListIsEmpty();
	//
	// // allGroupNames contains names of all groups: root, intrmdte, leaf
	// List<String> allGroupNames = new ArrayList<String>();
	// allGroupNames.add(this.rootGroup.getName());
	//		
	// this.groupDao.save(this.rootGroup);
	// for (Group group : this.intermediateGroups) {
	// allGroupNames.add(group.getName());
	// this.groupDao.save(group);
	// }
	//
	// List actualList = retrieveGroupListFromDb();
	// assertEquals(INTERMEDIATE_GROUP_NAMES.length + 1, actualList.size());
	// for (int i = 0; i < actualList.size(); i++) {
	// Map actualGroupMap = (Map) actualList.get(i);
	// // * NOTE * the keys in the map are all in UPPERCASE!
	// String group_name_actual = (String) actualGroupMap
	// .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
	// assertTrue(allGroupNames.contains(group_name_actual));
	//			
	// List parentGroupList =
	// getParentGroupGivenImmediateChildGroupName(group_name_actual);
	// if (group_name_actual != ROOT_GROUP_NAME) {
	// assertEquals(1, parentGroupList.size());
	// }
	// }
	// }

	// TEST DELETE

	// TEST GETTING ALL STUDENTS UNDER PARTICULAR PATH...Not just for the parent
	// node
	// but also for all of the children nodes...this means that the user needs
	// to have
	// permission

	private void verifyDataStoreGroupListIsEmpty() {
		assertTrue(retrieveAllGroupsListFromDb().isEmpty());
	}

	/*
	 * SELECT * FROM groups
	 */
	private static final String RETRIEVE_ALL_GROUPS_SQL = "SELECT * FROM "
			+ PersistentGroup.DATA_STORE_NAME;

	/*
	 * SELECT * FROM groups G, groups_related_to_users GU WHERE G.id =
	 * GU.group_fk
	 */
	private static final String RETRIEVE_GROUP_LIST_MEMBERS_SQL = "SELECT * FROM "
			+ PersistentGroup.DATA_STORE_NAME
			+ ", "
			+ PersistentGroup.USERS_JOIN_TABLE_NAME
			+ " WHERE "
			+ PersistentGroup.DATA_STORE_NAME
			+ ".id = "
			+ PersistentGroup.USERS_JOIN_TABLE_NAME
			+ "."
			+ PersistentGroup.GROUPS_JOIN_COLUMN_NAME;

	/*
	 * SELECT * FROM groups g1 WHERE g1.id = SELECT g2.parent_fk FROM groups g2
	 * WHERE g2.name = :childname
	 */
	// private List getParentGroupGivenImmediateChildGroupName(String childName)
	// {
	// String sqlQuery = RETRIEVE_GROUP_LIST_SQL + " g1 WHERE g1.id = "
	// + "SELECT g2." + PersistentGroup.COLUMN_NAME_PARENT_FK
	// + " FROM " + PersistentGroup.DATA_STORE_NAME + " g2 WHERE g2."
	// + PersistentGroup.COLUMN_NAME_NAME + " = '" + childName + "'";
	//
	// return retrieveGroupListFromDb(sqlQuery);
	// }
	private List retrieveAllGroupsListFromDb() {
		return this.jdbcTemplate.queryForList(RETRIEVE_ALL_GROUPS_SQL,
				(Object[]) null);
	}
	
	/*
	 * SELECT * FROM groups [WHERE name = group_names[0] OR name = group_names[1] OR ...] 
	 */
	private List retrieveGroupsListGivenGroupName(String... group_names) {
		String sqlQuery = RETRIEVE_ALL_GROUPS_SQL;
		
		if (group_names.length != 0) {
			sqlQuery += " WHERE ";
			for (int i = 0; i < group_names.length; i++) {
				sqlQuery += " name = '" + group_names[i] + "' ";
				if (i < group_names.length - 1) {
					sqlQuery += " OR ";
				}
			}
		}
		return this.jdbcTemplate.queryForList(sqlQuery,
				(Object[]) null);
	}

	private List retrieveGroupsWithMemberKeys() {
		return this.jdbcTemplate.queryForList(RETRIEVE_GROUP_LIST_MEMBERS_SQL,
				(Object[]) null);
	}

	private List retrieveGroupListFromDb(String sqlQuery) {
		return this.jdbcTemplate.queryForList(sqlQuery, (Object[]) null);
	}

}
