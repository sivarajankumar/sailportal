package eu.scy.controllers;

import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.scy.core.*;
import eu.scy.core.model.pedagogicalplan.*;
import eu.scy.core.model.impl.pedagogicalplan.*;

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

    @Override
    @Transactional
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView(VIEW_NAME);
            ControllerUtil.addUserToModelAndView(httpServletRequest, modelAndView);

            Scenario scenario = new ScenarioImpl();
            scenario.setName("Hillary");
            logger.info("SCENARIO ID: " + ((ScenarioImpl)scenario).getId());
            getScenarioService().createScenario(scenario);

            logger.info("SCENARIO ID: " + ((ScenarioImpl)scenario).getId());

            modelAndView.addObject("TULL", "TULLING");
        } catch (Exception e) {
            logger.info(e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return modelAndView;
    }


    public ScenarioService getScenarioService() {
        return scenarioService;
    }

    public void setScenarioService(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }
}
