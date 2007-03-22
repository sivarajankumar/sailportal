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

/**
 * Represents an offering from the Sail Data Service (SDS). The object is not
 * persisted but is intended to be obtained on the fly.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class SdsOffering implements SdsObject {

//	TODO Laurel These should be sdsobjects not integer curnit id, jnlp id, etc.
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer curnitId;

    private Integer sdsObjectId;

    private Integer jnlpId;

    /**
     * @param curnitId
     *            the curnitId to set
     */
    public void setCurnitId(Integer curnitId) {
        this.curnitId = curnitId;
    }

    /**
     * @param jnlpId
     *            the jnlpId to set
     */
    public void setJnlpId(Integer jnlpId) {
        this.jnlpId = jnlpId;
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
    public void setSdsObjectId(Integer id) {
        this.sdsObjectId = id;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((curnitId == null) ? 0 : curnitId.hashCode());
        result = PRIME * result + ((jnlpId == null) ? 0 : jnlpId.hashCode());
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result
                + ((sdsObjectId == null) ? 0 : sdsObjectId.hashCode());
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
        final SdsOffering other = (SdsOffering) obj;
        if (curnitId == null) {
            if (other.curnitId != null)
                return false;
        } else if (!curnitId.equals(other.curnitId))
            return false;
        if (jnlpId == null) {
            if (other.jnlpId != null)
                return false;
        } else if (!jnlpId.equals(other.jnlpId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (sdsObjectId == null) {
            if (other.sdsObjectId != null)
                return false;
        } else if (!sdsObjectId.equals(other.sdsObjectId))
            return false;
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

	/**
	 * @return the curnitId
	 */
	public Integer getCurnitId() {
		return curnitId;
	}

	/**
	 * @return the jnlpId
	 */
	public Integer getJnlpId() {
		return jnlpId;
	}
}