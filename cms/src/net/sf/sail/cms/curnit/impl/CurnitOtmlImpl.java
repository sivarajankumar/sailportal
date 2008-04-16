package net.sf.sail.cms.curnit.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import org.jcrom.JcrFile;
import org.jcrom.annotations.JcrFileNode;
import org.jcrom.annotations.JcrName;
import org.jcrom.annotations.JcrNode;
import org.jcrom.annotations.JcrPath;
import org.jcrom.annotations.JcrProperty;

/**
 * @author rokham
 * Definition of the Curnit object which will be passed between the CMS and the Project Layer
 * This object is persisted in Jackrabbit using JCROM's persistence API
 *
 */

@JcrNode(mixinTypes = {"mix:versionable"})
public class CurnitOtmlImpl{
	
	@JcrName private String name; // name of the node set by Jcrom - Jcrom will modify the name (eg. "Hello, World!" is changed to "Hello_World")
	@JcrPath private String path; // mandatory attribute -- requested by Jcrom
	
	@JcrProperty private String uniqueKey; // unique string defining each curnit 
	@JcrProperty private String curnitUuid; // curnit's uuid assigned by jackrabbit
	@JcrProperty private String curnitPath; // curnit's path in jackrabbit
	@JcrProperty private String title; // title set for each version of the curnit 
	@JcrProperty private String comment; // submitted for each new version
	@JcrProperty private String author; // author modifying each specific version of the curnit
	@JcrProperty private Date createdTime; // the time this version of the curnit was created
	
	private File otmlFile; // The otml file submitted by the external party
	
	//@JcrFileNode private JcrFile jcrOtml; // JcrFile type of the otml file to be persisted in jackrabbit
	//@JcrFileNode private List<JcrFile> resources; // attached files which will be referenced by the otml file

	/**
	 * The title set for each version of the curnit by the author
	 * @return String
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}


	/**
	 * The title set for each version of the curnit by the author
	 * @param title The title specified for each version of the curnit - titles can be the same from version to version
	 */
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}


	/**
	 * Get the read-only path to this node in jackrabbit - This is set by Jcrom
	 * @return String
	 */
	protected String getPath() {
		return path;
	}

	/**
	 * Get the unique string which defines this curnit
	 * @return String
	 */
	protected String getName() {
		return name;
	}


	/**
	 * Set the unique string which defines this curnit
	 * @param uniqueKey The string defining this curnit
	 */
	protected void setName(String name) {
		this.name = name;
	}


	/**
	 * Get the comment set by the author for this version of the curnit
	 * @return String 
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * Set the comment the author has submitted for this version of the curnit
	 * @param comment Author's comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * The the unique string identifying the author of this version of the curnit
	 * @return String
	 */
	public String getAuthor() {
		return author;
	}


	/**
	 * Set the unique string identifying the author of this version of the curnit
	 * @param author The unique string identifying the author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}


	protected Date getCreatedTime() {
		return createdTime;
	}


	protected void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
	public File getOtmlFile() {
		return otmlFile;
	}


	public void setOtmlFile(File otmlFile) {
		this.otmlFile = otmlFile;
	}


//	public List<JcrFile> getResources() {
//		return resources;
//	}
//
//
//	public void setResources(List<JcrFile> resources) {
//		this.resources = resources;
//	}


	public String toString(){
		return "Jcrom name is:\t" + this.getName() + "\n unique key is:\t" + this.getUniqueKey();
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	protected String getCurnitUuid() {
		return curnitUuid;
	}


	protected void setCurnitUuid(String curnitUuid) {
		this.curnitUuid = curnitUuid;
	}


	public String getCurnitPath() {
		return curnitPath;
	}


	public void setCurnitPath(String curnitPath) {
		this.curnitPath = curnitPath;
	}


//	protected JcrFile getJcrOtml() {
//		return jcrOtml;
//	}
//
//
//	protected void setJcrOtml(JcrFile jcrOtml) {
//		this.jcrOtml = jcrOtml;
//	}
}
