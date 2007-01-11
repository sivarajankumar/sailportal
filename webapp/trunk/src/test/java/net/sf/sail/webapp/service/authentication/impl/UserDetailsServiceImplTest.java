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
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;

/**
 * @author Cynick Young
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImplTest extends AbstractTransactionalDbTests {

	private static final String USERNAME = "user name";

	private static final String USERNAME2 = "another name";

	private static final String EMAIL = "billy@bob.com";

	private static final String ROLE = "user";

	private static final String PASSWORD = "password";

	private MutableUserDetails expectedUserDetails;

	private UserDetailsService userDetailsService;

	private UserDetailsDao<MutableUserDetails> userDetailsDao;
	
	private GrantedAuthorityDao<MutableGrantedAuthority> authorityDao;

	/**
	 * @param userDetailsDao
	 *            the userDetailsDao to set
	 */
	public void setUserDetailsDao(
			UserDetailsDao<MutableUserDetails> userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @param userDetailsService
	 *            the userDetailsService to set
	 */
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
	 */
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		this.expectedUserDetails = (MutableUserDetails) this.applicationContext
				.getBean("mutableUserDetails");
		this.expectedUserDetails.setUsername(USERNAME);
		this.expectedUserDetails.setPassword(PASSWORD);
	}

	/**
	 * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
	 */
	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		this.userDetailsDao.save(this.expectedUserDetails);
	}

	public void testLoadUserByUsername() {

		final String unknownUser = "user not found";
		try {
			this.userDetailsService.loadUserByUsername(unknownUser);
			fail("should have caught UsernameNotFoundException");
		} catch (UsernameNotFoundException expected) {
		}
		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME);
		assertEquals(expectedUserDetails, actual);
	}

	public void testDuplicateUserErrors() throws Exception {
		MutableUserDetails user = this.userDetailsDao.createDataObject();
		user.setUsername(USERNAME2);
		user.setPassword(PASSWORD);
		user.setEmailAddress(EMAIL);

		// create 2 users and attempt to save to DB
		// second user should cause exception to be thown
		this.userDetailsService.createUser(user);
		try {
			this.userDetailsService.createUser(user);
			fail("DuplicateUsernameException expected and not caught.");
		} catch (DuplicateUsernameException e) {
		}

	}

	/*
	 * This test checks creation of a user within the portal, but ignores the creation 
	 * of a user on the remote SDS. Tests for system integration are beyond the scope
	 * of this testing mechanism.
	 */
	public void testCreateUserWithEmail() throws Exception {
		MutableGrantedAuthority expectedAuthority = this.authorityDao.createDataObject();
		expectedAuthority.setAuthority(UserDetailsService.USER_ROLE);
		this.authorityDao.save(expectedAuthority);
		
		MutableUserDetails user = this.userDetailsDao.createDataObject();
		user.setUsername(USERNAME2);
		user.setPassword(PASSWORD);
		user.setEmailAddress(EMAIL);

		// create user (saves automatically)
		UserDetails expectedUser = this.userDetailsService.createUser(user);

		// retrieve user and compare
		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME2);
		assertEquals(expectedUser, actual);

		// check role
		GrantedAuthority[] authorities = actual.getAuthorities();
		if (authorities == null) fail("authorities is null");
		boolean foundUserRole = false;
		for (int i = 0; i < authorities.length; i++) {
			if (authorities[i].getAuthority() == UserDetailsService.USER_ROLE) {
				foundUserRole = true;
				break;
			}
		}
		assertTrue(foundUserRole);

	}

	/*
	 * This test checks creation of a user within the portal, but ignores the creation 
	 * of a user on the remote SDS. Tests for system integration are beyond the scope
	 * of this testing mechanism.
	 */
	public void testCreateUserBlankEmail() throws Exception {
		MutableUserDetails user = this.userDetailsDao.createDataObject();
		user.setUsername(USERNAME2);
		user.setPassword(PASSWORD);
		UserDetails expectedUser = this.userDetailsService.createUser(user);

		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME2);
		assertEquals(expectedUser, actual);
	}

	public void testDuplicateAuthorityErrors() throws Exception {
		// create 2 authorities and attempt to save to DB
		// second authority should cause exception to be thown
		this.userDetailsService.createGrantedAuthority(ROLE);
		try {
			this.userDetailsService.createGrantedAuthority(ROLE);
			fail("DuplicateAuthorityException expected and not caught.");
		} catch (DuplicateAuthorityException e) {
		}

	}

	public void testCreateAuthority() throws Exception {
		GrantedAuthority expectedGrantedAuthority = this.userDetailsService
				.createGrantedAuthority(ROLE);

		GrantedAuthority actual = this.userDetailsService
				.loadAuthorityByName(ROLE);
		assertEquals(expectedGrantedAuthority, actual);
	}


	public GrantedAuthorityDao<MutableGrantedAuthority> getAuthorityDao() {
		return authorityDao;
	}

	public void setAuthorityDao(
			GrantedAuthorityDao<MutableGrantedAuthority> authorityDao) {
		this.authorityDao = authorityDao;
	}

}