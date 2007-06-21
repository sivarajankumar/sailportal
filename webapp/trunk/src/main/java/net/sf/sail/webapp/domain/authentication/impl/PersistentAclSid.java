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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.userdetails.UserDetails;

/**
 * Concrete implementation of the <code>Sid</code> (security id) interface
 * that is marked with EJB3 annotations for persistence. This implementation
 * supports both principal and granted authority based <code>Sid</code> in a
 * single class. There is no need for separate implementations.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * @see org.acegisecurity.acls.sid.Sid
 */
@Entity
@Table(name = PersistentAclSid.DATA_STORE_NAME, uniqueConstraints = { @UniqueConstraint(columnNames = {
        PersistentAclSid.COLUMN_NAME_SID,
        PersistentAclSid.COLUMN_NAME_IS_PRINCIPAL }) })
public class PersistentAclSid implements Sid {

    @Transient
    public static final String DATA_STORE_NAME = "acl_sid";

    @Transient
    public static final String COLUMN_NAME_IS_PRINCIPAL = "is_principal";

    @Transient
    public static final String COLUMN_NAME_SID = "sid_name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;

    @Column(name = COLUMN_NAME_IS_PRINCIPAL, nullable = false)
    private Boolean isPrincipal;

    @Column(name = COLUMN_NAME_SID, nullable = false)
    private String sidName;

    /**
     * Tests whether this instance of <code>Sid</code> was created as a
     * principal.
     * 
     * @return <code>true</code> if this instance of <code>Sid</code> has
     *         been created using a principal, <code>false</code> if this has
     *         been created using a granted authority, and <code>null</code>
     *         if this instance has not been initialized properly.
     */
    public Boolean isPrincipal() {
        return this.getIsPrincipal();
    }

    private Boolean getIsPrincipal() {
        return isPrincipal;
    }

    private void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    private String getSidName() {
        return sidName;
    }

    private void setSidName(String sidName) {
        this.sidName = sidName;
    }

    /**
     * Gets the <code>Sid</code> as a <code>String</code> if this instance
     * has been created using a principal.
     * 
     * @return the principal
     * @throws UnsupportedOperationException
     *             if this instance of Sid is not a principal
     */
    public String getPrincipal() {
        if (this.getIsPrincipal() == null) {
            throw new IllegalStateException();
        }
        if (this.getIsPrincipal()) {
            return this.getSidName();
        }
        throw new UnsupportedOperationException(
                "Unsupported method for this instance of Sid");
    }

    /**
     * Sets the <code>Sid</code> using an <code>Authentication</code>
     * principal.
     * 
     * @param authentication
     *            to set
     */
    public void setPrincipal(Authentication authentication) {
        this.setIsPrincipal(Boolean.TRUE);
        if (authentication.getPrincipal() instanceof UserDetails) {
            this.setSidName(((UserDetails) authentication.getPrincipal())
                    .getUsername());
        } else {
            this.setSidName(authentication.getPrincipal().toString());
        }
    }

    /**
     * Sets the <code>Sid</code> using a <code>GrantedAuthority</code>.
     * 
     * @param grantedAuthority
     *            to set
     */
    public void setGrantedAuthority(GrantedAuthority grantedAuthority) {
        this.setIsPrincipal(Boolean.FALSE);
        this.setSidName(grantedAuthority.getAuthority());
    }

    /**
     * Gets the <code>Sid</code> as a <code>String</code> if this instance
     * has been created using a granted authority.
     * 
     * @return the granted authority
     * @throws UnsupportedOperationException
     *             if this instance of Sid is not a granted authority
     */
    public String getGrantedAuthority() {
        if (this.getIsPrincipal() == null) {
            throw new IllegalStateException();
        }
        if (this.getIsPrincipal()) {
            throw new UnsupportedOperationException(
                    "Unsupported method for this instance of Sid");
        } else {
            return this.getSidName();
        }
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
                + ((isPrincipal == null) ? 0 : isPrincipal.hashCode());
        result = PRIME * result + ((sidName == null) ? 0 : sidName.hashCode());
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
        final PersistentAclSid other = (PersistentAclSid) obj;
        if (isPrincipal == null) {
            if (other.isPrincipal != null)
                return false;
        } else if (!isPrincipal.equals(other.isPrincipal))
            return false;
        if (sidName == null) {
            if (other.sidName != null)
                return false;
        } else if (!sidName.equals(other.sidName))
            return false;
        return true;
    }
}