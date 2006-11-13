/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication;

/**
 * @author Laurel Williams
 * 
 * @version $Id:
 * 
 * A checked exception thrown when the data store already contains a user
 * details object with the same username.
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
