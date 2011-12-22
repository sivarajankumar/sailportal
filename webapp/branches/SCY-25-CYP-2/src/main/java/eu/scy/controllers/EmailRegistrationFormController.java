package eu.scy.controllers;



import java.util.Random;

import eu.scy.common.mission.MissionSpecificationElo;
import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import eu.scy.core.roolo.MissionELOService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import roolo.elo.api.metadata.CoreRooloMetadataKeyIds;
import roolo.search.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 20.des.2011
 * Time: 12:52:38
 * To change this template use File | Settings | File Templates.
 */
public class EmailRegistrationFormController extends SimpleFormController {

    private UserService userService;
    private MissionELOService missionELOService;

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String email = request.getParameter("email");
        logger.info("EMAIL: " + email);
        if (email != null) {
            SCYStudentUserDetails userDetails = (SCYStudentUserDetails) command;
            User user = null;
            SCYStudentUserDetails studentUserDetails = null;
            List<String> missionNames = new LinkedList<String>();
            try {
                String userNameCandidate = email;
                if (userNameCandidate.indexOf("@") > 0) {
                    userNameCandidate = userNameCandidate.substring(0, userNameCandidate.indexOf("@"));
                } else {
                    userNameCandidate = userNameCandidate.replaceAll("@", "-");
                    // silly - will not happen dude!
                }

                String password = getRandomPassword();
                user = userService.createUser(userNameCandidate, password, "ROLE_STUDENT");

                studentUserDetails = (SCYStudentUserDetails) user.getUserDetails();
                studentUserDetails.setFirstName("SCY");
                studentUserDetails.setLastName("User");
                userService.save(user);

                String missionId = "mini1";
                IQueryComponent typeQC = new MetadataQueryComponent(CoreRooloMetadataKeyIds.TECHNICAL_FORMAT.getId(), "scy/missionspecification");
                IQuery query = new Query(typeQC);
                List<ISearchResult> searchResults = getMissionELOService().getRepository().search(query);

                if (searchResults.size() > 0) {
                    for (int i = 0; i < searchResults.size(); i++) {
                        ISearchResult searchResult = searchResults.get(i);
                        String title = searchResult.getTitle(Locale.ENGLISH);
                        if (title != null) {
                            URI uri = searchResult.getUri();
                            MissionSpecificationElo missionSpecificationElo = MissionSpecificationElo.loadLastVersionElo(uri, getMissionELOService());
                            missionSpecificationElo.getMissionManagement().createMissionRuntimeModelElos(studentUserDetails.getUsername());
                            missionNames.add(missionSpecificationElo.getTitle());
                        }
                    }
                }

                sendMail(studentUserDetails, email, request, missionNames);
                ModelAndView modelAndView = new ModelAndView(getSuccessView());

                modelAndView.addObject("username", studentUserDetails.getUsername());
                modelAndView.addObject("password", studentUserDetails.getPassword());
                modelAndView.addObject("firstname", studentUserDetails.getFirstName());
                modelAndView.addObject("lastname", studentUserDetails.getLastName());

                return modelAndView;

            } catch (Exception e) {
                e.printStackTrace();
                return showForm(request, response, errors);
            }
        }

        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        return modelAndView;
    }


    private static void sendMail(SCYStudentUserDetails studentUserDetails, final String email, HttpServletRequest request, List<String> missionNames) {
        Session mailSession = null;

        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host.auth", "true");
            props.setProperty("mail.host.user", "true");
            props.setProperty("mail.host.password", "true");
            props.setProperty("mail.smtp.auth", "true");

            final String mailUserName = "adaptitbase";
            final String mailPassword = "901base901";
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.debug", "true");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");

            mailSession = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailUserName, mailPassword);
                }
            });

            // the newly created user
            InternetAddress to = new InternetAddress(email);
            to.setPersonal("SCY-User");

            // the sender
            InternetAddress from = new InternetAddress("henrik@enovate.no");
            from.setPersonal("no-scy-reply");

            // notification to scycom
            InternetAddress bcc = new InternetAddress("scycom@collide.info");
            bcc.setPersonal("SCYCom Team");

            Message message = new MimeMessage(mailSession);
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setRecipient(Message.RecipientType.BCC, bcc);

            StringBuilder messageText = new StringBuilder();
            messageText.append("Welcome to SCY");
            messageText.append("\n");
            messageText.append("\n");
            messageText.append("Your generated user has the following credentials:");
            messageText.append("\n");
            messageText.append("Username:  ").append(studentUserDetails.getUsername()).append("\n");
            messageText.append("Password: ").append(studentUserDetails.getPassword());
            messageText.append("\n");
            messageText.append("You can now try out SCY-Lab following this web address:");
            messageText.append("\n");
            messageText.append("http://scy-review.collide.info/webapp/index.html");
            messageText.append("\n");
            messageText.append("\n");
            messageText.append("If you have any further questions or encounter any problems,\n");
            messageText.append("don't hesitate to contact the SCY team via");
            messageText.append("\n");
            messageText.append("http://scy-net.eu");

            message.setText(messageText.toString());

            Transport.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    public MissionELOService getMissionELOService() {
        return missionELOService;
    }

    public void setMissionELOService(MissionELOService missionELOService) {
        this.missionELOService = missionELOService;
    }
    
    
    // password generation stuff

    /*
     * Set of characters that is valid. Must be printable, memorable, and "won't break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out, as are numeric zero and one.
     */
    protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9', };

    /* Generate a Password object with a random password. */
    public String getRandomPassword() {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(goodChar[r.nextInt(goodChar.length)]);
        }
        return sb.toString();
    }

}
