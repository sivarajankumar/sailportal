package net.sf.sail.cms.curnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipFile;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * @author rokham
 * Definition of the Curnit object which will be passed between the CMS and the Project Layer
 *
 */
public class CurnitOtmlImpl implements Curnit{
	
	// Unique number defining each curnit
	private String number;
	
	// Name given to the curnit by the author
	private String name;

	// Comment added to each submitted version of the curnit 
	private String comment;
	
	// Generated by the authoring tool
	private File otmlFile;
	
	// The resources used by the otml file
	private List<File> attachments;
	
	// Curnit author's details
	//private String author;
	
	// Specific version of this copy of the curnit
	private String version;
	
	// Exact time this version of the curnit was created
	private Date timeStamp;
	
	
	public Node curnitToNode(Session session) {
		
		Node root;
		try {
			root = session.getRootNode();
			Node curnit = root.addNode("curnit", "nt:unstructured");
			curnit.addMixin("mix:versionable");
			
			// adding curnit's properties
			curnit.setProperty("number", this.getCurnitNumber());
			curnit.setProperty("name", this.getCurnitName());
			curnit.setProperty("comment", this.getCurnitComment());
			
			// adding the otml file
			Node otml = curnit.addNode("otml", "nt:file");
			Node content = otml.addNode("jcr:content", "nt:resource");
			InputStream inputStrm = new FileInputStream(this.getOtmlFile());
			content.setProperty("jcr:data", inputStrm);
			
			// adding the attachments
			Node attachments = curnit.addNode("attachments", "nt:folder");
			Node attachment = null;
			for (File file: this.getAttachments()){
				attachment = attachments.addNode(file.getName(), "nt:file");
				content = attachment.addNode("jcr:content", "nt:resource");
				inputStrm = new FileInputStream(file);
				content.setProperty("jcr:data", inputStrm);
			}
			
			return curnit;
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}


	public String getCurnitComment() {
		return this.comment;
	}


	public String getCurnitName() {
		return this.name;
	}


	public String getCurnitNumber() {
		return this.number;
	}


	public Date getCurnitTimeStamp() {
		return this.timeStamp;
	}


	public String getCurnitVersion() {
		return this.version;
	}


	public void setCurnitComment(String comment) {
		this.comment = comment;
	}


	public void setCurnitName(String name) {
		this.name = name;
	}


	public void setCurnitNumber(String number) {
		this.number = number;
	}


	public void setCurnitTimeStamp(Date date) {
		this.timeStamp = date;
	}


	public void setCurnitVersion(String version) {
		this.version = version;
	}


	public File getOtmlFile() {
		return otmlFile;
	}


	public void setOtmlFile(File otmlFile) {
		this.otmlFile = otmlFile;
	}


	public List<File> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}


}
