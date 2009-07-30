package org.telscenter.sail.webapp.presentation.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.service.UserService;
import net.sf.sail.webapp.service.workgroup.WorkgroupService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.service.student.StudentService;

public class SmartRoomController  extends AbstractController {
	
//	private UserService userService;
//
	private static final String USERID = "userId";
	
	private WorkgroupService workgroupService;
	private StudentService studentService;
	private UserService userService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		
		Long userId = Long.parseLong(request.getParameter(USERID));
		
		User user = this.userService.retrieveById(userId);
//		User user = (User) request.getSession().getAttribute(User.CURRENT_USER_SESSION_KEY);
		
		mav.addObject("user", user.getUserDetails().getUsername().toString());
		mav.addObject("name", "rokham");
//		
//		
//		mav.addObject("workgroup", this.workgroupService.getWorkgroupList().get(0));
//		return mav;
//	}
		return mav;
	}

	public void setWorkgroupService(WorkgroupService workgroupService) {
		this.workgroupService = workgroupService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
