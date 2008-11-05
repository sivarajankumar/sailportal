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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;

import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.presentation.util.NavStep;
import org.telscenter.sail.webapp.presentation.util.SdsLogUtil;
import org.telscenter.sail.webapp.presentation.util.SessionNavLog;
import org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog;
import org.telscenter.sail.webapp.service.grading.SessionBundleService;
import org.telscenter.sail.webapp.service.grading.impl.SessionBundleServiceImpl;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;
import org.telscenter.sail.webapp.service.workgroup.impl.WISEWorkgroupServiceImpl;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class WorkgroupNavLogImpl implements WorkgroupNavLog {

	private List<SessionNavLog> sessionNavLogs = new LinkedList<SessionNavLog>();
	
	private List<NavStep> collapsed = new LinkedList<NavStep>();
	
	private WISEWorkgroup workgroup;
	
	private static SessionBundleService sessionBundleService;

	private static WISEWorkgroupService workgroupService;
	
	private SdsLogUtil util = new SdsLogUtil();
	
	public WorkgroupNavLogImpl(){}
	
	public WorkgroupNavLogImpl(long runId, long workgroupId){
		try{
			workgroup = (WISEWorkgroup) this.workgroupService.retrieveById(workgroupId); 
			for(SessionBundle session : this.sessionBundleService.getSessionBundles(runId, workgroup)){
				sessionNavLogs.add(new SessionNavLogImpl(session, runId));
			}
		} catch(Exception e){
			//error
		}
		collapse();		
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getTotalTime()
	 */
	public int getTotalTime(){
		int total = 0;
		for(SessionNavLog session : sessionNavLogs){
			total = total + session.getTotalTime();
		}
		return total;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getAverageTimeSpentPerStep()
	 */
	public int getAverageTimeSpentPerStep(){
		int total = 0;
		for(NavStep step : collapsed){
			total = total + step.getDuration();
		}
		return total / getNumOfSteps();
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getAverageTimeSpentPerVisit()
	 */
	public int getAverageTimeSpentPerVisit(){
		return getTotalTime() / getNumOfVisits();
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getNumOfSteps()
	 */
	public int getNumOfSteps(){
		return collapsed.size();
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getNumOfVisits()
	 */
	public int getNumOfVisits(){
		int total = 0;
		for(SessionNavLog session : sessionNavLogs){
			total = total + session.getNavSteps().size();
		}
		return total;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getLongestTimeSpentStep()
	 */
	public NavStep getLongestTimeSpentStep(){
		NavStep longest = null;
		for(NavStep step : collapsed){
			if(longest==null || step.getDuration() > longest.getDuration()){
				longest = step;
			}
		}
		return longest;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getLongestTimeSpentVisit()
	 */
	public NavStep getLongestTimeSpentVisit(){
		NavStep longest = null;
		for(SessionNavLog session : this.sessionNavLogs){
			if(longest==null || session.getLongestTimeSpentStep().getDuration() > longest.getDuration()){
				longest = session.getLongestTimeSpentStep();
			}
		}
		return longest;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getLeastTimeSpentStep()
	 */
	public NavStep getLeastTimeSpentStep(){
		NavStep least = null;
		for(NavStep step : collapsed){
			if(least==null || step.getDuration() < least.getDuration()){
				least = step;
			}
		}
		return least;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getLeastTimeSpentVisit()
	 */
	public NavStep getLeastTimeSpentVisit(){
		NavStep least = null;
		for(SessionNavLog session : this.sessionNavLogs){
			if(least==null || session.getLeastTimeSpentStep().getDuration() < least.getDuration()){
				least = session.getLeastTimeSpentStep();
			}
		}
		return least;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getTimeStepMap()
	 */
	public Map<Integer,NavStep> getTimeStepMap(){
		Map<Integer,NavStep> timeStepMap = new TreeMap<Integer,NavStep>();
		int currentTime = 0;
		
		for(SessionNavLog session : this.sessionNavLogs){
			for(NavStep step : session.getNavSteps()){
				timeStepMap.put(currentTime, step);
				currentTime = currentTime + step.getDuration();
			}
		}
		
		return timeStepMap;
	}
	
	/**
	 * @see org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog#getTotalTimePerStepMap()
	 */
	public Map<String,Integer> getTotalTimePerStepMap(){
		Map<String,Integer> totalTimeMap = new TreeMap<String,Integer>();
		
		for(NavStep step : collapsed){
			totalTimeMap.put(util.getActivityStep(step.getStep()), util.seconds(step.getDuration()));
		}
		
		return totalTimeMap;
	}
		
	/**
	 * Conflates all equivalent NavSteps from all sessions (if more than
	 * one NavStep, it means the student has visited that step multiple times)
	 */
	private void collapse(){
		for(SessionNavLog session : sessionNavLogs){
			for(NavStep step : session.getNavSteps()){
				NavStep adjust = exists(step);
				if(adjust==null){
					collapsed.add(copyNavStep(step));
				} else {
					adjust.setClose(adjust.getClose() +  step.getDuration());
				}
			}
		}
	}
	
	/**
	 * Given a <code>NavStep</code> searches this.collapsed for an already
	 * existing step. If it is found, returns that NavStep, if not, returns null
	 * 
	 * @param <code>NavStep</code> outerStep
	 * @return <code>NavStep</code>
	 */
	private NavStep exists(NavStep outerStep){
		for(NavStep innerStep : collapsed){
			if(util.uniqueOrderedNum(outerStep.getStep()).equals(util.uniqueOrderedNum(innerStep.getStep()))){
				return innerStep;
			}
		}
		return null;
	}
	
	/**
	 * Given a <code>NavStep</code> creates and returns a new NavStep with the same info
	 * 
	 * @param <code>NavStep</code> step
	 * @return <code>NavStep</code>
	 */
	private NavStep copyNavStep(NavStep step){
		NavStep newStep = new NavStep();
		newStep.setClose(step.getClose());
		newStep.setOpen(step.getOpen());
		newStep.setStep(step.getStep());
		return newStep;
	}

	/**
	 * @param sessionBundleService the sessionBundleService to set
	 */
	public void setSessionBundleService(SessionBundleService sessionBundleService) {
		this.sessionBundleService = sessionBundleService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}
