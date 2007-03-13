/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.dao.user.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDaoTest extends AbstractTransactionalDbTests {

    private static final String USERNAME = "user name";

    private static final String PASSWORD = "password";

    private static final String FIRST_NAME = USERNAME;

    private static final String LAST_NAME = USERNAME;

    private static final Integer SDS_USER_ID = new Integer(42);

    private HibernateUserDao userDao;

    private MutableUserDetails userDetails;

    private SdsUser sdsUser;

    private User defaultUser;

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(HibernateUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        this.userDetails = (MutableUserDetails) this.applicationContext
                .getBean("mutableUserDetails");
        this.sdsUser = (SdsUser) this.applicationContext.getBean("sdsUser");
        this.defaultUser = (User) this.applicationContext.getBean("user");

        this.defaultUser.setUserDetails(this.userDetails);
        this.defaultUser.setSdsUser(this.sdsUser);
        this.userDetails.setUsername(USERNAME);
        this.userDetails.setPassword(PASSWORD);
        this.sdsUser.setFirstName(FIRST_NAME);
        this.sdsUser.setLastName(LAST_NAME);
        this.sdsUser.setSdsObjectId(SDS_USER_ID);
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
     */
    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        this.userDetails = null;
        this.sdsUser = null;
        this.defaultUser = null;
    }

    public void testSave() {
        verifyDataStoreIsEmpty();

        // save the default user object using dao
        this.userDao.save(this.defaultUser);

        // verify data store contains saved data using direct jdbc retrieval
        // (not using dao)
        List actualList = retrieveUserListFromDb();
        assertEquals(1, actualList.size());

        Map actualUserMap = (Map) actualList.get(0);
        assertEquals(USERNAME, actualUserMap
                .get(PersistentUserDetails.COLUMN_NAME_USERNAME.toUpperCase()));
        assertEquals(PASSWORD, actualUserMap
                .get(PersistentUserDetails.COLUMN_NAME_PASSWORD.toUpperCase()));
        assertEquals(FIRST_NAME, actualUserMap
                .get(SdsUser.COLUMN_NAME_FIRST_NAME.toUpperCase()));
        assertEquals(LAST_NAME, actualUserMap.get(SdsUser.COLUMN_NAME_LAST_NAME
                .toUpperCase()));
        assertEquals(SDS_USER_ID, actualUserMap.get(SdsUser.COLUMN_NAME_USER_ID
                .toUpperCase()));

        User emptyUser = (User) this.applicationContext.getBean("user");
        try {
            this.userDao.save(emptyUser);
            fail("expected DataIntegrityViolationException");
        } catch (DataIntegrityViolationException expected) {
        }

        User partiallyEmptyUser = (User) this.applicationContext
                .getBean("user");
        partiallyEmptyUser.setUserDetails(this.userDetails);
        try {
            this.userDao.save(partiallyEmptyUser);
            fail("expected DataIntegrityViolationException");
        } catch (DataIntegrityViolationException expected) {
        }

        partiallyEmptyUser = (User) this.applicationContext.getBean("user");
        partiallyEmptyUser.setSdsUser(this.sdsUser);
        try {
            this.userDao.save(partiallyEmptyUser);
            fail("expected DataIntegrityViolationException");
        } catch (DataIntegrityViolationException expected) {
        }
    }

    public void testDelete() {
        verifyDataStoreIsEmpty();

        // save and delete the default granted authority object using dao
        this.userDao.save(this.defaultUser);
        this.userDao.delete(this.defaultUser);

        // * NOTE * must flush to test delete
        // see http://forum.springframework.org/showthread.php?t=18263 for
        // explanation
        this.toilet.flush();

        verifyDataStoreIsEmpty();
    }

    private void verifyDataStoreIsEmpty() {
        assertTrue(retrieveUserListFromDb().isEmpty());
    }

    private List retrieveUserListFromDb() {
        return this.jdbcTemplate.queryForList("select * from "
                + UserImpl.DATA_STORE_NAME + ", "
                + PersistentUserDetails.DATA_STORE_NAME + ", "
                + SdsUser.DATA_STORE_NAME + " where "
                + UserImpl.DATA_STORE_NAME + "."
                + UserImpl.COLUMN_NAME_USER_DETAILS_FK + " = "
                + PersistentUserDetails.DATA_STORE_NAME + ".id and "
                + UserImpl.DATA_STORE_NAME + "."
                + UserImpl.COLUMN_NAME_SDS_USER_FK + " = "
                + SdsUser.DATA_STORE_NAME + ".id;", (Object[]) null);
    }
}