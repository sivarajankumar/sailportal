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

package org.acegisecurity.securechannel;

import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;

import org.acegisecurity.intercept.web.FilterInvocation;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.util.Assert;

import java.io.IOException;

import java.util.Iterator;

import javax.servlet.ServletException;


/**
 * <p>Ensures channel security is inactive by review of <code>HttpServletRequest.isSecure()</code> responses.</p>
 *  <P>The class responds to one case-sensitive keyword, {@link #getInsecureKeyword}. If this keyword is detected,
 * <code>HttpServletRequest.isSecure()</code> is used to determine the channel security offered. If channel security
 * is present, the configured <code>ChannelEntryPoint</code> is called. By default the entry point is {@link
 * RetryWithHttpEntryPoint}.</p>
 *  <P>The default <code>insecureKeyword</code> is <code>REQUIRES_INSECURE_CHANNEL</code>.</p>
 *
 * @author Ben Alex
 * @version $Id: InsecureChannelProcessor.java 1496 2006-05-23 13:38:33Z benalex $
 */
public class InsecureChannelProcessor implements InitializingBean, ChannelProcessor {
    //~ Instance fields ================================================================================================

    private ChannelEntryPoint entryPoint = new RetryWithHttpEntryPoint();
    private String insecureKeyword = "REQUIRES_INSECURE_CHANNEL";

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(insecureKeyword, "insecureKeyword required");
        Assert.notNull(entryPoint, "entryPoint required");
    }

    public void decide(FilterInvocation invocation, ConfigAttributeDefinition config)
        throws IOException, ServletException {
        if ((invocation == null) || (config == null)) {
            throw new IllegalArgumentException("Nulls cannot be provided");
        }

        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attribute = (ConfigAttribute) iter.next();

            if (supports(attribute)) {
                if (invocation.getHttpRequest().isSecure()) {
                    entryPoint.commence(invocation.getRequest(), invocation.getResponse());
                }
            }
        }
    }

    public ChannelEntryPoint getEntryPoint() {
        return entryPoint;
    }

    public String getInsecureKeyword() {
        return insecureKeyword;
    }

    public void setEntryPoint(ChannelEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    public void setInsecureKeyword(String secureKeyword) {
        this.insecureKeyword = secureKeyword;
    }

    public boolean supports(ConfigAttribute attribute) {
        if ((attribute != null) && (attribute.getAttribute() != null)
            && attribute.getAttribute().equals(getInsecureKeyword())) {
            return true;
        } else {
            return false;
        }
    }
}
