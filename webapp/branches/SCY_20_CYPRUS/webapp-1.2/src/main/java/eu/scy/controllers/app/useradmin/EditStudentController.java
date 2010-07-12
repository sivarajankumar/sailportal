package eu.scy.controllers.app.useradmin;

import eu.scy.core.UserService;
import eu.scy.core.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 05.feb.2010
 * Time: 07:29:32
 * To change this template use File | Settings | File Templates.
 */
public class EditStudentController extends SimpleFormController {

    private UserService userService;

    private User user = null;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String username = httpServletRequest.getParameter("username");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", username);
        setUser(getUserService().getUser(username));

        modelAndView.addObject("user", user);

        

        return modelAndView;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
