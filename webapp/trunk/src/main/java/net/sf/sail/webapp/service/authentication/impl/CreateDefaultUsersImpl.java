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
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.CreateDefaultUsers;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A disposable class that is used to create default roles in the data store and
 * to create a default administrator account.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CreateDefaultUsersImpl implements CreateDefaultUsers {

  private static final String[] CONFIG_LOCATIONS = new String[] {
      "classpath:applicationContext-datasource.xml",
      "classpath:applicationContext-hibernate.xml",
      "classpath:applicationContext-security.xml" };

  private UserDetailsService userDetailsService = null;

  private UserDetailsDao<MutableUserDetails> userDetailsDao = null;

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out
          .println("Usage: CreateDefaultUsersImpl <admin-username> <admin-password>");
      System.exit(1);
    }
    AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext(
        CONFIG_LOCATIONS);
    try {
      CreateDefaultUsersImpl createDefaultUsers = new CreateDefaultUsersImpl();
      createDefaultUsers.init(context);
      MutableUserDetails adminUser = createDefaultUsers.userDetailsDao
          .createDataObject();
      adminUser.setUsername(args[0]);
      adminUser.setPassword(args[1]);
      createDefaultUsers.createRoles();
      createDefaultUsers.createAdministrator(adminUser);
    }
    finally {
      context.close();
    }
    System.exit(0);
  }

  @SuppressWarnings("unchecked")
  private void init(ApplicationContext context) {
    this.setUserDetailsService((UserDetailsService) context
        .getBean("userDetailsService"));
    this.setUserDetailsDao((UserDetailsDao) context.getBean("userDetailsDao"));
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
   * Sets the UserDetailsService.
   * 
   * @param userDetailsService
   */
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * Given a MutableUserDetails object (with username and password set), creates
   * a user with both UserDetailsService.USER_ROLE and
   * UserDetailsService.ADMIN_ROLE authorities. These roles must be set already
   * by using createRoles();
   * 
   * @param userDetails
   *          A UserDetails object with the username and password set.
   * @return A UserDetails object with username and password that were input and
   *         with roles UserDetailsService.USER_ROLE and
   *         UserDetailsService.ADMIN_ROLE authorities.
   * @throws AuthorityNotFoundException
   *           If the user or admin roles are not already loaded into the
   *           granted authority table in data store.
   * @throws UserCreationException
   *           If the user cannot be created (duplicate user name, null username
   *           or null password)
   */
  public MutableUserDetails createAdministrator(MutableUserDetails userDetails)
      throws AuthorityNotFoundException, DuplicateUsernameException {
    GrantedAuthority authority = userDetailsService
        .loadAuthorityByName(UserDetailsService.ADMIN_ROLE);
    userDetails.addAuthority(authority);
    return userDetailsService.createUser(userDetails);
  }

  /**
   * Creates two default roles in the the data store authorities table. These
   * are UserDetailsService.USER_ROLE and UserDetailsService.ADMIN_ROLE
   * authorities. This method should be run before attempting to create users.
   * 
   * @throws AuthorityCreationException
   *           If the authority passed in is null or cannot be created for some
   *           reason.
   */
  public void createRoles() throws DuplicateAuthorityException {
    this.userDetailsService
        .createGrantedAuthority(UserDetailsService.ADMIN_ROLE);
    this.userDetailsService
        .createGrantedAuthority(UserDetailsService.USER_ROLE);
  }
}