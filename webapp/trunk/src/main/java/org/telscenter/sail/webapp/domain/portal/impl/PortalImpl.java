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
package org.telscenter.sail.webapp.domain.portal.impl;

import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.telscenter.sail.webapp.domain.portal.Portal;

/**
 * TELS Portal implementation.
 * 
 * @author hirokiterashima
 * @version $Id:$
 */
@Entity
@Table(name = PortalImpl.DATA_STORE_NAME)
public class PortalImpl implements Portal {

	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	public static final String DATA_STORE_NAME = "telsportal";

	@Transient
	private static final String COLUMN_NAME_SENDMAIL_PROPERTIES = "sendmail_properties";

	@SuppressWarnings("unused")
	@Transient
	private static final String COLUMN_NAME_GOOGLEMAPS_API_KEY = "googlemaps_api_key";

	@Transient
	private static final String COLUMN_NAME_PORTAL_PROPERTIES = "portal_properties";
	
	@Transient
	private static final String SENDMAIL_ON_EXCEPTION_KEY = "sendmail_on_exception";

	@Transient
	private static final String PORTAL_NAME_KEY = "portal_name";

	@Column(name = COLUMN_NAME_PORTAL_PROPERTIES)
	private Properties portalProperties;
	
	@Column(name = COLUMN_NAME_SENDMAIL_PROPERTIES)
	private Properties sendmailProperties;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long id = null;

    @Version
    @Column(name = "OPTLOCK")
    protected Integer version = null;

	/**
	 * @return the sendEmailOnException
	 */
	public boolean isSendEmailOnException() {
		return Boolean.parseBoolean((String) portalProperties.get(SENDMAIL_ON_EXCEPTION_KEY));
	}

	/**
	 * @param sendEmailOnException the sendEmailOnException to set
	 */
	public void setSendEmailOnException(boolean sendEmailOnException) {
		this.portalProperties.setProperty(SENDMAIL_ON_EXCEPTION_KEY, Boolean.toString(sendEmailOnException));
	}

	/**
	 * @return the sendmailProperties
	 */
	public Properties getSendmailProperties() {
		return sendmailProperties;
	}

	/**
	 * @param sendmailProperties the sendmailProperties to set
	 */
	public void setSendmailProperties(Properties sendmailProperties) {
		this.sendmailProperties = sendmailProperties;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.portal.Portal#getPortalName()
	 */
	public String getPortalName() {
		return this.portalProperties.getProperty(PORTAL_NAME_KEY);
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.portal.Portal#setPortalName(java.lang.String)
	 */
	public void setPortalName(String portalName) {
		this.portalProperties.setProperty(PORTAL_NAME_KEY, portalName);
	}

	 /**
     * @see net.sf.sail.webapp.domain.Curnit#getId()
     */
    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }
    
    @SuppressWarnings("unused")
    private Integer getVersion() {
        return this.version;
    }

    @SuppressWarnings("unused")
    private void setVersion(Integer version) {
        this.version = version;
    }
}
