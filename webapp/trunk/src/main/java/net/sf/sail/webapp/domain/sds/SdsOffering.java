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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Represents an offering from the Sail Data Service (SDS).
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
@Entity
@Table(name = SdsOffering.DATA_STORE_NAME)
public class SdsOffering implements SdsObject {

    @Transient
    public static final String DATA_STORE_NAME = "sds_offerings";

    @Transient
    public static final String COLUMN_NAME_OFFERING_ID = "offering_id";

    @Transient
    public static final String COLUMN_NAME_OFFERING_NAME = "name";

    @Transient
    public static final String COLUMN_NAME_SDS_CURNIT_FK = "sds_curnit_fk";

    @Transient
    public static final String COLUMN_NAME_SDS_JNLP_FK = "sds_jnlp_fk";

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;

    @Column(name = SdsOffering.COLUMN_NAME_OFFERING_NAME, nullable = false)
    private String name;

    @OneToOne(targetEntity = SdsCurnit.class)
    @JoinColumn(name = SdsOffering.COLUMN_NAME_SDS_CURNIT_FK, nullable = false)
    private SdsCurnit curnit;

    @OneToOne(targetEntity = SdsJnlp.class)
    @JoinColumn(name = SdsOffering.COLUMN_NAME_SDS_JNLP_FK, nullable = false)
    private SdsJnlp jnlp;

    @Column(name = SdsOffering.COLUMN_NAME_OFFERING_ID, unique = true, nullable = false)
    private Integer sdsObjectId;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the curnit
     */
    public SdsCurnit getCurnit() {
        return curnit;
    }

    /**
     * @param curnit
     *            the curnit to set
     */
    public void setCurnit(SdsCurnit curnit) {
        this.curnit = curnit;
    }

    /**
     * @return the jnlp
     */
    public SdsJnlp getJnlp() {
        return jnlp;
    }

    /**
     * @param jnlp
     *            the jnlp to set
     */
    public void setJnlp(SdsJnlp jnlp) {
        this.jnlp = jnlp;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((curnit == null) ? 0 : curnit.hashCode());
        result = PRIME * result + ((jnlp == null) ? 0 : jnlp.hashCode());
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
        if (curnit == null) {
            if (other.curnit != null)
                return false;
        } else if (!curnit.equals(other.curnit))
            return false;
        if (jnlp == null) {
            if (other.jnlp != null)
                return false;
        } else if (!jnlp.equals(other.jnlp))
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
}