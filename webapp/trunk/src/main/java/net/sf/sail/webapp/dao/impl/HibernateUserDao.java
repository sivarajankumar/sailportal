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
package net.sf.sail.webapp.dao.impl;

import net.sf.sail.webapp.dao.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDao extends HibernateDaoSupport implements
    UserDao<User> {

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#createDataObject()
   */
  public User createDataObject() {
    return new UserImpl();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
   */
  public void delete(User user) {
	 this.getHibernateTemplate().delete(user);
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#retrieveByName(java.lang.String)
   */
  public User retrieveByName(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException();
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
   */
  public void save(User user) {
    this.getHibernateTemplate().saveOrUpdate(user);
  }
}