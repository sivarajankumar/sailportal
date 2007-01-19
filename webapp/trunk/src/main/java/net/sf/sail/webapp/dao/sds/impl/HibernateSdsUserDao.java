/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.dao.sds.impl;

import net.sf.sail.webapp.dao.sds.SdsUserDao;
import net.sf.sail.webapp.domain.sds.SdsUser;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class HibernateSdsUserDao extends HibernateDaoSupport implements
		SdsUserDao<SdsUser> {

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#createDataObject()
	 */
	public SdsUser createDataObject() {
		return new SdsUser();
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
	 */
	public void delete(SdsUser sdsUser) {
		this.getHibernateTemplate().delete(sdsUser);
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#retrieveByName(java.lang.String)
	 */
	public SdsUser retrieveByName(String name) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
	 */
	public void save(SdsUser sdsUser) {
		this.getHibernateTemplate().saveOrUpdate(sdsUser);
	}

}
