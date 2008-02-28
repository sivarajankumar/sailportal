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

import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.owned.Owned;
import org.telscenter.sail.webapp.domain.owned.impl.OwnedImpl;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeComment;
import org.telscenter.sail.webapp.domain.premadecomment.PremadeCommentList;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.impl.ProjectImpl;

/**
 * 
 * @author patrick lawler
 */

@Entity
@Table(name = PremadeCommentListImpl.DATA_STORE_NAME)
public class PremadeCommentListImpl implements PremadeCommentList, Comparable {
	
    @Transient
    public static final String DATA_STORE_NAME = "premadecommentlists";

    @Transient
    public static final String COLUMN_NAME_LABEL = "label";
    
    @Transient
    public static final String COLUMN_NAME_RUN = "run";
    
    @Transient
    public static final String COLUMN_NAME_OWNER = "owner";
    
    @Transient
    public static final long serialVersionUID = 1L;

	private static final String PREMADECOMMENTS_JOIN_TABLE = "premadecomments_related_to_premadecommentlists";
		
	private static final String PREMADECOMMENTSLIST_JOIN_COLUMN_NAME = "premadecommentslist_fk";

	private static final String PREMADECOMMENTS_JOIN_COLUMN_NAME = "premadecomments_fk";
    
    @ManyToMany(targetEntity = PremadeCommentImpl.class, fetch=FetchType.EAGER)
    @JoinTable(name = PREMADECOMMENTS_JOIN_TABLE, joinColumns = {@JoinColumn(name = PREMADECOMMENTSLIST_JOIN_COLUMN_NAME, nullable = false)}, inverseJoinColumns = @JoinColumn(name = PREMADECOMMENTS_JOIN_COLUMN_NAME, nullable=false))
    private Set<PremadeComment> list;

    @Column(name = PremadeCommentImpl.COLUMN_NAME_LABEL, nullable=false)
    private String label;
    
    @Column(name = PremadeCommentImpl.COLUMN_NAME_OWNER, nullable = true)
    private User owner = null;

    @Column(name = PremadeCommentImpl.COLUMN_NAME_RUN, nullable = true)
    private Run run = null;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    
	/**
	 * @return the list of Premade Comments
	 */
	public Set<PremadeComment> getPremadeCommentList() {
		return list;
	}

	/**
	 * @param premadeCommentList the PremadeComment list to set
	 */
	public void setPremadeCommentList(Set<PremadeComment> premadeCommentList) {
		this.list = premadeCommentList;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the run
	 */
	public Run getRun() {
		return run;
	}

	/**
	 * @param run the run to set
	 */
	public void setRun(Run run) {
		this.run = run;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public int compareTo(Object premadeCommentList){
		return this.getLabel().compareTo(((PremadeCommentListImpl) premadeCommentList).getLabel());
	}
}
