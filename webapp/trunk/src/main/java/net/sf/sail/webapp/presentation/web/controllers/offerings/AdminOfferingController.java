/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package net.sf.sail.webapp.presentation.web.controllers.offerings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 * 
 * 
 */
public class AdminOfferingController extends AbstractController {

	private OfferingService offeringService;

	protected final static String OFFERING_ID_KEY = "offering_id";

	protected final static String OFFERING_KEY = "offering";

	private static final String VIEW_NAME = "admin/adminoffering";

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws Exception {

		ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
		ControllerUtil.addUserToModelAndView(servletRequest, modelAndView);

		Long offeringId = Long.valueOf(servletRequest
				.getParameter(OFFERING_ID_KEY));
		Offering offering;
		try {
			offering = this.offeringService.getOffering(offeringId);
			modelAndView.addObject(OFFERING_KEY, offering);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			//TODO LAW do something.
//			errors.rejectValue("projectcode", "error.illegal-projectcode");
//			return showForm(servletRequest, servletResponse, errors);
		}
		return modelAndView;
	}

	/**
	 * @param offeringService
	 *            the offeringService to set
	 */
	@Required
	public void setOfferingService(OfferingService offeringService) {
		this.offeringService = offeringService;
	}
}