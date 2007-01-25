/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.domain.sds;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
@Entity
@Table(name = "sds_users")
public class SdsUser implements Serializable {

  @Transient
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;

  @Version
  @Column(name = "OPTLOCK")
  private Integer version = null;

  @Column(name = "userid", unique = true, nullable = false)
  private Integer userid = null;

  @Column(name = "first_name", nullable = false)
  private String firstName = null;

  @Column(name = "last_name", nullable = false)
  private String lastName = null;

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the userid
   */
  public Integer getUserid() {
    return userid;
  }

  /**
   * @param userid
   *          the userid to set
   */
  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  /**
   * @return the id
   */
  @SuppressWarnings("unused")
  private Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  @SuppressWarnings("unused")
  private void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the version
   */
  @SuppressWarnings("unused")
  private Integer getVersion() {
    return version;
  }

  /**
   * @param version
   *          the version to set
   */
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
    result = PRIME * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = PRIME * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = PRIME * result + ((userid == null) ? 0 : userid.hashCode());
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
    final SdsUser other = (SdsUser) obj;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    }
    else if (!firstName.equals(other.firstName))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    }
    else if (!lastName.equals(other.lastName))
      return false;
    if (userid == null) {
      if (other.userid != null)
        return false;
    }
    else if (!userid.equals(other.userid))
      return false;
    return true;
  }
}