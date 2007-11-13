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

package org.acegisecurity.ui.x509;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.ui.AuthenticationEntryPoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;

/**
 * In the X.509 authentication case (unlike CAS, for example) the certificate
 * will already have been extracted from the request and a secure context
 * established by the time the security-enforcement filter is invoked.
 * <p>
 * Therefore this class isn't actually responsible for the commencement of
 * authentication, as it is in the case of other providers. It will be called if
 * the certificate was rejected by Acegi's X509AuthenticationProvider, resulting
 * in a null authentication.
 * </p>
 * The <code>commence</code> method will always return an
 * <code>HttpServletResponse.SC_FORBIDDEN</code> (403 error).
 * 
 * @author Luke Taylor
 * @version $Id: X509ProcessingFilterEntryPoint.java 1496 2006-05-23 13:38:33Z
 * benalex $
 * 
 * @see org.acegisecurity.ui.ExceptionTranslationFilter
 */
public class X509ProcessingFilterEntryPoint implements AuthenticationEntryPoint, Ordered {
	// ~ Static fields/initializers
	// =====================================================================================

	private static final Log logger = LogFactory.getLog(X509ProcessingFilterEntryPoint.class);

	// ~ instance fields
	// =====================================================================================
	
	private int order = Integer.MAX_VALUE; // ~ default

	// ~ Methods
	// ========================================================================================================

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * Returns a 403 error code to the client.
	 * 
	 * @param request DOCUMENT ME!
	 * @param response DOCUMENT ME!
	 * @param authException DOCUMENT ME!
	 * 
	 * @throws IOException DOCUMENT ME!
	 * @throws ServletException DOCUMENT ME!
	 */
	public void commence(ServletRequest request, ServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("X509 entry point called. Rejecting access");
		}

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
	}
}
