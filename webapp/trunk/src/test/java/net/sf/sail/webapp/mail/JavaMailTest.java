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

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Tests sending a message to specified server. Note that this test DOES NOT pass if the properties file
 * is not found: sendmail.properties.
 * 
 * 1. rename sendmail_sample.properties to sendmail.properties
 * 2. fill in empty fields
 * 3. run test
 * 
 * @author aperritano
 * 
 */
public class JavaMailTest {

	private PropertyResourceBundle bundle;
	private Properties props;
	private Enumeration<String> keys;

	/**
	 * loads the property file that contains all the javamail server information
	 */
	@Before
	@Ignore
	public void setUp() throws IOException {
		InputStream is = getClass().getResourceAsStream( "/net/sf/sail/webapp/mail/sendmail.properties" );

		props = new Properties();
		props.load(is);
	}

	@After
	@Ignore
	public void tearDown() {
		props = null;
	}

	/**
	 * tests sending just a basic message.
	 * 
	 * @throws IOException
	 */
	@Test
	@Ignore
	public void testSendBasicMessage() throws IOException {
		this.setUp();
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();

		sender.setUsername((String) props.get("mail.username"));
		sender.setPassword((String) props.get("mail.password"));
		
	
		sender.setJavaMailProperties(props);
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo((String) props.get("mail.to1"));
			helper.setFrom((String) props.get("mail.username"));
			helper.setText((String) props.get("mail.message"));
			helper.setSubject(((String) props.get("mail.subject")));
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException iae) {
			iae.printStackTrace();
		}

		sender.send(message);
		this.tearDown();
	}

	/**
	 * tests sending a message to multiple email receiptents. 
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 */
	@Test
	@Ignore
	public void testSendMultiMessage() throws IOException {
		this.setUp();
		JavaMailHelper jm = new JavaMailHelper();
		jm.setProperties(props);

		String[] recipients = new String[] {
				(String) props.get("mail.to1"),
				(String) props.get("mail.to2") };

		try {
			jm.postMail(recipients, (String) props.get("mail.subject"),
					(String) props.get("mail.message"), (String) props
					.get("mail.from"));
			this.tearDown();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
