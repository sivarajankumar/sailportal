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
package net.sf.sail.webapp.service.impl;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.group.Group;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class AclServiceImplTest extends TestCase {

	private MutableAclService mutableAclService;
	private AclServiceImpl<Group> groupAclService;
	private TestingAuthenticationToken authority;
	private SecurityContext securityContext;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

		authority = new TestingAuthenticationToken("admin", null,
				new GrantedAuthority[] { new GrantedAuthorityImpl(
						"ROLE_ADMINISTRATOR") });
		authority.setAuthenticated(true);
		securityContext = new SecurityContextImpl();
		securityContext.setAuthentication(authority);
		SecurityContextHolder.setContext(securityContext);

		groupAclService = new AclServiceImpl<Group>();
		mutableAclService = EasyMock.createMock(MutableAclService.class);
		groupAclService.setMutableAclService(mutableAclService);
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		groupAclService = null;
		mutableAclService = null;
		authority = null;
		securityContext = null;
	}

	public void testCreateAcl() {
		Group group = EasyMock.createMock(Group.class);
		EasyMock.expect(group.getId()).andReturn(new Long(1)).anyTimes();
		EasyMock.replay(group);
		
		MutableAcl mockMutableAcl = EasyMock.createNiceMock(MutableAcl.class);
		EasyMock.expect(mutableAclService.createAcl(new ObjectIdentityImpl(group.getClass(), group.getId()))).andStubReturn(	mockMutableAcl);
		EasyMock.expect(mutableAclService.updateAcl(mockMutableAcl)).andReturn(mockMutableAcl);
		EasyMock.replay(mutableAclService);
		EasyMock.replay(mockMutableAcl);
		
		groupAclService.createAcl(group);
		
		EasyMock.verify(group);
		EasyMock.verify(mockMutableAcl);
		EasyMock.verify(mutableAclService);

	}

}
