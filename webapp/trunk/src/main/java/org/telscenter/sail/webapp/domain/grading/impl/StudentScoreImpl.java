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

package org.telscenter.sail.webapp.domain.grading.impl;

import org.telscenter.sail.webapp.domain.grading.StudentScore;

public class StudentScoreImpl extends IndividualScoreNumericImpl implements StudentScore {

	private String firstName;
	private String lastName;

	/* (non-Javadoc)
	 * @see org.telscenter.sail.webapp.domain.grading.StudentScore#getFirstName()
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/* (non-Javadoc)
	 * @see org.telscenter.sail.webapp.domain.grading.StudentScore#getLastName()
	 */
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see org.telscenter.sail.webapp.domain.grading.StudentScore#setFirstName(java.lang.String)
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see org.telscenter.sail.webapp.domain.grading.StudentScore#setLastName(java.lang.String)
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName + " score "
				+ this.getTotalAccumulatedScore() + " / "
				+ this.getTotalPossibleScore();
	}
	
	/**
	 * Compares student names for sorting
	 * 
	 * @param o
	 * @return
	 */
	public int compareTo(StudentScore o) {
		int lastCmp = lastName.compareTo(o.getLastName());
		return (lastCmp != 0 ? lastCmp : firstName.compareTo(o.getFirstName()));
	}
}