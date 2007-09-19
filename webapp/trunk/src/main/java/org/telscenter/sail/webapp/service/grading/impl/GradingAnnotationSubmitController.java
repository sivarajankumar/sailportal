package org.telscenter.sail.webapp.service.grading.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.EList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.pas.emf.pas.EActivity;
import org.telscenter.pas.emf.pas.ECurnitmap;
import org.telscenter.pas.emf.pas.EProject;
import org.telscenter.pas.emf.pas.EStep;
import org.telscenter.sail.webapp.domain.grading.GradeWorkByStepAggregate;
import org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading.GradeByStepController;
import org.telscenter.sail.webapp.service.grading.GradingService;

public class GradingAnnotationSubmitController extends AbstractController {

	private GradingService gradingService;

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		return null;
	}

	public GradingService getGradingService() {
		return gradingService;
	}

	public void setGradingService(GradingService gradingService) {
		this.gradingService = gradingService;
	}

}
