package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.core.*;
import eu.scy.core.model.User;
import eu.scy.core.model.pedagogicalplan.PedagogicalPlan;
import eu.scy.server.pedagogicalplan.StudentPedagogicalPlanService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 25.feb.2010
 * Time: 11:27:15
 * To change this template use File | Settings | File Templates.
 */
public class StudentDetailsController extends AbstractSCYController {

    private StudentPedagogicalPlanPersistenceService studentPedagogicalPlanPersistenceService;
    private PedagogicalPlanPersistenceService pedagogicalPlanPersistenceService;
    private AssignedPedagogicalPlanService assignedPedagogicalPlanService;
    private UserService userService;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        String username = request.getParameter("username");
        User student = getUserService().getUser(username);



        String assignPedPlan = request.getParameter("pedPlan");
        if(assignPedPlan != null && assignPedPlan.equals("published")) {
            logger.info("Assigning student plan to user!");
            getStudentPedagogicalPlanPersistenceService().createStudentPlan(username);
        } else {
            logger.info("assignPedPlan = " + assignPedPlan);
        }

        String pedagogicalPlanId = request.getParameter("pedPlanId");
        if(pedagogicalPlanId != null) {
            assignPedagogicalPlanToStudent(student, getPedagogicalPlanPersistenceService().getPedagogicalPlan(pedagogicalPlanId));
        }



        ModelAndView modelAndView = new ModelAndView();
        provideUserInfo(modelAndView, request);

        logger.info("LOADING STUDENT: " + student);

        modelAndView.addObject("student", student);

        modelAndView.addObject("studentPlans", getStudentPedagogicalPlanPersistenceService().getStudentPlans(student));
        modelAndView.addObject("assignedPedagogicalPlans", getAssignedPedagogicalPlanService().getAssignedPedagogicalPlans(student));
        modelAndView.addObject("availableAuthorities", getUserService().getGrantedAuthorities());


        return modelAndView;
    }

    private void assignPedagogicalPlanToStudent(User student, PedagogicalPlan pedagogicalPlan) {
        if(pedagogicalPlan == null) {
            logger.warn("NOTING TO ASSIGN - doing nothing!");
            return;
        }

        getStudentPedagogicalPlanPersistenceService().createStudentPlan(pedagogicalPlan, student);

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

    public AssignedPedagogicalPlanService getAssignedPedagogicalPlanService() {
        return assignedPedagogicalPlanService;
    }

    public void setAssignedPedagogicalPlanService(AssignedPedagogicalPlanService assignedPedagogicalPlanService) {
        this.assignedPedagogicalPlanService = assignedPedagogicalPlanService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
