package net.sf.sail.webapp.dao.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.sail.webapp.dao.impl");
    //$JUnit-BEGIN$
    suite.addTestSuite(HibernateUserDaoTest.class);
    //$JUnit-END$
    return suite;
  }

}
