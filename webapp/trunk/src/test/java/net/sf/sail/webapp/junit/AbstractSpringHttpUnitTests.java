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
package net.sf.sail.webapp.junit;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public abstract class AbstractSpringHttpUnitTests extends AbstractSpringTests {

    private static final String DEFAULT_NAME = "d fault";

    private static final String DEFAULT_URL = "http://www.google.com/";

    protected HttpRestTransport httpRestTransport;

    protected WebConversation webConversation;

    public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
        this.httpRestTransport = httpRestTransport;
    }

    @Override
    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.webConversation = new WebConversation();
    }

    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.webConversation = null;
        this.httpRestTransport = null;
    }

    /**
     * Uses a parser to build a JDom document from the response stream.
     * 
     * @param webResponse
     * @return the JDom document
     * @throws IOException
     * @throws JDOMException
     */
    protected Document createDocumentFromResponse(WebResponse webResponse)
            throws IOException, JDOMException {
        InputStream responseStream = null;
        try {
            SAXBuilder builder = new SAXBuilder();
            responseStream = webResponse.getInputStream();
            return builder.build(responseStream);
        } finally {
            if (responseStream != null) {
                responseStream.close();
            }
        }
    }

    /**
     * Uses httpunit to go over the network to make a GET REST request.
     * 
     * @param urlRelativeToBaseUrl
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected WebResponse makeHttpRestGetRequest(String urlRelativeToBaseUrl)
            throws MalformedURLException, IOException, SAXException {
        WebRequest webRequest = new GetMethodWebRequest(this.httpRestTransport
                .getBaseUrl()
                + urlRelativeToBaseUrl);
        webRequest.setHeaderField("Accept", HttpRestTransport.APPLICATION_XML);
        return this.webConversation.getResponse(webRequest);
    }

    /**
     * Uses httpunit to go over the network to make a POST REST request.
     * 
     * @param urlRelativeToBaseUrl
     * @param body
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected WebResponse makeHttpRestPostRequest(String urlRelativeToBaseUrl,
            String body) throws MalformedURLException, IOException,
            SAXException {
        InputStream bodyDataStream = new ByteArrayInputStream(body.getBytes());
        WebRequest webRequest = new PostMethodWebRequest(this.httpRestTransport
                .getBaseUrl()
                + urlRelativeToBaseUrl, bodyDataStream,
                HttpRestTransport.APPLICATION_XML);
        return this.webConversation.getResponse(webRequest);
    }

    /**
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected Integer createUserInSds() throws MalformedURLException,
            IOException, SAXException {
        WebResponse webResponse = this.makeHttpRestPostRequest("/sail_user",
                "<user><first-name>" + DEFAULT_NAME
                        + "</first-name><last-name>" + DEFAULT_NAME
                        + "</last-name></user>");
        return this.extractNewlyCreatedId(webResponse);
    }

    /**
     * @param sdsCurnitId
     * @param sdsJnlpId
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected Integer createOfferingInSds(Integer sdsCurnitId, Integer sdsJnlpId)
            throws MalformedURLException, IOException, SAXException {
        WebResponse webResponse = this.makeHttpRestPostRequest("/offering",
                "<offering><name>" + DEFAULT_NAME + "</name><curnit-id>"
                        + sdsCurnitId + "</curnit-id><jnlp-id>" + sdsJnlpId
                        + "</jnlp-id></offering>");
        return this.extractNewlyCreatedId(webResponse);
    }

    /**
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected Integer createJnlpInSds() throws MalformedURLException,
            IOException, SAXException {
        WebResponse webResponse = this.makeHttpRestPostRequest("/jnlp",
                "<jnlp><name>" + DEFAULT_NAME + "</name><url>" + DEFAULT_URL
                        + "</url></jnlp>");
        return this.extractNewlyCreatedId(webResponse);
    }

    /**
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws SAXException
     */
    protected Integer createCurnitInSds() throws MalformedURLException,
            IOException, SAXException {
        WebResponse webResponse = this.makeHttpRestPostRequest("/curnit",
                "<curnit><name>" + DEFAULT_NAME + "</name><url>" + DEFAULT_URL
                        + "</url></curnit>");
        return this.extractNewlyCreatedId(webResponse);
    }

    private Integer extractNewlyCreatedId(WebResponse webResponse) {
        assertEquals(HttpStatus.SC_CREATED, webResponse.getResponseCode());
        String locationHeader = webResponse.getHeaderField("Location");
        return new Integer(locationHeader.substring(locationHeader
                .lastIndexOf("/") + 1));
    }
}