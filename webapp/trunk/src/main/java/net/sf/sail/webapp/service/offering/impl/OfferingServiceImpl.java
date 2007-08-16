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
package net.sf.sail.webapp.service.offering.impl;

import java.util.List;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.curnit.CurnitDao;
import net.sf.sail.webapp.dao.jnlp.JnlpDao;
import net.sf.sail.webapp.dao.offering.OfferingDao;
import net.sf.sail.webapp.dao.sds.SdsOfferingDao;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.Jnlp;
import net.sf.sail.webapp.domain.Offering;
import net.sf.sail.webapp.domain.impl.OfferingImpl;
import net.sf.sail.webapp.domain.impl.OfferingParameters;
import net.sf.sail.webapp.domain.sds.SdsOffering;
import net.sf.sail.webapp.domain.webservice.BadRequestException;
import net.sf.sail.webapp.domain.webservice.NetworkTransportException;
import net.sf.sail.webapp.service.AclService;
import net.sf.sail.webapp.service.offering.OfferingService;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class OfferingServiceImpl implements OfferingService {

	private OfferingDao<Offering> offeringDao;

	protected CurnitDao<Curnit> curnitDao;

	protected JnlpDao<Jnlp> jnlpDao;

	protected SdsOfferingDao sdsOfferingDao;

	protected AclService<Offering> aclService;

	/**
	 * @param offeringDao
	 *            the offeringDao to set
	 */
	@Required
	public void setOfferingDao(OfferingDao<Offering> offeringDao) {
		this.offeringDao = offeringDao;
	}

	/**
	 * @param sdsOfferingDao
	 *            the sdsOfferingDao to set
	 */
	@Required
	public void setSdsOfferingDao(SdsOfferingDao sdsOfferingDao) {
		this.sdsOfferingDao = sdsOfferingDao;
	}

	/**
	 * @param curnitDao
	 *            the curnitDao to set
	 */
	@Required
	public void setCurnitDao(CurnitDao<Curnit> curnitDao) {
		this.curnitDao = curnitDao;
	}

	/**
	 * @param jnlpDao
	 *            the jnlpDao to set
	 */
	@Required
	public void setJnlpDao(JnlpDao<Jnlp> jnlpDao) {
		this.jnlpDao = jnlpDao;
	}

	/**
	 * @param offeringAclService
	 *            the offeringAclService to set
	 */
	@Required
	public void setAclService(AclService<Offering> aclService) {
		this.aclService = aclService;
	}

	/**
	 * @see net.sf.sail.webapp.service.offering.OfferingService#getOfferingList()
	 */
	public List<Offering> getOfferingList() {
		return offeringDao.getList();
	}

	/**
	 * @throws ObjectNotFoundException 
	 * @see net.sf.sail.webapp.service.offering.OfferingService#getOffering(java.lang.Long)
	 */
	public Offering getOffering(Long id) throws ObjectNotFoundException {
		return offeringDao.getById(id);
	}

	/**
	 * @see net.sf.sail.webapp.service.offering.OfferingService#createOffering(Offering)
	 */
	@Transactional(rollbackFor = { BadRequestException.class,
			NetworkTransportException.class, ObjectNotFoundException.class })
	public Offering createOffering(OfferingParameters offeringParameters)
			throws ObjectNotFoundException {
		SdsOffering sdsOffering = generateSdsOfferingFromParameters(offeringParameters);
		Offering offering = new OfferingImpl();
		offering.setSdsOffering(sdsOffering);
		this.offeringDao.save(offering);

		this.aclService.createAcl(offering);

		return offering;
	}

	protected SdsOffering generateSdsOfferingFromParameters(
			OfferingParameters offeringParameters)
			throws ObjectNotFoundException {
		SdsOffering sdsOffering = new SdsOffering();
		sdsOffering.setName(offeringParameters.getName());
		Curnit curnit = this.curnitDao
				.getById(offeringParameters.getCurnitId());
		sdsOffering.setSdsCurnit(curnit.getSdsCurnit());

		// TODO LAW this will need to be rethought this gets the "default jnlp"
		// assuming there is only one jnlp for everything
		List<Jnlp> jnlpList = this.jnlpDao.getList();
		Jnlp jnlp = jnlpList.get(0);
		sdsOffering.setSdsJnlp(jnlp.getSdsJnlp());
		this.sdsOfferingDao.save(sdsOffering);
		return sdsOffering;
	}

}