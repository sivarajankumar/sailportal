/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.authentication.impl.HibernateGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.acegisecurity.GrantedAuthority;
import org.hibernate.SessionFactory;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDetailsDaoTest extends AbstractTransactionalDbTests {

  private static final String DEFAULT_ROLE_1 = "default_role_1";

  private static final String DEFAULT_ROLE_2 = "default_role_2";

  private static final String DEFAULT_ROLE_3 = "default_role_3";

  private static final String DEFAULT_USERNAME = "me";

  private static final String DEFAULT_PASSWORD = "my secret";

  private HibernateGrantedAuthority role1;

  private HibernateGrantedAuthority role2;

  private HibernateGrantedAuthority role3;

  private HibernateUserDetails defaultUserDetails;

  private HibernateGrantedAuthorityDao authorityDao;

  private HibernateUserDetailsDao userDetailsDao;

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.role1 = new HibernateGrantedAuthority();
    this.role2 = new HibernateGrantedAuthority();
    this.role3 = new HibernateGrantedAuthority();
    this.role1.setAuthority(DEFAULT_ROLE_1);
    this.role2.setAuthority(DEFAULT_ROLE_2);
    this.role3.setAuthority(DEFAULT_ROLE_3);
    SessionFactory sessionFactory = (SessionFactory) this.springContext
        .getBean("sessionFactory");
    this.authorityDao = new HibernateGrantedAuthorityDao();
    authorityDao.setSessionFactory(sessionFactory);
    this.userDetailsDao = new HibernateUserDetailsDao();
    this.userDetailsDao.setSessionFactory(sessionFactory);

    this.defaultUserDetails = new HibernateUserDetails();
    this.defaultUserDetails.setUsername(DEFAULT_USERNAME);
    this.defaultUserDetails.setPassword(DEFAULT_PASSWORD);
    this.defaultUserDetails.setAuthorities(new GrantedAuthority[] { this.role1,
        this.role2, this.role3 });
  }

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpInTransaction()
   */
  @Override
  protected void onSetUpInTransaction() throws Exception {
    super.onSetUpInTransaction();
    this.authorityDao.save(this.role1);
    this.authorityDao.save(this.role2);
    this.authorityDao.save(this.role3);
  }

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    super.onTearDownAfterTransaction();
    this.role1 = null;
    this.role2 = null;
    this.role3 = null;
    this.defaultUserDetails = null;
    this.authorityDao = null;
    this.userDetailsDao = null;
  }

  public void testSave() {
    verifyUserandJoinTablesAreEmpty();

    this.userDetailsDao.save(this.defaultUserDetails);
    // flush is required to cascade the join table for some reason
    this.userDetailsDao.getHibernateTemplate().flush();

    // verify data store contains saved data using direct jdbc retrieval (not
    // dao)
    assertEquals(1, retrieveUsersTableFromDb().size());
    assertEquals(3, retrieveUsersRolesTableFromDb().size());
    List actualList = retrieveUserDetailsListFromDb();
    assertEquals(3, actualList.size());

    List<String> defaultRolesList = new ArrayList<String>(3);
    defaultRolesList.add(DEFAULT_ROLE_1);
    defaultRolesList.add(DEFAULT_ROLE_2);
    defaultRolesList.add(DEFAULT_ROLE_3);

    for (int i = 0; i < actualList.size(); i++) {
      Map actualUserDetailsMap = (Map) actualList.get(i);
      // * NOTE* the keys in the map are all in UPPERCASE!
      String actualValue = (String) actualUserDetailsMap.get("USERNAME");
      assertEquals(DEFAULT_USERNAME, actualValue);
      actualValue = (String) actualUserDetailsMap.get("PASSWORD");
      assertEquals(DEFAULT_PASSWORD, actualValue);
      actualValue = (String) actualUserDetailsMap.get("ROLE");
      assertTrue(defaultRolesList.contains(actualValue));
      defaultRolesList.remove(actualValue);
    }
  }

   public void testDelete() {
     this.verifyUserandJoinTablesAreEmpty();

     this.userDetailsDao.save(this.defaultUserDetails);
     // flush is required to cascade the join table for some reason
     this.userDetailsDao.getHibernateTemplate().flush();

     this.userDetailsDao.delete(this.defaultUserDetails);
     this.userDetailsDao.getHibernateTemplate().flush();
     
     this.verifyUserandJoinTablesAreEmpty();
     
     List actualList = this.retrieveRolesTableFromDb();
     assertEquals(3, actualList.size());
     
     List<String> defaultRolesList = new ArrayList<String>(3);
     defaultRolesList.add(DEFAULT_ROLE_1);
     defaultRolesList.add(DEFAULT_ROLE_2);
     defaultRolesList.add(DEFAULT_ROLE_3);

     for (int i = 0; i < actualList.size(); i++) {
       Map actualRolesMap = (Map) actualList.get(i);
       // * NOTE* the keys in the map are all in UPPERCASE!
       String actualValue = (String) actualRolesMap.get("ROLE");
       assertTrue(defaultRolesList.contains(actualValue));
       defaultRolesList.remove(actualValue);
     }

   }

  private void verifyUserandJoinTablesAreEmpty() {
    assertTrue(this.retrieveUserDetailsListFromDb().isEmpty());
    assertTrue(this.retrieveUsersRolesTableFromDb().isEmpty());
  }

  private List retrieveUserDetailsListFromDb() {
    return this.jdbcTemplate
        .queryForList(
            "select * from users, users_roles, roles where users.id = users_roles.user_fk and roles.id = users_roles.role_fk;",
            (Object[]) null);
  }

  private List retrieveUsersTableFromDb() {
    return this.jdbcTemplate.queryForList("select * from users;",
        (Object[]) null);
  }
  
  private List retrieveRolesTableFromDb() {
    return this.jdbcTemplate.queryForList("select * from roles;",
        (Object[]) null);
  }

  private List retrieveUsersRolesTableFromDb() {
    return this.jdbcTemplate.queryForList("select * from users_roles;",
        (Object[]) null);
  }
}
