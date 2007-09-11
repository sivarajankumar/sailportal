/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.dao.sds.impl;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Cynick Young
 * 
 * @version $$Id$$
 * 
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.sf.sail.webapp.dao.sds.impl");
		//$JUnit-BEGIN$
		suite.addTestSuite(SdsOfferingCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(HttpRestSdsOfferingDaoTest.class);
		suite.addTestSuite(SdsJnlpCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsWorkgroupUpdateCommandHttpRestImplTest.class);
		suite.addTestSuite(HttpRestSdsCurnitDaoTest.class);
		suite
				.addTestSuite(SdsWorkgroupMemberCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsUserCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(HttpRestSdsJnlpDaoTest.class);
		suite.addTestSuite(SdsCurnitCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsWorkgroupCreateCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsOfferingListCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsCurnitUpdateCommandHttpRestImplTest.class);
		suite.addTestSuite(SdsCurnitListCommandHttpRestImplTest.class);
		suite.addTestSuite(HttpRestSdsUserDaoTest.class);
		suite.addTestSuite(SdsOfferingUpdateCommandHttpRestImplTest.class);
		suite.addTest(AllTests.suite());
		suite.addTestSuite(SdsUserUpdateCommandHttpRestImplTest.class);
		suite.addTestSuite(HttpRestSdsWorkgroupDaoTest.class);
		suite.addTestSuite(SdsJnlpUpdateCommandHttpRestImplTest.class);
		//$JUnit-END$
		return suite;
	}

}
