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
 */package net.sf.sail.webapp.controllers;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Laurel Williams
 *
 * @version $Id$
 */
public class SignupControllerTest extends TestCase {

    private ApplicationContext applicationContext = null;

    public void setUp() throws Exception {
    	super.setUp();
    	applicationContext = new FileSystemXmlApplicationContext("src/main/WEB-INF/dispatcher-servlet.xml");
    }

    public void testOnSubmit() throws Exception {
        @SuppressWarnings("unused")
		SignupController signupController = (SignupController) applicationContext.getBean("signupController");
        //TODO not sure what to test for as yet.
    }

	protected void tearDown() throws Exception {
		super.tearDown();
		applicationContext = null;
	}
    
    

}
