import junit.framework.Test;
import junit.framework.TestSuite;


public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		//$JUnit-BEGIN$
	    suite.addTest(net.sf.sail.webapp.AllTests.suite());
	    suite.addTest(org.telscenter.sail.webapp.AllTests.suite());
		//$JUnit-END$
		return suite;
	}

}
