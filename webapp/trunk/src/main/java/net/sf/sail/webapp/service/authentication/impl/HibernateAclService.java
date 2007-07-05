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

import java.util.Map;

import net.sf.sail.webapp.dao.authentication.ObjectIdentityDao;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclTargetObjectIdentity;

import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.jdbc.LookupStrategy;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.Sid;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateAclService implements AclService {

    private LookupStrategy lookupStrategy;

    private ObjectIdentityDao<PersistentAclTargetObjectIdentity> objectIdentityDao;

    /**
     * @see org.acegisecurity.acls.AclService#findChildren(org.acegisecurity.acls.objectidentity.ObjectIdentity)
     */
    public ObjectIdentity[] findChildren(ObjectIdentity parentIdentity) {
        return this.objectIdentityDao.findChildren(parentIdentity);
    }

    /**
     * @see org.acegisecurity.acls.AclService#readAclById(org.acegisecurity.acls.objectidentity.ObjectIdentity)
     */
    public Acl readAclById(ObjectIdentity object) throws NotFoundException {
        return readAclById(object, null);
    }

    /**
     * @see org.acegisecurity.acls.AclService#readAclById(org.acegisecurity.acls.objectidentity.ObjectIdentity,
     *      org.acegisecurity.acls.sid.Sid[])
     */
    @SuppressWarnings("unchecked")
    public Acl readAclById(ObjectIdentity object, Sid[] sids)
            throws NotFoundException {
        Map map = readAclsById(new ObjectIdentity[] { object }, sids);

        if (map.size() == 0) {
            throw new NotFoundException("Could not find ACL");
        } else {
            return (Acl) map.get(object);
        }
    }

    /**
     * @see org.acegisecurity.acls.AclService#readAclsById(org.acegisecurity.acls.objectidentity.ObjectIdentity[])
     */
    @SuppressWarnings("unchecked")
    public Map readAclsById(ObjectIdentity[] objects) throws NotFoundException {
        return readAclsById(objects, null);
    }

    /**
     * @see org.acegisecurity.acls.AclService#readAclsById(org.acegisecurity.acls.objectidentity.ObjectIdentity[],
     *      org.acegisecurity.acls.sid.Sid[])
     */
    @SuppressWarnings("unchecked")
    public Map readAclsById(ObjectIdentity[] objects, Sid[] sids)
            throws NotFoundException {
        return lookupStrategy.readAclsById(objects, sids);
    }

    /**
     * @param lookupStrategy
     *                the lookupStrategy to set
     */
    @Required
    public void setLookupStrategy(LookupStrategy lookupStrategy) {
        this.lookupStrategy = lookupStrategy;
    }

    /**
     * @param objectIdentityDao
     *                the objectIdentityDao to set
     */
    @Required
    public void setObjectIdentityDao(
            ObjectIdentityDao<PersistentAclTargetObjectIdentity> objectIdentityDao) {
        this.objectIdentityDao = objectIdentityDao;
    }
}