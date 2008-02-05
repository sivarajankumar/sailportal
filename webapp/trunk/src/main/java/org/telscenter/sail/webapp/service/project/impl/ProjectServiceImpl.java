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

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.webservice.http.HttpRestTransport;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.curnit.CurnitService;
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
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public class ProjectServiceImpl implements ProjectService {

	private static final String PREVIEW_RUN_NAME = "preview";

	private static final String PREVIEW_PERIOD_NAME = "preview period";
	
	private static Set<String> PREVIEW_PERIOD_NAMES;

	private ProjectDao<Project> projectDao;
	
	private CurnitService curnitService;
	
	private JnlpService jnlpService;
	
	private RunService runService;
	
	private WorkgroupService workgroupService;
	
	private UserService userService;
	
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
		Project project = new ProjectImpl();
		project.setCurnit(curnit);
		project.setJnlp(jnlp);
		this.projectDao.save(project);
		createPreviewRun(project);
		// TODO HIROKI add acl here, to grant appropriate permissions
		return project;
	}

	/**
	 * Creates a PreviewRun for this project and
	 * set it in this project
	 * @param project
	 * @throws ObjectNotFoundException 
	 */
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
	public ModelAndView previewProject(PreviewProjectParameters params) throws ObjectNotFoundException, IOException {
		Project project = params.getProject();
		// this is a temporary hack until projects can be run without have to create a 
		// workgroup with at least 1 member in it. See this JIRA task:
		// http://jira.concord.org/browse/SDS-23
		User previewUser = userService.retrieveById(new Long(1));
		Workgroup previewWorkgroup = 
			workgroupService.getWorkgroupForPreviewOffering(project.getPreviewRun(), previewUser);
		
		String previewProjectUrl = generatePreviewProjectUrlString(
				params.getHttpRestTransport(),
				project.getPreviewRun(),
				previewWorkgroup);
		
		return new ModelAndView(new RedirectView(previewProjectUrl));
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

		String portalUrl = request.getScheme() + "://" + request.getServerName() + ":" +
		request.getServerPort() + request.getContextPath();

		String entireUrl = jnlpUrl + 
		"?emf.annotation.bundle.url=" +
		portalUrl +
		retrieveAnnotationBundleUrl + 
		"?workgroupId=" + workgroup.getId();
		return entireUrl;
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
	public Project getById(Long projectId) throws ObjectNotFoundException {
		return this.projectDao.getById(projectId);
	}

	/**
	 * @see org.telscenter.sail.webapp.service.project.ProjectService#getProjectList()
	 */
    @Transactional(readOnly = true)
	public List<Project> getProjectList() {
		return this.projectDao.getList();
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

}
