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

package org.acegisecurity.ui.switchuser;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.AcegiMessageSource;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationCredentialsNotFoundException;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.LockedException;

import org.acegisecurity.context.SecurityContextHolder;

import org.acegisecurity.event.authentication.AuthenticationSwitchUserEvent;

import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import org.acegisecurity.ui.AuthenticationDetailsSource;
import org.acegisecurity.ui.AuthenticationDetailsSourceImpl;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.util.Assert;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Switch User processing filter responsible for user context switching.<p>This filter is similar to Unix 'su'
 * however for Acegi-managed web applications.  A common use-case for this feature is the ability to allow
 * higher-authority users (i.e. ROLE_ADMIN) to switch to a regular user (i.e. ROLE_USER).</p>
 *  <p>This filter assumes that the user performing the switch will be required to be logged in as normal (i.e.
 * ROLE_ADMIN user). The user will then access a page/controller that enables the administrator to specify who they
 * wish to become (see <code>switchUserUrl</code>). <br>
 * <b>Note: This URL will be required to have to appropriate security contraints configured so that  only users of that
 * role can access (i.e. ROLE_ADMIN).</b></p>
 *  <p>On successful switch, the user's  <code>SecurityContextHolder</code> will be updated to reflect the
 * specified user and will also contain an additinal {@link org.acegisecurity.ui.switchuser.SwitchUserGrantedAuthority
 * } which contains the original user.</p>
 *  <p>To 'exit' from a user context, the user will then need to access a URL (see <code>exitUserUrl</code>)  that
 * will switch back to the original user as identified by the <code>SWITCH_USER_GRANTED_AUTHORITY</code>.</p>
 *  <p>To configure the Switch User Processing Filter, create a bean definition for the Switch User processing
 * filter and add to the filterChainProxy. <br>
 * Example:<pre>
 * &lt;bean id="switchUserProcessingFilter" class="org.acegisecurity.ui.switchuser.SwitchUserProcessingFilter">
 *    &lt;property name="authenticationDao" ref="jdbcDaoImpl" />
 *    &lt;property name="switchUserUrl">&lt;value>/j_acegi_switch_user&lt;/value>&lt;/property>
 *    &lt;property name="exitUserUrl">&lt;value>/j_acegi_exit_user&lt;/value>&lt;/property>
 *    &lt;property name="targetUrl">&lt;value>/index.jsp&lt;/value>&lt;/property>&lt;/bean></pre></p>
 *
 * @author Mark St.Godard
 * @version $Id: SwitchUserProcessingFilter.java 1784 2007-02-24 21:00:24Z luke_t $
 *
 * @see org.acegisecurity.ui.switchuser.SwitchUserGrantedAuthority
 */
