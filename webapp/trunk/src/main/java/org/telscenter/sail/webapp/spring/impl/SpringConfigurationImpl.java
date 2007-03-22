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
package org.telscenter.sail.webapp.spring.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.sf.sail.webapp.spring.SpringConfiguration;

/**
 * Implementation of <code>SpringConfiguration</code> specifically for the
 * TELS portal.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public final class SpringConfigurationImpl implements SpringConfiguration {

    private static final String[] CONFIG_LOCATIONS;

    static {
        final List<String> configLocationsList = Collections
                .list(Collections
                        .enumeration(Arrays
                                .asList(net.sf.sail.webapp.spring.impl.SpringConfigurationImpl
                                        .getConfigLocationsStatically())));
        configLocationsList
                .add("configurations/applicationContexts/tels/extensions.xml");
        // Keep the overrides as the last item to be added to the list to ensure
        // that the overridden bean has indeed been defined.
        configLocationsList
                .add("configurations/applicationContexts/tels/overrides.xml");
        CONFIG_LOCATIONS = configLocationsList.toArray(new String[0]);
    }

    /**
     * @see net.sf.sail.webapp.spring.SpringConfiguration#getConfigLocations()
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
     * @see net.sf.sail.webapp.spring.SpringConfiguration#getConfigLocations()
     */
    public static String[] getConfigLocationsStatically() {
        return CONFIG_LOCATIONS;
    }
}