package net.sf.sail.webapp.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.sf.sail.webapp.dao");
		// $JUnit-BEGIN$
		suite.addTest(net.sf.sail.webapp.dao.authentication.impl.AllTests
				.suite());
		suite.addTest(net.sf.sail.webapp.dao.curnit.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.group.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.jnlp.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.offering.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.sds.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.user.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.workgroup.impl.AllTests.suite());
		suite.addTest(net.sf.sail.webapp.dao.group.impl.AllTests.suite());

		// $JUnit-END$
		return suite;
	}

}
