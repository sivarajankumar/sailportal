/**
 * TODO copyright information for TELS Portal
 */
package org.telscenter.sail.webapp.domain.authentication;

/**
 * TELS Portal version of MutableUserDetails interface
 * 
 * @author Hiroki Terashima
 * 
 * @version $Id: $
 */
public interface MutableUserDetails extends
		net.sf.sail.webapp.domain.authentication.MutableUserDetails {

	public String getFirstname();
	
	public void setFirstname(String firstname);
	
	public String getLastname();
	
	public void setLastname(String lastname);
	
	public Gender getGender();
	
	public void setGender(Gender gender);
}
