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

package org.acegisecurity.context;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * Populates the {@link SecurityContextHolder} with information obtained from
 * the <code>HttpSession</code>.
 * 
 * <p>
 * The <code>HttpSession</code> will be queried to retrieve the
 * <code>SecurityContext</code> that should be stored against the
 * <code>SecurityContextHolder</code> for the duration of the web request. At
 * the end of the web request, any updates made to the
 * <code>SecurityContextHolder</code> will be persisted back to the
 * <code>HttpSession</code> by this filter.
 * </p>
 * <p>
 * If a valid <code>SecurityContext</code> cannot be obtained from the
 * <code>HttpSession</code> for whatever reason, a fresh
 * <code>SecurityContext</code> will be created and used instead. The created
 * object will be of the instance defined by the {@link #setContext(Class)}
 * method (which defaults to {@link
 * org.acegisecurity.context.SecurityContextImpl}.
 * </p>
 * <p>
 * No <code>HttpSession</code> will be created by this filter if one does not
 * already exist. If at the end of the web request the <code>HttpSession</code>
 * does not exist, a <code>HttpSession</code> will <b>only</b> be created if
 * the current contents of the <code>SecurityContextHolder</code> are not
 * {@link java.lang.Object#equals(java.lang.Object)} to a <code>new</code>
 * instance of {@link #setContext(Class)}. This avoids needless
 * <code>HttpSession</code> creation, but automates the storage of changes
 * made to the <code>SecurityContextHolder</code>. There is one exception to
 * this rule, that is if the {@link #forceEagerSessionCreation} property is
 * <code>true</code>, in which case sessions will always be created
 * irrespective of normal session-minimisation logic (the default is
 * <code>false</code>, as this is resource intensive and not recommended).
 * </p>
 * <p>
 * This filter will only execute once per request, to resolve servlet container
 * (specifically Weblogic) incompatibilities.
 * </p>
 * <p>
 * If for whatever reason no <code>HttpSession</code> should <b>ever</b> be
 * created (eg this filter is only being used with Basic authentication or
 * similar clients that will never present the same <code>jsessionid</code>
 * etc), the {@link #setAllowSessionCreation(boolean)} should be set to
 * <code>false</code>. Only do this if you really need to conserve server
 * memory and ensure all classes using the <code>SecurityContextHolder</code>
 * are designed to have no persistence of the <code>SecurityContext</code>
 * between web requests. Please note that if {@link #forceEagerSessionCreation}
 * is <code>true</code>, the <code>allowSessionCreation</code> must also be
 * <code>true</code> (setting it to <code>false</code> will cause a startup
 * time error).
 * </p>
 * <p>
 * This filter MUST be executed BEFORE any authentication processing mechanisms.
 * Authentication processing mechanisms (eg BASIC, CAS processing filters etc)
 * expect the <code>SecurityContextHolder</code> to contain a valid
 * <code>SecurityContext</code> by the time they execute.
 * </p>
 * 
 * @author Ben Alex
 * @author Patrick Burleson
 * @version $Id: HttpSessionContextIntegrationFilter.java 1784 2007-02-24
 * 21:00:24Z luke_t $
 */
public class HttpSessionContextIntegrationFilter implements InitializingBean, Filter {
	// ~ Static fields/initializers
	// =====================================================================================

	protected static final Log logger = LogFactory.getLog(HttpSessionContextIntegrationFilter.class);

	static final String FILTER_APPLIED = "__acegi_session_integration_filter_applied";

	public static final String ACEGI_SECURITY_CONTEXT_KEY = "ACEGI_SECURITY_CONTEXT";

	// ~ Instance fields
	// ================================================================================================

	private Class context = SecurityContextImpl.class;

	private Object contextObject;

	/**
	 * Indicates if this filter can create a <code>HttpSession</code> if
	 * needed (sessions are always created sparingly, but setting this value to
	 * <code>false</code> will prohibit sessions from ever being created).
	 * Defaults to <code>true</code>. Do not set to <code>false</code> if
	 * you are have set {@link #forceEagerSessionCreation} to <code>true</code>,
	 * as the properties would be in conflict.
	 */
	private boolean allowSessionCreation = true;

	/**
	 * Indicates if this filter is required to create a <code>HttpSession</code>
	 * for every request before proceeding through the filter chain, even if the
	 * <code>HttpSession</code> would not ordinarily have been created. By
	 * default this is <code>false</code>, which is entirely appropriate for
	 * most circumstances as you do not want a <code>HttpSession</code>
	 * created unless the filter actually needs one. It is envisaged the main
	 * situation in which this property would be set to <code>true</code> is
	 * if using other filters that depend on a <code>HttpSession</code>
	 * already existing, such as those which need to obtain a session ID. This
	 * is only required in specialised cases, so leave it set to
	 * <code>false</code> unless you have an actual requirement and are
	 * conscious of the session creation overhead.
	 */
	private boolean forceEagerSessionCreation = false;

	/**
	 * Indicates whether the <code>SecurityContext</code> will be cloned from
	 * the <code>HttpSession</code>. The default is to simply reference (ie
	 * the default is <code>false</code>). The default may cause issues if
	 * concurrent threads need to have a different security identity from other
	 * threads being concurrently processed that share the same
	 * <code>HttpSession</code>. In most normal environments this does not
	 * represent an issue, as changes to the security identity in one thread is
	 * allowed to affect the security identitiy in other threads associated with
	 * the same <code>HttpSession</code>. For unusual cases where this is not
	 * permitted, change this value to <code>true</code> and ensure the
	 * {@link #context} is set to a <code>SecurityContext</code> that
	 * implements {@link Cloneable} and overrides the <code>clone()</code>
	 * method.
	 */
	private boolean cloneFromHttpSession = false;

	public boolean isCloneFromHttpSession() {
		return cloneFromHttpSession;
	}

	public void setCloneFromHttpSession(boolean cloneFromHttpSession) {
		this.cloneFromHttpSession = cloneFromHttpSession;
	}

	public HttpSessionContextIntegrationFilter() throws ServletException {
		this.contextObject = generateNewContext();
	}

	// ~ Methods
	// ========================================================================================================

	public void afterPropertiesSet() throws Exception {
		if ((this.context == null) || (!SecurityContext.class.isAssignableFrom(this.context))) {
			throw new IllegalArgumentException("context must be defined and implement SecurityContext "
					+ "(typically use org.acegisecurity.context.SecurityContextImpl; existing class is " + this.context
					+ ")");
		}

		if ((forceEagerSessionCreation == true) && (allowSessionCreation == false)) {
			throw new IllegalArgumentException(
					"If using forceEagerSessionCreation, you must set allowSessionCreation to also be true");
		}
	}

	/**
	 * Does nothing. We use IoC container lifecycle services instead.
	 */
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		boolean filterApplied = false;
		if ((request != null) && (request.getAttribute(FILTER_APPLIED) != null)) {
			// ensure that filter is only applied once per request
			chain.doFilter(request, response);
		}
		else {
			if (request != null) {
				filterApplied = true;
				request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			}

			HttpSession httpSession = null;
			boolean httpSessionExistedAtStartOfRequest = false;

			try {
				httpSession = ((HttpServletRequest) request).getSession(forceEagerSessionCreation);
			}
			catch (IllegalStateException ignored) {
			}

			if (httpSession != null) {
				httpSessionExistedAtStartOfRequest = true;

				Object contextFromSessionObject = httpSession.getAttribute(ACEGI_SECURITY_CONTEXT_KEY);

				if (contextFromSessionObject != null) {
					// Clone if required (see SEC-356)
					if (cloneFromHttpSession) {
						Assert.isInstanceOf(Cloneable.class, contextFromSessionObject,
								"Context must implement Clonable and provide a Object.clone() method");
						try {
							Method m = contextFromSessionObject.getClass().getMethod("clone", new Class[] {});
							if (!m.isAccessible()) {
								m.setAccessible(true);
							}
							contextFromSessionObject = m.invoke(contextFromSessionObject, new Object[] {});
						}
						catch (Exception ex) {
							ReflectionUtils.handleReflectionException(ex);
						}
					}

					if (contextFromSessionObject instanceof SecurityContext) {
						if (logger.isDebugEnabled()) {
							logger.debug("Obtained from ACEGI_SECURITY_CONTEXT a valid SecurityContext and "
									+ "set to SecurityContextHolder: '" + contextFromSessionObject + "'");
						}

						SecurityContextHolder.setContext((SecurityContext) contextFromSessionObject);
					}
					else {
						if (logger.isWarnEnabled()) {
							logger
									.warn("ACEGI_SECURITY_CONTEXT did not contain a SecurityContext but contained: '"
											+ contextFromSessionObject
											+ "'; are you improperly modifying the HttpSession directly "
											+ "(you should always use SecurityContextHolder) or using the HttpSession attribute "
											+ "reserved for this class? - new SecurityContext instance associated with "
											+ "SecurityContextHolder");
						}

						SecurityContextHolder.setContext(generateNewContext());
					}
				}
				else {
					if (logger.isDebugEnabled()) {
						logger.debug("HttpSession returned null object for ACEGI_SECURITY_CONTEXT - new "
								+ "SecurityContext instance associated with SecurityContextHolder");
					}

					SecurityContextHolder.setContext(generateNewContext());
				}
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug("No HttpSession currently exists - new SecurityContext instance "
							+ "associated with SecurityContextHolder");
				}

				SecurityContextHolder.setContext(generateNewContext());
			}

			// Make the HttpSession null, as we want to ensure we don't keep
			// a reference to the HttpSession laying around in case the
			// chain.doFilter() invalidates it.
			httpSession = null;

			// Proceed with chain
			int contextWhenChainProceeded = SecurityContextHolder.getContext().hashCode();

			try {
				chain.doFilter(request, response);
			}
			catch (IOException ioe) {
				throw ioe;
			}
			catch (ServletException se) {
				throw se;
			}
			finally {
				// do clean up, even if there was an exception
				// Store context back to HttpSession
				try {
					httpSession = ((HttpServletRequest) request).getSession(false);
				}
				catch (IllegalStateException ignored) {
				}

				if ((httpSession == null) && httpSessionExistedAtStartOfRequest) {
					if (logger.isDebugEnabled()) {
						logger.debug("HttpSession is now null, but was not null at start of request; "
								+ "session was invalidated, so do not create a new session");
					}
				}

				// Generate a HttpSession only if we need to
				if ((httpSession == null) && !httpSessionExistedAtStartOfRequest) {
					if (!allowSessionCreation) {
						if (logger.isDebugEnabled()) {
							logger
									.debug("The HttpSession is currently null, and the "
											+ "HttpSessionContextIntegrationFilter is prohibited from creating an HttpSession "
											+ "(because the allowSessionCreation property is false) - SecurityContext thus not "
											+ "stored for next request");
						}
					}
					else if (!contextObject.equals(SecurityContextHolder.getContext())) {
						if (logger.isDebugEnabled()) {
							logger.debug("HttpSession being created as SecurityContextHolder contents are non-default");
						}

						try {
							httpSession = ((HttpServletRequest) request).getSession(true);
						}
						catch (IllegalStateException ignored) {
						}
					}
					else {
						if (logger.isDebugEnabled()) {
							logger
									.debug("HttpSession is null, but SecurityContextHolder has not changed from default: ' "
											+ SecurityContextHolder.getContext()
											+ "'; not creating HttpSession or storing SecurityContextHolder contents");
						}
					}
				}

				// If HttpSession exists, store current
				// SecurityContextHolder contents but only if
				// SecurityContext has
				// actually changed (see JIRA SEC-37)
				if ((httpSession != null)
						&& (SecurityContextHolder.getContext().hashCode() != contextWhenChainProceeded)) {
					httpSession.setAttribute(ACEGI_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

					if (logger.isDebugEnabled()) {
						logger.debug("SecurityContext stored to HttpSession: '" + SecurityContextHolder.getContext()
								+ "'");
					}
				}

				if (filterApplied) {
					request.removeAttribute(FILTER_APPLIED);
				}
				
				// Remove SecurityContextHolder contents
				SecurityContextHolder.clearContext();

				if (logger.isDebugEnabled()) {
					logger.debug("SecurityContextHolder set to new context, as request processing completed");
				}
			}
		}
	}

	public SecurityContext generateNewContext() throws ServletException {
		try {
			return (SecurityContext) this.context.newInstance();
		}
		catch (InstantiationException ie) {
			throw new ServletException(ie);
		}
		catch (IllegalAccessException iae) {
			throw new ServletException(iae);
		}
	}

	public Class getContext() {
		return context;
	}

	/**
	 * Does nothing. We use IoC container lifecycle services instead.
	 * 
	 * @param filterConfig ignored
	 * 
	 * @throws ServletException ignored
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public boolean isAllowSessionCreation() {
		return allowSessionCreation;
	}

	public boolean isForceEagerSessionCreation() {
		return forceEagerSessionCreation;
	}

	public void setAllowSessionCreation(boolean allowSessionCreation) {
		this.allowSessionCreation = allowSessionCreation;
	}

	public void setContext(Class secureContext) {
		this.context = secureContext;
	}

	public void setForceEagerSessionCreation(boolean forceEagerSessionCreation) {
		this.forceEagerSessionCreation = forceEagerSessionCreation;
	}
}
