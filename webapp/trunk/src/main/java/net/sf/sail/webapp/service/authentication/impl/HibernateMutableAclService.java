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
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.AclSidDao;
import net.sf.sail.webapp.dao.authentication.AclTargetObjectDao;
import net.sf.sail.webapp.dao.authentication.AclTargetObjectIdentityDao;
import net.sf.sail.webapp.domain.authentication.MutableAclSid;
import net.sf.sail.webapp.domain.authentication.MutableAclTargetObject;
import net.sf.sail.webapp.domain.authentication.MutableAclTargetObjectIdentity;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclSid;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclTargetObject;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclTargetObjectIdentity;

import org.acegisecurity.Authentication;
import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.ChildrenExistException;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.jdbc.JdbcMutableAclService;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.Assert;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateMutableAclService extends HibernateAclService implements
        MutableAclService {

    private AclSidDao<MutableAclSid> aclSidDao;

    private AclTargetObjectDao<MutableAclTargetObject> aclTargetObjectDao;

    private AclTargetObjectIdentityDao<MutableAclTargetObjectIdentity> aclTargetObjectIdentityDao;
    
    private JdbcMutableAclService mutableAclService;

    /**
     * @see org.acegisecurity.acls.MutableAclService#createAcl(org.acegisecurity.acls.objectidentity.ObjectIdentity)
     */
    public MutableAcl createAcl(ObjectIdentity objectIdentity)
            throws AlreadyExistsException {
        // TODO - implement this using a hibernate dao
        // return this.mutableAclService.createAcl(objectIdentity);

        // Need to retrieve the current principal, in order to know who "owns"
        // this ACL (can be changed later on), or else create a new one
        final Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String sidName = (auth.getPrincipal() instanceof UserDetails) ? ((UserDetails) auth
                .getPrincipal()).getUsername()
                : (String) auth.getPrincipal();
        MutableAclSid sid = this.aclSidDao.retrieveBySidName(sidName);
        if (sid == null) {
            sid = new PersistentAclSid();
            sid.setPrincipal(auth);
        }

        // Need to retrieve the target object, or else create a new one
        final String classname = objectIdentity.getJavaType().getName();
        MutableAclTargetObject aclTargetObject = this.aclTargetObjectDao
                .retrieveByClassname(classname);
        if (aclTargetObject == null) {
            aclTargetObject = new PersistentAclTargetObject();
            aclTargetObject.setClassname(classname);
        }

        // Create the acl_object_identity row
        MutableAclTargetObjectIdentity mutableObjectIdentity;
        if (objectIdentity instanceof MutableAclTargetObjectIdentity) {
            mutableObjectIdentity = (MutableAclTargetObjectIdentity) objectIdentity;
        } else {
            mutableObjectIdentity = new PersistentAclTargetObjectIdentity();
            mutableObjectIdentity.setAclTargetObjectId((Long) objectIdentity
                    .getIdentifier());
        }
        mutableObjectIdentity.setInheriting(Boolean.TRUE);
        mutableObjectIdentity.setAclTargetObject(aclTargetObject);
        mutableObjectIdentity.setOwnerSid(sid);

        try {
            this.aclTargetObjectIdentityDao.save(mutableObjectIdentity);
        } catch (DataIntegrityViolationException dive) {
            throw new AlreadyExistsException("Object identity '"
                    + objectIdentity + "' already exists");
        }
       
        // Retrieve the ACL via superclass (ensures cache registration, proper
        // retrieval etc)
        Acl acl = readAclById(objectIdentity);
        Assert.isInstanceOf(MutableAcl.class, acl,
                "MutableAcl should be been returned");

        return (MutableAcl) acl;
    }

    /**
     * @see org.acegisecurity.acls.MutableAclService#deleteAcl(org.acegisecurity.acls.objectidentity.ObjectIdentity,
     *      boolean)
     */
    public void deleteAcl(ObjectIdentity objectIdentity, boolean deleteChildren)
            throws ChildrenExistException {
        // TODO - implement this using a hibernate dao
        this.mutableAclService.deleteAcl(objectIdentity, deleteChildren);
    }

    /**
     * @see org.acegisecurity.acls.MutableAclService#updateAcl(org.acegisecurity.acls.MutableAcl)
     */
    public MutableAcl updateAcl(MutableAcl acl) throws NotFoundException {
        // // TODO - implement this using a hibernate dao
        // return this.mutableAclService.updateAcl(acl);
        AccessControlEntry[] aces = acl.getEntries();
        return acl;
    }

    /**
     * @param aclSidDao
     *                the aclSidDao to set
     */
    @Required
    public void setAclSidDao(AclSidDao<MutableAclSid> aclSidDao) {
        this.aclSidDao = aclSidDao;
    }

    /**
     * @param aclTargetObjectDao
     *                the aclTargetObjectDao to set
     */
    @Required
    public void setAclTargetObjectDao(
            AclTargetObjectDao<MutableAclTargetObject> aclTargetObjectDao) {
        this.aclTargetObjectDao = aclTargetObjectDao;
    }

    /**
     * @param aclTargetObjectIdentityDao
     *                the aclTargetObjectIdentityDao to set
     */
    @Required
    public void setAclTargetObjectIdentityDao(
            AclTargetObjectIdentityDao<MutableAclTargetObjectIdentity> aclTargetObjectIdentityDao) {
        this.aclTargetObjectIdentityDao = aclTargetObjectIdentityDao;
    }

    /**
     * @param mutableAclService
     *                the mutableAclService to set
     */
    @Required
    public void setMutableAclService(JdbcMutableAclService mutableAclService) {
        // TODO - remove the delegate after implementing hibernate dao
        this.mutableAclService = mutableAclService;
    }

}