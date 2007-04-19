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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.service.offering.OfferingService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class OfferingListController extends AbstractController {

    private OfferingService offeringService;
    
    public final static String OFFERINGS_ITERATOR = "offering_iterator";

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(OFFERINGS_ITERATOR, this.offeringService.getOfferingIterator());
        return modelAndView;
    }

    /**
     * @param offeringService
     *            the offeringService to set
     */
    public void setOfferingService(OfferingService offeringService) {
        this.offeringService = offeringService;
    }
}