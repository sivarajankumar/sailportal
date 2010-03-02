package eu.scy.controllers.fileupload;

import eu.scy.core.FileService;
import eu.scy.core.UserService;
import eu.scy.core.model.*;
import eu.scy.core.model.impl.FileDataImpl;
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
    private FileService fileService;


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
        FileUploadBean bean = (FileUploadBean) command;

        User user = getUserService().getUser(getCurrentUserName(request));
        StudentUserDetails details = (StudentUserDetails) user.getUserDetails();


        MultipartFile file = bean.getFile();
        File tmpFile = new File(file.getOriginalFilename());
        tmpFile.deleteOnExit();
        file.transferTo(tmpFile);

        if(file.getContentType().contains("image")) {
            ImageRef fileRef = (ImageRef) getFileService().saveFile(tmpFile);
            details.setProfilePicture(fileRef);
        } else {
            FileRef fileRef = getFileService().saveFile(tmpFile);
        }

        getUserService().save(user);

        ModelAndView modelAndView = new ModelAndView(getSuccessView());
        modelAndView.addObject("userDetails", details);
        return modelAndView;
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

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}

