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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.junit.AbstractSpringTests;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

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

    public void setSdsOfferingDao(HttpRestSdsOfferingDao sdsOfferingDao) {
        this.sdsOfferingDao = sdsOfferingDao;
    }

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
        // To test, we will retrieve the offering list through 2 methods, via
        // DAO and httpunit. Compare the lists and make sure that they're
        // equivalent.
        // *Note* there is a small chance that between the 2 retrievals, a new
        // offering may be inserted into the SDS and cause this test to break.
        Set<SdsOffering> actualSet = this.sdsOfferingDao.getList();

        WebRequest webRequest = new GetMethodWebRequest(this.httpRestTransport
                .getBaseUrl()
                + "/offering");
        webRequest.setHeaderField("Accept", "application/xml");
        WebResponse webResponse = this.webConversation.getResponse(webRequest);
        assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

        SAXBuilder builder = new SAXBuilder();
        InputStream responseStream = webResponse.getInputStream();
        Document doc = builder.build(responseStream);
        responseStream.close();

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