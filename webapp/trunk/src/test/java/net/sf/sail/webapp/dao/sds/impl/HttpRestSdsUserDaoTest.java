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

import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.junit.AbstractSpringTests;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

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
public class HttpRestSdsUserDaoTest extends AbstractSpringTests {

    private static final String EXPECTED_FIRST_NAME = "first";

    private static final String EXPECTED_LAST_NAME = "last";

    private HttpRestSdsUserDao sdsUserDao;

    private SdsUser sdsUser;

    private HttpRestTransport httpRestTransport;

    private WebConversation webConversation;

    public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
        this.httpRestTransport = httpRestTransport;
    }

    public void setSdsUserDao(HttpRestSdsUserDao sdsUserDao) {
        this.sdsUserDao = sdsUserDao;
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#onSetUp()
     */
    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.sdsUser = this.sdsUserDao.createDataObject();
        this.sdsUser.setFirstName(EXPECTED_FIRST_NAME);
        this.sdsUser.setLastName(EXPECTED_LAST_NAME);
        this.webConversation = new WebConversation();
    }

    /**
     * @see org.springframework.test.AbstractSingleSpringContextTests#onTearDown()
     */
    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.sdsUserDao = null;
        this.sdsUser = null;
        this.webConversation = null;
        this.httpRestTransport = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#createDataObject()}.
     */
    public void testCreateDataObject() {
        assertTrue(this.sdsUserDao.createDataObject() instanceof SdsUser);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    @SuppressWarnings("unchecked")
    public void testSave_NewUser() throws Exception {
        assertNull(this.sdsUser.getSdsObjectId());
        this.sdsUserDao.save(this.sdsUser);
        assertNotNull(this.sdsUser.getSdsObjectId());

        // retrieve newly created user using httpunit and compare with sdsUser object
        WebRequest webRequest = new GetMethodWebRequest(this.httpRestTransport
                .getBaseUrl()
                + "/user/" + this.sdsUser.getSdsObjectId());
        webRequest.setHeaderField("Accept", "application/xml");
        WebResponse webResponse = this.webConversation.getResponse(webRequest);
        assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

        SAXBuilder builder = new SAXBuilder();
        InputStream responseStream = webResponse.getInputStream();
        Document doc = builder.build(responseStream);
        responseStream.close();

        Element rootElement = doc.getRootElement();
        SdsUser actualSdsUser = new SdsUser();
        actualSdsUser.setFirstName(rootElement.getChild("first-name").getValue());
        actualSdsUser.setLastName(rootElement.getChild("last-name").getValue());
        actualSdsUser.setSdsObjectId(new Integer(rootElement.getChild("id").getValue()));
        assertEquals(this.sdsUser, actualSdsUser);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    public void testDelete() {
        try {
            this.sdsUserDao.delete(this.sdsUser);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
}