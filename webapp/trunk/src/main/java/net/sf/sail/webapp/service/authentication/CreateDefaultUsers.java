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

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;

/**
 * A disposable class that is used to create default roles in the data store and
 * to create a default administrator account.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public interface CreateDefaultUsers {

	/**
	 * Given a MutableUserDetails object (with username and password set),
	 * creates a user with both UserDetailsService.USER_ROLE and
	 * UserDetailsService.ADMIN_ROLE authorities. These roles must be set
	 * already by using createRoles();
	 * 
	 * @param userDetails
	 *            A UserDetails object with the username and password set.
	 * @return A UserDetails object with username and password that were input
	 *         and with roles UserDetailsService.USER_ROLE and
	 *         UserDetailsService.ADMIN_ROLE authorities.
	 * @throws AuthorityNotFoundException
	 *             If the user or admin roles are not already loaded into the
	 *             granted authority table in data store.
	 * @throws DuplicateUsernameException
	 *             If the username is a duplicate of one already in the data
	 *             store.
	 * 
	 */
	public MutableUserDetails createAdministrator(MutableUserDetails userDetails)
			throws AuthorityNotFoundException, DuplicateUsernameException;

	/**
	 * Creates two default roles in the the data store authorities table. These
	 * are UserDetailsService.USER_ROLE and UserDetailsService.ADMIN_ROLE
	 * authorities. This method should be run before attempting to create users.
	 * 
	 * @throws DuplicateAuthorityException
	 *             If the authority is a duplicate of one already in the data
	 *             store.
	 * 
	 */

	public void createRoles() throws DuplicateAuthorityException;
}
