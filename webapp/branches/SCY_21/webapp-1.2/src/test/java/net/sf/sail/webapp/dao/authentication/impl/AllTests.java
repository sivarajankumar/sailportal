/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.dao.authentication.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	net.sf.sail.webapp.dao.authentication.impl.HibernateAclEntryDaoTest.class,
	net.sf.sail.webapp.dao.authentication.impl.HibernateAclSidDaoTest.class,
	net.sf.sail.webapp.dao.authentication.impl.HibernateAclTargetObjectDaoTest.class,
	net.sf.sail.webapp.dao.authentication.impl.HibernateAclTargetObjectIdentityDaoTest.class,
	net.sf.sail.webapp.dao.authentication.impl.HibernateGrantedAuthorityDaoTest.class,
	net.sf.sail.webapp.dao.authentication.impl.HibernateUserDetailsDaoTest.class
})

public class AllTests {
}