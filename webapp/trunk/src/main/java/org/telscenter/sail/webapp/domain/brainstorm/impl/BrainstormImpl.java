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
package org.telscenter.sail.webapp.domain.brainstorm.impl;

import java.util.Date;
import java.util.Map;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.AnswerImpl;
import org.telscenter.sail.webapp.domain.brainstorm.question.Question;
import org.telscenter.sail.webapp.domain.brainstorm.question.impl.QuestionImpl;
import org.telscenter.sail.webapp.domain.impl.RunImpl;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;

/**
 * Simple brainstorm implementation.
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = BrainstormImpl.DATA_STORE_NAME)
public class BrainstormImpl implements Brainstorm {

	@Transient
	private static final long serialVersionUID = 1L;

    @Transient
    public static final String DATA_STORE_NAME = "brainstorms";
    
    @Transient
    public static final String ANSWERS_JOIN_TABLE_NAME = "brainstorms_related_to_brainstormanswers";
    
    @Transient
    public static final String BRAINSTORMS_JOIN_COLUMN_NAME = "brainstorms_fk";

    @Transient
    public static final String ANSWERS_JOIN_COLUMN_NAME = "brainstormanswers_fk";
    
    @Transient
    public static final String QUESTIONS_JOIN_COLUMN_NAME = "brainstormquestions_fk";
    
    @Transient
    public static final String COLUMN_NAME_RUN_FK = "runs_fk";

    @OneToMany(targetEntity = AnswerImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = ANSWERS_JOIN_TABLE_NAME, joinColumns = { @JoinColumn(name = BRAINSTORMS_JOIN_COLUMN_NAME, nullable = false) }, inverseJoinColumns = @JoinColumn(name = ANSWERS_JOIN_COLUMN_NAME, nullable = false))
	private Set<Answer> answers = new TreeSet<Answer>();
	
    @OneToOne(targetEntity = QuestionImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = QUESTIONS_JOIN_COLUMN_NAME)
	private Question question;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = RunImpl.class)
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    @JoinColumn(name = COLUMN_NAME_RUN_FK, nullable = false, unique = false)
	private Run run;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Version
    @Column(name = "OPTLOCK")
    private Integer version = null;
	
	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#getAnswers()
	 */
	public Set<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#getQuestion()
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#getRun()
	 */
	public Run getRun() {
		return run;
	}

	 /**
     * @return the id
     */
    public Long getId() {
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
     * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#setRun(org.telscenter.sail.webapp.domain.Run)
     */
	public void setRun(Run run) {
		this.run = run;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#setAnswers(java.util.Set)
	 */
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#addAnswer(org.telscenter.sail.webapp.domain.brainstorm.answer.Answer)
	 */
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.Brainstorm#setQuestion(org.telscenter.sail.webapp.domain.brainstorm.question.Question)
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	public Map<WISEWorkgroup, Date> getWorkgroupLastVisitedMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<WISEWorkgroup> getWorkgroupsThatRequestHelp() {
		// TODO Auto-generated method stub
		return null;
	}

}