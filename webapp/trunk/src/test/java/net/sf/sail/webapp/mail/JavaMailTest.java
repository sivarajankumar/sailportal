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
import java.net.URL;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Tests sending a message to specified server. Note that this test passes if the properties file
 * is not found.
 * 
 * @author aperritano
 * 
 */
public class JavaMailTest extends TestCase {

	private Properties props;

	/**
	 * load the prop file
	 */
	protected void setUp() throws Exception {
		URL resource = JavaMailTest.class.getResource("sendmail.properties");

		if (resource != null) {
			props = new Properties();
			props.load(resource.openStream());
		}
		else {
			System.out.println("JavaMailTest not run because sendmail.properties file not found");
		}
	}

	/**
	 * tests the basic sending
	 * 
	 * @throws IOException
	 */
	public void testSendBasicMessage() throws IOException {
		if (props != null) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			sender.send(message);
		} else
			assertTrue(true);

	}

	/**
	 * tests send multi emails interface
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void testSendMultiMessage() throws IOException, MessagingException {
		if (props != null) {
			JavaMail jm = new JavaMail();
			jm.setProperties(props);

			String[] recipients = new String[] {
					(String) props.get("mail.to1"),
					(String) props.get("mail.to2") };

			jm.postMail(recipients, (String) props.get("mail.subject"),
					(String) props.get("mail.message"), (String) props
							.get("mail.from"));
		} else
			assertTrue(true);

	}

}
