package org.telscenter.sail.webapp.presentation.web.controllers.run;

import org.apache.commons.lang.StringUtils;

public class RunUtils {

	private static String SEPERATOR = "-";
	
	/**
	 * enter a project code and recieve a run cod
	 * 
	 * @param projectCode
	 * @return a run code
	 */
	public static String getRunCode(String projectCode ) {
		return StringUtils.substringBefore(projectCode, SEPERATOR);
	}
	
	/**
	 * enter a project code and recieve the period of that the run
	 * 
	 * @param projectCode
	 * @return the period of that run
	 */
	public static String getRunPeriod(String projectCode) {
		return StringUtils.substringAfter(projectCode, SEPERATOR);
	}
}
