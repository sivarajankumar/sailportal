/**
 * 
 */
package org.telscenter.sail.webapp.presentation.web.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.mail.IMailFacade;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.telscenter.sail.webapp.domain.authentication.MutableUserDetails;
import org.telscenter.sail.webapp.domain.general.contactwise.ContactWISE;
import org.telscenter.sail.webapp.domain.general.contactwise.IssueType;
import org.telscenter.sail.webapp.domain.general.contactwise.OperatingSystem;
import org.telscenter.sail.webapp.domain.general.contactwise.WebBrowser;
import org.telscenter.sail.webapp.domain.general.contactwise.impl.ContactWISEGeneral;

/**
 * @author gloriasass
 *
 */
public class ContactWiseProjectController extends SimpleFormController {

	protected IMailFacade javaMail = null;
	
	protected Properties uiHTMLProperties = null;
	
	public ContactWiseProjectController() {
		setSessionForm(true);
	}
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
	throws Exception {
		
		ContactWISEGeneral contactWISEGeneral = (ContactWISEGeneral) command;

		//retrieves the contents of the email to be sent
		String[] recipients = contactWISEGeneral.getMailRecipients();
		String subject = contactWISEGeneral.getMailSubject();
		String message = contactWISEGeneral.getMailMessage();
		String fromEmail = contactWISEGeneral.getEmail();
		
		//sends the email to the recipients
		javaMail.postMail(recipients, subject, message, fromEmail);
		
		ModelAndView modelAndView = new ModelAndView(getSuccessView());

		return modelAndView;
	}
	
	@Override
	protected Object formBackingObject(HttpServletRequest request) 
			throws Exception {
		ContactWISE contactWISE = new ContactWISEGeneral();
		
		//tries to retrieve the user from the session
		User user = (User) request.getSession().getAttribute(
				User.CURRENT_USER_SESSION_KEY);

		/* if the user is logged in to the session, auto populate the name and 
		email address in the form, if not, the fields will just be blank */
		if (user != null) {
			MutableUserDetails telsUserDetails = 
				(MutableUserDetails) user.getUserDetails();
			contactWISE.setName(telsUserDetails.getFirstname() + " " + 
					telsUserDetails.getLastname());
			contactWISE.setEmail(telsUserDetails.getEmailAddress());
		}
		return contactWISE;
	}
	
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request) 
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//places the array of constants into the model so the view can display
		model.put("issuetypes", IssueType.values());
		model.put("operatingsystems", OperatingSystem.values());
		model.put("webbrowsers", WebBrowser.values());
		return model;
	}

	/**
	 * @return the javaMail
	 */
	public IMailFacade getJavaMail() {
		return javaMail;
	}

	/**
	 * @param javaMail is the object that contains the functionality to send
	 * an email. This javaMail is set by the contactWiseController bean 
	 * in controllers.xml.
	 */
	public void setJavaMail(IMailFacade javaMail) {
		this.javaMail = javaMail;
	}


	/**
	 * @param uiHTMLProperties contains the regularly formatted (regular 
	 * casing and spaces instead of underscores) for the enums. This properties
	 * file is set by the contactWiseController bean in controllers.xml.
	 */
	public void setUiHTMLProperties(Properties uiHTMLProperties) {
		/* these are necessary so that the enums can retrieve the values from 
		the properties file */
		IssueType.setProperties(uiHTMLProperties);
		OperatingSystem.setProperties(uiHTMLProperties);
		WebBrowser.setProperties(uiHTMLProperties);
	}

}
