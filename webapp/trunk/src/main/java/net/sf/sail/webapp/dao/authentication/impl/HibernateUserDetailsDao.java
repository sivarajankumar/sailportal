/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data Access Object (DAO) class that deals with a persistent store using
 * Hibernate. It is only meant for <code>HibernateUserDetails</code> objects.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDetailsDao extends HibernateDaoSupport {

  /**
   * Saves the user details to a persistent data store using Hibernate
   * 
   * @param userDetails
   */
  public void save(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().saveOrUpdate(userDetails);
  }

  /**
   * Deletes the user details from a persistent data store using Hibernate
   * 
   * @param userDetails
   */
  public void delete(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().delete(userDetails);
  }
}
