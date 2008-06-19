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
package org.telscenter.sail.webapp.service.project.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.curnit.CurnitService;
import net.sf.sail.webapp.service.file.impl.AuthoringJNLPModifier;
import net.sf.sail.webapp.service.jnlp.JnlpService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.acegisecurity.acls.AlreadyExistsException;
import org.acegisecurity.acls.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.impl.RunParameters;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.cmsImpl.RooloProjectImpl;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * TELS Portal's ProjectService can work with projects that are persisted
 * in the local datastore via hibernate, as well as projects that are persisted
 * in the local or remote content management system, via rmi or webdav.
 * 
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public class ProjectServiceImpl implements ProjectService {

	protected static final String PREVIEW_RUN_NAME = "preview";

	private static final String PREVIEW_PERIOD_NAME = "preview period";

	private static final String JNLP_CONTENT_TYPE = "application/x-java-jnlp-file";
	
	protected static Set<String> PREVIEW_PERIOD_NAMES;

	private ProjectDao<Project> projectDao;
	
	private ProjectDao<RooloProjectImpl> rooloProjectDao;
	
	private CurnitService curnitService;
	
	private JnlpService jnlpService;
	
	private RunService runService;
	
	private WorkgroupService workgroupService;
	
	private UserService userService;

	private AuthoringJNLPModifier modifier;
	
	private String authoringToolJnlpUrl;
	
	public static String retrieveAnnotationBundleUrl = "/student/getannotationbundle.html";

	{
		PREVIEW_PERIOD_NAMES = new HashSet<String>();
		PREVIEW_PERIOD_NAMES.add(PREVIEW_PERIOD_NAME);
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#createProject(org.telscenter.sail.webapp.domain.impl.ProjectParameters)
	 */
	@Transactional(rollbackFor = { AlreadyExistsException.class,
            NotFoundException.class, DataIntegrityViolationException.class
	})
	public Project createProject(ProjectParameters projectParameters) 
	    throws ObjectNotFoundException {
		Curnit curnit = 
			this.curnitService.getById(projectParameters.getCurnitId());
		Jnlp jnlp = 
			this.jnlpService.getById(projectParameters.getJnlpId());
		Project project = this.projectDao.createEmptyProject();
		project.setCurnit(curnit);
		project.setJnlp(jnlp);
		this.projectDao.save(project);
		createPreviewRun(project);
		// TODO HIROKI add acl here, to grant appropriate permissions
		return project;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#updateProject(org.telscenter.sail.webapp.domain.project.Project)
	 */
	@Transactional()
	public void updateProject(Project project) {
		if (project instanceof ProjectImpl) {
			this.projectDao.save(project);
		} else if (project instanceof RooloProjectImpl) {
			this.rooloProjectDao.save((RooloProjectImpl) project);
		}
	}
	
	/**
	 * Creates a PreviewRun for this project and
	 * set it in this project
	 * @param project
	 * @throws ObjectNotFoundException 
	 */
	@Transactional
	private void createPreviewRun(Project project) throws ObjectNotFoundException {
		RunParameters runParameters = new RunParameters();
		runParameters.setCurnitId(project.getCurnit().getId());
		runParameters.setJnlpId(project.getJnlp().getId());
		runParameters.setName(PREVIEW_RUN_NAME);
		runParameters.setOwners(null);
		runParameters.setPeriodNames(PREVIEW_PERIOD_NAMES);
		runParameters.setProject(project);
		Run previewRun = this.runService.createRun(runParameters);
		project.setPreviewRun(previewRun);
		this.projectDao.save(project);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#launchProject(org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters)
	 */
	public ModelAndView launchProject(LaunchProjectParameters params) {
		String entireUrl = generateStudentStartProjectUrlString(
				params.getHttpRestTransport(), params.getHttpServletRequest(), 
				params.getRun(), params.getWorkgroup(),
				retrieveAnnotationBundleUrl
				);
		return new ModelAndView(new RedirectView(entireUrl));
	}

	/**
	 * @throws IOException 
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#previewProject(java.lang.Long)
	 */
	@Transactional
	public ModelAndView previewProject(PreviewProjectParameters params) throws ObjectNotFoundException, IOException {
		Project project = params.getProject();
		// this is a temporary hack until projects can be run without have to create a 
		// workgroup with at least 1 member in it. See this JIRA task:
		// http://jira.concord.org/browse/SDS-23
		User previewUser = userService.retrieveById(new Long(2));// preview user is user #2 in the database
		Workgroup previewWorkgroup = 
			workgroupService.getWorkgroupForPreviewOffering(project.getPreviewRun(), previewUser);
		
		String previewProjectUrl = generatePreviewProjectUrlString(
				params.getHttpRestTransport(),
				project.getPreviewRun(),
				previewWorkgroup);
		
		return new ModelAndView(new RedirectView(previewProjectUrl));
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#authorProject(org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters)
	 */
	@Transactional
	public ModelAndView authorProject(AuthorProjectParameters authorProjectParameters)
			throws Exception {
		// TODO get the author jnlpurl from project

		// TODO replace the below when ready to switch to otml
		//String curnitUrl = project.getCurnit().getSdsCurnit().getUrl();
		String curnitUrl = "http://www.telscenter.org/confluence/download/attachments/20047/Airbags.otml";
		
		URL jnlpURL = new URL(authoringToolJnlpUrl);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(jnlpURL.openStream()));
		
		String jnlpString = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			jnlpString += inputLine;
		}

		HttpServletResponse httpServletResponse = authorProjectParameters.getHttpServletResponse();
		
		String outputJNLPString = modifier.modifyJnlp(jnlpString, curnitUrl);
		httpServletResponse.setHeader("Cache-Control", "no-cache");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader ("Expires", 0);

		String fileName = authorProjectParameters.getHttpServletRequest().getServletPath();
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		fileName = fileName.substring(0, fileName.indexOf(".")) + ".jnlp";
		httpServletResponse.addHeader("Content-Disposition", "Inline; fileName=" + fileName);

		httpServletResponse.setContentType(JNLP_CONTENT_TYPE);
		//httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().print(outputJNLPString);
		
		return null;
	}

	/**
	 * Generates the url string that users need to go to start the project
	 * @param httpRestTransport
	 * @param request request that was made
	 * @param run <code>Run</code> that the user is in
	 * @param workgroup <code>Workgroup</code> that the user is in
	 * @param retrieveAnnotationBundleUrl
	 * @returnurl String url representation to download the jnlp and start
     *     the project
	 */
	public static String generateStudentStartProjectUrlString(HttpRestTransport httpRestTransport, HttpServletRequest request,
			Run run, Workgroup workgroup, String retrieveAnnotationBundleUrl) {
		String jnlpUrl = generateLaunchProjectUrlString(httpRestTransport, run,
				workgroup);

		String entireUrl = jnlpUrl + "?" + generateRetrieveAnnotationBundleParamRequestString(request, workgroup);
		return entireUrl;
	}
	
	/**
	 * Generates the request parameter string to be added to the end of
	 * the launch/preview project url
	 * 
	 * @param request
	 * @param workgroup
	 * @return
	 */
	public static String generateRetrieveAnnotationBundleParamRequestString(HttpServletRequest request, Workgroup workgroup) {
		String portalUrl = request.getScheme() + "://" + request.getServerName() + ":" +
		request.getServerPort() + request.getContextPath();

	    String retrieveAnnotationBundleUrlString = "emf.annotation.bundle.url=" + 
	         portalUrl + retrieveAnnotationBundleUrl + "?workgroupId=" + workgroup.getId();

		return retrieveAnnotationBundleUrlString;
	}
	
	/**
	 * Generates the url string that is used to preview a project
	 * @param httpRestTransport
	 * @param request
	 * @param run
	 * @param workgroup
	 * @return
	 */
	public static String generatePreviewProjectUrlString(HttpRestTransport httpRestTransport, Run run, Workgroup workgroup) {
		String launchProjectUrlString = generateLaunchProjectUrlString(httpRestTransport, run, workgroup);
		String previewProjectUrlString = launchProjectUrlString + "/view";
		return previewProjectUrlString;
	}


	/**
	 * Returns the basic URL used to launch the project, ie
	 * http://saildataservice.concord.org/3/offering/2374/jnlp/12063
	 * 
	 * @param httpRestTransport
	 * @param run
	 * @param workgroup
	 * @return
	 */
	private static String generateLaunchProjectUrlString(
			HttpRestTransport httpRestTransport, Run run, Workgroup workgroup) {
		String jnlpUrl = httpRestTransport.getBaseUrl() + "/offering/" + 
		run.getSdsOffering().getSdsObjectId() + "/jnlp/" +
		workgroup.getSdsWorkgroup().getSdsObjectId();
		return jnlpUrl;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getById(java.lang.Long)
	 */
    @Transactional(readOnly = true)
	public Project getById(Serializable projectId) throws ObjectNotFoundException {
    	Project project = this.projectDao.getById(projectId);
    	if (project != null) {
    		return project;
    	} else {   	
    		RooloProjectImpl rooloProject = this.rooloProjectDao.getById(projectId);
    		return rooloProject;
    	}
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList()
	 */
    @Transactional(readOnly = true)
	public List<Project> getProjectList() {
    	List<Project> projectList = this.projectDao.getList();
    	List<RooloProjectImpl> rooloProjectList = this.rooloProjectDao.getList();
    	List<Project> projects = new ArrayList<Project>();
    	projects.addAll(projectList);
    	projects.addAll(rooloProjectList);
		return projects;
	}
    
	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(java.lang.String)
	 */
	public List<Project> getProjectListByTag(String projectinfotag) throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByTag(projectinfotag);
    	List<RooloProjectImpl> rooloProjectList = this.rooloProjectDao.retrieveListByTag(projectinfotag);
    	List<Project> projects = new ArrayList<Project>();
    	projects.addAll(projectList);
    	projects.addAll(rooloProjectList);
		return projects;
	}

	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(org.telscenter.sail.webapp.domain.project.impl.FamilyTag)
	 */
	public List<Project> getProjectListByTag(FamilyTag familytag) throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByTag(familytag);
    	List<RooloProjectImpl> rooloProjectList = this.rooloProjectDao.retrieveListByTag(familytag);
    	List<Project> projects = new ArrayList<Project>();
    	projects.addAll(projectList);
    	projects.addAll(rooloProjectList);
		return projects;
	}
	
	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByInfo(org.telscenter.sail.webapp.domain.project.impl.ProjectInfo)
	 */
	public List<Project> getProjectListByInfo(ProjectInfo info)
			throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByInfo(info);
    	List<RooloProjectImpl> rooloProjectList = this.rooloProjectDao.retrieveListByInfo(info);
    	List<Project> projects = new ArrayList<Project>();
    	projects.addAll(projectList);
    	projects.addAll(rooloProjectList);
		return projects;		
	}

	/**
	 * @param projectDao the projectDao to set
	 */
	public void setProjectDao(ProjectDao<Project> projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * @param curnitService the curnitService to set
	 */
	public void setCurnitService(CurnitService curnitService) {
		this.curnitService = curnitService;
	}

	/**
	 * @param jnlpService the jnlpService to set
	 */
	public void setJnlpService(JnlpService jnlpService) {
		this.jnlpService = jnlpService;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param rooloProjectDao the rooloProjectDao to set
	 */
	public void setRooloProjectDao(ProjectDao<RooloProjectImpl> rooloProjectDao) {
		this.rooloProjectDao = rooloProjectDao;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(AuthoringJNLPModifier modifier) {
		this.modifier = modifier;
	}

	/**
	 * @param authoringToolJnlpUrl the authoringToolJnlpUrl to set
	 */
	public void setAuthoringToolJnlpUrl(String authoringToolJnlpUrl) {
		this.authoringToolJnlpUrl = authoringToolJnlpUrl;
	}
}
