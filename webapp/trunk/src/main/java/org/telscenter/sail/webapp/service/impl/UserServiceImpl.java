/**
 * 
 */
package org.telscenter.sail.webapp.service.impl;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hiroki Terashima
 * @version $$Id$$
 */
public class UserServiceImpl extends
		net.sf.sail.webapp.service.impl.UserServiceImpl {

	/**
	 * @see net.sf.sail.webapp.service.UserService#createUser(net.sf.sail.webapp.domain.authentication.MutableUserDetails)
	 */
	@Override
	@Transactional(rollbackFor = { DuplicateUsernameException.class,
			BadRequestException.class, NetworkTransportException.class })
	public User createUser(final MutableUserDetails userDetails)
			throws DuplicateUsernameException, BadRequestException,
			NetworkTransportException {

		org.telscenter.sail.webapp.domain.authentication.MutableUserDetails details = 
			(org.telscenter.sail.webapp.domain.authentication.MutableUserDetails) userDetails;

		String coreUsername = details.getCoreUsername();
		String[] suffixes = details.getUsernameSuffixes(); // extra at end to ensure username uniqueness
		int index = 0;  // index within suffixes array 
		
		details.setNumberOfLogins(0);
		for (;;) {   // loop until a unique username can be found
			try {
				details.setUsername(coreUsername + suffixes[index]);
				return super.createUser(details);
			}
			catch (DuplicateUsernameException e) {
				if (index >= suffixes.length) {
					throw e;
				}
				index++;
				continue;
			}
			catch (BadRequestException e) {
				throw e;
			}
			catch (NetworkTransportException e) {
				throw e;
			}
		}
	}
	
}
