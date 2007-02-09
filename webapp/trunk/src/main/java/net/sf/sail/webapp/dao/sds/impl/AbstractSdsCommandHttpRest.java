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

//  protected String baseUrl;

//  protected Integer portalId;

//  /**
//   * Sets the base url for the website providing the Sail Data Service (i.e.
//   * http://rails.dev.concord.org/sds/portal/)
//   * 
//   * @param baseUrl
//   *          the baseUrl to set
//   */
//  public void setBaseUrl(final String baseUrl) {
//    this.baseUrl = baseUrl;
//  }

//  /**
//   * Sets the portal id assigned to the specific portal being accessed via the
//   * Sail Data Services. This is simply an integer that has been assigned by
//   * creating a new portal relationship (see
//   * http://www.telscenter.org/confluence/display/SAIL/REST+protocol+for+SAIL+Data+Services+(SDS))
//   * 
//   * @param portalId
//   *          the portalId to set
//   */
//  public void setPortalId(final Integer portalId) {
//    this.portalId = portalId;
//  }

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