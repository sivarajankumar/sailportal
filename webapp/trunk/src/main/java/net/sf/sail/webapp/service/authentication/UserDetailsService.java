/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface UserDetailsService extends
    org.acegisecurity.userdetails.UserDetailsService {

  /**
   * Given a unique, non-null username, and any non-null password, creates a
   * MutableUserDetails object. If username is not unique or username or
   * password is null, throws UserCreationException
   * 
   * @param username
   * @param password
   * @throws UserCreationException
   */
  public void createUser(String username, String password)
      throws UserCreationException;

}