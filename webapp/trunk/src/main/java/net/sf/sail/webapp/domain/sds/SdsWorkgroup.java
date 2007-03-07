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
package net.sf.sail.webapp.domain.sds;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class SdsWorkgroup implements SdsObject {

    private static final long serialVersionUID = 1L;

    private Integer sdsObjectId;

    private String name;

    private SdsOffering sdsOffering;

    private Set<SdsUser> members = new HashSet<SdsUser>();

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see net.sf.sail.webapp.domain.sds.SdsObject#getSdsObjectId()
     */
    public Integer getSdsObjectId() {
        return this.sdsObjectId;
    }

    /**
     * @see net.sf.sail.webapp.domain.sds.SdsObject#setSdsObjectId(java.lang.Integer)
     */
    public void setSdsObjectId(Integer sdsObjectId) {
        this.sdsObjectId = sdsObjectId;
    }

    /**
     * @return the sdsOffering
     */
    public SdsOffering getSdsOffering() {
        return this.sdsOffering;
    }

    /**
     * @param sdsOffering
     *            the sdsOffering to set
     */
    public void setSdsOffering(SdsOffering sdsOffering) {
        this.sdsOffering = sdsOffering;
    }

    /**
     * @return the members
     */
    public Set<SdsUser> getMembers() {
        return this.members;
    }

    /**
     * @param members
     *            the members to set
     */
    public void setMembers(Set<SdsUser> members) {
        this.members = members;
    }

    /**
     * Adds a single <code>SdsUser</code> to a set of workgroup members.
     * 
     * @param member
     *            to be added to the workgroup
     */
    public void addMember(SdsUser member) {
        this.members.add(member);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((this.members == null) ? 0 : this.members.hashCode());
        result = PRIME * result
                + ((this.name == null) ? 0 : this.name.hashCode());
        result = PRIME
                * result
                + ((this.sdsObjectId == null) ? 0 : this.sdsObjectId.hashCode());
        result = PRIME
                * result
                + ((this.sdsOffering == null) ? 0 : this.sdsOffering.hashCode());
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
        final SdsWorkgroup other = (SdsWorkgroup) obj;
        if (this.members == null) {
            if (other.members != null)
                return false;
        } else if (!this.members.equals(other.members))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.sdsObjectId == null) {
            if (other.sdsObjectId != null)
                return false;
        } else if (!this.sdsObjectId.equals(other.sdsObjectId))
            return false;
        if (this.sdsOffering == null) {
            if (other.sdsOffering != null)
                return false;
        } else if (!this.sdsOffering.equals(other.sdsOffering))
            return false;
        return true;
    }
}