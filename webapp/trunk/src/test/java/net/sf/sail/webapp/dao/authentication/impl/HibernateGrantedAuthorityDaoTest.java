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
package net.sf.sail.webapp.dao.authentication.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.authentication.MutableGrantedAuthority;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.acegisecurity.GrantedAuthority;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Cynick Young
 * 
 * @version $Id: HibernateGrantedAuthorityDaoTest.java 87 2007-01-25 19:46:04Z
 *          cynick $
 * 
 */
public class HibernateGrantedAuthorityDaoTest extends
    AbstractTransactionalDbTests {

  private static final String DEFAULT_ROLE = "default_role";

  private MutableGrantedAuthority defaultGrantedAuthority;

  private HibernateGrantedAuthorityDao authorityDao;

  /**
   * @param authorityDao
   *          the authorityDao to set
   */
  public void setAuthorityDao(HibernateGrantedAuthorityDao authorityDao) {
    this.authorityDao = authorityDao;
  }

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.defaultGrantedAuthority = (MutableGrantedAuthority) this.applicationContext
        .getBean("mutableGrantedAuthority");
    this.defaultGrantedAuthority.setAuthority(DEFAULT_ROLE);
  }

  /**
   * @see net.sf.sail.webapp.junit.AbstractTransactionalDbTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    super.onTearDownAfterTransaction();
    this.defaultGrantedAuthority = null;
  }

  public void testSave() {
    verifyDataStoreIsEmpty();

    // save the default granted authority object using dao
    this.authorityDao.save(this.defaultGrantedAuthority);

    // verify data store contains saved data using direct jdbc retrieval
    // (not using dao)
    List actualList = retrieveGrantedAuthorityListFromDb();
    assertEquals(1, actualList.size());

    Map actualGrantedAuthorityMap = (Map) actualList.get(0);
    // * NOTE* the keys in the map are all in UPPERCASE!
    String actualRole = (String) actualGrantedAuthorityMap.get("ROLE");
    assertEquals(DEFAULT_ROLE, actualRole);

    MutableGrantedAuthority emptyAuthority = (MutableGrantedAuthority) this.applicationContext
        .getBean("mutableGrantedAuthority");
    try {
      this.authorityDao.save(emptyAuthority);
      fail("DataIntegrityViolationException expected");
    }
    catch (DataIntegrityViolationException expected) {
    }

    MutableGrantedAuthority duplicateAuthority = (MutableGrantedAuthority) this.applicationContext
        .getBean("mutableGrantedAuthority");
    duplicateAuthority.setAuthority(DEFAULT_ROLE);
    // TODO - look into changing to DuplicateAuthorityException instead
    try {
      this.authorityDao.save(duplicateAuthority);
      fail("DataIntegrityViolationException expected");
    }
    catch (DataIntegrityViolationException expected) {
    }
  }

  public void testDelete() {
    verifyDataStoreIsEmpty();

    // save and delete the default granted authority object using dao
    this.authorityDao.save(this.defaultGrantedAuthority);
    this.authorityDao.delete(this.defaultGrantedAuthority);

    // * NOTE * must flush to test delete
    // see http://forum.springframework.org/showthread.php?t=18263 for
    // explanation
    this.authorityDao.getHibernateTemplate().flush();

    verifyDataStoreIsEmpty();
  }

  public void testRetrieve() {
    this.authorityDao.save(this.defaultGrantedAuthority);

    MutableGrantedAuthority actualAuthority = this.authorityDao
        .retrieveByName(DEFAULT_ROLE);
    assertEquals(this.defaultGrantedAuthority, actualAuthority);

    // choose random non-existent authority and try to retrieve
    assertNull(this.authorityDao.retrieveByName("blah"));

  }

  public void testCreateDataObject() {
    GrantedAuthority authority = this.authorityDao.createDataObject();
    assertTrue(authority instanceof MutableGrantedAuthority);
  }

  private void verifyDataStoreIsEmpty() {
    assertTrue(retrieveGrantedAuthorityListFromDb().isEmpty());
  }

  private List retrieveGrantedAuthorityListFromDb() {
    return this.jdbcTemplate.queryForList("select * from roles;",
        (Object[]) null);
  }
}