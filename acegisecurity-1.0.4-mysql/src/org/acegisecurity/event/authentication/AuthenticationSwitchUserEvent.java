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

package org.acegisecurity.event.authentication;

import org.acegisecurity.Authentication;

import org.acegisecurity.userdetails.UserDetails;


/**
 * Application event which indicates that a user context switch.
 *
 * @author Mark St.Godard
 * @version $Id: AuthenticationSwitchUserEvent.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class AuthenticationSwitchUserEvent extends AbstractAuthenticationEvent {
    //~ Instance fields ================================================================================================

    private UserDetails targetUser;

    //~ Constructors ===================================================================================================

/**
     * Switch user context event constructor
     *
     * @param authentication The current <code>Authentication</code> object
     * @param targetUser The target user
     */
    public AuthenticationSwitchUserEvent(Authentication authentication, UserDetails targetUser) {
        super(authentication);
        this.targetUser = targetUser;
    }

    //~ Methods ========================================================================================================

    public UserDetails getTargetUser() {
        return targetUser;
    }
}
