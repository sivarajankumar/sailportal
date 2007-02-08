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
package net.sf.sail.webapp.service.offerings.impl;

import java.util.List;

import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.offerings.OfferingsService;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingsServiceImpl implements OfferingsService {

  private SdsOfferingDao sdsOfferingDao;

  /**
   * @param sdsOfferingDao
   *          the sdsOfferingDao to set
   */
  public void setSdsOfferingDao(SdsOfferingDao sdsOfferingDao) {
    this.sdsOfferingDao = sdsOfferingDao;
  }

  /**
   * @see net.sf.sail.webapp.service.offerings.OfferingsService#getOfferingsList()
   */
  public List<SdsOffering> getOfferingsList() {
    return sdsOfferingDao.getList(sdsOfferingDao.createDataObject());
  }

}