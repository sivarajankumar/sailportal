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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.sds.SdsObject;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.HttpPutRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public abstract class AbstractSdsUpdateCommandHttpRestImplTest extends TestCase {

    static final String HEADER_LOCATION = "Location";

    static final String PORTAL_URL = "http://portal/url/";

    static final Integer EXPECTED_ID = new Integer(1);

    protected HttpRestTransport mockTransport;

    protected HttpPutRequest httpRequest;

    protected SdsCommand putCommand;

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
        this.putCommand = null;
        this.httpRequest = null;
    }

    @SuppressWarnings("unchecked")
    public void testExecute_Exception() throws Exception {
        EasyMock.expect(this.mockTransport.put(this.httpRequest)).andThrow(
                new BadRequestException("exception"));
        EasyMock.replay(this.mockTransport);
        try {
            this.putCommand.execute(this.httpRequest);
            fail("Expected BadRequestException");
        } catch (BadRequestException e) {
        }
        EasyMock.verify(this.mockTransport);

        EasyMock.reset(this.mockTransport);
        EasyMock.expect(this.mockTransport.put(this.httpRequest)).andThrow(
                new NetworkTransportException("exception"));
        EasyMock.replay(this.mockTransport);
        try {
            this.putCommand.execute(this.httpRequest);
            fail("Expected NetworkTransportException");
        } catch (NetworkTransportException e) {
        }
        EasyMock.verify(this.mockTransport);
    }

    @SuppressWarnings("unchecked")
    protected SdsObject doExecuteTest(String directory) {
        Map<String, String> responseMap = new HashMap<String, String>();
        responseMap.put(HEADER_LOCATION, PORTAL_URL + directory + EXPECTED_ID);
        EasyMock.expect(this.mockTransport.put(this.httpRequest)).andReturn(
                responseMap);
        EasyMock.replay(this.mockTransport);
        SdsObject actual = (SdsObject) this.putCommand
                .execute(this.httpRequest);
        return actual;
    }

}