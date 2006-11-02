/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.junit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author Cynick Young
 * 
 * @version $Id: $
 * 
 */
public abstract class AbstractTransactionalDbTests extends
    AbstractTransactionalDataSourceSpringContextTests {

  private static final String[] CONFIG_LOCATIONS = new String[] {
      "classpath:applicationContext-datasource.xml",
      "classpath:applicationContext-hibernate.xml" };

  protected ApplicationContext springContext;

  protected Session hibernateSession;

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
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onSetUpInTransaction()
   */
  @Override
  protected void onSetUpInTransaction() throws Exception {
    super.onSetUpInTransaction();
    SessionFactory sessionFactory = (SessionFactory) springContext
        .getBean("sessionFactory");
    hibernateSession = sessionFactory.getCurrentSession();
  }

  /**
   * @see org.springframework.test.AbstractTransactionalSpringContextTests#onTearDownAfterTransaction()
   */
  @Override
  protected void onTearDownAfterTransaction() throws Exception {
    springContext = null;
  }
}
