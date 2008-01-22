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
package org.telscenter.sail.webapp.domain.premadecomment.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.telscenter.sail.webapp.domain.owned.impl.OwnedImpl;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeComment;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeCommentList;

/**
 * 
 * @author patrick lawler
 */

@Entity
@Table(name = PremadeCommentListImpl.DATA_STORE_NAME)
public class PremadeCommentListImpl extends OwnedImpl implements PremadeCommentList{
	
    @Transient
    public static final String DATA_STORE_NAME = "premadecommentlists";

    @Transient
    public static final String COLUMN_NAME_LIST = "list";
    
    @Transient
    public static final long serialVersionUID = 1L;
    
    @Column(name = PremadeCommentListImpl.COLUMN_NAME_LIST, nullable = false)
    private List<PremadeComment> list;

	/**
	 * @return the list of Premade Comments
	 */
	public List<PremadeComment> getPremadeCommentList() {
		return list;
	}

	/**
	 * @param premadeCommentList the PremadeComment list to set
	 */
	public void setPremadeCommentList(List<PremadeComment> premadeCommentList) {
		this.list = premadeCommentList;
	}
}
