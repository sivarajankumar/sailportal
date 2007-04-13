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
package net.sf.sail.webapp.service.offering.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.offering.OfferingDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.offering.impl.OfferingServiceImpl;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingServiceImplTest extends TestCase {

    private SdsOfferingDao mockSdsOfferingDao;

    private SdsOffering sdsOffering;

    private OfferingDao mockOfferingDao;

    private Offering offering;

    private OfferingServiceImpl offeringServiceImpl;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.offeringServiceImpl = new OfferingServiceImpl();

        this.mockSdsOfferingDao = EasyMock.createMock(SdsOfferingDao.class);
        this.offeringServiceImpl.setSdsOfferingDao(this.mockSdsOfferingDao);

        this.mockOfferingDao = EasyMock.createMock(OfferingDao.class);
        this.offeringServiceImpl.setOfferingDao(this.mockOfferingDao);

        this.sdsOffering = new SdsOffering();
        this.offering = new OfferingImpl();
        this.offering.setSdsOffering(this.sdsOffering);
        // sdsOffering.setSdsObjectId(3);
        // SdsCurnit curnit = new SdsCurnit();
        // curnit.setSdsObjectId(1);
        // sdsOffering.setCurnit(curnit);

        // SdsJnlp jnlp = new SdsJnlp();
        // jnlp.setSdsObjectId(2);
        // sdsOffering.setJnlp(jnlp);
        // sdsOffering.setName("test");
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.offeringServiceImpl = null;
        this.mockSdsOfferingDao = null;
        this.sdsOffering = null;
        this.mockOfferingDao = null;
        this.offering = null;
    }

    public void testGetOfferingsList() throws Exception {
        Set<SdsOffering> expectedSdsOfferingSet = new HashSet<SdsOffering>();
        expectedSdsOfferingSet.add(this.sdsOffering);

        EasyMock.expect(mockSdsOfferingDao.getList()).andReturn(
                expectedSdsOfferingSet);
        EasyMock.replay(mockSdsOfferingDao);
        assertEquals(expectedSdsOfferingSet, offeringServiceImpl
                .getOfferingsList());
        EasyMock.verify(mockSdsOfferingDao);
    }

    // tests that the command is delegated to the DAOs and that the DAOs are
    // called once
    @SuppressWarnings("unchecked")
    public void testCreateOffering() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockSdsOfferingDao);

        this.mockOfferingDao.save(this.offering);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockOfferingDao);

        offeringServiceImpl.createOffering(this.offering);

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }

    public void testCreateOffering_BadRequestException() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall().andThrow(
                new BadRequestException("bad request"));
        EasyMock.replay(this.mockSdsOfferingDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockOfferingDao);

        try {
            this.offeringServiceImpl.createOffering(this.offering);
            fail("BadRequestException expected");
        } catch (BadRequestException expected) {
        }

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }

    public void testCreateOffering_NetworkTransportException() throws Exception {
        this.mockSdsOfferingDao.save(this.sdsOffering);
        EasyMock.expectLastCall().andThrow(
                new NetworkTransportException("network transport exception"));
        EasyMock.replay(this.mockSdsOfferingDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockOfferingDao);

        try {
            this.offeringServiceImpl.createOffering(this.offering);
            fail("NetworkTransportException expected");
        } catch (NetworkTransportException expected) {
        }

        EasyMock.verify(this.mockSdsOfferingDao);
        EasyMock.verify(this.mockOfferingDao);
    }
}