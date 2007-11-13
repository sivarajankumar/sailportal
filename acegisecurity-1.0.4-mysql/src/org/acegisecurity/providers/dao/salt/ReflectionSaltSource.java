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

package org.acegisecurity.providers.dao.salt;

import org.acegisecurity.AuthenticationServiceException;

import org.acegisecurity.providers.dao.SaltSource;

import org.acegisecurity.userdetails.UserDetails;

import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Method;


/**
 * Obtains a salt from a specified property of the {@link org.acegisecurity.userdetails.User} object.<P>This allows
 * you to subclass <code>User</code> and provide an additional bean getter for a salt. You should use a synthetic
 * value that does not change, such as a database primary key.  Do not use <code>username</code> if it is likely to
 * change.</p>
 *
 * @author Ben Alex
 * @version $Id: ReflectionSaltSource.java 1519 2006-05-29 15:06:32Z benalex $
 */
public class ReflectionSaltSource implements SaltSource, InitializingBean {
    //~ Instance fields ================================================================================================

    private String userPropertyToUse;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        if ((this.getUserPropertyToUse() == null) || "".equals(this.getUserPropertyToUse())) {
            throw new IllegalArgumentException("A userPropertyToUse must be set");
        }
    }

    /**
     * Performs reflection on the passed <code>User</code> to obtain the salt.<P>The property identified by
     * <code>userPropertyToUse</code> must be available from the passed <code>User</code> object. If it is not
     * available, an {@link AuthenticationServiceException} will be thrown.</p>
     *
     * @param user which contains the method identified by <code>userPropertyToUse</code>
     *
     * @return the result of invoking <code>user.userPropertyToUse()</code>
     *
     * @throws AuthenticationServiceException if reflection fails
     */
    public Object getSalt(UserDetails user) {
        try {
            Method reflectionMethod = user.getClass().getMethod(this.userPropertyToUse, new Class[] {});

            return reflectionMethod.invoke(user, new Object[] {});
        } catch (Exception exception) {
            throw new AuthenticationServiceException(exception.getMessage(), exception);
        }
    }

    public String getUserPropertyToUse() {
        return userPropertyToUse;
    }

    /**
     * The method name to call to obtain the salt. If your <code>UserDetails</code> contains a
     * <code>UserDetails.getSalt()</code> method, you should set this property to <code>getSalt</code>.
     *
     * @param userPropertyToUse the name of the <b>getter</b> to call to obtain the salt from the
     *        <code>UserDetails</code>
     */
    public void setUserPropertyToUse(String userPropertyToUse) {
        this.userPropertyToUse = userPropertyToUse;
    }
}
