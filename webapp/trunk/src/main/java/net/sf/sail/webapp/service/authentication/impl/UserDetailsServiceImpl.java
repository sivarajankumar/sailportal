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

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.UserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.MutableUserDetails;
import net.sf.sail.webapp.service.authentication.AuthorityNotFoundException;
import net.sf.sail.webapp.service.authentication.DuplicateAuthorityException;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

/**
 * A class to provide services for MutableUserDetails objects.
 * 
 * @author Cynick Young
 * @author Laurel Willliams
 * 
 * @version $Id$
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDetailsDao<MutableUserDetails> userDetailsDao;

	private GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao;

	/**
	 * @param grantedAuthorityDao
	 *            The granted authority to set.
	 */
	public void setGrantedAuthorityDao(
			GrantedAuthorityDao<MutableGrantedAuthority> grantedAuthorityDao) {
		this.grantedAuthorityDao = grantedAuthorityDao;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = this.userDetailsDao.retrieveByName(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("Username: " + username
					+ " not found.");
		}
		return userDetails;
	}

	/**
	 * @param userDetailsDao
	 *            The userDetailsDao to set.
	 */
	public void setUserDetailsDao(
			UserDetailsDao<MutableUserDetails> userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#createGrantedAuthority(java.lang.String)
	 */
	@Transactional(rollbackFor = { DuplicateAuthorityException.class })
	public MutableGrantedAuthority createGrantedAuthority(ApplicationContext applicationContext, String authority)
			throws DuplicateAuthorityException {
		this.checkNoAuthorityCreationErrors(authority);

		//TODO look at this - should we be getting this from the bean???
		MutableGrantedAuthority grantedAuthority = (MutableGrantedAuthority) applicationContext
		.getBean("mutableGrantedAuthority");
		grantedAuthority.setAuthority(authority);
		this.grantedAuthorityDao.save(grantedAuthority);
		return grantedAuthority;
	}

	/**
	 * Validates user input checks that the data store does not already contain
	 * an authority with the same name
	 * 
	 * @param authority
	 *            The authority to be checked for in the data store.
	 * @throws DuplicateAuthorityException
	 *             If the authority is the same as an authority already in data
	 *             store.
	 */
	private void checkNoAuthorityCreationErrors(String authority)
			throws DuplicateAuthorityException {

	    if (this.grantedAuthorityDao.hasRole(authority)){
	    	throw new DuplicateAuthorityException(authority);
	      }
	}

	/**
	 * @see net.sf.sail.webapp.service.authentication.UserDetailsService#loadAuthorityByName(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public GrantedAuthority loadAuthorityByName(String authority)
			throws AuthorityNotFoundException {
		GrantedAuthority grantedAuthority = this.grantedAuthorityDao
				.retrieveByName(authority);
		if (grantedAuthority == null) {
			throw new AuthorityNotFoundException(authority);
		}
		return grantedAuthority;
	}
}