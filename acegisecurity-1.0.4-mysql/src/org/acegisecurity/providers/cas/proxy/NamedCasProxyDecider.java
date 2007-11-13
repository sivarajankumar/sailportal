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

package org.acegisecurity.providers.cas.proxy;

import org.acegisecurity.AcegiMessageSource;

import org.acegisecurity.providers.cas.CasProxyDecider;
import org.acegisecurity.providers.cas.ProxyUntrustedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.util.Assert;

import java.util.List;


/**
 * Accepts proxied requests if the closest proxy is named in the <code>validProxies</code> list.<P>Also accepts the
 * request if there was no proxy (ie the user directly authenticated against this service).</p>
 */
public class NamedCasProxyDecider implements CasProxyDecider, InitializingBean, MessageSourceAware {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(NamedCasProxyDecider.class);

    //~ Instance fields ================================================================================================

    private List validProxies;
    protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.validProxies, "A validProxies list must be set");
        Assert.notNull(this.messages, "A message source must be set");
    }

    public void confirmProxyListTrusted(List proxyList)
        throws ProxyUntrustedException {
        Assert.notNull(proxyList, "proxyList cannot be null");

        if (logger.isDebugEnabled()) {
            logger.debug("Proxy list: " + proxyList.toString());
        }

        if (proxyList.size() == 0) {
            // A Service Ticket (not a Proxy Ticket)
            return;
        }

        if (!validProxies.contains(proxyList.get(0))) {
            throw new ProxyUntrustedException(messages.getMessage("NamedCasProxyDecider.untrusted",
                    new Object[] {proxyList.get(0)}, "Nearest proxy {0} is untrusted"));
        }
    }

    public List getValidProxies() {
        return validProxies;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public void setValidProxies(List validProxies) {
        this.validProxies = validProxies;
    }
}
