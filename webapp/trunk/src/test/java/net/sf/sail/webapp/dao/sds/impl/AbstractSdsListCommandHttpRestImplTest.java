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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public abstract class AbstractSdsListCommandHttpRestImplTest extends TestCase {

	protected HttpRestTransport mockTransport;
	protected HttpGetRequest httpRequest;
	protected SdsCommand listCommand;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.mockTransport = EasyMock.createMock(HttpRestTransport.class);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.mockTransport = null;
		this.listCommand = null;
		this.httpRequest = null;
	}

	protected void setAndTestResponseStream(final String responseString)
			throws IOException {
		final InputStream responseStream = new ByteArrayInputStream(
				responseString.getBytes());

		final byte[] streamBytes = new byte[responseString.length()];
		assertEquals(responseString.length(), responseStream.read(streamBytes));
		assertEquals(responseString, new String(streamBytes));
		responseStream.reset();

		EasyMock.expect(this.mockTransport.get(this.httpRequest)).andReturn(
				responseStream);
		EasyMock.replay(this.mockTransport);
	}

	@SuppressWarnings("unchecked")
	public void testExecuteExceptions() throws Exception {
		EasyMock.expect(this.mockTransport.get(this.httpRequest)).andThrow(
				new BadRequestException("exception"));
		EasyMock.replay(this.mockTransport);
		try {
			this.listCommand.execute(this.httpRequest);
			fail("Expected BadRequestException");
		} catch (BadRequestException e) {
		}
		EasyMock.verify(this.mockTransport);

		EasyMock.reset(this.mockTransport);
		EasyMock.expect(this.mockTransport.get(this.httpRequest)).andThrow(
				new NetworkTransportException("exception"));
		EasyMock.replay(this.mockTransport);
		try {
			this.listCommand.execute(this.httpRequest);
			fail("Expected NetworkTransportException");
		} catch (NetworkTransportException e) {
		}
		EasyMock.verify(this.mockTransport);
	}
	

	/**
	 * Not testing this since we would be essentially testing info that is hard
	 * coded.
	 */
	public void testGenerateRequest() {
	}


}
