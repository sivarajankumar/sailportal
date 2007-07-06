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
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.impl.CurnitParameters;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.service.curnit.CurnitNotFoundException;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImplTest extends TestCase {

	private static final String CURNIT_NAME = "name";
	private static final String CURNIT_URL = "url";
	private static final Long EXISTING_CURNIT_ID = new Long(10);
	private static final Long NONEXISTING_CURNIT_ID = new Long(103);
	
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
        List<Curnit> expectedList = new LinkedList<Curnit>();
        expectedList.add(new CurnitImpl());

        EasyMock.expect(mockCurnitDao.getList()).andReturn(expectedList);
        EasyMock.replay(mockCurnitDao);
        assertEquals(expectedList, curnitServiceImpl.getCurnitList());
        EasyMock.verify(mockCurnitDao);
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

    public void testGetById() throws Exception {
    	Curnit expectedCurnit = new CurnitImpl();
    	EasyMock.expect(mockCurnitDao.getById(EXISTING_CURNIT_ID)).andReturn(expectedCurnit);
    	EasyMock.replay(mockCurnitDao);
    	assertEquals(expectedCurnit, curnitServiceImpl.getById(EXISTING_CURNIT_ID));
    	EasyMock.verify(mockCurnitDao);
    	EasyMock.reset(mockCurnitDao);

    	// now check when curnit is not found
    	EasyMock.expect(mockCurnitDao.getById(NONEXISTING_CURNIT_ID))
    	        .andReturn(null);
    	EasyMock.replay(mockCurnitDao);
    	try {
    		curnitServiceImpl.getById(NONEXISTING_CURNIT_ID);
    		fail("CurnitNotFoundException expected but was not thrown");
    	} catch (CurnitNotFoundException e) {
    	}
    	EasyMock.verify(mockCurnitDao);
    }
    
    public void testChangeCurnitName() {
    	
    }

}