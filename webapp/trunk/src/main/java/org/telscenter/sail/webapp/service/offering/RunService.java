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
package org.telscenter.sail.webapp.service.offering;

import java.util.List;

import org.acegisecurity.annotation.Secured;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.domain.impl.RunParameters;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.curnit.CurnitNotFoundException;
import net.sf.sail.webapp.service.offering.OfferingService;

/**
 * A service for working with <code>Run</code> objects
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public interface RunService extends OfferingService {

    /**
     * Creates a new <code>SdsOffering</code> on the SDS as well as an
     * <code>Run</code> object in the local data store. A side effect is
     * that the Run id is set to the value that the SDS assigns to the new
     * Run.
     * 
     * @param offeringParameters
     *            The object that encapsulate parameters for creating a run
     * @return the run created.
     */
//  @Secured( { "ROLE_TEACHER", "ROLE_ADMINISTRATOR", "ROLE_RESEARCHER", "ROLE_TA" })
	public RunImpl createRun(RunParameters runParameters) throws CurnitNotFoundException;
	
	/**
	 * Retrieves a list of <code>Run</code>
	 * 
	 * @return <code>List</code> of <code>Run</code>
	 */
    @Secured( { "ROLE_USER", "AFTER_ACL_COLLECTION_READ" })
    public List<Run> getRunList();
    
    /**
     * Retrieves a list of <code>Run</code> that the given user
     * is associated with
     * 
     * @param user <code>User</code> that is associated with 0 or more runs
     * @return list of <code>Run</code> that the user is associated with
     */
    public List<Run> getRunList(User user);
    
    /**
     * Checks if the runcode already exists in the database
     * 
     * @param runcode 
     *           The <code>String</code> to check for
     * @return <code>true</code> 
     */
    public boolean isRunCodeInDB(String runcode);
    
    /**
     * Retrieves the Run domain object using the unique runcode
     * 
     * @param runcode
     *          The <code>String</code> runcode to use for lookup
     * @return <code>Run</code>
     *          The Run object with the runcode
     * @throws <code>RunNotFoundException</code> when runcode cannot be used 
     *          to find an existing run    
     */
    public Run retrieveRunByRuncode(String runcode) throws RunNotFoundException;
    
    /**
     * Retrieves the Run domain object using a unique runId
     * 
     * @param runId 
     *         <code>Long</code> runId to use for lookup
     * @return <code>Run</code>
     *          The Run object with the runId
     * @throws <code>RunNotFoundException</code> when runId cannot be used 
     *          to find an existing run   
     */
    public Run retrieveById(Long runId) throws RunNotFoundException;
}
