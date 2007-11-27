/**
 * 
 */
package org.telscenter.sail.webapp.service.impl;

import net.sf.sail.webapp.dao.sds.HttpStatusCodeException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserNotFoundException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class UserServiceImpl extends
		net.sf.sail.webapp.service.impl.UserServiceImpl {

	/**
	 * @throws DuplicateUsernameException 
	 * @see net.sf.sail.webapp.service.UserService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 */
	@Override
	@Transactional(rollbackFor = { DuplicateUsernameException.class, HttpStatusCodeException.class})
	public User createUser(final MutableUserDetails userDetails) throws DuplicateUsernameException, HttpStatusCodeException {

		org.telscenter.sail.webapp.domain.authentication.MutableUserDetails details = 
			(org.telscenter.sail.webapp.domain.authentication.MutableUserDetails) userDetails;

		String coreUsername = details.getCoreUsername();
		String[] suffixes = details.getUsernameSuffixes(); // extra at end to ensure username uniqueness
		int index = 0;  // index within suffixes array 
		
		details.setNumberOfLogins(0);
		for (;;) {   // loop until a unique username can be found
			try {
				details.setUsername(coreUsername + suffixes[index]);
				User newUser = super.createUser(details);
				newUser.getSdsUser().setFirstName(details.getFirstname());
				newUser.getSdsUser().setLastName(details.getLastname());
				return newUser;
			}
			catch (DuplicateUsernameException e) {
				if (index >= suffixes.length) {
					throw e;
				}
				index++;
				continue;
			}
			catch (HttpStatusCodeException e) {
				throw e;
			}
		}
	}
	
	/**
	 * Comment me.
	 * 
	 * @param userDetails
	 * @throws UserNotFoundException
	 */
	public void checkUserUpdateErrors(MutableUserDetails userDetails) throws UserNotFoundException {
		//check if the use does exist
		User user = this.retrieveUser(userDetails);
		if(user == null) {
			throw new UserNotFoundException(userDetails.getUsername());
		}// if
	}
	
	/**
	 * @see net.sf.sail.webapp.service.UserService#retrieveUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User retrieveUserByUsername(String username) {
		User user = null;
		try {
  		   user = super.retrieveUserByUsername(username);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	
}
