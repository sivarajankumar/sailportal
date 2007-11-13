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

import org.acegisecurity.ConfigAttributeDefinition;


/**
 * Abstract implementation of <Code>FilterInvocationDefinitionSource</code>.
 *
 * @author Ben Alex
 * @version $Id: AbstractFilterInvocationDefinitionSource.java 1877 2007-05-25 05:33:06Z benalex $
 */
public abstract class AbstractFilterInvocationDefinitionSource implements FilterInvocationDefinitionSource {

    //~ Methods ========================================================================================================

    public ConfigAttributeDefinition getAttributes(Object object)
        throws IllegalArgumentException {
        if ((object == null) || !this.supports(object.getClass())) {
            throw new IllegalArgumentException("Object must be a FilterInvocation");
        }

        String url = ((FilterInvocation) object).getRequestUrl();

        return this.lookupAttributes(url);
    }

    /**
     * Performs the actual lookup of the relevant <code>ConfigAttributeDefinition</code> for the specified
     * <code>FilterInvocation</code>.
     * <p>Provided so subclasses need only to provide one basic method to properly interface with the
     * <code>FilterInvocationDefinitionSource</code>.
     * </p>
     * <p>Public visiblity so that tablibs or other view helper classes can access the
     * <code>ConfigAttributeDefinition</code> applying to a given URI pattern without needing to construct a mock
     * <code>FilterInvocation</code> and retrieving the attibutes via the {@link #getAttributes(Object)} method.</p>
     *
     * @param url the URI to retrieve configuration attributes for
     *
     * @return the <code>ConfigAttributeDefinition</code> that applies to the specified <code>FilterInvocation</code>
     */
    public abstract ConfigAttributeDefinition lookupAttributes(String url);

    public boolean supports(Class clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
