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
package org.telscenter.sail.webapp.presentation.util.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.presentation.util.NavStep;
import org.telscenter.sail.webapp.presentation.util.SdsLogUtil;
import org.telscenter.sail.webapp.presentation.util.SessionNavLog;

import net.sf.sail.emf.sailuserdata.ESockEntry;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;

/**
 * A representation of a specific SessionBundle's navigation log
 * 
 * @author patrick lawler
 * @version $Id:$
 */
public class SessionNavLogImpl implements SessionNavLog{
	
	private Set<NavStep> navSteps = new TreeSet<NavStep>();
	
	private long runId;
	
	private int openOffset;
	
	private SdsLogUtil util = new SdsLogUtil();
	
	public SessionNavLogImpl(SessionBundle session, long runId){
		this.runId = runId;
		try{
			createNavSteps(util.getNavLogs(session));
		}catch(Exception e){
			System.out.println(e);
			//error
		}
	}
	
	public SessionNavLogImpl(){}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.SessionNavLog#getNavSteps()
	 */
	public Set<NavStep> getNavSteps(){
		return this.navSteps;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.SessionNavLog#getTotalTime()
	 */
	public int getTotalTime(){
		int total = 0;
		for(NavStep step : this.navSteps){
			total = total + step.getDuration();
		}
		return total;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.SessionNavLog#getAverageTimeSpentPerStep()
	 */
	public int getAverageTimeSpentPerStep(){
		return this.getTotalTime() / this.navSteps.size();
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.SessionNavLog#getLongestTimeSpentStep
	 */
	public NavStep getLongestTimeSpentStep(){
		NavStep longest = null;
		for(NavStep step : this.navSteps){
			if(longest==null || step.getDuration()>longest.getDuration()){
				longest = step;
			}
		}
		return longest;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.SessionNavLog#getLeastTimeSpentStep
	 */
	public NavStep getLeastTimeSpentStep(){
		NavStep shortest = null;
		for(NavStep step : this.navSteps){
			if(shortest==null || step.getDuration() < shortest.getDuration()){
				shortest = step;
			}
		}
		return shortest;
	}
	

	/**
	 * Given a <code>List<ESockEntry></code> creates and populates 
	 * <code>Set<NavStep></code>
	 * 
	 * @param <code>List<ESockEntry></code> navLogs
	 * @throws Exception
	 */
	private void createNavSteps(List<ESockEntry> navLogs) throws Exception{
		for(ESockEntry entry : navLogs){
			String activityType = util.activityType(entry.getValue());
			if(activityType.equals("project_open")){
				this.openOffset = entry.getMillisecondsOffset();
				NavStep navStep = new NavStep();
				navStep.setOpen(entry.getMillisecondsOffset() - openOffset);
				navStep.setStep(null);
				this.navSteps.add(navStep);
			} else if(activityType.equals("step_open")){
				NavStep navStep = new NavStep();
				navStep.setOpen(entry.getMillisecondsOffset() - openOffset);
				navStep.setStep(util.getStepByPodUUID(util.podUUID(entry.getValue()), runId));
				this.navSteps.add(navStep);
			} else if(activityType.equals("step_close")){
				String entryUUID = util.podUUID(entry.getValue());
				if(entryUUID.equals("null")){
					for(NavStep step : this.navSteps){
						if(step.getStep()==null){
							this.navSteps.remove(step);
						}
					}
				} else {
					for(NavStep step : this.navSteps){
						if(step.getStep()==null){
							step.setClose(entry.getMillisecondsOffset() - openOffset);
							step.setStep(util.getStepByPodUUID(entryUUID, runId));
							break;
						} else if(step.getStep().getPodUUID().toString().equals(entryUUID)){
							if(!step.isClosed()){
								step.setClose(entry.getMillisecondsOffset() - openOffset);
								break;
							}
						}
					}
				}
			} else if(activityType.equals("project_close")){
				for(NavStep step : this.navSteps){
					if(!step.isClosed()){
						step.setClose(entry.getMillisecondsOffset() - openOffset);
					}
				}
			} else {
				//error
			}
		}
	}
}
