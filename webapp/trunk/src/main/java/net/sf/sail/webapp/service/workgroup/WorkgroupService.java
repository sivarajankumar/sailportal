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
package net.sf.sail.webapp.service.workgroup;

import java.util.Iterator;

import net.sf.sail.webapp.domain.Workgroup;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface WorkgroupService {

    /**
     * Gets an <code>Interator</code> that allows traversal of workgroups
     * available.
     * 
     * @return an <code>Iterator</code> of workgroups.
     */
    public Iterator<Workgroup> getWorkgroupIterator();

    /**
     * Creates a new <code>SdsWorkgroup</code> on the SDS as well as a
     * <code>Workgroup</code> object in the local data store. A side effect is
     * that the workgroup id is set to the value that the SDS assigns to the new
     * workgroup.
     * 
     * @param workgroup
     *            The workgroup you want to create (no workgroup id required).
     */
    public void createWorkgroup(Workgroup workgroup);
}