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
package net.sf.sail.webapp.service;

import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.sds.HttpStatusCodeException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.acegisecurity.userdetails.UserDetails;

/**
 * Represents the set of operations on a user.
 * 
 * @author Cynick Young
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public interface UserService {

	/**
	 * Given a MutableUserDetails object with a unique name, creates a remote
	 * SDS user and also inserts the object into the local db. If username is
	 * not unique throws a DuplicateUsernameException.
	 * 
	 * @param userDetails
	 *            A user object.
	 * @return A reference to a <code>User</code> object
	 * @throws DuplicateUsernameException
	 *             If username is not unique.
	 * @throws HttpStatusCodeException
	 *             If any unexpected status code is returned from the SDS while
	 *             creating the user.
	 */
	public User createUser(MutableUserDetails userDetails)
			throws DuplicateUsernameException, HttpStatusCodeException;
	
	/**
	 * Retrieve user with the given user details.
	 * 
	 * @param userDetails
	 *            that has valid authentication credentials
	 * @return <code>User</code> associated with the given user details
	 */
	public User retrieveUser(UserDetails userDetails);

	/**
	 * Retrieve user with the give username
	 * 
	 * @param username
	 * @return <code>User</code> associated with the given username
	 */
	public User retrieveUserByUsername(String username);

	/**
	 * Retrieve users with the given emailAddress
	 * 
	 * @param emailAddress
	 * @return <code>Users</code> associated with the given emailaddress
	 */
	public List<User> retrieveUserByEmailAddress(String emailAddress);

	/**
	 * Encodes a new password and updates a user in the persistent data store.
	 * 
	 * @param user
	 *            The user that you want to update
	 * @param newPassword
	 *            The UN-ENCODED new password that you want to put in place for
	 *            this user
	 * @return The user with the newly encoded password.
	 */
	public User updateUserPassword(final User user, String newPassword);

	/**
	 * Gets all users from persistent data store.
	 * 
	 * @return a Set of all users.
	 */
	public List<User> retrieveAllUsers();
	
	/**
	 * Retrieves User domain object using unique userId
	 * 
	 * @param userId
	 *      <code>Long</code> userId to use for lookup
	 * @return <code>User</code>
	 *      the User object with the given userId
	 * @throws ObjectNotFoundException when userId
	 *      cannot be used to find the existing user
	 */
	public User retrieveById(Long userId) throws ObjectNotFoundException;
	
	/**
	 * Instantiates a SdsUser object and populates its firstname
	 * and lastname using the provided <code>MutableUserDetails</code>
	 * and returns it
	 * 
	 * @param userDetails used to retrieve firstnamd and lastname
	 * @return new <code>SdsUser</code> object with firstname and lastname set
	 */
	public SdsUser createSdsUser(final MutableUserDetails userDetails);

	/**
	 * Updates the existing <code>MutableUserDetails</code> object
	 * @param userDetails
	 */
	public void updateUserDetails(MutableUserDetails userDetails);
}