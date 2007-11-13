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

package org.acegisecurity.providers;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;


/**
 * An {@link AuthenticationProvider} implementation for the  {@link TestingAuthenticationToken}.<p>It simply
 * accepts as valid whatever is contained within the <code>TestingAuthenticationToken</code>.</p>
 *  <p>The purpose of this implementation is to facilitate unit testing. This provider should <B>never be enabled
 * on a production system</b>.</p>
 *
 * @author Ben Alex
 * @version $Id: TestingAuthenticationProvider.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class TestingAuthenticationProvider implements AuthenticationProvider {
    //~ Methods ========================================================================================================

    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        return authentication;
    }

    public boolean supports(Class authentication) {
        if (TestingAuthenticationToken.class.isAssignableFrom(authentication)) {
            return true;
        } else {
            return false;
        }
    }
}
