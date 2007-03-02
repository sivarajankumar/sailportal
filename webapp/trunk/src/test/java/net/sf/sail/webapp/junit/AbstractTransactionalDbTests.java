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

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author Cynick Young
 * 
 * @version $Id: AbstractTransactionalDbTests.java 11 2006-11-08 19:22:53Z
 *          cynick $
 *          
 * Allows testers to perform data store integration tests. Provides transactions and access to the Spring Beans.
 * 
 */
public abstract class AbstractTransactionalDbTests extends
    AbstractTransactionalDataSourceSpringContextTests implements SpringConfiguration {

  protected HibernateFlusher toilet;

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpBeforeTransaction()
   */
  @Override
  protected void onSetUpBeforeTransaction() throws Exception {
    super.onSetUpBeforeTransaction();
    this.toilet = new HibernateFlusher();
    this.toilet.setSessionFactory((SessionFactory) this.applicationContext
        .getBean("sessionFactory"));
  }

  /**
   * @see org.springframework.test.AbstractSingleSpringContextTests#getConfigLocations()
   */
  @Override
  protected String[] getConfigLocations() {
    return CONFIG_LOCATIONS;
  }
}