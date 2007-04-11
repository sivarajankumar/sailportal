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
package net.sf.sail.webapp.dao.offering.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.hibernate.Session;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Test for HibernateOfferingDao
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateOfferingDaoTest extends AbstractTransactionalDbTests {

    private static final Integer SDS_ID = new Integer(7);

    private static final String DEFAULT_NAME = "Airbags";

    private static final String DEFAULT_URL = "http://mrpotatoiscoolerthanwoody.com";

    private static final SdsCurnit DEFAULT_SDS_CURNIT = new SdsCurnit();

    private static final SdsJnlp DEFAULT_SDS_JNLP = new SdsJnlp();

    private HibernateOfferingDao offeringDao;

    private SdsOffering sdsOffering;

    private Offering defaultOffering;

    /**
     * @param offeringDao
     *            the offeringDao to set
     */
    public void setOfferingDao(HibernateOfferingDao offeringDao) {
        this.offeringDao = offeringDao;
    }

    /**
     * @param sdsOffering
     *            the sdsOffering to set
     */
    public void setSdsOffering(SdsOffering sdsOffering) {
        this.sdsOffering = sdsOffering;
    }

    /**
     * @param defaultOffering
     *            the defaultOffering to set
     */
    public void setDefaultOffering(Offering defaultOffering) {
        this.defaultOffering = defaultOffering;
    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownAfterTransaction()
     */
    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        this.defaultOffering = null;
        this.sdsOffering = null;
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        DEFAULT_SDS_CURNIT.setSdsObjectId(SDS_ID);
        DEFAULT_SDS_CURNIT.setName(DEFAULT_NAME);
        DEFAULT_SDS_CURNIT.setUrl(DEFAULT_URL);

        DEFAULT_SDS_JNLP.setSdsObjectId(SDS_ID);
        DEFAULT_SDS_JNLP.setName(DEFAULT_NAME);
        DEFAULT_SDS_JNLP.setUrl(DEFAULT_URL);

        this.sdsOffering.setSdsObjectId(SDS_ID);
        this.sdsOffering.setName(DEFAULT_NAME);

        this.defaultOffering.setSdsOffering(this.sdsOffering);
    }

    /**
     * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        Session session = this.sessionFactory.getCurrentSession();
        session.save(DEFAULT_SDS_CURNIT); // save sds curnit
        session.save(DEFAULT_SDS_JNLP); // save sds jnlp

        this.sdsOffering.setCurnit(DEFAULT_SDS_CURNIT);
        this.sdsOffering.setJnlp(DEFAULT_SDS_JNLP);
    }

    public void testSave_NonExistentCurnit() {
        this.sdsOffering.setJnlp(DEFAULT_SDS_JNLP);

        SdsCurnit nonExistentSdsCurnit = (SdsCurnit) this.applicationContext
                .getBean("sdsCurnit");
        nonExistentSdsCurnit.setName(DEFAULT_NAME);
        nonExistentSdsCurnit.setSdsObjectId(SDS_ID);
        nonExistentSdsCurnit.setUrl(DEFAULT_URL);
        this.sdsOffering.setCurnit(nonExistentSdsCurnit);

        this.defaultOffering.setSdsOffering(sdsOffering);
        try {
            this.offeringDao.save(this.defaultOffering);
            fail("DataIntegrityViolationException expected");
        } catch (DataIntegrityViolationException expected) {
        }
    }

    public void testSave_NonExistentJnlp() {
        this.sdsOffering.setCurnit(DEFAULT_SDS_CURNIT);

        SdsJnlp nonExistentSdsJnlp = (SdsJnlp) this.applicationContext
                .getBean("sdsJnlp");
        nonExistentSdsJnlp.setName(DEFAULT_NAME);
        nonExistentSdsJnlp.setSdsObjectId(SDS_ID);
        nonExistentSdsJnlp.setUrl(DEFAULT_URL);
        this.sdsOffering.setJnlp(nonExistentSdsJnlp);

        this.defaultOffering.setSdsOffering(sdsOffering);
        try {
            this.offeringDao.save(this.defaultOffering);
            fail("DataIntegrityViolationException expected");
        } catch (DataIntegrityViolationException expected) {
        }
    }

    public void testSave() {
        verifyDataStoreIsEmpty();

        // save the default offering object using dao
        this.offeringDao.save(this.defaultOffering);

        // verify data store contains saved data using direct jdbc retrieval
        // (not using dao)
        List actualList = retrieveOfferingListFromDb();
        assertEquals(1, actualList.size());

        Map actualOfferingMap = (Map) actualList.get(0);
        assertEquals(SDS_ID, actualOfferingMap
                .get(SdsOffering.COLUMN_NAME_OFFERING_ID.toUpperCase()));
        assertEquals(DEFAULT_NAME, actualOfferingMap
                .get(SdsOffering.COLUMN_NAME_OFFERING_NAME.toUpperCase()));
    }

    public void testDelete() {
        verifyDataStoreIsEmpty();
        // save the default offering object using dao
        this.offeringDao.save(this.defaultOffering);
        List actualList = retrieveOfferingListFromDb();
        assertEquals(1, actualList.size());

        this.offeringDao.delete(this.defaultOffering);
        this.toilet.flush();
        verifyDataStoreIsEmpty();
    }

    private void verifyDataStoreIsEmpty() {
        assertTrue(retrieveOfferingListFromDb().isEmpty());
    }

    /*
     * SELECT * FROM offerings, sds_offerings WHERE offerings.sds_offering_fk =
     * sds_offerings.id
     */
    private static final String RETRIEVE_OFFERING_LIST_SQL = "SELECT * FROM "
            + OfferingImpl.DATA_STORE_NAME + ", " + SdsOffering.DATA_STORE_NAME
            + " WHERE " + OfferingImpl.DATA_STORE_NAME + "."
            + OfferingImpl.COLUMN_NAME_SDS_OFFERING_FK + " = "
            + SdsOffering.DATA_STORE_NAME + ".id";

    private List retrieveOfferingListFromDb() {
        return this.jdbcTemplate.queryForList(RETRIEVE_OFFERING_LIST_SQL,
                (Object[]) null);
    }
}