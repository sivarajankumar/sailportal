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
package net.sf.sail.webapp.spring;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Customized implementation that ignores the web.xml context parameter for
 * configLocations that is normally used to list all of the spring configuration
 * files. Instead, we pull the values out of SpringConfiguration class.
 * 
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public class CustomContextLoader extends ContextLoader {

    /**
     * The behaviour of this method is the same as the superclass except for
     * setting of the config locations.
     * 
     * @see org.springframework.web.context.ContextLoader#createWebApplicationContext(javax.servlet.ServletContext,
     *      org.springframework.context.ApplicationContext)
     */
    @Override
    protected WebApplicationContext createWebApplicationContext(
            ServletContext servletContext, ApplicationContext parent)
            throws BeansException {

        Class contextClass = determineContextClass(servletContext);
        if (!ConfigurableWebApplicationContext.class
                .isAssignableFrom(contextClass)) {
            throw new ApplicationContextException("Custom context class ["
                    + contextClass.getName()
                    + "] is not of type ConfigurableWebApplicationContext");
        }

        ConfigurableWebApplicationContext webApplicationContext = (ConfigurableWebApplicationContext) BeanUtils
                .instantiateClass(contextClass);
        webApplicationContext.setParent(parent);
        webApplicationContext.setServletContext(servletContext);
        webApplicationContext
                .setConfigLocations(SpringConfiguration.CONFIG_LOCATIONS);

        webApplicationContext.refresh();
        return webApplicationContext;
    }
}