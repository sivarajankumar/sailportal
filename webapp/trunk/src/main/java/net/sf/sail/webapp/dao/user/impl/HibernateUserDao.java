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
package net.sf.sail.webapp.dao.user.impl;

import net.sf.sail.webapp.dao.impl.AbstractHibernateDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.impl.UserImpl;

import org.acegisecurity.userdetails.UserDetails;
import org.springframework.dao.support.DataAccessUtils;


/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class HibernateUserDao extends AbstractHibernateDao<User> implements
        UserDao<User> {

    private static final String FIND_ALL_QUERY = "from UserImpl";

    /**
     * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getFindAllQuery()
     */
    @Override
    protected String getFindAllQuery() {
        return FIND_ALL_QUERY;
    }

    /**
     * @see net.sf.sail.webapp.dao.user.UserDao#retrieveByUserDetails(org.acegisecurity.userdetails.UserDetails)
     */
    public User retrieveByUserDetails(UserDetails userDetails) {
        return (User) DataAccessUtils
                .uniqueResult(this
                        .getHibernateTemplate()
                        .findByNamedParam(
                                "from UserImpl as user where user.userDetails = :userDetails",
                                "userDetails", userDetails));
    }

	/**
	 * @see net.sf.sail.webapp.dao.impl.AbstractHibernateDao#getDataObjectClass()
	 */
	@Override
	protected Class getDataObjectClass() {
		return UserImpl.class;
	}

    /**
     * @see
     */
//	TODO TP please comment
    public User retrieveByUsername(String username) {
        return (User) DataAccessUtils
                .requiredUniqueResult(this
                        .getHibernateTemplate()
                        .findByNamedParam(
                                "from UserImpl as user where user.userDetails.username = :username",
                                "username", username));
    }
    
    /**
     * @see
     */
//    TODO TP please comment
    public User retrieveByEmailAddress(String emailAddress	) {
        return (User) DataAccessUtils
                .requiredUniqueResult(this
                        .getHibernateTemplate()
                        .findByNamedParam(
                                "from UserImpl as user where user.userDetails.emailAddress = :emailAddress",
                                "emailAddress", emailAddress));
    }
   
}