/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.HibernateUserDetails;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.NullPasswordException;
import net.sf.sail.webapp.service.authentication.NullUsernameException;
import net.sf.sail.webapp.service.authentication.UserCreationException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDetailsDao<MutableUserDetails> userDetailsDao;

	/**
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = this.userDetailsDao.retrieve(username);
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
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(java.lang.String,
	 *      java.lang.String)
	 */
	public void createUser(String username, String password)
			throws UserCreationException {
		MutableUserDetails userDetails = validateAndCreateUserDetails(username,
				password);
		this.userDetailsDao.save(userDetails);
	}

	/**
	 * Creates a user, with optional email address
	 * 
	 * @see
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createUser(java.lang.String,
	 *      java.lang.String)
	 * 
	 * @param username
	 * @param password
	 * @param emailAddress
	 * @throws UserCreationException
	 */
	public void createUser(String username, String password, String emailAddress)
			throws UserCreationException {
		MutableUserDetails userDetails = validateAndCreateUserDetails(username,
				password);
		userDetails.setEmailAddress(emailAddress);
		this.userDetailsDao.save(userDetails);
	}

	/**
	 * Validates user input by calling the private method isNoCreationErrors
	 * Then creates a MutableUserDetails object with the username and password
	 * set.
	 * 
	 * @param username
	 * @param password
	 * @return An instance of MutableUserDetails with username and password set.
	 * @throws UserCreationException
	 */
	private MutableUserDetails validateAndCreateUserDetails(String username,
			String password) throws UserCreationException {
		// validate inputs
		UserCreationException exception = this.isNoCreationErrors(username, password);
		if (exception != null)
			throw exception;

		// create user details with username and password
		MutableUserDetails userDetails = new HibernateUserDetails();
		userDetails.setUsername(username);
		userDetails.setPassword(password);
		return userDetails;
	}

	/**
	 * Validates user input - checking for non null username and password. Also
	 * checks that the data store does not already contain a user with the same
	 * username
	 * 
	 * @param username
	 * @param password
	 * @return The appropriate UserCreationException. NullUsernameException if
	 *         the username is null, NullPasswordException if the password is
	 *         null, and DuplicateUsernameException if the username is the same
	 *         as a username already in data store. Returns null if no
	 *         exceptions are found.
	 */
	private UserCreationException isNoCreationErrors(String username,
			String password) {
		if (username == null)
			return new NullUsernameException();
		if (password == null)
			return new NullPasswordException();

		try {
			@SuppressWarnings("unused")
			UserDetails uniqueUserDetails = (MutableUserDetails) this
					.loadUserByUsername(username);
		} catch (UsernameNotFoundException unfe) {
			return null;
		}
		return new DuplicateUsernameException(username);
	}
}