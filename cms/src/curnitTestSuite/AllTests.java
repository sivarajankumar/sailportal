package curnitTestSuite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for curnitTestSuite");
		
		suite.addTestSuite((Class<? extends TestCase>) CreateCurnitTests.class);
		return suite;
	}

}
