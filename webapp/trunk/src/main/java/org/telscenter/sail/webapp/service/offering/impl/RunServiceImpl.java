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

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.offering.impl.OfferingServiceImpl;

import org.springframework.transaction.annotation.Transactional;
import org.telscenter.sail.webapp.dao.offering.RunDao;
import org.telscenter.sail.webapp.domain.impl.Run;
import org.telscenter.sail.webapp.domain.impl.RunParameters;
import org.telscenter.sail.webapp.service.offering.DuplicateRunCodeException;

/**
 * Services for WISE's Run Domain Object
 * 
 * @author Hiroki Terashima
 * @version $Id$
 */
public class RunServiceImpl extends OfferingServiceImpl {

	private static final String[] RUNCODE_WORDS = { "Tiger", "Cheetah", "Fox",
			"Owl", "Panda", "Jaguar", "Hawk", "Mole", "Falcon", "Orca",
			"Eagle", "Dolphin", "Otter", "Elephant", "Zebra", "Flea", "Wolf",
			"Dragon", "Kraken", "Cobra", "Ladybug", "Gecko", "Octopus",
			"Koala", "Tortoise", "Wombat", "Shark", "Whale", "Emu", "Sloth",
			"Slug", "Ant", "Mantis", "Bat", "Rhino", "Gator", "Monkey",
			"Diamond", "Ruby", "Topaz", "Sapphire", "Emerald", "Amber",
			"Garnet", "Moonstone", "Sunstone", "Opal", "Zircon", "Quartz" };

	private static final int MAX_RUNCODE_DIGIT = 10000;

	/**
	 * @see net.sf.sail.webapp.service.offering.OfferingService#getOfferingList()
	 */
	public List<Offering> getRunList() {
		return super.getOfferingList();
	}

	/**
	 * Generate a random runcode
	 * 
	 * @return the randomly generated runcode.
	 * 
	 */
	String generateRunCode() {
		Random rand = new Random();
		Integer digits = rand.nextInt(MAX_RUNCODE_DIGIT);
		StringBuffer sb = new StringBuffer(digits.toString());

		int max_runcode_digit_length = Integer.toString(MAX_RUNCODE_DIGIT)
				.length() - 1;
		while (sb.length() < max_runcode_digit_length) {
			sb.insert(0, "0");
		}

		String word = RUNCODE_WORDS[rand.nextInt(RUNCODE_WORDS.length)];
		String runCode = (word + sb.toString());
		return runCode;
	}

	/**
	 * Creates a run based on input parameters provided.
	 * 
	 * @param runParameters
	 * @return The run created.
	 * 
	 */
	@Transactional(rollbackFor = { BadRequestException.class,
			NetworkTransportException.class })
	public Run createRun(RunParameters runParameters) {

		String tempRunCode = generateRunCode();
		while (true) {
			try {
				checkForRunCodeError(tempRunCode);
			} catch (DuplicateRunCodeException e) {
				tempRunCode = generateRunCode();
				continue;
			}
			break;
		}
		runParameters.setRuncode(tempRunCode);
		runParameters.setStarttime(Calendar.getInstance().getTime());

		SdsOffering sdsOffering = new SdsOffering();
		sdsOffering.setName(runParameters.getName());
		sdsOffering.setSdsCurnit(runParameters.getCurnit().getSdsCurnit());
		sdsOffering.setSdsJnlp(runParameters.getJnlp().getSdsJnlp());
		this.sdsOfferingDao.save(sdsOffering);

		Run run = new Run();
		run.setSdsOffering(sdsOffering);
		run.setRuncode(runParameters.getRuncode());
		run.setEndtime(runParameters.getEndtime());
		run.setPeriods(runParameters.getPeriods());
		run.setStarttime(runParameters.getStarttime());
		this.offeringDao.save(run);
		return run;
	}

	/**
	 * Checks if the given runcode is unique.
	 * 
	 * @param runCode
	 *            A unique string.
	 * 
	 * @throws DuplicateRunCodeException
	 *             if the run's runcde already exists in the data store
	 */
	@SuppressWarnings("unchecked")
	private void checkForRunCodeError(String runCode)
			throws DuplicateRunCodeException {
		if (((RunDao) this.offeringDao).hasRuncode(runCode)) {
			throw new DuplicateRunCodeException("Runcode " + runCode
					+ " already exists.");
		}
	}

}
