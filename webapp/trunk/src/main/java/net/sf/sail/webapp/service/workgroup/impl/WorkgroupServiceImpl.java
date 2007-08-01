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
import java.util.Set;

import net.sf.sail.webapp.dao.sds.SdsWorkgroupDao;
import net.sf.sail.webapp.dao.workgroup.WorkgroupDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
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

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#createPreviewWorkgroupForOfferingIfNecessary(net.sf.sail.webapp.domain.Offering,
     *      java.util.List, net.sf.sail.webapp.domain.User)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
    public List<Workgroup> createPreviewWorkgroupForOfferingIfNecessary(
            Offering offering, List<Workgroup> workgroupList, User user,
            String previewWorkgroupName) {
        if (workgroupList.isEmpty()) {
            SdsWorkgroup sdsWorkgroup = new SdsWorkgroup();
            sdsWorkgroup.addMember(user.getSdsUser());
            sdsWorkgroup.setName(previewWorkgroupName);
            sdsWorkgroup.setSdsOffering(offering.getSdsOffering());
            this.sdsWorkgroupDao.save(sdsWorkgroup);

            Workgroup workgroup = new WorkgroupImpl();
            workgroup.addMember(user);
            workgroup.setOffering(offering);
            workgroup.setSdsWorkgroup(sdsWorkgroup);
            this.workgroupDao.save(workgroup);

            workgroupList.add(workgroup);
        }
        return workgroupList;
    }

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#addMembers(net.sf.sail.webapp.domain.Workgroup, java.util.Set)
     */
    @Transactional()
	public void addMembers(Workgroup workgroup, Set<User> membersToAdd) {
    	SdsWorkgroup sdsWorkgroup = workgroup.getSdsWorkgroup();
    	for (User member : membersToAdd) {
    		workgroup.addMember(member);
    		sdsWorkgroup.addMember(member.getSdsUser());
    	}
        this.sdsWorkgroupDao.save(sdsWorkgroup);
        workgroup.setSdsWorkgroup(sdsWorkgroup);
    	this.workgroupDao.save(workgroup);
	}

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#createWorkgroup(net.sf.sail.webapp.domain.Workgroup, net.sf.sail.webapp.domain.Offering)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
	public Workgroup createWorkgroup(String name, Set<User> members, Offering offering) {
        SdsWorkgroup sdsWorkgroup = new SdsWorkgroup();
        sdsWorkgroup.setName(name);
        for (User member : members) {
        	sdsWorkgroup.addMember(member.getSdsUser());
        }
    	sdsWorkgroup.setSdsOffering(offering.getSdsOffering());
        this.sdsWorkgroupDao.save(sdsWorkgroup);

        Workgroup workgroup = new WorkgroupImpl();
        for (User member : members) {
        	workgroup.addMember(member);
        }
        workgroup.setOffering(offering);
        workgroup.setSdsWorkgroup(sdsWorkgroup);
        this.workgroupDao.save(workgroup);

        return workgroup;
    }
}