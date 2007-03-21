/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.domain.webservice.http;

import java.io.InputStream;
import java.util.Map;

import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

/**
 * An HTTP REST facade.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface HttpRestTransport {

    public static final String APPLICATION_XML = "application/xml";

    /**
     * Performs the POST operation given the data required for the post.
     * 
     * @param httpRequestData
     *            All the data required for this post request.
     * @return A <code>Map</code> of response headers where the key is the
     *         header name and the value is the header value.
     * @throws BadRequestException
     *             when the status response code is not the same as what is
     *             expected but is a HTTP 400 Bad Request.
     * @throws NetworkTransportException
     *             for all other returned status codes and other network failure
     *             conditions.
     */
    public Map<String, String> post(HttpPostRequest httpRequestData);

    /**
     * Performs the GET operation given the data required for the get.
     * 
     * @param httpRequestData
     *            All the data required for this get request.
     * @return An <code>InputStream</code> containing the response body.
     * @throws BadRequestException
     *             when the status response code is not the same as what is
     *             expected but is a HTTP 400 Bad Request.
     * @throws NetworkTransportException
     *             for all other returned status codes and other network failure
     *             conditions.
     */
    public InputStream get(HttpGetRequest httpRequestData);

    /**
     * Get the base url bound to this HTTP transport.
     * 
     * @return the baseUrl
     */
    public String getBaseUrl();
}