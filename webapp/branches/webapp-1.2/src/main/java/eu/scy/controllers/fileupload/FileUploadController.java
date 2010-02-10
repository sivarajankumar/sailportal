package eu.scy.controllers.fileupload;

import eu.scy.core.UserService;
import eu.scy.core.model.StudentUserDetails;
import eu.scy.core.model.User;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;


import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 08.feb.2010
 * Time: 14:25:33
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadController extends SimpleFormController {

    private static Logger log = Logger.getLogger("FileUploadController.class");

    private UserService userService;




    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        log.info("*********************************************************************************************************************************************************");
        Map<Object, Object> dataMap = new HashMap<Object, Object>();

        User user = getUserService().getUser(getCurrentUserName(request));
        StudentUserDetails details = (StudentUserDetails) user.getUserDetails();

        dataMap.put("userDetails", details);
        dataMap.put("hei", "Hei");
        return dataMap;
    }


    protected ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception {
        log.info("========================SUBMITTED FILE!");


        // cast the bean
        FileUploadBean bean = (FileUploadBean) command;

        File tempFolder = new File(System.getProperty("java.io.tmpdir"));


        MultipartFile file = bean.getFile();
        if (file == null) {
            // hmm, that's strange, the user did not upload anything
        }

        log.info(file.getContentType());
        log.info(file.getOriginalFilename());


        String postFix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());

        String newFileName = getCurrentUserName(request) + Math.random() + postFix;

        File fileDirectory = getFileDirectory(request);
        String fileName = fileDirectory + File.separator + newFileName ;



        File profilePicture = new File(fileName);
        if(profilePicture.exists()) {
            profilePicture.renameTo(new File(fileName + "" + Math.random()));
            profilePicture.deleteOnExit();
        }
        profilePicture = new File(fileName);
        file.transferTo(profilePicture);
        log.info("Added image: " + profilePicture.getAbsolutePath());

        ImageConverter converter = new ImageConverter();
        converter.handleImageConversion(profilePicture);

        User user = getUserService().getUser(getCurrentUserName(request));
        StudentUserDetails details = (StudentUserDetails) user.getUserDetails();
        details.setProfilePictureUrl(newFileName);
        getUserService().save(user);

        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        modelAndView.addObject("userDetails", details);
        return modelAndView;

        // well, let's do nothing with the bean for now and return
        //return super.onSubmit(request, response, command, errors);
    }

    private File getFileDirectory(HttpServletRequest request) {
        try {
            File folder = new File(request.getSession().getServletContext().getRealPath("/") );
            if (folder.exists()) return folder;
            folder.mkdirs();
            return folder;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public String getCurrentUserName(HttpServletRequest request) {
        org.springframework.security.userdetails.User user = (org.springframework.security.userdetails.User) request.getSession().getAttribute("CURRENT_USER");
        return user.getUsername();


    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

