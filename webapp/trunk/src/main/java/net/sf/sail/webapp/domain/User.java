package net.sf.sail.webapp.domain;

import java.io.Serializable;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.sds.SdsUser;

public interface User extends Serializable {

	/**
	 * Gets the UserDetails object.
	 * 
	 * @return the userDetails
	 */
	public abstract MutableUserDetails getUserDetails();

	/**
	 * Sets the UserDetails object
	 * @param userDetails
	 *            the userDetails to set
	 */
	public abstract void setUserDetails(MutableUserDetails userDetails);

	/**
	 * Sets the sdsUser object.
	 * 
	 * @param sdsUser
	 *            the sdsUser to set
	 */
	public abstract void setSdsUser(SdsUser sdsUser);

}