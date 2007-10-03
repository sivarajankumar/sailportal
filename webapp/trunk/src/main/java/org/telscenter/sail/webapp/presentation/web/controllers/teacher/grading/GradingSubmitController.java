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
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.emf.sailuserdata.EAnnotation;
import net.sf.sail.emf.sailuserdata.EAnnotationGroup;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.domain.annotation.AnnotationBundle;
import net.sf.sail.webapp.service.annotation.AnnotationBundleService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.eclipse.emf.common.util.EList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.service.grading.GradingService;

/**
 * A Controller for TELS's that processes a teachers annotation
 *
 * @author Anthony Perritano
 * @version $Id: $
 */
public class GradingSubmitController extends AbstractController {

	public static final String WORKGROUP_ID = "workgroupId";
	public static final String RIM_NAME = "rimName";
	public static final String ANNOTATION_CONTENT = "annotationContent";
	public static final String POD_ID = "podId";
	private GradingService gradingService;
	private AnnotationBundleService annotationBundleService;
	private WorkgroupService workgroupService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String podId = request.getParameter(POD_ID);
		String annotationContent = request.getParameter(ANNOTATION_CONTENT);
		String rimName = request.getParameter(RIM_NAME);
		String runId = request.getParameter(GradeByStepController.RUN_ID);
		String workgroupId = request.getParameter(WORKGROUP_ID);
		
		if( podId != null ) {
			System.out.println("The Pod ID is "+podId);
			System.out.println("The ANNOTATION Content is "+annotationContent);
			System.out.println("The RIM_Name Content is "+rimName);
			System.out.println("The runId Content is "+runId);
			System.out.println("The workgroupId Content is "+workgroupId);
			//GradingService gs = new GradingServiceImpl();
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject(POD_ID, podId);
			
			
			///retrieve
			Workgroup workgroup = workgroupService.retrieveById(new Long(workgroupId));
			
			AnnotationBundle annotationBundle = annotationBundleService.getAnnotationBundle(workgroup);
			EList annotationGroups = annotationBundle.getEAnnotationBundle().getAnnotationGroups();
			
			for (Iterator agIt = annotationGroups.iterator(); agIt
					.hasNext();) {
				EAnnotationGroup agroup = (EAnnotationGroup) agIt.next();
				EList annotations = agroup.getAnnotations();
				
				for (Iterator annosIT = annotations.iterator(); annosIT
						.hasNext();) {
					EAnnotation annotation = (EAnnotation) annosIT.next();
					if( annotation.getEntityUUID().toString().equals(podId) && annotation.getEntityName().equals(rimName)) {
						annotation.setContents(annotationContent);
						List<AnnotationBundle> al = new ArrayList<AnnotationBundle>();
						al.add(annotationBundle);
						this.gradingService.saveGrades(al);
					}// if
				}
				
			}
			
			
			//save the bundle
			
			//this.gradingService.saveGrades(null);
			return modelAndView;
		} else {
			
			//throw error
			
		}// if
		
		ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
	}

	public GradingService getGradingService() {
		return gradingService;
	}

	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

	public AnnotationBundleService getAnnotationBundleService() {
		return annotationBundleService;
	}

	public void setAnnotationBundleService(
			AnnotationBundleService annotationBundleService) {
		this.annotationBundleService = annotationBundleService;
	}

	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

}