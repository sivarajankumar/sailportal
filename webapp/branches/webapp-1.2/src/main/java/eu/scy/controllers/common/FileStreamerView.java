package eu.scy.controllers.common;

import eu.scy.core.UserService;
import eu.scy.core.model.User;
import eu.scy.core.model.impl.SCYStudentUserDetails;
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
        if(httpServletRequest.getParameter("showIcon") != null) showIcon = true;
        logger.info("Getting profilePicture for " + userName);


        ServletOutputStream out = null;
        File source = null;
        try {

            User user = getUserService().getUser(userName);
            if (user.getUserDetails() instanceof SCYStudentUserDetails) {
                SCYStudentUserDetails userDetails = (SCYStudentUserDetails) user.getUserDetails();
                String pictureName = userDetails.getProfilePictureUrl();
                if(showIcon) {
                    pictureName = pictureName + ".jpg";
                }

                File fileDirectory = getFileDirectory(httpServletRequest);
                String fileName = fileDirectory + File.separator + pictureName;
                source = new File(fileName);
                if (source != null) {
                    byte[] bytes = getBytesFromFile(source);


                    out = httpServletResponse.getOutputStream();
                    httpServletResponse.setContentType(getContentType());
                    httpServletResponse.setContentLength(bytes.length);
                    out.write(bytes);
                    out.flush();
                }

            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (out != null) out = null;
        }

    }


    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
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
