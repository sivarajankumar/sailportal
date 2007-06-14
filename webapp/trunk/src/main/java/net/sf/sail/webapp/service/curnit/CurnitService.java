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
package net.sf.sail.webapp.service.curnit;

import java.util.List;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitParameters;
import net.sf.sail.webapp.domain.sds.SdsCurnit;

/**
 * A service for working with Curnits.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public interface CurnitService {

	/**
	 * Gets a <code>List</code> of curnits.
	 * 
	 * @return a <code>List</code> of curnits.
	 */
	public List<SdsCurnit> getCurnitList();


	/**
	 * Creates a new <code>SdsCurnit</code> on the SDS as well as a
	 * <code>Curnit</code> object in the local data store. A side effect is
	 * that the sdsObjectId is set to the value that the SDS assigns to the new
	 * curnit.
	 * 
	 * @param curnitParameters
	 *            The curnit parameters (name and url)
	 * @return the curnit
	 */
	public Curnit createCurnit(CurnitParameters curnitParameters);

}