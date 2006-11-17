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
package net.sf.sail.webapp.junit;

import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author Cynick Young
 * 
 * @version $Id: AbstractTransactionalDbTests.java 11 2006-11-08 19:22:53Z
 *          cynick $
 * 
 */
public abstract class AbstractTransactionalDbTests extends
    AbstractTransactionalDataSourceSpringContextTests {

  private static final String[] CONFIG_LOCATIONS = new String[] {
      "classpath:applicationContext-security.xml",
      "classpath:applicationContext-hibernate.xml",
      "classpath:applicationContext-datasource.xml" };

  protected ApplicationContext springContext;

  /**
   * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
   */
  @Override
  protected String[] getConfigLocations() {
    return CONFIG_LOCATIONS;
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    springContext = this.getContext(CONFIG_LOCATIONS);
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    springContext = null;
  }
}