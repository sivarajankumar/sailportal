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

import net.sf.sail.webapp.dao.authentication.AclSidDao;
import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;
import net.sf.sail.webapp.domain.authentication.MutableAclSid;
import net.sf.sail.webapp.domain.authentication.impl.PersistentAclSid;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateAclSidDao extends AbstractHibernateDao<MutableAclSid>
        implements AclSidDao<MutableAclSid> {

    private static final String FIND_ALL_QUERY = "from PersistentAclSid";

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#save(java.lang.Object)
     */
    @Override
    public void save(MutableAclSid object) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
     */
    @Override
    protected Class<PersistentAclSid> getDataObjectClass() {
        return PersistentAclSid.class;
    }

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
     */
    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }
}