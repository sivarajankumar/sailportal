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

import net.sf.sail.webapp.domain.sds.SdsJnlp;
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
public class HttpRestSdsJnlpDaoTest extends AbstractSpringHttpUnitTests {

	private static final String EXPECTED_NAME = "name";

	private static final String EXPECTED_URL = "http://tels-develop.soe.berkeley.edu:8080/maven-jnlp//curnit-airbag.jar";

	private HttpRestSdsJnlpDao sdsJnlpDao;

	private SdsJnlp sdsJnlp;

	public void setSdsJnlpDao(HttpRestSdsJnlpDao sdsJnlpDao) {
		this.sdsJnlpDao = sdsJnlpDao;
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onSetUp()
	 */
	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		this.sdsJnlp = (SdsJnlp) this.applicationContext
				.getBean("sdsJnlp");
		this.sdsJnlp.setName(EXPECTED_NAME);
		this.sdsJnlp.setUrl(EXPECTED_URL);
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onTearDown()
	 */
	@Override
	protected void onTearDown() throws Exception {
		super.onTearDown();
		this.sdsJnlpDao = null;
		this.sdsJnlp = null;
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
	 */
	@SuppressWarnings("unchecked")
	public void testSave_NewUser() throws Exception {
		assertNull(this.sdsJnlp.getSdsObjectId());
		this.sdsJnlpDao.save(this.sdsJnlp);
		assertNotNull(this.sdsJnlp.getSdsObjectId());

		// retrieve newly created user using httpunit and compare with sdsUser
		// saved via DAO
		WebResponse webResponse = makeHttpRestGetRequest("/jnlp/"
				+ this.sdsJnlp.getSdsObjectId());
		assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

		Document doc = createDocumentFromResponse(webResponse);

		Element rootElement = doc.getRootElement();
		SdsJnlp actualSdsJnlp = new SdsJnlp();
		actualSdsJnlp.setName(rootElement.getChild("name")
				.getValue());
		actualSdsJnlp.setUrl(rootElement.getChild("url").getValue());
		actualSdsJnlp.setSdsObjectId(new Integer(rootElement.getChild("id")
				.getValue()));
		assertEquals(this.sdsJnlp.getName(), actualSdsJnlp.getName());
		assertEquals(this.sdsJnlp.getSdsObjectId(), actualSdsJnlp.getSdsObjectId());
		assertEquals(this.sdsJnlp.getUrl(), actualSdsJnlp.getUrl());
		assertEquals(this.sdsJnlp, actualSdsJnlp);
	}

	/**
	 * Test method for
	 * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
	 */
	public void testDelete() {
		try {
			this.sdsJnlpDao.delete(this.sdsJnlp);
			fail("UnsupportedOperationException expected");
		} catch (UnsupportedOperationException expected) {
		}
	}
}