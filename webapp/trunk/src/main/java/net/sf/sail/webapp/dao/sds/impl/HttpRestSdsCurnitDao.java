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
import net.sf.sail.webapp.dao.sds.SdsCurnitCreateCommand;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.dao.sds.SdsCurnitListCommand;
import net.sf.sail.webapp.domain.sds.SdsCurnit;

import org.springframework.beans.factory.annotation.Required;

/**
 * HTTP REST implementation of <code>SdsCurnitDao</code> interface supporting
 * interactions with external SDS. This implementation uses finer command
 * objects to execute.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
public class HttpRestSdsCurnitDao extends AbstractDao<SdsCurnit> implements
        SdsCurnitDao {

    private SdsCurnitCreateCommand createCommand;

    private SdsCurnitListCommand listCommand;

    /**
     * @param createCommand
     *            the createCommand to set
     */
    @Required
    public void setCreateCommand(SdsCurnitCreateCommand createCommand) {
        this.createCommand = createCommand;
    }

    /**
     * @param listCommand
     *            the listCommand to set
     */
    @Required
    public void setListCommand(SdsCurnitListCommand listCommand) {
        this.listCommand = listCommand;
    }

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractDao#save(java.lang.Object)
     */
    public void save(SdsCurnit sdsCurnit) {
        this.createCommand.setSdsCurnit(sdsCurnit);
        this.createCommand.execute(this.createCommand.generateRequest());
        // TODO CY - when update command for SDS is written, need to
        // differentiate between create and update
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCurnitDao#getList()
     */
    public List<SdsCurnit> getList() {
        return this.listCommand.execute(this.listCommand.generateRequest());
    }

}