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
package net.sf.sail.webapp.domain.sds;

import junit.framework.TestCase;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class SdsWorkgroupTest extends TestCase {

    private static final Long ID = new Long(1);

    private static final Integer SDS_ID = new Integer(1);

    private SdsWorkgroup sdsWorkgroup;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.sdsWorkgroup = new SdsWorkgroup();
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.sdsWorkgroup = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.domain.sds.SdsWorkgroup#setSdsOffering(net.sf.sail.webapp.domain.sds.SdsOffering)}.
     */
    public void testSetSdsOffering() {
        SdsOffering sdsOffering = new SdsOffering();
        try {
            this.sdsWorkgroup.setSdsOffering(sdsOffering);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        sdsOffering.setId(ID);
        try {
            this.sdsWorkgroup.setSdsOffering(sdsOffering);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsOffering.setId(null);
        }

        sdsOffering.setSdsObjectId(SDS_ID);
        try {
            this.sdsWorkgroup.setSdsOffering(sdsOffering);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsOffering.setSdsObjectId(null);
        }

        sdsOffering.setId(ID);
        sdsOffering.setSdsObjectId(SDS_ID);
        this.sdsWorkgroup.setSdsOffering(sdsOffering);
        assertSame(sdsOffering, this.sdsWorkgroup.getSdsOffering());
    }

}