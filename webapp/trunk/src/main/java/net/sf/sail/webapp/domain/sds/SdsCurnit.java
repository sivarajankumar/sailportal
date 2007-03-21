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
package net.sf.sail.webapp.domain.sds;

/**
 * Represents a curnit from the Sail Data Service (SDS).
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class SdsCurnit implements SdsObject {

	private static final long serialVersionUID = 1L;

	private Integer sdsObjectId = null;

	private String name;

	private String url;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @see net.sf.sail.webapp.domain.sds.SdsObject#getSdsObjectId()
	 */
	public Integer getSdsObjectId() {
		return this.sdsObjectId;
	}

	/**
	 * @see net.sf.sail.webapp.domain.sds.SdsObject#setSdsObjectId(java.lang.Integer)
	 */
	public void setSdsObjectId(Integer id) {
		this.sdsObjectId = id;
	}

}
