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

package org.acegisecurity.ui.cas;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.AuthenticationEntryPoint;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.util.Assert;


/**
 * Used by the <code>SecurityEnforcementFilter</code> to commence authentication via the JA-SIG Central
 * Authentication Service (CAS).<P>The user's browser will be redirected to the JA-SIG CAS enterprise-wide login
 * page. This page is specified by the <code>loginUrl</code> property. Once login is complete, the CAS login page will
 * redirect to the page indicated by the <code>service</code> property. The <code>service</code> is a HTTP URL
 * belonging to the current application. The <code>service</code> URL is monitored by the {@link CasProcessingFilter},
 * which will validate the CAS login was successful.</p>
 *
 * @author Ben Alex
 * @version $Id: CasProcessingFilterEntryPoint.java 1822 2007-05-17 12:20:16Z vishalpuri $
 */
public class CasProcessingFilterEntryPoint implements AuthenticationEntryPoint, InitializingBean, Ordered{
    //~ Instance fields ================================================================================================

    private ServiceProperties serviceProperties;
    private String loginUrl;
    private int order = Integer.MAX_VALUE; // ~ default

    //~ Methods ========================================================================================================

    public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void afterPropertiesSet() throws Exception {
        Assert.hasLength(this.loginUrl, "loginUrl must be specified");
        Assert.notNull(this.serviceProperties, "serviceProperties must be specified");
    }

    public void commence(final ServletRequest servletRequest, final ServletResponse servletResponse,
        final AuthenticationException authenticationException)
        throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String urlEncodedService = response.encodeURL(this.serviceProperties.getService());

        final StringBuffer buffer = new StringBuffer(255);

        synchronized (buffer) {
            buffer.append(this.loginUrl);
            buffer.append("?service=");
            buffer.append(URLEncoder.encode(urlEncodedService, "UTF-8"));
            buffer.append(this.serviceProperties.isSendRenew() ? "&renew=true" : "");
        }

        response.sendRedirect(buffer.toString());
    }

    /**
     * The enterprise-wide CAS login URL. Usually something like
     * <code>https://www.mycompany.com/cas/login</code>.
     *
     * @return the enterprise-wide CAS login URL
     */
    public String getLoginUrl() {
        return this.loginUrl;
    }

    public ServiceProperties getServiceProperties() {
        return this.serviceProperties;
    }

    public void setLoginUrl(final String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setServiceProperties(final ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }
}
