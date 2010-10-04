package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.UserService;
import eu.scy.core.model.StudentUserDetails;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.ScyBaseObject;
import eu.scy.server.controllers.components.fileupload.FileUploadListener;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 29.mar.2010
 * Time: 06:43:09
 * To change this template use File | Settings | File Templates.
 */
public class UploadMultipleUsers implements FileUploadListener {

    private static Logger log = Logger.getLogger("UploadMultipleUsers.class");

    private UserService userService;
    
    private ScyBaseObject model;

    public void fileUploaded(File file) {
        log.info("MULTIPLE USERS IN FILE: " + file.getAbsolutePath());

        try {
            BufferedReader bufRdr = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufRdr.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",;");
                String firstName = "";
                String lastName = "";
                String suggestedUserName = "";
                String suggestedPassword = "";

                lastName = st.nextToken();
                if (st.hasMoreElements()) firstName = st.nextToken();
                if (st.hasMoreElements()) suggestedUserName = st.nextToken();
                if (st.hasMoreElements()) suggestedPassword = st.nextToken();

                log.info(firstName + " " + lastName + " " + suggestedUserName + " " + suggestedPassword);
                
                User user = getUserService().createUser(suggestedUserName, suggestedPassword, "ROLE_STUDENT");
                StudentUserDetails studentUserDetails = (StudentUserDetails) user.getUserDetails();
                studentUserDetails.setFirstName(firstName);
                studentUserDetails.setLastName(lastName);
                getUserService().save(user);


            }
            bufRdr.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    public void setModel(ScyBaseObject model) {
        this.model = model;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
