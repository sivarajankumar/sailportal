package net.sf.sail.cms.curnit;

/**
 * 
 * @author rokham
 * Details of a curnit's author
 */
public class Author {

	private String firstName;
	
	private String lastName;
	
	private String userName;

	/**
	 * Get author's first name
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set author's first name
	 * @param firstName author's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get author's last name
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set author's last name
	 * @param lastName author's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get author's user name
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set author's user name
	 * @param userName author's user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
