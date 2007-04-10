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
public class SdsOfferingTest extends TestCase {

    private static final Long ID = new Long(1);

    private static final Integer SDS_ID = new Integer(1);

    private SdsOffering sdsOffering;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        this.sdsOffering = new SdsOffering();
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.sdsOffering = null;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.domain.sds.SdsOffering#setCurnit(net.sf.sail.webapp.domain.sds.SdsCurnit)}.
     */
    public void testSetCurnit() {
        SdsCurnit sdsCurnit = new SdsCurnit();
        try {
            this.sdsOffering.setCurnit(sdsCurnit);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        sdsCurnit.setId(ID);
        try {
            this.sdsOffering.setCurnit(sdsCurnit);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsCurnit.setId(null);
        }

        sdsCurnit.setSdsObjectId(SDS_ID);
        try {
            this.sdsOffering.setCurnit(sdsCurnit);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsCurnit.setSdsObjectId(null);
        }

        sdsCurnit.setId(ID);
        sdsCurnit.setSdsObjectId(SDS_ID);
        this.sdsOffering.setCurnit(sdsCurnit);
        assertSame(sdsCurnit, this.sdsOffering.getCurnit());
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.domain.sds.SdsOffering#setJnlp(net.sf.sail.webapp.domain.sds.SdsJnlp)}.
     */
    public void testSetJnlp() {
        SdsJnlp sdsJnlp = new SdsJnlp();
        try {
            this.sdsOffering.setJnlp(sdsJnlp);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        sdsJnlp.setId(ID);
        try {
            this.sdsOffering.setJnlp(sdsJnlp);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsJnlp.setId(null);
        }

        sdsJnlp.setSdsObjectId(SDS_ID);
        try {
            this.sdsOffering.setJnlp(sdsJnlp);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
            sdsJnlp.setSdsObjectId(null);
        }

        sdsJnlp.setId(ID);
        sdsJnlp.setSdsObjectId(SDS_ID);
        this.sdsOffering.setJnlp(sdsJnlp);
        assertSame(sdsJnlp, this.sdsOffering.getJnlp());
    }

}