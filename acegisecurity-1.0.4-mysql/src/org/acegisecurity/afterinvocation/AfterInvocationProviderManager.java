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

package org.acegisecurity.afterinvocation;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.AfterInvocationManager;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import java.util.Iterator;
import java.util.List;


/**
 * Provider-based implementation of {@link AfterInvocationManager}.<p>Handles configuration of a bean context
 * defined list of  {@link AfterInvocationProvider}s.</p>
 *  <p>Every <code>AfterInvocationProvider</code> will be polled when the {@link #decide(Authentication, Object,
 * ConfigAttributeDefinition, Object)} method is called. The <code>Object</code> returned from each provider will be
 * presented to the successive provider for processing. This means each provider <b>must</b> ensure they return the
 * <code>Object</code>, even if they are not interested in the "after invocation" decision (perhaps as the secure
 * object invocation did not include a configuration attribute a given provider is configured to respond to).</p>
 *
 * @author Ben Alex
 * @version $Id: AfterInvocationProviderManager.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class AfterInvocationProviderManager implements AfterInvocationManager, InitializingBean {
    //~ Static fields/initializers =====================================================================================

    protected static final Log logger = LogFactory.getLog(AfterInvocationProviderManager.class);

    //~ Instance fields ================================================================================================

    private List providers;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        checkIfValidList(this.providers);
    }

    private void checkIfValidList(List listToCheck) {
        if ((listToCheck == null) || (listToCheck.size() == 0)) {
            throw new IllegalArgumentException("A list of AfterInvocationProviders is required");
        }
    }

    public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config,
        Object returnedObject) throws AccessDeniedException {
        Iterator iter = this.providers.iterator();

        Object result = returnedObject;

        while (iter.hasNext()) {
            AfterInvocationProvider provider = (AfterInvocationProvider) iter.next();
            result = provider.decide(authentication, object, config, result);
        }

        return result;
    }

    public List getProviders() {
        return this.providers;
    }

    public void setProviders(List newList) {
        checkIfValidList(newList);

        Iterator iter = newList.iterator();

        while (iter.hasNext()) {
            Object currentObject = null;

            try {
                currentObject = iter.next();

                AfterInvocationProvider attemptToCast = (AfterInvocationProvider) currentObject;
            } catch (ClassCastException cce) {
                throw new IllegalArgumentException("AfterInvocationProvider " + currentObject.getClass().getName()
                    + " must implement AfterInvocationProvider");
            }
        }

        this.providers = newList;
    }

    public boolean supports(ConfigAttribute attribute) {
        Iterator iter = this.providers.iterator();

        while (iter.hasNext()) {
            AfterInvocationProvider provider = (AfterInvocationProvider) iter.next();

            if (logger.isDebugEnabled()) {
                logger.debug("Evaluating " + attribute + " against " + provider);
            }

            if (provider.supports(attribute)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Iterates through all <code>AfterInvocationProvider</code>s and ensures each can support the presented
     * class.<p>If one or more providers cannot support the presented class, <code>false</code> is returned.</p>
     *
     * @param clazz the secure object class being queries
     *
     * @return if the <code>AfterInvocationProviderManager</code> can support the secure object class, which requires
     *         every one of its <code>AfterInvocationProvider</code>s to support the secure object class
     */
    public boolean supports(Class clazz) {
        Iterator iter = this.providers.iterator();

        while (iter.hasNext()) {
            AfterInvocationProvider provider = (AfterInvocationProvider) iter.next();

            if (!provider.supports(clazz)) {
                return false;
            }
        }

        return true;
    }
}
