/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.service.impl;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

/**
 * Tests services available to TELS Portal User
 *
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class UserServiceImplTest extends AbstractTransactionalDbTests {
	
	  private static final String USERNAME = "Billy Bob";

	  private static final String EMAIL = "billy@bob.com";

	  private static final String PASSWORD = "password";

	  private static final String FIRSTNAME = "Billy";

	  private static final String LASTNAME = "Bob";

	  private static final Gender GENDER = Gender.MALE;

	  private GrantedAuthorityDao<MutableGrantedAuthority> authorityDao;

	  private UserDao<User> userDao;

	  private UserDetailsService userDetailsService;

	  private UserService userService;
	  
	  public void testDuplicateUserErrors() throws Exception {
		  MutableUserDetails user = (MutableUserDetails) this.applicationContext
		      .getBean("studentUserDetails");
		  user.setUsername(USERNAME);
		  user.setPassword(PASSWORD);
		  user.setEmailAddress(EMAIL);
		  user.setFirstname(FIRSTNAME);
		  user.setLastname(LASTNAME);
		  user.setGender(GENDER);
		  
		  // create 2 users and attempt to save to DB
		  // second user should cause exception to be thrown
		  this.userService.createUser(user);
		  try {
			  this.userService.createUser(user);
			  fail("DuplicateUsernameException expected and not caught.");
		  }
		  catch (DuplicateUsernameException e) {
		  }
	  }

	  /*
	   * This test checks creation of a user within the portal, but ignores the
	   * creation of a user on the remote SDS. Tests for system integration are
	   * beyond the scope of this testing mechanism. We are assuming the SdsUserId
	   * cannot be null, enforced by the data store constraint.
	   */
	  public void testCreateUserWithEmail() throws Exception {
		  MutableGrantedAuthority expectedAuthority = (MutableGrantedAuthority) this.applicationContext
		      .getBean("mutableGrantedAuthority");
		  expectedAuthority.setAuthority(UserDetailsService.USER_ROLE);
		  this.authorityDao.save(expectedAuthority);
		  
		  MutableUserDetails userDetails = (MutableUserDetails) this.getApplicationContext()
		      .getBean("studentUserDetails");
		  userDetails.setUsername(USERNAME);
		  userDetails.setPassword(PASSWORD);
		  userDetails.setEmailAddress(EMAIL);
		  userDetails.setFirstname(FIRSTNAME);
		  userDetails.setLastname(LASTNAME);
		  userDetails.setGender(GENDER);
		  
		  // create user (saves automatically)
		  User expectedUser = this.userService.createUser(userDetails);
		  UserDetails expectedUserDetails = expectedUser.getUserDetails();
		  
		  // retrieve user and compare
		  UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
		  assertEquals(expectedUserDetails, actual);
		  
		  // check role
		  GrantedAuthority[] authorities = actual.getAuthorities();
		  if (authorities == null) 
			  fail("authorities is null");
		  boolean foundUserRole = false;
		  for (int i=0; i < authorities.length; i++) {
			  if (authorities[i].getAuthority() == UserDetailsService.USER_ROLE) {
				  foundUserRole = true;
				  break;
			  }
		  }
		  assertTrue(foundUserRole);
		  
		  // added this end transaction to catch a transaction commit within a
		  // transactio rollback problem
		  this.userDao.delete(expectedUser);
		  this.authorityDao.delete(expectedAuthority);
		  this.setComplete();
		  this.endTransaction();
		  
	  }
	  
	  /*
	   * This test checks creation of a user within the portal, but ignores the
	   * creation of a user on the remote SDS. Tests for system integration are
	   * beyond the scope of this testing mechanism. We are assuming the SdsUserId
	   * cannot be null, enforced by the data store constraint. Email is null
	   */
	  public void testCreateUserBlankEmail() throws Exception {
		  MutableUserDetails userDetails = (MutableUserDetails) this.applicationContext
		      .getBean("studentUserDetails");
		  userDetails.setUsername(USERNAME);
		  userDetails.setPassword(PASSWORD);
		  userDetails.setEmailAddress(EMAIL);
		  userDetails.setFirstname(FIRSTNAME);
		  userDetails.setLastname(LASTNAME);
		  userDetails.setGender(GENDER);
		  User expectedUser = this.userService.createUser(userDetails);
		  
		  MutableUserDetails expectedUserDetails = (MutableUserDetails) expectedUser.getUserDetails();
		  UserDetails actual = this.userDetailsService.loadUserByUsername(USERNAME);
		  assertEquals(expectedUserDetails, actual);
	  }
	 
	  /**
	   * @param authorityDao
	   *          the authorityDao to set
	   */
	  public void setAuthorityDao(
	      GrantedAuthorityDao<MutableGrantedAuthority> authorityDao) {
	    this.authorityDao = authorityDao;
	  }

	  /**
	   * @param userDetailsService
	   *          the userDetailsService to set
	   */
	  public void setUserDetailsService(UserDetailsService userDetailsService) {
	    this.userDetailsService = userDetailsService;
	  }

	  /**
	   * @param userService
	   *          the userService to set
	   */
	  public void setUserService(UserService userService) {
	    this.userService = userService;
	  }

	  /**
	   * @param userDao
	   *          the userDao to set
	   */
	  public void setUserDao(UserDao<User> userDao) {
	    this.userDao = userDao;
	  }

	  
}
