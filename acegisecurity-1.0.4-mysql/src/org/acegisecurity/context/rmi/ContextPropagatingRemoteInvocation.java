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

package org.acegisecurity.context.rmi;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;

import org.aopalliance.intercept.MethodInvocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.remoting.support.RemoteInvocation;

import java.lang.reflect.InvocationTargetException;


/**
 * The actual <code>RemoteInvocation</code> that is passed from the client to the server, which contains the
 * contents of {@link SecurityContextHolder}, being a {@link SecurityContext} object.<p>When constructed on the
 * client via {@link org.acegisecurity.context.rmi.ContextPropagatingRemoteInvocationFactory}, the contents of the
 * <code>SecurityContext</code> are stored inside the object. The object is then passed to the server that is
 * processing the remote invocation. Upon the server invoking the remote invocation, it will retrieve the passed
 * contents of the <code>SecurityContextHolder</code> and set them to the server-side
 * <code>SecurityContextHolder</code> whilst the target object is invoked. When the target invocation has been
 * completed, the server-side <code>SecurityContextHolder</code> will be reset to a new instance of
 * <code>SecurityContextImpl</code>.</p>
 *
 * @author James Monaghan
 * @author Ben Alex
 * @version $Id: ContextPropagatingRemoteInvocation.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class ContextPropagatingRemoteInvocation extends RemoteInvocation {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(ContextPropagatingRemoteInvocation.class);

    //~ Instance fields ================================================================================================

    private SecurityContext securityContext;

    //~ Constructors ===================================================================================================

/**
     * Constructs the object, storing the value of the client-side
     * <code>SecurityContextHolder</code> inside the object.
     *
     * @param methodInvocation the method to invoke
     */
    public ContextPropagatingRemoteInvocation(MethodInvocation methodInvocation) {
        super(methodInvocation);
        securityContext = SecurityContextHolder.getContext();

        if (logger.isDebugEnabled()) {
            logger.debug("RemoteInvocation now has SecurityContext: " + securityContext);
        }
    }

    //~ Methods ========================================================================================================

    /**
     * Invoked on the server-side as described in the class JavaDocs.<p>Invocations will always have their
     * {@link org.acegisecurity.Authentication#setAuthenticated(boolean)} set to <code>false</code>, which is
     * guaranteed to always be accepted by <code>Authentication</code> implementations. This ensures that even
     * remotely authenticated <code>Authentication</code>s will be untrusted by the server-side, which is an
     * appropriate security measure.</p>
     *
     * @param targetObject the target object to apply the invocation to
     *
     * @return the invocation result
     *
     * @throws NoSuchMethodException if the method name could not be resolved
     * @throws IllegalAccessException if the method could not be accessed
     * @throws InvocationTargetException if the method invocation resulted in an exception
     */
    public Object invoke(Object targetObject)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        SecurityContextHolder.setContext(securityContext);

        if ((SecurityContextHolder.getContext() != null)
            && (SecurityContextHolder.getContext().getAuthentication() != null)) {
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Set SecurityContextHolder to contain: " + securityContext);
        }

        try {
            return super.invoke(targetObject);
        } finally {
            SecurityContextHolder.clearContext();

            if (logger.isDebugEnabled()) {
                logger.debug("Set SecurityContext to new instance of SecurityContextImpl");
            }
        }
    }
}
