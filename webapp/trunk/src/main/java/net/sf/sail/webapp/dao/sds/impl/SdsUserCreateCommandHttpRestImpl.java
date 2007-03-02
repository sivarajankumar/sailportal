/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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
import java.util.HashMap;
import java.util.Map;

import net.sf.sail.webapp.dao.sds.SdsUserCreateCommand;
import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

import org.apache.commons.httpclient.HttpStatus;

/**
 * This class is thread-safe.
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsUserCreateCommandHttpRestImpl.java 69 2007-01-14 17:30:00Z
 *          cynick $
 * 
 */
public class SdsUserCreateCommandHttpRestImpl extends AbstractHttpRestCommand
        implements SdsUserCreateCommand {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    private static final Map<String, String> REQUEST_HEADERS;
    static {
        Map<String, String> map = new HashMap<String, String>(1);
        map.put(HEADER_CONTENT_TYPE, APPLICATION_XML);
        REQUEST_HEADERS = Collections.unmodifiableMap(map);
    }

    private static final ThreadLocal<SdsUser> SDS_USER = new ThreadLocal<SdsUser>();

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsUserCreateCommand#setSdsUser(net.sf.sail.webapp.domain.sds.SdsUser)
     */
    public void setSdsUser(SdsUser sdsUser) {
        SDS_USER.set(sdsUser);
    }

    private SdsUser getSdsUser() {
        return SDS_USER.get();
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest()
     */
    @SuppressWarnings("unchecked")
    public HttpPostRequest generateRequest() {
        final SdsUser sdsUser = this.getSdsUser();
        final String bodyData = "<user><first-name>" + sdsUser.getFirstName()
                + "</first-name><last-name>" + sdsUser.getLastName()
                + "</last-name></user>";

        final String url = "/user";

        return new HttpPostRequest(REQUEST_HEADERS, Collections.EMPTY_MAP,
                bodyData, url, HttpStatus.SC_CREATED);
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute()
     */
    public SdsUser execute(final HttpPostRequest httpRequest) {
        final Map<String, String> responseHeaders = this.transport
                .post(httpRequest);
        final String locationHeader = responseHeaders.get("Location");
        final SdsUser sdsUser = this.getSdsUser();
        // clear the thread local reference to avoid resource leak since we're
        // done
        // executing
        SDS_USER.set(null);
        sdsUser.setSdsObjectId(new Integer(locationHeader
                .substring(locationHeader.lastIndexOf("/") + 1)));
        return sdsUser;
    }
}