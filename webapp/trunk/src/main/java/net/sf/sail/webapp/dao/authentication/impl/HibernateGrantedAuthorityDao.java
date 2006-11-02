/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.domain.authentication.impl.HibernateGrantedAuthority;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Data Access Object (DAO) class that deals with a persistent store using
 * Hibernate. It is only meant for <code>HibernateGrantedAuthority</code>
 * objects.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HibernateGrantedAuthorityDao extends HibernateDaoSupport {

  /**
   * Saves the granted authority (role) to a persistent data store using
   * Hibernate
   * 
   * @param grantedAuthority
   */
  public void save(HibernateGrantedAuthority grantedAuthority) {
    this.getHibernateTemplate().saveOrUpdate(grantedAuthority);
  }

  /**
   * Deletes the granted authority (role) from a persistent data store using
   * Hibernate
   * 
   * @param grantedAuthority
   */
  public void delete(HibernateGrantedAuthority grantedAuthority) {
    this.getHibernateTemplate().delete(grantedAuthority);
  }
}
