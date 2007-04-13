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
package net.sf.sail.webapp.service.offerings;

import java.util.Set;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.sds.SdsOffering;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public interface OfferingService {

    /**
     * Gets a <code>Set</code> of SDS offerings.
     * 
     * @return a <code>Set</code> of SDS offerings.
     */
    public Set<SdsOffering> getOfferingsList();

    /**
     * Creates a new <code>SdsOffering</code> on the SDS as well as an
     * <code>Offering</code> object in the local data store. A side effect is
     * that the offering id is set to the value that the SDS assigns to the new
     * offering.
     * 
     * @param offering
     *            The offering you want to create (no offering id required).
     */
    public void createOffering(Offering offering);

}