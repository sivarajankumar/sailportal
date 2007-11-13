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
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthorizationServiceException;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import org.acegisecurity.acls.AclService;
import org.acegisecurity.acls.Permission;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.Iterator;


/**
 * <p>Given a <code>Collection</code> of domain object instances returned from a secure object invocation, remove
 * any <code>Collection</code> elements the principal does not have appropriate permission to access as defined by the
 * {@link AclService}.</p>
 *  <p>The <code>AclService</code> is used to retrieve the access control list (ACL) permissions associated with
 * each <code>Collection</code> domain object instance element for the current <code>Authentication</code> object.</p>
 *  <p>This after invocation provider will fire if any {@link ConfigAttribute#getAttribute()} matches the {@link
 * #processConfigAttribute}. The provider will then lookup the ACLs from the <code>AclService</code> and ensure the
 * principal is
 * {@link org.acegisecurity.acls.Acl#isGranted(org.acegisecurity.acls.Permission[],
 * org.acegisecurity.acls.sid.Sid[], boolean) Acl.isGranted(Permission[], Sid[], boolean)}
 * when presenting the {@link #requirePermission} array to that method.</p>
 *  <p>If the principal does not have permission, that element will not be included in the returned
 * <code>Collection</code>.</p>
 *  <p>Often users will setup a <code>BasicAclEntryAfterInvocationProvider</code> with a {@link
 * #processConfigAttribute} of <code>AFTER_ACL_COLLECTION_READ</code> and a {@link #requirePermission} of
 * <code>BasePermission.READ</code>. These are also the defaults.</p>
 *  <p>If the provided <code>returnObject</code> is <code>null</code>, a <code>null</code><code>Collection</code>
 * will be returned. If the provided <code>returnObject</code> is not a <code>Collection</code>, an {@link
 * AuthorizationServiceException} will be thrown.</p>
 *  <p>All comparisons and prefixes are case sensitive.</p>
 *
 * @author Ben Alex
 * @author Paulo Neves
 * @version $Id: AclEntryAfterInvocationCollectionFilteringProvider.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public class AclEntryAfterInvocationCollectionFilteringProvider extends AbstractAclProvider {
    //~ Static fields/initializers =====================================================================================

    protected static final Log logger = LogFactory.getLog(AclEntryAfterInvocationCollectionFilteringProvider.class);

    //~ Constructors ===================================================================================================

    public AclEntryAfterInvocationCollectionFilteringProvider(AclService aclService, Permission[] requirePermission) {
        super(aclService, "AFTER_ACL_COLLECTION_READ", requirePermission);
    }

    //~ Methods ========================================================================================================

    public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config,
        Object returnedObject) throws AccessDeniedException {
        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attr = (ConfigAttribute) iter.next();

            if (this.supports(attr)) {
                // Need to process the Collection for this invocation
                if (returnedObject == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Return object is null, skipping");
                    }

                    return null;
                }

                Filterer filterer = null;

                if (returnedObject instanceof Collection) {
                    Collection collection = (Collection) returnedObject;
                    filterer = new CollectionFilterer(collection);
                } else if (returnedObject.getClass().isArray()) {
                    Object[] array = (Object[]) returnedObject;
                    filterer = new ArrayFilterer(array);
                } else {
                    throw new AuthorizationServiceException("A Collection or an array (or null) was required as the "
                            + "returnedObject, but the returnedObject was: " + returnedObject);
                }

                // Locate unauthorised Collection elements
                Iterator collectionIter = filterer.iterator();

                while (collectionIter.hasNext()) {
                    Object domainObject = collectionIter.next();

                    boolean hasPermission = false;

                    if (domainObject == null) {
                        hasPermission = true;
                    } else if (!getProcessDomainObjectClass().isAssignableFrom(domainObject.getClass())) {
                        hasPermission = true;
                    } else {
                        hasPermission = hasPermission(authentication, domainObject);

                        if (!hasPermission) {
                            filterer.remove(domainObject);

                            if (logger.isDebugEnabled()) {
                                logger.debug("Principal is NOT authorised for element: " + domainObject);
                            }
                        }
                    }
                }

                return filterer.getFilteredObject();
            }
        }

        return returnedObject;
    }
}
