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
package net.sf.sail.webapp.service.offering.impl;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.offering.OfferingDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.impl.JnlpImpl;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.OfferingParameters;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingServiceImplTest extends TestCase {
	
	private static final String CURNIT_NAME = "name";
	
	private static final String CURNIT_URL = "url";
	
	private static final String JNLP_NAME = "jname";
	private static final String JNLP_URL = "jurl";

	private SdsOfferingDao mockSdsOfferingDao;

	private OfferingDao<Offering> mockOfferingDao;

	private OfferingServiceImpl offeringServiceImpl;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		this.offeringServiceImpl = new OfferingServiceImpl();

		this.mockSdsOfferingDao = EasyMock.createMock(SdsOfferingDao.class);
		this.offeringServiceImpl.setSdsOfferingDao(this.mockSdsOfferingDao);

		this.mockOfferingDao = EasyMock.createMock(OfferingDao.class);
		this.offeringServiceImpl.setOfferingDao(this.mockOfferingDao);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.offeringServiceImpl = null;
		this.mockSdsOfferingDao = null;
		this.mockOfferingDao = null;
	}

	public void testGetOfferingList() throws Exception {
		List<Offering> expectedList = new LinkedList<Offering>();
		expectedList.add(new OfferingImpl());

		EasyMock.expect(this.mockOfferingDao.getList()).andReturn(expectedList);
		EasyMock.replay(this.mockOfferingDao);
		assertEquals(expectedList, offeringServiceImpl.getOfferingList());
		EasyMock.verify(this.mockOfferingDao);
	}

	public void testCreateOffering() throws Exception {
		OfferingParameters offeringParameters = new OfferingParameters();

		SdsCurnit sdsCurnit = new SdsCurnit();
		sdsCurnit.setName(CURNIT_NAME);
		sdsCurnit.setUrl(CURNIT_URL);
		Curnit curnit = new CurnitImpl();
		curnit.setSdsCurnit(sdsCurnit);
		offeringParameters.setCurnit(curnit);

		SdsJnlp sdsJnlp = new SdsJnlp();
		sdsJnlp.setName(JNLP_NAME);
		sdsJnlp.setUrl(JNLP_URL);
		Jnlp jnlp = new JnlpImpl();
		jnlp.setSdsJnlp(sdsJnlp);
		offeringParameters.setJnlp(jnlp);

		offeringParameters.setName(CURNIT_NAME);

		Offering offering = offeringServiceImpl
				.createOffering(offeringParameters);

		assertEquals(CURNIT_NAME, offering.getSdsOffering().getSdsCurnit().getName());
		assertEquals(CURNIT_URL, offering.getSdsOffering().getSdsCurnit().getUrl());
		assertEquals(JNLP_NAME, offering.getSdsOffering().getSdsJnlp().getName());
		assertEquals(JNLP_URL, offering.getSdsOffering().getSdsJnlp().getUrl());
		assertEquals(CURNIT_NAME, offering.getSdsOffering().getName());
	}
}