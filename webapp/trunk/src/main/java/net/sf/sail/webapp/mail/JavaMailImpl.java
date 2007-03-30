package net.sf.sail.webapp.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
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

/**
 * @author tony
 * @version $Id$
 */
public class JavaMailImpl implements IMailFacade {

	private String fallback;
	private String password;
	private String smtpHostName;
	private String smtpPort;
	private String sslFactory;
	private String userName;
	private boolean mailDebug = false;
	private String smtpAuth;
	private String emailAddress;
	
	public String getEmailFromAddress() {
		return this.emailAddress;
	}

	
	public String getSMTPAuth() {
		return this.smtpAuth;
	}

	public String getPassword() {
		return this.password;
	}

	public String getSMTPHostName() {
		return this.smtpHostName;
	}

	public String getSMTPPort() {
		return this.smtpPort;
	}

	public String getSSLFactory() {
		return this.sslFactory;
	}

	public String getUserName() {
		return userName;
	}

	public String getFallback() {
		return this.fallback;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setSMTPHostName(String smtpHostName) {
		this.smtpHostName = smtpHostName;
	}

	public void setSMTPPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setSSLFactory(String sslFactory) {
		this.sslFactory = sslFactory;
	}

	public void setUserName(String username) {
		this.userName = username;
	}


	public void setSMTPAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public void setEmailFromAddress(String emailFromAddress) {
		this.emailAddress = emailFromAddress;
	}

	public boolean isMailDebug() {
		return mailDebug;
	}

	public void setMailDebug(boolean mailDebug) {
		this.mailDebug = mailDebug;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public boolean sendPlainTextMessage(String[] emailRecipients, String subject, String message) {
		
		Properties props = new Properties();

		props.put("mail.smtp.host", getSMTPHostName()); //$NON-NLS-1$
		props.put("mail.smtp.auth", getSMTPAuth()); //$NON-NLS-1$
		props.put("mail.debug", isMailDebug()); //$NON-NLS-1$
		props.put("mail.smtp.port", getSMTPPort()); //$NON-NLS-1$
		props.put("mail.smtp.socketFactory.port", getSMTPPort()); //$NON-NLS-1$
		props.put("mail.smtp.socketFactory.class", getSSLFactory()); //$NON-NLS-1$
		props.put("mail.smtp.socketFactory.fallback", getFallback()); //$NON-NLS-1$
		
		Session mailSession = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getUserName(), getPassword());
					}
				});

		mailSession.setDebug(isMailDebug());
		try {

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(getEmailFromAddress())); //$NON-NLS-1$
			InternetAddress[] sendToAddress = { new InternetAddress(emailRecipients[0]) }; //$NON-NLS-1$
			msg.setRecipients(Message.RecipientType.TO, sendToAddress);

			msg.setSubject(subject); //$NON-NLS-1$
			msg.setSentDate(new Date());
			msg.setText(message);

			Transport.send(msg);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}// try
		
		return false;
	}
}
