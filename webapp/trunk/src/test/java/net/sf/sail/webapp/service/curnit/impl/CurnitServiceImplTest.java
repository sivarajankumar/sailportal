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

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.curnit.CurnitDao;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitParameters;
import net.sf.sail.webapp.domain.sds.SdsCurnit;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImplTest extends TestCase {

	private static final String CURNIT_NAME = "name";
	private static final String CURNIT_URL = "url";
	
    private SdsCurnitDao mockSdsCurnitDao;
    private CurnitDao<Curnit> mockCurnitDao;
 
     private CurnitServiceImpl curnitServiceImpl;
     
    /**
     * @see junit.framework.TestCase#setUp()
     */
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.curnitServiceImpl = new CurnitServiceImpl();

        this.mockSdsCurnitDao = EasyMock.createMock(SdsCurnitDao.class);
        this.curnitServiceImpl.setSdsCurnitDao(this.mockSdsCurnitDao);

        this.mockCurnitDao = EasyMock.createMock(CurnitDao.class);
        this.curnitServiceImpl.setCurnitDao(this.mockCurnitDao);
        
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.curnitServiceImpl = null;
        this.mockSdsCurnitDao = null;
        this.mockCurnitDao = null;
    }

    public void testGetCurnitList() throws Exception {
        List<SdsCurnit> expectedList = new LinkedList<SdsCurnit>();
        expectedList.add(new SdsCurnit());

        EasyMock.expect(mockSdsCurnitDao.getList()).andReturn(expectedList);
        EasyMock.replay(mockSdsCurnitDao);
        assertEquals(expectedList, curnitServiceImpl.getCurnitList());
        EasyMock.verify(mockSdsCurnitDao);
    }

    public void testCreateCurnit() throws Exception {   
    	//TODO LAW can we get curnitParameters from bean?
        CurnitParameters curnitParameters = new CurnitParameters();
        curnitParameters.setName(CURNIT_NAME);
        curnitParameters.setUrl(CURNIT_URL);

        Curnit curnit = this.curnitServiceImpl.createCurnit(curnitParameters);

        SdsCurnit actualSdsCurnit = curnit.getSdsCurnit();
        assertEquals(CURNIT_NAME, actualSdsCurnit.getName());
        assertEquals(CURNIT_URL, actualSdsCurnit.getUrl());
    }


}