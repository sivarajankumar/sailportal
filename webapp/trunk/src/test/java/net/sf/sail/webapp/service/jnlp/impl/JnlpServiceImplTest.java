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
package net.sf.sail.webapp.service.jnlp.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.jnlp.JnlpDao;
import net.sf.sail.webapp.dao.sds.SdsJnlpDao;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.impl.JnlpImpl;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class JnlpServiceImplTest extends TestCase {

    private SdsJnlpDao mockSdsJnlpDao;

    private SdsJnlp sdsJnlp;

    private JnlpDao mockJnlpDao;

    private Jnlp jnlp;

    private JnlpServiceImpl jnlpServiceImpl;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.jnlpServiceImpl = new JnlpServiceImpl();

        this.mockSdsJnlpDao = EasyMock.createMock(SdsJnlpDao.class);
        this.jnlpServiceImpl.setSdsJnlpDao(this.mockSdsJnlpDao);

        this.mockJnlpDao = EasyMock.createMock(JnlpDao.class);
        this.jnlpServiceImpl.setJnlpDao(this.mockJnlpDao);

        this.sdsJnlp = new SdsJnlp();

        this.jnlp = new JnlpImpl();
        this.jnlp.setSdsJnlp(this.sdsJnlp);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.jnlpServiceImpl = null;
        this.mockSdsJnlpDao = null;
        this.sdsJnlp = null;
        this.mockJnlpDao = null;
        this.jnlp = null;
    }

    public void testGetJnlpIterator() throws Exception {
        Set<Jnlp> jnlpSet = new HashSet<Jnlp>();
        jnlpSet.add(this.jnlp);
        Iterator expectedJnlpIterator = jnlpSet.iterator();

        EasyMock.expect(this.mockJnlpDao.iterate()).andReturn(
                expectedJnlpIterator);
        EasyMock.replay(this.mockJnlpDao);
        assertEquals(expectedJnlpIterator, jnlpServiceImpl.getJnlpIterator());
        EasyMock.verify(this.mockJnlpDao);
    }

    // tests that the command is delegated to the DAOs and that the DAOs are
    // called once
    @SuppressWarnings("unchecked")
    public void testCreateJnlp() throws Exception {
        this.mockSdsJnlpDao.save(this.sdsJnlp);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockSdsJnlpDao);

        this.mockJnlpDao.save(this.jnlp);
        EasyMock.expectLastCall();
        EasyMock.replay(this.mockJnlpDao);

        this.jnlpServiceImpl.createJnlp(this.jnlp);

        EasyMock.verify(this.mockSdsJnlpDao);
        EasyMock.verify(this.mockJnlpDao);
    }

    public void testCreateJnlp_BadRequestException() throws Exception {
        this.mockSdsJnlpDao.save(this.sdsJnlp);
        EasyMock.expectLastCall().andThrow(
                new BadRequestException("bad request"));
        EasyMock.replay(this.mockSdsJnlpDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockJnlpDao);

        try {
            this.jnlpServiceImpl.createJnlp(this.jnlp);
            fail("BadRequestException expected");
        } catch (BadRequestException expected) {
        }

        EasyMock.verify(this.mockSdsJnlpDao);
        EasyMock.verify(this.mockJnlpDao);
    }

    public void testCreateJnlp_NetworkTransportException() throws Exception {
        this.mockSdsJnlpDao.save(this.sdsJnlp);
        EasyMock.expectLastCall().andThrow(
                new NetworkTransportException("network transport exception"));
        EasyMock.replay(this.mockSdsJnlpDao);

        // expecting no calls to Dao.save()
        EasyMock.replay(this.mockJnlpDao);

        try {
            this.jnlpServiceImpl.createJnlp(this.jnlp);
            fail("NetworkTransportException expected");
        } catch (NetworkTransportException expected) {
        }

        EasyMock.verify(this.mockSdsJnlpDao);
        EasyMock.verify(this.mockJnlpDao);
    }
}