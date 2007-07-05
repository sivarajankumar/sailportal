/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.domain.authentication.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.authentication.MutableAclEntry;
import net.sf.sail.webapp.domain.authentication.MutableAclSid;
import net.sf.sail.webapp.domain.authentication.MutableAclTargetObjectIdentity;

/**
 * Concrete implementation of <code>MutableAclEntry</code> marked with EJB3
 * annotations for persistence.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 */
@Entity
@Table(name = PersistentAclEntry.DATA_STORE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        PersistentAclEntry.COLUMN_NAME_TARGET_OBJECT_ID,
        PersistentAclEntry.COLUMN_NAME_ACE_ORDER }) })
public class PersistentAclEntry implements MutableAclEntry {

    @Transient
    private static final long serialVersionUID = 1L;

    @Transient
    static final String DATA_STORE_NAME = "acl_entry";

    @Transient
    static final String COLUMN_NAME_TARGET_OBJECT_ID = "acl_object_identity";

    @Transient
    static final String COLUMN_NAME_ACE_ORDER = "ace_order";

    @Transient
    static final String COLUMN_NAME_SID = "sid";

    @Transient
    static final String COLUMN_NAME_MASK = "mask";

    @Transient
    static final String COLUMN_NAME_GRANTING = "granting";

    @Transient
    static final String COLUMN_NAME_AUDIT_SUCCESS = "audit_success";

    @Transient
    static final String COLUMN_NAME_AUDIT_FAILURE = "audit_failure";

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = PersistentAclTargetObjectIdentity.class)
    @JoinColumn(name = COLUMN_NAME_TARGET_OBJECT_ID, nullable = false)
    private MutableAclTargetObjectIdentity targetObjectIdentity;

    @Column(name = COLUMN_NAME_ACE_ORDER, nullable = false)
    private Integer aceOrder;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = PersistentAclSid.class)
    @JoinColumn(name = COLUMN_NAME_SID, nullable = false)
    private MutableAclSid sid;

    @Column(name = COLUMN_NAME_MASK, nullable = false)
    private Integer mask;

    @Column(name = COLUMN_NAME_GRANTING, nullable = false)
    private Boolean granting;

    @Column(name = COLUMN_NAME_AUDIT_SUCCESS, nullable = false)
    private Boolean auditSuccess;

    @Column(name = COLUMN_NAME_AUDIT_FAILURE, nullable = false)
    private Boolean auditFailure;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getAceOrder()
     */
    public Integer getAceOrder() {
        return aceOrder;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setAceOrder(java.lang.Integer)
     */
    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getAuditFailure()
     */
    public Boolean getAuditFailure() {
        return auditFailure;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setAuditFailure(java.lang.Boolean)
     */
    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getAuditSuccess()
     */
    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setAuditSuccess(java.lang.Boolean)
     */
    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getGranting()
     */
    public Boolean getGranting() {
        return granting;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setGranting(java.lang.Boolean)
     */
    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getMask()
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setMask(java.lang.Integer)
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getSid()
     */
    public MutableAclSid getSid() {
        return sid;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setSid(net.sf.sail.webapp.domain.authentication.MutableAclSid)
     */
    public void setSid(MutableAclSid sid) {
        this.sid = sid;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#getTargetObjectIdentity()
     */
    public MutableAclTargetObjectIdentity getTargetObjectIdentity() {
        return targetObjectIdentity;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#setTargetObjectIdentity(net.sf.sail.webapp.domain.authentication.impl.PersistentAclTargetObjectIdentity)
     */
    public void setTargetObjectIdentity(
            MutableAclTargetObjectIdentity targetObjectIdentity) {
        this.targetObjectIdentity = targetObjectIdentity;
    }

    @SuppressWarnings("unused")
    private Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    private Integer getVersion() {
        return version;
    }

    @SuppressWarnings("unused")
    private void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((aceOrder == null) ? 0 : aceOrder.hashCode());
        result = PRIME
                * result
                + ((targetObjectIdentity == null) ? 0 : targetObjectIdentity
                        .hashCode());
        return result;
    }

    /**
     * @see net.sf.sail.webapp.domain.authentication.MutableAclEntry#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PersistentAclEntry other = (PersistentAclEntry) obj;
        if (aceOrder == null) {
            if (other.aceOrder != null)
                return false;
        } else if (!aceOrder.equals(other.aceOrder))
            return false;
        if (targetObjectIdentity == null) {
            if (other.targetObjectIdentity != null)
                return false;
        } else if (!targetObjectIdentity.equals(other.targetObjectIdentity))
            return false;
        return true;
    }
}