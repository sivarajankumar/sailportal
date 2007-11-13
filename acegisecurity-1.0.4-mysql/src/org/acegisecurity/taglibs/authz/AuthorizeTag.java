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

package org.acegisecurity.taglibs.authz;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;

import org.acegisecurity.context.SecurityContextHolder;

import org.springframework.util.StringUtils;

import org.springframework.web.util.ExpressionEvaluationUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * An implementation of {@link javax.servlet.jsp.tagext.Tag} that allows it's body through if some authorizations
 * are granted to the request's principal.
 *
 * @author Francois Beausoleil
 * @version $Id: AuthorizeTag.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public class AuthorizeTag extends TagSupport {
    //~ Instance fields ================================================================================================

    private String ifAllGranted = "";
    private String ifAnyGranted = "";
    private String ifNotGranted = "";

    //~ Methods ========================================================================================================

    private Set authoritiesToRoles(Collection c) {
        Set target = new HashSet();

        for (Iterator iterator = c.iterator(); iterator.hasNext();) {
            GrantedAuthority authority = (GrantedAuthority) iterator.next();

            if (null == authority.getAuthority()) {
                throw new IllegalArgumentException(
                    "Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process "
                    + authority.toString());
            }

            target.add(authority.getAuthority());
        }

        return target;
    }

    public int doStartTag() throws JspException {
        if (((null == ifAllGranted) || "".equals(ifAllGranted)) && ((null == ifAnyGranted) || "".equals(ifAnyGranted))
            && ((null == ifNotGranted) || "".equals(ifNotGranted))) {
            return Tag.SKIP_BODY;
        }

        final Collection granted = getPrincipalAuthorities();

        final String evaledIfNotGranted = ExpressionEvaluationUtils.evaluateString("ifNotGranted", ifNotGranted,
                pageContext);

        if ((null != evaledIfNotGranted) && !"".equals(evaledIfNotGranted)) {
            Set grantedCopy = retainAll(granted, parseAuthoritiesString(evaledIfNotGranted));

            if (!grantedCopy.isEmpty()) {
                return Tag.SKIP_BODY;
            }
        }

        final String evaledIfAllGranted = ExpressionEvaluationUtils.evaluateString("ifAllGranted", ifAllGranted,
                pageContext);

        if ((null != evaledIfAllGranted) && !"".equals(evaledIfAllGranted)) {
            if (!granted.containsAll(parseAuthoritiesString(evaledIfAllGranted))) {
                return Tag.SKIP_BODY;
            }
        }

        final String evaledIfAnyGranted = ExpressionEvaluationUtils.evaluateString("ifAnyGranted", ifAnyGranted,
                pageContext);

        if ((null != evaledIfAnyGranted) && !"".equals(evaledIfAnyGranted)) {
            Set grantedCopy = retainAll(granted, parseAuthoritiesString(evaledIfAnyGranted));

            if (grantedCopy.isEmpty()) {
                return Tag.SKIP_BODY;
            }
        }

        return Tag.EVAL_BODY_INCLUDE;
    }

    public String getIfAllGranted() {
        return ifAllGranted;
    }

    public String getIfAnyGranted() {
        return ifAnyGranted;
    }

    public String getIfNotGranted() {
        return ifNotGranted;
    }

    private Collection getPrincipalAuthorities() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (null == currentUser) {
            return Collections.EMPTY_LIST;
        }

        if ((null == currentUser.getAuthorities()) || (currentUser.getAuthorities().length < 1)) {
            return Collections.EMPTY_LIST;
        }

        Collection granted = Arrays.asList(currentUser.getAuthorities());

        return granted;
    }

    private Set parseAuthoritiesString(String authorizationsString) {
        final Set requiredAuthorities = new HashSet();
        final String[] authorities = StringUtils.commaDelimitedListToStringArray(authorizationsString);

        for (int i = 0; i < authorities.length; i++) {
            String authority = authorities[i];

            // Remove the role's whitespace characters without depending on JDK 1.4+
            // Includes space, tab, new line, carriage return and form feed.
            String role = authority.trim(); // trim, don't use spaces, as per SEC-378
            role = StringUtils.replace(role, "\t", "");
            role = StringUtils.replace(role, "\r", "");
            role = StringUtils.replace(role, "\n", "");
            role = StringUtils.replace(role, "\f", "");

            requiredAuthorities.add(new GrantedAuthorityImpl(role));
        }

        return requiredAuthorities;
    }

    /**
     * Find the common authorities between the current authentication's {@link GrantedAuthority} and the ones
     * that have been specified in the tag's ifAny, ifNot or ifAllGranted attributes.<p>We need to manually
     * iterate over both collections, because the granted authorities might not implement {@link
     * Object#equals(Object)} and {@link Object#hashCode()} in the same way as {@link GrantedAuthorityImpl}, thereby
     * invalidating {@link Collection#retainAll(java.util.Collection)} results.</p>
     * <p>
     * <strong>CAVEAT</strong>:  This method <strong>will not</strong> work if the granted authorities
     * returns a <code>null</code> string as the return value of {@link
     * org.acegisecurity.GrantedAuthority#getAuthority()}.
     * </p>
     * <p>Reported by rawdave, on Fri Feb 04, 2005 2:11 pm in the Acegi Security System for Spring forums.</p>
     *
     * @param granted The authorities granted by the authentication. May be any implementation of {@link
     *        GrantedAuthority} that does <strong>not</strong> return <code>null</code> from {@link
     *        org.acegisecurity.GrantedAuthority#getAuthority()}.
     * @param required A {@link Set} of {@link GrantedAuthorityImpl}s that have been built using ifAny, ifAll or
     *        ifNotGranted.
     *
     * @return A set containing only the common authorities between <var>granted</var> and <var>required</var>.
     *
     * @see <a href="http://forum.springframework.org/viewtopic.php?t=3367">authz:authorize ifNotGranted not behaving
     *      as expected</a> TODO: wrong article Url
     */
    private Set retainAll(final Collection granted, final Set required) {
        Set grantedRoles = authoritiesToRoles(granted);
        Set requiredRoles = authoritiesToRoles(required);
        grantedRoles.retainAll(requiredRoles);

        return rolesToAuthorities(grantedRoles, granted);
    }

    private Set rolesToAuthorities(Set grantedRoles, Collection granted) {
        Set target = new HashSet();

        for (Iterator iterator = grantedRoles.iterator(); iterator.hasNext();) {
            String role = (String) iterator.next();

            for (Iterator grantedIterator = granted.iterator(); grantedIterator.hasNext();) {
                GrantedAuthority authority = (GrantedAuthority) grantedIterator.next();

                if (authority.getAuthority().equals(role)) {
                    target.add(authority);

                    break;
                }
            }
        }

        return target;
    }

    public void setIfAllGranted(String ifAllGranted) throws JspException {
        this.ifAllGranted = ifAllGranted;
    }

    public void setIfAnyGranted(String ifAnyGranted) throws JspException {
        this.ifAnyGranted = ifAnyGranted;
    }

    public void setIfNotGranted(String ifNotGranted) throws JspException {
        this.ifNotGranted = ifNotGranted;
    }
}
