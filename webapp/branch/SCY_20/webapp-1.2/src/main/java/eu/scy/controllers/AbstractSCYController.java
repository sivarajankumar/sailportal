package eu.scy.controllers;

import eu.scy.core.UserService;
import eu.scy.core.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 11.feb.2010
 * Time: 06:35:03
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSCYController extends AbstractController {

    private UserService userService;

    public User getCurrentUser(HttpServletRequest request) {
        String userName = getCurrentUserName(request);
        if (userName != null) {
            return getUserService().getUser(userName);
        }

        return null;
    }

    public void provideUserInfo(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.addObject("currentUser" , getCurrentUser(request));
    }

    public String getCurrentUserName(HttpServletRequest request) {
        org.springframework.security.userdetails.User user = (org.springframework.security.userdetails.User) request.getSession().getAttribute("CURRENT_USER");
        if (user != null) {
            return user.getUsername();
        }

        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
