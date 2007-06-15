/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.telscenter.sail.webapp.domain.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.impl.OfferingParameters;

/**
 * @author Laurel Williams
 *
 * @version $Id$
 */
public class RunParameters extends OfferingParameters {

	   private Date starttime;

	    private Date endtime;

	    private String runcode;
	    
	    private Set<Group> periods = new HashSet<Group>();

		/**
		 * @return the endtime
		 */
		public Date getEndtime() {
			return endtime;
		}

		/**
		 * @param endtime the endtime to set
		 */
		public void setEndtime(Date endtime) {
			this.endtime = endtime;
		}

		/**
		 * @return the periods
		 */
		public Set<Group> getPeriods() {
			return periods;
		}

		/**
		 * @param periods the periods to set
		 */
		public void setPeriods(Set<Group> periods) {
			this.periods = periods;
		}

		/**
		 * @return the runcode
		 */
		public String getRuncode() {
			return runcode;
		}

		/**
		 * @param runcode the runcode to set
		 */
		public void setRuncode(String runcode) {
			this.runcode = runcode;
		}

		/**
		 * @return the starttime
		 */
		public Date getStarttime() {
			return starttime;
		}

		/**
		 * @param starttime the starttime to set
		 */
		public void setStarttime(Date starttime) {
			this.starttime = starttime;
		}

}
