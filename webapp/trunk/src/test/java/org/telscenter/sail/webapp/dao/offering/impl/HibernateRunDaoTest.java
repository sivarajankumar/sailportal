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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.sail.webapp.dao.offering.impl.HibernateOfferingDao;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.group.impl.PersistentGroup;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.domain.sds.SdsJnlp;
import net.sf.sail.webapp.domain.sds.SdsOffering;

import org.hibernate.Session;
import org.telscenter.sail.webapp.domain.impl.Run;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test class for HibernateOfferingDao using Run Domain Object
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class HibernateRunDaoTest extends AbstractTransactionalDbTests {

    private static final Integer SDS_ID = new Integer(7);

    private static final String DEFAULT_NAME = "Airbags";
    
    private static final String DEFAULT_URL = "http://mrpotatoiscoolerthanwoody.com";

    private static final SdsCurnit DEFAULT_SDS_CURNIT = new SdsCurnit();

    private static final SdsJnlp DEFAULT_SDS_JNLP = new SdsJnlp();
	
	private Group DEFAULT_GROUP_1, DEFAULT_GROUP_2, DEFAULT_GROUP_3;

	private final Date DEFAULT_STARTTIME = Calendar.getInstance().getTime();

	private Date DEFAULT_ENDTIME = null;

	private final String DEFAULT_RUNCODE = "diamonds12345";

	private SdsOffering sdsOffering;
	
	private Run defaultRun;
	
	private HibernateOfferingDao offeringDao;

	/**
	 * @param sdsOffering the sdsOffering to set
	 */
    public void setSdsOffering(SdsOffering sdsOffering) {
        this.sdsOffering = sdsOffering;
    }
    
	/**
	 * @param defaultRun the defaultRun to set
	 */
	public void setDefaultRun(Run defaultRun) {
		this.defaultRun = defaultRun;
	}

	/**
	 * @param offeringDao the offeringDao to set
	 */
	public void setOfferingDao(HibernateOfferingDao offeringDao) {
		this.offeringDao = offeringDao;
	}
	
    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
     */
    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        
        DEFAULT_GROUP_1 = new PersistentGroup();
        DEFAULT_GROUP_1.setName("Period 1");

        DEFAULT_GROUP_2 = new PersistentGroup();
        DEFAULT_GROUP_2.setName("Period 2");

        DEFAULT_GROUP_3 = new PersistentGroup();
        DEFAULT_GROUP_3.setName("Period 3");

        DEFAULT_SDS_CURNIT.setSdsObjectId(SDS_ID);
        DEFAULT_SDS_CURNIT.setName(DEFAULT_NAME);
        DEFAULT_SDS_CURNIT.setUrl(DEFAULT_URL);

        DEFAULT_SDS_JNLP.setSdsObjectId(SDS_ID);
        DEFAULT_SDS_JNLP.setName(DEFAULT_NAME);
        DEFAULT_SDS_JNLP.setUrl(DEFAULT_URL);

        this.sdsOffering.setSdsObjectId(SDS_ID);
        this.sdsOffering.setName(DEFAULT_NAME);

        this.defaultRun.setSdsOffering(this.sdsOffering);
        
        this.defaultRun.setStarttime(DEFAULT_STARTTIME);
        this.defaultRun.setRuncode(DEFAULT_RUNCODE);
    }
    
    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpInTransaction()
     */
    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        Session session = this.sessionFactory.getCurrentSession();
        session.save(DEFAULT_SDS_CURNIT); // save sds curnit
        session.save(DEFAULT_SDS_JNLP); // save sds jnlp
        session.save(DEFAULT_GROUP_1); 
        session.save(DEFAULT_GROUP_2); 
        session.save(DEFAULT_GROUP_3); 

        this.sdsOffering.setSdsCurnit(DEFAULT_SDS_CURNIT);
        this.sdsOffering.setSdsJnlp(DEFAULT_SDS_JNLP);
    }
    

    /**
     * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
     */
    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        super.onTearDownAfterTransaction();
    }
    
    public void testSave() {
    	verifyRunAndJoinTablesAreEmpty();
    	
    	this.offeringDao.save(this.defaultRun);
        // flush is required to cascade the join table for some reason
        this.toilet.flush();
        
        List runsList = retrieveRunListFromDb();
        assertEquals(1, runsList.size());
        assertEquals(0, retrieveRunsRelatedToGroupsListFromDb().size());
        assertEquals(0, retrieveRunsAndGroupsListFromDb().size());
        
        Map runMap = (Map) runsList.get(0);
        assertEquals(this.DEFAULT_STARTTIME,
        		runMap.get(Run.COLUMN_NAME_STARTTIME.toUpperCase()));
        assertEquals(this.DEFAULT_RUNCODE,
        		runMap.get(Run.COLUMN_NAME_RUN_CODE.toUpperCase()));
        assertNull(runMap.get(Run.COLUMN_NAME_ENDTIME.toUpperCase()));
        
        // now add groups to the run
        Set<Group> periods = new HashSet<Group>();
        periods.add(DEFAULT_GROUP_1);
        periods.add(DEFAULT_GROUP_2);
        this.defaultRun.setPeriods(periods);

    	this.offeringDao.save(this.defaultRun);
        // flush is required to cascade the join table for some reason
        this.toilet.flush();

        runsList = retrieveRunListFromDb();
        List allList = retrieveRunsAndGroupsListFromDb();
        assertEquals(1, runsList.size());
        assertEquals(2, retrieveRunsRelatedToGroupsListFromDb().size());
        assertEquals(2, allList.size());

        List<String> periodNames = new ArrayList<String>();
        periodNames.add(DEFAULT_GROUP_1.getName());
        periodNames.add(DEFAULT_GROUP_2.getName());
        
        for (int i = 0; i < allList.size(); i++) {
        	Map allRunMap = (Map) allList.get(i);
        	String periodName =
        		(String) allRunMap.get(PersistentGroup.COLUMN_NAME_NAME);
        	assertTrue(periodNames.contains(periodName));
        	periodNames.remove(periodName);
        }
        
        
        // now "end/archive the run"
        this.DEFAULT_ENDTIME = Calendar.getInstance().getTime();
        this.defaultRun.setEndtime(this.DEFAULT_ENDTIME);
        
    	this.offeringDao.save(this.defaultRun);
        // flush is required to cascade the join table for some reason
        this.toilet.flush();
        
        runsList = retrieveRunListFromDb();
        runMap = (Map) runsList.get(0);
        assertEquals(this.DEFAULT_ENDTIME,
        		runMap.get(Run.COLUMN_NAME_ENDTIME.toUpperCase()));

    }

	private void verifyRunAndJoinTablesAreEmpty() {
		assertTrue(this.retrieveRunListFromDb().isEmpty());
		assertTrue(this.retrieveRunsRelatedToGroupsListFromDb().isEmpty());
	}

	/*
	 * SELECT * FROM runs_related_to_groups
	 */
	private List retrieveRunsRelatedToGroupsListFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM " +
				Run.PERIODS_JOIN_TABLE_NAME);
	}

	/*
	 * SELECT * FROM runs
	 */
	private List retrieveRunListFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM " +
				Run.DATA_STORE_NAME, (Object[]) null);
	}
	
	/*
	 * SELECT * FROM runs, runs_related_to_groups, groups
	 * WHERE runs.id = runs_related_to_groups.run_fk
	 * AND groups.id = runs_related_to_groups.group_fk
	 */
	private List retrieveRunsAndGroupsListFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM " +
				Run.DATA_STORE_NAME + ", " + Run.PERIODS_JOIN_TABLE_NAME +
				", " + PersistentGroup.DATA_STORE_NAME + " WHERE " +
				Run.DATA_STORE_NAME + ".id = " + 
				Run.PERIODS_JOIN_TABLE_NAME + "." + Run.RUNS_JOIN_COLUMN_NAME +
				" AND " + PersistentGroup.DATA_STORE_NAME + ".id = " +
				Run.PERIODS_JOIN_TABLE_NAME + "." + Run.PERIODS_JOIN_COLUMN_NAME, (Object []) null);
	}
}
