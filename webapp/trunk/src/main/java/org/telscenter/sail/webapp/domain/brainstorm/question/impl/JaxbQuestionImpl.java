/**
 * Copyright (c) 2008 Regents of the University of California (Regents). Created
 * by TELS, Graduate School of Education, University of California at Berkeley.
 *
 * This software is distributed under the GNU Lesser General Public License, v2.
 *
 * Permission is hereby granted, without written agreement and without license
 * or royalty fees, to use, copy, modify, and distribute this software and its
 * documentation for any purpose, provided that the above copyright notice and
 * the following two paragraphs appear in all copies of this software.
 *
 * REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE. THE SOFTWAREAND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED
 * HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.telscenter.sail.webapp.domain.brainstorm.question.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import net.sf.sail.jaxb.extension.BlockInteractionType;
import net.sf.sail.jaxb.extension.JaxbQtiMarshallingUtils;

import org.imsglobal.xsd.imsqti_v2p0.AssessmentItemType;
import org.imsglobal.xsd.imsqti_v2p0.ExtendedTextInteractionType;

/**
 * @author Anthony Perritano
 * @version $Id$
 */
@Entity(name = QuestionImpl.DATA_STORE_NAME)
public class JaxbQuestionImpl extends QuestionImpl {

	/**
	 * The assessment Itema for this question
	 */
	protected AssessmentItemType assessmentItemType;
	
	/**
	 * an interaction can be a choice or interaction
	 */
	protected BlockInteractionType blockInteractionType;
	/**
	 * The body will be xml formatted JAXB QTI String. It will also unmarshall the XML
	 * 
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
		
		if(body == null)
			throw new NullPointerException();
			
		InputStream is = new ByteArrayInputStream(body.getBytes());
		try {
			assessmentItemType = JaxbQtiMarshallingUtils.unmarshallAssessmentItemType(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//parse the blockinteraction for this question it will only have one part
		//for now
		blockInteractionType = (BlockInteractionType) assessmentItemType.getItemBody().getBlockElementGroup().get(0);
		
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.question.impl.QuestionImpl#getAnswerFieldExpectedLines()
	 */
	public BigInteger getAnswerFieldExpectedLines() {
		
		if( blockInteractionType instanceof ExtendedTextInteractionType)
			return ((ExtendedTextInteractionType) blockInteractionType).getExpectedLines();
		
		return new BigInteger("0");
	}

	/**
	 * @see org.telscenter.sail.webapp.domain.brainstorm.question.impl.QuestionImpl#getPrompt()
	 */
	public String getPrompt() {
		List<Serializable> blockContent = blockInteractionType.getPrompt().getContent();
		
		if(blockContent != null) {
			return (String) blockContent.get(0);
		}
		return null;
	}
}
