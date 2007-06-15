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
package net.sf.sail.webapp.service.jnlp.impl;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.jnlp.JnlpDao;
import net.sf.sail.webapp.dao.sds.SdsJnlpDao;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.impl.JnlpImpl;
import net.sf.sail.webapp.domain.impl.JnlpParameters;
import net.sf.sail.webapp.domain.sds.SdsJnlp;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class JnlpServiceImplTest extends TestCase {
	
	private static final String JNLP_NAME= "jnlpname";
	
	private static final String JNLP_URL = "URL";

    private SdsJnlpDao mockSdsJnlpDao;

     private JnlpDao<Jnlp> mockJnlpDao;

    private JnlpServiceImpl jnlpServiceImpl;

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception {
        super.setUp();
        this.jnlpServiceImpl = new JnlpServiceImpl();

        this.mockSdsJnlpDao = EasyMock.createMock(SdsJnlpDao.class);
        this.jnlpServiceImpl.setSdsJnlpDao(this.mockSdsJnlpDao);

        this.mockJnlpDao = EasyMock.createMock(JnlpDao.class);
        this.jnlpServiceImpl.setJnlpDao(this.mockJnlpDao);
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.jnlpServiceImpl = null;
        this.mockSdsJnlpDao = null;
        this.mockJnlpDao = null;
    }

    public void testGetJnlpList() throws Exception {
        List<Jnlp> expectedList = new LinkedList<Jnlp>();
        expectedList.add(new JnlpImpl());

        EasyMock.expect(this.mockJnlpDao.getList()).andReturn(expectedList);
        EasyMock.replay(this.mockJnlpDao);
        assertEquals(expectedList, jnlpServiceImpl.getJnlpList());
        EasyMock.verify(this.mockJnlpDao);
    }

    public void testCreateJnlp() throws Exception {
    	//TODO LAW get JnlpParmeters from bean?
    	JnlpParameters jnlpParameters = new JnlpParameters();
    	jnlpParameters.setName(JNLP_NAME);
    	jnlpParameters.setUrl(JNLP_URL);
    	
        Jnlp jnlp = this.jnlpServiceImpl.createJnlp(jnlpParameters);
        SdsJnlp sdsJnlp = jnlp.getSdsJnlp();
        assertEquals(JNLP_NAME, sdsJnlp.getName());
        assertEquals(JNLP_URL, sdsJnlp.getUrl());
        
    }

 
}