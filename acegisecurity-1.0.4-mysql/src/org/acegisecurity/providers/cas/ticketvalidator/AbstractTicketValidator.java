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

package org.acegisecurity.providers.cas.ticketvalidator;

import org.acegisecurity.providers.cas.TicketValidator;

import org.acegisecurity.ui.cas.ServiceProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;


/**
 * Convenience abstract base for <code>TicketValidator</code>s.
 *
 * @author Ben Alex
 * @version $Id: AbstractTicketValidator.java 1730 2006-11-12 23:10:09Z benalex $
 */
public abstract class AbstractTicketValidator implements TicketValidator, InitializingBean {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(AbstractTicketValidator.class);

    //~ Instance fields ================================================================================================

    private ServiceProperties serviceProperties;
    private String casValidate;
    private String trustStore;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(casValidate, "A casValidate URL must be set");
        Assert.notNull(serviceProperties, "serviceProperties must be specified");

        if ((trustStore != null) && (!"".equals(trustStore))) {
            if (logger.isDebugEnabled()) {
                logger.debug("Setting system property 'javax.net.ssl.trustStore'" + " to value [" + trustStore + "]");
            }

            System.setProperty("javax.net.ssl.trustStore", trustStore);
        }
    }

    /**
     * Mandatory URL to CAS' proxy ticket valiation service.<P>This is usually something like
     * <code>https://www.mycompany.com/cas/proxyValidate</code>.</p>
     *
     * @return the CAS proxy ticket validation URL
     */
    public String getCasValidate() {
        return casValidate;
    }

    public ServiceProperties getServiceProperties() {
        return serviceProperties;
    }

    /**
     * Optional property which will be used to set the system property <code>javax.net.ssl.trustStore</code>.
     *
     * @return the <code>javax.net.ssl.trustStore</code> that will be set during bean initialization, or
     *         <code>null</code> to leave the system property unchanged
     */
    public String getTrustStore() {
        return trustStore;
    }

    public void setCasValidate(String casValidate) {
        this.casValidate = casValidate;
    }

    public void setServiceProperties(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    public void setTrustStore(String trustStore) {
        this.trustStore = trustStore;
    }
}
