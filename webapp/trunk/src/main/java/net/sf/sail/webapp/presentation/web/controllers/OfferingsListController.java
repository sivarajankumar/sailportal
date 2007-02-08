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
package net.sf.sail.webapp.presentation.web.controllers;

// TODO create a logout button for this page
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.service.offerings.OfferingsService;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class OfferingsListController extends AbstractController {

	private String viewname;

	private OfferingsService offeringsService;

	/**
	 * @param viewname
	 *            the viewname to set
	 */
	public void setViewname(String viewname) {
		this.viewname = viewname;
	}

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws Exception {
		ModelMap model = new ModelMap();
		model.put("offeringslist", this.offeringsService.getOfferingsList(this.getApplicationContext()));
		return new ModelAndView(this.viewname, model);
	}

	/**
	 * @param offeringsService
	 *            the offeringsService to set
	 */
	public void setOfferingsService(OfferingsService offeringsService) {
		this.offeringsService = offeringsService;
	}
}