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
package org.telscenter.sail.webapp.presentation.web.controllers.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;

/**
 * @author hirokiterashima
 * @version $Id:$
 */
public class BasicExampleController extends AbstractController {

	private RunService runService;
	
	private StudentService studentService;
	
	private WorkgroupService workgroupService;
	
	private BrainstormService brainstormService;

	private HttpRestTransport httpRestTransport;
	
	protected final static String CURRENT_STUDENTRUNINFO_LIST_KEY = "current_run_list";

	protected final static String ENDED_STUDENTRUNINFO_LIST_KEY = "ended_run_list";

	protected final static String HTTP_TRANSPORT_KEY = "http_transport";

	protected final static String WORKGROUP_MAP_KEY = "workgroup_map";
	
	private static final String VIEW_NAME = "student/vle/basic_example_otml";
	
	private final static String CURRENT_DATE = "current_date";

	static final String DEFAULT_PREVIEW_WORKGROUP_NAME = "Your test workgroup";

	private static final String RUNID = "runId";
	
	private static final String RUN_STATUS_ID = "runSettingsId";

	private static final String SHOW_NEW_ANNOUNCEMENTS = "showNewAnnouncements";

	private String xmlString = "<node><node></node><node></node></node>";
	//private String xmlString = "<node id='0'><node id='0:0'><node id='0:0:0' type='reading'><content><html> <head> <base href='http://tels-group.soe.berkeley.edu/uccp/Assets/' /> <link href='css/UCCP.css' media='screen' rel='stylesheet' type='text/css' /> </head> <body> <div id='centeredDiv'> <div id='locationBar'> <div class='Unit1'>UNIT 1</div> <div class='Reading'></div> </div> <div id='mainCol'> <h3>Sample Reading Page</h3> <p>Reading pages are used to:</p> <ul> <li>Introduce you to new concepts. If a concept is particularly important, we'll highlight it in a 'key concept' box over to the right.</li><li>Explain a new wrinkle, or detail, to a concept that you've been working with for a while.</li> <li>Define a new vocabulary word. New terms, like <span class='vocab'>recursion</span>, will be highlighted and will appear in a 'Vocabulary' box to the right. Click any vocabulary term in the box to see its matching definition in the Course Glossary.</li> </ul> <p>We'll keep these reading pages short as possible. But keep in mind that they generally contain important information, so don't skip them just to jump ahead to the assignments.</p> </div> <div id='marginCol'> <div class='key top50'>Key points appear out here in the right margin, highlighted with a special Key icon. </div> <div class='vocab top75'> <p><i>Click the vocabulary term below to see its definition.</i></p> <p><a href='page/glossary.php' target=_glossary>Recursion</a></p> </div> </div> </div> <!-- end of #centered div--> </body> </html></content></node><node id='0:0:1' type='sequence'><node id='0:0:1:0' /><node id='0:0:1:1' /></node><node id='0:0:2' /></node><node id='0:1'><node id='0:1:0' type='video'><content><html lang='en'> <head> <base href='http://tels-group.soe.berkeley.edu/uccp/Assets/' /> <link href='css/UCCP.css' media='screen' rel='stylesheet' type='text/css' /> <meta http-equiv='Content-Type' content='text/html; charset=utf-8' /> </head><body> <div id='centeredDiv'> <div id='locationBar'> <div class='Unit1'>UNIT 1</div> <div class='Video'></div> </div> <h3>An Introduction</h3> <p>This is a video screen!!!. Click the 'play' button below (lower right corner) to start the video. To see a text transcript click 'Transcript' and scroll down.</p> <iframe id='videoAndControls' height='640' src='video/inc_introduction.html' >Your browser doesn't support iFrames. In order to watch videos, you'll need to find a browser that does, or go directly to <a href='video/inc_introduction.html' >the video page</a>.</iframe></div><!-- end of #centered div--></body></html></content></node></node></node> ";
	/** 
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	ModelAndView modelAndView = null;
		
    	String runManagerStr = request.getParameter("getRunManager");
    	if (runManagerStr != null && runManagerStr.equals("true")) {
    		modelAndView = new ModelAndView("student/vleConnection");
        	String runIdStr = request.getParameter(RUNID);
        	Run run = runService.retrieveById(Long.valueOf(runIdStr));
        	String xmlDoc = "";

        	if (run.isPaused()) {
        		xmlDoc = "<isPaused>true</isPaused>";
        	} else {
        		xmlDoc = "<isPaused>false</isPaused>";
        	}
    		modelAndView.addObject("xmlDoc", xmlDoc);
    	} else {
    		modelAndView = new ModelAndView(VIEW_NAME);
        	ControllerUtil.addUserToModelAndView(request, modelAndView);
        	User user = (User) request.getSession().getAttribute(
    				User.CURRENT_USER_SESSION_KEY);
        	String runIdStr = request.getParameter(RUNID);
        	modelAndView.addObject(RUNID, runIdStr);
        	modelAndView.addObject("xmlString", xmlString);
    	}
    	
        return modelAndView;
	}

	/**
	 * @param runService the runService to set
	 */
	@Required
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	@Required
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @param httpRestTransport
	 *            the httpRestTransport to set
	 */
	@Required
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	@Required
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}

}