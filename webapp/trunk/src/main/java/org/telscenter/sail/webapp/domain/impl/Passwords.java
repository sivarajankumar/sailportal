/**
 * 
 */
package org.telscenter.sail.webapp.domain.impl;

import java.io.Serializable;

/**
 * @author patricklawler
 * $Id:$
 */
public class Passwords implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String passwd1, passwd2;
	
	public Passwords(String p1, String p2){
		passwd1 = p1;
		passwd2 = p2;
	}
	
	
	public boolean match(){
		if (passwd1.equals(passwd2))
			return true;
		else
			return false;
	}
}
