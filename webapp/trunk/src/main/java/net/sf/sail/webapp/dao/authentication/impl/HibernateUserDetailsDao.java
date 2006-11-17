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

import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Class that implements the <code>UserDetailsDao</code> interface using
 * Hibernate.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDetailsDao extends HibernateDaoSupport implements
    UserDetailsDao<MutableUserDetails> {

  /**
   * @see net.sf.sail.webapp.dao.authentication.UserDetailsDao#createDataObject()
   */
  public MutableUserDetails createDataObject() {
    return new HibernateUserDetails();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
   */
  public void save(MutableUserDetails userDetails) {
    this.getHibernateTemplate().saveOrUpdate(userDetails);
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
   */
  public void delete(MutableUserDetails userDetails) {
    this.getHibernateTemplate().delete(userDetails);
  }

  /**
   * @see net.sf.sail.webapp.dao.authentication.UserDetailsDao#retrieveByUsername(java.lang.String)
   */
  public MutableUserDetails retrieveByUsername(String username) {
    return (MutableUserDetails) DataAccessUtils
        .uniqueResult(this
            .getHibernateTemplate()
            .findByNamedParam(
                "from HibernateUserDetails as user_details where user_details.username = :username",
                new String[] { "username" }, new Object[] { username }));
  }
}