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
package net.sf.sail.webapp.dao.sds.impl;

import java.util.List;
import java.util.Map;

import net.sf.sail.webapp.domain.sds.SdsUser;
import net.sf.sail.webapp.junit.AbstractTransactionalDbTests;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class HibernateSdsUserDaoTest extends AbstractTransactionalDbTests {

  private SdsUser sdsUser = null;

  private HibernateSdsUserDao sdsUserDao = null;

  private final Integer DEFAULT_USER_ID = 0;

  /**
   * @param sdsUserDao
   *          the sdsUserDao to set
   */
  public void setSdsUserDao(HibernateSdsUserDao sdsUserDao) {
    this.sdsUserDao = sdsUserDao;
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.sdsUser = (SdsUser) this.applicationContext.getBean("sdsUser");
    this.sdsUser.setUserId(DEFAULT_USER_ID);
    this.sdsUser.setFirstName("blah");
    this.sdsUser.setLastName("blah");
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
   */
  @Override
  protected void onSetUpInTransaction() throws Exception {
    super.onSetUpInTransaction();
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    super.onTearDownAfterTransaction();
    this.sdsUser = null;
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownInTransaction()
   */
  @Override
  protected void onTearDownInTransaction() throws Exception {
    super.onTearDownInTransaction();
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HibernateSdsUserDao#delete(net.sf.sail.webapp.domain.sds.SdsUser)}.
   */
  public void testDelete() {
    verifyDataStoreIsEmpty();

    // save and delete the sdsuser object using dao
    this.sdsUserDao.save(this.sdsUser);
    this.sdsUserDao.delete(this.sdsUser);

    // * NOTE * must flush to test delete
    // see http://forum.springframework.org/showthread.php?t=18263 for
    // explanation
    this.flusher.flush();

    verifyDataStoreIsEmpty();
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HibernateSdsUserDao#retrieveByName(java.lang.String)}.
   */
  public void testRetrieveByName() {
    try {
      this.sdsUserDao.retrieveByName("blah");
      fail("Expected exception");
    }
    catch (UnsupportedOperationException e) {
    }
  }

  /**
   * Test method for
   * {@link net.sf.sail.webapp.dao.sds.impl.HibernateSdsUserDao#save(net.sf.sail.webapp.domain.sds.SdsUser)}.
   */
  public void testSave() {
    verifyDataStoreIsEmpty();
    // save using dao
    this.sdsUserDao.save(this.sdsUser);

    // retrieve data without using dao
    List actualList = this.retrieveSdsUserListFromDb();
    assertEquals(1, actualList.size());

    Map actualSdsUserMap = (Map) actualList.get(0);
    Integer sdsUserId = (Integer) actualSdsUserMap.get(SdsUser.COLUMN_NAME_USER_ID.toUpperCase());
    assertEquals(DEFAULT_USER_ID, sdsUserId);

    SdsUser duplicateSdsUser = (SdsUser) this.applicationContext
        .getBean("sdsUser");
    duplicateSdsUser.setFirstName(this.sdsUser.getFirstName());
    duplicateSdsUser.setLastName(this.sdsUser.getLastName());
    duplicateSdsUser.setUserId(this.sdsUser.getUserId());
    try {
      this.sdsUserDao.save(duplicateSdsUser);
      fail("DataIntegrityViolationException expected");
    }
    catch (DataIntegrityViolationException expected) {
    }

    SdsUser emptySdsUser = (SdsUser) this.applicationContext.getBean("sdsUser");
    try {
      this.sdsUserDao.save(emptySdsUser);
      fail("DataIntegrityViolationException expected");
    }
    catch (DataIntegrityViolationException expected) {
    }
    // TODO - look into changing to DuplicateAuthorityException instead
  }

  private void verifyDataStoreIsEmpty() {
    assertTrue(retrieveSdsUserListFromDb().isEmpty());
  }

  private List retrieveSdsUserListFromDb() {
    return this.jdbcTemplate.queryForList("select * from " + SdsUser.DATA_STORE_NAME,
        (Object[]) null);
  }
}