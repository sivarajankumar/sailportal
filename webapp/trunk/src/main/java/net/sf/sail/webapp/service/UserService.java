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

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.acegisecurity.userdetails.UserDetails;

/**
 * Represents the set of operations on a user.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
/**
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
	 * @throws BadRequestException
	 *             If username is unacceptable according to SDS, ie. contains
	 *             invalid characters.
	 * @throws NetworkTransportException
	 *             If an error occurs during network communications with SDS
	 *             while creating a SDS user.
	 */
	public User createUser(MutableUserDetails userDetails)
			throws DuplicateUsernameException, BadRequestException,
			NetworkTransportException;

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
}