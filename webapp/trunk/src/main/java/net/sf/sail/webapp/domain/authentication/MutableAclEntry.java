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
package net.sf.sail.webapp.domain.authentication;

import java.io.Serializable;

/**
 * Represents the Access Control List (ACL) entry (ACE) that grants permissions
 * to a Security ID (SID) for a particular secured object.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 */
public interface MutableAclEntry extends Serializable {

    /**
     * @return the aceOrder
     */
    public abstract Integer getAceOrder();

    /**
     * @param aceOrder
     *                the aceOrder to set
     */
    public abstract void setAceOrder(Integer aceOrder);

    /**
     * @return the auditFailure
     */
    public abstract Boolean getAuditFailure();

    /**
     * @param auditFailure
     *                the auditFailure to set
     */
    public abstract void setAuditFailure(Boolean auditFailure);

    /**
     * @return the auditSuccess
     */
    public abstract Boolean getAuditSuccess();

    /**
     * @param auditSuccess
     *                the auditSuccess to set
     */
    public abstract void setAuditSuccess(Boolean auditSuccess);

    /**
     * @return the granting
     */
    public abstract Boolean getGranting();

    /**
     * @param granting
     *                the granting to set
     */
    public abstract void setGranting(Boolean granting);

    /**
     * @return the mask
     */
    public abstract Integer getMask();

    /**
     * @param mask
     *                the mask to set
     */
    public abstract void setMask(Integer mask);

    /**
     * @return the sid
     */
    public abstract MutableAclSid getSid();

    /**
     * @param sid
     *                the sid to set
     */
    public abstract void setSid(MutableAclSid sid);

    /**
     * @return the targetObjectIdentity
     */
    public abstract MutableAclTargetObjectIdentity getTargetObjectIdentity();

    /**
     * @param targetObjectIdentity
     *                the targetObjectIdentity to set
     */
    public abstract void setTargetObjectIdentity(
            MutableAclTargetObjectIdentity targetObjectIdentity);

}