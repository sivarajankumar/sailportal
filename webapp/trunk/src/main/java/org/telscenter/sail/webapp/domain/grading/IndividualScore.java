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

package org.telscenter.sail.webapp.domain.grading;

import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.group.Group;

import org.telscenter.pas.emf.pas.EStep;


/**
 * Represents a particular students score
 * 
 * @author Anthony Perritano
 * @version $Id$
 */
public interface IndividualScore extends Comparable<IndividualScore> {

	//getters
	
	public String getFirstName();
	public String getLastName();
	public String getAccmulatedScore(EStep step);
	public String getPossibleScore(EStep step);
	public String getTotalAccumulatedScore();
	public String getTotalPossibleScore();
	public Integer getTotalGradableSteps();
	public Integer getTotalGradedSteps();
	public Workgroup getWorkgroup();
	public Group getPeriod();
	public Long getRunId();
	public String getUsername();
	
	public Integer getPercentageCompleted(); 


	
	//setters
	public void setFirstName(String firstName);
	public void setLastName(String lastName);
	public void setAccmulatedScore(Object step, String score);
	public void setPossibleScore(Object step, String score);
	public void setWorkgroup(Workgroup workgroup);
	public void setPeriod(Group group);
	public void setRunId(Long runId);
	public void setUsername(String username);
	public void setTotalGradableSteps(Integer totalGradableSteps);
	
	
	
}
