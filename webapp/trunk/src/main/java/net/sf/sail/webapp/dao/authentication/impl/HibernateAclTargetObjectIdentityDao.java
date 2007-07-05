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
package net.sf.sail.webapp.dao.authentication.impl;

import java.util.List;

import net.sf.sail.webapp.dao.authentication.ObjectIdentityDao;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclTargetObjectIdentity;

import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateAclTargetObjectIdentityDao extends HibernateDaoSupport
        implements ObjectIdentityDao<PersistentAclTargetObjectIdentity> {

    private static final PersistentAclTargetObjectIdentity[] SAMPLE = new PersistentAclTargetObjectIdentity[0];

    /**
     * @see net.sf.sail.webapp.dao.authentication.ObjectIdentityDao#findChildren(org.acegisecurity.acls.objectidentity.ObjectIdentity)
     */
    public PersistentAclTargetObjectIdentity[] findChildren(
            ObjectIdentity parentIdentity) {
        List<?> list = this
                .getHibernateTemplate()
                .findByNamedParam(
                        "from PersistentAclTargetObjectIdentity as object_identity where object_identity.parent = :parent",
                        new String[] { "parent" },
                        new Object[] { parentIdentity });
        return list.toArray(SAMPLE);
    }
}