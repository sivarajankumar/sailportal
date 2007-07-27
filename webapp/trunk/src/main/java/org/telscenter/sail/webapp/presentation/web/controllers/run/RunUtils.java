package org.telscenter.sail.webapp.presentation.web.controllers.run;

import org.apache.commons.lang.StringUtils;

public class RunUtils {

	private static String SEPARATOR = "-";
	
	/**
	 * enter a project code and recieve a run cod
	 * 
	 * @param projectCode
	 * @return a run code
	 */
	public static String getRunCode(String projectcode) {
		return StringUtils.substringBefore(projectcode, SEPARATOR);
	}
	
	/**
	 * enter a project code and recieve the period of that the run
	 * 
	 * @param projectCode
	 * @return the period of that run
	 */
	public static String getRunPeriod(String projectcode) {
		return StringUtils.substringAfter(projectcode, SEPARATOR);
	}
	
	/**
	 * Checks if given projectcode is legal
	 * 
	 * @param projectcode
	 * @return <code>true</code> iff provided projectcode is legal
	 */
	public static boolean isLegalProjectcode(String projectcode) {
		if (StringUtils.contains(projectcode, SEPARATOR)) {
			String runcode = getRunCode(projectcode);
			String periodname = getRunPeriod(projectcode);
			if (!StringUtils.isEmpty(runcode) && 
			    !StringUtils.isEmpty(periodname) && 
			     StringUtils.isAlphanumeric(runcode) && 
				 StringUtils.isAlphanumeric(periodname)) {
					return true;
			}
		}
		return false;
	}
}
