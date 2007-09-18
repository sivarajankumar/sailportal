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
package org.telscenter.sail.webapp.service.grading.impl;

import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;
import net.sf.sail.webapp.domain.sessionbundle.impl.SessionBundleImpl;

import org.telscenter.sail.webapp.service.grading.SessionBundleService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class SessionBundleServiceImpl implements SessionBundleService {

	/**
	 * @see org.telscenter.sail.webapp.service.grading.SessionBundleService#getSessionBundle(java.lang.Long, net.sf.sail.webapp.domain.Workgroup)
	 */
	public SessionBundle getSessionBundle(Long runId, Workgroup workgroup) {
		// TODO REPLACE BELOW STUB IMPLEMENTATION WITH ACTUAL CODE WHEN the code is ready
		String portfolioXMLString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<sailuserdata:EPortfolio xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:sailuserdata=\"sailuserdata\">" +
		"<sessionBundles xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:sailuserdata=\"sailuserdata\" start=\"2007-05-23T17:09:32.320-0700\" stop=\"2007-05-23T17:10:30.221-0700\" curnitUUID=\"af133621-b3d1-11db-a373-8b155032f53b\" sessionUUID=\"19c4d720-fdde-4254-8c83-480ba66b161e\">" +
		"<sockParts podId=\"dddddddd-6004-0002-0000-000000000000\" rimName=\"undefined6\">" +
		"<sockEntries value=\"green house asourb sun light and global warming is the earth warming up but it is alike...actually I have no idea what I'm talking about. this is my answer for rim with rimname undefined6.\" millisecondsOffset=\"1277103\"/>" +
		"</sockParts>" + 
		"<sockParts podId=\"dddddddd-6004-0003-0000-000000000000\" rimName=\"undefined7\">" +
		"<sockEntries value=\"this is a response to rim with name undefined7 and I have no idea what I should write here.\" millisecondsOffset=\"1277103\"/>" +
		"</sockParts>" +
		"<agents role=\"RUN_WORKGROUP\">" +
		"<users>47fe0b74-08ae-11dc-add0-0014c2c34555</users>" +
		"</agents>" +
		"<sdsReturnAddresses>http://saildataservice.concord.org/3/offering/1200/bundle/1215/0</sdsReturnAddresses>" +
		"<launchProperties key=\"maven.jnlp.version\" value=\"plr-everything-jdic-snapshot-0.1.0-20070523.165636\"/>" +
		"</sessionBundles></sailuserdata:EPortfolio>";
		
		SessionBundle sessionBundle = new SessionBundleImpl();
		sessionBundle.setBundleString(portfolioXMLString);
		sessionBundle.setWorkgroup(workgroup);
		return sessionBundle;
	}

}
