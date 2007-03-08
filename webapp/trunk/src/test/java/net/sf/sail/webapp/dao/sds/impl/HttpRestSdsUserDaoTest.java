/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.dao.sds.impl;

import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.junit.AbstractSpringTests;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsUserDaoTest extends AbstractSpringTests {

    private static final String EXPECTED_FIRST_NAME = "first";

    private static final String EXPECTED_LAST_NAME = "last";

    private HttpRestSdsUserDao sdsUserDao;

    private SdsUser sdsUser;

    /**
     * @param sdsUserDao
     *            the sdsUserDao to set
     */
    public void setSdsUserDao(HttpRestSdsUserDao sdsUserDao) {
        this.sdsUserDao = sdsUserDao;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.sdsUser = this.sdsUserDao.createDataObject();
        this.sdsUser.setFirstName(EXPECTED_FIRST_NAME);
        this.sdsUser.setLastName(EXPECTED_LAST_NAME);
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.sdsUserDao = null;
        this.sdsUser = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#createDataObject()}.
     */
    public void testCreateDataObject() {
        assertTrue(this.sdsUserDao.createDataObject() instanceof SdsUser);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    @SuppressWarnings("unchecked")
    public void testSave_NewUser() {
        assertNull(this.sdsUser.getSdsObjectId());
        this.sdsUserDao.save(this.sdsUser);
        assertNotNull(this.sdsUser.getSdsObjectId());
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    public void testDelete() {
        try {
            this.sdsUserDao.delete(this.sdsUser);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
}