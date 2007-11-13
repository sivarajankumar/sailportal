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
package org.acegisecurity.acls;

import org.acegisecurity.acls.sid.Sid;

import java.io.Serializable;


/**
 * A mutable <code>Acl</code>.
 *
 * <p>
 * A mutable ACL must ensure that appropriate security checks are performed
 * before allowing access to its methods.
 * </p>
 *
 * @author Ben Alex
 * @version $Id: MutableAcl.java 1867 2007-05-25 02:22:18Z benalex $
 */
public interface MutableAcl extends Acl {
    //~ Methods ========================================================================================================

    void deleteAce(Serializable aceId) throws NotFoundException;

    /**
     * Retrieves all of the non-deleted {@link AccessControlEntry} instances currently stored by the
     * <code>MutableAcl</code>. The returned objects should be immutable outside the package, and therefore it is safe
     * to return them to the caller for informational purposes. The <code>AccessControlEntry</code> information is
     * needed so that invocations of update and delete methods on the <code>MutableAcl</code> can refer to a valid
     * {@link AccessControlEntry#getId()}.
     *
     * @return DOCUMENT ME!
     */
    AccessControlEntry[] getEntries();

    /**
     * Obtains an identifier that represents this <code>MutableAcl</code>.
     *
     * @return the identifier, or <code>null</code> if unsaved
     */
    Serializable getId();

    void insertAce(Serializable afterAceId, Permission permission, Sid sid, boolean granting)
        throws NotFoundException;

    /**
     * Change the value returned by {@link Acl#isEntriesInheriting()}.
     *
     * @param entriesInheriting the new value
     */
    void setEntriesInheriting(boolean entriesInheriting);

    /**
     * Changes the parent of this ACL.
     *
     * @param newParent the new parent
     */
    void setParent(Acl newParent);

    void updateAce(Serializable aceId, Permission permission)
        throws NotFoundException;
}
