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
 * Thrown if an authentication request is rejected because the credentials are invalid. For this exception to be
 * thrown, it means the account is neither locked nor disabled.
 *
 * @author Ben Alex
 * @version $Id: BadCredentialsException.java 1601 2006-08-24 10:56:26Z luke_t $
 */
public class BadCredentialsException extends AuthenticationException {
    //~ Instance fields ================================================================================================

    private Object extraInformation;

    //~ Constructors ===================================================================================================

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified
     * message.
     *
     * @param msg the detail message
     */
    public BadCredentialsException(String msg) {
        super(msg);
    }

    public BadCredentialsException(String msg, Object extraInformation) {
        super(msg);
        this.extraInformation = extraInformation;
    }

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified
     * message and root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public BadCredentialsException(String msg, Throwable t) {
        super(msg, t);
    }

    //~ Methods ========================================================================================================

    /**
     * Any additional information about the exception. Generally a <code>UserDetails</code> object.
     *
     * @return extra information or <code>null</code>
     */
    public Object getExtraInformation() {
        return extraInformation;
    }
}
