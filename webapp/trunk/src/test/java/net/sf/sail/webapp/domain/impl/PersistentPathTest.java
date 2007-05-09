/**
 * Copyright (c) 2007 Encore Research Group, University of Toronto
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
package net.sf.sail.webapp.domain.impl;

import junit.framework.TestCase;
import net.sf.sail.webapp.domain.IllegalPathException;
import net.sf.sail.webapp.domain.Path;

/**
 * @author Cynick Young
 * 
 * @version $Id$
 * 
 */
public class PersistentPathTest extends TestCase {

    public void testSetParent() {
        Path path1 = new PersistentPath();
        String pathName1 = "path1";
        path1.setName(pathName1);
        Path path2 = new PersistentPath();
        String pathName2 = "path2";
        path2.setName(pathName2);

        try {
            path1.setParent(path1);
            fail("IllegalPathException expected");
        } catch (IllegalPathException expected) {
        }

        path1.setParent(path2);

        try {
            path1.setParent(path1);
            fail("IllegalPathException expected");
        } catch (IllegalPathException expected) {
        }
        try {
            path1.setParent(path2);
            fail("IllegalPathException expected");
        } catch (IllegalPathException expected) {
        }
    }

    /**
     * Test method for
     * {@link net.sf.sail.webapp.domain.impl.PersistentPath#toString()}.
     */
    public void testToString() {
        Path path1 = new PersistentPath();
        String pathName1 = "path1";
        path1.setName(pathName1);
        Path path2 = new PersistentPath();
        String pathName2 = "path2";
        path2.setName(pathName2);
        Path path3 = new PersistentPath();
        String pathName3 = "path3";
        path3.setName(pathName3);
        Path path4 = new PersistentPath();
        String pathName4 = "path4";
        path4.setName(pathName4);

        assertEquals("/" + pathName1, path1.toString());

        path1.setParent(path2);
        assertEquals("/" + pathName2 + "/" + pathName1, path1.toString());
        assertEquals("/" + pathName2, path2.toString());

        path2.setParent(path3);
        assertEquals("/" + pathName3 + "/" + pathName2 + "/" + pathName1, path1
                .toString());
        assertEquals("/" + pathName3 + "/" + pathName2, path2.toString());
        assertEquals("/" + pathName3, path3.toString());

        path3.setParent(path4);
        assertEquals("/" + pathName4 + "/" + pathName3 + "/" + pathName2 + "/"
                + pathName1, path1.toString());
        assertEquals("/" + pathName4 + "/" + pathName3 + "/" + pathName2, path2
                .toString());
        assertEquals("/" + pathName4 + "/" + pathName3, path3.toString());
        assertEquals("/" + pathName4, path4.toString());
    }
}