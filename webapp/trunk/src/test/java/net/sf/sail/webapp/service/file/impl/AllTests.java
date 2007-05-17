package net.sf.sail.webapp.service.file.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.sf.sail.webapp.service.file.impl");
		//$JUnit-BEGIN$
		suite.addTestSuite(AuthoringJNLPModifierTest.class);
		//$JUnit-END$
		return suite;
	}

}
