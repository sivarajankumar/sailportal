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
package org.telscenter.sail.webapp.domain;

import java.util.Date;
import java.util.Set;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;

/**
 * TELS's representation for a length of time in which the 
 * offering becomes available for the students
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public interface Run extends Offering {

	   /**
     * @return the endtime
     */
    public Date getEndtime();

    /**
     * @param endtime
     *            the endtime to set
     */
    public void setEndtime(Date endtime);

    /**
     * @return the starttime
     */
    public Date getStarttime();

    /**
     * @param starttime
     *            the starttime to set
     */
    public void setStarttime(Date starttime);

    /**
     * @return the runcode
     */
    public String getRuncode();

    /**
     * @param runcode
     *            the runcode to set
     */
    public void setRuncode(String runcode);

	/**
	 * @return the periods associated with this run
	 */
	public Set<Group> getPeriods();

	/**
	 * @param periods the periods to set
	 */
	public void setPeriods(Set<Group> periods);
   
	/**
	 * @return a <code>Set</code> of Users who own this run
	 */
	public Set<User> getOwners();

	/**
	 * @param owners <code>Set</code> of Users who own this run
	 */
	public void setOwners(Set<User> owners);
	
	/**
	 * Returns the period with periodName that is associated
	 * with this run
	 * @param periodName
	 * @return Group the period with the periodName that is
	 *           associated with this run
	 * @throws <code>PeriodNotFoundException</code> if the provided
	 *           period does not exist in the database for this run
	 */
	public Group getPeriodByName(String periodName) throws PeriodNotFoundException;
}
