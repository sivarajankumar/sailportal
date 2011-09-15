package eu.scy.controllers;

/*import eu.scy.core.ScenarioService;
import eu.scy.core.model.impl.pedagogicalplan.ScenarioImpl;
import eu.scy.core.model.pedagogicalplan.Scenario;*/

import eu.scy.common.mission.MissionSpecificationElo;
import eu.scy.core.ScenarioService;
import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import eu.scy.core.roolo.MissionELOService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import roolo.elo.api.metadata.CoreRooloMetadataKeyIds;
import roolo.search.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 16.sep.2009
 * Time: 06:27:23
 */
public class RegisterStudentForSCYController extends SimpleFormController {

    private UserService userService;
    private ScenarioService scenarioService;
    private MissionELOService missionELOService;

    public RegisterStudentForSCYController() {
        logger.debug("** **** **** CREATING REGISTER STUDENT FOR SCY CONTROLLER!!");
        setValidateOnBinding(false);
    }

    /**
     * On submission of the signup form, a user is created and saved to the data
     * store.
     *
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    @Override
    @Transactional
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        SCYStudentUserDetails userDetails = (SCYStudentUserDetails) command;
        User user = null;
        SCYStudentUserDetails studentUserDetails = null;
        try {
            user = userService.createUser(userDetails.getUsername(), userDetails.getPassword(), "ROLE_STUDENT");
            studentUserDetails = (SCYStudentUserDetails) user.getUserDetails();
            if (userDetails.getFirstName() != null) studentUserDetails.setFirstName(userDetails.getFirstName());
            if (userDetails.getLastName() != null) studentUserDetails.setLastName(userDetails.getLastName());
            userService.save(user);

            String missionId = "mini1";
            IQueryComponent typeQC = new MetadataQueryComponent(CoreRooloMetadataKeyIds.TECHNICAL_FORMAT.getId(), "scy/missionspecification");
            IQueryComponent missionIdQC = new MetadataQueryComponent(CoreRooloMetadataKeyIds.MISSION_ID.getId(), missionId);
            IQueryComponent andQuery = new AndQuery(typeQC, missionIdQC);
            IQuery query = new Query(andQuery);
            List<ISearchResult> searchResults = getMissionELOService().getRepository().search(query);

            if(searchResults.size() > 0) {
                ISearchResult searchResult = searchResults.get(0);
                if(searchResult != null) {
                    URI uri = searchResult.getUri();
                    MissionSpecificationElo missionSpecificationElo = MissionSpecificationElo.loadLastVersionElo(uri, getMissionELOService());
                    missionSpecificationElo.getMissionManagement().createMissionRuntimeModelElos(userDetails.getUsername());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return showForm(request, response, errors);
        }
        ModelAndView modelAndView = new ModelAndView(getSuccessView());

        modelAndView.addObject("username", studentUserDetails.getUsername());
        modelAndView.addObject("password", studentUserDetails.getPassword());
        modelAndView.addObject("firstname", studentUserDetails.getFirstName());
        modelAndView.addObject("lastname", studentUserDetails.getLastName());

        return modelAndView;

    }


    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {
        System.out.println("******************************************************************************");
        logger.debug("ON BINDE AND VALIDATE STUDENT CONTROLLER!");
        SCYStudentUserDetails userDetails = (SCYStudentUserDetails) command;
    }

    /*@Override
     protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
     {
         System.out.println("________________________________________________________________________________________");
         logger.debug("INIT BINDER IN STUDENT CONTROLLER");
       binder.registerCustomEditor(Date.class,
         new CustomDateEditor(new SimpleDateFormat("MM/dd"), false)
       );
     } */


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ScenarioService getScenarioService() {
        return scenarioService;
    }

    public void setScenarioService(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    public MissionELOService getMissionELOService() {
        return missionELOService;
    }

    public void setMissionELOService(MissionELOService missionELOService) {
        this.missionELOService = missionELOService;
    }
}
