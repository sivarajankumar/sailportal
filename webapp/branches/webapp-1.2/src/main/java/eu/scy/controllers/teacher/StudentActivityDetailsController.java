package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.PedagogicalPlanPersistenceService;
import eu.scy.core.StudentPedagogicalPlanPersistenceService;
import eu.scy.core.model.student.StudentPlannedActivity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 25.feb.2010
 * Time: 19:25:56
 * To change this template use File | Settings | File Templates.
 */
public class StudentActivityDetailsController extends AbstractSCYController {

    private StudentPedagogicalPlanPersistenceService studentPedagogicalPlanPersistenceService;
    private PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        String eloId = request.getParameter("eloId");
        String username = request.getParameter("username");

        //StudentPlannedActivity activity = getStudentPedagogicalPlanPersistenceService().getStudentPlannedActivity(username, eloId);
        ModelAndView modelAndView = new ModelAndView();

        //modelAndView.addObject("activity", activity);

        return modelAndView;
    }

    public StudentPedagogicalPlanPersistenceService getStudentPedagogicalPlanPersistenceService() {
        return studentPedagogicalPlanPersistenceService;
    }

    public void setStudentPedagogicalPlanPersistenceService(StudentPedagogicalPlanPersistenceService studentPedagogicalPlanPersistenceService) {
        this.studentPedagogicalPlanPersistenceService = studentPedagogicalPlanPersistenceService;
    }

    public PedagogicalPlanPersistenceService getPedagogicalPlanPersistenceService() {
        return pedagogicalPlanPersistenceService;
    }

    public void setPedagogicalPlanPersistenceService(PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService) {
        this.pedagogicalPlanPersistenceService = pedagogicalPlanPersistenceService;
    }
}
