package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.model.User;
import eu.scy.core.openfire.BuddyService;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.Configuration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 03.mar.2010
 * Time: 06:32:40
 * To change this template use File | Settings | File Templates.
 */
public class StudentBuddyController extends AbstractSCYController {

    private BuddyService buddyService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        provideUserInfo(modelAndView, request);
        User user = null;


        if (request.getParameter("addBuddy") != null) {
            addBuddy(request, httpServletResponse, modelAndView);
        } else if (request.getParameter("removeBuddy") != null) {
            removeBuddy(request, httpServletResponse, modelAndView);
        }


        if (request.getParameter("username") != null) {
            user = getUserService().getUser(request.getParameter("username"));
            modelAndView.addObject("student", user);

            modelAndView.addObject("buddies", getBuddyService().getBuddies(user.getUserDetails().getUsername(), user.getUserDetails().getPassword()));
            modelAndView.addObject("students", getUserService().getStudents());

        }

        modelAndView.addObject("openfireHost", eu.scy.common.configuration.Configuration.getInstance().getOpenFireHost());


        return modelAndView;
    }

    private void removeBuddy(HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndView modelAndView) {
        String buddyToRemove = request.getParameter("buddy");
        String userName = request.getParameter("username");
        User user = getUserService().getUser(userName);
        try {
            getBuddyService().removeBuddy(user.getUserDetails().getUsername(), user.getUserDetails().getPassword(), buddyToRemove);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void addBuddy(HttpServletRequest request, HttpServletResponse httpServletResponse, ModelAndView modelAndView) {
        String potentialBuddyName = request.getParameter("potentialBuddy");
        String userName = request.getParameter("username");
        User user = getUserService().getUser(userName);

        try {
            getBuddyService().makeBuddies(user.getUserDetails().getUsername(), user.getUserDetails().getPassword(), potentialBuddyName);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public BuddyService getBuddyService() {
        buddyService.setHost(eu.scy.common.configuration.Configuration.getInstance().getOpenFireHost());
        return buddyService;
    }

    public void setBuddyService(BuddyService buddyService) {
        this.buddyService = buddyService;
    }
}
