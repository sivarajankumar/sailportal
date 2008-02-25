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

package org.telscenter.sail.webapp.service.premadecomment.impl;

import org.telscenter.sail.webapp.dao.premadecomment.impl.HibernatePremadeCommentDao;
import org.telscenter.sail.webapp.dao.premadecomment.impl.HibernatePremadeCommentListDao;

import junit.framework.TestCase;

/**
 * 
 * @author patrick lawler
 */
public class PremadeCommentServiceImplTest extends TestCase{
	
	private HibernatePremadeCommentDao premadeCommentDao;
	
	private HibernatePremadeCommentListDao premadeCommentListDao;
	
	@Override
	protected void setUp(){
		
	}
	
	@Override
	protected void tearDown(){
		
	}

	/**
	 * @return the premadeCommentDao
	 */
	public HibernatePremadeCommentDao getPremadeCommentDao() {
		return premadeCommentDao;
	}

	/**
	 * @param premadeCommentDao the premadeCommentDao to set
	 */
	public void setPremadeCommentDao(HibernatePremadeCommentDao premadeCommentDao) {
		this.premadeCommentDao = premadeCommentDao;
	}

	/**
	 * @return the premadeCommentListDao
	 */
	public HibernatePremadeCommentListDao getPremadeCommentListDao() {
		return premadeCommentListDao;
	}

	/**
	 * @param premadeCommentListDao the premadeCommentListDao to set
	 */
	public void setPremadeCommentListDao(
			HibernatePremadeCommentListDao premadeCommentListDao) {
		this.premadeCommentListDao = premadeCommentListDao;
	}
	
}
