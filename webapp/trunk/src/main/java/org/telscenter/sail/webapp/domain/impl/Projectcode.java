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
package org.telscenter.sail.webapp.domain.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * A Projectcode is a <code>String</code> concatenation of:
 * 1) runcode
 * 2) hyphon "-"
 * 3) periodname
 *
 * @author Hiroki Terashima
 * @version $Id: $
 */
public class Projectcode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String projectcode;
	
	private static String SEPARATOR = "-";

	/**
	 * Constuctor
	 * 
	 * @param projectcode
	 */
	public Projectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	
	/**
	 * Constructor
	 * 
	 * @param runcode
	 * @param periodname
	 */
	public Projectcode(String runcode, String periodname) {
		this.projectcode = runcode + SEPARATOR + periodname;
	}
	
	/**
	 * @return the projectcode
	 */
	public String getProjectcode() {
		return projectcode;
	}

	/**
	 * @param projectcode the projectcode to set
	 */
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	/**
	 * @return the runcode
	 */
	public String getRunCode() {
		return StringUtils.substringBefore(projectcode, SEPARATOR);
	}
	
	/**
	 * @return the periodname
	 */
	public String getRunPeriod() {
		return StringUtils.substringAfter(projectcode, SEPARATOR);
	}
	
	/**
	 * Checks if this projectcode is legal syntactically
	 * 
	 * @return <code>true</code> iff provided projectcode is legal
	 */
	public boolean isLegalProjectcode() {
		if (StringUtils.contains(projectcode, SEPARATOR)) {
			String runcode = getRunCode();
			String periodname = getRunPeriod();
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
