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
package org.telscenter.sail.webapp.service.project;

import java.io.IOException;
import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;

import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;

/**
 * A Service for Projects
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public interface ProjectService {
	
	/**
	 * Get a <code>List</code> of <code>Project</code>
	 * @return a <code>List</code> of <code>Project</code>
	 */
	public List<Project> getProjectList();
	
	/**
	 * Creates a new <code>Project</code>
	 * 
	 * Also, this creates a "Preview run"- that is, a run that is used
	 * just for the purpose of previewing this project. This is not the ideal
	 * solution to Previewing a Project, but the other solution is too much work
	 * (making a new JNLP project that takes in curniturl and jnlpurl). The author
	 * can also always use the authoring tool to preview the project
	 * 
	 * @param <code>ProjectParameters</code>
	 *     the project parameters object
	 * @return the <code>Project</code> that was created
	 * @throws ObjectNotFoundException when projectparameters references
	 *     curnitId and jnlpId that do not exist
	 */
	public Project createProject(ProjectParameters projectParameters) 
	    throws ObjectNotFoundException;
	
	/**
	 * Launches the project given the launchProjectParameters
	 * 
	 * @param launchProjectParameters parameters needed to launch the project
	 */
	public Object launchProject(LaunchProjectParameters launchProjectParameters) throws Exception;

	/**
	 * Launches a Preview of the Project
	 * 
	 * @param projectId
	 *     the id of the project
	 * @throws ObjectNotFoundException when the specified projectId
	 *     does not exist
	 * @throws IOException when the url cannot be loaded
	 */
	public Object previewProject(PreviewProjectParameters previewProjectParameters) throws Exception;

	/**
	 * Allows users to author a project
	 * 
	 * @param authorProjectParameters
	 * @return
	 * @throws Exception
	 */
	//public Object authorProject(AuthorProjectParameters authorProjectParameters) throws Exception;

	/**
	 * Gets a project with the given projectid
	 * 
	 * @param projectId
	 *     the id of the project
	 * @return <code>Project</code> with the specified projectId
	 * @throws ObjectNotFoundException when the specified projectId
	 *     does not exist
	 */
	public Project getById(Long projectId) throws ObjectNotFoundException;
}
