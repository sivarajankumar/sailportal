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
package org.telscenter.sail.webapp.service.premadecomment;

import java.util.Set;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.PremadeCommentListParameters;
import org.telscenter.sail.webapp.domain.impl.PremadeCommentParameters;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeComment;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeCommentList;

import net.sf.sail.webapp.domain.User;

/**
 * A service for working with <code>PremadeComment</code>
 * and <code>PremadeCommentList</code> objects.
 * 
 * @author patrick lawler
 *
 */
public interface PremadeCommentService {

	/**
	 * Creates a new PremadeComment in the data store.
	 * 
	 * @param params <code>PremadeCommentParameters</code>
	 * @return PremadeComment
	 */
	public PremadeComment createPremadeComment(PremadeCommentParameters params);
	
	/**
	 * Removes a PremadeComment from the data store using its id.
	 * 
	 * @param commentID <code>Long</code>
	 */
	public void deletePremadeComment(Long commentID);
	
	/**
	 * Updates the comment in a PremadeComment using its id.
	 * 
	 * @param premadeCommentID <code>Long</code>
	 * @param newComment <code>String</code>
	 * @return updated PremadeComment
	 */
	public PremadeComment updatePremadeCommentMessage(Long premadeCommentID, String newComment);
	
	/**
	 * Updates the label for a PremadeComment using its id
	 * and new label.
	 * 
	 * @param commentID <code>Long</code>
	 * @param newLabel <code>String</code>
	 * @return updated PremadeComment
	 */
	public PremadeComment updatePremadeCommentLabel(Long premadeCommentID, String newLabel);
	
	/**
	 * Retrieves all PremadeComments from the data store.
	 * 
	 * @return a Set<PremadeComment>
	 */	
	public Set<PremadeComment> retrieveAllPremadeComments();
	
	/**
	 * Retrieves all PremadeComments associated with a given user.
	 * 
	 * @param user <code>User</code>
	 * @return a Set<PremadeComment>
	 */
	public Set<PremadeComment> retrieveAllPremadeCommentsByUser(User user);
	
	/**
	 * Retrieves all PremadeComments associated with a given run.
	 * 
	 * @param run <code>Run</code>
	 * @return a Set<PremadeComment>
	 */
	public Set<PremadeComment> retrieveAllPremadeCommentsByRun(Run run);
	
	/**
	 * Creates a new PremadeCommentList in the data store.
	 * 
	 * @param params <code>PremadeCommentListParameters</code>
	 * @return PremadeCommentList
	 */
	public PremadeCommentList createPremadeCommentList(PremadeCommentListParameters params);
	
	/**
	 * Removes a PremadeCommentList from the data store given its ID
	 * 
	 * @param commentListID <code>Long</code>
	 */
	public void deletePremadeCommentList(Long commentListID);
	
	/**
	 * Updates the label of a PremadeCommentList given its ID
	 * and new label.
	 * 
	 * @param commentListID <code>Long</code>
	 * @param newLabel <code>String</code>
	 * @return PremadeCommentList
	 */
	public PremadeCommentList updatePremadeCommentListLabel(Long commentListID, String newLabel);
	
	/**
	 * Adds a PremadeComment to the PremadeCommentList given
	 * the PremadeComment and the PremadeCommentList ID
	 * 
	 * @param commentListID <code>Long</code>
	 * @param premadeComment <code>PremadeComment</code>
	 * @return PremadeCommentList
	 */
	public PremadeCommentList addPremadeCommentToList(Long commentListID, PremadeComment premadeComment);
	
	/**
	 * Removes a PremadeComment from the list of PremadeCommentList
	 * given the PremadeCommentList ID and the PremadeComment
	 * 
	 * @param commentListID <code>Long</code>
	 * @param PremadeComment <code>PremadeComment</code>
	 * @return PremadeCommentList
	 */
	public PremadeCommentList removePremadeCommentFromList(Long commentID, PremadeComment premadeComment);
	
	/**
	 * Retrieves all PremadeCommentLists from the data store.
	 * 
	 * @return a Set<PremadeCommentList>
	 */	
	public Set<PremadeCommentList> retrieveAllPremadeCommentLists();
	
	/**
	 * Retrieves all PremadeCommentLists associated with a given user.
	 * 
	 * @param user <code>User</code>
	 * @return a Set<PremadeCommentList>
	 */
	public Set<PremadeCommentList> retrieveAllPremadeCommentListsByUser(User user);
	
	/**
	 * Retrieves all PremadeCommentLists associated with a given run.
	 * 
	 * @param run <code>Run</code>
	 * @return a Set<PremadeCommentList>
	 */
	public Set<PremadeCommentList> retrieveAllPremadeCommentListsByRun(Run run);
}