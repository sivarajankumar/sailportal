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

import java.util.ArrayList;
import java.util.List;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.IllegalPathException;
import net.sf.sail.webapp.domain.group.Path;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class PersistentPath implements Path {
    private static final long serialVersionUID = 1L;

    private String name;

    private Path parent;

    private List<User> owners = new ArrayList<User>();

    /**
     * @see net.sf.sail.webapp.domain.group.Path#addOwner(net.sf.sail.webapp.domain.User)
     */
    public void addOwner(User user) {
        if (owners.contains(user)) {
            return;
        }
        owners.add(user);
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Path#getOwners()
     */
    public List getOwners() {
        return this.owners;
    }

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