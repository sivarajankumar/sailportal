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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.reports;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.presentation.google.charts.LineChart;
import org.telscenter.sail.webapp.presentation.google.charts.LineChartOptions;
import org.telscenter.sail.webapp.presentation.google.charts.impl.LineChartImpl;
import org.telscenter.sail.webapp.presentation.google.charts.impl.LineChartOptionsImpl;
import org.telscenter.sail.webapp.presentation.util.NavStep;
import org.telscenter.sail.webapp.presentation.util.SdsLogUtil;
import org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog;
import org.telscenter.sail.webapp.presentation.util.impl.WorkgroupNavLogImpl;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * @author patrick lawler
 *
 */
public class StepActivityGraphController extends AbstractController {
	
	private RunService runService;
	
	private WISEWorkgroupService workgroupService;
	
	private final static String RUNID = "runId";
	
	private final static String WORKGROUPID = "workgroupId";
	
	private final static String WORKGROUP = "workgroup";
	
	private final static String URL = "url";
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SdsLogUtil util = new SdsLogUtil();
		Run run = this.runService.retrieveById(Long.parseLong(request.getParameter(RUNID)));
		WISEWorkgroup workgroup = (WISEWorkgroup) this.workgroupService.retrieveById(Long.parseLong(request.getParameter(WORKGROUPID)));
		
		WorkgroupNavLog workgroupNavLog = new WorkgroupNavLogImpl(run.getId(),workgroup.getId());
		
		LineChartOptions options = new LineChartOptionsImpl();
		LineChart chart = new LineChartImpl();
		chart.setChartSize(750, 400);
		chart.setXYType(true);
		
		int maxX = 0;
		int maxY = 0;
		List<Integer> xData = new LinkedList<Integer>(workgroupNavLog.getTimeStepMap().keySet());
		List<Integer> xdata = new LinkedList<Integer>();
		List<NavStep> yData = new LinkedList<NavStep>(workgroupNavLog.getTimeStepMap().values());
		List<Integer> ydata = new LinkedList<Integer>();
		
		for(int milli : xData){
			maxX = util.seconds(milli);
			xdata.add(maxX);
		}
		List<String> yLabels = new LinkedList<String>();
		for(NavStep step : yData){
			maxY = Integer.parseInt(util.uniqueOrderedNum(step.getStep()));
			ydata.add(maxY);
			yLabels.add(util.getActivityStep(step.getStep()));
		}
		
		options.addScaling(0, maxX);
		options.addScaling(0, maxY);
		options.addLabels("x", null);
		options.addLabels("y", yLabels);
		chart.addData(xdata);
		chart.addData(ydata);
		chart.setOptions(options);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(WORKGROUP, workgroup);
		modelAndView.addObject(URL, chart.getURL());
		
		return modelAndView;
	}

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	/**
	 * @param workgroupService the workgroupService to set
	 */
	public void setWorkgroupService(WISEWorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}
}
