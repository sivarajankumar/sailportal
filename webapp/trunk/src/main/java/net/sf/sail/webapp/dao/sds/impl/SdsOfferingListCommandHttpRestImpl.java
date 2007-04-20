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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.sail.webapp.dao.sds.SdsOfferingListCommand;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;

/**
 * Implementation of <code>SdsOfferingListCommand</code> which lists all the
 * offerings from the Sail Data Service (uses Http REST). This class is
 * thread-safe.
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsOfferingListCommandHttpRestImpl.java 257 2007-03-30
 *          14:59:02Z cynick $
 * 
 */
public class SdsOfferingListCommandHttpRestImpl extends AbstractHttpRestCommand
        implements SdsOfferingListCommand {

    private static final Set<SdsOffering> EMPTY_SDSOFFERING_SET = Collections
            .emptySet();

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute()
     */
    @SuppressWarnings("unchecked")
    public Set<SdsOffering> execute(HttpGetRequest httpRequest) {
        Document doc = convertXmlInputStreamToXmlDocument(this.transport
                .get(httpRequest));
        if (doc == null) {
            return EMPTY_SDSOFFERING_SET;
        }

        List<Element> nodeList;
        try {
            nodeList = XPath.newInstance("/offerings/offering")
                    .selectNodes(doc);
        } catch (JDOMException e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage(), e);
            }
            return EMPTY_SDSOFFERING_SET;
        }

        Set<SdsOffering> sdsOfferingSet = new HashSet<SdsOffering>();
        for (Element offeringNode : nodeList) {
            SdsOffering sdsOffering = new SdsOffering();
            sdsOffering.setName(offeringNode.getChild("name").getValue());
            sdsOffering.setSdsObjectId(new Integer(offeringNode.getChild("id")
                    .getValue()));

            SdsCurnit sdsCurnit = new SdsCurnit();
            sdsCurnit.setSdsObjectId(new Integer(offeringNode.getChild(
                    "curnit-id").getValue()));
            sdsOffering.setSdsCurnit(sdsCurnit);

            SdsJnlp sdsJnlp = new SdsJnlp();
            sdsJnlp.setSdsObjectId(new Integer(offeringNode.getChild("jnlp-id")
                    .getValue()));
            sdsOffering.setSdsJnlp(sdsJnlp);

            sdsOfferingSet.add(sdsOffering);
        }
        return sdsOfferingSet;
    }

    /**
     * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest()
     */
    public HttpGetRequest generateRequest() {
        final String url = "/offering";

        return new HttpGetRequest(REQUEST_HEADERS_ACCEPT, EMPTY_STRING_MAP,
                url, HttpStatus.SC_OK);
    }

}