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
@Table(name = PersistentSid.DATA_STORE_NAME)
public class PersistentSid implements Sid {

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

    @Column(name = PersistentSid.COLUMN_NAME_IS_PRINCIPAL, nullable = false)
    private Boolean isPrincipal;

    @Column(name = PersistentSid.COLUMN_NAME_SID, unique = true, nullable = false)
    private String sidName;

    /**
     * @return the isPrincipal
     */
    public Boolean getIsPrincipal() {
        return isPrincipal;
    }

    /**
     * @param isPrincipal
     *            the isPrincipal to set
     */
    private void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    /**
     * @return the sidName
     */
    private String getSidName() {
        return sidName;
    }

    /**
     * @param sidName
     *            the sidName to set
     */
    private void setSidName(String sidName) {
        this.sidName = sidName;
    }

    /**
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

    public void setPrincipal(Authentication authentication) {
        this.setIsPrincipal(Boolean.TRUE);
        if (authentication.getPrincipal() instanceof UserDetails) {
            this.setSidName(((UserDetails) authentication.getPrincipal())
                    .getUsername());
        } else {
            this.setSidName(authentication.getPrincipal().toString());
        }
    }

    public void setGrantedAuthority(GrantedAuthority grantedAuthority) {
        this.setIsPrincipal(Boolean.FALSE);
        this.setSidName(grantedAuthority.getAuthority());
    }

    /**
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
        final PersistentSid other = (PersistentSid) obj;
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