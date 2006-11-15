/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.domain.authentication.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;

/**
 * Implementation class of <code>MutableGrantedAuthority</code> that uses the
 * Hibernate persistence mechanism.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
@Entity
@Table(name = "roles")
public class HibernateGrantedAuthority implements MutableGrantedAuthority {

  @Transient
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  @Column(name = "OPTLOCK")
  private Integer version;

  @Column(name = "role", unique = true, nullable = false)
  private String authority;

  /**
   * @see net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority#setAuthority(java.lang.String)
   */
  public void setAuthority(String role) {
    this.authority = role;
  }

  /**
   * @see org.acegisecurity.GrantedAuthority#getAuthority()
   */
  public String getAuthority() {
    return this.authority;
  }

  @SuppressWarnings("unused")
  private Long getId() {
    return id;
  }

  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  @SuppressWarnings("unused")
  private Integer getVersion() {
    return version;
  }

  @SuppressWarnings("unused")
  private void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    result = PRIME * result
        + ((this.authority == null) ? 0 : this.authority.hashCode());
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
    final HibernateGrantedAuthority other = (HibernateGrantedAuthority) obj;
    if (this.authority == null) {
      if (other.authority != null)
        return false;
    }
    else if (!this.authority.equals(other.authority))
      return false;
    return true;
  }
}