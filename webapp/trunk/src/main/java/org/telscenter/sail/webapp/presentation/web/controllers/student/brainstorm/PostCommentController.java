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

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Revision;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.AnswerImpl;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.RevisionImpl;
import org.telscenter.sail.webapp.domain.brainstorm.comment.Comment;
import org.telscenter.sail.webapp.domain.brainstorm.comment.impl.CommentImpl;
import org.telscenter.sail.webapp.domain.brainstorm.impl.BrainstormImpl;
import org.telscenter.sail.webapp.domain.brainstorm.question.Question;
import org.telscenter.sail.webapp.domain.brainstorm.question.impl.JaxbQuestionImpl;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.domain.workgroup.impl.WISEWorkgroupImpl;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class PostCommentController extends AbstractController{

	private final static String WORKGROUPID = "workgroupId";
	
	private final static String ANSWERID = "answerId";
	
	private final static String TEXT = "text";
	
	private final static String OPTION = "option";
	
	private final static String XMLDOC = "xmlDoc";
	
	private BrainstormService brainstormService;
	
	private WISEWorkgroupService workgroupService;
	
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
		
		//WISEWorkgroup workgroup = (WISEWorkgroup) this.workgroupService.retrieveById(Long.parseLong(request.getParameter(WORKGROUPID)));
		//Answer answer = this.brainstormService.getAnswer(Long.parseLong(request.getParameter(ANSWERID)));

		/*
		//following is mock brainstorm
		WISEWorkgroup workgroup = new WISEWorkgroupImpl();
		Brainstorm brainstorm = new BrainstormImpl();
		Question question = new JaxbQuestionImpl();
		//String questionBody = "<p>Watch the following Video on Java and <b>post 2 thoughts that you have</b> on the video.</p><object width='425' height='344'><param name='movie' value='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1'></param><param name='allowFullScreen' value='true'></param><embed src='http://www.youtube.com/v/SRLU1bJSLVg&hl=en&fs=1' type='application/x-shockwave-flash' allowfullscreen='true' width='425' height='344'></embed></object>";
			//"<iframe id='videos_list' name='videos_list' src='http://www.youtube.com/videos_list?user=honchikun' scrolling='auto' width='265' height='300' frameborder='0' marginheight='0' marginwidth='0'></iframe>";
		question.setBody(QUESTIONBODY);
		brainstorm.setQuestion(question);
		Set<Answer> answers = new TreeSet<Answer>();
		Answer answer1 = new AnswerImpl();
		Revision revision1 = new RevisionImpl();
		Calendar revision1Cal = Calendar.getInstance();
		revision1Cal.set(2008, 9, 10, 10, 30);
		revision1.setTimestamp(revision1Cal.getTime());
		revision1.setBody("I thought the movie was touching and educational. Nice intro to Java!");
		answer1.addRevision(revision1);
		Comment comment1 = new CommentImpl();
		comment1.setAnonymous(true);
		Calendar comment1Cal = Calendar.getInstance();
		comment1Cal.set(2008, 9, 10, 10, 50);
		comment1.setTimestamp(comment1Cal.getTime());
		WISEWorkgroupImpl workgroup1 = new WISEWorkgroupImpl();
		comment1.setWorkgroup(workgroup1);
		comment1.setBody("yah, I thought so too! let's watch it together again");
		answer1.addComment(comment1);
		
		Comment comment2 = new CommentImpl();
		comment2.setAnonymous(false);
		Calendar comment2Cal = Calendar.getInstance();
		comment2Cal.set(2008, 9, 10, 10, 56);
		comment2.setTimestamp(comment2Cal.getTime());
		WISEWorkgroupImpl workgroup2 = new WISEWorkgroupImpl();
		comment2.setWorkgroup(workgroup2);
		comment2.setBody("I disagree. The movie was a waste of time.");
		
		answer1.addComment(comment2);
		//answer1.setId(new Long(3));
		
		answers.add(answer1);
		brainstorm.setAnswers(answers);
		//remove to this point
		
		Comment comment = new CommentImpl();
		comment.setTimestamp(new Date());
		comment.setBody(request.getParameter(TEXT));
		if(Integer.parseInt(request.getParameter(OPTION)) == 0){
			comment.setAnonymous(true);
		}
		//comment.setId(new Long(77));
		comment.setWorkgroup(workgroup);
		answer1.addComment(comment);
		*/
		
		Answer answer = this.brainstormService.getAnswer(Long.parseLong(request.getParameter(ANSWERID)));
		WISEWorkgroup workgroup = (WISEWorkgroup) this.workgroupService.retrieveById(Long.parseLong(request.getParameter(WORKGROUPID)));

		Comment comment = new CommentImpl();
		comment.setBody(request.getParameter(TEXT));
		comment.setTimestamp(Calendar.getInstance().getTime());
		comment.setWorkgroup(workgroup);
		if(Integer.parseInt(request.getParameter(OPTION)) == 0){		
			comment.setAnonymous(true);
		}
		this.brainstormService.addComments(answer, comment);
		
		String xmlDoc = XMLBrainstorm.getXMLAnswer(answer);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(XMLDOC, xmlDoc);
		return modelAndView;
	}

	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}
