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
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.impl.PersistentGrantedAuthority;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Class that implements the Data Access Object (DAO) interface using Hibernate.
 * 
 * @author Cynick Young
 * 
 * @version $Id: HibernateGrantedAuthorityDao.java 11 2006-11-08 19:22:53Z
 *          cynick $
 * 
 */
public class HibernateGrantedAuthorityDao extends HibernateDaoSupport implements
		GrantedAuthorityDao<MutableGrantedAuthority> {

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#createDataObject()
	 */
	public MutableGrantedAuthority createDataObject() {
		return new PersistentGrantedAuthority();
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
	 */
	public void save(MutableGrantedAuthority grantedAuthority) {
		this.getHibernateTemplate().saveOrUpdate(grantedAuthority);
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
	 */
	public void delete(MutableGrantedAuthority grantedAuthority) {
		this.getHibernateTemplate().delete(grantedAuthority);
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#retrieveByName(java.lang.String)
	 */
	public MutableGrantedAuthority retrieveByName(String authority) {
		return (MutableGrantedAuthority) DataAccessUtils
				.uniqueResult(this
						.getHibernateTemplate()
						.findByNamedParam(
								"from PersistentGrantedAuthority as granted_authority where granted_authority.authority = :authority",
								new String[] { "authority" },
								new Object[] { authority }));
	}
	
	/**
	 * @see net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao#hasRole(java.lang.String)
	 */
	public boolean hasRole(String authority) {
		return (this.retrieveByName(authority) != null);
	}
}