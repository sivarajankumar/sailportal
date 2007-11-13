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

package org.acegisecurity.ui;

import org.acegisecurity.AccessDeniedException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * Used by {@link ExceptionTranslationFilter} to handle an
 * <code>AccessDeniedException</code>.
 *
 * @author Ben Alex
 * @version $Id: AccessDeniedHandler.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public interface AccessDeniedHandler {
    //~ Methods ========================================================================================================

    /**
     * Handles an access denied failure.
     *
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     *
     * @throws IOException in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    void handle(ServletRequest request, ServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException;
}
