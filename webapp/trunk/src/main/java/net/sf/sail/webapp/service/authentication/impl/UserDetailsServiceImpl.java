/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserDetailsDao userDetailsDao;

  /**
   * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
   */
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    UserDetails userDetails = this.userDetailsDao.retrieve(username);
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
  public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
    this.userDetailsDao = userDetailsDao;
  }
}