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
package org.telscenter.sail.webapp.dao.offering.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;

import org.telscenter.sail.webapp.domain.Offering;
import org.telscenter.sail.webapp.domain.impl.OfferingImpl;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test for HibernateOfferingDao
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateOfferingDaoTest extends AbstractTransactionalDbTests {

	private static final Integer SDS_OFFERING_ID = new Integer(7);
	
	private static final Integer SDS_CURNIT_ID = new Integer(10);

	private static final Integer SDS_JNLP_ID = new Integer(12);
	
	private static final String SDS_OFFERING_NAME = "Airbags run in Virginia";
	
	private static final String SDS_CURNIT_NAME = "Airbags";
	
	private static final String SDS_CURNIT_URL = "http://mrpotatoiscoolerthanwoody.com";

	private static final String SDS_JNLP_NAME = "Full snapshot";

	private static final String SDS_JNLP_URL = "http://woodyiscoolerthanmrpotato.com";

	private HibernateOfferingDao offeringDao;
	
	private SdsCurnit sdsCurnit;
	
	private SdsJnlp sdsJnlp;

	private SdsOffering sdsOffering;
	
	private Offering defaultOffering;
	
	/**
	 * @param offeringDao
	 *    the offeringDao to set
	 */
	public void setOfferingDao(HibernateOfferingDao offeringDao) {
		this.offeringDao = offeringDao;
	}
	
	/**
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
	 */
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		this.sdsOffering = (SdsOffering) this.applicationContext.getBean("sdsOffering");
		this.sdsOffering.setSdsObjectId(SDS_OFFERING_ID);
		this.sdsOffering.setName(SDS_OFFERING_NAME);
		
		this.sdsCurnit = new SdsCurnit();
		this.sdsCurnit.setSdsObjectId(SDS_CURNIT_ID);
		this.sdsCurnit.setName(SDS_CURNIT_NAME);
		this.sdsCurnit.setUrl(SDS_CURNIT_URL);

		this.sdsJnlp = new SdsJnlp();
		this.sdsJnlp.setSdsObjectId(SDS_JNLP_ID);
		this.sdsJnlp.setName(SDS_JNLP_NAME);
		this.sdsJnlp.setUrl(SDS_JNLP_URL);

		this.sdsOffering.setCurnit(this.sdsCurnit);
		this.sdsOffering.setJnlp(this.sdsJnlp);

		this.defaultOffering = (Offering) this.applicationContext.getBean("offering");
		this.defaultOffering.setSdsOffering(this.sdsOffering);
	}
	
	public void testSave() {
		// TODO: HIROKI complete this method
		verifyDataStoreIsEmpty();
		
		// save the default offering object using dao
		this.offeringDao.save(this.defaultOffering);
		
		// verify data store contains saved data using direct jdbc retrieval
		// (not using dao)
		List actualList = retrieveOfferingListFromDb();
		assertEquals(1, actualList.size());
		
		Map actualOfferingMap = (Map) actualList.get(0);
		assertEquals(SDS_OFFERING_ID, actualOfferingMap
				.get(SdsOffering.COLUMN_NAME_OFFERING_ID.toUpperCase()));
		assertEquals(SDS_OFFERING_NAME, actualOfferingMap
				.get(SdsOffering.COLUMN_NAME_OFFERING_NAME.toUpperCase()));

	}
	
	public void testDelete() {
		// TODO: HIROKI complete this method
		verifyDataStoreIsEmpty();		
		this.offeringDao.delete(this.defaultOffering);
	}
	
	private void verifyDataStoreIsEmpty() {
		assertTrue(retrieveOfferingListFromDb().isEmpty());
	}
	
	private List retrieveOfferingListFromDb() {
		/*
		 SELECT * FROM offerings, sds_offerings
		 WHERE offerings.sds_offering_fk = sds_offerings.id
		 */		
		String offerings = OfferingImpl.DATA_STORE_NAME;
		String sds_offerings = SdsOffering.DATA_STORE_NAME;
		
		return this.jdbcTemplate.queryForList(
				"SELECT * FROM " +
				offerings + ", " +
				sds_offerings + " WHERE " +
				offerings + "." + OfferingImpl.COLUMN_NAME_SDS_OFFERING_FK + " = " +
				sds_offerings + ".id"
				,(Object[]) null);
	}
}
