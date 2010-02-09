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
package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.mail.IMailFacade;
import net.sf.sail.webapp.service.UserService;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.message.Message;
import org.telscenter.sail.webapp.domain.message.impl.MessageImpl;
import org.telscenter.sail.webapp.service.message.MessageService;

/**
 * @author hirokiterashima
 * @version $Id:$
 */
public class MessageController extends AbstractController {

	private MessageService messageService;
	
	private UserService userService;
	
	private IMailFacade javaMail = null;
	
	private Properties emaillisteners = null;


	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		UserDetails userDetails = (UserDetails) context.getAuthentication().getPrincipal();
		User user = userService.retrieveUser(userDetails);  // logged-in user
		String action = request.getParameter("action");
		boolean successful = false;
		if (action.equals("index")) {
			List<Message> readMessages = messageService.retrieveReadMessages(user);
			List<Message> unreadMessages = messageService.retrieveUnreadMessages(user);			
			List<Message> sentMessages = messageService.retrieveSentMessages(user);
			ModelAndView mav = new ModelAndView("/message/index");
			mav.addObject("readMessages", readMessages);
			mav.addObject("unreadMessages", unreadMessages);
			mav.addObject("sentMessages", sentMessages);
			return mav;
		} 

		String failureMessage = "";
		Message message = null;
		String messageIdStr = request.getParameter("messageId");
		if (messageIdStr != null) {
			Long messageId = Long.valueOf(messageIdStr);
			message = messageService.retrieveById(messageId);
		}

		if (action.equals("archive")) {
			successful = archiveMessage(message,true,user);
		} else if (action.equals("unarchive")) {
			successful = archiveMessage(message, false, user);
		} else if (action.equals("compose") || action.equals("reply")){
			String recipientUsername = request.getParameter("recipient");
			User recipient = userService.retrieveUserByUsername(recipientUsername);
			if (recipient == null) {  // recipient not found
				successful = false;
				failureMessage = "recipient not found";
			} else {
				// we can try sending message
				try {
					Message originalMessage = null;
					String originalMessageId = request.getParameter("originalMessageId");
					if (originalMessageId != null) {
						originalMessage = messageService.retrieveById(new Long(originalMessageId));
					}
					successful = sendMessage(request, user, recipient, originalMessage);
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
					successful = false;
					failureMessage = "original message not found";
				}
			}
		} else {
			successful = false;
		}
		
		if (successful) {
			response.getWriter().print("success");
		} else {
			response.getWriter().print(failureMessage);
		}
		return null;
	}
	
	/**
	 * Handles composing a message from the specified user to another.
	 * all of the parameters needed to compose a request are in the request object.
	 * Sends an email to the recipient
	 * @param request
	 * @param user
	 * @param originalMessage if not null, this is a reply
	 * @return true iff message was successfully composed and sent.
	 */
	private boolean sendMessage(HttpServletRequest request, User sender, User recipient, Message originalMessage) {
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		Message message = new MessageImpl();
		message.setOriginalMessage(originalMessage);
		message.setDate(Calendar.getInstance().getTime());
		message.setSender(sender);
		message.setRecipient(recipient);
		message.setSubject(subject);
		message.setBody(body);
		messageService.saveMessage(message);
		emailMessage(message);
		return true;
	}

	private void emailMessage(Message message) {
		EmailMessageService emailMessageService =
			new EmailMessageService(message);
		Thread thread = new Thread(emailMessageService);
		thread.start();
	}

	class EmailMessageService implements Runnable {
		private static final int MAX_BODY_LENGTH = 50;  // maximum number of characters in the body to show in the email.
		private Message message;
		
		public EmailMessageService(Message message) {
			this.message = message;
		}

		public void run() {
			// sends email to the recipient
    		MutableUserDetails senderUserDetails = (MutableUserDetails) message.getSender().getUserDetails();
    		String senderName = senderUserDetails.getFirstname() + " " + senderUserDetails.getLastname();
    		MutableUserDetails recipientUserDetails = (MutableUserDetails) message.getRecipient().getUserDetails();
    		String recipientEmailAddress = recipientUserDetails.getEmailAddress();
    		
    		String[] recipients = {recipientEmailAddress, emaillisteners.getProperty("uber_admin")};
    		String messageBody = message.getBody();
    		if (messageBody.length() > MAX_BODY_LENGTH) {
    			// trim body if it's large, and add ...
    			messageBody = messageBody.substring(0, MAX_BODY_LENGTH) + "...";
    		}

    		String subject = senderName + " sent you a message on WISE4";	
    		String messageString = senderName + " sent you a message on WISE4:\n\n" +
    		"Subject: " + message.getSubject() + "\n" +
    		"Recipient:" + message.getRecipient().getUserDetails().getUsername() + "\n" +
    		"Message: " + messageBody + "\n\n" +
    		"To view the entire message and reply to this message, please log into WISE.\n\n" +
    		"Thanks,\n" +
    		"WISE4 Team";
    		
    		String fromEmail = "noreply@telscenter.org";
    		
    		//sends the email to the recipients
    		try {
    			javaMail.postMail(recipients, subject, messageString, fromEmail);
    		} catch (MessagingException e) {
    			// do nothing, no notification to uber_admin required.
    			e.printStackTrace();
    		}    	
		}
		
	}
	
	
	/**
	 * archives a message iff isRead is true. Will not archive
	 * if specified user is not the recipient of the message.
	 * @param message
	 * @param isRead
	 * @param user
	 * @return true iff successfully updated message.
	 */
	public boolean archiveMessage(Message message, boolean isRead, User user) {
		if (message.getRecipient().equals(user)) {
			messageService.markMessageRead(message, isRead);
			return true;
		} 
		return false;
	}

	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param javaMail the javaMail to set
	 */
	public void setJavaMail(IMailFacade javaMail) {
		this.javaMail = javaMail;
	}

	/**
	 * @param emaillisteners the emaillisteners to set
	 */
	public void setEmaillisteners(Properties emaillisteners) {
		this.emaillisteners = emaillisteners;
	}

}
