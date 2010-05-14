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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author Hiroki Terashima
 * @version $Id:$
 */
public class TelsProjectLibraryController extends AbstractController{
	
	private ProjectService projectService;
	
	private RunService runService;
	
	private Properties portalProperties;
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 User user = ControllerUtil.getSignedInUser();
		 List<Project> projectList = this.projectService.getProjectListByTag(FamilyTag.TELS);
		 List<Project> currentProjectList = new ArrayList<Project>();
		 
		 
		 Map<Long, Integer> usageMap = new TreeMap<Long, Integer>();
		Map<Long,String> urlMap = new TreeMap<Long,String>();
		Map<Long,String> filenameMap = new TreeMap<Long,String>();
		String curriculumBaseDir = this.portalProperties.getProperty("curriculum_base_dir");
		 for (Project p: projectList) {
			 if (p.isCurrent()){
				 currentProjectList.add(p);
				 String url = (String) p.getCurnit().accept(new CurnitGetCurnitUrlVisitor());
				 	if(url != null && url != ""){
						int ndx = url.lastIndexOf("/");
						if(ndx == -1){
							urlMap.put((Long) p.getId(), curriculumBaseDir);
							filenameMap.put((Long) p.getId(), url);
						} else {
							urlMap.put((Long) p.getId(), curriculumBaseDir + "/" + url.substring(0, ndx));
							filenameMap.put((Long) p.getId(), url.substring(ndx + 1, url.length()));
						}
					}
				 usageMap.put((Long) p.getId(), this.runService.getProjectUsage((Long) p.getId()));
			 }
		 }
		 
		 ModelAndView modelAndView = new ModelAndView();
	     modelAndView.addObject("projectList", currentProjectList);
	     modelAndView.addObject("usageMap", usageMap);
	     modelAndView.addObject("userId", user.getId());
	     modelAndView.addObject("urlMap", urlMap);
	     modelAndView.addObject("filenameMap", filenameMap);
	     modelAndView.addObject("curriculumBaseDir", curriculumBaseDir);
		 return modelAndView;
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param portalProperties the portalProperties to set
	 */
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}
}
