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

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.GrantedAuthority;

import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;

import org.springframework.util.Assert;


/**
 * Client-side object which queries a  {@link RemoteAuthenticationManager} to validate an authentication request.<p>A
 * new <code>Authentication</code> object is created by this class comprising the request <code>Authentication</code>
 * object's <code>principal</code>, <code>credentials</code> and the <code>GrantedAuthority</code>[]s returned by the
 * <code>RemoteAuthenticationManager</code>.</p>
 *  <p>The <code>RemoteAuthenticationManager</code> should not require any special username or password setting on
 * the remoting client proxy factory to execute the call. Instead the entire authentication request must be
 * encapsulated solely within the <code>Authentication</code> request object. In practical terms this means the
 * <code>RemoteAuthenticationManager</code> will <b>not</b> be protected by BASIC or any other HTTP-level
 * authentication.</p>
 *  <p>If authentication fails, a <code>RemoteAuthenticationException</code> will be thrown. This exception should
 * be caught and displayed to the user, enabling them to retry with alternative credentials etc.</p>
 *
 * @author Ben Alex
 * @version $Id: RemoteAuthenticationProvider.java 1809 2007-05-11 00:09:56Z vishalpuri $
 */
public class RemoteAuthenticationProvider implements AuthenticationProvider, InitializingBean, Ordered {
    //~ Instance fields ================================================================================================

    private RemoteAuthenticationManager remoteAuthenticationManager;
    private int order = -1; // default: same as non-Ordered

    //~ Methods ========================================================================================================

    public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.remoteAuthenticationManager, "remoteAuthenticationManager is mandatory");
    }

    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        GrantedAuthority[] authorities = remoteAuthenticationManager.attemptAuthentication(username, password);

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    public RemoteAuthenticationManager getRemoteAuthenticationManager() {
        return remoteAuthenticationManager;
    }

    public void setRemoteAuthenticationManager(RemoteAuthenticationManager remoteAuthenticationManager) {
        this.remoteAuthenticationManager = remoteAuthenticationManager;
    }

    public boolean supports(Class authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
