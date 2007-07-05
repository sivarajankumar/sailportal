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

import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.ChildrenExistException;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.jdbc.JdbcMutableAclService;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateMutableAclService extends HibernateAclService implements
        MutableAclService {

    private JdbcMutableAclService mutableAclService;

    /**
     * @see org.acegisecurity.acls.MutableAclService#createAcl(org.acegisecurity.acls.objectidentity.ObjectIdentity)
     */
    public MutableAcl createAcl(ObjectIdentity objectIdentity)
            throws AlreadyExistsException {
        // TODO - implement this using a hibernate dao
        return this.mutableAclService.createAcl(objectIdentity);
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
        // TODO - implement this using a hibernate dao
        return this.mutableAclService.updateAcl(acl);
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