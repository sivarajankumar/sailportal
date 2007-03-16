/**
 * TODO: replace with TELS PORTAL copyright information
 */
package org.telscenter.sail.webapp.dao.authentication.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.acegisecurity.GrantedAuthority;
import org.springframework.dao.DataIntegrityViolationException;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails;

import net.sf.sail.webapp.dao.authentication.impl.HibernateGrantedAuthorityDao;
import net.sf.sail.webapp.dao.authentication.impl.HibernateUserDetailsDao;
import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.impl.PersistentGrantedAuthority;
import net.sf.sail.webapp.domain.authentication.impl.PersistentUserDetails;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

/**
 * Test class for TeacherUserDetails
 * 
 * TODO: decide if this is a test class for TeacherUserDetails or TELS's MutableUserDetails
 * right now, it's testing TELS's MutableUserDetails using TeacherUserDetails 
 * 
 * @author Hiroki Terashima
 *
 * @version $Id: $
 *
 */
public class HibernateTeacherUserDetailsDaoTest extends
		AbstractTransactionalDbTests {

	private static final String DEFAULT_ROLE_1 = "default_role_1";

	private static final String DEFAULT_ROLE_2 = "default_role_2";

	private static final String DEFAULT_ROLE_3 = "default_role_3";

	private static final String DEFAULT_USERNAME = "HirokiT619";

	private static final String DEFAULT_PASSWORD = "my secret";

	private static final String DEFAULT_EMAIL = "billy@bob.com";

	private static final String DEFAULT_FIRSTNAME = "Hiroki";

	private static final String DEFAULT_LASTNAME = "Terashima";

	private static final String USERNAME_NOT_IN_DB = "blah";

	private MutableGrantedAuthority role1;

	private MutableGrantedAuthority role2;

	private MutableGrantedAuthority role3;

	private MutableUserDetails defaultUserDetails;

	private HibernateGrantedAuthorityDao authorityDao;

	private HibernateUserDetailsDao userDetailsDao;

	/**
	 * @param authorityDao
	 *          the authorityDao to set
	 */
	public void setAuthorityDao(HibernateGrantedAuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	/**
	 * @param userDetailsDao
	 *          the userDetailsDao to set
	 */
	public void setUserDetailsDao(HibernateUserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
	 */
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		this.role1 = (MutableGrantedAuthority) this.applicationContext
		.getBean("mutableGrantedAuthority");
		this.role2 = (MutableGrantedAuthority) this.applicationContext
		.getBean("mutableGrantedAuthority");
		this.role3 = (MutableGrantedAuthority) this.applicationContext
		.getBean("mutableGrantedAuthority");
		this.role1.setAuthority(DEFAULT_ROLE_1);
		this.role2.setAuthority(DEFAULT_ROLE_2);
		this.role3.setAuthority(DEFAULT_ROLE_3);

		this.defaultUserDetails = (org.telscenter.sail.webapp.domain.authentication.impl.TeacherUserDetails) this.applicationContext
		.getBean("teacherUserDetails");
		this.defaultUserDetails.setUsername(DEFAULT_USERNAME);
		this.defaultUserDetails.setPassword(DEFAULT_PASSWORD);
		this.defaultUserDetails.setEmailAddress(DEFAULT_EMAIL);
		this.defaultUserDetails.setAuthorities(new GrantedAuthority[] { this.role1,
				this.role2, this.role3 });
		this.defaultUserDetails.setFirstname(DEFAULT_FIRSTNAME);
		this.defaultUserDetails.setLastname(DEFAULT_LASTNAME);
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpInTransaction()
	 */
	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		this.authorityDao.save(this.role1);
		this.authorityDao.save(this.role2);
		this.authorityDao.save(this.role3);
	}

	/**
	 * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
	 */
	@Override
	protected void onTearDownAfterTransaction() throws Exception {
		super.onTearDownAfterTransaction();
		this.role1 = null;
		this.role2 = null;
		this.role3 = null;
		this.defaultUserDetails = null;
	}

	public void testSave() {
		verifyUserandJoinTablesAreEmpty();
		
		this.userDetailsDao.save(this.defaultUserDetails);
		// flush is required to cascade the join table for some reason
		this.toilet.flush();
		
		// verify data store contains saved data using direct jdbc retrieval (not dao)
		assertEquals(1, retrieveUsersTableFromDb().size());
		assertEquals(3, retrieveUsersRolesTableFromDb().size());
		List actualList = retrieveUserDetailsListFromDb();
		assertEquals(3, actualList.size());
		
		List<String> defaultRolesList = new LinkedList<String>();
		defaultRolesList.add(DEFAULT_ROLE_1);
		defaultRolesList.add(DEFAULT_ROLE_2);
		defaultRolesList.add(DEFAULT_ROLE_3);
		
		for (int i = 0; i < actualList.size(); i++) {
			Map actualUserDetailsMap = (Map) actualList.get(i);
			// *NOTE* the keys in the map are all in UPPERCASE!
			String actualValue = (String) actualUserDetailsMap
			    .get(PersistentUserDetails.COLUMN_NAME_USERNAME.toUpperCase());
			assertEquals(DEFAULT_USERNAME, actualValue);
			actualValue = (String) actualUserDetailsMap
			    .get(PersistentUserDetails.COLUMN_NAME_PASSWORD.toUpperCase());
			assertEquals(DEFAULT_PASSWORD, actualValue);
			actualValue = (String) actualUserDetailsMap
			    .get(PersistentUserDetails.COLUMN_NAME_EMAIL_ADDRESS.toUpperCase());
			assertEquals(DEFAULT_EMAIL, actualValue);
			actualValue = (String) actualUserDetailsMap
			    .get(TeacherUserDetails.COLUMN_NAME_FIRSTNAME.toUpperCase());
			assertEquals(DEFAULT_FIRSTNAME , actualValue);
			actualValue = (String) actualUserDetailsMap
			    .get(TeacherUserDetails.COLUMN_NAME_LASTNAME.toUpperCase());
			assertEquals(DEFAULT_LASTNAME , actualValue);
			actualValue = (String) actualUserDetailsMap
			    .get(PersistentGrantedAuthority.COLUMN_NAME_ROLE.toUpperCase());
			assertTrue(defaultRolesList.contains(actualValue));
			defaultRolesList.remove(actualValue);
		}
		
		MutableUserDetails duplicateUserDetails = (MutableUserDetails) this.applicationContext
		    .getBean("teacherUserDetails");
		duplicateUserDetails.setUsername(DEFAULT_USERNAME);
		duplicateUserDetails.setPassword(DEFAULT_PASSWORD);
		try {
			this.userDetailsDao.save(duplicateUserDetails);
			fail("DataIntegrityViolationException expected");
		}
		catch (DataIntegrityViolationException expected) {
		}
		
		MutableUserDetails emptyUserDetails = (MutableUserDetails) this.applicationContext
		    .getBean("teacherUserDetails");
		try {
			this.userDetailsDao.save(emptyUserDetails);
			fail("expected DataIntegrityViolationException");
		}
		catch (DataIntegrityViolationException expected) {
		}
		
		MutableUserDetails partiallyEmptyUserDetails = (MutableUserDetails) this.applicationContext
		    .getBean("teacherUserDetails");
		partiallyEmptyUserDetails.setUsername(DEFAULT_USERNAME);
		try {
			this.userDetailsDao.save(partiallyEmptyUserDetails);
			fail("expected DataIntegrityViolationException");
		}
		catch (DataIntegrityViolationException expected) {
		}
		
		partiallyEmptyUserDetails = (MutableUserDetails) this.applicationContext
		    .getBean("teacherUserDetails");
		partiallyEmptyUserDetails.setPassword(DEFAULT_PASSWORD);
		try {
			this.userDetailsDao.save(partiallyEmptyUserDetails);
			fail("expected DataIntegrityViolationException");
		}
		catch (DataIntegrityViolationException expected) {
		}
	}
	
	public void testDelete() {
		this.verifyUserandJoinTablesAreEmpty();
		
		this.userDetailsDao.save(this.defaultUserDetails);
		// flush is required to cascade the join table for some reason
		this.toilet.flush();
		
		this.userDetailsDao.delete(this.defaultUserDetails);
		this.toilet.flush();
		
		this.verifyUserandJoinTablesAreEmpty();
		
		List actualList = this.retrieveRolesTableFromDb();
		assertEquals(3, actualList.size());
		
		List<String> defaultRolesList = new LinkedList<String>();
		defaultRolesList.add(DEFAULT_ROLE_1);
		defaultRolesList.add(DEFAULT_ROLE_2);
		defaultRolesList.add(DEFAULT_ROLE_3);
		
		for (int i = 0; i < actualList.size(); i++) {
			Map actualRolesMap = (Map) actualList.get(i);
			// *NOTE* the keys in the map are all in UPPERCASE!
			String actualValue = (String) actualRolesMap
			    .get(PersistentGrantedAuthority.COLUMN_NAME_ROLE.toUpperCase());
			assertTrue(defaultRolesList.contains(actualValue));
			defaultRolesList.remove(actualValue);
		}
	}
	
	public void testRetrieveByUsername() {
		this.verifyUserandJoinTablesAreEmpty();
		
		this.userDetailsDao.save(this.defaultUserDetails);
		// flush is required to cascade the join table for some reason
		this.toilet.flush();
		
		// get user details record from persistent store and confirm it is complete
		MutableUserDetails userDetails = (org.telscenter.sail.webapp.domain.authentication.MutableUserDetails) this.userDetailsDao
		    .retrieveByName(DEFAULT_USERNAME);
		
		assertEquals(DEFAULT_USERNAME, userDetails.getUsername());
		assertEquals(DEFAULT_PASSWORD, userDetails.getPassword());
		assertEquals(DEFAULT_EMAIL, userDetails.getEmailAddress());
		assertEquals(DEFAULT_FIRSTNAME, userDetails.getFirstname());
		assertEquals(DEFAULT_LASTNAME, userDetails.getLastname());
		
		List<String> defaultRolesList = new LinkedList<String>();
		defaultRolesList.add(DEFAULT_ROLE_1);
		defaultRolesList.add(DEFAULT_ROLE_2);
		defaultRolesList.add(DEFAULT_ROLE_3);
		
		GrantedAuthority[] grantedAuthorities = userDetails.getAuthorities();
		for (int i = 0; i < grantedAuthorities.length; i++) {
			String role = grantedAuthorities[i].getAuthority();
			assertTrue(defaultRolesList.contains(role));
			defaultRolesList.remove(role);
		}
		
		// choose random non-existent user name and try to retrieve
		assertNull(this.userDetailsDao.retrieveByName(USERNAME_NOT_IN_DB));
	}
	
	public void testHasUsername() {
		this.userDetailsDao.save(this.defaultUserDetails);
		assertTrue(this.userDetailsDao.hasUsername(DEFAULT_USERNAME));
		assertFalse(this.userDetailsDao.hasUsername(USERNAME_NOT_IN_DB));
	}

	private void verifyUserandJoinTablesAreEmpty() {
		assertTrue(this.retrieveUserDetailsListFromDb().isEmpty());
		assertTrue(this.retrieveUsersRolesTableFromDb().isEmpty());
	}

	private List retrieveUserDetailsListFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM "
				+ PersistentUserDetails.DATA_STORE_NAME + ", "
				+ TeacherUserDetails.DATA_STORE_NAME + ", "
				+ PersistentUserDetails.GRANTED_AUTHORITY_JOIN_TABLE_NAME + ", "
				+ PersistentGrantedAuthority.DATA_STORE_NAME + " WHERE "
				+ PersistentUserDetails.DATA_STORE_NAME + ".id = " 
				+ TeacherUserDetails.DATA_STORE_NAME + ".id "
				+ "AND " + TeacherUserDetails.DATA_STORE_NAME + ".id = " 
				+ PersistentUserDetails.GRANTED_AUTHORITY_JOIN_TABLE_NAME + ".user_fk "
				+ "AND " + PersistentGrantedAuthority.DATA_STORE_NAME + ".id = " 
				+ PersistentUserDetails.GRANTED_AUTHORITY_JOIN_TABLE_NAME + ".role_fk;", (Object[]) null);
	}

	private List retrieveUsersTableFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM "
				+ PersistentUserDetails.DATA_STORE_NAME, (Object[]) null);
	}
	
	private List retrieveRolesTableFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM "
				+ PersistentGrantedAuthority.DATA_STORE_NAME, (Object[]) null);
	}
	
	private List retrieveUsersRolesTableFromDb() {
		return this.jdbcTemplate.queryForList("SELECT * FROM "
				+ PersistentUserDetails.GRANTED_AUTHORITY_JOIN_TABLE_NAME,
				(Object[]) null);
	}
	
}
