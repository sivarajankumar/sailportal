/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.authentication.impl.HibernateGrantedAuthority;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.hibernate.SessionFactory;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateGrantedAuthorityDaoTest extends
    AbstractTransactionalDbTests {

  private static final String DEFAULT_ROLE = "default_role";

  private HibernateGrantedAuthority defaultGrantedAuthority;

  private HibernateGrantedAuthorityDao authorityDao;

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.defaultGrantedAuthority = new HibernateGrantedAuthority();
    this.defaultGrantedAuthority.setAuthority(DEFAULT_ROLE);
    this.authorityDao = new HibernateGrantedAuthorityDao();
    this.authorityDao.setSessionFactory((SessionFactory) this.springContext
        .getBean("sessionFactory"));
  }

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    super.onTearDownAfterTransaction();
    this.defaultGrantedAuthority = null;
    this.authorityDao = null;
  }

  public void testSave() {
    verifyDataStoreIsEmpty();

    // save the default granted authority object using dao
    this.authorityDao.save(defaultGrantedAuthority);

    // verify data store contains saved data using direct jdbc retrieval (not
    // dao)
    List actualList = retrieveGrantedAuthorityListFromDb();
    assertEquals(1, actualList.size());

    Map actualGrantedAuthorityMap = (Map) actualList.get(0);
    // * NOTE* the keys in the map are all in UPPERCASE!
    String actualRole = (String) actualGrantedAuthorityMap.get("ROLE");
    assertEquals(DEFAULT_ROLE, actualRole);
  }

  public void testDelete() {
    verifyDataStoreIsEmpty();

    // save and delete the default granted authority object using dao
    this.authorityDao.save(this.defaultGrantedAuthority);
    this.authorityDao.delete(this.defaultGrantedAuthority);

    // * NOTE * must flush to test delete
    // see http://forum.springframework.org/showthread.php?t=18263 for
    // explanation
    this.authorityDao.getHibernateTemplate().flush();

    verifyDataStoreIsEmpty();
  }

  private void verifyDataStoreIsEmpty() {
    assertTrue(retrieveGrantedAuthorityListFromDb().isEmpty());
  }

  private List retrieveGrantedAuthorityListFromDb() {
    return this.jdbcTemplate.queryForList("select * from roles;",
        (Object[]) null);
  }
}
