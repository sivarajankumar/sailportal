package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 11.feb.2010
 * Time: 06:34:12
 * To change this template use File | Settings | File Templates.
 */
public class StudentListController extends AbstractSCYController {
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        provideUserInfo(modelAndView, httpServletRequest);

        List students = getUserService().getStudents();
        modelAndView.addObject("students", students);

        return modelAndView;
    }
}
