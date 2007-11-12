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

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.sds.HttpStatusCodeException;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupDao;
import net.sf.sail.webapp.dao.workgroup.WorkgroupDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.acegisecurity.acls.domain.BasePermission;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.domain.impl.ChangeWorkgroupParameters;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class WorkgroupServiceImpl implements WorkgroupService {

    protected SdsWorkgroupDao sdsWorkgroupDao;

    protected WorkgroupDao<Workgroup> workgroupDao;
    
    protected AclService<Workgroup> aclService;

    /**
	 * @param aclService the aclService to set
	 */
    @Required
	public void setAclService(AclService<Workgroup> aclService) {
		this.aclService = aclService;
	}

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
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#createWorkgroup(net.sf.sail.webapp.domain.Workgroup, net.sf.sail.webapp.domain.Offering)
     */
    @Transactional(rollbackFor = { HttpStatusCodeException.class })
	public Workgroup createWorkgroup(String name, Set<User> members, Offering offering) {
        SdsWorkgroup sdsWorkgroup = createSdsWorkgroup(name, members, offering);

        Workgroup workgroup = createWorkgroup(members, offering, sdsWorkgroup);

        this.sdsWorkgroupDao.save(workgroup.getSdsWorkgroup());
        this.workgroupDao.save(workgroup);
        
        this.aclService.addPermission(workgroup, BasePermission.ADMINISTRATION);

        return workgroup;
    }

	/**
	 * Creates and returns a <code>Workgroup</code> given parameters
	 * 
	 * @param members Set of users in this SdsWorkgroup
	 * @param offering which <code>Offering</code> this workgroup belongs in
	 * @param sdsWorkgroup <code>SdsWorkgroup</code that this Workgroup contains
	 * @return created <code>Workgroup</code>
	 */
	protected Workgroup createWorkgroup(Set<User> members, Offering offering,
			SdsWorkgroup sdsWorkgroup) {
		Workgroup workgroup = new WorkgroupImpl();
        for (User member : members) {
        	workgroup.addMember(member);
        }
        workgroup.setOffering(offering);
        workgroup.setSdsWorkgroup(sdsWorkgroup);
		return workgroup;
	}

	/**
	 * Creates and returns a <code>SdsWorkgroup</code> given parameters
	 * 
	 * @param name what this SdsWorkgroup is called
	 * @param members Set of users in this SdsWorkgroup
	 * @param offering which <code>Offering</code> this workgroup belongs in
	 * @return created <code>SdsWorkgroup</code>
	 */
	protected SdsWorkgroup createSdsWorkgroup(String name, Set<User> members,
			Offering offering) {
		SdsWorkgroup sdsWorkgroup = new SdsWorkgroup();
        sdsWorkgroup.setName(name);
        for (User member : members) {
        	sdsWorkgroup.addMember(member.getSdsUser());
        }
    	sdsWorkgroup.setSdsOffering(offering.getSdsOffering());
		return sdsWorkgroup;
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
    @Transactional(rollbackFor = { HttpStatusCodeException.class })
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
            
            this.aclService.addPermission(workgroup, BasePermission.ADMINISTRATION);

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
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#removeMembers(Workgroup, Set)
     */
    @Transactional()
	public void removeMembers(Workgroup workgroup, Set<User> membersToRemove) {
    	SdsWorkgroup sdsWorkgroup = workgroup.getSdsWorkgroup();
    	for (User member : membersToRemove) {
    		workgroup.removeMember(member);
    		sdsWorkgroup.removeMember(member.getSdsUser());
    	}
        this.sdsWorkgroupDao.save(sdsWorkgroup);
        workgroup.setSdsWorkgroup(sdsWorkgroup);
    	this.workgroupDao.save(workgroup);
	}

	/**
	 * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#retrieveById(Long)
	 */
    public Workgroup retrieveById(Long workgroupId) throws ObjectNotFoundException {
		return workgroupDao.getById(workgroupId);
	}

    /**
     * @see net.sf.sail.webapp.service.workgroup.WorkgroupService#updateWorkgroupMembership(net.sf.sail.webapp.domain.User, net.sf.sail.webapp.domain.Workgroup, net.sf.sail.webapp.domain.Workgroup)
     */
    public void updateWorkgroupMembership(ChangeWorkgroupParameters params) {
    	
    }
}