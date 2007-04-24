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
package net.sf.sail.webapp.service.workgroup.impl;

import java.util.List;

import net.sf.sail.webapp.dao.sds.SdsWorkgroupDao;
import net.sf.sail.webapp.dao.workgroup.WorkgroupDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class WorkgroupServiceImpl implements WorkgroupService {

    private SdsWorkgroupDao sdsWorkgroupDao;

    private WorkgroupDao<Workgroup> workgroupDao;

    /**
     * @param sdsWorkgroupDao
     *            the sdsWorkgroupDao to set
     */
    @Required
    public void setSdsWorkgroupDao(SdsWorkgroupDao sdsWorkgroupDao) {
        this.sdsWorkgroupDao = sdsWorkgroupDao;
    }

    /**
     * @param workgroupDao
     *            the workgroupDao to set
     */
    @Required
    public void setWorkgroupDao(WorkgroupDao<Workgroup> workgroupDao) {
        this.workgroupDao = workgroupDao;
    }

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#createWorkgroup(net.sf.sail.webapp.domain.Workgroup)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
    public void createWorkgroup(Workgroup workgroup) {
        this.sdsWorkgroupDao.save(workgroup.getSdsWorkgroup());
        this.workgroupDao.save(workgroup);
    }

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#getWorkgroupIterator()
     */
    @Transactional(readOnly = true)
    public List<Workgroup> getWorkgroupList() {
        return this.workgroupDao.getList();
    }

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#getWorkgroupListByOfferingAndUser(net.sf.sail.webapp.domain.Offering,
     *      net.sf.sail.webapp.domain.User)
     */
    @Transactional(readOnly = true)
    public List<Workgroup> getWorkgroupListByOfferingAndUser(Offering offering,
            User user) {
        return this.workgroupDao.getListByOfferingAndUser(offering, user);
    }
}