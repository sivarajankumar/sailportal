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
package net.sf.sail.webapp.service.file.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.sail.webapp.service.file.StringModifyService;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class AuthoringJNLPModifier implements StringModifyService {

	private static final String APPLICATION_ELEMENT_NAME = "application-desc";

	private static final String ARGUMENT_ELEMENT_NAME = "argument";

	/**
	 * Takes a string representation of the authoring launcher JNLP and adds in
	 * an argument to the application-desc element which represents the curntil
	 * url.
	 * 
	 * @param inputJNLP The contents of a authoring launcher jnlp file as a string.
	 * @param curnitURL The url for a curnit to be editted as a string
	 * @return A string representing the altered jnlp (with application argument added)
	 * @throws JDOMException
	 * @throws IOException
	 */
	public String modifyJnlp(String inputJNLP, String curnitURL)
			throws JDOMException, IOException {
		InputStream inputStream = new ByteArrayInputStream(inputJNLP.getBytes());
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(inputStream);
		Element rootElement = doc.getRootElement();
		Element applicationElement = rootElement
				.getChild(APPLICATION_ELEMENT_NAME);
		Element argumentElement = new Element(ARGUMENT_ELEMENT_NAME);
		argumentElement.setText(curnitURL);

		applicationElement.addContent(argumentElement);
		XMLOutputter outputter = new XMLOutputter();
		String outputJNLP = outputter.outputString(doc);
		return outputJNLP;

	}
}
