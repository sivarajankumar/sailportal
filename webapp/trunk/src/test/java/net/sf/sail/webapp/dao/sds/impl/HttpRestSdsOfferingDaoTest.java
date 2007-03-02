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

import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.junit.AbstractSpringTests;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsOfferingDaoTest extends AbstractSpringTests {

    private HttpRestSdsOfferingDao sdsOfferingDao;

    private HttpRestTransport httpRestTransport;

    private WebConversation webConversation;

    /**
     * @param sdsOfferingDao
     *            the sdsOfferingDao to set
     */
    public void setSdsOfferingDao(HttpRestSdsOfferingDao sdsOfferingDao) {
        this.sdsOfferingDao = sdsOfferingDao;
    }

    /**
     * @param httpRestTransport
     *            the httpRestTransport to set
     */
    public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
        this.httpRestTransport = httpRestTransport;
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#onSetUp()
     */
    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.webConversation = new WebConversation();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#onTearDown()
     */
    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.sdsOfferingDao = null;
        this.webConversation = null;
        this.httpRestTransport = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#getList()}.
     */
    @SuppressWarnings("unchecked")
    public void testGetList() throws Exception {
        WebRequest webRequest = new GetMethodWebRequest(this.httpRestTransport
                .getBaseUrl()
                + "workgroup");
        webRequest.setHeaderField("Accept", "application/xml");
        WebResponse webResponse = this.webConversation.getResponse(webRequest);
        // This integration test is only interested in the fact that the real
        // SDS returns an XML document with workgroups as the root element. As
        // we have no way to guarantee the data to be in a known state, the
        // actual test to parse the returned document will be done in a separate
        // unit test that will mock the response.
        assertEquals("workgroups", webResponse.getDOM().getDocumentElement()
                .getNodeName());
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#createDataObject()}.
     */
    public void testCreateDataObject() {
        assertTrue(this.sdsOfferingDao.createDataObject() instanceof SdsOffering);
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
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsOfferingDao#save(net.sf.sail.webapp.domain.sds.SdsOffering)}.
     */
    public void testSave() {
        try {
            this.sdsOfferingDao.save(null);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
}