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
package net.sf.sail.webapp.service.authentication.impl;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.authentication.AclSidDao;
import net.sf.sail.webapp.dao.authentication.AclTargetObjectDao;
import net.sf.sail.webapp.dao.authentication.AclTargetObjectIdentityDao;
import net.sf.sail.webapp.domain.authentication.MutableAclSid;
import net.sf.sail.webapp.domain.authentication.MutableAclTargetObject;
import net.sf.sail.webapp.domain.authentication.MutableAclTargetObjectIdentity;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.domain.AclAuthorizationStrategyImpl;
import org.acegisecurity.acls.domain.AclImpl;
import org.acegisecurity.acls.domain.ConsoleAuditLogger;
import org.acegisecurity.acls.jdbc.LookupStrategy;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.context.SecurityContextImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.easymock.EasyMock;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class HibernateMutableAclServiceTest extends TestCase {

    private static final String SID_NAME = "Sid Vicious";

    private static final Long ID = new Long(12);

    private static final Class<PersistentGroup> TARGET_CLASS = PersistentGroup.class;

    private static final String TARGET_CLASSNAME = TARGET_CLASS.getName();

    private HibernateMutableAclService aclService;

    private AclTargetObjectDao<MutableAclTargetObject> aclTargetObjectDao;

    private AclSidDao<MutableAclSid> aclSidDao;

    private AclTargetObjectIdentityDao<MutableAclTargetObjectIdentity> aclTargetObjectIdentityDao;

    private MutableAclTargetObject targetObject;

    private MutableAclSid aclSid;

    private MutableAclTargetObjectIdentity objectIdentity;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.aclService = new HibernateMutableAclService();
        this.aclTargetObjectDao = EasyMock.createMock(AclTargetObjectDao.class);
        this.aclSidDao = EasyMock.createMock(AclSidDao.class);
        this.aclTargetObjectIdentityDao = EasyMock
                .createMock(AclTargetObjectIdentityDao.class);
        this.aclService.setAclSidDao(this.aclSidDao);
        this.aclService.setAclTargetObjectDao(this.aclTargetObjectDao);
        this.aclService
                .setAclTargetObjectIdentityDao(this.aclTargetObjectIdentityDao);

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new TestingAuthenticationToken(
                SID_NAME, null, null));
        SecurityContextHolder.setContext(securityContext);
        assertEquals(SID_NAME, SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());

        this.targetObject = EasyMock.createMock(MutableAclTargetObject.class);
        this.aclSid = EasyMock.createMock(MutableAclSid.class);
        this.objectIdentity = EasyMock
                .createMock(MutableAclTargetObjectIdentity.class);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        this.aclService = null;
        this.aclTargetObjectDao = null;
        this.aclSidDao = null;
        this.aclTargetObjectIdentityDao = null;
    }

    public void testCreateAcl_AlreadyExistsException() {
        EasyMock.expect(objectIdentity.getJavaType()).andReturn(
                PersistentGroup.class);
        objectIdentity.setInheriting(Boolean.TRUE);
        EasyMock.expectLastCall();
        objectIdentity.setAclTargetObject(this.targetObject);
        EasyMock.expectLastCall();
        EasyMock.replay(this.targetObject);

        EasyMock.expect(this.aclSidDao.retrieveBySidName(SID_NAME)).andReturn(
                this.aclSid);
        objectIdentity.setOwnerSid(this.aclSid);
        EasyMock.expectLastCall();
        EasyMock.replay(this.aclSid);
        EasyMock.replay(this.aclSidDao);

        EasyMock.replay(objectIdentity);

        EasyMock.expect(
                this.aclTargetObjectDao.retrieveByClassname(TARGET_CLASSNAME))
                .andReturn(this.targetObject);
        EasyMock.expectLastCall();
        EasyMock.replay(this.aclTargetObjectDao);

        this.aclTargetObjectIdentityDao.save(objectIdentity);
        EasyMock.expectLastCall().andThrow(
                new DataIntegrityViolationException("Constraint Violation"));
        EasyMock.replay(this.aclTargetObjectIdentityDao);

        try {
            this.aclService.createAcl(objectIdentity);
            fail("AlreadyExistsException expected");
        } catch (AlreadyExistsException expected) {
        }
        EasyMock.verify(objectIdentity);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.service.authentication.impl.HibernateMutableAclService#createAcl(org.acegisecurity.acls.objectidentity.ObjectIdentity)}.
     */
    public void testCreateAcl() {
//      fail("Not yet implemented"); // TODO
        //tried to write these tests but became too onerous//

//        // 3 cases
//        // case 1 - existing both, 1 new entry acl_object_identity
//        EasyMock.expect(
//                this.aclTargetObjectDao.retrieveByClassname(TARGET_CLASSNAME))
//                .andStubReturn(targetObject);
//        EasyMock.replay(targetObject);
//        EasyMock.replay(this.aclTargetObjectDao);
//
//        EasyMock.expect(this.aclSidDao.retrieveBySidName(SID_NAME))
//                .andStubReturn(aclSid);
//        objectIdentity.setOwnerSid(this.aclSid);
//        EasyMock.expectLastCall();
//        EasyMock.replay(aclSid);
//        EasyMock.replay(this.aclSidDao);
//
//        EasyMock.expect(objectIdentity.getJavaType()).andReturn(TARGET_CLASS);
//        EasyMock.expect(objectIdentity.getIdentifier()).andReturn(ID);
//        objectIdentity.setInheriting(Boolean.TRUE);
//        EasyMock.expectLastCall();
//        objectIdentity.setAclTargetObject(this.targetObject);
//        EasyMock.expectLastCall();
//        EasyMock.replay(objectIdentity);
//
//        this.aclTargetObjectIdentityDao.save(objectIdentity);
//        EasyMock.expectLastCall();
//
//        LookupStrategy lookupStrategy = EasyMock
//                .createNiceMock(LookupStrategy.class);
//        Map map = new HashMap();
//        MutableAcl acl = new AclImpl(this.objectIdentity, ID,
//                new AclAuthorizationStrategyImpl(new GrantedAuthority[] {
//                        new GrantedAuthorityImpl("ADMIN"),
//                        new GrantedAuthorityImpl("ADMIN"),
//                        new GrantedAuthorityImpl("ADMIN") }),
//                new ConsoleAuditLogger());
//        map.put(this.objectIdentity, acl);
//        EasyMock.expect(
//                lookupStrategy.readAclsById(
//                        new ObjectIdentity[] { this.objectIdentity }, null))
//                .andReturn(map);
//        this.aclService.setLookupStrategy(lookupStrategy);
//        EasyMock.replay(lookupStrategy);
//
//        MutableAcl actualAcl = this.aclService.createAcl(objectIdentity);
//        assertEquals(ID, actualAcl.getId());
//        assertEquals(SID_NAME, actualAcl.getOwner());
//        assertEquals(0, actualAcl.getEntries().length);
//        assertTrue(actualAcl instanceof MutableAcl);
//        EasyMock.verify(objectIdentity);
//        EasyMock.verify(this.aclSidDao);
//        EasyMock.verify(this.aclTargetObjectDao);
//        EasyMock.verify(targetObject);
//        EasyMock.verify(aclSid);
//
//        EasyMock.reset(objectIdentity);
//        EasyMock.reset(this.aclSidDao);
//        EasyMock.reset(this.aclTargetObjectDao);
//        // case 2 - brand new sid but existing target object type, 2 new entries
//        // acl_sid, acl_object_identity
//        EasyMock.expect(
//                this.aclTargetObjectDao.retrieveByClassname(TARGET_CLASSNAME))
//                .andStubReturn(targetObject);
//        EasyMock.replay(targetObject);
//        EasyMock.replay(this.aclTargetObjectDao);
//
//        EasyMock.expect(this.aclSidDao.retrieveBySidName(SID_NAME)).andReturn(
//                null);
//        EasyMock.replay(this.aclSidDao);
//
//        EasyMock.expect(objectIdentity.getJavaType()).andReturn(TARGET_CLASS);
//        EasyMock.expect(objectIdentity.getIdentifier()).andReturn(ID);
//        EasyMock.replay(objectIdentity);
//
//        this.aclTargetObjectIdentityDao.save(objectIdentity);
//        EasyMock.expectLastCall();
//
//        actualAcl = this.aclService.createAcl(objectIdentity);
//        assertEquals(ID, actualAcl.getId());
//        assertEquals(SID_NAME, actualAcl.getOwner());
//        assertEquals(0, actualAcl.getEntries().length);
//        assertTrue(actualAcl instanceof MutableAcl);
//        EasyMock.verify(objectIdentity);
//        EasyMock.verify(this.aclSidDao);
//        EasyMock.verify(this.aclTargetObjectDao);
//        EasyMock.verify(targetObject);
//
//        EasyMock.reset(objectIdentity);
//        EasyMock.reset(this.aclSidDao);
//        EasyMock.reset(this.aclTargetObjectDao);
//        EasyMock.reset(targetObject);
//        // case 3 - brand new target object type and brand new sid, 3 new
//        // entries acl_class, acl_sid, acl_object_identity
//        EasyMock.expect(
//                this.aclTargetObjectDao.retrieveByClassname(TARGET_CLASSNAME))
//                .andReturn(null);
//        EasyMock.replay(this.aclTargetObjectDao);
//
//        EasyMock.expect(this.aclSidDao.retrieveBySidName(SID_NAME)).andReturn(
//                null);
//        EasyMock.replay(this.aclSidDao);
//
//        EasyMock.expect(objectIdentity.getJavaType()).andReturn(TARGET_CLASS);
//        EasyMock.expect(objectIdentity.getIdentifier()).andReturn(ID);
//        objectIdentity.setInheriting(Boolean.TRUE);
//        EasyMock.expectLastCall();
//        objectIdentity.setAclTargetObject(null);
//        EasyMock.expectLastCall();
//        EasyMock.replay(objectIdentity);
//
//        this.aclTargetObjectIdentityDao.save(objectIdentity);
//        EasyMock.expectLastCall();
//
//        actualAcl = this.aclService.createAcl(objectIdentity);
//        assertEquals(ID, actualAcl.getId());
//        assertEquals(SID_NAME, actualAcl.getOwner());
//        assertEquals(0, actualAcl.getEntries().length);
//        assertTrue(actualAcl instanceof MutableAcl);
//        EasyMock.verify(objectIdentity);
//        EasyMock.verify(this.aclSidDao);
//        EasyMock.verify(this.aclTargetObjectDao);
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.service.authentication.impl.HibernateMutableAclService#deleteAcl(org.acegisecurity.acls.objectidentity.ObjectIdentity, boolean)}.
     */
    public void testDeleteAcl() {
//        fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.service.authentication.impl.HibernateMutableAclService#updateAcl(org.acegisecurity.acls.MutableAcl)}.
     */
    public void testUpdateAcl() {
//        fail("Not yet implemented"); // TODO
    }
}