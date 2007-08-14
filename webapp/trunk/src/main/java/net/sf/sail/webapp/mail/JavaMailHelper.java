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
package net.sf.sail.webapp.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Helps easily construct an email message using the JavaMail Framework
 * 
 * @author Anthony Perritnao
 * @version $Id$
 */
public class JavaMailHelper implements IMailFacade {

	private Properties properties;
	
	/**
	 * Constructs an email and sends it the desired receptents.
	 * 
	 * @param recipients what emails they are going to 
	 * @param subject the email subject
	 * @param message the email message
	 * @param from from email address
	 */
	public void postMail(String[] recipients, String subject, String message,String from) throws MessagingException {
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		sender.setUsername((String) properties.get("mail.username"));
		sender.setPassword((String) properties.get("mail.password"));
		sender.setJavaMailProperties(properties);
		
		for (String recip : recipients) {
			MimeMessage mimeMessage = 	sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

			helper.setTo(recip);
			helper.setFrom(from);
			helper.setText(message);
			helper.setSubject(subject);
			
			sender.send(mimeMessage);
		}
		
	}

	/**
	 * returns the email server properties
	 * 
	 * @return
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the email server properties
	 * 
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	
}
