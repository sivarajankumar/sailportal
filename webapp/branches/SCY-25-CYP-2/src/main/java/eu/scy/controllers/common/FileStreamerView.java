package eu.scy.controllers.common;

import eu.scy.core.UserService;
import eu.scy.core.model.FileRef;
import eu.scy.core.model.ImageRef;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
import eu.scy.core.model.impl.SCYTeacherUserDetails;
import eu.scy.core.model.impl.SCYUserDetails;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 11.feb.2010
 * Time: 05:21:53
 * To change this template use File | Settings | File Templates.
 */
public class FileStreamerView extends AbstractView {

    private UserService userService;

    public FileStreamerView() {
        setContentType("image/jpg");
    }


    @Override
    protected void renderMergedOutputModel(Map map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        String userName = httpServletRequest.getParameter("username");
        Boolean showIcon = false;
        if (httpServletRequest.getParameter("showIcon") != null) showIcon = true;
        logger.debug("Getting profilePicture for " + userName);


        ServletOutputStream out = null;
        try {

            User user = getUserService().getUser(userName);
            if (user != null) {

                ImageRef fileRef = null;
                try {
                    if(user.getUserDetails() instanceof SCYStudentUserDetails) {
                        fileRef = ((SCYStudentUserDetails)user.getUserDetails()).getProfilePicture();
                    } else {
                        fileRef = ((SCYTeacherUserDetails)user.getUserDetails()).getProfilePicture();
                    }

                    logger.debug("loading image for " + user.getUserDetails().getUsername() + " image ref is  " + fileRef);
                } catch (Exception e) {
                    logger.debug("ImageRef does not exist!");
                    fileRef = null;
                }


                if (fileRef != null) {
                    byte[] bytes = null;
                    if (fileRef != null && fileRef.getIcon() != null) {
                        if (showIcon) {
                            bytes = ((ImageRef) fileRef).getIcon().getBytes();
                        } else {
                            bytes = fileRef.getFileData().getBytes();
                        }

                        out = httpServletResponse.getOutputStream();
                        httpServletResponse.setContentType(fileRef.getFileData().getContentType());
                        httpServletResponse.setContentLength(bytes.length);
                        out.write(bytes);
                        out.flush();
                    } else {
                        logger.debug("No image available - loading buddy icon");
                        InputStream is = null;
                        if (showIcon) is = this.getClass().getResourceAsStream("buddyicon_icon.png");
                        else is = this.getClass().getResourceAsStream("buddyicon_online.png");

                        bytes = new byte[is.available()];
                        is.read(bytes);
                        out = httpServletResponse.getOutputStream();
                        httpServletResponse.setContentType(getContentType());
                        httpServletResponse.setContentLength(bytes.length);
                        out.write(bytes);
                        out.flush();
                    }
                } else {
                    logger.debug("LOADING BUDDY ICON");
                    InputStream is = null;
                    if (showIcon) is = this.getClass().getResourceAsStream("buddyicon_icon.png");
                    else is = this.getClass().getResourceAsStream("buddyicon_online.png");


                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    logger.debug("Bytes: " + bytes.length);
                    out = httpServletResponse.getOutputStream();
                    httpServletResponse.setContentType(getContentType());
                    httpServletResponse.setContentLength(bytes.length);
                    out.write(bytes);
                    out.flush();

                }

            } else {
                logger.info("LOADING BUDDY ICON");
                InputStream is = null;
                if (showIcon) is = this.getClass().getResourceAsStream("buddyicon_icon.png");
                else is = this.getClass().getResourceAsStream("buddyicon_online.png");


                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                logger.debug("Bytes: " + bytes.length);
                out = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType(getContentType());
                httpServletResponse.setContentLength(bytes.length);
                out.write(bytes);
                out.flush();
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) out = null;
        }

    }


    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    private File getFileDirectory(HttpServletRequest request) {
        try {
            File folder = new File(request.getSession().getServletContext().getRealPath("/"));
            if (folder.exists()) return folder;
            folder.mkdirs();
            return folder;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
