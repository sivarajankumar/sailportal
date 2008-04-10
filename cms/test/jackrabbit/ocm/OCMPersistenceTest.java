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
package jackrabbit.ocm;

import jackrabbit.ocm.model.PressRelease;
import jackrabbit.ocm.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import net.sf.sail.cms.curnit.CurnitOtmlImpl;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl;

/**
 * @author Hiroki Terashima
 * @author Rokham Sadeghnezhadfard
 * @version $Id$
 */
public class OCMPersistenceTest {
	
	public static final void main(String[] args) {
		//testPressReleaseOCM();
		testCurnitOCM();
	}
	
	private static void testCurnitOCM() {
		Repository repository;
		Session session = null;
		try {
			repository = new TransientRepository();
			session = repository.login(new SimpleCredentials("username", "password".toCharArray()));
			RepositoryUtil.createNamespace(session);
			RepositoryUtil.registerNodeTypes(session, "/Users/hirokiterashima/eclipseworkspaces/telsportalworkspace3.3/cms/src/main/resources/custom_nodetypes.xml");
			List<Class> classes = new ArrayList<Class>();	
			classes.add(CurnitOtmlImpl.class); // Call this method for each persistent class
					
			Mapper mapper = new AnnotationMapperImpl(classes);
			ObjectContentManager ocm =  new ObjectContentManagerImpl(session, mapper);
			
			// Insert an object
			System.out.println("Insert a CurnitOtml in the repository");
			CurnitOtmlImpl curnitOtml = new CurnitOtmlImpl();
			curnitOtml.setNumber("/123curnit");
			curnitOtml.setName("name");
						
			ocm.insert(curnitOtml);
			ocm.save();
						
			// Retrieve 
			System.out.println("Retrieve a CurnitOtml from the repository");
			curnitOtml = (CurnitOtmlImpl) ocm.getObject("/123curnit");
			System.out.println("Curnit Name : " + curnitOtml.getName());
						
			// Delete
			System.out.println("Remove a CurnitOtml from the repository");
			ocm.remove(curnitOtml);
			ocm.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.logout();
		}
	}

	private static void testPressReleaseOCM() {
		Repository repository;
		Session session = null;
		try {
			repository = new TransientRepository();
			session = repository.login(new SimpleCredentials("username", "password".toCharArray()));
			RepositoryUtil.createNamespace(session);
			RepositoryUtil.registerNodeTypes(session, "/Users/hirokiterashima/eclipseworkspaces/telsportalworkspace3.3/cms/src/main/resources/custom_nodetypes.xml");
			List<Class> classes = new ArrayList<Class>();	
			classes.add(PressRelease.class); // Call this method for each persistent class
					
			Mapper mapper = new AnnotationMapperImpl(classes);
			ObjectContentManager ocm =  new ObjectContentManagerImpl(session, mapper);
			
			// Insert an object
			System.out.println("Insert a press release in the repository");
			PressRelease pressRelease = new PressRelease();
			pressRelease.setPath("/newtutorial");
			pressRelease.setTitle("This is the first tutorial on OCM");
			pressRelease.setPubDate(new Date());
			pressRelease.setContent("Many Jackrabbit users ask to the dev team to make a tutorial on OCM");
						
			ocm.insert(pressRelease);
			ocm.save();
						
			// Retrieve 
			System.out.println("Retrieve a press release from the repository");
			pressRelease = (PressRelease) ocm.getObject("/newtutorial");
			System.out.println("PressRelease title : " + pressRelease.getTitle());
						
			// Delete
			System.out.println("Remove a press release from the repository");
			ocm.remove(pressRelease);
			ocm.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.logout();
		}
	}

}
