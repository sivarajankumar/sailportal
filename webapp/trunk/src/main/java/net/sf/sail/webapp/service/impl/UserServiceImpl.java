/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.service.impl;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.dao.sds.SdsUserDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation class that uses daos to interact with the data store.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class UserServiceImpl implements UserService {

	private UserDetailsDao<MutableUserDetails> userDetailsDao;

	private GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao;

	private SdsUserDao sdsUserDao;

	private UserDao<User> userDao;

	private PasswordEncoder passwordEncoder;

	private SaltSource saltSource;

	/**
	 * @param sdsUserDao
	 *            the sdsUserDao to set
	 */
	@Required
	public void setSdsUserDao(SdsUserDao sdsUserDao) {
		this.sdsUserDao = sdsUserDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	@Required
	public void setUserDao(final UserDao<User> userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param grantedAuthorityDao
	 *            the grantedAuthorityDao to set
	 */
	@Required
	public void setGrantedAuthorityDao(
			final GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao) {
		this.grantedAuthorityDao = grantedAuthorityDao;
	}

	/**
	 * @param userDetailsDao
	 *            the userDetailsDao to set
	 */
	@Required
	public void setUserDetailsDao(
			final UserDetailsDao<MutableUserDetails> userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @see net.sf.sail.webapp.service.UserService#retrieveUser(org.acegisecurity.userdetails.UserDetails)
	 */
	@Transactional(readOnly = true)
	public User retrieveUser(UserDetails userDetails) {
		return this.userDao.retrieveByUserDetails(userDetails);
	}

	/**
     * @see interface
     */
	public User updateUser(final User user)
			throws BadRequestException, NetworkTransportException {

		try {
			this.encodePassword(user.getUserDetails());

			
			user.getSdsUser().setFirstName(user.getUserDetails().getUsername());
			user.getSdsUser().setLastName(user.getUserDetails().getUsername());
			
			this.userDao.save(user);

			return user;
		} catch (BadRequestException e) {
			throw e;
		} catch (NetworkTransportException e) {
			throw e;
		}
	}


	/**
	 * @see net.sf.sail.webapp.service.UserService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 */
	@Transactional(rollbackFor = { DuplicateUsernameException.class,
			BadRequestException.class, NetworkTransportException.class })
	public User createUser(final MutableUserDetails userDetails)
			throws DuplicateUsernameException, BadRequestException,
			NetworkTransportException {

		try {
			this.checkUserCreationErrors(userDetails.getUsername());
			this.assignRole(userDetails, UserDetailsService.USER_ROLE);
			this.encodePassword(userDetails);

			SdsUser sdsUser = new SdsUser();
			sdsUser.setFirstName(userDetails.getUsername());
			sdsUser.setLastName(userDetails.getUsername());
			this.sdsUserDao.save(sdsUser);

			User user = new UserImpl();
			user.setSdsUser(sdsUser);
			user.setUserDetails(userDetails);
			this.userDao.save(user);

			return user;
		} catch (BadRequestException e) {
			throw e;
		} catch (DuplicateUsernameException e) {
			throw e;
		} catch (NetworkTransportException e) {
			throw e;
		}
	}

	protected void encodePassword(MutableUserDetails userDetails) {
		userDetails.setPassword(this.passwordEncoder.encodePassword(userDetails
				.getPassword(), this.saltSource.getSalt(userDetails)));
	}

	private void assignRole(final MutableUserDetails userDetails,
			final String role) {
		GrantedAuthority authority = this.grantedAuthorityDao
				.retrieveByName(role);
		userDetails.addAuthority(authority);
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
	private void checkUserCreationErrors(final String username)
			throws DuplicateUsernameException {
		if (this.userDetailsDao.hasUsername(username)) {
			throw new DuplicateUsernameException(username);
		}
	}

	/**
	 * @param passwordEncoder
	 *            the passwordEncoder to set
	 */
	@Required
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * @param saltSource
	 *            the saltSource to set
	 */
	@Required
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}
}