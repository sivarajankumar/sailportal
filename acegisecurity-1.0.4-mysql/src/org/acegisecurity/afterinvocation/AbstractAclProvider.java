/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acegisecurity.afterinvocation;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;

import org.acegisecurity.acls.Acl;
import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.domain.BasePermission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityRetrievalStrategy;
import org.acegisecurity.acls.objectidentity.ObjectIdentityRetrievalStrategyImpl;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.acls.sid.SidRetrievalStrategy;
import org.acegisecurity.acls.sid.SidRetrievalStrategyImpl;

import org.springframework.util.Assert;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public abstract class AbstractAclProvider implements AfterInvocationProvider {
    //~ Instance fields ================================================================================================

    private AclService aclService;
    private Class processDomainObjectClass = Object.class;
    private ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy = new ObjectIdentityRetrievalStrategyImpl();
    private SidRetrievalStrategy sidRetrievalStrategy = new SidRetrievalStrategyImpl();
    private String processConfigAttribute;
    private Permission[] requirePermission = {BasePermission.READ};

    //~ Constructors ===================================================================================================

    public AbstractAclProvider(AclService aclService, String processConfigAttribute, Permission[] requirePermission) {
        Assert.hasText(processConfigAttribute, "A processConfigAttribute is mandatory");
        Assert.notNull(aclService, "An AclService is mandatory");

        if ((requirePermission == null) || (requirePermission.length == 0)) {
            throw new IllegalArgumentException("One or more requirePermission entries is mandatory");
        }

        this.aclService = aclService;
        this.processConfigAttribute = processConfigAttribute;
        this.requirePermission = requirePermission;
    }

    //~ Methods ========================================================================================================

    protected Class getProcessDomainObjectClass() {
        return processDomainObjectClass;
    }

    protected boolean hasPermission(Authentication authentication, Object domainObject) {
        // Obtain the OID applicable to the domain object
        ObjectIdentity objectIdentity = objectIdentityRetrievalStrategy.getObjectIdentity(domainObject);

        // Obtain the SIDs applicable to the principal
        Sid[] sids = sidRetrievalStrategy.getSids(authentication);

        Acl acl = null;

        try {
            // Lookup only ACLs for SIDs we're interested in
            acl = aclService.readAclById(objectIdentity, sids);

            return acl.isGranted(requirePermission, sids, false);
        } catch (NotFoundException ignore) {
            return false;
        }
    }

    public void setObjectIdentityRetrievalStrategy(ObjectIdentityRetrievalStrategy objectIdentityRetrievalStrategy) {
        Assert.notNull(objectIdentityRetrievalStrategy, "ObjectIdentityRetrievalStrategy required");
        this.objectIdentityRetrievalStrategy = objectIdentityRetrievalStrategy;
    }

    protected void setProcessConfigAttribute(String processConfigAttribute) {
        Assert.hasText(processConfigAttribute, "A processConfigAttribute is mandatory");
        this.processConfigAttribute = processConfigAttribute;
    }

    public void setProcessDomainObjectClass(Class processDomainObjectClass) {
        Assert.notNull(processDomainObjectClass, "processDomainObjectClass cannot be set to null");
        this.processDomainObjectClass = processDomainObjectClass;
    }

    public void setSidRetrievalStrategy(SidRetrievalStrategy sidRetrievalStrategy) {
        Assert.notNull(sidRetrievalStrategy, "SidRetrievalStrategy required");
        this.sidRetrievalStrategy = sidRetrievalStrategy;
    }

    public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null) && attribute.getAttribute().equals(this.processConfigAttribute)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This implementation supports any type of class, because it does not query the presented secure object.
     *
     * @param clazz the secure object
     *
     * @return always <code>true</code>
     */
    public boolean supports(Class clazz) {
        return true;
    }
}
