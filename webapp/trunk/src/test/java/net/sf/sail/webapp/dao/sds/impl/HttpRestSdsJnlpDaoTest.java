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

import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsJnlpDaoTest extends AbstractSpringHttpUnitTests {

    private static final String EXPECTED_NAME = "name";

    private static final String EXPECTED_URL = "http://www.encorewiki.org/download/attachments/2114/plr-everything-jdic-snapshot-20070125-0811.jnlp";

    private HttpRestSdsJnlpDao sdsJnlpDao;

    private SdsJnlp sdsJnlp;

    /**
     * @param sdsJnlpDao
     *            the sdsJnlpDao to set
     */
    public void setSdsJnlpDao(HttpRestSdsJnlpDao sdsJnlpDao) {
        this.sdsJnlpDao = sdsJnlpDao;
    }

    /**
     * @param sdsJnlp
     *            the sdsJnlp to set
     */
    public void setSdsJnlp(SdsJnlp sdsJnlp) {
        this.sdsJnlp = sdsJnlp;
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onSetUp()
     */
    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.sdsJnlp.setName(EXPECTED_NAME);
        this.sdsJnlp.setUrl(EXPECTED_URL);
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onTearDown()
     */
    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.sdsJnlpDao = null;
        this.sdsJnlp = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsJnlpDao#save(net.sf.sail.webapp.domain.sds.SdsJnlp)}.
     */
    @SuppressWarnings("unchecked")
    public void testSave_NewJnlp() throws Exception {
        assertNull(this.sdsJnlp.getSdsObjectId());
        this.sdsJnlpDao.save(this.sdsJnlp);
        assertNotNull(this.sdsJnlp.getSdsObjectId());

        // retrieve newly created user using httpunit and compare with sdsUser
        // saved via DAO
        SdsJnlp actualSdsJnlp = this.getJnlpInSds(this.sdsJnlp.getSdsObjectId());
 
        assertEquals(this.sdsJnlp.getName(), actualSdsJnlp.getName());
        assertEquals(this.sdsJnlp.getSdsObjectId(), actualSdsJnlp
                .getSdsObjectId());
        assertEquals(this.sdsJnlp.getUrl(), actualSdsJnlp.getUrl());
        assertEquals(this.sdsJnlp, actualSdsJnlp);
    }

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsJnlpDao#save(net.sf.sail.webapp.domain.sds.SdsJnlp)}.
	 */
	public void testUpdateJnlp() throws Exception {
		Integer sdsJnlpId = this.createJnlpInSds();
		SdsJnlp actualSdsJnlp = this.getJnlpInSds(sdsJnlpId);
		assertEquals(actualSdsJnlp.getSdsObjectId(), sdsJnlpId);
		assertEquals(actualSdsJnlp.getName(), DEFAULT_NAME);
		assertEquals(actualSdsJnlp.getUrl(), DEFAULT_JNLP_URL);

		SdsJnlp sdsJnlpToUpdate = (SdsJnlp) this.applicationContext
				.getBean("sdsJnlp");
		sdsJnlpToUpdate.setSdsObjectId(sdsJnlpId);

		String updateName = "Updated";
		String updateURL = EXPECTED_URL;

		sdsJnlpToUpdate.setName(updateName);
		sdsJnlpToUpdate.setUrl(updateURL);
		this.sdsJnlpDao.save(sdsJnlpToUpdate);
		SdsJnlp updatedSdsJnlp = this.getJnlpInSds(sdsJnlpId);

		assertEquals(sdsJnlpId, updatedSdsJnlp.getSdsObjectId());
		assertFalse(actualSdsJnlp.equals(updatedSdsJnlp));
		assertEquals(updateName, updatedSdsJnlp.getName());
		assertEquals(updateURL, updatedSdsJnlp.getUrl());
	}

	   /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    public void testDelete() {
        try {
            this.sdsJnlpDao.delete(this.sdsJnlp);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#getList()}.
     */
    public void testGetList() {
        try {
            this.sdsJnlpDao.getList();
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#getById(java.lang.Long)}.
     */
    public void testGetById() {
        try {
            this.sdsJnlpDao.getById(new Long(3));
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
	
}