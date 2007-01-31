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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Represents a user from the Sail Data Service (SDS). The object is persisted
 * into the local portal data store because a mapping of the relationship with
 * the portal user needs to be maintained.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
@Entity
@Table(name = SdsUser.DATA_STORE_NAME)
public class SdsUser implements SdsObject {

  @Transient
  public static final String DATA_STORE_NAME = "sds_users";

  @Transient
  public static final String COLUMN_NAME_USER_ID = "user_id";

  @Transient
  public static final String COLUMN_NAME_FIRST_NAME = "first_name";

  @Transient
  public static final String COLUMN_NAME_LAST_NAME = "last_name";

  @Transient
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;

  @Version
  @Column(name = "OPTLOCK")
  private Integer version = null;

  @Column(name = SdsUser.COLUMN_NAME_USER_ID, unique = true, nullable = false)
  private Integer sdsObjectId = null;

  @Column(name = SdsUser.COLUMN_NAME_FIRST_NAME, nullable = false)
  private String firstName = null;

  @Column(name = SdsUser.COLUMN_NAME_LAST_NAME, nullable = false)
  private String lastName = null;

  /**
   * @return the sdsObjectId
   */
  public Integer getSdsObjectId() {
    return sdsObjectId;
  }

  /**
   * @param sdsObjectId
   *          the sdsObjectId to set
   */
  public void setSdsObjectId(Integer sdsObjectId) {
    this.sdsObjectId = sdsObjectId;
  }

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
    result = PRIME * result
        + ((sdsObjectId == null) ? 0 : sdsObjectId.hashCode());
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
    if (sdsObjectId == null) {
      if (other.sdsObjectId != null)
        return false;
    }
    else if (!sdsObjectId.equals(other.sdsObjectId))
      return false;
    return true;
  }
}