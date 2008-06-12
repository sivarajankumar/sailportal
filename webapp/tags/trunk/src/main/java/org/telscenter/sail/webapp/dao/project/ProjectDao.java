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

import java.util.List;

import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.FamilyTag;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.SimpleDao;

/**
 * @author Hiroki Terashima
 *
 * @version $Id$
 */
public interface ProjectDao<T extends Project> extends SimpleDao<T> {
	
    /**
	 * Given an input string retrieve a list of corresponding records from data store.
	 * 
	 * @param family
	 *            <code>FamilyTag</code> representing the familytag of the data in
	 *            the data store.
	 * @return A list of project objects.
	 * @throws ObjectNotFoundException if list is not found.
	 */
	public List<Project> retrieveListByTag(FamilyTag familytag) throws ObjectNotFoundException;

    /**
	 * Given an input string retrieve a list of corresponding records from data store.
	 * 
	 * @param family
	 *            <code>String</code> representing the projectinfotag of the data in
	 *            the data store.
	 * @return A list of project objects.
	 * @throws ObjectNotFoundException if list is not found.
	 */
	public List<Project> retrieveListByTag(String projectinfotag) throws ObjectNotFoundException;
}
