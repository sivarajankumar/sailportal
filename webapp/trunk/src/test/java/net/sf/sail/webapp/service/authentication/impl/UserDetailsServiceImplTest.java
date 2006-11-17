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

import net.sf.sail.webapp.dao.authentication.impl.HibernateUserDetailsDao;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.NullAuthorityException;
import net.sf.sail.webapp.service.authentication.NullPasswordException;
import net.sf.sail.webapp.service.authentication.NullUsernameException;
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

	public void testCreateUserErrors() throws Exception {
		// create 2 users and attempt to save to DB
		// second user should cause exception to be thown
		this.userDetailsService.createUser(USERNAME2, PASSWORD, EMAIL);
		try {
			this.userDetailsService.createUser(USERNAME2, PASSWORD, EMAIL);
			fail("DuplicateUsernameException expected and not caught.");
		} catch (DuplicateUsernameException e) {} 

		// try to save user with null username
		try {
			this.userDetailsService.createUser(null, PASSWORD, EMAIL);
			fail("NullUsernameException expected and not caught.");
		} catch (NullUsernameException e) {}
		
		// try to save user with null password
		try {
			this.userDetailsService.createUser(USERNAME2, null, EMAIL);
			fail("NullPasswordException expected and not caught");
		} catch (NullPasswordException e) {}

	}
	
	public void testCreateUserWithEmail() throws Exception {
		// create user (saves automatically)
		UserDetails expectedUser = this.userDetailsService.createUser(USERNAME2, PASSWORD, EMAIL);
		
		// retrieve user and compare
		UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME2);
		assertEquals(expectedUser, actual);
	}
	
	public void testCreateUserBlankEmail() throws Exception {
		UserDetails expectedUser = this.userDetailsService.createUser(USERNAME2, PASSWORD, null);
		
		UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME2);
		assertEquals(expectedUser, actual);
	}
	
	public void testCreateAuthorityErrors() throws Exception {
		// create 2 authorities and attempt to save to DB
		// second authority should cause exception to be thown
		this.userDetailsService.createGrantedAuthority(ROLE);
		try {
			this.userDetailsService.createGrantedAuthority(ROLE);
			fail("DuplicateAuthorityException expected and not caught.");
		} catch (DuplicateAuthorityException e) {} 

		// try to save user with null authority
		try {
			this.userDetailsService.createGrantedAuthority(null);
			fail("NullAuthorityException expected and not caught.");
		} catch (NullAuthorityException e) {}
		
	}
	
	public void testCreateAuthority() throws Exception {
		GrantedAuthority expectedGrantedAuthority = this.userDetailsService.createGrantedAuthority(ROLE);
		
		GrantedAuthority actual = this.userDetailsService.loadAuthorityByName(ROLE);
		assertEquals(expectedGrantedAuthority, actual);
	}
	
	
}