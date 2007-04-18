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

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.curnit.CurnitDao;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImplTest extends TestCase {

    private SdsCurnitDao mockSdsCurnitDao;

    private SdsCurnit sdsCurnit;

    private CurnitDao<Curnit> mockCurnitDao;

    private Curnit curnit;

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

        this.sdsCurnit = new SdsCurnit();

        this.curnit = new CurnitImpl();
        this.curnit.setSdsCurnit(this.sdsCurnit);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.curnitServiceImpl = null;
        this.mockSdsCurnitDao = null;
        this.sdsCurnit = null;
        this.mockCurnitDao = null;
        this.curnit = null;
    }

    public void testGetCurnitList() throws Exception {
        Set<SdsCurnit> expectedSdsCurnitSet = new HashSet<SdsCurnit>();
        expectedSdsCurnitSet.add(this.sdsCurnit);

        EasyMock.expect(mockSdsCurnitDao.getList()).andReturn(
                expectedSdsCurnitSet);
        EasyMock.replay(mockSdsCurnitDao);
        assertEquals(expectedSdsCurnitSet, curnitServiceImpl.getCurnitList());
        EasyMock.verify(mockSdsCurnitDao);
    }

    // tests that the command is delegated to the DAOs and that the DAOs are
    // called once
    public void testCreateCurnit() throws Exception {
        this.mockSdsCurnitDao.save(this.sdsCurnit);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockSdsCurnitDao);

        this.mockCurnitDao.save(this.curnit);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockCurnitDao);

        this.curnitServiceImpl.createCurnit(this.curnit);

        EasyMock.verify(this.mockSdsCurnitDao);
        EasyMock.verify(this.mockCurnitDao);
    }

    public void testCreateCurnit_BadRequestException() throws Exception {
        this.mockSdsCurnitDao.save(this.sdsCurnit);
        EasyMock.expectLastCall().andThrow(
                new BadRequestException("bad request"));
        EasyMock.replay(this.mockSdsCurnitDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockCurnitDao);

        try {
            this.curnitServiceImpl.createCurnit(this.curnit);
            fail("BadRequestException expected");
        } catch (BadRequestException expected) {
        }

        EasyMock.verify(this.mockSdsCurnitDao);
        EasyMock.verify(this.mockCurnitDao);
    }

    public void testCreateCurnit_NetworkTransportException() throws Exception {
        this.mockSdsCurnitDao.save(this.sdsCurnit);
        EasyMock.expectLastCall().andThrow(
                new NetworkTransportException("network transport exception"));
        EasyMock.replay(this.mockSdsCurnitDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockCurnitDao);

        try {
            this.curnitServiceImpl.createCurnit(this.curnit);
            fail("NetworkTransportException expected");
        } catch (NetworkTransportException expected) {
        }

        EasyMock.verify(this.mockSdsCurnitDao);
        EasyMock.verify(this.mockCurnitDao);
    }
}