package net.sf.sail.webapp.service;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.springframework.context.ApplicationContext;

public interface UserService {

  /**
   * Given a MutableUserDetails object with a unique name, creates a remote SDS
   * user and also inserts the object into the local db. If username is not
   * unique throws a DuplicateUsernameException.
   * 
   * @param applicationContext The Spring application context containing the beans.
   * @param userDetails
   *          A user object.
   * @return A reference to a User object
   * @throws DuplicateUsernameException
   *           If username is not unique.
   * @throws BadRequestException
   *           If username is unacceptable according to SDS, ie. contains
   *           invalid characters.
   * @throws NetworkTransportException
   *           If an error occurs during network communications with SDS while
   *           creating a SDS user.
   */
  public User createUser(ApplicationContext applicationContext, MutableUserDetails userDetails)
      throws DuplicateUsernameException, BadRequestException,
      NetworkTransportException;

}