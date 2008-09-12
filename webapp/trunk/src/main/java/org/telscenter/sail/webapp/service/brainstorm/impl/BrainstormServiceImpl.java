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
package org.telscenter.sail.webapp.service.brainstorm.impl;

import java.io.Serializable;
import java.util.Set;

import net.sf.sail.webapp.dao.ObjectNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.dao.brainstorm.BrainstormDao;
import org.telscenter.sail.webapp.dao.brainstorm.answer.AnswerDao;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Revision;
import org.telscenter.sail.webapp.domain.brainstorm.comment.Comment;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class BrainstormServiceImpl implements BrainstormService {

	private BrainstormDao<Brainstorm> brainstormDao;  // mock the dao
	
	private AnswerDao<Answer> answerDao;
	
	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#createBrainstorm(org.telscenter.sail.webapp.domain.brainstorm.Brainstorm)
	 */
	@Transactional()
	public void createBrainstorm(Brainstorm brainstorm) {
		this.brainstormDao.save(brainstorm);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addAnswer(org.telscenter.sail.webapp.domain.brainstorm.Brainstorm, org.telscenter.sail.webapp.domain.brainstorm.answer.Answer)
	 */
	@Transactional()
	public void addAnswer(Brainstorm brainstorm, Answer answer) {
		brainstorm.addAnswer(answer);
		this.brainstormDao.save(brainstorm);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addComments(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer, org.telscenter.sail.webapp.domain.brainstorm.comment.Comment)
	 */
	public void addComments(Answer answer, Comment comment) {
		answer.addComment(comment);
		this.answerDao.save(answer);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addRevision(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer, org.telscenter.sail.webapp.domain.brainstorm.answer.Revision)
	 */
	public void addRevision(Answer answer, Revision revision) {
		answer.addRevision(revision);
		this.answerDao.save(answer);
	}

	/**
	 * @throws ObjectNotFoundException 
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#getBrainstormById(java.io.Serializable)
	 */
	public Brainstorm getBrainstormById(Serializable id) throws ObjectNotFoundException {
		return this.brainstormDao.getById(id);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#getBrainstormsByRun(org.telscenter.sail.webapp.domain.Run)
	 */
	public Set<Brainstorm> getBrainstormsByRun(Run run) {
		return null;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#markAsHelpful(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer, org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup)
	 */
	public void markAsHelpful(Answer answer, WISEWorkgroup workgroup) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#requestHelp(org.telscenter.sail.webapp.domain.brainstorm.Brainstorm, org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup)
	 */
	public void requestHelp(Brainstorm brainstorm, WISEWorkgroup workgroup) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#getBrainstormByAnswer(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer)
	 */
	public Brainstorm getBrainstormByAnswer(Answer answer) {
		return this.brainstormDao.retrieveByAnswer(answer);
	}
	
	/**
	 * @param Long the id of the answer to be retrieved
	 */
	public Answer getAnswer(Long id) throws Exception{
		return this.answerDao.getById(id);
	}

	/**
	 * @param brainstormDao the brainstormDao to set
	 */
	public void setBrainstormDao(BrainstormDao<Brainstorm> brainstormDao) {
		this.brainstormDao = brainstormDao;
	}

	/**
	 * @param answerDao the answerDao to set
	 */
	public void setAnswerDao(AnswerDao<Answer> answerDao) {
		this.answerDao = answerDao;
	}

}
