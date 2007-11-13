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

package org.acegisecurity.providers.rcp;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.GrantedAuthority;

import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;


/**
 * Server-side processor of a remote authentication request.<P>This bean requires no security interceptor to
 * protect it. Instead, the bean uses the configured <code>AuthenticationManager</code> to resolve an authentication
 * request.</p>
 *
 * @author Ben Alex
 * @version $Id: RemoteAuthenticationManagerImpl.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class RemoteAuthenticationManagerImpl implements RemoteAuthenticationManager, InitializingBean {
    //~ Instance fields ================================================================================================

    private AuthenticationManager authenticationManager;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.authenticationManager, "authenticationManager is required");
    }

    public GrantedAuthority[] attemptAuthentication(String username, String password)
        throws RemoteAuthenticationException {
        UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(username, password);

        try {
            return authenticationManager.authenticate(request).getAuthorities();
        } catch (AuthenticationException authEx) {
            throw new RemoteAuthenticationException(authEx.getMessage());
        }
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
