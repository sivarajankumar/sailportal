/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;

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
    UserDetailsDao<HibernateUserDetails> {

  /**
   * @see net.sf.sail.webapp.dao.authentication.Dao#save(java.lang.Object)
   */
  public void save(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().saveOrUpdate(userDetails);
  }

  /**
   * @see net.sf.sail.webapp.dao.authentication.Dao#delete(java.lang.Object)
   */
  public void delete(HibernateUserDetails userDetails) {
    this.getHibernateTemplate().delete(userDetails);
  }

  /**
   * @see net.sf.sail.webapp.dao.authentication.UserDetailsDao#retrieve(java.lang.String)
   */
  public HibernateUserDetails retrieve(String username) {
    return (HibernateUserDetails) DataAccessUtils
        .uniqueResult(this
            .getHibernateTemplate()
            .findByNamedParam(
                "from HibernateUserDetails as user_details where user_details.username = :username",
                new String[] { "username" }, new Object[] { username }));
  }
}