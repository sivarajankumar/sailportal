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
package net.sf.sail.webapp.domain.sds;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

/**
 * An SDS command as described in
 * http://www.telscenter.org/confluence/display/SAIL/REST+protocol+for+SAIL+Data+Services+(SDS).
 * First, generate the appropriate request using generateRequest and then
 * execute the request using execute.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface SdsCommand {

  /**
   * Puts together the request data and the user data required to execute the
   * commmand.
   * 
   * @param userDetails
   *          The user information required for the create commmand.
   * @return The HttpPostRequest constructed from the user details.
   */
  public HttpPostRequest generateRequest(MutableUserDetails userDetails);

  /**
   * Executes the commmand based on data contructed via the generateRequest
   * method.
   * 
   * @return The integer returning the user id of the user in the Sail Data
   *         Services.
   */
  public Integer execute();
}