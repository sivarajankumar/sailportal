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

public class AuthoringJNLPModifier implements StringModifyService {
	
	private static final String APPLICATION_ELEMENT_NAME = "application-desc";
	
	private static final String ARGUMENT_ELEMENT_NAME = "argument";

	public String modifyJnlp(String inputJNLP, String curnitURL) throws JDOMException, IOException {
		InputStream inputStream = new ByteArrayInputStream(inputJNLP.getBytes());
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(inputStream);
        Element rootElement = doc.getRootElement();
        Element applicationElement = rootElement.getChild(APPLICATION_ELEMENT_NAME);
		Element argumentElement = new Element(ARGUMENT_ELEMENT_NAME);
		argumentElement.setText(curnitURL);

        applicationElement.addContent(argumentElement);
        XMLOutputter outputter = new XMLOutputter();
        String outputJNLP = outputter.outputString(doc);
		return outputJNLP;
		
	}
}
