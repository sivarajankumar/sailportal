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

  private static final long serialVersionUID = 1L;

  private Long id;

  private Integer version;

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
  @Column(name = "role", unique = true, nullable = false)
  public String getAuthority() {
    return this.authority;
  }

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the version
   */
  @Version
  @Column(name = "OPTLOCK")
  public Integer getVersion() {
    return version;
  }

  /**
   * @param version
   *          the version to set
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

}
