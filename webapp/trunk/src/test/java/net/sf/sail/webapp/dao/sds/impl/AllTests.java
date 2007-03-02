package net.sf.sail.webapp.dao.sds.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(
                "Test for net.sf.sail.webapp.dao.sds.impl");
        //$JUnit-BEGIN$
        suite.addTestSuite(SdsOfferingListCommandHttpRestImplTest.class);
        suite.addTestSuite(HttpRestSdsOfferingDaoTest.class);
        suite.addTestSuite(SdsUserCreateCommandHttpRestImplTest.class);
        suite.addTestSuite(SdsWorkgroupModifyCommandHttpRestImplTest.class);
        suite.addTestSuite(HttpRestSdsUserDaoTest.class);
        suite.addTestSuite(SdsWorkgroupCreateCommandHttpRestImplTest.class);
        //$JUnit-END$
        return suite;
    }

}
