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

import org.telscenter.sail.webapp.domain.Grouping;

/**
 * A Period is a TELS Portal implementation of Grouping that
 * A Teacher user uses to separate a big class of students into smaller groupings
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = Period.DATA_STORE_NAME)
public class Period implements Grouping {

	@Transient
	public static final String DATA_STORE_NAME = "periods";

	@Transient
	private static final String COLUMN_NAME_PERIOD_NAME = "period_name";

	@Transient
	private static final String COLUMN_NAME_RUN_FK = "run_fk";
	
	@Transient
	private static final String USERS_JOIN_TABLE_NAME = "periods_relates_to_users";
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@Version
	@Column(name = "OPTLOCK")
	private Integer version = null;
	
	@Column(name = Period.COLUMN_NAME_PERIOD_NAME, nullable = false)
	private String periodname;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = Run.class)
	@JoinColumn(name = Period.COLUMN_NAME_RUN_FK, nullable = false)
	private Run run;

	@ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinTable(name = Period.USERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = "period_fk") }, inverseJoinColumns = @JoinColumn(name = "user_fk"))
	private Set<User> members = new HashSet<User>();
	
	/**
	 * @return the periodname
	 */
	public String getPeriodname() {
		return periodname;
	}

	/**
	 * @param periodname the periodname to set
	 */
	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}

	/**
	 * @return the run
	 */
	public Run getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(Run run) {
		this.run = run;
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
