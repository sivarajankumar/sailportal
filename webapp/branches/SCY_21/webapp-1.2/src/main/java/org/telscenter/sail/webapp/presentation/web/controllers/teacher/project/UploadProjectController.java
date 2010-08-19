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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Module;
import org.telscenter.sail.webapp.domain.admin.OtmlFileUpload;
import org.telscenter.sail.webapp.domain.impl.ModuleImpl;
import org.telscenter.sail.webapp.domain.impl.OtmlModuleImpl;
import org.telscenter.sail.webapp.domain.impl.RooloOtmlModuleImpl;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectUpload;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * For letting teachers upload projects.
 * @author hirokiterashima
 * @version $Id:$
 */
public class UploadProjectController extends SimpleFormController {

	private ProjectService projectService;

	/**
	 * @override @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ProjectUpload bean = (ProjectUpload) command;
		MultipartFile file = bean.getFile();
		
		// upload the zipfile
		String uploadDirStr = getServletContext().getRealPath("/upload");
		File uploadDir = new File(uploadDirStr);
		uploadDir.mkdirs();
		String sep = System.getProperty("file.separator");
		String newFilename = uploadDirStr + sep + file.getOriginalFilename();
		File uploadedFile = new File(newFilename);
		uploadedFile.createNewFile();
		FileCopyUtils.copy(file.getBytes(),uploadedFile);
		
		
		// unzip the zip file
		 try {
		      ZipFile zipFile = new ZipFile(newFilename);

		      Enumeration entries = zipFile.entries();

		      while(entries.hasMoreElements()) {
		        ZipEntry entry = (ZipEntry)entries.nextElement();

		        if(entry.isDirectory()) {
		          // Assume directories are stored parents first then children.
		          System.err.println("Extracting directory: " + entry.getName());
		          // This is not robust, just for demonstration purposes.
		          (new File(uploadDirStr + sep + entry.getName())).mkdir();
		          continue;
		        }

		        System.err.println("Extracting file: " + entry.getName());
		        copyInputStream(zipFile.getInputStream(entry),
		           new BufferedOutputStream(new FileOutputStream(uploadDirStr + sep + entry.getName())));
		      }

		      zipFile.close();
		    } catch (IOException ioe) {
		      System.err.println("Unhandled exception:");
		      ioe.printStackTrace();
		    }
				
		ModelAndView modelAndView = new ModelAndView(new RedirectView(getSuccessView()));		
		return modelAndView;
	}
	
	  public static final void copyInputStream(InputStream in, OutputStream out)
	  throws IOException
	  {
	    byte[] buffer = new byte[1024];
	    int len;

	    while((len = in.read(buffer)) >= 0)
	      out.write(buffer, 0, len);

	    in.close();
	    out.close();
	  }

	/**
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
}
