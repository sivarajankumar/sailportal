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
import net.sf.sail.webapp.dao.sds.SdsUserCreateCommand;
import net.sf.sail.webapp.dao.sds.SdsUserDao;
import net.sf.sail.webapp.domain.sds.SdsUser;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsUserDao extends AbstractDao<SdsUser> implements
        SdsUserDao {

    private SdsUserCreateCommand createCommand;

    /**
     * @param createCommand
     *            the createCommand to set
     */
    public void setCreateCommand(SdsUserCreateCommand createCommand) {
        this.createCommand = createCommand;
    }

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractDao#save(java.lang.Object)
     */
    public void save(SdsUser sdsUser) {
        this.createCommand.setSdsUser(sdsUser);
        this.createCommand.execute(this.createCommand.generateRequest());
        // TODO - when update command for SDS is written, need to differentiate
        // between create and update
    }

    /**
     * @see net.sf.sail.webapp.dao.SimpleDao#createDataObject()
     */
    public SdsUser createDataObject() {
        return new SdsUser();
    }
}