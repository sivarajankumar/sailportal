/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.grading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.domain.Workgroup;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author Patrick
 *
 */
public class ProjectPickerGradingController extends AbstractController {

	private RunService runService;

	private static final String VIEW_NAME = "teacher/grading/projectPickerGrading";

	protected static final String CURRENT_RUN_LIST_KEY = "current_run_list";
	
	protected static final String ARCHIVED_RUN_LIST_KEY = "archived_run_list";

	protected static final String GRADE_BY_TYPE_REQUEST_PARAM_NAME = "gradeByType";
	
	private static String GRADING_PAGE_BASE_URL = "gradingpage";
	
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

    	ModelAndView modelAndView = new ModelAndView(VIEW_NAME);
    	ControllerUtil.addUserToModelAndView(request, modelAndView);
 
		User user = (User) modelAndView.getModel().get(ControllerUtil.USER_KEY);
		List<Run> runList = this.runService.getRunList();
		// this is a temporary solution to filtering out runs that the logged-in user owns.
		// when the ACL entry permissions is figured out, we shouldn't have to do this filtering
		// start temporary code
		List<Run> current_runs = new ArrayList<Run>();
		List<Run> archived_runs = new ArrayList<Run>();
		
		for (Run run : runList) {
			if (run.getOwners().contains(user)) {
				if (run.isEnded()) {
					archived_runs.add(run);
				} else {
					current_runs.add(run);
				}
			}
		}
		// end temporary code
		
		// set grading page base url...gradeByTypeParam is assumed to be not null
		String gradingPageBaseUrl = "";
		String gradeByTypeParam = request.getParameter(GRADE_BY_TYPE_REQUEST_PARAM_NAME);
		if (gradeByTypeParam.equals("step")) {
			gradingPageBaseUrl = "gradebystep.html";
		} else if (gradeByTypeParam.equals("group")) {
			gradingPageBaseUrl = "selectworkgroup.html";
		}

		modelAndView.addObject(CURRENT_RUN_LIST_KEY, current_runs);
		modelAndView.addObject(ARCHIVED_RUN_LIST_KEY, archived_runs);
		modelAndView.addObject(GRADING_PAGE_BASE_URL, gradingPageBaseUrl);
		modelAndView.addObject(GRADE_BY_TYPE_REQUEST_PARAM_NAME, gradeByTypeParam);

        return modelAndView;
	}


	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
}
