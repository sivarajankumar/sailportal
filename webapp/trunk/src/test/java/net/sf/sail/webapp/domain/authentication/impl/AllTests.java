package net.sf.sail.webapp.domain.authentication.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(
                "Test for net.sf.sail.webapp.domain.authentication.impl");
        //$JUnit-BEGIN$
        suite.addTestSuite(PersistentSidTest.class);
        suite.addTestSuite(PersistentAclTargetObjectIdentityTest.class);
        //$JUnit-END$
        return suite;
    }

}
