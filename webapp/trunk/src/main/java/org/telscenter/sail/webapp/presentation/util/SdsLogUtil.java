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
package org.telscenter.sail.webapp.presentation.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ERim;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.grading.SessionBundleService;
import org.telscenter.sail.webapp.service.grading.impl.GradingServiceImpl;
import org.telscenter.sail.webapp.service.grading.impl.SessionBundleServiceImpl;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;
import org.telscenter.sail.webapp.service.workgroup.impl.WISEWorkgroupServiceImpl;

import net.sf.sail.emf.sailuserdata.ESockEntry;
import net.sf.sail.emf.sailuserdata.ESockPart;
import net.sf.sail.webapp.domain.sessionbundle.SessionBundle;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class SdsLogUtil {

	private static GradingService gradingService;
	
	private static SessionBundleService sessionBundleService;
	
	private static WISEWorkgroupService workgroupService;
	
	/**
	 * Given a <code>Long</code> runId, returns the number of Activities
	 * for that run
	 * 
	 * @param <code>long</code> runId of the run
	 * @return <code>int</code> the number of activities
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public int numOfActivities(long runId) throws Exception{
		return this.gradingService.getCurnitmap(runId).getProject().getActivity().size();
	}
	
	/**
	 * Given a <code>long</code> runId, returns a List of EActivities
	 * for that run
	 * 
	 * @param <code>long</code> runId
	 * @return <code>List<EActivity></code>
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	@SuppressWarnings("unchecked")
	public List<EActivity> getActivities(long runId) throws Exception{
		return this.gradingService.getCurnitmap(runId).getProject().getActivity();
	}
	
	/**
	 * Given a <code>long</code> runId, returns the number of Steps associated
	 * with that run
	 * 
	 * @param <code>long</code> runId
	 * @return <code>int</code> the number of Steps
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public int numOfSteps(long runId){
		try{
			return this.gradingService.getSteps(runId).size();
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	 * Given a <code>long</code> runId, returns a list of ESteps associated with
	 * that run
	 * 
	 * @param <code>long</code> runId
	 * @return <code>List<EStep></code>
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public List<EStep> getSteps(long runId) throws Exception{
		return this.gradingService.getSteps(runId);
	}
	
	/**
	 * Given a <code>long</code> runId, returns <code>int</code> the number of rims for all
	 * steps in all activities that are associated with that run
	 * 
	 * @param <code>long</code> runId
	 * @return <code>int</code> number of rims
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public int numOfRims(long runId)throws Exception{
		int total = 0;
		for(EActivity activity : this.getActivities(runId)){
			total = total + numOfRims(activity);
		}
		return total;
	}
	
	/**
	 * Given an <code>EActivity</code> activity, returns <code>int</code> the number
	 * of rims in all steps for that activity
	 * 
	 * @param <code>EActivity<code> activity
	 * @return <code>int</code>
	 */
	@SuppressWarnings("unchecked")
	public int numOfRims(EActivity activity){
		int total = 0;
		for(EStep step : (List<EStep>) activity.getStep()){
			total = total + step.getRim().size();
		}
		return total;
	}
	
	/**
	 * Given a <code>long</code> runId, returns a <code>List<ERim></code> list of
	 * Rims that are associated with that run
	 * 
	 * @param <code>long</code>runId
	 * @return <code>List<ERim></code>
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public List<ERim> getRims(long runId) throws Exception{
		List<ERim> list = new ArrayList<ERim>();
		for(EActivity activity : this.getActivities(runId)){
			list.addAll(getRims(activity));
		}
		return list;
	}
	
	/**
	 * Given an <code>EActivity</code> activity, returns a <code>List<ERim></code> list
	 * of Rims that are associated with the given activity
	 * 
	 * @param <code>EActivity</code> activity
	 * @return <code>List<ERim></code>
	 */
	@SuppressWarnings("unchecked")
	public List<ERim> getRims(EActivity activity){
		List<ERim> list = new ArrayList<ERim>();
		for(EStep step : (List<EStep>) activity.getStep()){
			list.addAll(step.getRim());
		}
		return list;
	}
	
	/**
	 * Given a <code>long</code> runId and a <code>long</code> workgroupId, returns the
	 * number of navigation logs associated with that run and that workgroup
	 * 
	 * @param <code>long</code> runId
	 * @param <code>long</code> workgroupId
	 * @return <code>int</code> 
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 * or when no workgroup is found with the specified workgroupId
	 */
	public int numOfNavLogs(long runId, long workgroupId)throws Exception{
		int total = 0;
		List<SessionBundle> sessions = this.sessionBundleService.getSessionBundles(runId, 
				this.workgroupService.retrieveById(workgroupId));
		for(SessionBundle session : sessions){
			total = total + numOfNavLogs(session);
		}
		return total;
	}
	
	/**
	 * Given a <code>SessionBundle</code> session, returns the number of
	 * navigation logs associated with that session
	 * 
	 * @param <code>SessionBundle</code> session
	 * @return <code>int</code>
	 */
	@SuppressWarnings("unchecked")
	public int numOfNavLogs(SessionBundle session){
		int total = 0;
		for(ESockPart sockPart : (List<ESockPart>) session.getESessionBundle().getSockParts()){
			if(sockPart.getRimName().equals("navigation_log")){
				total = total + sockPart.getSockEntries().size();
			}
		}
		return total;
	}
	
	/**
	 * Given a <code>long</code> runId and a <code>long</code> workgroupId returns a 
	 * <code>List<ESockEntry></code> list of navigation log entries
	 * 
	 * @param <code>long</code>runId
	 * @param <code>long</code>workgroupId
	 * @return <code>List<ESockEntry></code>
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 * or when no workgroup is found with the specified workgroupId
	 */
	public List<ESockEntry> getNavLogs(long runId, long workgroupId)throws Exception{
		List<ESockEntry> list = new ArrayList<ESockEntry>();
		List<SessionBundle> sessions = this.sessionBundleService.getSessionBundles(runId, 
				this.workgroupService.retrieveById(workgroupId));
		for(SessionBundle session : sessions){
			list.addAll(getNavLogs(session));
		}
		return list;
	}
	
	/**
	 * Given a <code>SessionBundle</code> returns a <code>List<ESockEntry></code> list
	 * of navigation log entries
	 * 
	 * @param <code>SessionBundle</code> session
	 * @return <code>List<ESockEntry></code>
	 */
	@SuppressWarnings("unchecked")
	public List<ESockEntry> getNavLogs(SessionBundle session){
		List<ESockEntry> list = new ArrayList<ESockEntry>();
		for(ESockPart sockPart : (List<ESockPart>) session.getESessionBundle().getSockParts()){
			if(sockPart.getRimName().equals("navigation_log")){
				list.addAll(sockPart.getSockEntries());
			}
		}
		return list;
	}
	
	/**
	 * Given a <code>String</code> XML SockEntry, returns the <code>String</code>
	 * activityType
	 * 
	 * @param <code>String</code> s
	 * @return <code>String</code>
	 */
	public String activityType(String s){
		String headless = stripHead(s);
		return StringUtils.strip(headless.substring(StringUtils.indexOf(headless, '<') + 1, StringUtils.indexOf(headless, ' ')));
	}
	
	/**
	 * Given a <code>String</code> XML SockEntry, returns the <code>String</code>
	 * podUUID
	 * 
	 * @param <code>String</code> s
	 * @return <code>String</code>
	 */
	public String podUUID(String s){
		String headless = stripHead(s);
		return StringUtils.strip(headless.substring(StringUtils.indexOf(headless, '"') + 1, StringUtils.lastIndexOf(headless, '"')));
	}
	
	/**
	 * Given a <code>String</code> XML SockEntry, returns a <code>String</code>
	 * with the XML header info removed
	 * 
	 * @param <code>String</code> s
	 * @return <code>String</code>
	 */
	private String stripHead(String s){
		return StringUtils.strip(s.
				substring(StringUtils.indexOf(s, '>') + 1,StringUtils.lastIndexOf(s, '>')));
	}
	
	/**
	 * Given a <code>String</code> id (podUUID) and a <code>long</code> runId,
	 * returns the step that is associated with that run and that podUUID
	 * 
	 * @param <code>String</code> id
	 * @param <code>long</code> runId
	 * @return <code>EStep</code>
	 * @throws <code>ObjectNotFoundException</code> when no run is found with specified runId
	 */
	public EStep getStepByPodUUID(String id, long runId)throws Exception{
		List<EStep> allSteps = this.gradingService.getSteps(runId);
		for(EStep step : allSteps){
			if(step.getPodUUID().toString().equals(id)){
				return step;
			}
		}
		return null;
	}
	
	/**
	 * Given an <code>EStep</code> returns a <code>String</code> concatenation
	 * of the Activity and Step
	 * 
	 * @param <code>EStep</code> step
	 * @return <code>String</code>
	 */
	public String uniqueOrderedNum(EStep step){
		return ((EActivity)step.eContainer()).getNumber() + step.getNumber();
	}
	
	/**
	 * Given an <code>EStep</code> returns a <code>String</code> with the
	 * Activity number and Step number - e.g. A4 S3
	 */
	public String getActivityStep(EStep step){
		return "A" + (Integer.parseInt(((EActivity)step.eContainer()).getNumber()) + 1) +
			" S" + (Integer.parseInt(step.getNumber()) + 1);
	}
	
	/**
	 * Given the time in milliseconds the time in seconds rounded to the nearest second
	 */
	public int seconds(int milli){
		return Math.round((milli / 1000));
	}
	
	/**
	 * Given the time in milliseconds, returns the time in minutes
	 * @param <code>int</code> milli
	 * @return <code>float</code>
	 */
	public float minutes(int milli){
		return (milli / 1000 )/60;
	}

	/**
	 * @param gradingService the gradingService to set
	 */
	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
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

