package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.ScenarioService;
import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 12.feb.2010
 * Time: 05:46:20
 * To change this template use File | Settings | File Templates.
 */
public class AddNewStudentController extends SimpleFormController {
    private UserService userService;

    public AddNewStudentController() {
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
        SCYStudentUserDetails userDetails = (SCYStudentUserDetails) command;
        User user = null;
        SCYStudentUserDetails studentUserDetails = null;
        try {
            user = userService.createUser(userDetails.getUsername(), userDetails.getPassword(), "ROLE_STUDENT");
            studentUserDetails = (SCYStudentUserDetails) user.getUserDetails();
            if (userDetails.getFirstName() != null) studentUserDetails.setFirstName(userDetails.getFirstName());
            if (userDetails.getLastName() != null) studentUserDetails.setLastName(userDetails.getLastName());
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return showForm(request, response, errors);
        }

        logger.info("Student added - opening success view: " + getSuccessView());

        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        List students = getUserService().getStudents();
        modelAndView.addObject("students", students);
        return modelAndView;

    }


    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
        System.out.println("******************************************************************************");
        logger.debug("ON BINDE AND VALIDATE STUDENT CONTROLLER!");
        SCYStudentUserDetails userDetails = (SCYStudentUserDetails) command;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
