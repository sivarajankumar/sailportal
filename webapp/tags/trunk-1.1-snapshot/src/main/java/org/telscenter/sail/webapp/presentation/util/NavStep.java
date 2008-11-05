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

import org.telscenter.pas.emf.pas.EStep;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class NavStep implements Comparable<NavStep>{
	
	private EStep step;
	
	private int open;
	
	private int close;

	/**
	 * @return duration for this step
	 */
	public int getDuration(){
		return this.close - this.open;
	}
	
	/**
	 * @return whether this step has been closed or not
	 */
	public boolean isClosed(){
		return this.close > 0;
	}
	
	/**
	 * @return the step
	 */
	public EStep getStep() {
		return step;
	}

	/**
	 * @param step the step to set
	 */
	public void setStep(EStep step) {
		this.step = step;
	}

	/**
	 * @return the open
	 */
	public int getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(int open) {
		this.open = open;
	}

	/**
	 * @return the close
	 */
	public int getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(int close) {
		this.close = close;
	}

	public int compareTo(NavStep o) {
		return this.getOpen() - o.getOpen();
	}

}
