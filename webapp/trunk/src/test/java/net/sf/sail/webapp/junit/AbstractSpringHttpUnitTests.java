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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.SAXException;

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
public abstract class AbstractSpringHttpUnitTests extends AbstractSpringTests {

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
        SAXBuilder builder = new SAXBuilder();
        InputStream responseStream = webResponse.getInputStream();
        Document doc = builder.build(responseStream);
        responseStream.close();
        return doc;
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
        webRequest.setHeaderField("Accept", "application/xml");
        return this.webConversation.getResponse(webRequest);
    }
}