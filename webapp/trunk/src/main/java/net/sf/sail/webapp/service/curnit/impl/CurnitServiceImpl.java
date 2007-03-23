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
package net.sf.sail.webapp.service.curnit.impl;

import java.util.Set;

import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.service.curnit.CurnitService;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImpl implements CurnitService {

	private SdsCurnitDao sdsCurnitDao;

	/**
	 * @param sdsCurnitDao
	 *            the sdsCurnitDao to set
	 */
	@Required
	public void setSdsCurnitDao(SdsCurnitDao sdsCurnitDao) {
		this.sdsCurnitDao = sdsCurnitDao;
	}

	/**
	 * @see net.sf.sail.webapp.service.curnit.CurnitService#createCurnit(net.sf.sail.webapp.domain.sds.SdsCurnit)
	 */
	public void createCurnit(SdsCurnit sdsCurnit) {
		this.sdsCurnitDao.save(sdsCurnit);
	}

	/**
	 * @see net.sf.sail.webapp.service.curnit.CurnitService#getCurnitList()
	 */
	public Set<SdsCurnit> getCurnitList() {
		return this.sdsCurnitDao.getList();
	}

}
