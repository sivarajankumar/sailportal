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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.http.HttpGetRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.springframework.context.ApplicationContext;

/**
 * The command which lists all the offerings from the Sail Data Service (uses
 * Http REST).
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsOfferingListCommandHttpRestImpl.java 117 2007-02-01
 *          19:59:19Z cynick $
 * 
 */
public class SdsOfferingListCommandHttpRestImpl
		extends
		AbstractSdsCommandHttpRest<HttpGetRequest, SdsOffering, List<SdsOffering>> {

	private static final Log logger = LogFactory
			.getLog(SdsOfferingListCommandHttpRestImpl.class);

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	@SuppressWarnings("unchecked")
	public List<SdsOffering> execute(ApplicationContext applicationContext,
			final SdsOffering sdsObject) {
		assert (this.httpRequest != null);
		SAXBuilder builder = new SAXBuilder();
		try {
			InputStream responseStream = this.transport.get(this.httpRequest);
			Document doc = builder.build(responseStream);
			List<Element> nodeList = XPath.newInstance("/offerings/offering")
					.selectNodes(doc);

			List<SdsOffering> sdsOfferingList = new LinkedList<SdsOffering>();
			for (Element offeringNode : nodeList) {

				SdsOffering sdsOffering = (SdsOffering) applicationContext
						.getBean("sdsOffering");
				sdsOffering.setName(offeringNode.getChild("name").getValue());
				sdsOffering.setCurnitId(new Integer(offeringNode.getChild(
						"curnit-id").getValue()));
				sdsOffering.setJnlpId(new Integer(offeringNode.getChild(
						"jnlp-id").getValue()));
				sdsOffering.setSdsObjectId(new Integer(offeringNode.getChild(
						"id").getValue()));
				sdsOfferingList.add(sdsOffering);
			}
			return sdsOfferingList;
		} catch (JDOMException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			return Collections.EMPTY_LIST;
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			return Collections.EMPTY_LIST;
		}
	}

	private static final String HEADER_ACCEPT = "Accept";

	private static Map<String, String> REQUEST_HEADERS = new HashMap<String, String>(
			1);
	static {
		REQUEST_HEADERS.put(HEADER_ACCEPT, APPLICATION_XML);
		REQUEST_HEADERS = Collections.unmodifiableMap(REQUEST_HEADERS);
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest(net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	@SuppressWarnings("unchecked")
	public HttpGetRequest generateRequest(final SdsOffering sdsObject) {
		final String url = this.baseUrl + this.portalId + SLASH + "offering";

		this.httpRequest = new HttpGetRequest(REQUEST_HEADERS,
				Collections.EMPTY_MAP, url, HttpStatus.SC_OK);

		return this.httpRequest;
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute(net.sf.sail.webapp.domain.sds.SdsObject)
	 */
	public List<SdsOffering> execute(SdsOffering sdsObject) {
		throw new UnsupportedOperationException();
	}
}