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
package net.sf.sail.webapp.domain.group;

import java.io.Serializable;
import java.util.List;

import net.sf.sail.webapp.domain.User;

/**
 * An interface that defines the concept of a hierarchical path in a tree
 * structure. The path is the route from the root node down to the current node.
 * The path can represent the tree structure where only the leaf nodes are full
 * node objects.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface Path extends Serializable {

    /**
     * Set the parent path of this current path.
     * 
     * @param path
     * @throws IllegalPathException
     *             if the parent has already been set or if trying to set the
     *             parent to itself.
     */
    public void setParent(Path path) throws IllegalPathException;

    /**
     * Get the parent path.
     * 
     * @return <code>Path</code> object that is the parent in a hierachy.
     */
    public Path getParent();

    /**
     * Set the name of the current path. The name may be modified at any time.
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * Get the name of the current path.
     * 
     * @return
     */
    public String getName();

    /**
     * Add the user who has permission to access this path.
     * 
     * @param user
     */
    public void addOwner(User user);

    /**
     * Get the list of users who have permission to access this path.
     * 
     * @return
     */
    public List getOwners();
}