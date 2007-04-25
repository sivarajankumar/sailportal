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
package net.sf.sail.webapp.service.workgroup.impl;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsWorkgroupDao;
import net.sf.sail.webapp.dao.workgroup.WorkgroupDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.impl.WorkgroupImpl;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class WorkgroupServiceImplTest extends TestCase {

    private SdsWorkgroupDao mockSdsWorkgroupDao;

    private SdsWorkgroup sdsWorkgroup;

    private WorkgroupDao<Workgroup> mockWorkgroupDao;

    private Workgroup workgroup;

    private WorkgroupServiceImpl workgroupServiceImpl;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.workgroupServiceImpl = new WorkgroupServiceImpl();

        this.mockSdsWorkgroupDao = EasyMock.createMock(SdsWorkgroupDao.class);
        this.workgroupServiceImpl.setSdsWorkgroupDao(this.mockSdsWorkgroupDao);

        this.mockWorkgroupDao = EasyMock.createMock(WorkgroupDao.class);
        this.workgroupServiceImpl.setWorkgroupDao(this.mockWorkgroupDao);

        this.sdsWorkgroup = new SdsWorkgroup();

        this.workgroup = new WorkgroupImpl();
        this.workgroup.setSdsWorkgroup(this.sdsWorkgroup);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.workgroupServiceImpl = null;
        this.mockSdsWorkgroupDao = null;
        this.sdsWorkgroup = null;
        this.mockWorkgroupDao = null;
        this.workgroup = null;
    }

    public void testCreatePreviewWorkgroupForOfferingIfNecessary_Necessary() {
        Offering expectedOffering = new OfferingImpl();
        User expectedUser = new UserImpl();
        List<Workgroup> inputList = new LinkedList<Workgroup>();

        this.mockSdsWorkgroupDao.save(null);
        EasyMock.expectLastCall();
        this.mockWorkgroupDao.save(null);
        EasyMock.expectLastCall();

        List<Workgroup> actualList = this.workgroupServiceImpl
                .createPreviewWorkgroupForOfferingIfNecessary(expectedOffering,
                        inputList, expectedUser, null);
        assertEquals(1, actualList.size());
        Workgroup actualWorkgroup = actualList.get(0);
        assertEquals(expectedOffering, actualWorkgroup.getOffering());
        assertEquals(1, actualWorkgroup.getMembers().size());
        assertTrue(actualWorkgroup.getMembers().contains(expectedUser));
    }

    public void testCreatePreviewWorkgroupForOfferingIfNecessary_NotNecessary() {
        List<Workgroup> expectedList = new LinkedList<Workgroup>();
        expectedList.add(this.workgroup);
        Offering expectedOffering = new OfferingImpl();

        EasyMock.replay(this.mockSdsWorkgroupDao);
        EasyMock.replay(this.mockWorkgroupDao);

        assertEquals(expectedList, this.workgroupServiceImpl
                .createPreviewWorkgroupForOfferingIfNecessary(expectedOffering,
                        expectedList, null, null));
        EasyMock.verify(this.mockSdsWorkgroupDao);
        EasyMock.verify(this.mockWorkgroupDao);
    }

    public void testGetWorkgroupListByOfferingAndUser() {
        List<Workgroup> expectedList = new LinkedList<Workgroup>();
        expectedList.add(this.workgroup);

        EasyMock.expect(
                this.mockWorkgroupDao.getListByOfferingAndUser(null, null))
                .andReturn(expectedList);
        EasyMock.replay(this.mockWorkgroupDao);
        assertEquals(expectedList, workgroupServiceImpl
                .getWorkgroupListByOfferingAndUser(null, null));
        EasyMock.verify(this.mockWorkgroupDao);
    }

    public void testGetWorkgroupList() throws Exception {
        List<Workgroup> expectedList = new LinkedList<Workgroup>();
        expectedList.add(this.workgroup);

        EasyMock.expect(this.mockWorkgroupDao.getList())
                .andReturn(expectedList);
        EasyMock.replay(this.mockWorkgroupDao);
        assertEquals(expectedList, workgroupServiceImpl.getWorkgroupList());
        EasyMock.verify(this.mockWorkgroupDao);
    }

    // tests that the command is delegated to the DAOs and that the DAOs are
    // called once
    public void testCreateWorkgroup() throws Exception {
        this.mockSdsWorkgroupDao.save(this.sdsWorkgroup);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockSdsWorkgroupDao);

        this.mockWorkgroupDao.save(this.workgroup);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockWorkgroupDao);

        this.workgroupServiceImpl.createWorkgroup(this.workgroup);

        EasyMock.verify(this.mockSdsWorkgroupDao);
        EasyMock.verify(this.mockWorkgroupDao);
    }

    public void testCreateWorkgroup_BadRequestException() throws Exception {
        this.mockSdsWorkgroupDao.save(this.sdsWorkgroup);
        EasyMock.expectLastCall().andThrow(
                new BadRequestException("bad request"));
        EasyMock.replay(this.mockSdsWorkgroupDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockWorkgroupDao);

        try {
            this.workgroupServiceImpl.createWorkgroup(this.workgroup);
            fail("BadRequestException expected");
        } catch (BadRequestException expected) {
        }

        EasyMock.verify(this.mockSdsWorkgroupDao);
        EasyMock.verify(this.mockWorkgroupDao);
    }

    public void testCreateWorkgroup_NetworkTransportException()
            throws Exception {
        this.mockSdsWorkgroupDao.save(this.sdsWorkgroup);
        EasyMock.expectLastCall().andThrow(
                new NetworkTransportException("network transport exception"));
        EasyMock.replay(this.mockSdsWorkgroupDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockWorkgroupDao);

        try {
            this.workgroupServiceImpl.createWorkgroup(this.workgroup);
            fail("NetworkTransportException expected");
        } catch (NetworkTransportException expected) {
        }

        EasyMock.verify(this.mockSdsWorkgroupDao);
        EasyMock.verify(this.mockWorkgroupDao);
    }
}