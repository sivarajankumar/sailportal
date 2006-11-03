/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Class that implements the Data Access Object (DAO) interface using Hibernate.
 * It is only meant for <code>HibernateUserDetails</code> objects.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDetailsDao extends HibernateDaoSupport implements
    Dao<HibernateUserDetails> {

  /**
   * @see net.sf.sail.webapp.dao.authentication.impl.Dao#save(java.lang.Object)
   */
  public void save(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().saveOrUpdate(userDetails);
  }

  /**
   * @see net.sf.sail.webapp.dao.authentication.impl.Dao#delete(java.lang.Object)
   */
  public void delete(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().delete(userDetails);
  }
}
