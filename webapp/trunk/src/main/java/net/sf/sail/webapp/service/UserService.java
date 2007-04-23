package net.sf.sail.webapp.service;

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
}