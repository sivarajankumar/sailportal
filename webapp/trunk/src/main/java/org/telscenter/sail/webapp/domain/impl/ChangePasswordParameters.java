/**
 * 
 */
package org.telscenter.sail.webapp.domain.impl;

import java.io.Serializable;

/**
 * @author patricklawler
 * @version $Id:$
 */
public class ChangePasswordParameters implements Serializable {

	private static final long serialVersionUID = 1L;

	private String passwd1, passwd2;

	/**
	 * @return the passwd1
	 */
	public String getPasswd1() {
		return passwd1;
	}

	/**
	 * @param passwd1 the passwd1 to set
	 */
	public void setPasswd1(String passwd1) {
		this.passwd1 = passwd1;
	}

	/**
	 * @return the passwd2
	 */
	public String getPasswd2() {
		return passwd2;
	}

	/**
	 * @param passwd2 the passwd2 to set
	 */
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}

}
