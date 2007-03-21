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

import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;

import com.meterware.httpunit.WebResponse;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsCurnitDaoTest extends AbstractSpringHttpUnitTests {

	private static final String EXPECTED_NAME = "name";

	private static final String EXPECTED_URL = "http://tels-develop.soe.berkeley.edu:8080/maven-jnlp//curnit-airbag.jar";

	private HttpRestSdsCurnitDao sdsCurnitDao;

	private SdsCurnit sdsCurnit;

	public void setSdsCurnitDao(HttpRestSdsCurnitDao sdsCurnitDao) {
		this.sdsCurnitDao = sdsCurnitDao;
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onSetUp()
	 */
	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		this.sdsCurnit = (SdsCurnit) this.applicationContext
				.getBean("sdsCurnit");
		this.sdsCurnit.setName(EXPECTED_NAME);
		this.sdsCurnit.setUrl(EXPECTED_URL);
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onTearDown()
	 */
	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		this.sdsCurnitDao = null;
		this.sdsCurnit = null;
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
	 */
	@SuppressWarnings("unchecked")
	public void testSave_NewUser() throws Exception {
		assertNull(this.sdsCurnit.getSdsObjectId());
		this.sdsCurnitDao.save(this.sdsCurnit);
		assertNotNull(this.sdsCurnit.getSdsObjectId());

		// retrieve newly created user using httpunit and compare with sdsUser
		// saved via DAO
		WebResponse webResponse = makeHttpRestGetRequest("/curnit/"
				+ this.sdsCurnit.getSdsObjectId());
		assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

		Document doc = createDocumentFromResponse(webResponse);

		Element rootElement = doc.getRootElement();
		SdsCurnit actualSdsCurnit = new SdsCurnit();
		actualSdsCurnit.setName(rootElement.getChild("name")
				.getValue());
		actualSdsCurnit.setUrl(rootElement.getChild("url").getValue());
		actualSdsCurnit.setSdsObjectId(new Integer(rootElement.getChild("id")
				.getValue()));
		assertEquals(this.sdsCurnit, actualSdsCurnit);
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
	 */
	public void testDelete() {
		try {
			this.sdsCurnitDao.delete(this.sdsCurnit);
			fail("UnsupportedOperationException expected");
		} catch (UnsupportedOperationException expected) {
		}
	}
}