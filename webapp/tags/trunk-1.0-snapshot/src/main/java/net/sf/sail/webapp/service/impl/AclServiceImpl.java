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

import java.util.ArrayList;
import java.util.List;

import net.sf.sail.webapp.domain.Persistable;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.AclService;

import org.acegisecurity.Authentication;
import org.acegisecurity.acls.AccessControlEntry;
import org.acegisecurity.acls.MutableAcl;
import org.acegisecurity.acls.MutableAclService;
import org.acegisecurity.acls.NotFoundException;
import org.acegisecurity.acls.Permission;
import org.acegisecurity.acls.objectidentity.ObjectIdentity;
import org.acegisecurity.acls.objectidentity.ObjectIdentityImpl;
import org.acegisecurity.acls.sid.PrincipalSid;
import org.acegisecurity.acls.sid.Sid;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Required;

/**
 * A class which allows creation of access control lists for any object.
 * 
 * @author Laurel Williams
 * 
 * @version $Id$
 */
public class AclServiceImpl<T extends Persistable> implements AclService<T> {

	private MutableAclService mutableAclService;

	/**
	 * @param mutableAclService
	 *            the mutableAclService to set
	 */
	@Required
	public void setMutableAclService(MutableAclService mutableAclService) {
		this.mutableAclService = mutableAclService;
	}

	/**
	 * @see net.sf.sail.webapp.service.AclService#addPermission(java.lang.Object)
	 */
	public void addPermission(T object, Permission permission) {
		if (object != null) {	
			MutableAcl acl = null;
			ObjectIdentity objectIdentity = new ObjectIdentityImpl(object.getClass(), object
					.getId());

	        try {
	            acl = (MutableAcl) mutableAclService.readAclById(objectIdentity);
	        } catch (NotFoundException nfe) {
	            acl = mutableAclService.createAcl(objectIdentity);
	        }
			acl.insertAce(null, permission,
					new PrincipalSid(this.getAuthentication()), true);
			this.mutableAclService.updateAcl(acl);
		} else {
			throw new IllegalArgumentException(
					"Cannot create ACL. Object not set.");
		}
	}

	/**
	 * @see net.sf.sail.webapp.service.AclService#addPermission(java.lang.Object, org.acegisecurity.acls.Permission, org.acegisecurity.Authentication)
	 */
	public void addPermission(T object, Permission permission, User user) {
		if (object != null) {	
			MutableAcl acl = null;
			ObjectIdentity objectIdentity = new ObjectIdentityImpl(object.getClass(), object
					.getId());
			
	        try {
	            acl = (MutableAcl) mutableAclService.readAclById(objectIdentity);
	        } catch (NotFoundException nfe) {
	            acl = mutableAclService.createAcl(objectIdentity);
	        }
			acl.insertAce(null, permission,
					new PrincipalSid(user.getUserDetails().getUsername()), true);
			this.mutableAclService.updateAcl(acl);
		} else {
			throw new IllegalArgumentException(
					"Cannot create ACL. Object not set.");
		}
	}

	/**
	 * @see net.sf.sail.webapp.service.AclService#removePermission(java.lang.Object, org.acegisecurity.acls.Permission, net.sf.sail.webapp.domain.User)
	 */
	public void removePermission(T object, Permission permission, User user) {
		if (object != null) {	
			MutableAcl acl = null;
			ObjectIdentity objectIdentity = new ObjectIdentityImpl(object.getClass(), object
					.getId());
			Sid[] sid = {new PrincipalSid(user.getUserDetails().getUsername())};
			
	        try {
	            acl = (MutableAcl) mutableAclService.readAclById(objectIdentity, sid);
	        } catch (NotFoundException nfe) {
	        	return;
	        }
	        AccessControlEntry[] aces = acl.getEntries();
	        for (AccessControlEntry ace : aces) {
	        	if (ace.getPermission().equals(permission) && ace.getSid().equals(sid[0])) {
	        		acl.deleteAce(ace.getId());
	        	}
	        }
			this.mutableAclService.updateAcl(acl);
		} else {
			throw new IllegalArgumentException(
					"Cannot delete ACL. Object not set.");
		}		
	}

	private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

	public List<Permission> getPermissions(T object, User user) {
		List<Permission> permissions = new ArrayList<Permission>();
		if (object != null) {	
			MutableAcl acl = null;
			ObjectIdentity objectIdentity = new ObjectIdentityImpl(object.getClass(), object
					.getId());
			Sid[] sid = {new PrincipalSid(user.getUserDetails().getUsername())};
			
	        try {
	            acl = (MutableAcl) mutableAclService.readAclById(objectIdentity, sid);
	        } catch (NotFoundException nfe) {
	        	return permissions;
	        }
	        AccessControlEntry[] aces = acl.getEntries();
	        for (AccessControlEntry ace : aces) {
	        	if (ace.getSid().equals(sid[0])) {
	        		permissions.add(ace.getPermission());
	        	}
	        }
	        return permissions;
		} else {
			throw new IllegalArgumentException(
					"Cannot retrieve ACL. Object not set.");
		}		
	}
}
