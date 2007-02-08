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

import java.util.List;

import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsOffering;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsOfferingDao implements SdsOfferingDao {

  private SdsCommand<SdsOffering, List<SdsOffering>> sdsOfferingListCommand;

  /**
   * @param sdsOfferingListCommand
   *          the sdsOfferingListCommand to set
   */
  public void setSdsOfferingListCommand(
      SdsCommand<SdsOffering, List<SdsOffering>> sdsOfferingListCommand) {
    this.sdsOfferingListCommand = sdsOfferingListCommand;
  }

  /**
   * @see net.sf.sail.webapp.dao.sds.SdsOfferingDao#getList()
   */
  // TODO - get rid of the unnecessary parameter
  public List<SdsOffering> getList(SdsOffering sdsOffering) {
    this.sdsOfferingListCommand.generateRequest(sdsOffering);
    return this.sdsOfferingListCommand.execute(sdsOffering);
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#createDataObject()
   */
  public SdsOffering createDataObject() {
    return new SdsOffering();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
   */
  public void delete(SdsOffering object) {
    throw new UnsupportedOperationException();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#retrieveByName(java.lang.String)
   */
  public SdsOffering retrieveByName(String name) {
    throw new UnsupportedOperationException();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
   */
  public void save(SdsOffering object) {
    throw new UnsupportedOperationException();
  }

}
