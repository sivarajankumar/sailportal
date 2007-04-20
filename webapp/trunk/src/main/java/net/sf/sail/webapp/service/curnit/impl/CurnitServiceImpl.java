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

import java.util.List;

import net.sf.sail.webapp.dao.curnit.CurnitDao;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.curnit.CurnitService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImpl implements CurnitService {

    private SdsCurnitDao sdsCurnitDao;

    private CurnitDao<Curnit> curnitDao;

    /**
     * @param curnitDao
     *            the curnitDao to set
     */
    @Required
    public void setCurnitDao(CurnitDao<Curnit> curnitDao) {
        this.curnitDao = curnitDao;
    }

    /**
     * @param sdsCurnitDao
     *            the sdsCurnitDao to set
     */
    @Required
    public void setSdsCurnitDao(SdsCurnitDao sdsCurnitDao) {
        this.sdsCurnitDao = sdsCurnitDao;
    }

    /**
     * @see net.sf.sail.webapp.service.curnit.CurnitService#createCurnit(Curnit)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
    public void createCurnit(Curnit curnit) {
        this.sdsCurnitDao.save(curnit.getSdsCurnit());
        this.curnitDao.save(curnit);
    }

    /**
     * @see net.sf.sail.webapp.service.curnit.CurnitService#getCurnitList()
     */
    public List<SdsCurnit> getCurnitList() {
        return this.sdsCurnitDao.getList();
    }

}