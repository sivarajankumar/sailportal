/**
 * Copyright University of Toronto 2006 (c)
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