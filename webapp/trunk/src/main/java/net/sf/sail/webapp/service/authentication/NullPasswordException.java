/**
 * Copyright (c) 2006 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.service.authentication;

/**
 * A checked exception thrown when the user details password is set to null.
 * 
 * @author Laurel Williams
 * 
 * @version $Id: $
 */
public class NullPasswordException extends UserCreationException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "Password cannnot be null.";

	public String getMessage() {
		return MESSAGE;
	}

}
