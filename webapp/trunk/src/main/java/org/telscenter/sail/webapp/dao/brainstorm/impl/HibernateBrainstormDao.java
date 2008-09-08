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
package org.telscenter.sail.webapp.dao.brainstorm.impl;

import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;

import org.springframework.dao.support.DataAccessUtils;
import org.telscenter.sail.webapp.dao.brainstorm.BrainstormDao;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.impl.BrainstormImpl;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateBrainstormDao extends AbstractHibernateDao<Brainstorm> implements
		BrainstormDao<Brainstorm> {

	private static final String FIND_ALL_QUERY = "from BrainstormImpl";

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
	 */
	@Override
	protected Class<BrainstormImpl> getDataObjectClass() {
		return BrainstormImpl.class;
	}

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
	 */
	@Override
	protected String getFindAllQuery() {
		return FIND_ALL_QUERY;
	}

	/**
	 * @see org.telscenter.sail.webapp.dao.brainstorm.BrainstormDao#retrieveByAnswer(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer)
	 * SELECT brainstorms.* from brainstorms, brainstorms_related_to_brainstormanswers, brainstormanswers
	 * WHERE brainstorms.id=brainstorms_related_to_brainstormanswers.brainstorms_fk
	 * AND brainstormanswers.id=brainstorms_related_to_brainstormanswers.brainstormanswers_fk
	 * AND brainstormanswers.id=answer.id
	 */
	public Brainstorm retrieveByAnswer(Answer answer) {
		return (Brainstorm) DataAccessUtils
		    .uniqueResult(this.getHibernateTemplate().findByNamedParam(
		    		"SELECT brainstorms.* from brainstorms, brainstorms_related_to_brainstormanswers, brainstormanswers " +
		    		"WHERE brainstorms.id=brainstorms_related_to_brainstormanswers.brainstorms_fk " +
		    		"AND brainstormanswers.id=brainstorms_related_to_brainstormanswers.brainstormanswers_fk " +
		    		"AND brainstormanswers.id = :answerId", "answerId", answer.getId()));
	}

}