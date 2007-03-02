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

import java.util.Collections;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsUserCreateCommand;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

import org.easymock.EasyMock;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class HttpRestSdsUserDaoTest extends TestCase {

    private static final String EXPECTED_FIRST_NAME = "first";

    private static final String EXPECTED_LAST_NAME = "last";

    private HttpRestSdsUserDao sdsUserDao;

    private SdsUserCreateCommand mockCommand;

    private SdsUser sdsUser;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.sdsUserDao = new HttpRestSdsUserDao();
        this.sdsUser = this.sdsUserDao.createDataObject();
        this.sdsUser.setFirstName(EXPECTED_FIRST_NAME);
        this.sdsUser.setLastName(EXPECTED_LAST_NAME);
        this.mockCommand = EasyMock.createMock(SdsUserCreateCommand.class);
        this.sdsUserDao.setCreateCommand(this.mockCommand);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.sdsUserDao = null;
        this.mockCommand = null;
        this.sdsUser = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#createDataObject()}.
     */
    public void testCreateDataObject() {
        assertTrue(this.sdsUserDao.createDataObject() instanceof SdsUser);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    @SuppressWarnings("unchecked")
    public void testSave() {
        final HttpPostRequest httpRequest = new HttpPostRequest(
                Collections.EMPTY_MAP, Collections.EMPTY_MAP, "some body data",
                "some url", 1);
        this.mockCommand.setSdsUser(this.sdsUser);
        EasyMock.expectLastCall();
        EasyMock.expect(this.mockCommand.generateRequest()).andReturn(
                httpRequest);
        EasyMock.expect(this.mockCommand.execute(httpRequest)).andReturn(
                this.sdsUser);
        EasyMock.replay(this.mockCommand);
        this.sdsUserDao.save(this.sdsUser);
        EasyMock.verify(this.mockCommand);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
     */
    public void testDelete() {
        try {
            this.sdsUserDao.delete(this.sdsUser);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }
}