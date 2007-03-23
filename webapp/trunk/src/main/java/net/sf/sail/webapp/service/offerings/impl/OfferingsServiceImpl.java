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

import java.util.Set;

import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.service.offerings.OfferingsService;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Laurel Williams
 * 
 * @version $Id:OfferingsServiceImpl.java 159 2007-03-02 22:42:21Z cynick $
 */
public class OfferingsServiceImpl implements OfferingsService {

    private SdsOfferingDao sdsOfferingDao;

    /**
     * @param sdsOfferingDao
     *            the sdsOfferingDao to set
     */
    @Required
    public void setSdsOfferingDao(SdsOfferingDao sdsOfferingDao) {
        this.sdsOfferingDao = sdsOfferingDao;
    }

    /**
     * @see net.sf.sail.webapp.service.offerings.OfferingsService#getOfferingsList()
     */
    public Set<SdsOffering> getOfferingsList() {
        return sdsOfferingDao.getList();
    }

	/**
	 * @see net.sf.sail.webapp.service.offerings.OfferingsService#createOffering(net.sf.sail.webapp.domain.sds.SdsOffering)
	 */
	public void createOffering(SdsOffering sdsOffering) {
		sdsOfferingDao.save(sdsOffering);
	}
    
    

}