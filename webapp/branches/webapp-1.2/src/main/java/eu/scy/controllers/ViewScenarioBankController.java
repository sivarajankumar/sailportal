package eu.scy.controllers;

import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.userdetails.UserDetails;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.scy.core.*;
import eu.scy.core.model.pedagogicalplan.*;
import eu.scy.core.model.impl.pedagogicalplan.*;
import org.telscenter.sail.webapp.service.project.ProjectService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 15.okt.2009
 * Time: 00:16:30
 * To change this template use File | Settings | File Templates.
 */
public class ViewScenarioBankController extends AbstractController {

    protected static final String VIEW_NAME = "admin/scenarios";

    private ScenarioService scenarioService;

    private ProjectService projectService;

    public ViewScenarioBankController() {
        super();
        logger.info("********** ******** CREATED VIEW!!!");
    }

    /*@Override
    @Transactional
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("******* ******** *****HANDLING REQUEST!!!!");
        Scenario scenario = new ScenarioImpl();
            scenario.setName("Hillaryyyy");
            logger.info("HR: SCENARIO ID: " + ((ScenarioImpl)scenario).getId());
            logger.info("Scenarios before: " + getScenarioService().getScenarios().size());
            getScenarioService().createScenario(scenario);
            logger.info("Scenarios after: " + getScenarioService().getScenarios().size());
            logger.info("HR: SCENARIO ID AFTER SAVE: " + ((ScenarioImpl)scenario).getId());
        return super.handleRequest(httpServletRequest, httpServletResponse);    //To change body of overridden methods use File | Settings | File Templates.
    } */

    @Override
    @Transactional
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        logger.info("******* ******** *****HANDLING REQUEST INTERNAL !!!!");
        ModelAndView modelAndView = null;


        


        try {
            modelAndView = new ModelAndView(VIEW_NAME);
            ControllerUtil.addUserToModelAndView(httpServletRequest, modelAndView);
            //logger.info("Scenarios before: " + getScenarioService().getScenarios().size());
            Scenario scenario = new ScenarioImpl();
            scenario.setName("Hillary");
            logger.info("SCENARIO ID: " + ((ScenarioImpl)scenario).getId());
            getScenarioService().createScenario(scenario);

            ScenarioImpl impl = (ScenarioImpl) scenario;

            logger.info("SCENARIO ID AFTER SAVE: " + impl.getId() + " " + impl.getTimeCreated());
            //logger.info("Scenarios after: " + getScenarioService().getScenarios().size());
            modelAndView.addObject("TULL", "TULLING");
            modelAndView.addObject("MODEL", scenario);
            modelAndView.addObject("SCENARIOS", getScenarioService().getScenarios());
        } catch (Exception e) {
            logger.debug(e);
            e.printStackTrace(); 
        }

        return modelAndView;
    }


    public ScenarioService getScenarioService() {
        return scenarioService;
    }

    public void setScenarioService(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
