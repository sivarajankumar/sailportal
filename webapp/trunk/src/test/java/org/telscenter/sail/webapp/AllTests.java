package org.telscenter.sail.webapp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.telscenter.sail.webapp");
		//$JUnit-BEGIN$
		   suite.addTest(org.telscenter.sail.webapp.dao.authentication.impl.AllTests.suite());
		   suite.addTest(org.telscenter.sail.webapp.presentation.web.controllers.AllTests.suite());
		   suite.addTest(org.telscenter.sail.webapp.service.impl.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
