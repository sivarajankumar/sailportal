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

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.CreateDefaultUsers;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;


/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CreateDefaultUsersImpl implements CreateDefaultUsers {

	private UserDetailsService userService = null;

	/**
	 * Sets the UserDetailsService.
	 * 
	 * @param userService
	 */
	public void setUserService(UserDetailsService userService) {
		this.userService = userService;
	}
	
	/**
	 * @see net.sf.sail.webapp.service.authentication.CreateDefaultUsers#createAdministrator(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 */
	public MutableUserDetails createAdministrator(MutableUserDetails userDetails)
			throws DuplicateUsernameException, AuthorityNotFoundException {
		GrantedAuthority authority = userService
				.loadAuthorityByName(UserDetailsService.ADMIN_ROLE);
		userDetails.addAuthority(authority);
		return userService.createUser(userDetails);
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.CreateDefaultUsers#createRoles()
	 */
	public void createRoles() throws DuplicateAuthorityException {
		this.userService.createGrantedAuthority(UserDetailsService.ADMIN_ROLE);
		this.userService.createGrantedAuthority(UserDetailsService.USER_ROLE);
	}

}
