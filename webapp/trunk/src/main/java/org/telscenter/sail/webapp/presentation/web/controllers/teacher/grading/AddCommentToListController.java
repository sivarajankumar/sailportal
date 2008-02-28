/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeComment;
import org.telscenter.sail.webapp.domain.premadecomment.impl.PremadeCommentImpl;
import org.telscenter.sail.webapp.service.premadecomment.PremadeCommentService;

/**
 * @author Patrick
 *
 */
public class AddCommentToListController extends AbstractController {

	private PremadeCommentService premadeCommentService;
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String listId = request.getParameter("listNumber");
		String comment = request.getParameter("comment");
		
		//System.out.println("listNumber: " + listId + ", comment: " + comment);
		
		PremadeComment premadeComment = new PremadeCommentImpl();
		premadeComment.setComment(comment);
		premadeComment.setLabel(comment);
		
		premadeCommentService.addPremadeCommentToList(new Long(listId), premadeComment);
		
		System.out.println(premadeComment.getId());
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("commentId", premadeComment.getId());
		
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
