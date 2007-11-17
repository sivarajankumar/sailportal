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

import java.util.HashMap;
import java.util.Map;

import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.group.Group;

import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.IndividualScore;

public class IndividualScoreImpl implements IndividualScore {

	private String firstName;
	private String lastName;
	private Group period;
	private Workgroup workgroup;
	private HashMap<Object, String> possibleScores = new HashMap<Object, String>();
	private HashMap<Object, String> accumulatedScores = new HashMap<Object, String>();
	private Long runId;
	private String username;
	private Integer totalGradableSteps;
	
	public String getAccmulatedScore(EStep step) {
		return accumulatedScores.get(step);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Group getPeriod() {
		return period;
	}

	public String getPossibleScore(EStep step) {
		return possibleScores.get(step);
	}

	public Long getRunId() {
		return this.runId;
	}

	public String getTotalAccumulatedScore() {

		int totalScore = 0;

		for (Map.Entry<Object, String> entry : accumulatedScores.entrySet()) {
			String accScore = entry.getValue();
			
			if( !accScore.equals("unscored")) {
				totalScore = totalScore + new Integer(accScore).intValue();
			}// if
		}// for

		return "" + totalScore;
	}

	public String getTotalPossibleScore() {
		int totalScore = 0;

		for (Map.Entry<Object, String> entry : possibleScores.entrySet()) {
			String possScore = entry.getValue();
			totalScore = totalScore + new Integer(possScore).intValue();
		}

		return ""+ totalScore;
	}

	public Workgroup getWorkgroup() {
		return workgroup;
	}

	public void setAccmulatedScore(Object step, String score) {
		accumulatedScores.put(step, score);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPeriod(Group group) {
		this.period = group;
	}

	public void setPossibleScore(Object step, String score) {
		possibleScores.put(step, score);
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public void setWorkgroup(Workgroup workgroup) {
		this.workgroup = workgroup;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTotalGradableSteps() {
		return totalGradableSteps;
	}

	public Integer getTotalGradedSteps() {
		
		int gradableStepsScored = 0;

		for (Map.Entry<Object, String> entry : accumulatedScores.entrySet()) {
			String accScore = entry.getValue();
			
			if( !accScore.equals("unscored")) {
				gradableStepsScored++;
			}// if
		}// for

		return new Integer(gradableStepsScored);
	}

	public Integer getPercentageCompleted() {
		if( getTotalGradableSteps() != 0 ) {
			return new Integer((int) ((((float)this.getTotalGradedSteps())/((float)this.getTotalGradableSteps())) * 100));
		}
		
		return new Integer(0);
	}
	
	public Integer getCurrentScorePercentage() {
		if( getTotalGradableSteps() != 0 ) {
			
			Integer totalAccumatedScore = new Integer(this.getTotalAccumulatedScore());
			Integer totalPossibleScore = new Integer(this.getTotalPossibleScore());
			return new Integer((int) (((float)totalAccumatedScore)/((float)totalPossibleScore) * 100));
		}
		
		return new Integer(0);
	}
	
	public void setTotalGradableSteps(Integer totalGradableSteps) {
		this.totalGradableSteps = totalGradableSteps;
	}

	
	@Override
	public String toString() {
		return this.lastName + ", " + this.firstName + " score "
				+ this.getTotalAccumulatedScore() + " / "
				+ this.getTotalPossibleScore();
	}

	public int compareTo(IndividualScore o) {
		return username.compareTo(o.getUsername());
    }

	// this is TELS-specific.
//	public int compareTo(IndividualScore o) {
//		int lastCmp = lastName.compareTo(o.getLastName());
//		return (lastCmp != 0 ? lastCmp : firstName.compareTo(o.getFirstName()));
//	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		final IndividualScoreImpl other = (IndividualScoreImpl) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
