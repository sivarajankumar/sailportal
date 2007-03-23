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
package net.sf.sail.webapp.service.curnit.impl;

import static org.easymock.EasyMock.createMock;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import net.sf.sail.webapp.dao.sds.SdsCurnitDao;
import net.sf.sail.webapp.domain.sds.SdsCurnit;

import org.easymock.EasyMock;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class CurnitServiceImplTest extends TestCase {

	private SdsCurnitDao mockSdsCurnitDao;

	private SdsCurnit sdsCurnit;

	private CurnitServiceImpl curnitServiceImpl;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		super.setUp();
		this.mockSdsCurnitDao = createMock(SdsCurnitDao.class);
		this.curnitServiceImpl = new CurnitServiceImpl();
		this.curnitServiceImpl.setSdsCurnitDao(this.mockSdsCurnitDao);

		this.sdsCurnit = new SdsCurnit();
		sdsCurnit.setSdsObjectId(1);
		sdsCurnit.setName("test");
		sdsCurnit.setUrl("this is a url");
	}

	/**
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.curnitServiceImpl = null;
		this.mockSdsCurnitDao = null;
		this.sdsCurnit = null;
	}

	public void testGetCurnitList() throws Exception {
		Set<SdsCurnit> expectedSdsCurnitSet = new HashSet<SdsCurnit>();
		expectedSdsCurnitSet.add(this.sdsCurnit);

		EasyMock.expect(mockSdsCurnitDao.getList()).andReturn(
				expectedSdsCurnitSet);
		EasyMock.replay(mockSdsCurnitDao);
		assertEquals(expectedSdsCurnitSet, curnitServiceImpl
				.getCurnitList());
		EasyMock.verify(mockSdsCurnitDao);
	}

	// tests that the command is delegated to the DAO and that the DAO is called
	// once
	public void testCreateCurnit() throws Exception {
		mockSdsCurnitDao.save(this.sdsCurnit);
		EasyMock.expectLastCall();
		EasyMock.replay(mockSdsCurnitDao);

		this.curnitServiceImpl.createCurnit(this.sdsCurnit);

		EasyMock.verify(mockSdsCurnitDao);
	}
}