/**
 * Copyright Encore Research Group University of Toronto 2006 (c)
 * LGPL
 */
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.impl.HibernateUserDetailsDao;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImplTest extends AbstractTransactionalDbTests {

  private static final String USERNAME = "user name";

  private static final String PASSWORD = "password";

  private HibernateUserDetails expectedUserDetails;

  private UserDetailsService userDetailsService;

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.expectedUserDetails = (HibernateUserDetails) this.springContext
        .getBean("mutableUserDetails");
    this.expectedUserDetails.setUsername(USERNAME);
    this.expectedUserDetails.setPassword(PASSWORD);
    this.userDetailsService = (UserDetailsService) this.springContext
        .getBean("userDetailsService");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
   */
  @Override
  protected void onSetUpInTransaction() throws Exception {
    super.onSetUpInTransaction();
    HibernateUserDetailsDao userDetailsDao = (HibernateUserDetailsDao) this.springContext
        .getBean("userDetailsDao");
    userDetailsDao.save(this.expectedUserDetails);
  }

  public void testLoadUserByUsername() {
    final String unknownUser = "user not found";
    try {
      this.userDetailsService.loadUserByUsername(unknownUser);
      fail("should have caught UsernameNotFoundException");
    }
    catch (UsernameNotFoundException expected) {
    }
    UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
    assertEquals(expectedUserDetails, actual);
  }
}