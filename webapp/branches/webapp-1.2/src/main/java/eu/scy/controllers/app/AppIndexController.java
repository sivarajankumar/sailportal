package eu.scy.controllers.app;

import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import eu.scy.core.model.impl.SCYUserDetails;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 05.feb.2010
 * Time: 05:33:57
 * To change this template use File | Settings | File Templates.
 */
public class AppIndexController extends AbstractController {

    private UserService userService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        User user = getUserService().getUser(getCurrentUserName(httpServletRequest));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currentUser", user);
        if(user.getUserDetails().getUsername().contains("armin") || user.getUserDetails().getUsername().contains("Armin")) {
            modelAndView.addObject("rickRoll", true);
        } else {
            modelAndView.addObject("rickRoll", false);
        }

        if(user.getUserDetails() instanceof SCYStudentUserDetails) {
            SCYStudentUserDetails details = (SCYStudentUserDetails) user.getUserDetails();
            if(details.getProfilePicture() != null) {
                modelAndView.addObject("showProfilePicture", true);
            } else {
                modelAndView.addObject("showProfilePicture", false);
            }
        } else {
            modelAndView.addObject("showProfilePicture", false);
        }

        return modelAndView;
    }

     public String getCurrentUserName(HttpServletRequest request) {
        org.springframework.security.userdetails.User user = (org.springframework.security.userdetails.User) request.getSession().getAttribute("CURRENT_USER");
        return user.getUsername();


    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
