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
package org.acegisecurity.acls.jdbc;

import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.sid.Sid;

import java.util.Map;


/**
 * Performs optimised lookups for {@link JdbcAclService}.
 *
 * @author Ben Alex
 * @version $Id: LookupStrategy.java,v 1.3 2007/02/27 20:16:02 simonv Exp $
 */
public interface LookupStrategy {
    //~ Methods ========================================================================================================

    /**
     * Perform database-specific optimized lookup.
     *
     * @param objects the identities to lookup (required)
     * @param sids the SIDs for which identities are required (may be <code>null</code> - implementations may elect not
     *        to provide SID optimisations)
     *
     * @return the <code>Map</code> pursuant to the interface contract for {@link
     *         org.acegisecurity.acls.AclService#readAclsById(ObjectIdentity[], Sid[])}
     */
    public Map readAclsById(ObjectIdentity[] objects, Sid[] sids);
    
    /**
     * Gets the configured class of the expected return value from 
     * <code>ObjectIdentity.getIdentifier()</code>.   This Class must be configured
     * correctly, as many implementations of AclService use hash map lookups to 
     * determine if acl entires have been loaded, if the LookUpStrategy is configured
     * to expect Long, but domain objects provide an Integer, lookups will fail. 
     * @return The class of object returned by 
     * <code>ObjectIdentity.getIdentifier()</code>.
     */
    public Class getObjectIdentifierClass();
    
    /**
     * Returns the name of the database column in the ACL_OBJECT_IDENTITY table
     * which contains the identity of the domain objects.  This may vary depending
     * on the type of the id in the domain model, e.g. it could be a String or an 
     * Integer
     * @return
     */
    public String getObjectIdentifierColumnName();
    
}
