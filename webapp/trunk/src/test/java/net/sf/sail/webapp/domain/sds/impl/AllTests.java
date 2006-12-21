package net.sf.sail.webapp.domain.sds.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.sail.webapp.domain.sds.impl");
    //$JUnit-BEGIN$
    suite.addTestSuite(SdsUserCreateCommandHttpRestImplTest.class);
    //$JUnit-END$
    return suite;
  }

}
