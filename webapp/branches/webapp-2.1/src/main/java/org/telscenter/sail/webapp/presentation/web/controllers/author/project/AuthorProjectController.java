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
package org.telscenter.sail.webapp.presentation.web.controllers.author.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.presentation.web.listeners.PasSessionListener;
import net.sf.sail.webapp.service.curnit.CurnitService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.impl.CreateUrlModuleParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ProjectMetadataImpl;
import org.telscenter.sail.webapp.domain.project.impl.ProjectType;
import org.telscenter.sail.webapp.presentation.util.Util;
import org.telscenter.sail.webapp.presentation.util.json.JSONException;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Controller for users with author privileges to author projects
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class AuthorProjectController extends AbstractController {

	private static final String PROJECT_ID_PARAM_NAME = "projectId";
	
	private static final String COMMAND = "command";

	private ProjectService projectService;
	
	private Properties portalProperties = null;
	
	private HttpRestTransport httpRestTransport;
	
	private CurnitService curnitService;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String projectIdStr = request.getParameter(PROJECT_ID_PARAM_NAME);
		
		Project project;
		if(projectIdStr != null && projectIdStr != ""){
			project = projectService.getById(projectIdStr);
		} else {
			project = null;
		}
		
		AuthorProjectParameters params = new AuthorProjectParameters();
		params.setProject(project);
		params.setHttpServletRequest(request);
		params.setHttpServletResponse(response);
		params.setHttpRestTransport(httpRestTransport);
		params.setPortalUrl(Util.getPortalUrl(request));
		
		String command = request.getParameter(COMMAND);
		if(command != null && command != ""){
			if(command.equals("launchAuthoring")){
				return (ModelAndView) projectService.authorProject(params);
			} else if(command.equals("createProject")){
				return handleCreateProject(request, response);
			} else if(command.equals("projectList")){
				return handleProjectList(request, response);
			} else if (command.equals("notifyProjectOpen")) {
				return handleNotifyProjectOpen(request, response);
			} else if (command.equals("notifyProjectClose")){
				return handleNotifyProjectClose(request, response);
			} else if(command.equals("publishMetadata")){
				return this.handlePublishMetadata(request, response);
			} else if(command.equals("getUsername")){
				return this.handleGetUsername(request, response);
			}
		}
		
		return (ModelAndView) projectService.authorProject(params);
	}

	private ModelAndView handleCreateProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = request.getParameter("param1");
		String name = request.getParameter("param2");
		User user = (User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY);
		Set<User> owners = new HashSet<User>();
		owners.add(user);
		
		CreateUrlModuleParameters cParams = new CreateUrlModuleParameters();
		cParams.setUrl(path);
		Curnit curnit = curnitService.createCurnit(cParams);
		
		ProjectMetadata metadata = new ProjectMetadataImpl();
		ProjectParameters pParams = new ProjectParameters();
		
		metadata.setTitle(name);
		pParams.setCurnitId(curnit.getId());
		pParams.setOwners(owners);
		pParams.setProjectname(name);
		pParams.setMetadata(metadata);
		pParams.setProjectType(ProjectType.LD);
		
		Project project = projectService.createProject(pParams);
		response.getWriter().write(project.getId().toString());
		return null;
	}

	/**
	 * Handles notifications of opened projects
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView handleNotifyProjectOpen(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String projectPath = request.getParameter("param1");
		
		HttpSession currentUserSession = request.getSession();
		HashMap<String, ArrayList<String>> openedProjectsToSessions = 
			(HashMap<String, ArrayList<String>>) currentUserSession.getServletContext().getAttribute("openedProjectsToSessions");
		
		if (openedProjectsToSessions == null) {
			openedProjectsToSessions = new HashMap<String, ArrayList<String>>(); 
			currentUserSession.getServletContext().setAttribute("openedProjectsToSessions", openedProjectsToSessions);
		}
		
		if (openedProjectsToSessions.get(projectPath) == null) {
			openedProjectsToSessions.put(projectPath, new ArrayList<String>());
		}
		ArrayList<String> sessions = openedProjectsToSessions.get(projectPath);  // sessions that are currently authoring this project
		if (!sessions.contains(currentUserSession.getId())) {
			sessions.add(currentUserSession.getId());
		}
		 HashMap<String, User> allLoggedInUsers = (HashMap<String, User>) currentUserSession.getServletContext()
			.getAttribute(PasSessionListener.ALL_LOGGED_IN_USERS);
		
		String otherUsersAlsoEditingProject = "";
		for (String sessionId : sessions) {
			if (sessionId != currentUserSession.getId()) {
				User user = allLoggedInUsers.get(sessionId);
				if (user != null) {
					otherUsersAlsoEditingProject += user.getUserDetails().getUsername();
				}
			}
		}
		response.getWriter().write(otherUsersAlsoEditingProject);
		return null;
	}
	
	/**
	 * Handles notifications of closed projects
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView handleNotifyProjectClose(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String projectPath = request.getParameter("param1");
		HttpSession currentSession = request.getSession();
		
		Map<String, ArrayList<String>> openedProjectsToSessions = (Map<String, ArrayList<String>>) currentSession.getServletContext().getAttribute("openedProjectsToSessions");
		
		if(openedProjectsToSessions == null || openedProjectsToSessions.get(projectPath) == null){
			return null;
		} else {
			ArrayList<String> sessions = openedProjectsToSessions.get(projectPath);
			if(!sessions.contains(currentSession.getId())){
				return null;
			} else {
				sessions.remove(currentSession.getId());
				response.getWriter().write("success");
				return null;
			}
		}
	}
	
	private ModelAndView handleProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<Project> allAuthorableProjects = new ArrayList<Project>();
		List<Project> projects = projectService.getProjectList((User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY));
		List<Project> sharedProjects = projectService.getSharedProjectList((User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY));

		// in the future, we'll want to filter this allAuthorableProjects list even further by what kind of
		// permissions (view, edit, share) the user has on the project.
		allAuthorableProjects.addAll(projects);
		allAuthorableProjects.addAll(sharedProjects);
		
		String curriculumBaseDir = portalProperties.getProperty("curriculum_base_dir");
		String xmlList = "";
		for(Project project : allAuthorableProjects){
			if(project.getProjectType()==ProjectType.LD){
				String versionId = this.projectService.getActiveVersion(project);
				String rawProjectUrl = (String) project.getCurnit().accept(new CurnitGetCurnitUrlVisitor());
				String polishedProjectUrl = null;
				
				if(versionId == null || versionId.equals("")){
					polishedProjectUrl = rawProjectUrl;
				} else {
					polishedProjectUrl = rawProjectUrl.replace(".project.json", ".project." + versionId + ".json");
				}
				xmlList += curriculumBaseDir + polishedProjectUrl + "~" + project.getId() + "|";
			}
		}
		xmlList += "";
		
		response.getWriter().write(xmlList);
		return null;
	}
	
	/**
	 * Handles the publish metadata request from the authoring tool
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ObjectNotFoundException 
	 * @throws IOException 
	 */
	private ModelAndView handlePublishMetadata(HttpServletRequest request, HttpServletResponse response) throws ObjectNotFoundException, IOException{
		Long projectId = Long.parseLong(request.getParameter("projectId"));
		String versionId = request.getParameter("versionId");
		
		Project project = this.projectService.getById(projectId);
		
		/* retrieve the metadata from the file */
		JSONObject metadata = this.projectService.getProjectMetadataFile(project, versionId);
		
		/* set the fields in the ProjectMetadata where appropriate */
		if(metadata != null){
			ProjectMetadata pMeta = this.projectService.getMetadata(projectId, versionId);
			
			/* if no previous metadata exists for this project, then we want to create one
			 * and set it in the project */
			if(pMeta == null){
				pMeta = new ProjectMetadataImpl();
				pMeta.setVersionId(versionId);
				pMeta.setProjectId(projectId);
				project.setMetadata(pMeta);
			}
			
			Object title = this.getJSONFieldValue(metadata, "title");
			if(title != null){
				pMeta.setTitle((String) title);
			}
			
			Object author = this.getJSONFieldValue(metadata, "author");
			if(author != null){
				pMeta.setAuthor((String) author);
			}
			
			Object subject = this.getJSONFieldValue(metadata, "subject");
			if(subject != null){
				pMeta.setSubject((String) subject);
			}
			
			Object summary = this.getJSONFieldValue(metadata, "summary");
			if(summary != null){
				pMeta.setSummary((String) summary);
			}
			
			Object graderange = this.getJSONFieldValue(metadata, "graderange");
			if(graderange != null){
				pMeta.setGradeRange((String) graderange);
			}
			
			Object contact = this.getJSONFieldValue(metadata, "contact");
			if(contact != null){
				pMeta.setContact((String) contact);
			}
			
			Object techreqs = this.getJSONFieldValue(metadata, "techreqs");
			if(techreqs != null){
				pMeta.setTechReqs((String) techreqs);
			}
			
			Object lessonplan = this.getJSONFieldValue(metadata, "lessonplan");
			if(lessonplan != null){
				pMeta.setLessonPlan((String) lessonplan);
			}
			
			Object totaltime = this.getJSONFieldValue(metadata, "totaltime");
			if(totaltime != null && !((String) totaltime).equals("")){
				pMeta.setTotalTime(Long.parseLong((String) totaltime));
			} 
			
			Object comptime = this.getJSONFieldValue(metadata, "comptime");
			if(comptime != null && !((String) comptime).equals("")){
				pMeta.setCompTime(Long.parseLong((String) comptime));
			}
			
			Object keywords = this.getJSONFieldValue(metadata, "keywords");
			if(keywords != null){
				pMeta.setKeywords((String) keywords);
			}
			
			/* save the project metadata */
			this.projectService.updateMetadata(pMeta);
			
			/* write success message */
			response.getWriter().write("Project metadata was successfully published to the portal.");
		} else {
			/* write error message that portal could not access metadata file */
			response.getWriter().write("The portal was unable to access the data in the metadata file. The metadata may be out of sync.");
		}
		
		return null;
	}
	
	/**
	 * Returns the value of the given <code>String</code> field name in the given
	 * <code>JSONObject</code> if it exists, returns null otherwise. This function
	 * is provided as a means to catch the JSON error that is associated with retrieving
	 * fields in JSONObjects without the caller having to catch it.
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	private Object getJSONFieldValue(JSONObject obj, String fieldName){
		try{
			return obj.get(fieldName);
		} catch(JSONException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Writes the current user's username to the response
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView handleGetUsername(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY);
		response.getWriter().write(user.getUserDetails().getUsername());
		return null;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/**
	 * @param httpRestTransport the httpRestTransport to set
	 */
	public void setHttpRestTransport(HttpRestTransport httpRestTransport) {
		this.httpRestTransport = httpRestTransport;
	}

	/**
	 * @param portalProperties the portalProperties to set
	 */
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}

	/**
	 * @param curnitService the curnitService to set
	 */
	public void setCurnitService(CurnitService curnitService) {
		this.curnitService = curnitService;
	}
}