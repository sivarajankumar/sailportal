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
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.NullPasswordException;
import net.sf.sail.webapp.service.authentication.NullUsernameException;
import net.sf.sail.webapp.service.authentication.UserCreationException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * A class to provide services for MutableUserDetails objects.
 * 
 * @author Cynick Young
 * @author Laurel Willliams
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserDetailsDao<MutableUserDetails> userDetailsDao;

  /**
   * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
   */
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    UserDetails userDetails = this.userDetailsDao.retrieveByUsername(username);
    if (userDetails == null) {
      throw new UsernameNotFoundException("Username: " + username
          + " not found.");
    }
    return userDetails;
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
   * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(java.lang.String,
   *      java.lang.String)
   */
  public void createUser(String username, String password)
      throws UserCreationException {
    MutableUserDetails userDetails = validateAndCreateUserDetails(username,
        password);
    this.userDetailsDao.save(userDetails);
  }

  /**
   * Creates a user, with optional email address
   * 
   * @see
   * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(java.lang.String,
   *      java.lang.String)
   * 
   * @param username
   * @param password
   * @param emailAddress
   * @throws UserCreationException
   */
  public void createUser(String username, String password, String emailAddress)
      throws UserCreationException {
    MutableUserDetails userDetails = validateAndCreateUserDetails(username,
        password);
    userDetails.setEmailAddress(emailAddress);
    this.userDetailsDao.save(userDetails);
  }

  /**
   * Validates user input by calling the private method isNoCreationErrors Then
   * creates a MutableUserDetails object with the username and password set.
   * 
   * @param username
   * @param password
   * @return An instance of MutableUserDetails with username and password set.
   * @throws UserCreationException
   */
  private MutableUserDetails validateAndCreateUserDetails(String username,
      String password) throws UserCreationException {
    // validate inputs
    this.isNoCreationErrors(username, password);

    // create user details with username and password
    MutableUserDetails userDetails = this.userDetailsDao.createDataObject();
    userDetails.setUsername(username);
    userDetails.setPassword(password);
    return userDetails;
  }

  /**
   * Validates user input - checking for non null username and password. Also
   * checks that the data store does not already contain a user with the same
   * username
   * 
   * @param username
   * @param password
   * @return The appropriate UserCreationException. NullUsernameException if the
   *         username is null, NullPasswordException if the password is null,
   *         and DuplicateUsernameException if the username is the same as a
   *         username already in data store. Returns null if no exceptions are
   *         found.
   */
  private void isNoCreationErrors(String username, String password)
      throws UserCreationException {
    if (username == null)
      throw new NullUsernameException();
    if (password == null)
      throw new NullPasswordException();

    try {
      @SuppressWarnings("unused")
      UserDetails uniqueUserDetails = (MutableUserDetails) this
          .loadUserByUsername(username);
    }
    catch (UsernameNotFoundException unfe) {
      return;
    }
    throw new DuplicateUsernameException(username);
  }
}