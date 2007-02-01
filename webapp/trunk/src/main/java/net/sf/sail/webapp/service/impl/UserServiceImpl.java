/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
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

import net.sf.sail.webapp.dao.UserDao;
import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.dao.sds.SdsCommand;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation class that uses daos to interact with the data store.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class UserServiceImpl implements UserService {

  private UserDetailsDao<MutableUserDetails> userDetailsDao;

  private GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao;

  private UserDao<User> userDao;

  /**
   * @param userDao
   *          the userDao to set
   */
  public void setUserDao(final UserDao<User> userDao) {
    this.userDao = userDao;
  }

  /**
   * @param grantedAuthorityDao
   *          the grantedAuthorityDao to set
   */
  public void setGrantedAuthorityDao(
      final GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao) {
    this.grantedAuthorityDao = grantedAuthorityDao;
  }

  /**
   * @param userDetailsDao
   *          the userDetailsDao to set
   */
  public void setUserDetailsDao(
      final UserDetailsDao<MutableUserDetails> userDetailsDao) {
    this.userDetailsDao = userDetailsDao;
  }

  /**
   * @see net.sf.sail.webapp.service.UserService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
   */
  @Transactional(rollbackFor = { DuplicateUsernameException.class,
      BadRequestException.class, NetworkTransportException.class })
  public User createUser(final ApplicationContext applicationContext,
      final MutableUserDetails userDetails) throws DuplicateUsernameException,
      BadRequestException, NetworkTransportException {

    try {
      this.checkUserCreationErrors(userDetails.getUsername());

      this.assignRole(userDetails, UserDetailsService.USER_ROLE);

      SdsUser sdsUser = this.requestNewSdsUser(applicationContext, userDetails);

      User user = (User) applicationContext.getBean("user");
      user.setSdsUser(sdsUser);
      user.setUserDetails(userDetails);
      this.userDao.save(user);

      return user;
    }
    catch (BadRequestException e) {
      throw e;
    }
    catch (DuplicateUsernameException e) {
      throw e;
    }
    catch (NetworkTransportException e) {
      throw e;
    }
  }

  @SuppressWarnings("unchecked")
  private SdsUser requestNewSdsUser(ApplicationContext applicationContext,
      MutableUserDetails userDetails) {
    // **hack: first name and last name required by SDS**//
    SdsUser sdsUser = (SdsUser) applicationContext.getBean("sdsUser");
    sdsUser.setFirstName(userDetails.getUsername());
    sdsUser.setLastName(userDetails.getUsername());

    SdsCommand<SdsUser, SdsUser> command = (SdsCommand) applicationContext
        .getBean("sdsUserCreateCommand");
    command.generateRequest(sdsUser);
    return command.execute(sdsUser);
  }

  private void assignRole(final MutableUserDetails userDetails,
      final String role) {
    GrantedAuthority authority = this.grantedAuthorityDao.retrieveByName(role);
    userDetails.addAuthority(authority);
  }

  /**
   * Validates user input checks that the data store does not already contain a
   * user with the same username
   * 
   * @param username
   *          The username to check for in the data store
   * @throws DuplicateUsernameException
   *           if the username is the same as a username already in data store.
   */
  private void checkUserCreationErrors(final String username)
      throws DuplicateUsernameException {
    if (this.userDetailsDao.hasUsername(username)) {
      throw new DuplicateUsernameException(username);
    }
  }
}