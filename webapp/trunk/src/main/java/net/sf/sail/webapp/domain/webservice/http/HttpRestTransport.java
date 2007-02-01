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

/**
 * An HTTP REST facade.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface HttpRestTransport {

  /**
   * Performs the POST operation given the data required for the post.
   * 
   * @param httpRequestData
   *          All the data required for this post request.
   * @return A <code>Map</code> of response headers where the key is the
   *         header name and the value is the header value.
   */
  public Map<String, String> post(HttpPostRequest httpRequestData);

  /**
   * Performs the GET operation given the data required for the get.
   * 
   * @param httpRequestData
   *          All the data required for this get request.
   * @return An <code>InputStream</code> containing the response body.
   */
  public InputStream get(HttpGetRequest httpRequestData);
}