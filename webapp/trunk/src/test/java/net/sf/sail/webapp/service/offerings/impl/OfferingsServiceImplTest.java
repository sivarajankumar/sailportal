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
package net.sf.sail.webapp.service.offerings.impl;

import static org.easymock.EasyMock.createMock;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id:OfferingsServiceImplTest.java 159 2007-03-02 22:42:21Z cynick $
 */
public class OfferingsServiceImplTest extends TestCase {

	private SdsOfferingDao mockSdsOfferingDao;

	private SdsOffering sdsOffering;

	private OfferingsServiceImpl offeringServiceImpl;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		this.mockSdsOfferingDao = createMock(SdsOfferingDao.class);
		this.offeringServiceImpl = new OfferingsServiceImpl();
		this.offeringServiceImpl.setSdsOfferingDao(this.mockSdsOfferingDao);

		this.sdsOffering = new SdsOffering();
		sdsOffering.setSdsObjectId(3);
		SdsCurnit curnit = new SdsCurnit();
		curnit.setSdsObjectId(1);
		sdsOffering.setCurnit(curnit);

		SdsJnlp jnlp = new SdsJnlp();
		jnlp.setSdsObjectId(2);
		sdsOffering.setJnlp(jnlp);
		sdsOffering.setName("test");
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.offeringServiceImpl = null;
		this.mockSdsOfferingDao = null;
		this.sdsOffering = null;
	}

	public void testGetOfferingsList() throws Exception {
		Set<SdsOffering> expectedSdsOfferingSet = new HashSet<SdsOffering>();
		expectedSdsOfferingSet.add(this.sdsOffering);

		EasyMock.expect(mockSdsOfferingDao.getList()).andReturn(
				expectedSdsOfferingSet);
		EasyMock.replay(mockSdsOfferingDao);
		assertEquals(expectedSdsOfferingSet, offeringServiceImpl
				.getOfferingsList());
		EasyMock.verify(mockSdsOfferingDao);
	}

	// tests that the command is delegated to the DAO and that the DAO is called
	// once
	public void testCreateOffering() throws Exception {
		mockSdsOfferingDao.save(this.sdsOffering);
		EasyMock.expectLastCall();
		EasyMock.replay(mockSdsOfferingDao);

		offeringServiceImpl.createOffering(this.sdsOffering);

		EasyMock.verify(mockSdsOfferingDao);
	}
}