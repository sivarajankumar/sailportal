/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.junit;

import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author Cynick Young
 * 
 * @version $Id$
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