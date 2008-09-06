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
import java.util.TreeSet;

import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Revision;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.AnswerImpl;
import org.telscenter.sail.webapp.domain.brainstorm.comment.Comment;
import org.telscenter.sail.webapp.domain.brainstorm.impl.BrainstormImpl;
import org.telscenter.sail.webapp.domain.brainstorm.question.Question;
import org.telscenter.sail.webapp.domain.brainstorm.question.impl.QuestionImpl;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class BrainstormServiceImpl implements BrainstormService {

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addAnswer(org.telscenter.sail.webapp.domain.brainstorm.Brainstorm, org.telscenter.sail.webapp.domain.brainstorm.answer.Answer)
	 */
	public void addAnswer(Brainstorm brainstorm, Answer answer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addComments(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer, org.telscenter.sail.webapp.domain.brainstorm.comment.Comment)
	 */
	public void addComments(Answer answer, Comment comment) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#addRevision(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer, org.telscenter.sail.webapp.domain.brainstorm.answer.Revision)
	 */
	public void addRevision(Answer answer, Revision revision) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#getBrainstormById(java.io.Serializable)
	 */
	public Brainstorm getBrainstormById(Serializable id) {
		// change later with actual implementation
		Brainstorm brainstorm = new BrainstormImpl();
		Question question = new QuestionImpl();
		String questionBody = "<p>Watch the following Video on Java and <b>post 2 thoughts that you have</b> on the video.</p><object width='425' height='344'><param name='movie' value='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1'></param><param name='allowFullScreen' value='true'></param><embed src='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1' type='application/x-shockwave-flash' allowfullscreen='true' width='425' height='344'></embed></object>";
			//"<iframe id='videos_list' name='videos_list' src='http://www.youtube.com/videos_list?user=honchikun' scrolling='auto' width='265' height='300' frameborder='0' marginheight='0' marginwidth='0'></iframe>";
		question.setBody(questionBody);
		brainstorm.setQuestion(question);
		Set<Answer> answers = new TreeSet<Answer>();
		Answer answer1 = new AnswerImpl();
		answers.add(answer1);
		brainstorm.setAnswers(answers);
		return brainstorm;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.brainstorm.BrainstormService#getBrainstormsByRun(org.telscenter.sail.webapp.domain.Run)
	 */
	public Set<Brainstorm> getBrainstormsByRun(Run run) {
		// TODO Auto-generated method stub
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

}
