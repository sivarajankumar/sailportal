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

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.sf.sail.webapp.domain.User;

/**
 * WISE "run" domain object
 *  A WISE run is an offering with more information, such as
 *  starttime, stoptime, creator, runcode
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = Run.DATA_STORE_NAME)
public class Run extends OfferingImpl {

	@Transient
	public static final String DATA_STORE_NAME = "runs";

	@Transient
	public static final String COLUMN_NAME_COLUMN_NAME_RUN_CREATOR_FK = "run_creator_fk";

	@Transient
	public static final String COLUMN_NAME_STARTTIME = "start_time";
	
	@Transient
	public static final String COLUMN_NAME_ENDTIME = "end_time";	

	@Transient
	public static final String COLUMN_NAME_RUN_CODE = "run_code";	
	
	@Transient
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	@Version
	@Column(name = "OPTLOCK")
	private Integer version = null;

	@OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name = Run.COLUMN_NAME_COLUMN_NAME_RUN_CREATOR_FK, nullable = false)
	private User creator;
	
	@Column(name = Run.COLUMN_NAME_STARTTIME, nullable = false)
	private Date starttime;

	@Column(name = Run.COLUMN_NAME_ENDTIME, nullable = false)
	private Date endtime;

	@Column(name = Run.COLUMN_NAME_RUN_CODE, nullable = false, unique = true)
	private String runcode;
	
	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the runcode
	 */
	public String getRuncode() {
		return runcode;
	}

	/**
	 * @param runcode the runcode to set
	 */
	public void setRuncode(String runcode) {
		this.runcode = runcode;
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
