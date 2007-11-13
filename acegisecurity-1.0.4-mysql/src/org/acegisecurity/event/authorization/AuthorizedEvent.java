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

package org.acegisecurity.event.authorization;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttributeDefinition;


/**
 * Event indicating a secure object was invoked successfully.<P>Published just before the secure object attempts to
 * proceed.</p>
 *
 * @author Ben Alex
 * @version $Id: AuthorizedEvent.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class AuthorizedEvent extends AbstractAuthorizationEvent {
    //~ Instance fields ================================================================================================

    private Authentication authentication;
    private ConfigAttributeDefinition configAttributeDefinition;

    //~ Constructors ===================================================================================================

/**
     * Construct the event.
     *
     * @param secureObject the secure object
     * @param configAttribs that apply to the secure object
     * @param authentication that successfully called the secure object
     *
     * @throws IllegalArgumentException DOCUMENT ME!
     */
    public AuthorizedEvent(Object secureObject, ConfigAttributeDefinition configAttribs, Authentication authentication) {
        super(secureObject);

        if ((configAttribs == null) || (authentication == null)) {
            throw new IllegalArgumentException("All parameters are required and cannot be null");
        }

        this.configAttributeDefinition = configAttribs;
        this.authentication = authentication;
    }

    //~ Methods ========================================================================================================

    public Authentication getAuthentication() {
        return authentication;
    }

    public ConfigAttributeDefinition getConfigAttributeDefinition() {
        return configAttributeDefinition;
    }
}
