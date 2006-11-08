/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.service.authentication.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Cynick Young
 *
 * @version $Id$
 *
 */
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.sail.webapp.service.authentication.impl");
    //$JUnit-BEGIN$
    suite.addTestSuite(UserDetailsServiceImplTest.class);
    //$JUnit-END$
    return suite;
  }

}
