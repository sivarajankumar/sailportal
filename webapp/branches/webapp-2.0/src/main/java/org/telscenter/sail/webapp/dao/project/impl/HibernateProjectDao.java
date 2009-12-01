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

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;
import net.sf.sail.webapp.domain.User;

import org.telscenter.sail.webapp.dao.project.ProjectDao;
import org.telscenter.sail.webapp.domain.project.FamilyTag;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectInfo;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;

/**
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public class HibernateProjectDao extends AbstractHibernateDao<Project> implements
		ProjectDao<Project> {

    	private static final String FIND_ALL_QUERY = "from ProjectImpl";

	/**
	 * @see org.telscenter.sail.webapp.dao.offering.RunDao#retrieveByRunCode(String)
	 */
	@SuppressWarnings("unchecked")
	public List<Project> retrieveListByTag(FamilyTag familytag) throws ObjectNotFoundException {
		List<Project> projects = this
						.getHibernateTemplate()
						.findByNamedParam(
								"from ProjectImpl as project where project.familytag = :familytag",
								"familytag", familytag);
		if (projects == null)
			throw new ObjectNotFoundException(familytag, this
					.getDataObjectClass());
		return projects;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.dao.offering.RunDao#retrieveByRunCode(String)
	 */
	@SuppressWarnings("unchecked")
	public List<Project> retrieveListByTag(String projectinfotag) throws ObjectNotFoundException {
		List<Project> projects = this
		.getHibernateTemplate()
		.findByNamedParam(
				"from ProjectImpl as project where upper(project.projectinfotag) = :projectinfotag",
				"projectinfotag", projectinfotag.toString().toUpperCase());
		if (projects == null)
			throw new ObjectNotFoundException(projectinfotag, this
					.getDataObjectClass());
		return projects;
	}
	
	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
	 */
	@Override
	protected String getFindAllQuery() {
		return FIND_ALL_QUERY;
	}
	
	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
	 */
	@Override
	protected Class<? extends ProjectImpl> getDataObjectClass() {
		return ProjectImpl.class;
	}

	public Project createEmptyProject() {
		return new ProjectImpl();
	}

	public List<Project> retrieveListByInfo(ProjectInfo projectinfo)
		throws ObjectNotFoundException {
	    
	    return this.retrieveListByTag(projectinfo.getFamilyTag());
	}
	
	/**
	 * @see org.telscenter.sail.webapp.dao.project.ProjectDao#getProjectListByUAR(net.sf.sail.webapp.domain.User, java.lang.String)
	 */
	public List<Project> getProjectListByUAR(User user, String role){
		String q = "select project from ProjectImpl project inner join project." +
			role + "s " + role + " where " + role + ".id='" + user.getId() + "'";
		return this.getHibernateTemplate().find(q);
	}
	
	/**
	 * @see org.telscenter.sail.webapp.dao.project.ProjectDao#getProjectList(java.lang.String)
	 */
	public List<Project> getProjectList(String query){
		return this.getHibernateTemplate().find(query);
	}
}
