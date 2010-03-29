package eu.scy.controllers.teacher;

import eu.scy.controllers.AbstractSCYController;
import eu.scy.server.controllers.components.fileupload.FileUploadListener;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 29.mar.2010
 * Time: 06:43:09
 * To change this template use File | Settings | File Templates.
 */
public class UploadMultipleUsers implements FileUploadListener {

    private static Logger log = Logger.getLogger("UploadMultipleUsers.class");

    public void fileUploaded(File file) {
        log.info("MULTIPLE USERS IN FILE: " + file.getAbsolutePath());
    }
}
