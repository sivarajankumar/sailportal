/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
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
package org.telscenter.sail.webapp.domain.brainstorm;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.question.Question;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;

import net.sf.sail.webapp.domain.Persistable;

/**
 * Brainstorm (aka "Q&A") interface. A Brainstorm has:
 * -- a question, usually made by teachers
 * -- 0 or more answers (or "posts") usually made by students
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public interface Brainstorm extends Persistable {
	
	/**
	 * Returns the <code>Question</code> for this brainstorm.
	 * 
	 * @return <code>Question</code> this <code>Brainstorm</code>'s
	 *     question.
	 */
	public Question getQuestion();
	
	/**
	 * Set the <code>Question</code> for this brainstorm.
	 * 
	 * @param <code>Question</code> this <code>Brainstorm</code>'s
	 *     question.
	 */
	public void setQuestion(Question question);
	
	/**
	 * Returns a list of <code>Answer</code> for this brainstorm, filtered
	 * by the subgroups of the logged-in user, if such subgroup exists.
	 * 
	 * @return a List of <code>Answer</code> 
	 */
	public Set<Answer> getAnswers();
	
	/**
	 * Sets a list of <code>Answer</code> for this brainstorm.
	 * 
	 * @param a List of <code>Answer</code> 
	 */
	public void setAnswers(Set<Answer> answers);
	
	/**
	 * Adds an <code>Answer</code> to this brainstorm.
	 * 
	 * @param answer Add this Answer to this Brainstorm.
	 */
	public void addAnswer(Answer answer);
	
	/**
	 * Indicates whether the students posts to this brainstorm anonymously
	 * 
	 * @return true iff students can post anonymously on this brainstorm.
	 */
	public boolean isAnonymousAllowed();
	
	/**
	 * Indicates whether the students posts to this brainstorm anonymously
	 * 
	 * @param true iff students can post anonymously on this brainstorm.
	 */
	public void setAnonymousAllowed(boolean isPostingAnonymousAllowed);
	
	/**
	 * Return the <code>Run</code> that this Brainstorm is for
	 * 
	 * @return <code>Run</code> that this Brainstorm has
	 *    been set up in.
	 */
	public Run getRun();
	
	/**
	 * Set the <code>Run</code> that this Brainstorm is for
	 * 
	 * @param <code>Run</code> that this Brainstorm has
	 *    been set up in.
	 */
	public void setRun(Run run);
	
	/**
	 * Returns a Set of Workgroups that has requested for help
	 * on this Brainstorm.
	 * 
	 * @return <code>Set</code> of <code>WISEWorkgroup</code>.
	 */
	public Set<WISEWorkgroup> getWorkgroupsThatRequestHelp();
	
	/**
	 * Returns an association of workgroup and their last visited timestamp
	 * on this brainstorm step.
	 * 
	 * @return <code>Map</code> of <code>WISEWorkgroup</code> and 
	 *     <code>Date</code>
	 */
	public Map<WISEWorkgroup, Date> getWorkgroupLastVisitedMap();
}
