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
package org.acegisecurity.acls.sid;

import org.acegisecurity.Authentication;

import org.acegisecurity.userdetails.UserDetails;

import org.springframework.util.Assert;


/**
 * Represents an <code>Authentication.getPrincipal()</code> as a <code>Sid</code>.<p>This is a basic implementation
 * that simply uses the <code>String</code>-based principal for <code>Sid</code> comparison. More complex principal
 * objects may wish to provide an alternative <code>Sid</code> implementation that uses some other identifier.</p>
 *
 * @author Ben Alex
 * @version $Id: PrincipalSid.java 1754 2006-11-17 02:01:21Z benalex $
 */
public class PrincipalSid implements Sid {
    //~ Instance fields ================================================================================================

    private String principal;

    //~ Constructors ===================================================================================================

    public PrincipalSid(String principal) {
        Assert.hasText(principal, "Principal required");
        this.principal = principal;
    }

    public PrincipalSid(Authentication authentication) {
        Assert.notNull(authentication, "Authentication required");
        Assert.notNull(authentication.getPrincipal(), "Principal required");
        this.principal = authentication.getPrincipal().toString();

        if (authentication.getPrincipal() instanceof UserDetails) {
            this.principal = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
    }

    //~ Methods ========================================================================================================

    public boolean equals(Object object) {
        if ((object == null) || !(object instanceof PrincipalSid)) {
            return false;
        }

        // Delegate to getPrincipal() to perform actual comparison (both should be identical) 
        return ((PrincipalSid) object).getPrincipal().equals(this.getPrincipal());
    }

    public String getPrincipal() {
        return principal;
    }

    public String toString() {
        return "PrincipalSid[" + this.principal + "]";
    }
}
