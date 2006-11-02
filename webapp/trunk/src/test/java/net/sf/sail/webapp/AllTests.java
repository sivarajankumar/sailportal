/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * This is the all encompassing AllTests that includes all the other test suites
 * from every package. This file needs to be kept up-to-date by hand every time
 * a new package of unit tests is added. This will test the entire application.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for net.sf.sail.webapp");
    // Add new package unit tests here
    suite.addTest(net.sf.sail.webapp.dao.authentication.impl.AllTests
        .suite());
    // End add new package unit tests here
    return suite;
  }

}
