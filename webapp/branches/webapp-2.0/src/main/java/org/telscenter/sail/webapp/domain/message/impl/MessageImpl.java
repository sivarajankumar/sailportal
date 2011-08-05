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
package org.telscenter.sail.webapp.domain.message.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.telscenter.sail.webapp.domain.message.Message;

/**
 * Message from one WISE4 user to another.
 *  * id
 * originalMessageId; used to denote if this message is a reply to a previous message
 * isRead (true|false)
 * date
 * fromUser
 * toUserId
 * subject 
 * body   
 * 
 * @author hirokiterashima
 * @version $Id:$
 */
@Entity
@Table(name = MessageImpl.DATA_STORE_NAME)
public class MessageImpl implements Message {
	
    @Transient
    public static final String DATA_STORE_NAME = "messages";

    @Transient
    private static final String COLUMN_NAME_SUBJECT = "subject";

    @Transient
	private static final String JOIN_COLUMN_NAME_ORIGINAL_MESSAGE = "originalMessage";

    @Transient
	private static final String COLUMN_NAME_ISREAD = "isRead";

    @Transient
	private static final String COLUMN_NAME_DATE = "date";

    @Transient
	private static final String JOIN_COLUMN_NAME_SENDER = "sender";

    @Transient
	private static final String JOIN_COLUMN_NAME_RECIPIENT = "recipient";

    @Transient
	private static final String COLUMN_NAME_BODY = "body";

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    
	@ManyToOne(targetEntity = MessageImpl.class, fetch=FetchType.EAGER)
	@JoinColumn(name = MessageImpl.JOIN_COLUMN_NAME_ORIGINAL_MESSAGE, nullable=true, unique=false)
	private Message originalMessage;
	
	@Column(name = MessageImpl.COLUMN_NAME_ISREAD)
	private boolean isRead;
	
	@Column(name = MessageImpl.COLUMN_NAME_DATE, nullable=false)
	private Date date;
	    
    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = MessageImpl.JOIN_COLUMN_NAME_SENDER, nullable=false)
	private User sender;

    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = MessageImpl.JOIN_COLUMN_NAME_RECIPIENT, nullable=false)
	private User recipient;

    @Column(name=MessageImpl.COLUMN_NAME_SUBJECT, nullable=false)
    private String subject;

    @Column(name=MessageImpl.COLUMN_NAME_BODY, nullable=false)
    private String body;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the originalMessage
	 */
	public Message getOriginalMessage() {
		return originalMessage;
	}

	/**
	 * @param originalMessage the originalMessage to set
	 */
	public void setOriginalMessage(Message originalMessage) {
		this.originalMessage = originalMessage;
	}

	/**
	 * @return the isRead
	 */
	public boolean isRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * @return the recipient
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
}
