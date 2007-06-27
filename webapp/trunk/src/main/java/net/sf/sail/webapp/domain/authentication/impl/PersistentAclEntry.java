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

import java.io.Serializable;

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

/**
 * Represents the Access Control List (ACL) entry (ACE) that grants permissions
 * to a Security ID (SID) for a particular secured object. This class is marked
 * with EJB3 annotations for persistence.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 */
@Entity
@Table(name = PersistentAclEntry.DATA_STORE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        PersistentAclEntry.COLUMN_NAME_TARGET_OBJECT_ID,
        PersistentAclEntry.COLUMN_NAME_ACE_ORDER }) })
public class PersistentAclEntry implements Serializable {

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_NAME_TARGET_OBJECT_ID, nullable = false)
    private PersistentAclTargetObjectIdentity targetObjectIdentity;

    @Column(name = COLUMN_NAME_ACE_ORDER, nullable = false)
    private Integer aceOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_NAME_SID, nullable = false)
    private PersistentAclSid sid;

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
     * @return the aceOrder
     */
    public Integer getAceOrder() {
        return aceOrder;
    }

    /**
     * @param aceOrder
     *            the aceOrder to set
     */
    public void setAceOrder(Integer aceOrder) {
        this.aceOrder = aceOrder;
    }

    /**
     * @return the auditFailure
     */
    public Boolean getAuditFailure() {
        return auditFailure;
    }

    /**
     * @param auditFailure
     *            the auditFailure to set
     */
    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    /**
     * @return the auditSuccess
     */
    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    /**
     * @param auditSuccess
     *            the auditSuccess to set
     */
    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    /**
     * @return the granting
     */
    public Boolean getGranting() {
        return granting;
    }

    /**
     * @param granting
     *            the granting to set
     */
    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    /**
     * @return the mask
     */
    public Integer getMask() {
        return mask;
    }

    /**
     * @param mask
     *            the mask to set
     */
    public void setMask(Integer mask) {
        this.mask = mask;
    }

    /**
     * @return the sid
     */
    public PersistentAclSid getSid() {
        return sid;
    }

    /**
     * @param sid
     *            the sid to set
     */
    public void setSid(PersistentAclSid sid) {
        this.sid = sid;
    }

    /**
     * @return the targetObjectIdentity
     */
    public PersistentAclTargetObjectIdentity getTargetObjectIdentity() {
        return targetObjectIdentity;
    }

    /**
     * @param targetObjectIdentity
     *            the targetObjectIdentity to set
     */
    public void setTargetObjectIdentity(
            PersistentAclTargetObjectIdentity targetObjectIdentity) {
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
     * @see java.lang.Object#hashCode()
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
     * @see java.lang.Object#equals(java.lang.Object)
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