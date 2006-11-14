/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication;

/**
 * A checked exception thrown when the data store already contains a user
 * details object with the same username.
 * 
 * @author Laurel Williams
 * 
 * @version $Id: $
 * 
 */
public class DuplicateUsernameException extends UserCreationException {

	private static final long serialVersionUID = 1L;

	private String message;

	public DuplicateUsernameException(String username) {
		this.message = "Username:" + username + " already in use.";
	}

	public String getMessage() {
		return this.message;
	}

}
