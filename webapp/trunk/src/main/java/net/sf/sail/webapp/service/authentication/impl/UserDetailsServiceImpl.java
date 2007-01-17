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
import net.sf.sail.webapp.dao.sds.impl.SdsUserCreateCommandHttpRestImpl;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.domain.webservice.http.impl.HttpRestTransportImpl;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

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
	 *            The granted authority to set.
	 */
	public void setGrantedAuthorityDao(
			GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao) {
		this.grantedAuthorityDao = grantedAuthorityDao;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
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
	 *            The userDetailsDao to set.
	 */
	public void setUserDetailsDao(
			UserDetailsDao<MutableUserDetails> userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 */
	@Transactional(rollbackFor = { DuplicateUsernameException.class,
			BadRequestException.class, NetworkTransportException.class })
	public MutableUserDetails createUser(MutableUserDetails userDetails)
			throws DuplicateUsernameException, BadRequestException,
			NetworkTransportException {

		this.checkUserCreationErrors(userDetails.getUsername());

		//**hack: first name and last name required by SDS**//
		userDetails.setFirstName(userDetails.getUsername());
		userDetails.setLastName(userDetails.getUsername());
		SdsUserCreateCommandHttpRestImpl command = prepareSDSUserCreateCommand(userDetails);
		Integer SDSUserId = command.execute();

		//TODO store the SDS user Id in local data store and save in user information
		
		assignRole(userDetails, USER_ROLE);
		this.userDetailsDao.save(userDetails);
		return userDetails;
	}

	private void assignRole(MutableUserDetails userDetails, String role) {
		GrantedAuthority authority = this.grantedAuthorityDao
				.retrieveByName(role);
		userDetails.addAuthority(authority);
	}

	// TODO portal id and base url should not be hard coded
	private SdsUserCreateCommandHttpRestImpl prepareSDSUserCreateCommand(
			MutableUserDetails userDetails) {
		SdsUserCreateCommandHttpRestImpl command = new SdsUserCreateCommandHttpRestImpl();
		command.setTransport(new HttpRestTransportImpl());

		// portal id must already be known in advance
		// (1 is the SDS test portal)
		command.setPortalId(1);

		// http://rails.dev.concord.org/sds/
		command.setBaseUrl("http://rails.dev.concord.org/sds/");
		command.generateRequest(userDetails);
		return command;
	}

	/**
	 * Validates user input checks that the data store does not already contain
	 * a user with the same username
	 * 
	 * @param username
	 *            The username to check for in the data store
	 * @throws DuplicateUsernameException
	 *             if the username is the same as a username already in data
	 *             store.
	 */
	private void checkUserCreationErrors(String username)
			throws DuplicateUsernameException {
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
	@Transactional(rollbackFor = { DuplicateAuthorityException.class })
	public MutableGrantedAuthority createGrantedAuthority(String authority)
			throws DuplicateAuthorityException {
		this.checkNoAuthorityCreationErrors(authority);

		MutableGrantedAuthority grantedAuthority = this.grantedAuthorityDao
				.createDataObject();
		grantedAuthority.setAuthority(authority);
		this.grantedAuthorityDao.save(grantedAuthority);
		return grantedAuthority;
	}

	/**
	 * Validates user input checks that the data store does not already contain
	 * an authority with the same name
	 * 
	 * @param authority
	 *            The authority to be checked for in the data store.
	 * @throws DuplicateAuthorityException
	 *             If the authority is the same as an authority already in data
	 *             store.
	 */
	private void checkNoAuthorityCreationErrors(String authority)
			throws DuplicateAuthorityException {

		try {
			@SuppressWarnings("unused")
			GrantedAuthority grantedAuthority = (MutableGrantedAuthority) this
					.loadAuthorityByName(authority);
		} catch (AuthorityNotFoundException e) {
			return;
		}
		throw new DuplicateAuthorityException(authority);
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#loadAuthorityByName(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public GrantedAuthority loadAuthorityByName(String authority)
			throws AuthorityNotFoundException {
		GrantedAuthority grantedAuthority = this.grantedAuthorityDao
				.retrieveByName(authority);
		if (grantedAuthority == null) {
			throw new AuthorityNotFoundException(authority);
		}
		return grantedAuthority;
	}
}