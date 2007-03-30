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
package org.telscenter.sail.webapp.dao.workgroup.impl;

import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;

import org.telscenter.sail.webapp.domain.Workgroup;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test for HibernateWorkgroupDao
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateWorkgroupDaoTest extends AbstractTransactionalDbTests {

    private static final Integer SDS_WORKGROUP_ID = new Integer(42);

	private static final String SDS_WORKGROUP_NAME = "the heros";

	private static final SdsOffering DEFAULT_SDS_OFFERING = new SdsOffering();
	
	private HibernateWorkgroupDao workgroupDao;
	
	private SdsWorkgroup sdsWorkgroup;
	
	private Workgroup defaultWorkgroup;

	/**
	 * @param workgroupDao
	 *    the workgroupDao to set
	 */
	public void setWorkgroupDao(HibernateWorkgroupDao workgroupDao) {
		this.workgroupDao = workgroupDao;
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
	 */
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		// TODO: HIROKI initialize necessary domain objects with necessary fields
		super.onSetUpBeforeTransaction();
		this.sdsWorkgroup = (SdsWorkgroup) this.applicationContext.getBean("sdsWorkgroup");
		this.defaultWorkgroup = (Workgroup) this.applicationContext.getBean("workgroup");
		
		this.defaultWorkgroup.setSdsWorkgroup(this.sdsWorkgroup);
		this.sdsWorkgroup.setSdsObjectId(SDS_WORKGROUP_ID);
		this.sdsWorkgroup.setName(SDS_WORKGROUP_NAME);
		this.sdsWorkgroup.setSdsOffering(DEFAULT_SDS_OFFERING);

	}
	
	public void testSave() {
		//this.workgroupDao.save(this.defaultWorkgroup);
	}
	
	public void testDelete() {
		this.workgroupDao.delete(this.defaultWorkgroup);
	}
}
