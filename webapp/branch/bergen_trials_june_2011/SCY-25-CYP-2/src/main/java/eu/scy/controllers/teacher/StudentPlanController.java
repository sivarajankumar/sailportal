package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.PedagogicalPlanPersistenceService;
import eu.scy.core.StudentPedagogicalPlanPersistenceService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 25.feb.2010
 * Time: 14:03:19
 * To change this template use File | Settings | File Templates.
 */
public class StudentPlanController extends AbstractSCYController {

    private StudentPedagogicalPlanPersistenceService studentPedagogicalPlanPersistenceService;
    private PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        provideUserInfo(modelAndView, request);

        String studentPlanId = request.getParameter("studentPlanId");
        if(studentPlanId != null) {
            modelAndView.addObject("studentPlan", getStudentPedagogicalPlanPersistenceService().getStudentPlanElo(studentPlanId));
        }


        return modelAndView;
    }

    public PedagogicalPlanPersistenceService getPedagogicalPlanPersistenceService() {
        return pedagogicalPlanPersistenceService;
    }

    public void setPedagogicalPlanPersistenceService(PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService) {
        this.pedagogicalPlanPersistenceService = pedagogicalPlanPersistenceService;
    }

    public StudentPedagogicalPlanPersistenceService getStudentPedagogicalPlanPersistenceService() {

        return studentPedagogicalPlanPersistenceService;
    }

    public void setStudentPedagogicalPlanPersistenceService(StudentPedagogicalPlanPersistenceService studentPedagogicalPlanPersistenceService) {
        this.studentPedagogicalPlanPersistenceService = studentPedagogicalPlanPersistenceService;
    }
}
