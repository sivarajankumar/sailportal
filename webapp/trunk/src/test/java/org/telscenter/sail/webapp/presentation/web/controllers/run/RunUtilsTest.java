package org.telscenter.sail.webapp.presentation.web.controllers.run;

import junit.framework.TestCase;

public class RunUtilsTest extends TestCase {
	
	String runCode = "saphire8886";
	String period = "6";
	String projectCode = runCode + "-" + period;
	private final String[] ILLEGAL_PROJECTCODES = {"Owl0896", "Owl0896-", "-3", "-", ""};	
	
	public void testGetRunCode() {
		String runCode2 = RunUtils.getRunCode(projectCode);
		assertEquals(runCode, runCode2);
	}
	
	public void testGetPeriod() {
		String runPeriod = RunUtils.getRunPeriod(projectCode);
		assertEquals(period, runPeriod);
	}
	
	public void testIsLegalProjectcode() {
		for (String illegalProjectcode : ILLEGAL_PROJECTCODES) {
			assertFalse(RunUtils.isLegalProjectcode(illegalProjectcode));
		}
	}

}
