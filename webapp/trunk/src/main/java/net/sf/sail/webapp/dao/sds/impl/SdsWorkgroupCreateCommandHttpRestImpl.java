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
import java.util.HashMap;
import java.util.Map;

import net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpPostRequest;

import org.apache.commons.httpclient.HttpStatus;

/**
 * Implementation of <code>SdsWorkgroupCreateCommand</code> which creates a
 * workgroup in the Sail Data Service (uses Http REST). This class is
 * thread-safe.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class SdsWorkgroupCreateCommandHttpRestImpl extends
        AbstractHttpRestCommand implements SdsWorkgroupCreateCommand {

    private static final ThreadLocal<SdsWorkgroup> SDS_WORKGROUP = new ThreadLocal<SdsWorkgroup>();

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsWorkgroupCreateCommand#setWorkgroup(net.sf.sail.webapp.domain.sds.SdsWorkgroup)
     */
    public void setWorkgroup(SdsWorkgroup workgroup) {
        SDS_WORKGROUP.set(workgroup);
    }

    private SdsWorkgroup getWorkgroup() {
        return SDS_WORKGROUP.get();
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(net.sf.sail.webapp.domain.webservice.http.AbstractHttpRequest)
     */
    public SdsWorkgroup execute(final HttpPostRequest httpRequest) {
        final Map<String, String> responseHeaders = this.transport
                .post(httpRequest);
        final String locationHeader = responseHeaders.get("Location");
        SdsWorkgroup workgroup = this.getWorkgroup();
        workgroup.setSdsObjectId(new Integer(locationHeader
                .substring(locationHeader.lastIndexOf("/") + 1)));

        // release the thread local reference to the actual object to prevent
        // resource leak problem
        this.setWorkgroup(null);
        return workgroup;
    }

    private static final Map<String, String> REQUEST_HEADERS;
    static {
        Map<String, String> map = new HashMap<String, String>(1);
        map.put("Content-Type", APPLICATION_XML);
        REQUEST_HEADERS = Collections.unmodifiableMap(map);
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest()
     */
    @SuppressWarnings("unchecked")
    public HttpPostRequest generateRequest() {
        final SdsWorkgroup workgroup = this.getWorkgroup();
        final String bodyData = "<SDS_WORKGROUP><name>" + workgroup.getName()
                + "</name><offering-id>"
                + workgroup.getSdsOffering().getSdsObjectId()
                + "</offering-id></SDS_WORKGROUP>";
        final String url = "/workgroup";
        return new HttpPostRequest(REQUEST_HEADERS, Collections.EMPTY_MAP,
                bodyData, url, HttpStatus.SC_CREATED);
    }
}