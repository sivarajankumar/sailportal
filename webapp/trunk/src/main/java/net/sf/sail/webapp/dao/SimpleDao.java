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
     * Factory method to create an instance of a data object that works with
     * this particular data access object (dao).
     * 
     * @return new instance of a data object
     */
    public T createDataObject();

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