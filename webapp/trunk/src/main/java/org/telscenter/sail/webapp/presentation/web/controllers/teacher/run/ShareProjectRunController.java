/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers.teacher.run;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.core.service.UserService;
import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.telscenter.sail.webapp.domain.Run;
import org.telscenter.sail.webapp.domain.impl.AddSharedTeacherParameters;
import org.telscenter.sail.webapp.domain.impl.EndRunParameters;
import org.telscenter.sail.webapp.service.authentication.UserDetailsService;
import org.telscenter.sail.webapp.service.offering.RunService;

/**
 * @author MattFish
 * @author Hiroki Terashima
 * @author Patrick Lawler
 * @version $Id:$
 */
public class ShareProjectRunController extends SimpleFormController {
	
	private RunService runService;
	
	private static final String RUNID_PARAM_NAME = "runId";

	private static final String RUN_PARAM_NAME = "run";

	private static final String EXISTING_SHAREDONWERS_PARAM_NAME = "existingsharedowners";

	private static final String USERNAME_PARAM_NAME = "username";
	
	private static final String USER_FOUND_PARAM_NAME = "found";
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		AddSharedTeacherParameters params = new AddSharedTeacherParameters();
		params.setRun(runService.retrieveById(Long.parseLong(request.getParameter(RUNID_PARAM_NAME))));
		params.setPermission(UserDetailsService.RUN_READ_ROLE);
		return params;
	}
	

    /**
     * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) 
	    throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Run run = runService.retrieveById(Long.parseLong(request.getParameter(RUNID_PARAM_NAME)));
		Set<User> sharedowners = run.getSharedowners();
		Set<AddSharedTeacherParameters> existingSharedOwners =
			new HashSet<AddSharedTeacherParameters>();
		for (User sharedowner : sharedowners) {
			String sharedTeacherRole = runService.getSharedTeacherRole(run, sharedowner);
			AddSharedTeacherParameters addSharedTeacherParameters = 
				new AddSharedTeacherParameters();
			addSharedTeacherParameters.setPermission(sharedTeacherRole);
			addSharedTeacherParameters.setRun(run);
			addSharedTeacherParameters.setSharedOwnerUsername(
					sharedowner.getUserDetails().getUsername());
			model.put(sharedowner.getUserDetails().getUsername(), 
					addSharedTeacherParameters);
		}
		model.put(RUN_PARAM_NAME, run);
		
		return model;
	}

	/**
     * On submission of the Change Student's Password form, the associated student's password
     * in the database gets changed to the submitted password
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) {
    	AddSharedTeacherParameters params = (AddSharedTeacherParameters) command;
    	runService.addSharedTeacherToRun(params);
    	ModelAndView modelAndView = new ModelAndView(new RedirectView(getSuccessView()));
    	modelAndView.addObject(RUNID_PARAM_NAME, params.getRun().getId());
    	return modelAndView;
    }

	/**
	 * @param runService the runService to set
	 */
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
}

