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
package org.telscenter.sail.webapp.presentation.web.controllers.author.project;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.impl.PublishProjectMetadataParameters;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.domain.project.impl.ProjectMetadataImpl;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;
import org.telscenter.sail.webapp.service.project.ProjectService;

/**
 * @author patrick lawler
 * @version $Id:$
 */
public class PublishProjectMetadataController extends SimpleFormController{

	
	private ProjectService projectService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, 
			BindException errors) throws Exception {
		PublishProjectMetadataParameters params = (PublishProjectMetadataParameters) command;
		
		//check to make sure a project id was provided
		if(params.getProjectId() == null || params.getProjectId().equals("")){
			return new ModelAndView(getFormView());
		}
		
		//retrieve project
		Project project = this.projectService.getById(Long.parseLong(params.getProjectId()));
		
		//check for existing metadata object
		ProjectMetadata metadata = project.getMetadata();
		if(metadata == null){//if not create new and set in project
			metadata = new ProjectMetadataImpl();
			project.setMetadata(metadata);
		}
		
		//set metadata's values from params
		metadata.setTitle(params.getTitle());
		metadata.setAuthor(params.getAuthor());
		metadata.setSubject(params.getSubject());
		metadata.setSummary(params.getSummary());
		metadata.setGradeRange(params.getGraderange());
		metadata.setContact(params.getContact());
		metadata.setTechReqs(params.getTechreqs());
		metadata.setLessonPlan(params.getLessonplan());
		
		try{
			Long total = Long.parseLong(params.getTotaltime());
			metadata.setTotalTime(total);
		} catch(NumberFormatException e){}
		
		try{
			Long comp = Long.parseLong(params.getComptime());
			metadata.setCompTime(comp);
		} catch(NumberFormatException e){}

		//save project (and hence metadata)
		this.projectService.updateProject(project);
		
		ModelAndView mav = new ModelAndView(this.getSuccessView());
		return mav;
	}

	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String id = request.getParameter("projectId");
		if(id != null && id != ""){
			Project project = this.projectService.getById(Long.parseLong(id));
			ProjectMetadata metadata = project.getMetadata();
			if(metadata != null){
				String title = metadata.getTitle();
				String author = metadata.getAuthor();
				String subject = metadata.getSubject();
				String summary = metadata.getSummary();
				String graderange = metadata.getGradeRange();
				String contact = metadata.getContact();
				String techreqs = metadata.getTechReqs();
				String lessonplan = metadata.getLessonPlan();
				Long totaltime = metadata.getTotalTime();
				Long comptime = metadata.getCompTime();
				
				if(title != null && title != ""){
					model.put("currentTitle", title);
				}
				
				if(author != null && author != ""){
					model.put("currentAuthor", author);
				}
				
				if(subject != null && subject != ""){
					model.put("currentSubject", subject);
				}
				
				if(summary != null && summary != ""){
					model.put("currentSummary", summary);
				}
				
				if(graderange != null && graderange != ""){
					model.put("currentGraderange", graderange);
				}
				
				if(contact != null && contact != ""){
					model.put("currentContact", contact);
				}
				
				if(techreqs != null && techreqs != ""){
					model.put("currentTechreqs", techreqs);
				}
				
				if(lessonplan != null && lessonplan != ""){
					model.put("currentLessonplan", lessonplan);
				}
				
				if(totaltime != null){
					model.put("currentTotaltime", totaltime);
				}
				
				if(comptime != null){
					model.put("currentComptime", comptime);
				}
			}
		}
		
		return model;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		String meta = request.getParameter("metadata");
		JSONObject jsonMeta = new JSONObject(meta);
		PublishProjectMetadataParameters params = new PublishProjectMetadataParameters();
		
		params.setTitle(jsonMeta.getString("title"));
		params.setAuthor(jsonMeta.getString("author"));
		params.setSubject(jsonMeta.getString("subject"));
		params.setSummary(jsonMeta.getString("summary"));
		params.setGraderange(jsonMeta.getString("graderange"));
		params.setTotaltime(jsonMeta.getString("totaltime"));
		params.setComptime(jsonMeta.getString("comptime"));
		params.setContact(jsonMeta.getString("contact"));
		params.setTechreqs(jsonMeta.getString("techreqs"));
		params.setProjectId(request.getParameter("projectId"));
		params.setLessonplan(jsonMeta.getString("lessonplan"));
		
		return params;
	}
	
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
