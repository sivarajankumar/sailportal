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
package org.telscenter.sail.webapp.dao.offering;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.offering.OfferingDao;

import org.telscenter.sail.webapp.domain.Run;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public interface RunDao<T extends Run> extends OfferingDao<Run> {

    /**
	 * Given an input string retrieve a corresponding record from data store.
	 * 
	 * @param runcode
	 *            <code>String</code> representing the runcode of the data in
	 *            the data store.
	 * @return A new instance of a data object.
	 * @throws ObjectNotFoundException if Run is not found.
	 */
	public Run retrieveByRunCode(String runcode) throws ObjectNotFoundException;
//TODO LAW remove	
//    /**
//	 * Checks if the given runcode is already being used
//	 * 
//	 * @param runcode
//	 *            <code>String</code> the runcode to check
//	 * @return true iff the given runcode exists in the data store
//	 */
//	public boolean hasRuncode(String runcode);
}
