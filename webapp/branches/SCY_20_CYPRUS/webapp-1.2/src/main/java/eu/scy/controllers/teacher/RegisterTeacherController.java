package eu.scy.controllers.teacher;

import eu.scy.core.ScenarioService;
import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import eu.scy.core.model.impl.SCYTeacherUserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 09.feb.2010
 * Time: 07:10:56
 * To change this template use File | Settings | File Templates.
 */
public class RegisterTeacherController extends SimpleFormController {

    private UserService userService;
    private ScenarioService scenarioService;

    public RegisterTeacherController() {
        logger.debug("** **** **** CREATING REGISTER STUDENT FOR SCY CONTROLLER!!");
		setValidateOnBinding(false);
	}

    /**
     * On submission of the signup form, a user is created and saved to the data
     * store.
     *
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    @Transactional
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        SCYTeacherUserDetails userDetails  = (SCYTeacherUserDetails) command;
        User user = null;
        SCYTeacherUserDetails studentUserDetails=null;
            try {
                user = userService.createUser(userDetails.getUsername(), userDetails.getPassword(), "ROLE_TEACHER");
                studentUserDetails = (SCYTeacherUserDetails) user.getUserDetails();
                if(userDetails.getFirstName() != null) studentUserDetails.setFirstName(userDetails.getFirstName());
                if(userDetails.getLastName() != null) studentUserDetails.setLastName(userDetails.getLastName());
                userService.save(user);
            } catch (Exception e) {
                e.printStackTrace();
                return showForm(request, response, errors);
            }
        ModelAndView modelAndView = new ModelAndView(getSuccessView());

        modelAndView.addObject("username", studentUserDetails.getUsername());
        modelAndView.addObject("password", studentUserDetails.getPassword());
        modelAndView.addObject("firstname", studentUserDetails.getFirstName());
        modelAndView.addObject("lastname", studentUserDetails.getLastName());

        return modelAndView;

    }


@Override
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
    System.out.println("******************************************************************************");
        logger.debug("ON BINDE AND VALIDATE STUDENT CONTROLLER!");
    SCYTeacherUserDetails userDetails = (SCYTeacherUserDetails) command;
	}

	/*@Override
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
	{
        System.out.println("________________________________________________________________________________________");
        logger.debug("INIT BINDER IN STUDENT CONTROLLER");
	  binder.registerCustomEditor(Date.class,
	    new CustomDateEditor(new SimpleDateFormat("MM/dd"), false)
	  );
	} */


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ScenarioService getScenarioService() {
        return scenarioService;
    }

    public void setScenarioService(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }
}
