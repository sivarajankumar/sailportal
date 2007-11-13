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

package org.acegisecurity.providers.x509.cache;

import org.acegisecurity.providers.x509.X509UserCache;

import org.acegisecurity.userdetails.UserDetails;

import java.security.cert.X509Certificate;


/**
 * "Cache" that doesn't do any caching.
 *
 * @author Luke Taylor
 * @version $Id: NullX509UserCache.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class NullX509UserCache implements X509UserCache {
    //~ Methods ========================================================================================================

    public UserDetails getUserFromCache(X509Certificate certificate) {
        return null;
    }

    public void putUserInCache(X509Certificate certificate, UserDetails user) {}

    public void removeUserFromCache(X509Certificate certificate) {}
}
