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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;

import org.easymock.EasyMock;
import org.springframework.context.support.StaticApplicationContext;

/**
 * @author Laurel Williams
 *
 * @version $Id$
 */
public class OfferingsServiceImplTest extends TestCase {
	
	private SdsCommand<SdsOffering, List<SdsOffering>> mockSdsOfferingDao;
	
	private StaticApplicationContext applicationContext;
	
	private List<SdsOffering> expectedSdsOfferingList;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		mockSdsOfferingDao = createMock(SdsCommand.class);
		this.applicationContext = new StaticApplicationContext();
		applicationContext.registerPrototype("sdsOffering", SdsOffering.class);
		expectedSdsOfferingList = new ArrayList<SdsOffering>();
		SdsOffering offering = new SdsOffering();
		offering.setCurnitId(1);
		offering.setJnlpId(2);
		offering.setName("test");
		offering.setSdsObjectId(3);
		expectedSdsOfferingList.add(offering);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		mockSdsOfferingDao = null;
		applicationContext = null;
	}
	
	@SuppressWarnings("unchecked")
	public void testGetOfferingsList() throws Exception {
		SdsOffering sdsOffering = (SdsOffering) this.applicationContext.getBean("sdsOffering");
		EasyMock.expect(mockSdsOfferingDao.generateRequest(sdsOffering))
				.andReturn(
						new HttpGetRequest(Collections.EMPTY_MAP,
								Collections.EMPTY_MAP, null, 0));
		EasyMock.expect(mockSdsOfferingDao.execute(this.applicationContext, sdsOffering)).andReturn(expectedSdsOfferingList);
		EasyMock.replay(mockSdsOfferingDao);
		OfferingsServiceImpl offeringServiceImpl = new OfferingsServiceImpl();
		offeringServiceImpl.setSdsOfferingDao(mockSdsOfferingDao);
		List<SdsOffering> actualOfferingList = offeringServiceImpl.getOfferingsList(this.applicationContext);
		assertEquals(expectedSdsOfferingList, actualOfferingList);
	}

}
