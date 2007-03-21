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
            "classpath:configurations/applicationContexts/pas/user.xml",
            "classpath:configurations/applicationContexts/tels/extensions.xml",
            "classpath:configurations/applicationContexts/tels/overrides.xml" };

    /**
     * @see net.sf.sail.webapp.spring.impl.SpringConfiguration#getConfigLocations()
     */
    public String[] getConfigLocations() {
        return CONFIG_LOCATIONS;
    }

    /**
     * Static method to get the config locations so an instance of this object
     * doesn't have to be created.
     * 
     * @return <code>String[]</code> such that each string in the array
     *         defines the location of an applicationContext XML configuration
     *         file used by the Spring container
     * @see net.sf.sail.webapp.spring.impl.SpringConfiguration#getConfigLocations()
     */
    public static String[] getConfigLocationsStatically() {
        return CONFIG_LOCATIONS;
    }
}