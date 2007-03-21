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
package net.sf.sail.webapp.domain.authentication;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * This interface extends Acegi Security's <code>UserDetails</code> and
 * provides mutator methods to the properties. <code>UserDetails</code>
 * represents user information.
 * 
 * @author Cynick Young
 * @author Laurel Williams
 * 
 * @version $Id:MutableUserDetails.java 159 2007-03-02 22:42:21Z cynick $
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

  /**
   * Sets the user's email address.
   * 
   * @param emailAddress
   */
  public void setEmailAddress(String emailAddress);

  /**
   * Gets the user's email address.
   * 
   * @return emailAddress
   */
  public String getEmailAddress();

  /**
   * Adds a GrantedAuthority to a user.
   * 
   * @param authority
   */
  public void addAuthority(GrantedAuthority authority);
}