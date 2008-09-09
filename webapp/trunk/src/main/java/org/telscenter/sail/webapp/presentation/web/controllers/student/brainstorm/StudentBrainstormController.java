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
package org.telscenter.sail.webapp.presentation.web.controllers.student.brainstorm;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.AnswerImpl;
import org.telscenter.sail.webapp.domain.brainstorm.impl.BrainstormImpl;
import org.telscenter.sail.webapp.domain.brainstorm.question.Question;
import org.telscenter.sail.webapp.domain.brainstorm.question.impl.JaxbQuestionImpl;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;

/**
 * Displays Brainstorm step for students
 * @author Hiroki Terashima
 * @version $Id$
 */
public class StudentBrainstormController extends AbstractController {

	private static final String BRAINSTORM_KEY = "brainstorm";

	@SuppressWarnings("unused")
	private BrainstormService brainstormService;
	
	private static final String QUESTIONBODY = 
		"<assessmentItem xmlns=\"http://www.imsglobal.org/xsd/imsqti_v2p0\" xmlns:ns2=\"http://www.w3.org/1999/xlink\" xmlns:ns3=\"http://www.w3.org/1998/Math/MathML\" timeDependent=\"false\" adaptive=\"false\">" +
        "<responseDeclaration identifier=\"TEXT_NOTE_ID\"/>" +
        "<itemBody>" +
        "<extendedTextInteraction hasInlineFeedback=\"false\" placeholderText=\"I'm just sayin\" responseIdentifier=\"TEXT_NOTE_ID\" expectedLines=\"6\">" +
            "<prompt>&lt;p&gt;Watch the following Video on Java and &lt;b&gt;post 2 thoughts that you have&lt;/b&gt; on the video.&lt;/p&gt;&lt;object width='425' height='344'&gt;&lt;param name='movie' value='http://www.youtube.com/v/SRLU1bJSLVg&amp;hl=en&amp;fs=1'&gt;&lt;/param&gt;&lt;param name='allowFullScreen' value='true'&gt;&lt;/param&gt;&lt;embed src='http://www.youtube.com/v/SRLU1bJSLVg&amp;hl=en&amp;fs=1' type='application/x-shockwave-flash' allowfullscreen='true' width='425' height='344'&gt;&lt;/embed&gt;&lt;/object&gt;</prompt>" +
        "</extendedTextInteraction>" +
        "</itemBody>" +
        "</assessmentItem>";
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Brainstorm brainstorm = brainstormService.getBrainstormById(1);
		//brainstormSerivce.getById()...
		// want to get brainstorm by id from database
		// for now, create a mock Brainstorm object
		Brainstorm brainstorm = new BrainstormImpl();
		Question question = new JaxbQuestionImpl();
		//String questionBody = "<p>Watch the following Video on Java and <b>post 2 thoughts that you have</b> on the video.</p><object width='425' height='344'><param name='movie' value='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1'></param><param name='allowFullScreen' value='true'></param><embed src='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1' type='application/x-shockwave-flash' allowfullscreen='true' width='425' height='344'></embed></object>";
			//"<iframe id='videos_list' name='videos_list' src='http://www.youtube.com/videos_list?user=honchikun' scrolling='auto' width='265' height='300' frameborder='0' marginheight='0' marginwidth='0'></iframe>";
		question.setBody(QUESTIONBODY);
		brainstorm.setQuestion(question);
		Set<Answer> answers = new TreeSet<Answer>();
		Answer answer1 = new AnswerImpl();
		answers.add(answer1);
		brainstorm.setAnswers(answers);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(BRAINSTORM_KEY, brainstorm);
		return modelAndView;
	}

	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}

}
