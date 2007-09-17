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

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;
import org.xml.sax.SAXException;

import com.meterware.httpunit.WebResponse;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsOfferingDaoTest extends AbstractSpringHttpUnitTests {

	private static final String EXPECTED_NAME = "silly";

	private HttpRestSdsOfferingDao sdsOfferingDao;

	private SdsOffering sdsOffering;

	private SdsCurnit sdsCurnit;
	private SdsJnlp sdsJnlp;

	public void setSdsOfferingDao(HttpRestSdsOfferingDao sdsOfferingDao) {
		this.sdsOfferingDao = sdsOfferingDao;
	}

	/**
	 * @param sdsOffering
	 *            the sdsOffering to set
	 */
	public void setSdsOffering(SdsOffering sdsOffering) {
		this.sdsOffering = sdsOffering;
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onSetUp()
	 */
	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onTearDown()
	 */
	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		this.sdsOfferingDao = null;
		this.sdsOffering = null;
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#getList()}.
	 */
	@SuppressWarnings("unchecked")
	public void testGetList() throws Exception {
		// To test, we will retrieve the offering list through 2 methods, via
		// DAO and httpunit. Compare the lists and make sure that they're
		// equivalent.
		// *Note* there is a small chance that between the 2 retrievals, a new
		// offering may be inserted into the SDS and cause this test to break.
		List<SdsOffering> actualSet = this.sdsOfferingDao.getList();

		WebResponse webResponse = makeHttpRestGetRequest("/offering");
		assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

		Document doc = createDocumentFromResponse(webResponse);

		List<Element> nodeList = XPath.newInstance("/offerings/offering/id")
				.selectNodes(doc);
		assertEquals(nodeList.size(), actualSet.size());
		List<Integer> offeringIdList = new ArrayList<Integer>(nodeList.size());
		for (Element element : nodeList) {
			offeringIdList.add(new Integer(element.getText()));
		}

		assertEquals(offeringIdList.size(), actualSet.size());
		for (SdsOffering offering : actualSet) {
			offeringIdList.contains(offering.getSdsObjectId());
		}
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#save(net.sf.sail.webapp.domain.sds.SdsOffering)}.
	 */
	public void testUpdateOffering() throws Exception {
		this.sdsOffering = createAndSaveOffering();
		Serializable constantSdsOfferingId = this.sdsOffering.getSdsObjectId();

		SdsOffering actualSdsOffering = this
				.getOfferingAlternativeMethod(constantSdsOfferingId);
		assertEquals(actualSdsOffering.getSdsObjectId(), constantSdsOfferingId);
		assertEquals(actualSdsOffering.getName(), EXPECTED_NAME);
		assertEquals(actualSdsOffering.getSdsCurnit().getSdsObjectId(),
				this.sdsCurnit.getSdsObjectId());
		assertEquals(actualSdsOffering.getSdsJnlp().getSdsObjectId(),
				this.sdsJnlp.getSdsObjectId());

		SdsOffering sdsOfferingToUpdate = this.sdsOffering;
		String updateName = "Updated";
		sdsOfferingToUpdate.setName(updateName);

		this.sdsOfferingDao.save(sdsOfferingToUpdate);
		SdsOffering updatedSdsOffering = this
				.getOfferingAlternativeMethod(constantSdsOfferingId);

		assertEquals(constantSdsOfferingId, updatedSdsOffering.getSdsObjectId());
		assertFalse(actualSdsOffering.equals(updatedSdsOffering));
		assertEquals(updateName, updatedSdsOffering.getName());
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#save(net.sf.sail.webapp.domain.sds.SdsOffering)}.
	 */
	@SuppressWarnings("unchecked")
	public void testSave_NewOffering() throws Exception {
		this.sdsOffering = createAndSaveOffering();

		SdsOffering actualSdsOffering = this
				.getOfferingAlternativeMethod(this.sdsOffering.getSdsObjectId());
		assertEqualOfferings(actualSdsOffering);
	}

	private SdsOffering createAndSaveOffering() throws MalformedURLException,
			IOException, SAXException {
		SdsOffering sdsOffering = (SdsOffering) this.applicationContext
				.getBean("sdsOffering");
		sdsOffering.setName(EXPECTED_NAME);

		// create curnit in SDS
		this.sdsCurnit = (SdsCurnit) this.applicationContext
				.getBean("sdsCurnit");
		this.sdsCurnit.setSdsObjectId(createCurnitInSds());
		sdsOffering.setSdsCurnit(sdsCurnit);

		// create jnlp in SDS
		this.sdsJnlp = (SdsJnlp) this.applicationContext.getBean("sdsJnlp");
		this.sdsJnlp.setSdsObjectId(createJnlpInSds());
		sdsOffering.setSdsJnlp(sdsJnlp);

		assertNull(sdsOffering.getSdsObjectId());
		sdsOfferingDao.save(sdsOffering);
		assertNotNull(sdsOffering.getSdsObjectId());
		return sdsOffering;
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#delete(net.sf.sail.webapp.domain.sds.SdsOffering)}.
	 */
	public void testDelete() {
		try {
			this.sdsOfferingDao.delete(null);
			fail("UnsupportedOperationException expected");
		} catch (UnsupportedOperationException expected) {
		}
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#getById(java.lang.Long)}.
	 */
	public void testGetById() throws Exception {
		this.sdsOffering = createAndSaveOffering(); 
		SdsOffering actualSdsOffering = this.sdsOfferingDao.getById(this.sdsOffering.getSdsObjectId());
		assertEqualOfferings(actualSdsOffering);
	}

	private void assertEqualOfferings(SdsOffering actualSdsOffering) {
		assertEquals(this.sdsOffering.getName(), actualSdsOffering.getName());
		assertEquals(this.sdsOffering.getSdsObjectId(), actualSdsOffering
				.getSdsObjectId());
		assertEquals(this.sdsOffering.getSdsCurnit().getSdsObjectId(),
				actualSdsOffering.getSdsCurnit().getSdsObjectId());
		assertEquals(this.sdsOffering.getSdsJnlp().getSdsObjectId(),
				actualSdsOffering.getSdsJnlp().getSdsObjectId());
		//TODO LAW this below should change soon
		assertNull(actualSdsOffering.getSdsCurnitMap());
	}

}