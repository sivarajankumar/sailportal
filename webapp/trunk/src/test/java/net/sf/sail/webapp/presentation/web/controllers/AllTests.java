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
package net.sf.sail.webapp.presentation.web.controllers;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.sf.sail.webapp.presentation.web.controllers.groups.AddgroupControllerTest;
import net.sf.sail.webapp.presentation.web.controllers.offerings.CreateOfferingControllerTest;
import net.sf.sail.webapp.presentation.web.controllers.offerings.OfferingListControllerTest;

/**
 * @author Cynick Young
 *
 * @version $Id$
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for net.sf.sail.webapp.presentation.web.controllers");
		//$JUnit-BEGIN$
		suite.addTestSuite(LoginControllerTest.class);
		suite.addTestSuite(SignupControllerTest.class);
		suite.addTestSuite(CreateOfferingControllerTest.class);
		suite.addTestSuite(OfferingListControllerTest.class);
		suite.addTestSuite(AddgroupControllerTest.class);
		//$JUnit-END$
		return suite;
	}

}
