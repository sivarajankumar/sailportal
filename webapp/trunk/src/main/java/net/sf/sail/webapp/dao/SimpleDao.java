/**
 * Copyright University of Toronto 2006 (c)
 */
package net.sf.sail.webapp.dao;

/**
 * Data Access Object (DAO) interface that defines simple generic operations for
 * dealing with a persistent store.
 * 
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public interface SimpleDao<T> {

  /**
   * Saves the object to a persistent data store.
   * 
   * @param object
   */
  public void save(T object);

  /**
   * Deletes the object from a persistent data store.
   * 
   * @param object
   */
  public void delete(T object);
}