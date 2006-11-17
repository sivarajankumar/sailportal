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
package net.sf.sail.webapp.service.authentication;

import org.acegisecurity.GrantedAuthority;
import org.springframework.dao.DataAccessException;

import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;

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
	 * password are null, throws UserCreationException
	 * 
	 * @param username
	 * @param password
	 * @param email_address
	 *            Can be null or a valid email address (no checking is done).
	 * @throws UserCreationException
	 *             If username is not unique or username or password are null.
	 */
	public MutableUserDetails createUser(String username, String password,
			String email_address) throws UserCreationException;

	/**
	 * Given a string representing a role of a user, created a granted authority
	 * record in the data store
	 * 
	 * @param authority
	 * @return A MutableGrantedAuthority object
	 * @throws AuthorityCreationException
	 *             If authority is not unique or null.
	 */
	public MutableGrantedAuthority createGrantedAuthority(String authority)
			throws AuthorityCreationException;

	/**
	 * Given an authority string, loads an authority from the data store.
	 * 
	 * @param authority
	 * @return A MutableGrantedAuthority object
	 * @throws AuthorityNotFoundException
	 *             If authority is not in data store.
	 */
	public GrantedAuthority loadAuthorityByName(String authority)
			throws AuthorityNotFoundException, DataAccessException;

}