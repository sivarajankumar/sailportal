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
package net.sf.sail.webapp.domain.webservice.http;

import java.util.Map;

import net.sf.sail.webapp.domain.webservice.BadRequestException;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public final class HttpGetRequest extends AbstractHttpRequest {

   public HttpGetRequest(final Map<String, String> requestHeaders,
      final Map<String, String> requestParameters, final String url,
      final int expectedResponseStatusCode) throws BadRequestException {
     super(requestHeaders, requestParameters, url, expectedResponseStatusCode);
   }

  /*
   * This is intentionally private - to make the HttpPostRequest object
   * immutable.
   */
  private HttpGetRequest() {
  }

}
