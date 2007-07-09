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
package net.sf.sail.webapp.dao.authentication.impl;

import java.util.List;

import net.sf.sail.webapp.dao.AbstractTransactionalDaoTests;
import net.sf.sail.webapp.domain.authentication.MutableAclSid;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclSid;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateAclSidDaoTest extends
        AbstractTransactionalDaoTests<HibernateAclSidDao, MutableAclSid> {

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        this.dao = (HibernateAclSidDao) this.applicationContext
                .getBean("aclSidDao");
        this.dataObject = (MutableAclSid) this.applicationContext
                .getBean("mutableAclSid");
    }

    /**
     * @see net.sf.sail.webapp.dao.AbstractTransactionalDaoTests#testSave()
     */
    @Override
    public void testSave() {
        try {
            this.dao.save(this.dataObject);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }

    /**
     * @see net.sf.sail.webapp.dao.AbstractTransactionalDaoTests#testDelete()
     */
    @Override
    public void testDelete() {
        // TODO Auto-generated method stub
    }

    /**
     * @see net.sf.sail.webapp.dao.AbstractTransactionalDaoTests#testGetById()
     */
    @Override
    public void testGetById() {
        // TODO Auto-generated method stub
    }

    /**
     * @see net.sf.sail.webapp.dao.AbstractTransactionalDaoTests#testGetList()
     */
    @Override
    public void testGetList() {
        // TODO Auto-generated method stub
    }

    /**
     * @see net.sf.sail.webapp.dao.AbstractTransactionalDaoTests#retrieveDataObjectListFromDb()
     */
    @Override
    protected List<?> retrieveDataObjectListFromDb() {
        return this.jdbcTemplate.queryForList("SELECT * FROM "
                + PersistentAclSid.DATA_STORE_NAME, (Object[]) null);
    }
}