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
package org.telscenter.sail.webapp.service.message.impl;

import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;

import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.dao.message.MessageDao;
import org.telscenter.sail.webapp.domain.message.Message;
import org.telscenter.sail.webapp.service.message.MessageService;

/**
 * @author hirokiterashima
 * @version $Id:$
 */
public class MessageServiceImpl implements MessageService {

	private MessageDao<Message> messageDao;
	
	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#markMessageRead(org.telscenter.sail.webapp.domain.message.Message)
	 */
	@Transactional()
	public void markMessageRead(Message message,boolean isRead) {
		message.setRead(isRead);
		messageDao.save(message);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#retrieveMessages()
	 */
	@Transactional()	
	public List<Message> retrieveMessages(User recipient) {
		return messageDao.getListByRecipient(recipient);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#retrieveSentMessages()
	 */
	@Transactional()	
	public List<Message> retrieveSentMessages(User sender) {
		return messageDao.getListBySender(sender);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#retrieveUnreadMessages()
	 */
	@Transactional()	
	public List<Message> retrieveUnreadMessages(User recipient) {
		return messageDao.getListByRecipient(recipient,false);
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#retrieveUnreadMessages()
	 */
	@Transactional()	
	public List<Message> retrieveReadMessages(User recipient) {
		return messageDao.getListByRecipient(recipient,true);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#sendMessage(org.telscenter.sail.webapp.domain.message.Message)
	 */
	@Transactional()	
	public Message saveMessage(Message message) {
		messageDao.save(message);
		return message;
	}

	/**
	 * @param messageDao the messageDao to set
	 */
	public void setMessageDao(MessageDao<Message> messageDao) {
		this.messageDao = messageDao;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.message.MessageService#retrieveById(java.lang.Long)
	 */
	public Message retrieveById(Long id) throws ObjectNotFoundException {
		return this.messageDao.getById(id);
	}

}
