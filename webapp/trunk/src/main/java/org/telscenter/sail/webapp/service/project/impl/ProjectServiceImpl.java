/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
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

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.service.curnit.CurnitNotFoundException;
import net.sf.sail.webapp.service.curnit.impl.CurnitServiceImpl;

import org.telscenter.sail.webapp.domain.Project;
import org.telscenter.sail.webapp.domain.impl.ProjectImpl;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * Service for the TELS's Project Domain Object
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class ProjectServiceImpl extends CurnitServiceImpl implements
		ProjectService {
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectList() {
		return (List<Project>) super.getCurnitList();
	}

	@Override
	public Project getById(Long projectId) throws CurnitNotFoundException {
		// TODO HT: change Initializer.java to populate database with Projects, not curnits.
		Curnit curnit = super.getById(projectId);
		Project project = new ProjectImpl();
		project.setSdsCurnit(curnit.getSdsCurnit());
		Set<Integer> grades = new TreeSet<Integer>();
		grades.add(1);
		grades.add(2);
		grades.add(3);
		grades.add(4);
		grades.add(5);
		project.setGrades(grades);
		project.setDescription("This project is for advanced bio-engineers.");
		return project;
	}
}
