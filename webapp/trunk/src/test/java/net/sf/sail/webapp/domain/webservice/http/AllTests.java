package net.sf.sail.webapp.domain.webservice.http;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.sail.webapp.domain.webservice.http");
    // $JUnit-BEGIN$
    suite.addTestSuite(HttpPostRequestTest.class);
    // $JUnit-END$
    return suite;
  }

}
