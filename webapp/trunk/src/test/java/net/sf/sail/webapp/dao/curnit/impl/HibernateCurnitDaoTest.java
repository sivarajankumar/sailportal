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
package net.sf.sail.webapp.dao.curnit.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateCurnitDaoTest extends AbstractTransactionalDbTests {

    private static final Integer SDS_ID = new Integer(7);

    private static final String DEFAULT_NAME = "Airbags";

    private static final String DEFAULT_URL = "http://mrpotatoiscoolerthanwoody.com";

    private HibernateCurnitDao curnitDao;

    private SdsCurnit sdsCurnit;

    private Curnit defaultCurnit;

    public void setCurnitDao(HibernateCurnitDao curnitDao) {
        this.curnitDao = curnitDao;
    }

    public void setDefaultCurnit(Curnit defaultCurnit) {
        this.defaultCurnit = defaultCurnit;
    }

    public void setSdsCurnit(SdsCurnit sdsCurnit) {
        this.sdsCurnit = sdsCurnit;
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        this.sdsCurnit.setSdsObjectId(SDS_ID);
        this.sdsCurnit.setName(DEFAULT_NAME);
        this.sdsCurnit.setUrl(DEFAULT_URL);

        this.defaultCurnit.setSdsCurnit(this.sdsCurnit);
    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownAfterTransaction()
     */
    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        this.curnitDao = null;
        this.sdsCurnit = null;
        this.defaultCurnit = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.impl.AbstractHibernateDao#delete(java.lang.Object)}.
     */
    public void testDelete() {
        verifyDataStoreIsEmpty();
        this.curnitDao.save(this.defaultCurnit);
        List actualList = retrieveCurnitListFromDb();
        assertEquals(1, actualList.size());

        this.curnitDao.delete(this.defaultCurnit);
        this.toilet.flush();
        verifyDataStoreIsEmpty();
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.impl.AbstractHibernateDao#save(java.lang.Object)}.
     */
    public void testSave() {
        verifyDataStoreIsEmpty();

        this.curnitDao.save(this.defaultCurnit);

        // verify data store contains saved data using direct jdbc retrieval
        // (not using dao)
        List actualList = retrieveCurnitListFromDb();
        assertEquals(1, actualList.size());

        Map actualCurnitMap = (Map) actualList.get(0);
        assertEquals(SDS_ID, actualCurnitMap
                .get(SdsCurnit.COLUMN_NAME_CURNIT_ID.toUpperCase()));
        assertEquals(DEFAULT_NAME, actualCurnitMap
                .get(SdsCurnit.COLUMN_NAME_CURNIT_NAME.toUpperCase()));
        assertEquals(DEFAULT_URL, actualCurnitMap
                .get(SdsCurnit.COLUMN_NAME_CURNIT_URL.toUpperCase()));
    }

    private void verifyDataStoreIsEmpty() {
        assertTrue(retrieveCurnitListFromDb().isEmpty());
    }

    /*
     * SELECT * FROM curnits, sds_curnits WHERE curnits.sds_curnit_fk =
     * sds_curnits.id
     */
    private static final String RETRIEVE_CURNIT_LIST_SQL = "SELECT * FROM "
            + CurnitImpl.DATA_STORE_NAME + ", " + SdsCurnit.DATA_STORE_NAME
            + " WHERE " + CurnitImpl.DATA_STORE_NAME + "."
            + CurnitImpl.COLUMN_NAME_SDS_CURNIT_FK + " = "
            + SdsCurnit.DATA_STORE_NAME + ".id";

    private List retrieveCurnitListFromDb() {
        return this.jdbcTemplate.queryForList(RETRIEVE_CURNIT_LIST_SQL,
                (Object[]) null);
    }
    
    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getList()}.
     */
    public void testGetList() throws Exception {
        verifyDataStoreIsEmpty();
        List<Curnit> expectedEmptyList = this.curnitDao.getList();
        assertTrue(expectedEmptyList.isEmpty());
        
        this.curnitDao.save(this.defaultCurnit);
        List expectedList = retrieveCurnitListFromDb();
        assertEquals(1, expectedList.size());

        List<Curnit> actualList = this.curnitDao.getList();
        assertEquals(1, actualList.size());
        assertEquals(this.defaultCurnit, actualList.get(0));
    }
    
    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getById(java.lang.Long)}.
     */ 
    public void testGetById() throws Exception {
    	verifyDataStoreIsEmpty();
    	Curnit expectedNullCurnit = this.curnitDao.getById(new Long(3));
    	assertNull(expectedNullCurnit);
    	
    	this.curnitDao.save(this.defaultCurnit);
    	List<Curnit> actualList = this.curnitDao.getList();
    	Curnit actualCurnit = (Curnit) actualList.get(0);
    	
    	Curnit retrievedByIdCurnit = (Curnit) this.curnitDao.getById(actualCurnit.getId());
    	assertEquals(actualCurnit, retrievedByIdCurnit);
    }
}