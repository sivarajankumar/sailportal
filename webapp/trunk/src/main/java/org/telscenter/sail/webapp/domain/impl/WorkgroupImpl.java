/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.domain.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;

import org.telscenter.sail.webapp.domain.Offering;
import org.telscenter.sail.webapp.domain.Workgroup;

/**
 * @author Hiroki Terashima
 * @version $Id: User.java 231 2007-03-26 07:03:00Z hiroki $
 */
@Entity
@Table(name = WorkgroupImpl.DATA_STORE_NAME)
public class WorkgroupImpl implements Workgroup {

	@Transient
	public static final String DATA_STORE_NAME = "workgroups";

	@Transient
	public static final String COLUMN_NAME_SDS_WORKGROUP_FK = "sds_workgroup_fk";

	@Transient
	public static final String COLUMN_NAME_OFFERING_FK = "offering_fk";
	
	@Transient
	public static final String USERS_JOIN_TABLE_NAME = "workgroups_relates_to_users";
	
	@Transient
	public static final String USERS_JOIN_COLUMN_NAME = "user_fk";

	@Transient
	public static final String WORKGROUPS_JOIN_COLUMN_NAME = "workgroup_fk";
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@Version
	@Column(name = "OPTLOCK")
	private Integer version = null;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = SdsWorkgroup.class)
	@JoinColumn(name = WorkgroupImpl.COLUMN_NAME_SDS_WORKGROUP_FK, nullable = false, unique = true)
	private SdsWorkgroup sdsWorkgroup;
	
	@OneToOne(cascade = CascadeType.ALL, targetEntity = OfferingImpl.class)
	@JoinColumn(name = WorkgroupImpl.COLUMN_NAME_OFFERING_FK, nullable = false)
	private Offering offering;
	
	@ManyToMany(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
	@JoinTable(name = WorkgroupImpl.USERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = WORKGROUPS_JOIN_COLUMN_NAME) }, inverseJoinColumns = @JoinColumn(name = USERS_JOIN_COLUMN_NAME))
	private Set<User> members = new HashSet<User>();
	
	/**
	 * @see org.telscenter.sail.webapp.domain.Workgroup#setSdsWorkgroup(net.sf.sail.webapp.domain.sds.SdsWorkgroup)
	 */
	public void setSdsWorkgroup(SdsWorkgroup sdsWorkgroup) {
		this.sdsWorkgroup = sdsWorkgroup;
	}
	
	/**
	 * @return the sdsWorkgroup
	 */
	@SuppressWarnings("unused")
	private SdsWorkgroup getSdsWorkgroup() {
		return sdsWorkgroup;
	}
	
	/**
	 * @return the members
	 */
	public Set<User> getMembers() {
		return members;
	}

	/**
	 * @param members the members to set
	 */
	public void setMembers(Set<User> members) {
		this.members = members;
	}

	/**
	 * @return the offering
	 */
	public Offering getOffering() {
		return offering;
	}

	/**
	 * @param offering the offering to set
	 */
	public void setOffering(Offering offering) {
		this.offering = offering;
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
	 *            the id to set
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
	 *            the version to set
	 */
	@SuppressWarnings("unused")
	private void setVersion(Integer version) {
		this.version = version;
	}
}
