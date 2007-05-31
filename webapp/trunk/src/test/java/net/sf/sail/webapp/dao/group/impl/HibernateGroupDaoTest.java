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

import org.hibernate.Session;

import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.Path;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.group.impl.PersistentPath;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test class for HibernateGroupDao
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class HibernateGroupDaoTest extends AbstractTransactionalDbTests {
	
	private static final String DEFAULT_PATH_NAME = "Period 1";

	private HibernateGroupDao groupDao;
	
	private Group defaultGroup;
	
	private Path defaultPath;
	
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
	 * @param defaultPath the defaultPath to set
	 */
	public void setDefaultPath(Path defaultPath) {
		this.defaultPath = defaultPath;
	}

	/**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
    	super.onSetUpBeforeTransaction();

    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        // a Path needs to exist already before a group can be created
        Session session = this.sessionFactory.getCurrentSession();
        this.defaultPath.setName(DEFAULT_PATH_NAME);
        session.save(this.defaultPath);   // save path
        this.defaultGroup.setPath(this.defaultPath);
    }

	public void testSave_NoMembers() {
		verifyDataStoreGroupListIsEmpty();

		this.groupDao.save(this.defaultGroup);
		List actualList = retrieveGroupListFromDb();
		assertEquals(1, actualList.size());
		for (int i = 0; i < actualList.size(); i++) {
			Map actualGroupMap = (Map) actualList.get(i);
			// * NOTE * the keys in the map are all in UPPERCASE!
			System.out.println("Map:" + actualGroupMap.toString());
			String path_name_actual = (String) actualGroupMap
			        .get(PersistentPath.COLUMN_NAME_NAME.toUpperCase());
			assertEquals(DEFAULT_PATH_NAME, path_name_actual);

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
	 * SELECT * FROM groups, paths WHERE
	 * groups.path_fk = paths.id
	 */
	private static final String RETRIEVE_GROUP_LIST_SQL = "SELECT * FROM "
		+ PersistentGroup.DATA_STORE_NAME + ", "
		+ PersistentPath.DATA_STORE_NAME + " WHERE "
		+ PersistentGroup.DATA_STORE_NAME + "."
		+ PersistentGroup.COLUMN_NAME_PATH_FK + " = "
		+ PersistentPath.DATA_STORE_NAME + ".id";
	
	private List retrieveGroupListFromDb() {
		return this.jdbcTemplate.queryForList(RETRIEVE_GROUP_LIST_SQL, 
				(Object[]) null);
	}

}
