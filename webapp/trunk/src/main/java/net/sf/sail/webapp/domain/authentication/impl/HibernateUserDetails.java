/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.domain.authentication.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;

import org.acegisecurity.GrantedAuthority;

/**
 * Implementation class of <code>MutableUserDetails</code> that uses the
 * Hibernate persistence mechanism.
 * 
 * @author Cynick Young
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 */
//TODO Add validation for user information such as username not null, etc.
@Entity
@Table(name = "users")
public class HibernateUserDetails implements MutableUserDetails {

	private static final long serialVersionUID = 1L;

	private Long id = null;

	private Integer version = null;

	// Hibernate annotations requires the use of a java <code>Collection</code>.
	// However, Acegi Security deals with an array. There are internal methods
	// to convert to and from the different data structures.
	private Set<HibernateGrantedAuthority> grantedAuthorities = null;

	private String password = null;

	private String username = null;

	private String emailAddress = null;

	private Boolean accountNonExpired = Boolean.TRUE;

	private Boolean accountNonLocked = Boolean.TRUE;

	private Boolean credentialsNonExpired = Boolean.TRUE;

	private Boolean enabled = Boolean.TRUE;

	@SuppressWarnings("unused")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	@SuppressWarnings("unused")
	@Version
	@Column(name = "OPTLOCK")
	private Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	@SuppressWarnings("unused")
	private void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @see net.sf.sail.webapp.domain.authentication.MutableUserDetails#setPassword(java.lang.String)
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @see net.sf.sail.webapp.domain.authentication.MutableUserDetails#setUsername(java.lang.String)
	 */
	@Column(unique = true, name = "username", nullable = false)
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
	 */
	@Transient
	public GrantedAuthority[] getAuthorities() {
		// Used by Acegi Security. This implements the required method from
		// Acegi
		// Security. This implementation does not obtain the values directly
		// from
		// the data store.
		return this.getGrantedAuthorities().toArray(new GrantedAuthority[0]);
	}

	/**
	 * @see net.sf.sail.webapp.domain.authentication.MutableUserDetails#setAuthorities(org.acegisecurity.GrantedAuthority[])
	 */
	@SuppressWarnings("unchecked")
	public void setAuthorities(GrantedAuthority[] authorities) {
		this.setGrantedAuthorities(new HashSet(Arrays.asList(authorities)));
	}

	@ManyToMany(targetEntity = net.sf.sail.webapp.domain.authentication.impl.HibernateGrantedAuthority.class)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_fk") }, inverseJoinColumns = @JoinColumn(name = "role_fk"))
	private Set<HibernateGrantedAuthority> getGrantedAuthorities() {
		/* Used by Hibernate only for persistence */
		return this.grantedAuthorities;
	}

	@SuppressWarnings("unused")
	private void setGrantedAuthorities(
			Set<HibernateGrantedAuthority> grantedAuthorities) {
		/* Used by Hibernate only for persistence */
		this.grantedAuthorities = grantedAuthorities;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#getPassword()
	 */
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#getUsername()
	 */
	@Column(name = "username", unique = true, nullable = false)
	public String getUsername() {
		return this.username;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Column(name = "account_not_expired", nullable = false)
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Column(name = "account_not_locked", nullable = false)
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Column(name = "credentials_not_expired", nullable = false)
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
	 */
	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	@SuppressWarnings("unused")
	private void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	@SuppressWarnings("unused")
	private void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	@SuppressWarnings("unused")
	private void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	@SuppressWarnings("unused")
	private void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((this.username == null) ? 0 : this.username.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final HibernateUserDetails other = (HibernateUserDetails) obj;
		if (this.username == null) {
			if (other.username != null)
				return false;
		} else if (!this.username.equals(other.username))
			return false;
		return true;
	}

	/**
	 * @see net.sf.sail.webapp.domain.authentication.MutableUserDetails#getEmailAddress()
	 */
	@Column(name = "email_address", nullable = true)
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @see net.sf.sail.webapp.domain.authentication.MutableUserDetails#setEmailAddress(java.lang.String)
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}