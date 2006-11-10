/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication;

import net.sf.sail.webapp.dao.SimpleDao;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface UserDetailsDao<T extends MutableUserDetails> extends
    SimpleDao<T> {

  /**
   * Given a username, returns a <code>UserDetails</code> object from the
   * persistent store or null if no such username exists.
   * 
   * @param username
   * @return <code>UserDetails</code> object or <code>null</code> if no such
   *         username exists.
   * @exception IncorrectResultSizeDataAccessException
   *              if more than one result object has been found.
   */
  public T retrieve(String username);
}