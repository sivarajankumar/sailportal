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
	
	@JcrName private String name; // name of the node set by Jcrom
	@JcrPath private String path; // mandatory attribute -- requested by Jcrom
	
	@JcrProperty private String uniqueKey; // unique string defining each curnit 
	@JcrProperty private String curnitUuid; // curnit's uuid assigned by jackrabbit
	@JcrProperty private String title; // title set for each version of the curnit 
	@JcrProperty private String comment; // submitted for each new version
	@JcrProperty private String author; // author modifying each specific version of the curnit
	@JcrProperty private Date createdTime; // the time this version of the curnit was created
	@JcrProperty private String curnitVersion; // the uniqe version of this curnit
	
	private File otmlFile; // The otml file submitted by the external party
	private List<File> otmlResources; // The resource files submitted by the external party
	
	@JcrFileNode private JcrFile jcrOtml; // JcrFile type of the otml file to be persisted in jackrabbit
	@JcrFileNode private List<JcrFile> jcrResources; // attached files which will be referenced by the otml file
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getUniqueKey() {
		return uniqueKey;
	}
	
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
	public String getCurnitUuid() {
		return curnitUuid;
	}
	
	public void setCurnitUuid(String curnitUuid) {
		this.curnitUuid = curnitUuid;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getCurnitVersion() {
		return curnitVersion;
	}
	
	public void setCurnitVersion(String curnitVersion) {
		this.curnitVersion = curnitVersion;
	}
	
	public File getOtmlFile() {
		return otmlFile;
	}
	
	public void setOtmlFile(File otmlFile) {
		this.otmlFile = otmlFile;
	}
	
	public List<File> getOtmlResources() {
		return otmlResources;
	}
	
	public void setOtmlResources(List<File> otmlResources) {
		this.otmlResources = otmlResources;
	}
	
	public JcrFile getJcrOtml() {
		return jcrOtml;
	}
	
	public void setJcrOtml(JcrFile jcrOtml) {
		this.jcrOtml = jcrOtml;
	}
	
	public List<JcrFile> getJcrResources() {
		return jcrResources;
	}
	
	public void setJcrResources(List<JcrFile> jcrResources) {
		this.jcrResources = jcrResources;
	}
}
