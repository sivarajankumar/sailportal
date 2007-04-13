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
package net.sf.sail.webapp.domain.webservice;

/**
 * Signals that a network transport error has occured on a request. Thrown by
 * transport mechanisms when an unexpected response status is encountered.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class NetworkTransportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NetworkTransportException(String message) {
        super(message);
    }

    public NetworkTransportException(int statusCode) {
        this(String.valueOf(statusCode));
    }

    public NetworkTransportException(int statusCode, String message) {
        this(statusCode + " " + message);
    }
}