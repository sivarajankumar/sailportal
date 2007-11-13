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
import org.acegisecurity.GrantedAuthority;

import java.util.List;
import java.util.Vector;


/**
 * Basic implementation of {@link SidRetrievalStrategy} that creates a {@link Sid} for the principal, as well as
 * every granted authority the principal holds.<p>The returned array will always contain the {@link PrincipalSid}
 * before any {@link GrantedAuthoritySid} elements.</p>
 *
 * @author Ben Alex
 * @version $Id: SidRetrievalStrategyImpl.java 1754 2006-11-17 02:01:21Z benalex $
 */
public class SidRetrievalStrategyImpl implements SidRetrievalStrategy {
    //~ Methods ========================================================================================================

    public Sid[] getSids(Authentication authentication) {
        List list = new Vector();
        list.add(new PrincipalSid(authentication));

        GrantedAuthority[] authorities = authentication.getAuthorities();

        for (int i = 0; i < authorities.length; i++) {
            list.add(new GrantedAuthoritySid(authorities[i]));
        }

        return (Sid[]) list.toArray(new Sid[] {});
    }
}
