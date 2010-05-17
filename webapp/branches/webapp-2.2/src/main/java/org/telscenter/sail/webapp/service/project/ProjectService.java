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
import java.io.Serializable;
import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.NotAuthorizedException;

import org.springframework.security.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.domain.impl.ProjectParameters;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.domain.project.impl.AuthorProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchProjectParameters;
import org.telscenter.sail.webapp.domain.project.impl.LaunchReportParameters;
import org.telscenter.sail.webapp.domain.project.impl.PreviewProjectParameters;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;

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
	@Transactional
	public List<Project> getAllProjectsList();
	
	/**
	 * Get a <code>List</code> of <code>Project</code>
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
	@Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })
	public List<Project> getProjectList();

	/**
	 * Get a <code>List</code> of <code>Project</code> that the specified
	 * user owns
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
    @Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })
	public List<Project> getProjectList(User user);

	/**
	 * Get a <code>List</code> of <code>Project</code> that have
	 * been shared with the specified user.
	 * 
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
    @Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })
	public List<Project> getSharedProjectList(User user);

	/**
	 * Get a <code>List</code> of <code>Project</code> with
	 * matching FamilyTag
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
	public List<Project> getProjectListByTag(FamilyTag tag) throws ObjectNotFoundException;
	
	/**
	 * Get a <code>List</code> of <code>Project</code> with
	 * matching ProjectInfoTag
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
	public List<Project> getProjectListByTag(String tag) throws ObjectNotFoundException;

	/**
	 * Get a <code>List</code> of <code>Project</code> with
	 * matching ProjectInfo
	 * @return a <code>List</code> of <code>Project</code>
	 */
	@Transactional
	public List<Project> getProjectListByInfo(ProjectInfo info) throws ObjectNotFoundException;
	
	/**
	 * Retrieves a <code>List</code> of <code>Project</code> that
	 * has been bookmarked by the given <code>User</code>
	 * 
	 * @param <code>User</code> bookmarker
	 * @return <code>List<Project></code>
	 * @throws ObjectNotFoundException
	 */
	@Transactional
	public List<Project> getBookmarkerProjectList(User bookmarker) throws ObjectNotFoundException;
	
	/**
	 * Adds the given <code>User</code> bookmarker to the <code>Project</code> project
	 * 
	 * @param <code>Project</code> project
	 * @param <code>User</code> bookmarker
	 */
	@Transactional
	public void addBookmarkerToProject(Project project, User bookmarker) throws ObjectNotFoundException;
	
	/**
	 * Removes the given <code>User</code> bookmarker from the <code>Project</code> project
	 * 
	 * @param <code>Project</code> project
	 * @param <code>User</code> bookmarker
	 */
	@Transactional
	public void removeBookmarkerFromProject(Project project, User bookmarker) throws ObjectNotFoundException;

	/**
     * Returns the permission that the specified user has on the specified project
     * 
     * @param project The <code>Project</code> that is shared.
     * @param user The <code>User</code> that shares the <code>Project</code>
     * @return A <code>String</code> containing the permission that 
     *     the user has on the project. If the user does not have permission on the project,
     *     null is returned.
     */
    @Transactional(readOnly = true)
    public String getSharedTeacherRole(Project project, User user);
    
    /**
     * @param addSharedTeacherParameters
     */
    @Secured( {"ROLE_TEACHER"} )
    @Transactional()
	public void addSharedTeacherToProject(AddSharedTeacherParameters addSharedTeacherParameters) throws ObjectNotFoundException;
	
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
	 * Saves the project
	 * 
	 * @param project <code>Project</code> contains updated Project.
	 */
	public void updateProject(Project project, User user) throws NotAuthorizedException;
	
	/**
	 * Launches the project given the launchProjectParameters
	 * 
	 * @param launchProjectParameters parameters needed to launch the project
	 */
	public Object launchProject(LaunchProjectParameters launchProjectParameters) throws Exception;

	/**
	 * Launches the report given the launchReportParameters
	 * 
	 * @return
	 */
	public Object launchReport(LaunchReportParameters launchReportParameters);
	
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
	public Object authorProject(AuthorProjectParameters authorProjectParameters) throws Exception;

	/**
	 * Gets a project with the given projectid
	 * 
	 * @param projectId
	 *     the id of the project
	 * @return <code>Project</code> with the specified projectId
	 * @throws ObjectNotFoundException when the specified projectId
	 *     does not exist
	 */
	public Project getById(Serializable projectId) throws ObjectNotFoundException;
	
	/**
	 * Given a <code>String</code> query, returns a <code>List</code> of
	 * <code>Project</code> that satisfies the query.
	 * 
	 * @param <code>String</code> query
	 * @return <code>List<Project></code>
	 */
	public List<Project> getProjectList(String query);
	
	/**
	 * Given a <code>Project</code> project, attempts to minify a file and returns
	 * a <code>String</code> of the final status of the operation.
	 * 
	 * @param project
	 * @return
	 */
	public String minifyProject(Project project);
	
	/**
	 * Give a <code>Project</code> and a <code>String</code> versionId, returns 
	 * <code>JSONObject</code> the project metadata from the file, if it exists, 
	 * returns null otherwise.
	 * 
	 * @param project
	 * @return JSONObject - project metadata
	 */
	public JSONObject getProjectMetadataFile(Project project, String versionId);
	
	/**
	 * Give a <code>Project</code> returns <code>JSONObject</code> the project metadata 
	 * from the active version of the file, if it exists, returns null otherwise.
	 * 
	 * @param project
	 * @return JSONObject - project metadata
	 */
	public JSONObject getProjectMetadataFile(Project project);
	
	/**
	 * Given a <code>Project</code> project, attempts to retrieve and return the
	 * <code>String</code> currently active version of the project from the version
	 * master. Returns the active version if successful, null otherwise.
	 * 
	 * @param project
	 * @return String activeVersion
	 */
	public String getActiveVersion(Project project);
	
	/**
	 * Given a <code>String</code> projectIDPaths - a | delimited list of ~ delimited
	 * project IDs, project paths and returns <code>String</code> a | delimited list of
	 * ~ delimited project IDs, version id.
	 * 
	 * @param projects
	 * @return String
	 */
	public String getActiveVersions(String projectIDPaths);
	
	/**
	 * Given a <code>Project</code> project, a <code>String</code> username, and a
	 * <code>String</code> snapshotName, attempts to create a snapshot of the given
	 * version of the project. If successful, returns <code>String</code> the versionId
	 * of the snapshot, returns "failed" otherwise.
	 * 
	 * @param project
	 * @param username
	 * @param snapshotName
	 * @return String versionId
	 */
	public String takeSnapshot(Project project, String username, String snapshotName);
	
	/**
	 * Given a <code>Project</code> and the <code>ProjectMetadata</code> metadata updates the
	 * metadata in the database.
	 * 
	 * @param project
	 * @param metadata
	 * @return ProjectMetadata
	 */
	public ProjectMetadata updateMetadata(ProjectMetadata metadata);
	
	/**
	 * Given a <code>Long</code> projectId and a <code>String</code> versionId, returns the
	 * <code>ProjectMetadata</code> that is associated with that projectId and versionId, if
	 * one exists, returns null otherwise.
	 * 
	 * @param projectId
	 * @param versionId
	 * @return
	 */
	public ProjectMetadata getMetadata(Long projectId, String versionId);
	
	/**
	 * Given a <code>Project</code> project and <code>User</code> user, returns
	 * <code>boolean</code> true if the user is allowed to create a run from that
	 * project (ie, project is TELS, owner, sharedOwner), returns false otherwise.
	 * 
	 * @param project
	 * @param user
	 * @return boolean
	 */
	public boolean canCreateRun(Project project, User user);
	
	/**
	 * Given a <code>Project</code> project and <code>User</code> user, returns
	 * <code>boolean</code> true if the user is allowed to author that particular
	 * project, returns false otherwise.
	 * 
	 * @param project
	 * @param user
	 * @return boolean
	 */
	public boolean canAuthorProject(Project project, User user);
	
	/**
	 * Given a <code>Project</code> project and a <code>User</code> user, returns
	 * <code>boolean</code> true if the user has read access to that particular
	 * project, returns false otherwise.
	 * 
	 * @param project
	 * @param user
	 * @return
	 */
	public boolean canReadProject(Project project, User user);
	
	/**
	 * Returns a <code>List<Project></code> list of all projects in the data store.
	 * 
	 * @return List<Project> projects
	 */
	public List<Project> getAdminProjectList();
}
