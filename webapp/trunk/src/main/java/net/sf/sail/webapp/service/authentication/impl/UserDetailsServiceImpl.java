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

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.authentication.AuthorityCreationException;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.NullAuthorityException;
import net.sf.sail.webapp.service.authentication.NullPasswordException;
import net.sf.sail.webapp.service.authentication.NullUsernameException;
import net.sf.sail.webapp.service.authentication.UserCreationException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
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

	private GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao;

	/**
	 * @param grantedAuthorityDao
	 *            The granted authority to set
	 */
	public void setGrantedAuthorityDao(
			GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao) {
		this.grantedAuthorityDao = grantedAuthorityDao;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = this.userDetailsDao.retrieveByName(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("Username: " + username
					+ " not found.");
		}
		return userDetails;
	}

	/**
	 * @param userDetailsDao
	 *            the userDetailsDao to set
	 */
	public void setUserDetailsDao(
			UserDetailsDao<MutableUserDetails> userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 * In addition, sets the user role to UserDetailsService.USER_ROLE
	 */
	public MutableUserDetails createUser(MutableUserDetails userDetails) throws UserCreationException {
		this.checkUserCreationErrors(userDetails.getUsername(), userDetails.getPassword());

		GrantedAuthority authority = this.grantedAuthorityDao.retrieveByName(USER_ROLE);
		userDetails.addAuthority(authority);
		
		this.userDetailsDao.save(userDetails);
		return userDetails;
	}

	/**
	 * Validates user input - checking for non null username and password. Also
	 * checks that the data store does not already contain a user with the same
	 * username
	 * 
	 * @param username
	 * @param password
	 * @throws UserCreationException - NullUsernameException if
	 *         the username is null, NullPasswordException if the password is
	 *         null, and DuplicateUsernameException if the username is the same
	 *         as a username already in data store.
	 */
	private void checkUserCreationErrors(String username, String password)
			throws UserCreationException {
		if (username == null)
			throw new NullUsernameException();
		if (password == null)
			throw new NullPasswordException();

		try {
			@SuppressWarnings("unused")
			UserDetails uniqueUserDetails = (MutableUserDetails) this
					.loadUserByUsername(username);
		} catch (UsernameNotFoundException unfe) {
			return;
		}
		throw new DuplicateUsernameException(username);
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createGrantedAuthority(java.lang.String)
	 */
	public MutableGrantedAuthority createGrantedAuthority(String authority)
			throws AuthorityCreationException {
		this.checkNoAuthorityCreationErrors(authority);
		
		MutableGrantedAuthority grantedAuthority = this.grantedAuthorityDao
				.createDataObject();
		grantedAuthority.setAuthority(authority);
		this.grantedAuthorityDao.save(grantedAuthority);
		return grantedAuthority;
	}

	/**
	 * Validates user input - checking for non null authority. Also
	 * checks that the data store does not already contain an authority with the same
	 * name
	 * 
	 * @param authority
	 * @throws AuthorityCreationException NullAuthorityException if
	 *         the username is null and DuplicateAuthorityException if the authority is the same
	 *         as an authority already in data store. 
	 */
	private void checkNoAuthorityCreationErrors(String authority)
			throws AuthorityCreationException {
		if (authority == null)
			throw new NullAuthorityException();

		try {
			@SuppressWarnings("unused")
			GrantedAuthority grantedAuthority = (MutableGrantedAuthority) this.loadAuthorityByName(authority);
		} catch (AuthorityNotFoundException e) {
			return;
		}
		throw new DuplicateAuthorityException(authority);
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#loadAuthorityByName(java.lang.String)
	 */
	public GrantedAuthority loadAuthorityByName(String authority) throws AuthorityNotFoundException {
		GrantedAuthority grantedAuthority = this.grantedAuthorityDao.retrieveByName(authority);
		if (grantedAuthority == null) {
			throw new AuthorityNotFoundException(authority);
		}
		return grantedAuthority;
	}
}