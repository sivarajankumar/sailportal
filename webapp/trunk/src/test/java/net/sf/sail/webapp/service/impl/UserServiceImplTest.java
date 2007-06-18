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
package net.sf.sail.webapp.service.impl;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.dao.SaltSource;
import org.acegisecurity.providers.encoding.PasswordEncoder;
import org.acegisecurity.userdetails.UserDetails;
import org.easymock.EasyMock;
import org.telscenter.sail.webapp.presentation.web.controllers.LostPasswordTeacherController;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class UserServiceImplTest extends AbstractTransactionalDbTests {

	private static final String USERNAME = "another name";

	private static final String EMAIL = "billy@bob.com";

	private static final String PASSWORD = "password";

	private GrantedAuthorityDao<MutableGrantedAuthority> authorityDao;

	private UserDao<User> userDao;

	private UserDetailsService userDetailsService;

	private UserService userService;

	MutableGrantedAuthority expectedAuthorityCreate;

	MutableUserDetails userDetailsCreate;

	@SuppressWarnings("unchecked")
	public void testRetrieveUser() {
		MutableUserDetails userDetails = (MutableUserDetails) this.applicationContext
				.getBean("mutableUserDetails");
		UserDao<User> mockUserDao = EasyMock.createMock(UserDao.class);
		EasyMock.expect(mockUserDao.retrieveByUserDetails(userDetails))
				.andReturn(new UserImpl());
		EasyMock.replay(mockUserDao);

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserDao(mockUserDao);
		userServiceImpl.retrieveUser(userDetails);
		EasyMock.verify(mockUserDao);
	}

	public void testUpdateUser() throws Exception {
		setupCreateTest();

		// create user (saves automatically)
		User expectedUser = this.userService.createUser(userDetailsCreate);
		UserDetails expectedUserDetails = expectedUser.getUserDetails();

		// retrieve user and compare
		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME);
		assertEquals(expectedUserDetails, actual);

		checkPasswordEncoding(actual);
		checkRole(actual);
		
		PersistentUserDetails actualUserDetails = (PersistentUserDetails) actual;
		
		String newPassword = LostPasswordTeacherController.generateRandomPassword();
		actualUserDetails.setPassword(newPassword);
		
		expectedUser.setUserDetails(actualUserDetails);
		
		this.userService.updateUser(expectedUser);
		
		// retrieve user and compare
		UserDetails updatedUser = this.userDetailsService
				.loadUserByUsername(USERNAME);
		
		User retrievedUserDetails = this.userService.retrieveUser(expectedUser.getUserDetails());
		assertEquals(updatedUser.getPassword(), retrievedUserDetails.getUserDetails().getPassword());
		
		// added this end transaction to catch a transaction commit within a
		// transaction rollback problem
		this.userDao.delete(expectedUser);
		this.authorityDao.delete(expectedAuthorityCreate);
		this.setComplete();
		this.endTransaction();

	}

	public void testDuplicateUserErrors() throws Exception {
		MutableUserDetails user = (MutableUserDetails) this.applicationContext
				.getBean("mutableUserDetails");
		user.setUsername(USERNAME);
		user.setPassword(PASSWORD);
		user.setEmailAddress(EMAIL);

		// create 2 users and attempt to save to DB
		// second user should cause exception to be thrown
		this.userService.createUser(user);
		try {
			this.userService.createUser(user);
			fail("DuplicateUsernameException expected and not caught.");
		} catch (DuplicateUsernameException e) {
		}

	}

	private void setupCreateTest() {
		expectedAuthorityCreate = (MutableGrantedAuthority) this.applicationContext
				.getBean("mutableGrantedAuthority");
		expectedAuthorityCreate.setAuthority(UserDetailsService.USER_ROLE);
		this.authorityDao.save(expectedAuthorityCreate);

		userDetailsCreate = (MutableUserDetails) this.applicationContext
				.getBean("mutableUserDetails");
		userDetailsCreate.setUsername(USERNAME);
		userDetailsCreate.setPassword(PASSWORD);
	}

	/*
	 * This test checks creation of a user within the portal, but ignores the
	 * creation of a user on the remote SDS. Tests for system integration are
	 * beyond the scope of this testing mechanism. We are assuming the SdsUserId
	 * cannot be null, enforced by the data store constraint.
	 */
	public void testCreateUserWithEmail() throws Exception {
		setupCreateTest();

		// create user (saves automatically)
		User expectedUser = this.userService.createUser(userDetailsCreate);
		UserDetails expectedUserDetails = expectedUser.getUserDetails();

		// retrieve user and compare
		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME);
		assertEquals(expectedUserDetails, actual);

		checkPasswordEncoding(actual);
		checkRole(actual);

		// added this end transaction to catch a transaction commit within a
		// transaction rollback problem
		this.userDao.delete(expectedUser);
		this.authorityDao.delete(expectedAuthorityCreate);
		this.setComplete();
		this.endTransaction();
	}


	private void checkPasswordEncoding(UserDetails actual) {
		// check password encoding
		assertFalse(PASSWORD.equals(actual.getPassword()));
		PasswordEncoder passwordEncoder = (PasswordEncoder) this.applicationContext
				.getBean("passwordEncoder");
		SaltSource saltSource = (SaltSource) this.applicationContext
				.getBean("systemSaltSource");
		String encodedPassword = passwordEncoder.encodePassword(PASSWORD,
				saltSource.getSalt(userDetailsCreate));
		assertEquals(encodedPassword, actual.getPassword());
	}

	private void checkRole(UserDetails actual) {
		// check role
		GrantedAuthority[] authorities = actual.getAuthorities();
		if (authorities == null)
			fail("authorities is null");
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
	 * This test checks creation of a user within the portal, but ignores the
	 * creation of a user on the remote SDS. Tests for system integration are
	 * beyond the scope of this testing mechanism. We are assuming the SdsUserId
	 * cannot be null, enforced by the data store constraint.
	 */
	public void testCreateUserBlankEmail() throws Exception {
		setupCreateTest();
		userDetailsCreate.setEmailAddress(EMAIL);

		// create user (saves automatically)
		User expectedUser = this.userService.createUser(userDetailsCreate);
		UserDetails expectedUserDetails = expectedUser.getUserDetails();

		// retrieve user and compare
		UserDetails actual = this.userDetailsService
				.loadUserByUsername(USERNAME);
		assertEquals(expectedUserDetails, actual);

		checkPasswordEncoding(actual);
		checkRole(actual);

		// added this end transaction to catch a transaction commit within a
		// transaction rollback problem
		this.userDao.delete(expectedUser);
		this.authorityDao.delete(expectedAuthorityCreate);
		this.setComplete();
		this.endTransaction();
	}

	/**
	 * @param authorityDao
	 *            the authorityDao to set
	 */
	public void setAuthorityDao(
			GrantedAuthorityDao<MutableGrantedAuthority> authorityDao) {
		this.authorityDao = authorityDao;
	}

	/**
	 * @param userDetailsService
	 *            the userDetailsService to set
	 */
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}

}