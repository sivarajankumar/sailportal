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

package org.acegisecurity;

/**
 * Simply accepts as valid whatever is passed to it, if <code>grantAccess</code> is set to <code>true</code>.
 *
 * @author Ben Alex
 * @author Wesley Hall
 * @version $Id: MockAuthenticationManager.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class MockAuthenticationManager extends AbstractAuthenticationManager {
    //~ Instance fields ================================================================================================

    private boolean grantAccess = true;

    //~ Constructors ===================================================================================================

    public MockAuthenticationManager(boolean grantAccess) {
        this.grantAccess = grantAccess;
    }

    public MockAuthenticationManager() {
        super();
    }

    //~ Methods ========================================================================================================

    public Authentication doAuthentication(Authentication authentication)
        throws AuthenticationException {
        if (grantAccess) {
            return authentication;
        } else {
            throw new BadCredentialsException("MockAuthenticationManager instructed to deny access");
        }
    }
}
