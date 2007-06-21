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

import net.sf.sail.webapp.dao.impl.AbstractDao;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupDao;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupMemberCreateCommand;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupUpdateCommand;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;

import org.springframework.beans.factory.annotation.Required;

/**
 * HTTP REST implementation of <code>SdsWorkgroupDao</code> interface
 * supporting interactions with external SDS. This implementation uses finer
 * command objects to execute.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsWorkgroupDao extends AbstractDao<SdsWorkgroup>
		implements SdsWorkgroupDao {

	private SdsWorkgroupCreateCommand createCommand;

	private SdsWorkgroupUpdateCommand updateCommand;

	private SdsWorkgroupMemberCreateCommand membershipCreateCommand;

	/**
	 * @param updateCommand
	 *            the updateCommand to set
	 */
	@Required
	public void setUpdateCommand(SdsWorkgroupUpdateCommand updateCommand) {
		this.updateCommand = updateCommand;
	}

	/**
	 * @param membershipCreateCommand
	 *            the membershipCreateCommand to set
	 */
	@Required
	public void setMembershipCreateCommand(
			SdsWorkgroupMemberCreateCommand membershipCreateCommand) {
		this.membershipCreateCommand = membershipCreateCommand;
	}

	/**
	 * @param createCommand
	 *            the createCommand to set
	 */
	@Required
	public void setCreateCommand(SdsWorkgroupCreateCommand createCommand) {
		this.createCommand = createCommand;
	}

	/**
	 * @see net.sf.sail.webapp.dao.SimpleDao#save(java.lang.Object)
	 */
	public void save(SdsWorkgroup workgroup) {
		if (workgroup.getSdsObjectId() == null) {
			this.createCommand.setSdsWorkgroup(workgroup);
			this.createCommand.execute(this.createCommand.generateRequest());
		} else {
			this.updateCommand.setSdsWorkgroup(workgroup);
			this.updateCommand.execute(this.updateCommand.generateRequest());
		}
		if (!workgroup.getMembers().isEmpty()) {
			// after saving or updating the workgroup, save the membership as
			// well
			this.membershipCreateCommand.setWorkgroup(workgroup);
			this.membershipCreateCommand.execute(this.membershipCreateCommand
					.generateRequest());
		}
	}
	
}