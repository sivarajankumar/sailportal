package org.telscenter.sail.webapp.presentation.web.controllers.run;

import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

public class RunUtilsTest extends TestCase {
	
	String runCode = "saphire8886";
	String period = "6";
	String projectCode = runCode + "-" + period;
	
	public void testGetRunCode() {
		String runCode2 = RunUtils.getRunCode(projectCode);
		assertEquals(runCode, runCode2);
	}
	
	public void testGetPeriod() {
		String runPeriod = RunUtils.getRunPeriod(projectCode);
		assertEquals(period, runPeriod);
	}

}
