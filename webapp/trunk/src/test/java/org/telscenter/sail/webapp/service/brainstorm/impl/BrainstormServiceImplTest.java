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
package org.telscenter.sail.webapp.service.brainstorm.impl;

import static org.easymock.EasyMock.*;
import org.telscenter.sail.webapp.dao.brainstorm.BrainstormDao;
import org.telscenter.sail.webapp.domain.brainstorm.Brainstorm;
import org.telscenter.sail.webapp.domain.brainstorm.answer.Answer;
import org.telscenter.sail.webapp.domain.brainstorm.answer.impl.AnswerImpl;
import org.telscenter.sail.webapp.domain.brainstorm.impl.BrainstormImpl;
import org.telscenter.sail.webapp.junit.AbstractTransactionalDbTests;
import org.telscenter.sail.webapp.service.brainstorm.BrainstormService;

/**
 * Tests for brainstorm service.
 * 
 * @author Hiroki Terashima
 * @version $Id:$
 */
public class BrainstormServiceImplTest extends AbstractTransactionalDbTests {

	private BrainstormService brainstormService;
	
	private BrainstormDao<Brainstorm> mockBrainstormDao;  // mock this
	
	@SuppressWarnings("unchecked")
	public void testAddAnswer() {
		// this test simply confirms that the dao is called appropriately,
		// since the DAO is being tested and does all the work
		Brainstorm brainstorm = new BrainstormImpl();
		Answer answer = new AnswerImpl();
		mockBrainstormDao = createMock(BrainstormDao.class);
		mockBrainstormDao.save(brainstorm);
		expectLastCall();
		replay(mockBrainstormDao);

		BrainstormServiceImpl brainstormServiceImpl = new BrainstormServiceImpl();
		brainstormServiceImpl.setBrainstormDao(mockBrainstormDao);
		brainstormServiceImpl.addAnswer(brainstorm, answer);
		assertEquals(1, brainstorm.getAnswers().size());
		assertTrue(brainstorm.getAnswers().contains(answer));
		verify(mockBrainstormDao);
	}
	
	/**
	 * @param brainstormService the brainstormService to set
	 */
	public void setBrainstormService(BrainstormService brainstormService) {
		this.brainstormService = brainstormService;
	}
}
