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
package net.sf.sail.webapp.spring.impl;

import net.sf.sail.webapp.spring.SpringConfiguration;

/**
 * Implementation of <code>SpringConfiguration</code> specifically for the PAS
 * portal.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public final class SpringConfigurationImpl implements SpringConfiguration {

    private static final String[] CONFIG_LOCATIONS = new String[] {
            "classpath:configurations/applicationContexts/pas/acegiSecurity.xml",
            "classpath:configurations/applicationContexts/pas/datasource.xml",
            "classpath:configurations/applicationContexts/pas/hibernate.xml",
            "classpath:configurations/applicationContexts/pas/sds.xml",
            "classpath:configurations/applicationContexts/pas/security.xml",
            "classpath:configurations/applicationContexts/pas/spring.xml",
            "classpath:configurations/applicationContexts/pas/user.xml" };

    /**
     * @see net.sf.sail.webapp.spring.SpringConfiguration#getConfigLocations()
     */
    public String[] getConfigLocations() {
        return CONFIG_LOCATIONS;
    }
}