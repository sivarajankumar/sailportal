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

package org.acegisecurity.userdetails;

import org.acegisecurity.GrantedAuthority;

import org.springframework.util.Assert;


/**
 * Models core user information retieved by an {@link UserDetailsService}.<p>Implemented with value object
 * semantics (immutable after construction, like a <code>String</code>). Developers may use this class directly,
 * subclass it, or write their own {@link UserDetails} implementation from scratch.</p>
 *
 * @author Ben Alex
 * @version $Id: User.java 1784 2007-02-24 21:00:24Z luke_t $
 */
public class User implements UserDetails {
    //~ Instance fields ================================================================================================

    private static final long serialVersionUID = 1L;
    private String password;
    private String username;
    private GrantedAuthority[] authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    //~ Constructors ===================================================================================================

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.acegisecurity.providers.dao.DaoAuthenticationProvider}.
     *
     * @param username the username presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param enabled set to <code>true</code> if the user is enabled
     * @param authorities the authorities that should be granted to the caller
     *        if they presented the correct username and password and the user
     *        is enabled
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *         either as a parameter or as an element in the
     *         <code>GrantedAuthority[]</code> array
     *
     * @deprecated use new constructor with extended properties (this
     *             constructor will be removed from release 1.0.0)
     */
    public User(String username, String password, boolean enabled, GrantedAuthority[] authorities)
        throws IllegalArgumentException {
        this(username, password, enabled, true, true, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.acegisecurity.providers.dao.DaoAuthenticationProvider}.
     *
     * @param username the username presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param enabled set to <code>true</code> if the user is enabled
     * @param accountNonExpired set to <code>true</code> if the account has not
     *        expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *        have not expired
     * @param authorities the authorities that should be granted to the caller
     *        if they presented the correct username and password and the user
     *        is enabled
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *         either as a parameter or as an element in the
     *         <code>GrantedAuthority[]</code> array
     *
     * @deprecated use new constructor with extended properties (this
     *             constructor will be removed from release 1.0.0)
     */
    public User(String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, GrantedAuthority[] authorities)
        throws IllegalArgumentException {
        this(username, password, enabled, accountNonExpired, credentialsNonExpired, true, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.acegisecurity.providers.dao.DaoAuthenticationProvider}.
     *
     * @param username the username presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param enabled set to <code>true</code> if the user is enabled
     * @param accountNonExpired set to <code>true</code> if the account has not
     *        expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *        have not expired
     * @param accountNonLocked set to <code>true</code> if the account is not
     *        locked
     * @param authorities the authorities that should be granted to the caller
     *        if they presented the correct username and password and the user
     *        is enabled
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *         either as a parameter or as an element in the
     *         <code>GrantedAuthority[]</code> array
     */
    public User(String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities)
        throws IllegalArgumentException {
        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        setAuthorities(authorities);
    }

    //~ Methods ========================================================================================================

    public boolean equals(Object rhs) {
        if (!(rhs instanceof User) || (rhs == null)) {
            return false;
        }

        User user = (User) rhs;

        // We rely on constructor to guarantee any User has non-null and >0
        // authorities
        if (user.getAuthorities().length != this.getAuthorities().length) {
            return false;
        }

        for (int i = 0; i < this.getAuthorities().length; i++) {
            if (!this.getAuthorities()[i].equals(user.getAuthorities()[i])) {
                return false;
            }
        }

        // We rely on constructor to guarantee non-null username and password
        return (this.getPassword().equals(user.getPassword()) && this.getUsername().equals(user.getUsername())
                && (this.isAccountNonExpired() == user.isAccountNonExpired())
                && (this.isAccountNonLocked() == user.isAccountNonLocked())
                && (this.isCredentialsNonExpired() == user.isCredentialsNonExpired())
                && (this.isEnabled() == user.isEnabled()));
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int hashCode() {
        int code = 9792;

        if (this.getAuthorities() != null) {
            for (int i = 0; i < this.getAuthorities().length; i++) {
                code = code * (this.getAuthorities()[i].hashCode() % 7);
            }
        }

        if (this.getPassword() != null) {
            code = code * (this.getPassword().hashCode() % 7);
        }

        if (this.getUsername() != null) {
            code = code * (this.getUsername().hashCode() % 7);
        }

        if (this.isAccountNonExpired()) {
            code = code * -2;
        }

        if (this.isAccountNonLocked()) {
            code = code * -3;
        }

        if (this.isCredentialsNonExpired()) {
            code = code * -5;
        }

        if (this.isEnabled()) {
            code = code * -7;
        }

        return code;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    protected void setAuthorities(GrantedAuthority[] authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");

        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i],
                "Granted authority element " + i + " is null - GrantedAuthority[] cannot contain any null elements");
        }

        this.authorities = authorities;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if (this.getAuthorities() != null) {
            sb.append("Granted Authorities: ");

            for (int i = 0; i < this.getAuthorities().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }

                sb.append(this.getAuthorities()[i].toString());
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
