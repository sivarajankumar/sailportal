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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * @author Hiroki Terashima
 * 
 */
public class SdsWorkgroupMemberCreateCommandHttpRestImplTest extends TestCase {

    private static final Integer SDS_USER_ID1 = new Integer(1);

    private static final Integer SDS_USER_ID2 = new Integer(3);

    private static final Integer SDS_USER_ID3 = new Integer(5);

    private static final Integer WORKGROUP_ID = new Integer(42);

    private SdsWorkgroup testWorkgroup;

    private SdsWorkgroupMemberCreateCommandHttpRestImpl command;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.command = new SdsWorkgroupMemberCreateCommandHttpRestImpl();
        this.testWorkgroup = new SdsWorkgroup();
        this.testWorkgroup.setSdsObjectId(WORKGROUP_ID);
        this.command.setWorkgroup(testWorkgroup);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.testWorkgroup = null;
        this.command = null;
    }

    @SuppressWarnings("unchecked")
    public void testGenerateRequest_BodyDataWithThreeUsers() throws Exception {
        addUsersToCommandWorkgroup();
        assertEquals(3, this.testWorkgroup.getMembers().size());

        HttpPostRequest request = this.command.generateRequest();
        List<Element> userNodeList = convertBodyDataToXMLNodes(request);

        Set setOfExpectedIds = setUpExpectedUserIdSet();

        Set<SdsUser> sdsUserList = new HashSet<SdsUser>();
        for (Element memberNode : userNodeList) {
            SdsUser sdsUser = new SdsUser();
            sdsUser.setSdsObjectId(new Integer(memberNode.getValue()));
            sdsUserList.add(sdsUser);
            assertTrue(setOfExpectedIds.contains(sdsUser.getSdsObjectId()));
        }
        assertEquals(setOfExpectedIds.size(), sdsUserList.size());
    }

    public void testGenerateRequest_BodyDataWithNoUsers() throws Exception {
        assertTrue(this.testWorkgroup.getMembers().isEmpty());

        HttpPostRequest request = this.command.generateRequest();
        List<Element> userNodeList = convertBodyDataToXMLNodes(request);

        assertTrue(userNodeList.isEmpty());
    }

    public void testGenerateRequest_Url() throws Exception {
        final String EXPECTED_URL = "/workgroup/" + WORKGROUP_ID
                + "/membership";
        HttpPostRequest request = this.command.generateRequest();

        assertEquals(EXPECTED_URL, request.getUrl());
    }

    private Set<Integer> setUpExpectedUserIdSet() {
        Set<Integer> setOfExpectedIds = new HashSet<Integer>(3);
        setOfExpectedIds.add(SDS_USER_ID1);
        setOfExpectedIds.add(SDS_USER_ID2);
        setOfExpectedIds.add(SDS_USER_ID3);
        return setOfExpectedIds;
    }

    @SuppressWarnings("unchecked")
    private List<Element> convertBodyDataToXMLNodes(HttpPostRequest request)
            throws JDOMException, IOException {
        InputStream requestStream = new ByteArrayInputStream(request
                .getBodyData().getBytes());
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(requestStream);
        requestStream.close();

        return XPath.newInstance(
                "/workgroup-memberships/workgroup-membership/user-id")
                .selectNodes(doc);
    }

    private void addUsersToCommandWorkgroup() {
        SdsUser user = new SdsUser();
        user.setSdsObjectId(SDS_USER_ID1);
        this.testWorkgroup.addMember(user);

        SdsUser user2 = new SdsUser();
        user2.setSdsObjectId(SDS_USER_ID2);
        this.testWorkgroup.addMember(user2);

        SdsUser user3 = new SdsUser();
        user3.setSdsObjectId(SDS_USER_ID3);
        this.testWorkgroup.addMember(user3);
    }
}