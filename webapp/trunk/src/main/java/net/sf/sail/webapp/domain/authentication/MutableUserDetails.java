/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.domain.authentication;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * This interface extends Acegi Security's <code>UserDetails</code> and
 * provides mutator methods to the properties. <code>UserDetails</code>
 * represents user information.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * @see org.acegisecurity.userdetails.UserDetails
 * 
 */
public interface MutableUserDetails extends UserDetails {

  /**
   * Sets an array of <code>GrantedAuthority</code> for this user. A
   * <code>GrantedAuthority</code> represents a role that can be given
   * specific access permissions. An example could be Admin, User, Manager, and
   * BankTeller roles.
   * 
   * @param authorities
   * @see org.acegisecurity.GrantedAuthority
   */
  public void setAuthorities(GrantedAuthority[] authorities);

  /**
   * Sets the user's password. This may or may not be plaintext. It will be up
   * to the implementor to decide if encryption is required. If encryption is
   * used, it must be representable as a <code>String</code>.
   * 
   * @param password
   */
  public void setPassword(String password);

  /**
   * Sets the user's name.
   * 
   * @param username
   */
  public void setUsername(String username);
}
