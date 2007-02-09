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

import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.sds.SdsObject;
import net.sf.sail.webapp.domain.webservice.http.AbstractHttpRequest;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;

/**
 * Abstract implementation that encapsulates an SDS command.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public abstract class AbstractSdsCommandHttpRest<H extends AbstractHttpRequest, S extends SdsObject, O>
    implements SdsCommand<S, O> {

  protected static final String APPLICATION_XML = "application/xml";

  protected static final String SLASH = "/";

  protected H httpRequest;

  protected HttpRestTransport transport;

  /**
   * Sets the http REST transport mechanism for the create command.
   * 
   * @param transport
   *          the transport to set
   */
  public void setTransport(final HttpRestTransport transport) {
    this.transport = transport;
  }
}