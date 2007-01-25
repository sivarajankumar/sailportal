/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.domain.impl;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.domain.sds.SdsUser;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
@Entity
@Table(name = "users")
public class UserImpl implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id = null;

  @Version
  @Column(name = "OPTLOCK")
  private Integer version = null;

  // @Column(name = "sds_user", unique = true, nullable = false)
  @OneToOne(cascade = CascadeType.ALL, targetEntity = SdsUser.class)
  @JoinColumn(name = "sds_user_fk", nullable = false, unique = true)
  private SdsUser sdsUser;

  // @Column(name = "user_details", unique = true, nullable = false)
  @OneToOne(cascade = CascadeType.ALL, targetEntity = PersistentUserDetails.class)
  // @PrimaryKeyJoinColumn
  @JoinColumn(name = "user_details_fk", nullable = false, unique = true)
  private MutableUserDetails userDetails;

  /**
   * @return the userDetails
   */
  public MutableUserDetails getUserDetails() {
    return userDetails;
  }

  /**
   * @param userDetails
   *          the userDetails to set
   */
  public void setUserDetails(MutableUserDetails userDetails) {
    this.userDetails = userDetails;
  }

  /**
   * @return the sdsUser
   */
  @SuppressWarnings("unused")
  private SdsUser getSdsUser() {
    return sdsUser;
  }

  /**
   * @param sdsUser
   *          the sdsUser to set
   */
  public void setSdsUser(SdsUser sdsUser) {
    this.sdsUser = sdsUser;
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
}