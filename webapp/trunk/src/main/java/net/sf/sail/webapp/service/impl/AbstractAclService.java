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
package net.sf.sail.webapp.service.impl;

import net.sf.sail.webapp.domain.Persistable;
import net.sf.sail.webapp.service.AclService;

import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.domain.BasePermission;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;

/**
 * A class which allows creation of access control lists for groups.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class AbstractAclService<T extends Persistable> implements AclService<T> {

	private MutableAclService mutableAclService;

	/**
	 * @param mutableAclService
	 *            the mutableAclService to set
	 */
	@Required
	public void setMutableAclService(MutableAclService mutableAclService) {
		this.mutableAclService = mutableAclService;
	}

	public void createAcl(T object) {
		if (object != null) {
			MutableAcl acl = this.mutableAclService
					.createAcl(new ObjectIdentityImpl(object.getClass(), object
							.getId()));
			acl.insertAce(null, BasePermission.ADMINISTRATION,
					new PrincipalSid(SecurityContextHolder.getContext()
							.getAuthentication()), true);
			this.mutableAclService.updateAcl(acl);
		} else
			throw new IllegalArgumentException(
					"Cannot create ACL. Object not set.");
	}

}
