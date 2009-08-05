
package org.telscenter.sail.webapp.presentation.web.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.group.GroupService;
import net.sf.sail.webapp.service.offering.OfferingService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.student.StudentService;

public class SmartRoomController  extends AbstractController {
	
//	private UserService userService;
//
	private static final String USERID = "userId";
	private static final String RUNID = "runId";
	private static final String OFFERINGID = "offeringId";
	
	private WorkgroupService workgroupService;
	private StudentService studentService;
	private UserService userService;
	private RunService runService;
	private OfferingService offeringService;
	private GroupService groupService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();

		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("getUserInfo")) {
				// if getUserInfo is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getUserInfo&userId=3
				return handleGetUserInfo(request, response);
			}if (action.equals("getRunsList")) {
				// if getRunList is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getRunsList&userId=3
				return handleGetRunsList(request, response);
			}if (action.equals("getOwnersList")) {
				// if getOwnersList is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getOwnersList&runId=1
				return handleGetOwnersList(request, response);
			}if (action.equals("getRunInfo")) {
				// if getRunInfo is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getRunInfo&runId=1
				return handleGetRunInfo(request, response);
				///***************************************************************************************
			}if (action.equals("getGroups")) {
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getGroups&userId=3
				return handleGetGroups(request, response);
			}if (action.equals("getRuns")) {
				// if getRuns is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getRuns&offeringId=1
				return handleGetRunsByOfferingId(request, response);
			}if (action.equals("getOfferings")) {
				// if getOfferings is requested, return xmlString instead in the response
				// for see the result http://localhost:8080/webapp/smartroom/smartroom.html?action=getOfferings
				return handleGetOfferings(request, response);
			} else {
				// shouldn't get here
				throw new RuntimeException("should not get here");
			}
		} else {
			// need action
			throw new RuntimeException("need action parameter");
		}

	}
   /**
	 * Returns the USER information of the specific USER_ID as an XML string 
	 * @param request
	 * @param response
	 * @return
	 * @throws ObjectNotFoundException
	 * @throws IOException
	 */
	private ModelAndView handleGetUserInfo(HttpServletRequest request,
			HttpServletResponse response) throws ObjectNotFoundException, IOException {

		Long userId = Long.parseLong(request.getParameter(USERID));
		
		User user = this.userService.retrieveById(userId);
		
		String userInfoXML = makeUserInfoXML(user);
		setResponse(response, userInfoXML);
		return null;
	}

	/**
	 * Returns the RUNs information of the specific USER as an XML string 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException \\\
	 * @throws ObjectNotFoundException
	 */
	private ModelAndView handleGetRunsList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		Long userId = Long.parseLong(request.getParameter(USERID));
		User user = this.userService.retrieveById(userId);
		List<Run> runsList = this.runService.getRunList(user);
		
 		String runsInfoXML = "<runs>";
		for(Iterator<Run> iter = runsList.iterator(); iter.hasNext();){
			runsInfoXML += makeRunInfoXML(iter.next());
		}
		runsInfoXML += "</runs>";
		setResponse(response, runsInfoXML);
		return null;
	}
	/**
	 * Returns the OWNERs information of the specific RUN as an XML String 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ObjectNotFoundException
	 */
	private ModelAndView handleGetOwnersList(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		Long runId = Long.parseLong(request.getParameter(RUNID));
		Run run = this.runService.retrieveById(runId);
		
		Set<User> ownersSet = run.getOwners();
		
 		String ownersInfoXML = "<owners>";
		for(Iterator<User> iter = ownersSet.iterator(); iter.hasNext();){
			
			ownersInfoXML += makeUserInfoXML(iter.next());
		}
		ownersInfoXML += "</owners>";
		
		setResponse(response, ownersInfoXML);
		return null;
	}
	/**
	 * Returns the RUN information of the specific RUN_ID as an XML String 
	 * is paused or messages that teacher wants to send to students.
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ObjectNotFoundException 
	 */
	private ModelAndView handleGetRunInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		Long runId = Long.parseLong(request.getParameter(RUNID));
		Run run = this.runService.retrieveById(runId);
		
    	String runInfoXML = makeRunInfoXML(run);
		setResponse(response, runInfoXML);
		return null;
	}
	/**
	 * Returns the RUNs ID of the specific OFFERING ID as an XML String 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ObjectNotFoundException
	 */
	private ModelAndView handleGetGroups(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		//Long userId = Long.parseLong(request.getParameter(USERID));
		List<Group> groupList = this.groupService.getGroups();
		
 		String groupXML = "<groups>";
		for(Iterator<Group> iter = groupList.iterator(); iter.hasNext();){
			
			Group group = iter.next();
			groupXML += "<name>" + group.getName() + "</name>";
			groupXML += "<id>" + group.getId() + "</id>";
		}
		groupXML += "</groups>";
		
		setResponse(response, groupXML);
		return null;
	}
	/**
	 * Returns the RUNs ID of the specific OFFERING ID as an XML String 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ObjectNotFoundException
	 */
	private ModelAndView handleGetRunsByOfferingId(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		Long offeringId = Long.parseLong(request.getParameter(OFFERINGID));
		Set<Workgroup> workgroupSet = this.offeringService.getWorkgroupsForOffering(offeringId);
		
 		String workgroupXML = "<workgrpups>";
		for(Iterator<Workgroup> iter = workgroupSet.iterator(); iter.hasNext();){
			
			Workgroup workgroup = iter.next();
			workgroupXML += "<id>" + workgroup.getId() + "</id>";
//			workgroupXML += workgroup.
		}
		workgroupXML += "</workgrpups>";
		
		setResponse(response, workgroupXML);
		return null;
	}	/**
	 * Returns the RUN information of the specific RUN_ID as an XML String 
	 * is paused or messages that teacher wants to send to students.
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ObjectNotFoundException 
	 */
	private ModelAndView handleGetOfferings(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ObjectNotFoundException {

		List<Offering> offeringList = this.offeringService.getOfferingList();
		String offeringXML = "<offerings>";
		for(Iterator<Offering> iter = offeringList.iterator(); iter.hasNext();){
			offeringXML += makeOfferingXML(iter.next());
		}
		offeringXML += "</offerings>";
		setResponse(response, offeringXML);
		return null;
	}
	/**
	 * Returns the RunInfoString XML containing some information about run
	 * @param run
	 * @return
	 */
	private String makeRunInfoXML(Run run){

		String runInfoXML = "<runInfo>";
		runInfoXML += "<name>" + run.getName() + "</name>";
		runInfoXML += "<id>" + run.getId()+ "</id>";
		runInfoXML += "<startTime>" + run.getStarttime().toString() + "</startTime>";
		if (run.isEnded())
			runInfoXML += "<endTime>" + run.getEndtime().toString() + "</endTime>";
		else
			runInfoXML += "<endTime>" + "null" + "</endTime>";
		runInfoXML += "</runInfo>";
		return runInfoXML;
	}
	
	/**
	 * Returns the RunInfoString XML containing some information about user
	 * @param run
	 * @return
	 */
	private String makeUserInfoXML(User user){
	
		String userInfoXML = "<userInfo>";
		userInfoXML += "<id>" + user.getUserDetails().getId().toString() + "</id>";
		userInfoXML += "<name>" + user.getUserDetails().getUsername().toString() + "</name>";
		userInfoXML += "<email>" + user.getUserDetails().getEmailAddress() + "</email>";
		userInfoXML += "</userInfo>";
		return userInfoXML;
	}	/**
	 * Returns the RunInfoString XML containing some information about user
	 * @param run
	 * @return
	 */
	private String makeOfferingXML(Offering offering){
	
		String offeringXML = "<offering>";
		offeringXML += "<id>";
		offeringXML += offering.getId().toString();
		offeringXML += "</id>";
		offeringXML += "</offering>";
		return offeringXML;
	}
//	public void setWorkgroupService(WorkgroupService workgroupService) {
//		this.workgroupService = workgroupService;
//	}
//
//	public void setStudentService(StudentService studentService) {
//		this.studentService = studentService;
//	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setOfferingService(OfferingService offeringService) {
		this.offeringService = offeringService;
	}

	public void setGroupService(GroupService groupService){
		this.groupService = groupService;
	}
	private void setResponse(HttpServletResponse response, String strXML) throws IOException{
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("text/xml");
		response.getWriter().print(strXML);

	}
}
