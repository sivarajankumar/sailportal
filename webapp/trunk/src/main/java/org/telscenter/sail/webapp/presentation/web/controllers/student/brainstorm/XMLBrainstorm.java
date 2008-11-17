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

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

import net.sf.sail.webapp.domain.User;

import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Revision;
import org.telscenter.sail.webapp.domain.brainstorm.comment.Comment;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public final class XMLBrainstorm {
	
	public static String getXMLAnswers(Set<Answer> answers){
		if(answers.size()>0){
			String XMLAnswers = "<answers>";
			for(Answer answer : answers){
				XMLAnswers = XMLAnswers + getXMLAnswer(answer);
			}
			XMLAnswers = XMLAnswers + "</answers>";
			return XMLAnswers;
		} else {
			return "";
		}
	}
	
	public static String getXMLAnswer(Answer answer){
		String XMLAnswer = "<answer><id>" + answer.getId() + "</id><anon>" + answer.isAnonymous() +
			"</anon><revisions>" + getXMLRevisions(answer.getRevisions()) + "</revisions><comments>" + 
			getXMLComments(answer.getComments()) + "</comments>" + getXMLWorkgroup(answer.getWorkgroup())
			+ "<helpfulworkgroups>";
			if(answer.getWorkgroupsThatFoundAnswerHelpful()!=null){
				XMLAnswer = XMLAnswer + getXMLHelpfulWorkgroups(answer.getWorkgroupsThatFoundAnswerHelpful());
			}
			XMLAnswer = XMLAnswer + "</helpfulworkgroups></answer>";
		return XMLAnswer;
	}
	
	public static String getXMLComments(Set<Comment> comments){
		String XMLComments = "";
		if(comments.size()>0){
			for(Comment comment : comments){
				XMLComments = XMLComments + getXMLComment(comment);
			}
		}
		return XMLComments;
	}
	
	public static String getXMLRevisions(Set<Revision> revisions){
		String XMLRevisions = "";
		if(revisions.size()>0){
			for(Revision revision : revisions){
				XMLRevisions = XMLRevisions + getXMLRevision(revision);
			}
		}
		return XMLRevisions;
	}
	
	public static String getXMLComment(Comment comment){
		Date timestamp = comment.getTimestamp();
		String formattedTime = 
			DateFormat.getTimeInstance(DateFormat.SHORT).format(timestamp);

		String formattedDate = 
			DateFormat.getDateInstance(DateFormat.MEDIUM).format(timestamp);
		
		String XMLComment = "<comment><id>" + comment.getId() + "</id><timestamp>" + 
		    formattedTime + " " + formattedDate + "</timestamp>" + getXMLWorkgroup(comment.getWorkgroup()) 
			+ "<body>" + comment.getBody() + "</body><anon>" + comment.isAnonymous() +
			"</anon></comment>";
		return XMLComment;
	}

	public static String getXMLRevision(Revision revision) {
		Date timestamp = revision.getTimestamp();
		String formattedTime = 
			DateFormat.getTimeInstance(DateFormat.SHORT).format(timestamp);

		String formattedDate = 
			DateFormat.getDateInstance(DateFormat.MEDIUM).format(timestamp);

		String XMLRevision = "<revision><id>" + revision.getId() + "</id><timestamp>" +
			formattedTime + " " + formattedDate + "</timestamp><body>" + revision.getBody() + "</body></revision>";
		return XMLRevision;
	}

	public static String getXMLWorkgroup(WISEWorkgroup workgroup) {
		String XMLWorkgroup = "<workgroup><id>" + workgroup.getId() + "</id>" + getXMLMembers(workgroup.getMembers())
			+ "</workgroup>";
		return XMLWorkgroup;
	}
	
	public static String getXMLHelpfulWorkgroups(Set<WISEWorkgroup> workgroups){
		String XMLHelpfulWorkgroups = "";
		if(workgroups.size()>0){
			for(WISEWorkgroup workgroup : workgroups){
				XMLHelpfulWorkgroups = XMLHelpfulWorkgroups + getXMLWorkgroup(workgroup);
			}
		}
		return XMLHelpfulWorkgroups;
	}

	public static String getXMLMembers(Set<User> members) {
		String XMLMembers = "<members>";
		for(User user : members){
			XMLMembers = XMLMembers + "<student><id>" + user.getId() + "</id><firstname>" +
				user.getSdsUser().getFirstName() + "</firstname><lastname>" +
				user.getSdsUser().getLastName() + "</lastname></student>";
		}
		XMLMembers = XMLMembers + "</members>";
		return XMLMembers;
	}
}
