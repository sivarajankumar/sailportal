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
package org.telscenter.sail.webapp.dao.offering.impl;

import org.springframework.dao.support.DataAccessUtils;
import org.telscenter.sail.webapp.dao.offering.RunDao;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.RunImpl;

import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;

/**
 * DAO for WISE run, which extends offering
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
public class HibernateRunDao extends AbstractHibernateDao<Run> 
        implements RunDao<Run> {

	private static final String FIND_ALL_QUERY = "from RunImpl";

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
     */
    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }
    
    /**
     * @see org.telscenter.sail.webapp.dao.offering.RunDao#retrieveByRunCode(String)
     */
    public Run retrieveByRunCode(String runcode) {
    	return (Run) DataAccessUtils.uniqueResult(
    			this.getHibernateTemplate().findByNamedParam(
    					"from RunImpl as run where run.runcode = :runcode", 
    					"runcode", runcode));
    }

    /**
     * @see org.telscenter.sail.webapp.dao.offering.RunDao#hasRuncode(String)
     */
    public boolean hasRuncode(String runcode) {
    	return (this.retrieveByRunCode(runcode) != null);
    }

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
	 */
	@Override
	protected Class<RunImpl> getDataObjectClass() {
		return RunImpl.class;
	}
}
