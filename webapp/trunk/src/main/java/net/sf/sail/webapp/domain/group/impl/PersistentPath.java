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
package net.sf.sail.webapp.domain.group.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.group.IllegalPathException;
import net.sf.sail.webapp.domain.group.Path;

/**
 * TODO: add comments to describe this works
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
@Entity
@Table(name = PersistentPath.DATA_STORE_NAME)
public class PersistentPath implements Path {

    @Transient
    public static final String DATA_STORE_NAME = "paths";
    
    @Transient
    public static final String COLUMN_NAME_NAME = "name";
    
    @Transient
    public static final String COLUMN_NAME_PARENT_FK = "parent_fk";

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@Version
	@Column(name = "OPTLOCK")
	private Integer version = null;
    
    @Column(name = PersistentPath.COLUMN_NAME_NAME, nullable = false)
    private String name;
 
    @OneToOne(targetEntity = PersistentPath.class, fetch = FetchType.EAGER)
    @JoinColumn(name = COLUMN_NAME_PARENT_FK)
    private Path parent;

    /**
     * @see net.sf.sail.webapp.domain.group.Path#getName()
     */
    public String getName() {
        return this.name;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Path#setName(java.lang.String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Path#getParent()
     */
    public Path getParent() {
        return this.parent;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Path#setParent(net.sf.sail.webapp.domain.group.Path)
     */
    public void setParent(Path path) {
        if (this.parent != null) {
            throw new IllegalPathException(
                    "Cannot set parent again after it has already been set.");
        }
        if (path.equals(this)) {
            throw new IllegalPathException("Cannot set parent to this object.");
        }
        this.parent = path;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (this.parent == null) ? "/" + this.getName() : this.parent
                .toString()
                + "/" + this.getName();
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
                + ((this.name == null) ? 0 : this.name.hashCode());
        result = PRIME * result
                + ((this.parent == null) ? 0 : this.parent.hashCode());
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
        final PersistentPath other = (PersistentPath) obj;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.parent == null) {
            if (other.parent != null)
                return false;
        } else if (!this.parent.equals(other.parent))
            return false;
        return true;
    }
}