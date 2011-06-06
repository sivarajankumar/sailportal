package eu.scy.filters;

import eu.scy.core.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 18.des.2009
 * Time: 07:28:07
 * To change this template use File | Settings | File Templates.
 */
public class SCYAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

    private static final Log LOGGER = LogFactory.getLog(SCYAuthenticationProcessingFilter.class);

    private UserService  userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void successfulAuthentication(
            javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response,
            Authentication authResult) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        if (LOGGER.isDebugEnabled()) {
            logDebug(userDetails);
        }

        HttpSession session = request.getSession();
        ApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        UserService userService = (UserService) springContext.getBean("userService");
        eu.scy.core.model.impl.SCYUserImpl user = (eu.scy.core.model.impl.SCYUserImpl) userService.getUser(((UserDetails) authResult.getPrincipal()).getUsername());
        UserDetails principal = (UserDetails) authResult.getPrincipal();
        logger.info("prin: "+ principal.getPassword());
        logger.info("UN: " + principal.getUsername());
        if (user != null &&  (user.getUserDetails().getUsername().equals(principal.getUsername()) && user.getUserDetails().getPassword().equals(principal.getPassword()))) {
            logger.info("LOGIN SUCCESS");
            session.setAttribute("CURRENT_USER", userDetails);
        } else {
            throw new BadCredentialsException("invalid username/password");
        }


        // add new session in a allLoggedInUsers servletcontext HashMap variable
        String sessionId = session.getId();
        /*HashMap<String, User> allLoggedInUsers = (HashMap<String, User>) session.getServletContext().getAttribute("allLoggedInUsers");
        if (allLoggedInUsers == null) {
            allLoggedInUsers = new HashMap<String, User>();
            session.getServletContext().setAttribute(PasSessionListener.ALL_LOGGED_IN_USERS, allLoggedInUsers);
        }
        allLoggedInUsers.put(sessionId, user);
        */
        super.successfulAuthentication(request, response, authResult);
    }

    private void logDebug(UserDetails userDetails) {
        LOGGER.debug("UserDetails logging in: " + userDetails.getUsername());
    }
}
