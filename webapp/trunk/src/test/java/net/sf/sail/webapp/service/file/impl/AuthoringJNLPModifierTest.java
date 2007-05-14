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

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class AuthoringJNLPModifierTest extends TestCase {

	private static final String inputJnlp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
	"<jnlp codebase=\"" +
	"http://tels-develop.soe.berkeley.edu:8080/jnlp\" spec=\"1.0+\"> <information><title>Authoring" +
	"Everything Snapshot</title>	<vendor>TELS Center</vendor><homepage href=\"index.html\" /><description>" +
	"Preview Basic Pas</description></information><security><all-permissions /></security>" +
	"<resources> <j2se initial-heap-size=\"32m\" max-heap-size=\"128m\" version=\"1.5+\" />" +
	"<property name=\"maven.jnlp.version\" value=\"authoring-everything-snapshot-0.1.0-20070511.182952\" />" +
	"<jar href=\"org/concord/framework/framework.jar\" version=\"0.1.0-20070511.131402-50\" />" +
	"<jar href=\"com/webrenderer/webrenderer-win/webrenderer-win.jar\" version=\"3.0\" /> </resources>" +
	"<application-desc main-class=\"org.telscenter.proprietary.TelsProprietaryLauncher\" /> </jnlp>";

	private static final String curnitURL = "test.jar";

	private static final String outputJnlp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jnlp codebase=\"" +
	"http://tels-develop.soe.berkeley.edu:8080/jnlp\" spec=\"1.0+\"> <information><title>Authoring" +
	"Everything Snapshot</title>	<vendor>TELS Center</vendor><homepage href=\"index.html\" /><description>" +
	"Preview Basic Pas</description></information><security><all-permissions /></security>" +
	"<resources> <j2se initial-heap-size=\"32m\" max-heap-size=\"128m\" version=\"1.5+\" />" +
	"<property name=\"maven.jnlp.version\" value=\"authoring-everything-snapshot-0.1.0-20070511.182952\" />" +
	"<jar href=\"org/concord/framework/framework.jar\" version=\"0.1.0-20070511.131402-50\" />" +
	"<jar href=\"com/webrenderer/webrenderer-win/webrenderer-win.jar\" version=\"3.0\" /> </resources>" +
	"<application-desc main-class=\"org.telscenter.proprietary.TelsProprietaryLauncher\">" +
	"<argument>" + curnitURL + "</argument></application-desc> </jnlp>";
	
	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testmodifyJNLP() throws Exception {
		AuthoringJNLPModifier modifier = new AuthoringJNLPModifier();
		assertEquals(StringUtils.deleteWhitespace(outputJnlp), StringUtils.deleteWhitespace(modifier.modifyJnlp(inputJnlp, curnitURL)));
	}

}

