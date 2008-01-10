/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.domain.general.contactwise.impl;

import java.util.Properties;

import org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE;
import org.telscenter.sail.webapp.domain.general.contactwise.IssueType;
import org.telscenter.sail.webapp.domain.general.contactwise.OperatingSystem;
import org.telscenter.sail.webapp.domain.general.contactwise.WebBrowser;

/**
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public class ContactWISEGeneral implements ContactWISE {

	private static final long serialVersionUID = 1L;

	private IssueType issuetype;
	
	private OperatingSystem operatingsystem;
	
	private WebBrowser webbrowser;
	
	private String name;
	
	private String email;
	
	private String summary;
	
	private String description;
	
	private static Properties emaillisteners;
	
	/**
	 * @param properties the properties to set
	 */
	public void setEmaillisteners(Properties emaillisteners) {
		this.emaillisteners = emaillisteners;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE#getIssueType()
	 */
	public IssueType getIssuetype() {
		return issuetype;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE#getSummary()
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param issueType the issueType to set
	 */
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the operatingsystem
	 */
	public OperatingSystem getOperatingsystem() {
		return operatingsystem;
	}

	/**
	 * @param operatingsystem the operatingsystem to set
	 */
	public void setOperatingsystem(OperatingSystem operatingsystem) {
		this.operatingsystem = operatingsystem;
	}

	/**
	 * @return the webbrowser
	 */
	public WebBrowser getWebbrowser() {
		return webbrowser;
	}

	/**
	 * @param webbrowser the webbrowser to set
	 */
	public void setWebbrowser(WebBrowser webbrowser) {
		this.webbrowser = webbrowser;
	}

	public String[] getMailRecipients() {
		String[] recipients = {
				emaillisteners.getProperty(this.issuetype.name().toLowerCase())
		};
		return recipients;
	}
	
	public String getMailSubject() {
		String subject = "[Contact WISE] " + issuetype + ": " + summary;
		
		return subject;
	}
	
	public String getMailMessage() {
		String message = "Contact WISE Request\n" +
		 "=================\n" + 
		 "Name: " + name + "\n" + 
		 "Email: " + email + "\n" + 
		 "Issue Type: " + issuetype + "\n" +
		 "Operating System: " + operatingsystem + "\n" + 
		 "Web Browser: " + webbrowser + "\n" +
		 "Summary: " + summary + "\n" + 
		 "Description: " + description + "\n";
		
		return message;
	}
}
