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
package org.telscenter.sail.webapp.domain.admin;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.mail.IMailFacade;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.telscenter.sail.webapp.dao.offering.RunDao;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author hirokiterashima
 * @version $Id:$
 */
public class AdminJob extends QuartzJobBean {

	private IMailFacade javaMail = null;

	private Properties emaillisteners = null;
	
	private Properties portalProperties = null;
	
	private RunDao<Run> runDao;
	
	private UserDao<User> userDao;
	
	private Date yesterday = null;
	
	{
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		yesterday = new java.sql.Date(today.getTimeInMillis());
	}

	/**
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		// do the actual work
		String messageBody = "";
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		List<Run> runsCreatedSinceYesterday = findRunsCreatedSinceYesterday();
		messageBody += "Number of Runs started since " 
			+ df.format(yesterday) + ": "
			+ runsCreatedSinceYesterday.size();
		
		List<User> teachersJoinedSinceYesterday = findUsersJoinedSinceYesterday("teacherUserDetails");
		messageBody += "\n\n";
		messageBody += "Number of Teachers joined since " 
			+ df.format(yesterday) + ": "
			+ teachersJoinedSinceYesterday.size();

		List<User> studentsJoinedSinceYesterday = findUsersJoinedSinceYesterday("studentUserDetails");
		messageBody += "\n\n";
		messageBody += "Number of Students joined since " 
			+ df.format(yesterday) + ": "
			+ studentsJoinedSinceYesterday.size();

		sendEmail(messageBody);
	}


	private List<User> findUsersJoinedSinceYesterday(String who) {
		String field = "signupdate";
		String type = ">";
		Object term = yesterday;
		String classVar = who;

		List<User> usersJoinedSinceYesterday =
			userDao.retrieveByField(field, type, term, classVar);
		
		return usersJoinedSinceYesterday;
	}


	/**
	 * Finds number of runs that were created since yesterday
	 */
	private List<Run> findRunsCreatedSinceYesterday() {
		String field = "starttime";
		String type = ">";
		Object term = yesterday;
		List<Run> runsStartedSinceYesterday = 
			runDao.retrieveByField(field, type, term);

		return runsStartedSinceYesterday;
	}

	
	public void sendEmail(String message) {
		String[] recipients = {emaillisteners.getProperty("uber_admin")};
		
		String subject = "Admin Job complete on Portal: "
		    + " (" + portalProperties.getProperty("portal.name") + ")";		

		String msg = message;
		
		String fromEmail = "wise_gateway@berkeley.edu";
		
		//sends the email to the recipients
		try {
			javaMail.postMail(recipients, subject, msg, fromEmail);
		} catch (MessagingException e) {
		}
	}
	
	
	/**
	 * @param javaMail the javaMail to set
	 */
	public void setJavaMail(IMailFacade javaMail) {
		this.javaMail = javaMail;
	}

	/**
	 * @param emaillisteners the emaillisteners to set
	 */
	public void setEmaillisteners(Properties emaillisteners) {
		this.emaillisteners = emaillisteners;
	}


	/**
	 * @param portalProperties the portalProperties to set
	 */
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}

	/**
	 * @param runDao the runDao to set
	 */
	public void setRunDao(RunDao<Run> runDao) {
		this.runDao = runDao;
	}


	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao<User> userDao) {
		this.userDao = userDao;
	}
}
