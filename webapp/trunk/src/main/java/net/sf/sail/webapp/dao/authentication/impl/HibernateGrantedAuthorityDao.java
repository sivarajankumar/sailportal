/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import net.sf.sail.webapp.dao.SimpleDao;
import net.sf.sail.webapp.domain.authentication.impl.HibernateGrantedAuthority;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Class that implements the Data Access Object (DAO) interface using Hibernate.
 * It is only meant for <code>HibernateGrantedAuthority</code> objects.
 * 
 * @author Cynick Young
 * 
 * @version $Id: HibernateGrantedAuthorityDao.java 11 2006-11-08 19:22:53Z
 *          cynick $
 * 
 */
public class HibernateGrantedAuthorityDao extends HibernateDaoSupport implements
    SimpleDao<HibernateGrantedAuthority> {

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
   */
  public void save(HibernateGrantedAuthority grantedAuthority) {
    this.getHibernateTemplate().saveOrUpdate(grantedAuthority);
  }

  /**
   * @see net.sf.sail.webapp.dao.SimpleDao#delete(java.lang.Object)
   */
  public void delete(HibernateGrantedAuthority grantedAuthority) {
    this.getHibernateTemplate().delete(grantedAuthority);
  }
}