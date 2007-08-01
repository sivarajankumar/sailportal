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
 * Tests sending a message to specified server.
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
//		URL resource = JavaMailTest.class.getResource("sendmail.properties");
//		
//		props = new Properties();
//		props.load(resource.openStream());
	}

	/**
	 * tests the basic sending 
	 * 
	 * @throws IOException
	 */
	public void testSendBasicMessage() throws IOException  {
		// TODO TP: TELSP-139 these tests don't pass on the server.
//		JavaMailSenderImpl sender = new JavaMailSenderImpl();
//	
//		sender.setUsername((String) props.get("mail.username"));
//		sender.setPassword((String) props.get("mail.password"));
//		sender.setJavaMailProperties(props);
//		MimeMessage message = 	sender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(message);
//		try {
//			helper.setTo("youremail@gmail.com");
//			helper.setFrom((String) props.get("mail.username"));
//			helper.setText("pimp testin' from the portal da place to be");
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		sender.send(message);
	
	}
	
	/**
	 * tests send multi emails interface
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 */
	public void testSendMultiMessage() throws IOException, MessagingException {
		// TODO TP: TELSP-139 these tests don't pass on the server.
//		 JavaMail jm = new JavaMail();
//		 jm.setProperties(props);
//		 
//		 String[] recipients = new String[]{"email1@gmail.com","email2@gmail.com"};
//		 
//		 jm.postMail(recipients, "[PORTAL TEST]", "just a test. one two. one two.", "tels-portal");
//		
	}
	


}


