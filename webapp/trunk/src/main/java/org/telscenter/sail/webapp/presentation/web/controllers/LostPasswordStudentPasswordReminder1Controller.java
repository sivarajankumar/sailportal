/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
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

package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;

/**
 * Step 1 of the student password reminder
 * 
 * @author Anthony Perritano
 * @version $Id: $
 */
public class LostPasswordStudentPasswordReminder1Controller extends
        SimpleFormController {
    private static final String USERNAME = "username";

    private static final String ACCOUNTQUESTION = "accountquestion";

    protected UserService userService = null;

    /**
     * submits the page
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        StudentUserDetails userDetails = (StudentUserDetails) command;

        String username = null;
        try {

            username = StringUtils.trimToNull(userDetails.getUsername());
            if (username != null) {

                User user = userService.retrieveUserByUsername(userDetails
                        .getUsername());

                String accountQuestion = ((StudentUserDetails) user
                        .getUserDetails()).getAccountQuestion();

                Map<String, String> model = new HashMap<String, String>();
                model.put(USERNAME, username);
                model.put(ACCOUNTQUESTION, accountQuestion);

                ModelAndView modelAndView = new ModelAndView(getSuccessView(),
                        model);
                return modelAndView;

            }// if
        } catch (EmptyResultDataAccessException e) {
            errors.rejectValue(USERNAME, "error.username-not-found",
                    new Object[] { userDetails.getUsername() },
                    "Username not found");
            return showForm(request, response, errors);

        } catch (Exception e) {
            e.printStackTrace();
            return showForm(request, response, errors);
        }// try

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    /**
     * Sets the userDetailsService object.
     * 
     * @param userDetailsService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
