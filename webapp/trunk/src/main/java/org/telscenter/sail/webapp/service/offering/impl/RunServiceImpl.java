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
package org.telscenter.sail.webapp.service.offering.impl;

import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.dao.offering.RunDao;
import org.telscenter.sail.webapp.domain.impl.Run;
import org.telscenter.sail.webapp.service.offering.DuplicateRunCodeException;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.offering.impl.OfferingServiceImpl;

/**
 * Services for WISE's Run Domain Object
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class RunServiceImpl extends OfferingServiceImpl {
        
    private static final String[] RUNCODE_WORDS = { 
    	"Tiger", "Cheetah", "Fox", "Owl", "Panda",
    	"Jaguar", "Hawk", "Mole", "Falcon", "Orca",
    	"Eagle", "Dolphin", "Otter", "Elephant", "Zebra",
    	"Flea", "Wolf", "Dragon", "Kraken", "Cobra",
    	"Ladybug", "Gecko", "Octopus", "Koala", "Tortoise",
    	"Wombat", "Shark", "Whale", "Emu", "Sloth",
    	"Slug", "Ant", "Mantis", "Bat", "Rhino",
    	"Gator", "Monkey", "Diamond", "Ruby", "Topaz",
    	"Sapphire", "Emerald", "Amber", "Garnet", "Moonstone",
    	"Sunstone", "Opal", "Zircon", "Quartz"
    };

	private static final int MAX_RUNCODE_DIGIT = 10000;

	/**
     * @see net.sf.sail.webapp.service.offering.OfferingService#getOfferingList()
     */
    public List<Offering> getRunList() {
        return super.getOfferingList();
    }

    /**
     * Assign a new randomly-generated runcode to this run
     * 
     * @param run 
     *     <code>Run</code> to modify its runcode
     */
    public void assignNewRunCodeToRun(Run run) {
    	Random rand = new Random();
    	Integer digits = rand.nextInt(MAX_RUNCODE_DIGIT);
    	StringBuffer sb = new StringBuffer(digits.toString());

    	int max_runcode_digit_length = Integer.toString(MAX_RUNCODE_DIGIT).length() - 1;
    	while (sb.length() < max_runcode_digit_length) {
    		sb.insert(0, "0");
    	}
    	
    	String word = RUNCODE_WORDS[rand.nextInt(RUNCODE_WORDS.length)];
    	System.out.println(word + sb.toString());
    	run.setRuncode(word + sb.toString());
    }
    
    /**
     * @see net.sf.sail.webapp.service.offering.OfferingService#createOffering(Offering)
     */
    @Transactional(rollbackFor = { BadRequestException.class,
            NetworkTransportException.class })
	public void createRun(Run run) {
    	assert(run.getRuncode() == null) : "RunCode already assigned";
    	
    	assignNewRunCodeToRun(run);
    	while(true) {
    		try {
    			checkRunCreationErrors(run);
    		} catch (DuplicateRunCodeException e) {
    			assignNewRunCodeToRun(run);
    			continue;
    		}
    		break;
    	}
		super.createOffering(run);
	}
    
    /**
     * Checks if the given run's runcode is unique
     * 
     * @param run <code>Run</code> to check the runcode for uniqueness
     * @throws DuplicateRunCodeException if the run's runcde already exists
     *    in the data store
     */
    @SuppressWarnings("unchecked")
	private void checkRunCreationErrors(Run run) 
           throws DuplicateRunCodeException {
    	if ( ((RunDao) this.offeringDao).hasRuncode(run.getRuncode())) {
    		throw new DuplicateRunCodeException("Runcode " + run.getRuncode() + " already exists.");
    	}
    }

}
