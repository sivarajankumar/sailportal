/**
 * Copyright Encore Research Group University of Toronto 2006 (c)
 * LGPL
 */
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.impl.HibernateUserDetailsDao;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.NullPasswordException;
import net.sf.sail.webapp.service.authentication.NullUsernameException;
import net.sf.sail.webapp.service.authentication.UserCreationException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

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
	
	private static final String PASSWORD = "password";

	private HibernateUserDetails expectedUserDetails;

	private UserDetailsService userDetailsService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
	 */
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		this.expectedUserDetails = (HibernateUserDetails) this.springContext
				.getBean("mutableUserDetails");
		this.expectedUserDetails.setUsername(USERNAME);
		this.expectedUserDetails.setPassword(PASSWORD);
		this.userDetailsService = (UserDetailsService) this.springContext
				.getBean("userDetailsService");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
	 */
	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		HibernateUserDetailsDao userDetailsDao = (HibernateUserDetailsDao) this.springContext
				.getBean("userDetailsDao");
		userDetailsDao.save(this.expectedUserDetails);
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

	public void testCreateUserErrors() {
		// create 2 users and attempt to save to DB
		// second user should cause exception to be thown
		try {
			this.userDetailsService.createUser(USERNAME, PASSWORD);
			this.userDetailsService.createUser(USERNAME, PASSWORD);
			fail("DuplicateUsernameException expected and not caught.");
		} catch (DuplicateUsernameException e) {
		} catch (UserCreationException e) {
			fail(e.getMessage());
		}

		//try to save user with null username
		try {
			this.userDetailsService.createUser(null, PASSWORD);
			fail("NullUsernameException expected and not caught.");
		} catch (NullUsernameException e) {
		} catch (UserCreationException e) {
			fail(e.getMessage());
		}
		
		//try to save user with null password
		try {
			this.userDetailsService.createUser(USERNAME, null);
			fail("NullPasswordException expected and not caught");
		} catch (NullPasswordException e) {
		} catch (UserCreationException e) {
			fail(e.getMessage());
		}

	}
	
	public void testCreateUser() {
		// create user (saves automatically)
		try {
			this.userDetailsService.createUser("whatever", PASSWORD);
		} catch (Exception e) {
			fail("Duplicate user name detected.");
		}
		
		// retrieve user and compare
		UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
		assertEquals(expectedUserDetails, actual);
	}
	
	
}