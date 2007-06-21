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
package net.sf.sail.webapp.dao.sds.impl;

import java.util.List;

import net.sf.sail.webapp.dao.impl.AbstractDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingCreateCommand;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingListCommand;
import net.sf.sail.webapp.dao.sds.SdsOfferingUpdateCommand;
import net.sf.sail.webapp.domain.sds.SdsOffering;

import org.springframework.beans.factory.annotation.Required;

/**
 * HTTP REST implementation of <code>SdsOfferingDao</code> interface
 * supporting interactions with external SDS. This implementation uses finer
 * command objects to execute.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsOfferingDao extends AbstractDao<SdsOffering> implements
		SdsOfferingDao {

	private SdsOfferingListCommand listCommand;

	private SdsOfferingCreateCommand createCommand;

	private SdsOfferingUpdateCommand updateCommand;

	/**
	 * @param listCommand
	 *            the listCommand to set
	 */
	@Required
	public void setListCommand(SdsOfferingListCommand listCommand) {
		this.listCommand = listCommand;
	}

	/**
	 * @param createCommand
	 *            the createCommand to set
	 */
	@Required
	public void setCreateCommand(SdsOfferingCreateCommand createCommand) {
		this.createCommand = createCommand;
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsOfferingDao#getList()
	 */
	@SuppressWarnings("unchecked")
	public List<SdsOffering> getList() {
		return this.listCommand.execute(this.listCommand.generateRequest());
	}

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractDao#save(java.lang.Object)
	 */
	public void save(SdsOffering sdsOffering) {
		if (sdsOffering.getSdsObjectId() == null) {
			this.createCommand.setSdsOffering(sdsOffering);
			this.createCommand.execute(this.createCommand.generateRequest());
		} else {
			this.updateCommand.setSdsOffering(sdsOffering);
			this.updateCommand.execute(this.updateCommand.generateRequest());
		}
	}

	/**
	 * @param updateCommand
	 *            the updateCommand to set
	 */
	@Required
	public void setUpdateCommand(SdsOfferingUpdateCommand updateCommand) {
		this.updateCommand = updateCommand;
	}

}