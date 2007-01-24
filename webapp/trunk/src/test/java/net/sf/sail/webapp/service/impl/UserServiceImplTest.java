/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.service.impl;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class UserServiceImplTest extends AbstractTransactionalDbTests {

  private static final String USERNAME = "another name";

  private static final String EMAIL = "billy@bob.com";

  private static final String PASSWORD = "password";

  private UserDetailsDao<MutableUserDetails> userDetailsDao;

  private GrantedAuthorityDao<MutableGrantedAuthority> authorityDao;

  private UserDetailsService userDetailsService;

  private UserService userService;

  public void testDuplicateUserErrors() throws Exception {
    MutableUserDetails user = this.userDetailsDao.createDataObject();
    user.setUsername(USERNAME);
    user.setPassword(PASSWORD);
    user.setEmailAddress(EMAIL);

    // create 2 users and attempt to save to DB
    // second user should cause exception to be thown
    this.userService.createUser(user);
    try {
      this.userService.createUser(user);
      fail("DuplicateUsernameException expected and not caught.");
    }
    catch (DuplicateUsernameException e) {
    }

  }

  /*
   * This test checks creation of a user within the portal, but ignores the
   * creation of a user on the remote SDS. Tests for system integration are
   * beyond the scope of this testing mechanism.
   */
  public void testCreateUserWithEmail() throws Exception {
    MutableGrantedAuthority expectedAuthority = this.authorityDao
        .createDataObject();
    expectedAuthority.setAuthority(UserDetailsService.USER_ROLE);
    this.authorityDao.save(expectedAuthority);

    MutableUserDetails user = this.userDetailsDao.createDataObject();
    user.setUsername(USERNAME);
    user.setPassword(PASSWORD);
    user.setEmailAddress(EMAIL);

    // create user (saves automatically)
    UserDetails expectedUser = this.userService.createUser(user);

    // retrieve user and compare
    UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
    assertEquals(expectedUser, actual);

    // check role
    GrantedAuthority[] authorities = actual.getAuthorities();
    if (authorities == null)
      fail("authorities is null");
    boolean foundUserRole = false;
    for (int i = 0; i < authorities.length; i++) {
      if (authorities[i].getAuthority() == UserDetailsService.USER_ROLE) {
        foundUserRole = true;
        break;
      }
    }
    assertTrue(foundUserRole);

  }

  /*
   * This test checks creation of a user within the portal, but ignores the
   * creation of a user on the remote SDS. Tests for system integration are
   * beyond the scope of this testing mechanism.
   */
  public void testCreateUserBlankEmail() throws Exception {
    MutableUserDetails user = this.userDetailsDao.createDataObject();
    user.setUsername(USERNAME);
    user.setPassword(PASSWORD);
    UserDetails expectedUser = this.userService.createUser(user);

    UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
    assertEquals(expectedUser, actual);
  }

  /**
   * @param authorityDao
   *          the authorityDao to set
   */
  public void setAuthorityDao(
      GrantedAuthorityDao<MutableGrantedAuthority> authorityDao) {
    this.authorityDao = authorityDao;
  }

  /**
   * @param userDetailsDao
   *          the userDetailsDao to set
   */
  public void setUserDetailsDao(
      UserDetailsDao<MutableUserDetails> userDetailsDao) {
    this.userDetailsDao = userDetailsDao;
  }

  /**
   * @param userDetailsService
   *          the userDetailsService to set
   */
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * @param userService
   *          the userService to set
   */
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}