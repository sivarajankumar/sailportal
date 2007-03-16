/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.dao.authentication.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.telscenter.sail.webapp.dao.authentication.impl");
		//$JUnit-BEGIN$
		suite.addTestSuite(HibernateStudentUserDetailsDaoTest.class);
		suite.addTestSuite(HibernateTeacherUserDetailsDaoTest.class);
		//$JUnit-END$
		return suite;
	}
}