public class SwitchUserProcessingFilter implements Filter, InitializingBean, ApplicationEventPublisherAware,
    MessageSourceAware {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(SwitchUserProcessingFilter.class);

    // ~ Static fields/initializers
    // =============================================
    public static final String ACEGI_SECURITY_SWITCH_USERNAME_KEY = "j_username";
    public static final String ROLE_PREVIOUS_ADMINISTRATOR = "ROLE_PREVIOUS_ADMINISTRATOR";

    //~ Instance fields ================================================================================================

    private ApplicationEventPublisher eventPublisher;
    private AuthenticationDetailsSource authenticationDetailsSource = new AuthenticationDetailsSourceImpl();
    protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();
    private String exitUserUrl = "/j_acegi_exit_user";
    private String switchUserUrl = "/j_acegi_switch_user";
    private String targetUrl;
    private SwitchUserAuthorityChanger switchUserAuthorityChanger;

    // ~ Instance fields
    // ========================================================
    private UserDetailsService userDetailsService;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(switchUserUrl, "switchUserUrl must be specified");
        Assert.hasLength(exitUserUrl, "exitUserUrl must be specified");
        Assert.hasLength(targetUrl, "targetUrl must be specified");
        Assert.notNull(userDetailsService, "authenticationDao must be specified");
        Assert.notNull(messages, "A message source must be set");
    }

    /**
     * Attempt to exit from an already switched user.
     *
     * @param request The http servlet request
     *
     * @return The original <code>Authentication</code> object or <code>null</code> otherwise.
     *
     * @throws AuthenticationCredentialsNotFoundException If no <code>Authentication</code> associated with this
     *         request.
     */
    protected Authentication attemptExitUser(HttpServletRequest request)
        throws AuthenticationCredentialsNotFoundException {
        // need to check to see if the current user has a SwitchUserGrantedAuthority
        Authentication current = SecurityContextHolder.getContext().getAuthentication();

        if (null == current) {
            throw new AuthenticationCredentialsNotFoundException(messages.getMessage(
                    "SwitchUserProcessingFilter.noCurrentUser", "No current user associated with this request"));
        }

        // check to see if the current user did actual switch to another user
        // if so, get the original source user so we can switch back
        Authentication original = getSourceAuthentication(current);

        if (original == null) {
            logger.error("Could not find original user Authentication object!");
            throw new AuthenticationCredentialsNotFoundException(messages.getMessage(
                    "SwitchUserProcessingFilter.noOriginalAuthentication",
                    "Could not find original Authentication object"));
        }

        // get the source user details
        UserDetails originalUser = null;
        Object obj = original.getPrincipal();

        if ((obj != null) && obj instanceof UserDetails) {
            originalUser = (UserDetails) obj;
        }

        // publish event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new AuthenticationSwitchUserEvent(current, originalUser));
        }

        return original;
    }

    /**
     * Attempt to switch to another user. If the user does not exist or is not active, return null.
     *
     * @param request The http request
     *
     * @return The new <code>Authentication</code> request if successfully switched to another user, <code>null</code>
     *         otherwise.
     *
     * @throws AuthenticationException
     * @throws UsernameNotFoundException If the target user is not found.
     * @throws LockedException DOCUMENT ME!
     * @throws DisabledException If the target user is disabled.
     * @throws AccountExpiredException If the target user account is expired.
     * @throws CredentialsExpiredException If the target user credentials are expired.
     */
    protected Authentication attemptSwitchUser(HttpServletRequest request)
        throws AuthenticationException {
        UsernamePasswordAuthenticationToken targetUserRequest = null;

        String username = request.getParameter(ACEGI_SECURITY_SWITCH_USERNAME_KEY);

        if (username == null) {
            username = "";
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Attempt to switch to user [" + username + "]");
        }

        // load the user by name
        UserDetails targetUser = this.userDetailsService.loadUserByUsername(username);

        // user not found
        if (targetUser == null) {
            throw new UsernameNotFoundException(messages.getMessage("SwitchUserProcessingFilter.usernameNotFound",
                    new Object[] {username}, "Username {0} not found"));
        }

        // account is expired
        if (!targetUser.isAccountNonLocked()) {
            throw new LockedException(messages.getMessage("SwitchUserProcessingFilter.locked", "User account is locked"));
        }

        // user is disabled
        if (!targetUser.isEnabled()) {
            throw new DisabledException(messages.getMessage("SwitchUserProcessingFilter.disabled", "User is disabled"));
        }

        // account is expired
        if (!targetUser.isAccountNonExpired()) {
            throw new AccountExpiredException(messages.getMessage("SwitchUserProcessingFilter.expired",
                    "User account has expired"));
        }

        // credentials expired
        if (!targetUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(messages.getMessage("SwitchUserProcessingFilter.credentialsExpired",
                    "User credentials have expired"));
        }

        // ok, create the switch user token
        targetUserRequest = createSwitchUserToken(request, username, targetUser);

        if (logger.isDebugEnabled()) {
            logger.debug("Switch User Token [" + targetUserRequest + "]");
        }

        // publish event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new AuthenticationSwitchUserEvent(
                    SecurityContextHolder.getContext().getAuthentication(), targetUser));
        }

        return targetUserRequest;
    }

    /**
     * Create a switch user token that contains an additional <tt>GrantedAuthority</tt> that contains the
     * original <code>Authentication</code> object.
     *
     * @param request The http servlet request.
     * @param username The username of target user
     * @param targetUser The target user
     *
     * @return The authentication token
     *
     * @see SwitchUserGrantedAuthority
     */
    private UsernamePasswordAuthenticationToken createSwitchUserToken(HttpServletRequest request, String username,
        UserDetails targetUser) {
        UsernamePasswordAuthenticationToken targetUserRequest;

        // grant an additional authority that contains the original Authentication object
        // which will be used to 'exit' from the current switched user.
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority switchAuthority = new SwitchUserGrantedAuthority(ROLE_PREVIOUS_ADMINISTRATOR, currentAuth);

        // get the original authorities
        List orig = Arrays.asList(targetUser.getAuthorities());

        // Allow subclasses to change the authorities to be granted
        if (switchUserAuthorityChanger != null) {
            switchUserAuthorityChanger.modifyGrantedAuthorities(targetUser, currentAuth, orig);
        }

        // add the new switch user authority
        List newAuths = new ArrayList(orig);
        newAuths.add(switchAuthority);

        GrantedAuthority[] authorities = {};
        authorities = (GrantedAuthority[]) newAuths.toArray(authorities);

        // create the new authentication token
        targetUserRequest = new UsernamePasswordAuthenticationToken(targetUser, targetUser.getPassword(), authorities);

        // set details
        targetUserRequest.setDetails(authenticationDetailsSource.buildDetails((HttpServletRequest) request));

        return targetUserRequest;
    }

    public void destroy() {}

    /**
     *
     * @see javax.servlet.Filter#doFilter
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        Assert.isInstanceOf(HttpServletRequest.class, request);
        Assert.isInstanceOf(HttpServletResponse.class, response);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // check for switch or exit request
        if (requiresSwitchUser(httpRequest)) {
            // if set, attempt switch and store original
            Authentication targetUser = attemptSwitchUser(httpRequest);

            // update the current context to the new target user
            SecurityContextHolder.getContext().setAuthentication(targetUser);

            // redirect to target url
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(httpRequest.getContextPath() + targetUrl));

            return;
        } else if (requiresExitUser(httpRequest)) {
            // get the original authentication object (if exists)
            Authentication originalUser = attemptExitUser(httpRequest);

            // update the current context back to the original user
            SecurityContextHolder.getContext().setAuthentication(originalUser);

            // redirect to target url
            httpResponse.sendRedirect(httpResponse.encodeRedirectURL(httpRequest.getContextPath() + targetUrl));

            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * Find the original <code>Authentication</code> object from the current user's granted authorities. A
     * successfully switched user should have a <code>SwitchUserGrantedAuthority</code> that contains the original
     * source user <code>Authentication</code> object.
     *
     * @param current The current  <code>Authentication</code> object
     *
     * @return The source user <code>Authentication</code> object or <code>null</code> otherwise.
     */
    private Authentication getSourceAuthentication(Authentication current) {
        Authentication original = null;

        // iterate over granted authorities and find the 'switch user' authority
        GrantedAuthority[] authorities = current.getAuthorities();

        for (int i = 0; i < authorities.length; i++) {
            // check for switch user type of authority
            if (authorities[i] instanceof SwitchUserGrantedAuthority) {
                original = ((SwitchUserGrantedAuthority) authorities[i]).getSource();
                logger.debug("Found original switch user granted authority [" + original + "]");
            }
        }

        return original;
    }

    public void init(FilterConfig ignored) throws ServletException {}

    /**
     * Checks the request URI for the presence of <tt>exitUserUrl</tt>.
     *
     * @param request The http servlet request
     *
     * @return <code>true</code> if the request requires a exit user, <code>false</code> otherwise.
     *
     * @see SwitchUserProcessingFilter#exitUserUrl
     */
    protected boolean requiresExitUser(HttpServletRequest request) {
        String uri = stripUri(request);

        return uri.endsWith(request.getContextPath() + exitUserUrl);
    }

    /**
     * Checks the request URI for the presence of <tt>switchUserUrl</tt>.
     *
     * @param request The http servlet request
     *
     * @return <code>true</code> if the request requires a switch, <code>false</code> otherwise.
     *
     * @see SwitchUserProcessingFilter#switchUserUrl
     */
    protected boolean requiresSwitchUser(HttpServletRequest request) {
        String uri = stripUri(request);

        return uri.endsWith(request.getContextPath() + switchUserUrl);
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher)
        throws BeansException {
        this.eventPublisher = eventPublisher;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    /**
     * Set the URL to respond to exit user processing.
     *
     * @param exitUserUrl The exit user URL.
     */
    public void setExitUserUrl(String exitUserUrl) {
        this.exitUserUrl = exitUserUrl;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    /**
     * Set the URL to respond to switch user processing.
     *
     * @param switchUserUrl The switch user URL.
     */
    public void setSwitchUserUrl(String switchUserUrl) {
        this.switchUserUrl = switchUserUrl;
    }

    /**
     * Sets the URL to go to after a successful switch / exit user request.
     *
     * @param targetUrl The target url.
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    /**
     * Sets the authentication data access object.
     *
     * @param authenticationDao The authentication dao
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Strips any content after the ';' in the request URI
     *
     * @param request The http request
     *
     * @return The stripped uri
     */
    private static String stripUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int idx = uri.indexOf(';');

        if (idx > 0) {
            uri = uri.substring(0, idx);
        }

        return uri;
    }

    /**
     * @param switchUserAuthorityChanger to use to fine-tune the authorities granted to subclasses (may be null if
     * SwitchUserProcessingFilter shoudl not fine-tune the authorities)
     */
    public void setSwitchUserAuthorityChanger(SwitchUserAuthorityChanger switchUserAuthorityChanger) {
        this.switchUserAuthorityChanger = switchUserAuthorityChanger;
    }
}
