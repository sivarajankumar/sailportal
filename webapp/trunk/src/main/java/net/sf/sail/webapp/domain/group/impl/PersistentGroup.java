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
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.Path;

/**
 * @author Cynick Young
 *
 * @version $Id$
 */
public class PersistentGroup implements Group {

    private List<User> members = new ArrayList<User>();
    private Path path;
    
    /**
     * @see net.sf.sail.webapp.domain.group.Group#addMember(net.sf.sail.webapp.domain.User)
     */
    public void addMember(User member) {
        if (this.members.contains(member)) {
            return;
        }
        this.members.add(member);
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Group#getMembers()
     */
    public List<User> getMembers() {
        return this.members;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Group#getPath()
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Group#setMembers(java.util.List)
     */
    public void setMembers(List<User> members) {
        this.members = members;
    }

    /**
     * @see net.sf.sail.webapp.domain.group.Group#setPath(net.sf.sail.webapp.domain.group.Path)
     */
    public void setPath(Path path) {
        this.path = path;
    }

}
