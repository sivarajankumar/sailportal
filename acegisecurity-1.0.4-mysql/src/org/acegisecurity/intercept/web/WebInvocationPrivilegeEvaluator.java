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

package org.acegisecurity.intercept.web;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttributeDefinition;

import org.acegisecurity.intercept.AbstractSecurityInterceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;


/**
 * Allows users to determine whether they have privileges for a given web URI.
 *
 * @author Ben Alex
 * @version $Id: WebInvocationPrivilegeEvaluator.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class WebInvocationPrivilegeEvaluator implements InitializingBean {
    //~ Static fields/initializers =====================================================================================

    protected static final Log logger = LogFactory.getLog(WebInvocationPrivilegeEvaluator.class);

    //~ Instance fields ================================================================================================

    private AbstractSecurityInterceptor securityInterceptor;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(securityInterceptor, "SecurityInterceptor required");
    }

    public boolean isAllowed(FilterInvocation fi, Authentication authentication) {
        Assert.notNull(fi, "FilterInvocation required");

        ConfigAttributeDefinition attrs = securityInterceptor.obtainObjectDefinitionSource().getAttributes(fi);

        if (attrs == null) {
            if (securityInterceptor.isRejectPublicInvocations()) {
                return false;
            }

            return true;
        }

        if ((authentication == null) || (authentication.getAuthorities() == null)
            || (authentication.getAuthorities().length == 0)) {
            return false;
        }

        try {
            securityInterceptor.getAccessDecisionManager().decide(authentication, fi, attrs);
        } catch (AccessDeniedException unauthorized) {
            if (logger.isDebugEnabled()) {
                logger.debug(fi.toString() + " denied for " + authentication.toString(), unauthorized);
            }

            return false;
        }

        return true;
    }

    public void setSecurityInterceptor(AbstractSecurityInterceptor securityInterceptor) {
        Assert.notNull(securityInterceptor, "AbstractSecurityInterceptor cannot be null");
        Assert.isTrue(FilterInvocation.class.equals(securityInterceptor.getSecureObjectClass()),
            "AbstractSecurityInterceptor does not support FilterInvocations");
        Assert.notNull(securityInterceptor.getAccessDecisionManager(),
            "AbstractSecurityInterceptor must provide a non-null AccessDecisionManager");
        this.securityInterceptor = securityInterceptor;
    }
}
