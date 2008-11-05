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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.reports;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.presentation.google.charts.BarChart;
import org.telscenter.sail.webapp.presentation.google.charts.BarChartOptions;
import org.telscenter.sail.webapp.presentation.google.charts.GoogleChart;
import org.telscenter.sail.webapp.presentation.google.charts.impl.BarChartImpl;
import org.telscenter.sail.webapp.presentation.google.charts.impl.BarChartOptionsImpl;
import org.telscenter.sail.webapp.presentation.util.WorkgroupNavLog;
import org.telscenter.sail.webapp.presentation.util.impl.WorkgroupNavLogImpl;
import org.telscenter.sail.webapp.service.offering.RunService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class TotalTimePerStepController extends AbstractController{

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
		
		Run run = this.runService.retrieveById(Long.parseLong(request.getParameter(RUNID)));
		WISEWorkgroup workgroup = (WISEWorkgroup) this.workgroupService.retrieveById(Long.parseLong(request.getParameter(WORKGROUPID)));
		
		WorkgroupNavLog workgroupNavLog = new WorkgroupNavLogImpl(run.getId(), workgroup.getId());
		
		Map<String,Double> gradient1 = new TreeMap<String,Double>();
		Map<String,Double> gradient2 = new TreeMap<String,Double>();
		Map<String,Double> stripe = new TreeMap<String,Double>();
		gradient1.put("000000", 0.5);
		gradient1.put("990020bb", 1.0);
		gradient1.put("4500ff", 0.0);
		gradient2.put("ffffff", 0.0);
		gradient2.put("ff2045", 1.0);
		stripe.put("bbbbbb", 0.25);
		stripe.put("888888", 0.25);
		stripe.put("444444", 0.25);
		List<String> legend = new LinkedList<String>();
		legend.add(workgroup.generateWorkgroupName());
		legend.add("this is extra, extra");
		
		List<Integer> data2 = new LinkedList<Integer>();
		List<Integer> data1 = new LinkedList<Integer>(workgroupNavLog.getTotalTimePerStepMap().values());
		List<List<Integer>> dataSets = new LinkedList<List<Integer>>();
		dataSets.add(data1);
		dataSets.add(data2);
		data2.add(30);
		data2.add(60);
		data2.add(47);
		data2.add(99);
		data2.add(12);
		data2.add(42);
		data2.add(230);
		data2.add(500);
		
		BarChart chart = new BarChartImpl();
		chart.setChartSize(740,400);
		chart.addData(dataSets);
		chart.setOrientation("v");
		chart.setGrouped(true);
		
		BarChartOptions options = new BarChartOptionsImpl();
		options.addLabels("x", new ArrayList(workgroupNavLog.getTotalTimePerStepMap().keySet()));
		options.addLabels("y", null);
		options.addLinearGradient("c", 45, gradient1);
		options.addChartColor("888888");
		options.addChartColor("aaaaaa");
		options.addChartColor("666666");
		options.addGridLines(5.5, 10.0);
		options.addGridLines(15.0, 25.0, 5.0, 2.5);
		options.addLegendLabels(legend);
		options.addLinearGradient("bg", 90, gradient2);
		//options.addLinearStripe("c", 0, stripe);
		options.addMoreLineStyle("40ff40", 0, 3, 1);
		options.addMoreLineStyle("009900", 0, 9, -1);
		options.setLegendPosition("l");
		options.addShapeMarker("a", "a59915", 0, 1, 5, 1);
		options.addTitle("Total Time Per Step Chart", "00ff95", 32);
		options.addAxisStyle(0, "80409f", 8);
		options.setBarWidthAndSpacing(10, 4, 10);
		options.addScaling(0, 110);
		options.addScaling(0, 550);
		
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
