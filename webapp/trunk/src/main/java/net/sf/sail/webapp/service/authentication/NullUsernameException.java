/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication;

/**
 *  A checked exception thrown when the user details username is set to null.
 *  
 * @author Laurel Williams
 *
 * @version $Id: $
 */
public class NullUsernameException extends UserCreationException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Username cannnot be null.";
	
	public String getMessage() {
		return MESSAGE;
	}

	
}
