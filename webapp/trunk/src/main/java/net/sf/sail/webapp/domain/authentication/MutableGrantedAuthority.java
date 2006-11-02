/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.domain.authentication;

import org.acegisecurity.GrantedAuthority;

/**
 * This interface extends Acegi Security's <code>GrantedAuthority</code> and
 * provides mutator methods to the properties. <code>GrantedAuthority</code>
 * represents a role that can be given specific access permissions. An example
 * could be Admin, User, Manager, and Bank Teller roles.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * @see org.acegisecurity.GrantedAuthority
 */
public interface MutableGrantedAuthority extends GrantedAuthority {

  /**
   * Sets the name of the role of this authority.
   * 
   * @param role
   */
  public void setAuthority(String role);
}
