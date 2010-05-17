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
package org.telscenter.sail.webapp.dao.project.impl;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;
import net.sf.sail.webapp.domain.impl.CurnitGetCurnitUrlVisitor;

import org.telscenter.sail.webapp.dao.project.ProjectMetadataDao;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.domain.project.impl.ProjectMetadataImpl;
import org.telscenter.sail.webapp.domain.project.impl.ProjectType;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class HibernateProjectMetadataDao extends AbstractHibernateDao<ProjectMetadata> implements ProjectMetadataDao<ProjectMetadata>{
	
	private ProjectService projectService;
	
	private Properties portalProperties;
	
	private final static String FIND_ALL_QUERY = "from ProjectMetadataImpl";

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
	 */
	@Override
	protected Class<? extends ProjectMetadata> getDataObjectClass() {
		return ProjectMetadataImpl.class;
	}

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
	 */
	@Override
	protected String getFindAllQuery() {
		return FIND_ALL_QUERY;
	}

	/**
	 * @see org.telscenter.sail.webapp.dao.project.ProjectMetadataDao#addMetadataToProjects(java.util.List)
	 */
	public List<Project> addMetadataToProjects(List<Project> projects){
		this.populateProjectListWithMetadata(projects, this.getActiveVersionsForProjectList(projects));
		return projects;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.dao.project.ProjectMetadataDao#addMetadataToProject(org.telscenter.sail.webapp.domain.project.Project)
	 */
	public Project addMetadataToProject(Project project){
		String versionId = this.projectService.getActiveVersion(project);
		
		if(versionId!=null){
			project.setMetadata(this.getMetadataByProjectIdAndVersionId((Long)project.getId(), versionId));
		}
		
		return project;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.dao.project.ProjectMetadataDao#getMetadataByProjectIdAndVersionId(java.lang.Long, java.lang.String)
	 */
	public ProjectMetadata getMetadataByProjectIdAndVersionId(Long projectId, String versionId){
		String q = "select metadata from ProjectMetadataImpl metadata where metadata.projectId=" + projectId + " and metadata.versionId='" + versionId + "'";
		
		List<ProjectMetadata> metas = this.getHibernateTemplate().find(q);
		
		/* This list should only contain one metadata */
		if(metas != null && metas.size()==1){
			return metas.iterator().next();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a <code>String</code> of | delimited project id, version id ~ delimited pairs for each of 
	 * the projects in the given <code>List<Project></code> projectList.
	 * 
	 * @param projectList
	 * @return String
	 */
	private String getActiveVersionsForProjectList(List<Project> projectList){
		String curriculumBase = this.portalProperties.getProperty("curriculum_base_dir");
		String projectIDPaths = null;
		
		/* create a | delimited list of a ~ delimited string of project id and the project path */
		for(int y=0;y<projectList.size();y++){
			Project project = projectList.get(y);
			if(project.getProjectType().equals(ProjectType.LD)){
				String url = (String) project.getCurnit().accept(new CurnitGetCurnitUrlVisitor());
				
				if(projectIDPaths==null){
					projectIDPaths = project.getId() + "~" + curriculumBase + url;
				} else {
					projectIDPaths += "|" + project.getId() + "~" + curriculumBase + url;
				}
			}
		}
		
		/* return the versions for the ids from project service (which gets it from the version master) */
		return this.projectService.getActiveVersions(projectIDPaths);
	}
	
	/**
	 * Given a <code>List<Project></code> and the | and ~ delimited <code>String</code> of active versions
	 * and project ids, populates the projects in the list with the associated metadata.
	 * 
	 * @param projectList
	 * @param activeVersionProjectIdList
	 */
	private void populateProjectListWithMetadata(List<Project> projectList, String activeVersionProjectIdList){
		Map<Long,String> idVersionMap = new TreeMap<Long,String>();
		String[] projectIDVersions = activeVersionProjectIdList.split("\\|");
		
		/* parse the project ids and versions and place them in a map for easy lookup */
		for(int z=0;z<projectIDVersions.length;z++){
			if(projectIDVersions[z] != null && !projectIDVersions[z].equals("") && !projectIDVersions[z].equals("null")){
				String[] splitz = projectIDVersions[z].split("~");
				idVersionMap.put(Long.parseLong(splitz[0]), splitz[1]);
			}
		}
		
		/* add any metadata associated with each project in the list */
		for(Project project : projectList){
			String versionId = idVersionMap.get(project.getId());
			
			if(versionId != null){
				project.setMetadata(this.getMetadataByProjectIdAndVersionId((Long)project.getId(), versionId));
			}
		}
	}
	
	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * @param portalProperties the portalProperties to set
	 */
	public void setPortalProperties(Properties portalProperties) {
		this.portalProperties = portalProperties;
	}
	
}
