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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.dao.sds.SdsOfferingListCommand;
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

/**
 * Implementation of <code>SdsOfferingListCommand</code> which lists all the
 * offerings from the Sail Data Service (uses Http REST). This class is
 * thread-safe.
 * 
 * @author Cynick Young
 * 
 * @version $Id: SdsOfferingListCommandHttpRestImpl.java 117 2007-02-01
 *          19:59:19Z cynick $
 * 
 */
public class SdsOfferingListCommandHttpRestImpl extends AbstractHttpRestCommand
		implements SdsOfferingListCommand {

	private static final Log logger = LogFactory
			.getLog(SdsOfferingListCommandHttpRestImpl.class);

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#execute()
	 */
	@SuppressWarnings("unchecked")
	public Set<SdsOffering> execute(HttpGetRequest httpRequest) {
		Document doc = convertXmlInputStreamToXmlDocument(this.transport
				.get(httpRequest));
		if (doc == null) {
			return Collections.EMPTY_SET;
		}

		List<Element> nodeList;
		try {
			nodeList = XPath.newInstance("/offerings/offering")
					.selectNodes(doc);
		} catch (JDOMException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			return Collections.EMPTY_SET;
		}

		Set<SdsOffering> sdsOfferingSet = new HashSet<SdsOffering>();
		for (Element offeringNode : nodeList) {
			SdsOffering sdsOffering = new SdsOffering();
			sdsOffering.setName(offeringNode.getChild("name").getValue());
			sdsOffering.setCurnitId(new Integer(offeringNode.getChild(
					"curnit-id").getValue()));
			sdsOffering.setJnlpId(new Integer(offeringNode.getChild("jnlp-id")
					.getValue()));
			sdsOffering.setSdsObjectId(new Integer(offeringNode.getChild("id")
					.getValue()));
			sdsOfferingSet.add(sdsOffering);
		}
		return sdsOfferingSet;
	}

	private static final String HEADER_ACCEPT = "Accept";

	private static final Map<String, String> REQUEST_HEADERS;
	static {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put(HEADER_ACCEPT, APPLICATION_XML);
		REQUEST_HEADERS = Collections.unmodifiableMap(map);
	}

	/**
	 * @see net.sf.sail.webapp.dao.sds.SdsCommand#generateRequest()
	 */
	@SuppressWarnings("unchecked")
	public HttpGetRequest generateRequest() {
		final String url = "/offering";

		return new HttpGetRequest(REQUEST_HEADERS, Collections.EMPTY_MAP, url,
				HttpStatus.SC_OK);
	}

	private Document convertXmlInputStreamToXmlDocument(InputStream inputStream) {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		try {
			if (logger.isDebugEnabled()) {
				logOfferingList(inputStream);
			}
			doc = builder.build(inputStream);
		} catch (JDOMException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				if (logger.isErrorEnabled()) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return doc;
	}

	private void logOfferingList(InputStream responseStream) throws IOException {
		byte[] responseBuffer = new byte[responseStream.available()];
		responseStream.read(responseBuffer);
		logger.debug(new String(responseBuffer));
		responseStream.reset();
	}
}