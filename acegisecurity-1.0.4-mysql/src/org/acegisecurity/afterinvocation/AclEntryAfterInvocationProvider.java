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

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.AcegiMessageSource;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.Permission;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Iterator;


/**
 * <p>Given a domain object instance returned from a secure object invocation, ensures the principal has
 * appropriate permission as defined by the {@link AclService}.</p>
 * <p>The <code>AclService</code> is used to retrieve the access control list (ACL) permissions associated with a
 * domain object instance for the current <code>Authentication</code> object.</p>
 * <p>This after invocation provider will fire if any  {@link ConfigAttribute#getAttribute()} matches the {@link
 * #processConfigAttribute}. The provider will then lookup the ACLs from the <code>AclService</code> and ensure the
 * principal is {@link org.acegisecurity.acls.Acl#isGranted(org.acegisecurity.acls.Permission[],
   org.acegisecurity.acls.sid.Sid[], boolean) Acl.isGranted(Permission[], Sid[], boolean)}
 * when presenting the {@link #requirePermission} array to that method.</p>
 * <p>Often users will setup an <code>AclEntryAfterInvocationProvider</code> with a {@link
 * #processConfigAttribute} of <code>AFTER_ACL_READ</code> and a {@link #requirePermission} of
 * <code>BasePermission.READ</code>. These are also the defaults.</p>
 * <p>If the principal does not have sufficient permissions, an <code>AccessDeniedException</code> will be thrown.</p>
 * <p>If the provided <code>returnObject</code> is <code>null</code>, permission will always be granted and
 * <code>null</code> will be returned.</p>
 * <p>All comparisons and prefixes are case sensitive.</p>
 */
public class AclEntryAfterInvocationProvider extends AbstractAclProvider implements MessageSourceAware {
    //~ Static fields/initializers =====================================================================================

    protected static final Log logger = LogFactory.getLog(AclEntryAfterInvocationProvider.class);

    //~ Instance fields ================================================================================================

    protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();

    //~ Constructors ===================================================================================================

    public AclEntryAfterInvocationProvider(AclService aclService, Permission[] requirePermission) {
        super(aclService, "AFTER_ACL_READ", requirePermission);
    }

    //~ Methods ========================================================================================================

    public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config,
        Object returnedObject) throws AccessDeniedException {
        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attr = (ConfigAttribute) iter.next();

            if (this.supports(attr)) {
                // Need to make an access decision on this invocation
                if (returnedObject == null) {
                    // AclManager interface contract prohibits nulls
                    // As they have permission to null/nothing, grant access
                    if (logger.isDebugEnabled()) {
                        logger.debug("Return object is null, skipping");
                    }

                    return null;
                }

                if (!getProcessDomainObjectClass().isAssignableFrom(returnedObject.getClass())) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Return object is not applicable for this provider, skipping");
                    }

                    return returnedObject;
                }

                if (hasPermission(authentication, returnedObject)) {
                    return returnedObject;
                } else {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Denying access");
                    }

                    throw new AccessDeniedException(messages.getMessage(
                            "BasicAclEntryAfterInvocationProvider.noPermission",
                            new Object[] {authentication.getName(), returnedObject},
                            "Authentication {0} has NO permissions to the domain object {1}"));
                }
            }
        }

        return returnedObject;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
