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
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.telscenter.sail.webapp.domain.PeriodNotFoundException;
import org.telscenter.sail.webapp.domain.Run;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.UserImpl;

/**
 * WISE "run" domain object A WISE run is an offering with more information,
 * such as starttime, stoptime, runcode
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = RunImpl.DATA_STORE_NAME)
public class RunImpl extends OfferingImpl implements Run {

    @Transient
    public static final String DATA_STORE_NAME = "runs";

    @Transient
    public static final String COLUMN_NAME_STARTTIME = "start_time";

    @Transient
    public static final String COLUMN_NAME_ENDTIME = "end_time";

    @Transient
    public static final String COLUMN_NAME_RUN_CODE = "run_code";
    
    @Transient
    public static final String PERIODS_JOIN_TABLE_NAME = "runs_related_to_groups";
    
    @Transient
    public static final String PERIODS_JOIN_COLUMN_NAME = "groups_fk";
  
    @Transient
    public static final String RUNS_JOIN_COLUMN_NAME = "runs_fk";

    @Transient
    public static final String OWNERS_JOIN_TABLE_NAME = "runs_related_to_owners";
    
    @Transient
    public static final String OWNERS_JOIN_COLUMN_NAME = "owners_fk";
      
    @Transient
    public static final long serialVersionUID = 1L;
    
    @Column(name = RunImpl.COLUMN_NAME_STARTTIME, nullable = false)
    private Date starttime;

    @Column(name = RunImpl.COLUMN_NAME_ENDTIME)
    private Date endtime;

    @Column(name = RunImpl.COLUMN_NAME_RUN_CODE, nullable = false, unique = true)
    private String runcode;
    
    @OneToMany(targetEntity = PersistentGroup.class, fetch = FetchType.EAGER)
    @JoinTable(name = PERIODS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = RUNS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = PERIODS_JOIN_COLUMN_NAME, nullable = false))
    @Sort(type = SortType.NATURAL)
    private Set<Group> periods = new TreeSet<Group>();
    
    @ManyToMany(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = OWNERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name =  RUNS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = OWNERS_JOIN_COLUMN_NAME, nullable = false))
    private Set<User> owners = new HashSet<User>();

    /**
     * @return the endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     *            the endtime to set
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
     * @param starttime
     *            the starttime to set
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
     * @param runcode
     *            the runcode to set
     */
    public void setRuncode(String runcode) {
        this.runcode = runcode;
    }

	/**
	 * @return the periods
	 */
	public Set<Group> getPeriods() {
		return periods;
	}

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(Set<Group> periods) {
		this.periods = periods;
	}
	
	/**
	 * @return a <code>Set</code> of Users who own this run
	 */
	public Set<User> getOwners() {
		return owners;
	}

	/**
	 * @param owners <code>Set</code> of Users who own this run
	 */
	public void setOwners(Set<User> owners) {
		this.owners = owners;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.domain.Run#getPeriodByName(java.lang.String)
	 */
	public Group getPeriodByName(String periodName) throws PeriodNotFoundException {
		Set<Group> periods = getPeriods();
		for (Group period : periods) {
			if (period.getName().equals(periodName)) {
				return period;
			}
		}
		throw new PeriodNotFoundException("Period " + periodName + 
				" does not exist");
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.Run#isEnded()
	 */
	public boolean isEnded() {
		return this.endtime != null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.Run#isStudentAssociatedToThisRun(User)
	 */
	public boolean isStudentAssociatedToThisRun(User studentUser) {
		return getPeriodOfStudent(studentUser) != null;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.Run#getPeriodOfStudent(User)
	 */
	public Group getPeriodOfStudent(User studentUser) {
		Set<Group> periods = getPeriods();
		for (Group period : periods) {
			if (period.getMembers().contains(studentUser)) {
				return period;
			}
		}
		return null;
	}
}