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
package org.telscenter.sail.webapp.dao.project;

import java.io.Serializable;
import java.util.List;

import net.sf.sail.webapp.dao.SimpleDao;

import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public interface ProjectMetadataDao<T extends ProjectMetadata> extends SimpleDao<T>{

	/**
	 * Given a <code>List<Project></code> iterates through each project, retrieves
	 * the metadata for that project from the data store and adds it to the project,
	 * then returns <code>List<Project></code> the list of projects with their metadata.
	 * 
	 * @param projects
	 * @return List<Project> projects
	 */
	public List<Project> addMetadataToProjects(List<Project> projects);
	
	/**
	 * Given a <code>Long</code> projectId and a <code>String</code> versionId,
	 * returns the <code>ProjectMetadata</code> associated with that projectId and versionId
	 * if one exists in the data store, returns null otherwise.
	 * 
	 * @param projectId
	 * @param versionId
	 * @return
	 */
	public ProjectMetadata getMetadataByProjectIdAndVersionId(Long projectId, String versionId);
	
	/**
	 * Given a <code>Project</code> determines the active version and sets the metadata based
	 * on that project's id and version, if it exists and returns the <code>Project</code> project.
	 * 
	 * @param project
	 * @return Project - project
	 */
	public Project addMetadataToProject(Project project);
}
