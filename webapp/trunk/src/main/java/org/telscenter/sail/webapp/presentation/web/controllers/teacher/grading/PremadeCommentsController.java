/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.impl.PremadeCommentListParameters;
import org.telscenter.sail.webapp.domain.impl.PremadeCommentParameters;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeComment;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeCommentList;
import org.telscenter.sail.webapp.domain.premadecomment.impl.PremadeCommentImpl;
import org.telscenter.sail.webapp.domain.premadecomment.impl.PremadeCommentListImpl;
import org.telscenter.sail.webapp.service.premadecomment.PremadeCommentService;

import sun.security.krb5.internal.crypto.e;

/**
 * @author Geoff
 *
 */
public class PremadeCommentsController extends AbstractController {
	
	private PremadeCommentService premadeCommentService;
	
	private static final String PREMADE_COMMENTS_LISTS = "premadeCommentLists";
	private static final String COMMENT_BOX = "commentBox";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//retrieves the current user
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);
		
		/* retreives the document javascript id of the comment box in the 
		   grading tool page */
		String commentBox = request.getParameter("commentBox");
		
		//retrieves all the comment lists created by the current user
		Set<PremadeCommentList> allCommentLists = 
			premadeCommentService.retrieveAllPremadeCommentListsByUser(user);

		modelAndView.addObject(PREMADE_COMMENTS_LISTS, allCommentLists);
		modelAndView.addObject(COMMENT_BOX, commentBox);
		
		return modelAndView;
	}

	/**
	 * @return the premadeCommentService
	 */
	public PremadeCommentService getPremadeCommentService() {
		return premadeCommentService;
	}

	/**
	 * @param premadeCommentService the premadeCommentService to set
	 */
	public void setPremadeCommentService(PremadeCommentService premadeCommentService) {
		this.premadeCommentService = premadeCommentService;
	}

}
