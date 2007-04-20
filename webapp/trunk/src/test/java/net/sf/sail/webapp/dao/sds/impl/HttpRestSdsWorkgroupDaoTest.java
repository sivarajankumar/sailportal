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

import java.util.List;

import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;

import com.meterware.httpunit.WebResponse;

/**
 * @author Cynick Young
 * 
 * @version $Id: HttpRestSdsWorkgroupDaoTest.java 257 2007-03-30 14:59:02Z
 *          cynick $
 * 
 */
public class HttpRestSdsWorkgroupDaoTest extends AbstractSpringHttpUnitTests {

    private static final String DEFAULT_NAME = "d fault";

    private HttpRestSdsWorkgroupDao sdsWorkgroupDao;

    private SdsWorkgroup sdsWorkgroup;

    private SdsOffering sdsOffering;

    private SdsUser sdsUser;

    /**
     * @see net.sf.sail.webapp.junit.AbstractSpringHttpUnitTests#onTearDown()
     */
    @Override
    protected void onTearDown() throws Exception {
        super.onTearDown();
        this.sdsWorkgroup = null;
        this.sdsOffering = null;
        this.sdsUser = null;
        this.sdsWorkgroupDao = null;
    }

    /**
     * @param sdsWorkgroupDao
     *            the sdsWorkgroupDao to set
     */
    public void setSdsWorkgroupDao(HttpRestSdsWorkgroupDao sdsWorkgroupDao) {
        this.sdsWorkgroupDao = sdsWorkgroupDao;
    }

    /**
     * @param sdsOffering
     *            the SdsOffering to set
     */
    public void setSdsOffering(SdsOffering sdsOffering) {
        this.sdsOffering = sdsOffering;
    }

    /**
     * @param sdsUser
     *            the SdsUser to set
     */
    public void setSdsUser(SdsUser sdsUser) {
        this.sdsUser = sdsUser;
    }

    /**
     * @param sdsWorkgroup
     *            the SdsWorkgroup to set
     */
    public void setSdsWorkgroup(SdsWorkgroup sdsWorkgroup) {
        this.sdsWorkgroup = sdsWorkgroup;
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsWorkgroupDao#save(net.sf.sail.webapp.domain.sds.SdsWorkgroup)}.
     */
    @SuppressWarnings("unchecked")
    public void testSave_NewSdsWorkgroup() throws Exception {
        // create curnit in SDS
        SdsCurnit sdsCurnit = (SdsCurnit) this.applicationContext
                .getBean("sdsCurnit");
        sdsCurnit.setSdsObjectId(createCurnitInSds());
        this.sdsOffering.setSdsCurnit(sdsCurnit);

        // create jnlp in SDS
        SdsJnlp sdsJnlp = (SdsJnlp) this.applicationContext.getBean("sdsJnlp");
        sdsJnlp.setSdsObjectId(createJnlpInSds());
        this.sdsOffering.setSdsJnlp(sdsJnlp);

        // create offering in SDS
        Integer sdsOfferingId = createOfferingInSds(sdsCurnit.getSdsObjectId(),
                sdsJnlp.getSdsObjectId());
        this.sdsOffering.setSdsObjectId(sdsOfferingId);

        this.sdsWorkgroup.setName(DEFAULT_NAME);
        this.sdsWorkgroup.setSdsOffering(this.sdsOffering);
        this.sdsWorkgroup.addMember(this.sdsUser);

        // create user in SDS
        Integer sdsUserId = createUserInSds();
        this.sdsUser.setSdsObjectId(sdsUserId);

        assertNull(this.sdsWorkgroup.getSdsObjectId());
        this.sdsWorkgroupDao.save(this.sdsWorkgroup);
        assertNotNull(this.sdsWorkgroup.getSdsObjectId());

        // retrieve newly created workgroup using httpunit and compare with
        // sdsWorkgroup saved via DAO
        WebResponse webResponse = makeHttpRestGetRequest("/workgroup/"
                + this.sdsWorkgroup.getSdsObjectId());
        assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

        System.out.println("response: " + webResponse.getText());

        Document doc = createDocumentFromResponse(webResponse);

        Element rootElement = doc.getRootElement();
        assertEquals(this.sdsWorkgroup.getSdsObjectId(), new Integer(
                rootElement.getChild("id").getValue()));
        assertEquals(this.sdsWorkgroup.getName(), rootElement.getChild("name")
                .getValue());
        assertEquals(this.sdsWorkgroup.getSdsOffering().getSdsObjectId(),
                new Integer(rootElement.getChild("offering-id").getValue()));

        // compare the members in the workgroup
        webResponse = makeHttpRestGetRequest("/workgroup/"
                + this.sdsWorkgroup.getSdsObjectId() + "/membership");
        assertEquals(HttpStatus.SC_OK, webResponse.getResponseCode());

        System.out.println("response: " + webResponse.getText());

        doc = createDocumentFromResponse(webResponse);

        List<Element> nodeList;
        nodeList = XPath.newInstance(
                "/workgroup-memberships/workgroup-membership").selectNodes(doc);

        assertEquals(1, nodeList.size());
        assertEquals(this.sdsUser.getSdsObjectId(), new Integer(nodeList.get(0)
                .getChild("sail-user-id").getValue()));
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.dao.sds.impl.HttpRestSdsWorkgroupDao#delete(net.sf.sail.webapp.domain.sds.SdsWorkgroup)}.
     */
    public void testDelete() {
        try {
            this.sdsWorkgroupDao.delete(this.sdsWorkgroup);
            fail("UnsupportedOperationException expected");
        } catch (UnsupportedOperationException expected) {
        }
    }

}