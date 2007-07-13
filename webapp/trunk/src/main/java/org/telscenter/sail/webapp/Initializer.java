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
package org.telscenter.sail.webapp;

import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.spring.SpringConfiguration;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A disposable class that is used to initialize the system.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class Initializer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: Initializer "
                    + "<spring-configuration-classname> ");
            System.exit(1);
        }
        ConfigurableApplicationContext applicationContext = null;
        try {
            SpringConfiguration springConfig = (SpringConfiguration) BeanUtils
                    .instantiateClass(Class.forName(args[0]));
            applicationContext = new ClassPathXmlApplicationContext(
                    springConfig.getRootApplicationContextConfigLocations());

            CreateDefaultUsers createDefaultUsers = new CreateDefaultUsers(
                    applicationContext);
            createDefaultUsers.createRoles(applicationContext);
            createDefaultUsers.createAdministrator(applicationContext, "admin",
                    "pass");

            CreateDefaultOfferings createDefaultOfferings = new CreateDefaultOfferings(
                    applicationContext);
            Curnit[] curnits = createDefaultOfferings
                    .createDefaultCurnits(applicationContext);
            Jnlp[] jnlps = createDefaultOfferings
                    .createDefaultJnlps(applicationContext);
            createDefaultOfferings.createDefaultOfferings(applicationContext,
                    curnits, jnlps);

        } catch (Exception all) {
            System.err.println(all.getLocalizedMessage());
            all.printStackTrace(System.out);
            System.exit(2);
        } finally {
            applicationContext.close();
        }
    }

}