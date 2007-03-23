package net.sf.sail.webapp.service.curnit.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.sf.sail.webapp.service.curnit.impl");
		//$JUnit-BEGIN$
		suite.addTestSuite(CurnitServiceImplTest.class);
		//$JUnit-END$
		return suite;
	}

}
