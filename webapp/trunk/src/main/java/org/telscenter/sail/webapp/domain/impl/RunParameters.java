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

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.OfferingParameters;


/**
 * @author Laurel Williams
 *
 * @version $Id$
 */
public class RunParameters extends OfferingParameters implements Serializable {

	private static final long serialVersionUID = 1L;

	private Set<String> periodNames = new TreeSet<String>();
	
	private Set<User> owners = new HashSet<User>();
	
	private String manuallyEnteredPeriods = new String();

	/**
	 * @return the periodNames
	 */
	public Set<String> getPeriodNames() {
		return periodNames;
	}

	/**
	 * @param periodNames the periodNames to set
	 */
	public void setPeriodNames(Set<String> periodNames) {
		this.periodNames = periodNames;
	}

	/**
	 * @return the owners
	 */
	public Set<User> getOwners() {
		return owners;
	}

	/**
	 * @param owners the owners to set
	 */
	public void setOwners(Set<User> owners) {
		this.owners = owners;
	}
	
	/**
	 * @param manuallyEnteredPeriods the manuallyEnteredPerios to set
	 */
	public void setManuallyEnteredPeriods(String text){
		this.manuallyEnteredPeriods = text;
	}
	
	/**
	 * @return manuallyEnteredPeriods
	 */
	public String getManuallyEnteredPeriods(){
		return this.manuallyEnteredPeriods;
	}
	
}
