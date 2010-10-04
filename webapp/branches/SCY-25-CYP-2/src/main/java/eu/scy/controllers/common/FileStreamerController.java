package eu.scy.controllers.common;

import eu.scy.core.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 11.feb.2010
 * Time: 05:43:30
 * To change this template use File | Settings | File Templates.
 */
public class FileStreamerController extends AbstractController {

    private UserService userService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        FileStreamerView fileStreamerView = new FileStreamerView();
        fileStreamerView.setUserService(getUserService());
        return new ModelAndView(fileStreamerView);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
