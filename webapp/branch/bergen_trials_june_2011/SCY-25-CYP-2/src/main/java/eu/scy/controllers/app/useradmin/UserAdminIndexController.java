package eu.scy.controllers.app.useradmin;

import eu.scy.core.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 05.feb.2010
 * Time: 06:05:52
 * To change this template use File | Settings | File Templates.
 */
public class UserAdminIndexController extends AbstractController {

    private UserService userService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("users", getUserService().getUsers());
        modelAndView.addAllObjects(myModel);

        return modelAndView;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
