/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao.authentication.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Cynick Young
 *
 * @version $Id: $
 *
 */
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(
        "Test for net.sf.sail.webapp.dao.authentication.impl");
    //$JUnit-BEGIN$
    suite.addTestSuite(HibernateGrantedAuthorityDaoTest.class);
    //$JUnit-END$
    return suite;
  }

}
