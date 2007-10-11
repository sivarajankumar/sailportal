/**
 * Copyright (c) 2007 Regents of the University of California (Regents). Created
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
package org.telscenter.sail.webapp.service.workgroup.impl;

import java.util.Set;

import net.sf.sail.webapp.dao.ObjectNotFoundException;
import net.sf.sail.webapp.dao.sds.HttpStatusCodeException;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.group.Group;
import net.sf.sail.webapp.domain.sds.SdsWorkgroup;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;
import net.sf.sail.webapp.service.workgroup.impl.WorkgroupServiceImpl;

import org.acegisecurity.acls.domain.BasePermission;
import org.springframework.transaction.annotation.Transactional;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.workgroup.WISEWorkgroup;
import org.telscenter.sail.webapp.domain.workgroup.impl.WISEWorkgroupImpl;
import org.telscenter.sail.webapp.service.grading.GradingService;
import org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService;

/**
 * @author Hiroki Terashima
 * @version $Id$
 */
public class WISEWorkgroupServiceImpl extends WorkgroupServiceImpl implements
		WISEWorkgroupService {

	private AnnotationBundleService annotationBundleService;
	
	private GradingService gradingService;
	
	/**
	 * @see org.telscenter.sail.webapp.service.workgroup.WISEWorkgroupService#createWISEWorkgroup(java.lang.String, java.util.Set, org.telscenter.sail.webapp.domain.Run, net.sf.sail.webapp.domain.group.Group)
	 */
	@Transactional(rollbackFor = { HttpStatusCodeException.class })
	public WISEWorkgroup createWISEWorkgroup(String name, Set<User> members,
			Run run, Group period) throws ObjectNotFoundException {

        SdsWorkgroup sdsWorkgroup = createSdsWorkgroup(name, members, run);

        WISEWorkgroup workgroup = createWISEWorkgroup(members, run, sdsWorkgroup, period);

        this.sdsWorkgroupDao.save(workgroup.getSdsWorkgroup());
        this.workgroupDao.save(workgroup);
        
        this.aclService.addPermission(workgroup, BasePermission.ADMINISTRATION);
        
        ECurnitmap curnitmap = gradingService.getCurnitmap(run.getId());
        
        this.annotationBundleService.createAnnotationBundle(workgroup, curnitmap);

        return workgroup;
	}

	/**
	 * A helper method to create a <code>WISEWorkgroup</code> given parameters
	 * 
	 * @param members set of users in this workgroup
	 * @param run the <code>Run</code> that this workgroup belongs in
	 * @param sdsWorkgroup <code>SdsWorkgroup</code> that this 
	 *     workgroup contains
	 * @param period <code>Group</code> that this workgroup belongs in
	 * @return the created <code>WISEWorkgroup</code>
	 */
	private WISEWorkgroup createWISEWorkgroup(Set<User> members, Run run,
			SdsWorkgroup sdsWorkgroup, Group period) {
		WISEWorkgroup workgroup = new WISEWorkgroupImpl();
		for (User member : members) {
			workgroup.addMember(member);
		}
		workgroup.setOffering(run);
		workgroup.setSdsWorkgroup(sdsWorkgroup);
		workgroup.setPeriod(period);
		return workgroup;
	}

	/**
	 * @param annotationService the annotationService to set
	 */
	public void setAnnotationBundleService(AnnotationBundleService annotationBundleService) {
		this.annotationBundleService = annotationBundleService;
	}

	/**
	 * @param gradingService the gradingService to set
	 */
	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

}
