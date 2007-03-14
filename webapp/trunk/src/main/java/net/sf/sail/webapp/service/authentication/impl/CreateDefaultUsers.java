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
package net.sf.sail.webapp.service.authentication.impl;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.DuplicateUsernameException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;
import net.sf.sail.webapp.spring.SpringConfiguration;

import org.acegisecurity.GrantedAuthority;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A disposable class that is used to create default roles in the data store and
 * to create a default administrator account.
 * 
 * @author Laurel Williams
 * @author Cynick Young
 * 
 * @version $Id$
 */
public class CreateDefaultUsers {

    private UserDetailsService userDetailsService = null;

    private UserService userService = null;

    /**
     * Stand alone application that initializes the database with user and admin
     * roles, as well as an administrator user account. Your chosen
     * administrator username and password need to be passed as command line
     * arguments.
     * 
     * @param args
     *            args[0] - the admin username. args[1] - the admin password.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out
                    .println("Usage: CreateDefaultUsers <admin-username> <admin-password>");
            System.exit(1);
        }
        AbstractXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                SpringConfiguration.CONFIG_LOCATIONS);
        try {
            CreateDefaultUsers createDefaultUsers = new CreateDefaultUsers();
            createDefaultUsers.init(applicationContext);
            MutableUserDetails adminUser = (MutableUserDetails) applicationContext
                    .getBean("mutableUserDetails");
            adminUser.setUsername(args[0]);
            adminUser.setPassword(args[1]);
            createDefaultUsers.createRoles(applicationContext);
            createDefaultUsers.createAdministrator(adminUser);
        } catch (Exception all) {
            System.err.println(all.getLocalizedMessage());
            all.printStackTrace(System.out);
            System.exit(2);
        } finally {
            applicationContext.close();
        }
    }

    @SuppressWarnings("unchecked")
    private void init(ApplicationContext context) {
        this.setUserDetailsService((UserDetailsService) context
                .getBean("userDetailsService"));
        this.setUserService((UserService) context.getBean("userService"));
    }

    /**
     * Sets the UserDetailsService.
     * 
     * @param userDetailsService
     */
    @Required
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Given a MutableUserDetails object (with username and password set),
     * creates a user with both UserDetailsService.USER_ROLE and
     * UserDetailsService.ADMIN_ROLE authorities. These roles must be set
     * already by using createRoles();
     * 
     * @param userDetails
     *            A UserDetails object with the username and password set.
     * @return A User object with UserDetails set including username and
     *         password that were input and with roles
     *         UserDetailsService.USER_ROLE and UserDetailsService.ADMIN_ROLE
     *         authorities.
     * @throws AuthorityNotFoundException
     *             If the user or admin roles are not already loaded into the
     *             granted authority table in data store.
     * @throws UserCreationException
     *             If the user cannot be created (duplicate user name, null
     *             username or null password)
     */
    public User createAdministrator(MutableUserDetails userDetails)
            throws AuthorityNotFoundException, DuplicateUsernameException {
        GrantedAuthority authority = userDetailsService
                .loadAuthorityByName(UserDetailsService.ADMIN_ROLE);
        userDetails.addAuthority(authority);
        return userService.createUser(userDetails);
    }

    /**
     * Creates two default roles in the the data store authorities table. These
     * are UserDetailsService.USER_ROLE and UserDetailsService.ADMIN_ROLE
     * authorities. This method should be run before attempting to create users.
     * 
     * @param applicationContext
     *            The Spring application context that contains the beans.
     * @throws DuplicateAuthorityException
     *             if authority to be created is not unique
     */
    public void createRoles(ApplicationContext applicationContext)
            throws DuplicateAuthorityException {
        MutableGrantedAuthority mutableGrantedAuthority = (MutableGrantedAuthority) applicationContext
                .getBean("mutableGrantedAuthority");
        mutableGrantedAuthority.setAuthority(UserDetailsService.ADMIN_ROLE);
        this.userDetailsService.createGrantedAuthority(mutableGrantedAuthority);

        mutableGrantedAuthority = (MutableGrantedAuthority) applicationContext
                .getBean("mutableGrantedAuthority");
        mutableGrantedAuthority.setAuthority(UserDetailsService.USER_ROLE);
        this.userDetailsService.createGrantedAuthority(mutableGrantedAuthority);
    }

    /**
     * @param userService
     *            the userService to set
     */
    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}