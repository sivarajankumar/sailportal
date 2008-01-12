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
package org.telscenter.sail.webapp.service.module.impl;

import java.util.List;

import org.telscenter.sail.webapp.dao.module.ModuleDao;
import org.telscenter.sail.webapp.domain.Module;
import org.telscenter.sail.webapp.domain.impl.ModuleImpl;
import org.telscenter.sail.webapp.domain.impl.ModuleParameters;
import org.telscenter.sail.webapp.service.module.ModuleService;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.domain.Curnit;
import net.sf.sail.webapp.domain.impl.CurnitImpl;
import net.sf.sail.webapp.domain.impl.CurnitParameters;
import net.sf.sail.webapp.domain.sds.SdsCurnit;
import net.sf.sail.webapp.service.curnit.impl.CurnitServiceImpl;

/**
 *  Service for the TELS's Module Domain Object
 *  
 * @author Hiroki Terashima
 * @version $Id$
 */
public class ModuleServiceImpl extends CurnitServiceImpl implements
		ModuleService {
	
	private ModuleDao<Module> moduleDao;
	
	@Override
	public Module createCurnit(CurnitParameters curnitParameters) {
		ModuleParameters moduleParameters = (ModuleParameters) curnitParameters;
		SdsCurnit sdsCurnit = new SdsCurnit();
		sdsCurnit.setName(moduleParameters.getName());
		sdsCurnit.setUrl(moduleParameters.getUrl());
	    this.sdsCurnitDao.save(sdsCurnit);  
		
		Module module = new ModuleImpl();
		module.setSdsCurnit(sdsCurnit);
        this.moduleDao.save(module);
        return module;
	}

	@SuppressWarnings("unchecked")
	public List<Module> getProjectList() {
		return moduleDao.getList();
		//return (List<Module>) super.getCurnitList();
	}

	/**
	 * @see net.sf.sail.webapp.service.curnit.impl.CurnitServiceImpl#getById(java.lang.Long)
	 */
	@Override
	public Module getById(Long moduleId) throws ObjectNotFoundException {
		return moduleDao.getById(moduleId);
		//return moduleDao.getById(moduleId);
	}

	/**
	 * @param moduleDao the moduleDao to set
	 */
	public void setModuleDao(ModuleDao<Module> moduleDao) {
		this.moduleDao = moduleDao;
	}

}
