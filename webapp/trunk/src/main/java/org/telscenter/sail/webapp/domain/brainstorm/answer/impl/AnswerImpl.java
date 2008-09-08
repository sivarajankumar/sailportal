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
package org.telscenter.sail.webapp.domain.brainstorm.answer.impl;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Revision;
import org.telscenter.sail.webapp.domain.brainstorm.comment.Comment;
import org.telscenter.sail.webapp.domain.brainstorm.comment.impl.CommentImpl;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.domain.workgroup.impl.WISEWorkgroupImpl;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity(name = AnswerImpl.DATA_STORE_NAME)
public class AnswerImpl implements Answer {
	
    @Transient
    public static final String DATA_STORE_NAME = "brainstormanswers";

    @Transient
    private static final long serialVersionUID = 1L;
    
    @Transient
    public static final String WORKGROUPS_JOIN_COLUMN_NAME = "workgroups_fk";

    @Transient
	private static final String COMMENTS_JOIN_TABLE_NAME = "brainstormanswers_related_to_brainstormcomments";

    @Transient
	private static final String ANSWERS_JOIN_COLUMN_NAME = "brainstormanswers_fk";

    @Transient
	private static final String COMMENTS_JOIN_COLUMN_NAME = "brainstormcomments_fk";
    
    @ManyToOne(targetEntity = WISEWorkgroupImpl.class, cascade = CascadeType.ALL)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    @JoinColumn(name = WORKGROUPS_JOIN_COLUMN_NAME, nullable = false, unique = false)
    private WISEWorkgroup workgroup;
    
    @OneToMany(targetEntity = CommentImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = COMMENTS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = ANSWERS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = COMMENTS_JOIN_COLUMN_NAME, nullable = false))
    @Sort(type = SortType.NATURAL)
    private Set<Comment> comments = new TreeSet<Comment>();
   
    //private Set<>
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    
    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;
    
	/**
	 * @see net.sf.sail.webapp.domain.Persistable#getId()
	 */
	public Serializable getId() {
		return id;
	}
	
    /**
     * @param id
     *            the id to set
     */
    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the version
     */
    @SuppressWarnings("unused")
    private Integer getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    @SuppressWarnings("unused")
    private void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @see org.telscenter.sail.webapp.domain.brainstorm.answer.Answer#getWorkgroup()
     */
	public WISEWorkgroup getWorkgroup() {
		return workgroup;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public Set<Revision> getRevisions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<WISEWorkgroup> getWorkgroupsThatFoundThisAnswerHelpful() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAnnonymous() {
		return false;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void addRevision(Revision revision) {
		
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((workgroup == null) ? 0 : workgroup.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnswerImpl other = (AnswerImpl) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (workgroup == null) {
			if (other.workgroup != null)
				return false;
		} else if (!workgroup.equals(other.workgroup))
			return false;
		return true;
	}

	public int compareTo(Answer o) {
		if (this.getComments() == null &&
				o.getComments() != null) {
			return 1;
		}
		if (this.getComments() != null &&
				o.getComments() == null) {
			return -1;
		}
		if (this.getComments() == null &&
				o.getComments() == null) {
			return 0;
		}
		if (this.getComments() != null &&
				o.getComments() != null) {
			return 0;
		}
		return 0;
	}

}
