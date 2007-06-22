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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
@Entity
@Table(name = PersistentAclTargetObjectIdentity.DATA_STORE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        PersistentAclTargetObjectIdentity.COLUMN_NAME_TARGET_OBJECT,
        PersistentAclTargetObjectIdentity.COLUMN_NAME_TARGET_OBJECT_ID }) })
public class PersistentAclTargetObjectIdentity implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Transient
    static final String DATA_STORE_NAME = "acl_object_identity";

    @Transient
    static final String COLUMN_NAME_TARGET_OBJECT = "object_id_class";

    @Transient
    static final String COLUMN_NAME_TARGET_OBJECT_ID = "object_id_identity";

    @Transient
    static final String COLUMN_NAME_PARENT = "parent_object";

    @Transient
    static final String COLUMN_NAME_OWNER_SID = "owner_sid";

    @Transient
    static final String COLUMN_NAME_IS_INHERITING = "entries_inheriting";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_NAME_TARGET_OBJECT, nullable = false)
    private PersistentAclTargetObject aclTargetObject;

    @Column(name = COLUMN_NAME_TARGET_OBJECT_ID, nullable = false)
    private Long aclTargetObjectId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_NAME_PARENT)
    private PersistentAclTargetObjectIdentity parent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = COLUMN_NAME_OWNER_SID)
    private PersistentAclSid ownerSid;

    @Column(name = COLUMN_NAME_IS_INHERITING, nullable = false)
    private Boolean isInheriting;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;

    /**
     * @return the id
     */
    @SuppressWarnings("unused")
    private Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    @SuppressWarnings("unused")
    private Integer getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    @SuppressWarnings("unused")
    private void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the aclTargetObject
     */
    public PersistentAclTargetObject getAclTargetObject() {
        return aclTargetObject;
    }

    /**
     * @param aclTargetObject
     *            the aclTargetObject to set
     */
    public void setAclTargetObject(PersistentAclTargetObject aclTargetObject) {
        this.aclTargetObject = aclTargetObject;
    }

    /**
     * @return the aclTargetObjectId
     */
    public Long getAclTargetObjectId() {
        return aclTargetObjectId;
    }

    /**
     * @param aclTargetObjectId
     *            the aclTargetObjectId to set
     */
    public void setAclTargetObjectId(Long aclTargetObjectId) {
        this.aclTargetObjectId = aclTargetObjectId;
    }

    /**
     * @return the isInheriting
     */
    public Boolean getIsInheriting() {
        return isInheriting;
    }

    /**
     * @param isInheriting
     *            the isInheriting to set
     */
    public void setIsInheriting(Boolean isInheriting) {
        this.isInheriting = isInheriting;
    }

    /**
     * @return the ownerSid
     */
    public PersistentAclSid getOwnerSid() {
        return ownerSid;
    }

    /**
     * @param ownerSid
     *            the ownerSid to set
     */
    public void setOwnerSid(PersistentAclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    /**
     * @return the parent
     */
    public PersistentAclTargetObjectIdentity getParent() {
        return parent;
    }

    /**
     * @param parent
     *            the parent to set
     */
    public void setParent(PersistentAclTargetObjectIdentity parent) {
        this.parent = parent;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((aclTargetObject == null) ? 0 : aclTargetObject.hashCode());
        result = PRIME
                * result
                + ((aclTargetObjectId == null) ? 0 : aclTargetObjectId
                        .hashCode());
        result = PRIME * result
                + ((ownerSid == null) ? 0 : ownerSid.hashCode());
        result = PRIME * result + ((parent == null) ? 0 : parent.hashCode());
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
        final PersistentAclTargetObjectIdentity other = (PersistentAclTargetObjectIdentity) obj;
        if (aclTargetObject == null) {
            if (other.aclTargetObject != null)
                return false;
        } else if (!aclTargetObject.equals(other.aclTargetObject))
            return false;
        if (aclTargetObjectId == null) {
            if (other.aclTargetObjectId != null)
                return false;
        } else if (!aclTargetObjectId.equals(other.aclTargetObjectId))
            return false;
        if (ownerSid == null) {
            if (other.ownerSid != null)
                return false;
        } else if (!ownerSid.equals(other.ownerSid))
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        return true;
    }
}