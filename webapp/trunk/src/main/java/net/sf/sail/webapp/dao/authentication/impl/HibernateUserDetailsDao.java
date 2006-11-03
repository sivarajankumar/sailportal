/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
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
  
  /**
   * Given a username, returns a <code>UserDetails</code> object from the persistent store or null if no such username exists.
   * 
   * @param username
   * @return UserDetails object or null if no such username exists.
   * @exception IncorrectResultSizeDataAccessException if more than one result object has been found.
   */
  public UserDetails retrieve(String username) {
    return (UserDetails) DataAccessUtils.uniqueResult(this.getHibernateTemplate().findByNamedParam("from HibernateUserDetails as user_details where user_details.username = :username", new String[] {"username"}, new Object[] {username}));
  }
}
