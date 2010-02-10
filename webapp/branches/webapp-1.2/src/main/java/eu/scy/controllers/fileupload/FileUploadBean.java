package eu.scy.controllers.fileupload;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 08.feb.2010
 * Time: 14:26:52
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadBean {

    private MultipartFile file;
    private String firstName;
    private String lastName;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
