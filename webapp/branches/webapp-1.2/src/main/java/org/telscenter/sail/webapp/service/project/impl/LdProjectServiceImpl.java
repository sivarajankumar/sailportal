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

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.curnit.CurnitService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.acls.AlreadyExistsException;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.impl.RunParameters;
import org.telscenter.sail.webapp.domain.impl.UrlModuleImpl;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchReportParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class LdProjectServiceImpl implements ProjectService {


	protected static final String PREVIEW_RUN_NAME = "preview";

	private static final String PREVIEW_PERIOD_NAME = "preview period";
	
	protected static Set<String> PREVIEW_PERIOD_NAMES;
	
	private CurnitService curnitService;
	
	private ProjectDao<Project> projectDao;
	
	private AclService<Project> aclService;
	
	private WorkgroupService workgroupService;
	
	private UserService userService;
	
	private RunService runService;
	
	{
		PREVIEW_PERIOD_NAMES = new HashSet<String>();
		PREVIEW_PERIOD_NAMES.add(PREVIEW_PERIOD_NAME);
	}
	
	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#addBookmarkerToProject(org.telscenter.sail.webapp.domain.project.Project, net.sf.sail.webapp.domain.User)
	 */
	public void addBookmarkerToProject(Project project, User bookmarker){
		project.getBookmarkers().add(bookmarker);
		this.projectDao.save(project);
	}

	public void addSharedTeacherToProject(
			AddSharedTeacherParameters addSharedTeacherParameters)
			throws ObjectNotFoundException {
		Project project = addSharedTeacherParameters.getProject();
		String sharedOwnerUsername = addSharedTeacherParameters.getSharedOwnerUsername();
		User user = userService.retrieveUserByUsername(sharedOwnerUsername);
		project.getSharedowners().add(user);
		this.projectDao.save(project);

		String permission = addSharedTeacherParameters.getPermission();
		if (permission.equals(UserDetailsService.PROJECT_WRITE_ROLE)) {
			this.aclService.removePermission(project, BasePermission.READ, user);
			this.aclService.addPermission(project, BasePermission.WRITE, user);	
		} else if (permission.equals(UserDetailsService.PROJECT_READ_ROLE)) {
			this.aclService.removePermission(project, BasePermission.WRITE, user);
			this.aclService.addPermission(project, BasePermission.READ, user);
		} else if (permission.equals(UserDetailsService.PROJECT_SHARE_ROLE)) {
			this.aclService.removePermission(project, BasePermission.READ, user);
			this.aclService.addPermission(project, BasePermission.WRITE, user);	
			this.aclService.addPermission(project, BasePermission.ADMINISTRATION, user);
		}
	}

	public ModelAndView authorProject(AuthorProjectParameters params)
			throws Exception {
		String portalUrl = ControllerUtil.getBaseUrlString(params.getHttpServletRequest());
		String vleAuthorUrl = portalUrl + "/vlewrapper/vle/author.html";
		String portalAuthorUrl = portalUrl + "/webapp/author/authorproject.html";
		String command = params.getHttpServletRequest().getParameter("param1");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("portalAuthorUrl", portalAuthorUrl);
		mav.addObject("vleAuthorUrl", vleAuthorUrl);
		
		if(command != null && command != ""){
			mav.addObject("command", command);
		}
		
		Project project = params.getProject();
		if(project != null){
			mav.addObject("command", "editProject");
			mav.addObject("projectId", URLEncoder.encode((String) project.getCurnit().accept(new CurnitGetCurnitUrlVisitor()), "UTF-8"));
		}
		return mav;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#createProject(org.telscenter.sail.webapp.domain.impl.ProjectParameters)
	 */
	@Transactional(rollbackFor = { AlreadyExistsException.class,
            NotFoundException.class, DataIntegrityViolationException.class
	})
	public Project createProject(ProjectParameters projectParameters)
			throws ObjectNotFoundException {
		Curnit curnit = this.curnitService.getById(projectParameters.getCurnitId());
		Project project = this.projectDao.createEmptyProject();
		project.setCurnit(curnit);
		project.setName(projectParameters.getProjectname());
		project.setOwners(projectParameters.getOwners());
		project.setProjectType(projectParameters.getProjectType());
		//TODO -- the family tag and isCurrent being set here may need to be removed
		project.setFamilytag(FamilyTag.TELS);
		project.setCurrent(true);
		this.projectDao.save(project);
		this.aclService.addPermission(project, BasePermission.ADMINISTRATION);		
		createPreviewRun(project);
		return project;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getBookmarkerProjectList(net.sf.sail.webapp.domain.User)
	 */
	public List<Project> getBookmarkerProjectList(User bookmarker)
			throws ObjectNotFoundException {
		return this.projectDao.getProjectListByUAR(bookmarker, "bookmarker");
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getById(java.io.Serializable)
	 */
	@Transactional(readOnly = true)
	public Project getById(Serializable projectId)
			throws ObjectNotFoundException {
		Project project = this.projectDao.getById(projectId);
		project.populateProjectInfo();
		return project;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList()
	 */
    @Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })    
    @Transactional(readOnly = true)
	public List<Project> getProjectList() {
    	List<Project> projectList = this.projectDao.getList();
    	for (Project project : projectList) {
				project.populateProjectInfo();
    	}	
		return projectList;
	}

    /**
     * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList(net.sf.sail.webapp.domain.User)
     */
    @Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })
	public List<Project> getProjectList(User user) {
    	return this.projectDao.getProjectListByUAR(user, "owner");
	}

	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByInfo(org.telscenter.sail.webapp.domain.project.impl.ProjectInfo)
	 */
	public List<Project> getProjectListByInfo(ProjectInfo info)
			throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByInfo(info);
		return projectList;		
	}

	/**
	 * @override @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(org.telscenter.sail.webapp.domain.project.impl.FamilyTag)
	 */
	public List<Project> getProjectListByTag(FamilyTag familytag) throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByTag(familytag);
    	for (Project project : projectList) {
    		project.populateProjectInfo();
    	}
		return projectList;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectListByTag(java.lang.String)
	 */
	public List<Project> getProjectListByTag(String projectinfotag) throws ObjectNotFoundException {
    	List<Project> projectList = this.projectDao.retrieveListByTag(projectinfotag);
		return projectList;
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getSharedProjectList(net.sf.sail.webapp.domain.User)
	 */
	public List<Project> getSharedProjectList(User user) {
		return this.projectDao.getProjectListByUAR(user, "sharedowner");
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getSharedTeacherRole(org.telscenter.sail.webapp.domain.project.Project, net.sf.sail.webapp.domain.User)
	 */
	public String getSharedTeacherRole(Project project, User user) {
		List<Permission> permissions = this.aclService.getPermissions(project, user);
		// for projects, a user can have at most one permission per project
		if (!permissions.isEmpty()) {
			if (permissions.contains(BasePermission.ADMINISTRATION)) {
				return UserDetailsService.PROJECT_SHARE_ROLE;
			}
			Permission permission = permissions.get(0);
			if (permission.equals(BasePermission.READ)) {
				return UserDetailsService.PROJECT_READ_ROLE;
			} else if (permission.equals(BasePermission.WRITE)) {
				return UserDetailsService.PROJECT_WRITE_ROLE;
			}
		}
		return null;
	}

	public ModelAndView launchProject(LaunchProjectParameters params)
			throws Exception {
		return new ModelAndView(new RedirectView(generateStudentStartProjectUrlString( params.getHttpServletRequest(), 
				params.getRun(), params.getWorkgroup())));
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#previewProject(org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters)
	 */
	@Transactional()
	public ModelAndView previewProject(PreviewProjectParameters params) throws Exception {
		Project project = params.getProject();
		// this is a temporary hack until projects can be run without have to create a 
		// workgroup with at least 1 member in it. See this JIRA task:
		// http://jira.concord.org/browse/SDS-23
		User previewUser = userService.retrieveById(new Long(2));// preview user is user #2 in the database

		Workgroup previewWorkgroup = 
			workgroupService.getPreviewWorkgroupForRooloOffering(project.getPreviewRun(), previewUser);
				
		return new ModelAndView(new RedirectView(generateStudentPreviewProjectUrlString(params.getHttpServletRequest(), 
				project.getPreviewRun(), previewWorkgroup) + "&preview=true"));
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#removeBookmarkerFromProject(org.telscenter.sail.webapp.domain.project.Project, net.sf.sail.webapp.domain.User)
	 */
	@Transactional()
	public void removeBookmarkerFromProject(Project project, User bookmarker){
		project.getBookmarkers().remove(bookmarker);
		this.projectDao.save(project);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#updateProject(org.telscenter.sail.webapp.domain.project.Project)
	 */
	@Transactional()
	public void updateProject(Project project) {
		this.projectDao.save(project);
	}
	
	/**
	 * Returns url string for actual run
	 * @param request
	 * @param run
	 * @param workgroup
	 * @return
	 */
	public String generateStudentStartProjectUrlString(HttpServletRequest request,
			Run run, Workgroup workgroup) {
		String portalUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + 
			request.getContextPath();
		String launchVLE = "/student/vle/vle.html?runId=" + run.getId() + "&workgroupId=" + workgroup.getId();
		return portalUrl + launchVLE;
	}

	/**
	 * Returns url string for previewing the run
	 * @param request
	 * @param run
	 * @param workgroup
	 * @return
	 */
	public String generateStudentPreviewProjectUrlString(HttpServletRequest request,
			Run run, Workgroup workgroup) {
		String portalUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + 
			request.getContextPath();
		String launchVLE = "/preview.html?runId=" + run.getId() + "&workgroupId=" + workgroup.getId();
		return portalUrl + launchVLE;
	}

	/**
	 * Creates a PreviewRun for this project and
	 * set it in this project
	 * @param project
	 * @throws ObjectNotFoundException 
	 */
	@Transactional
	protected void createPreviewRun(Project project) throws ObjectNotFoundException {
		RunParameters runParameters = new RunParameters();
		runParameters.setCurnitId(project.getCurnit().getId());
		runParameters.setJnlpId(null);
		runParameters.setName(PREVIEW_RUN_NAME);
		runParameters.setOwners(null);
		runParameters.setPeriodNames(PREVIEW_PERIOD_NAMES);
		runParameters.setProject(project);
		Run previewRun = this.runService.createRun(runParameters);
		project.setPreviewRun(previewRun);
		this.projectDao.save(project);
	}

	/**
	 * @param curnitService the curnitService to set
	 */
	public void setCurnitService(CurnitService curnitService) {
		this.curnitService = curnitService;
	}

	/**
	 * @param projectDao the projectDao to set
	 */
	public void setProjectDao(ProjectDao<Project> projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * @param aclService the aclService to set
	 */
	public void setAclService(AclService<Project> aclService) {
		this.aclService = aclService;
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
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public Object launchReport(LaunchReportParameters launchReportParameters) {
		// do nothing for now
		return null;
	}
	
}