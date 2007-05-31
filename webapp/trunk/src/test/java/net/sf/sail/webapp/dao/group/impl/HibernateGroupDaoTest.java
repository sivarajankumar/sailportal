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
	
	private static final String DEFAULT_GROUP_NAME = "My Class";

	private HibernateGroupDao groupDao;
	
	private Group defaultGroup;
	
	/**
	 * @param groupDao 
	 *             the groupDao to set
	 */
	public void setGroupDao(HibernateGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/**
	 * @param defaultGroup 
	 *              the defaultGroup to set
	 */
	public void setDefaultGroup(Group defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	/**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
    	super.onSetUpBeforeTransaction();
    	this.defaultGroup.setName(DEFAULT_GROUP_NAME);
    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
    }

	public void testSave_NoMembers_NoParent() {
		verifyDataStoreGroupListIsEmpty();

		this.groupDao.save(this.defaultGroup);
		List actualList = retrieveGroupListFromDb();
		assertEquals(1, actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			Map actualGroupMap = (Map) actualList.get(i);
			// * NOTE * the keys in the map are all in UPPERCASE!
			System.out.println("Map:" + actualGroupMap.toString());
			String group_name_actual = (String) actualGroupMap
			        .get(PersistentGroup.COLUMN_NAME_NAME.toUpperCase());
			assertEquals(DEFAULT_GROUP_NAME, group_name_actual);

		}
	}
	
//	public void testSave_NoMembers_Parent() {
//		verifyDataStoreGroupListIsEmpty();
//		
//		
//	}

	
	
	// TEST DELETE 
	
	// TEST GETTING ALL STUDENTS UNDER PARTICULAR PATH...Not just for the parent node
	// but also for all of the children nodes...this means that the user needs to have
	// permission
	
	private void verifyDataStoreGroupListIsEmpty() {
		assertTrue(retrieveGroupListFromDb().isEmpty());
	}
	
	/*
	 * SELECT * FROM groups WHERE groups.name = 'DEFAULT_GROUP_NAME'
	 */
	private static final String RETRIEVE_GROUP_LIST_SQL = "SELECT * FROM "
		+ PersistentGroup.DATA_STORE_NAME + " WHERE "
		+ PersistentGroup.DATA_STORE_NAME + "."
		+ PersistentGroup.COLUMN_NAME_NAME + "= '" + DEFAULT_GROUP_NAME + "'";
		
	private List retrieveGroupListFromDb() {
		return this.jdbcTemplate.queryForList(RETRIEVE_GROUP_LIST_SQL, 
				(Object[]) null);
	}

}
