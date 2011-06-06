package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.PedagogicalPlanPersistenceService;
import eu.scy.core.model.User;
import eu.scy.server.pedagogicalplan.PedagogicalPlanService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 25.feb.2010
 * Time: 12:53:01
 * To change this template use File | Settings | File Templates.
 */
public class SelectPedagogicalPlanForStudent extends AbstractSCYController {

    private PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        String username = request.getParameter("username");
        User student = getUserService().getUser(username);

        ModelAndView modelAndView = new ModelAndView();
        provideUserInfo(modelAndView, request);

        modelAndView.addObject("student", student);
        modelAndView.addObject("pedagogicalPlans", getPedagogicalPlanPersistenceService().getPedagogicalPlans());


        return modelAndView;


    }

    public PedagogicalPlanPersistenceService getPedagogicalPlanPersistenceService() {
        return pedagogicalPlanPersistenceService;
    }

    public void setPedagogicalPlanPersistenceService(PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService) {
        this.pedagogicalPlanPersistenceService = pedagogicalPlanPersistenceService;
    }
}
