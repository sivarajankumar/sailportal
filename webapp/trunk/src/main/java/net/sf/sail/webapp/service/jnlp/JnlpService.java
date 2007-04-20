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
package net.sf.sail.webapp.service.jnlp;

import java.util.List;

import net.sf.sail.webapp.domain.Jnlp;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface JnlpService {

    /**
     * Gets a <code>List</code> that allows traversal of jnlps available.
     * 
     * @return a <code>List</code> of jnlps.
     */
    public List<Jnlp> getJnlpList();

    /**
     * Creates a new <code>SdsJnlp</code> on the SDS as well as a
     * <code>Jnlp</code> object in the local data store. A side effect is that
     * the sdsObjectId is set to the value that the SDS assigns to the new jnlp.
     * 
     * @param jnlp
     *            The jnlp you want to create (no jnlp id required).
     */
    public void createJnlp(Jnlp jnlp);

}