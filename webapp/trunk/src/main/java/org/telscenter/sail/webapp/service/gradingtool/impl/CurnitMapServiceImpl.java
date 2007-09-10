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

package org.telscenter.sail.webapp.service.gradingtool.impl;

import org.telscenter.sail.webapp.domain.gradingtool.CurnitMap;
import org.telscenter.sail.webapp.domain.gradingtool.PasActivity;
import org.telscenter.sail.webapp.domain.gradingtool.PasProject;
import org.telscenter.sail.webapp.domain.gradingtool.PasStep;
import org.telscenter.sail.webapp.service.gradingtool.CurnitMapService;
/**
 * Implementation of the curnit map service
 * 
 * @author Anthony Perritano
 */
public class CurnitMapServiceImpl implements CurnitMapService {

	public CurnitMap getCurnitMapByRunId(String runId) {
		
	CurnitMap curnitMap = new CurnitMap();
		
		PasProject p = new PasProject();
		p.setId("1");
		p.setTitle("Birds of a feather flock together");
		
		PasActivity act1 = new PasActivity();
		act1.setId("2");
		act1.setTitle("Souring into Evolution");
		
		PasStep s1 = new PasStep();
		s1.setId("4");
		s1.setTitle("Introduction");
		
		PasStep s2 = new PasStep();
		s2.setId("5");
		s2.setTitle("Thinking about bird evolution");
				
		PasActivity act2 = new PasActivity();
		act2.setId("7");
		act2.setTitle("Darwianian evolution");
		
		PasStep s4 = new PasStep();
		s4.setId("8");
		s4.setTitle("Darwins Approach");
		
		PasStep s5 = new PasStep();
		s5.setId("9");
		s5.setTitle("Your ideas");
		
		act1.addStep(s1);
		act1.addStep(s2);
		act2.addStep(s4);
		act2.addStep(s5);
		
		p.addActivity(act1);
		p.addActivity(act2);
		
		curnitMap.setProject(p);
		
		return curnitMap;
	}
	
}
