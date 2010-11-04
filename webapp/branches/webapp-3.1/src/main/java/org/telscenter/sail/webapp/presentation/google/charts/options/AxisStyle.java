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
package org.telscenter.sail.webapp.presentation.google.charts.options;

import org.telscenter.sail.webapp.presentation.google.charts.ChartOption;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class AxisStyle implements ChartOption{

	private int index;
	
	private String color;
	
	private int size = -1;
	
	private int alignment = -2;
	
	public AxisStyle(int index, String color){
		this.index = index;
		this.color = color;
	}
	
	public AxisStyle(int index, String color, int size){
		this.index = index;
		this.color = color;
		this.size = size;
	}
	
	public AxisStyle(int index, String color, int size, int alignment){
		this.index = index;
		this.color = color;
		this.size = size;
		this.alignment = alignment;
	}

	public String getOptionString() {
		String style = this.index + "," + this.color;
		if(this.size > -1){
			style = style + "," + this.size;
		}
		if(this.alignment > -2){
			style = style + "," + this.alignment;
		}
		return style;
	}
	
	
}
