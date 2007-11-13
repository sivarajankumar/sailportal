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

package org.acegisecurity.providers.jaas.event;

import org.acegisecurity.Authentication;


/**
 * Fired by the {@link org.acegisecurity.providers.jaas.JaasAuthenticationProvider JaasAuthenticationProvider}
 * after successfully logging the user into the LoginContext, handling all callbacks, and calling all
 * AuthorityGranters.
 *
 * @author Ray Krueger
 * @version $Id: JaasAuthenticationSuccessEvent.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class JaasAuthenticationSuccessEvent extends JaasAuthenticationEvent {
    //~ Constructors ===================================================================================================

    public JaasAuthenticationSuccessEvent(Authentication auth) {
        super(auth);
    }
}
