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
package net.sf.sail.webapp.service.offering.impl;

import java.util.List;

import net.sf.sail.webapp.dao.offering.OfferingDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.OfferingParameters;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingServiceImpl implements OfferingService {

    protected OfferingDao<Offering> offeringDao;

    protected SdsOfferingDao sdsOfferingDao;

    /**
     * @param offeringDao
     *            the offeringDao to set
     */
    @Required
    public void setOfferingDao(OfferingDao<Offering> offeringDao) {
        this.offeringDao = offeringDao;
    }

    /**
     * @param sdsOfferingDao
     *            the sdsOfferingDao to set
     */
    @Required
    public void setSdsOfferingDao(SdsOfferingDao sdsOfferingDao) {
        this.sdsOfferingDao = sdsOfferingDao;
    }

    /**
     * @see net.sf.sail.webapp.service.offering.OfferingService#getOfferingList()
     */
    public List<Offering> getOfferingList() {
        return offeringDao.getList();
    }

    /**
     * @see net.sf.sail.webapp.service.offering.OfferingService#createOffering(Offering)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
    public Offering createOffering(OfferingParameters offeringParameters) {
    	SdsOffering sdsOffering = new SdsOffering();
    	sdsOffering.setName(offeringParameters.getName());
    	sdsOffering.setSdsCurnit(offeringParameters.getCurnit().getSdsCurnit());
    	sdsOffering.setSdsJnlp(offeringParameters.getJnlp().getSdsJnlp());
        this.sdsOfferingDao.save(sdsOffering);
        
        Offering offering = new OfferingImpl();
        offering.setSdsOffering(sdsOffering);
        this.offeringDao.save(offering);
        return offering;
    }
}