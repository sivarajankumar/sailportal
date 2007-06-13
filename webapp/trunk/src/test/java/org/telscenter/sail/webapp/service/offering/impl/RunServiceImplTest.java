/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.service.offering.impl;

import java.util.LinkedList;
import java.util.List;

import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.easymock.EasyMock;
import org.telscenter.sail.webapp.dao.offering.RunDao;
import org.telscenter.sail.webapp.domain.impl.Run;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test class for RunServiceImpl class
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class RunServiceImplTest extends AbstractTransactionalDbTests {

    private SdsOfferingDao mockSdsOfferingDao;

    private SdsOffering sdsOffering;

    private RunDao<Run> mockOfferingDao;

    private Run run;

    private RunServiceImpl runServiceImpl;

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpInTransaction()
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        
        this.runServiceImpl = new RunServiceImpl();

        this.mockSdsOfferingDao = EasyMock.createMock(SdsOfferingDao.class);
        this.runServiceImpl.setSdsOfferingDao(this.mockSdsOfferingDao);

        this.mockOfferingDao = EasyMock.createMock(RunDao.class);
        this.runServiceImpl.setOfferingDao(this.mockOfferingDao);

        this.sdsOffering = new SdsOffering();
        this.run = new Run();
        this.run.setSdsOffering(this.sdsOffering);
    }

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
     */
    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
        this.runServiceImpl = null;
        this.mockSdsOfferingDao = null;
        this.sdsOffering = null;
        this.mockOfferingDao = 	null;
        this.run = null;
    }

    public void testGetRunList() throws Exception {
        List<Offering> expectedList = new LinkedList<Offering>();
        expectedList.add(this.run);

        EasyMock.expect(this.mockOfferingDao.getList()).andReturn(expectedList);
        EasyMock.replay(this.mockOfferingDao);
        assertEquals(expectedList, runServiceImpl.getRunList());
        EasyMock.verify(this.mockOfferingDao);
    }

    // tests that the command is delegated to the DAOs and that the DAOs are
    // called once
    public void testCreateRun() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockSdsOfferingDao);

        this.mockOfferingDao.hasRuncode(EasyMock.isA(String.class));
        EasyMock.expectLastCall().andReturn(false);

        this.mockOfferingDao.save(this.run);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockOfferingDao);

        runServiceImpl.createRun(this.run);

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }

    public void testCreateRun_BadRequestException() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall().andThrow(
                new BadRequestException("bad request"));
        EasyMock.replay(this.mockSdsOfferingDao);

        this.mockOfferingDao.hasRuncode(EasyMock.isA(String.class));
        EasyMock.expectLastCall().andReturn(false);
        
        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockOfferingDao);

        try {
            this.runServiceImpl.createRun(this.run);
            fail("BadRequestException expected");
        } catch (BadRequestException expected) {
        }

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }

    public void testCreateRun_NetworkTransportException() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall().andThrow(
                new NetworkTransportException("network transport exception"));
        EasyMock.replay(this.mockSdsOfferingDao);

        this.mockOfferingDao.hasRuncode(EasyMock.isA(String.class));
        EasyMock.expectLastCall().andReturn(false);
        
        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockOfferingDao);

        try {
            this.runServiceImpl.createRun(this.run);
            fail("NetworkTransportException expected");
        } catch (NetworkTransportException expected) {
        }

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }
    
    // TODO: test when duplicate runcode is generated by the 
    // runcode generator
}
