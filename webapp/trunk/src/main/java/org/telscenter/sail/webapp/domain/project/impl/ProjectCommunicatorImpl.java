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
package org.telscenter.sail.webapp.domain.project.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Implement;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.project.ExternalProject;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectCommunicator;

/**
 * @author hirokiterashima
 * @version $Id$
 */
@Entity
@Table(name = ProjectCommunicatorImpl.DATA_STORE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
public class ProjectCommunicatorImpl implements ProjectCommunicator {

	private String baseUrl = "http://loops.diy.concord.org";
	
	private static final String PREVIEW_DIY_PROJECT_SUFFIX = "/sail_jnlp/6/1/authoring";

	private static final String DATA_STORE_NAME = "projectcommunicators";
	
	public List<ExternalProject> getProjectList() {
		
		String getProjectListUrlStr = baseUrl + "/external_otrunk_activities.xml";
		Document doc = null;
		// retrieve xml and parse
		try {
			URL getProjectListUrl = new URL(getProjectListUrlStr);
			URLConnection getProjectListConnection = getProjectListUrl.openConnection();
			DataInputStream dis;
			String inputLine;

			dis = new DataInputStream(getProjectListConnection.getInputStream());
			doc = this.convertXmlInputStreamToXmlDocument(dis);
//			while ((inputLine = dis.readLine()) != null) {
//				
//			}
			dis.close();
		} catch (MalformedURLException me) {
			System.out.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
		
		List<ExternalProject> diyProjects = new ArrayList<ExternalProject>();
		Element diy = doc.getRootElement();
		List<Element> children = diy.getChildren("external-otrunk-activity");
		for (Element child : children) {
			// create a DIY Project, add to the List
			String name = child.getChildText("name");
			ExternalProjectImpl project = new ExternalProjectImpl();
			project.setName(name);
			Serializable externalDIYId = child.getChildText("id");
			project.setExternalId(externalDIYId );
			diyProjects.add(project);
		}
		
		return diyProjects;
	}        
	
	protected Document convertXmlInputStreamToXmlDocument(InputStream inputStream) {
	    SAXBuilder builder = new SAXBuilder();
	    Document doc = null;
	    try {
	        doc = builder.build(inputStream);
	    } catch (JDOMException e) {
	    } catch (IOException e) {
	    } finally {
	        try {
	            inputStream.close();
	        } catch (IOException e) {
	        }
	    }
	    return doc;
	}

	public Object previewProject(ExternalProject externalProject) {
		Serializable id = externalProject.getExternalId();
		String previewProjectUrl = baseUrl + "/external_otrunk_activities/" + id + PREVIEW_DIY_PROJECT_SUFFIX;
		return new ModelAndView(new RedirectView(previewProjectUrl ));
	}

	
}
