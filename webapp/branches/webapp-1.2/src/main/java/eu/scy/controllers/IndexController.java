package eu.scy.controllers;

import eu.scy.core.UserService;
import eu.scy.core.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 26.nov.2009
 * Time: 10:23:39
 * To change this template use File | Settings | File Templates.
 */
public class IndexController extends AbstractController {


    private UserService userService;

    protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {

        //User user = getUserService().getUser(getCurrentUserName(request));

        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("currentUser", user);
        return modelAndView;
	}

   
}
