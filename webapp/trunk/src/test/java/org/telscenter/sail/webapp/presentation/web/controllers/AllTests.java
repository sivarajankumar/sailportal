/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.telscenter.sail.webapp.presentation.web.controllers");
		//$JUnit-BEGIN
		suite.addTestSuite(SignupControllerTest.class);
		//$JUnit-END
		return suite;
	}
}
