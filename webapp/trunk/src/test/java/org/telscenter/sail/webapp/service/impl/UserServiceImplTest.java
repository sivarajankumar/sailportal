/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.service.impl;

import java.util.Calendar;
import java.util.Date;

import net.sf.sail.webapp.dao.authentication.GrantedAuthorityDao;
import net.sf.sail.webapp.dao.user.UserDao;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.authentication.UserDetailsService;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.telscenter.sail.webapp.domain.authentication.Gender;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.impl.StudentUserDetails;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Tests services available to TELS Portal User
 * 
 * @author Hiroki Terashima
 * 
 * @version $Id$
 * 
 */
public class UserServiceImplTest extends AbstractTransactionalDbTests {

    private static final String EMAIL = "billy@bob.com";

    private static final String PASSWORD = "password";

    private static final String FIRSTNAME = "Billy";

    private static final String LASTNAME = "Bob";
    
    private static final Date SIGNUPDATE = Calendar.getInstance().getTime();
    
    private static final Gender GENDER = Gender.FEMALE;

	private static final Date BIRTHDAY = Calendar.getInstance().getTime();

    private GrantedAuthorityDao<MutableGrantedAuthority> authorityDao;

    private UserDao<User> userDao;

    private UserDetailsService userDetailsService;

    private UserService userService;

    public void testDuplicateUserErrors() throws Exception {
        StudentUserDetails userDetails = (StudentUserDetails) this.applicationContext
                .getBean("studentUserDetails");
        userDetails.setPassword(PASSWORD);
        userDetails.setEmailAddress(EMAIL);
        userDetails.setFirstname(FIRSTNAME);
        userDetails.setLastname(LASTNAME);
        userDetails.setSignupdate(SIGNUPDATE);
        userDetails.setGender(GENDER);
        userDetails.setBirthday(BIRTHDAY);

        // create 2 users and attempt to save to DB
        // second user should create a new user with similar username but with an added "a"
        this.userService.createUser(userDetails);

        StudentUserDetails userDetails2 = (StudentUserDetails) this.applicationContext
        .getBean("studentUserDetails");
        userDetails2.setPassword(PASSWORD);
        userDetails2.setEmailAddress(EMAIL);
        userDetails2.setFirstname(FIRSTNAME);
        userDetails2.setLastname(LASTNAME);
        userDetails2.setSignupdate(SIGNUPDATE);
        userDetails2.setGender(GENDER);
        userDetails2.setBirthday(BIRTHDAY);

        this.userService.createUser(userDetails2);

        assertEquals(userDetails.getUsername() + userDetails.getUsernameSuffixes()[1],
        		userDetails2.getUsername());
    }

    /*
     * This test checks creation of a user within the portal, but ignores the
     * creation of a user on the remote SDS. Tests for system integration are
     * beyond the scope of this testing mechanism. We are assuming the SdsUserId
     * cannot be null, enforced by the data store constraint.
     */
    public void testCreateUserWithEmail() throws Exception {
        MutableGrantedAuthority expectedAuthority = (MutableGrantedAuthority) this.applicationContext
                .getBean("mutableGrantedAuthority");
        expectedAuthority.setAuthority(UserDetailsService.USER_ROLE);
        this.authorityDao.save(expectedAuthority);

        StudentUserDetails userDetails = (StudentUserDetails) this.applicationContext
               .getBean("studentUserDetails");
        userDetails.setPassword(PASSWORD);
        userDetails.setEmailAddress(EMAIL);
        userDetails.setFirstname(FIRSTNAME);
        userDetails.setLastname(LASTNAME);
        userDetails.setSignupdate(SIGNUPDATE);
        userDetails.setGender(GENDER);
        userDetails.setBirthday(BIRTHDAY);

        // create user (saves automatically)
        User expectedUser = this.userService.createUser(userDetails);
        UserDetails expectedUserDetails = expectedUser.getUserDetails();

        // retrieve user and compare
        UserDetails actual = this.userDetailsService
                .loadUserByUsername(userDetails.getUsername());
        assertEquals(expectedUserDetails, actual);

        // check role
        GrantedAuthority[] authorities = actual.getAuthorities();
        if (authorities == null)
            fail("authorities is null");
        boolean foundUserRole = false;
        for (int i = 0; i < authorities.length; i++) {
            if (authorities[i].getAuthority() == UserDetailsService.USER_ROLE) {
                foundUserRole = true;
                break;
            }
        }
        assertTrue(foundUserRole);

        // added this end transaction to catch a transaction commit within a
        // transactio rollback problem
        this.userDao.delete(expectedUser);
        this.authorityDao.delete(expectedAuthority);
        this.setComplete();
        this.endTransaction();
    }

    /*
     * This test checks creation of a user within the portal, but ignores the
     * creation of a user on the remote SDS. Tests for system integration are
     * beyond the scope of this testing mechanism. We are assuming the SdsUserId
     * cannot be null, enforced by the data store constraint. Email is null
     */
    public void testCreateUserBlankEmail() throws Exception {
    	StudentUserDetails userDetails = (StudentUserDetails) this.applicationContext
    	       .getBean("studentUserDetails");
    	userDetails.setPassword(PASSWORD);
    	userDetails.setEmailAddress(EMAIL);
    	userDetails.setFirstname(FIRSTNAME);
    	userDetails.setLastname(LASTNAME);
        userDetails.setSignupdate(SIGNUPDATE);
    	userDetails.setGender(GENDER);
    	userDetails.setBirthday(BIRTHDAY);
    	
        User expectedUser = this.userService.createUser(userDetails);

        MutableUserDetails expectedUserDetails = (MutableUserDetails) expectedUser
                .getUserDetails();
        UserDetails actual = this.userDetailsService
                .loadUserByUsername(userDetails.getUsername());
        assertEquals(expectedUserDetails, actual);
    }

    /**
     * @param authorityDao
     *            the authorityDao to set
     */
    public void setAuthorityDao(
            GrantedAuthorityDao<MutableGrantedAuthority> authorityDao) {
        this.authorityDao = authorityDao;
    }

    /**
     * @param userDetailsService
     *            the userDetailsService to set
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(UserDao<User> userDao) {
        this.userDao = userDao;
    }

}
