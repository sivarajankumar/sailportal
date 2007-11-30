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
package org.telscenter.sail.webapp.domain.workgroup.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;

import net.sf.sail.webapp.domain.group.Group;

/**
 * A WISE Workgroup object implementation
 *
 * @author Hiroki Terashima
 * @version $Id$
 */
@Entity
@Table(name = WISEWorkgroupImpl.DATA_STORE_NAME)
public class WISEWorkgroupImpl extends net.sf.sail.webapp.domain.impl.WorkgroupImpl
		implements WISEWorkgroup {

	@Transient
	private static final long serialVersionUID = 1L;

    @Transient
    public static final String DATA_STORE_NAME = "wiseworkgroups";

	@Transient
	private static final String COLUMN_NAME_PERIOD = "period";

	@Lob
	@Column(name = WISEWorkgroupImpl.COLUMN_NAME_PERIOD, length = 2147483647) // Keep length to force it to use large field type
	private Group period;
	
	/**
	 * @see org.telscenter.sail.webapp.domain.workgroup.impl.Workgroup#getPeriod()
	 */
	public Group getPeriod() {
		return period;
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.workgroup.impl.Workgroup#setPeriod(net.sf.sail.webapp.domain.group.Group)
	 */
	public void setPeriod(Group period) {
		this.period = period;
	}
}
