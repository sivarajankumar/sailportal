package net.sf.sail.cms.curnit;

public class Curnit {
	
	// Unique number defining each curnit
	private String curnitNumber;
	
	// Name given to the curnit by the author
	private String curnitName;

	// Comment added to each submitted version of the curnit 
	private String comment;
	
	/*
	 * The rest of the attributes associated to a Curnit object, to be defined
	 * 
	 * OTML - A file
	 * List of data refered to in the OTML file - These can be either binary data or URL to links online
	 * author - I think we should create and Author object with (name, last name, user name...etc)
	 * version - used by CMS side to let retriever of the curnit know of their version
	 * timestamp - to be generated/used on CMS end
	 * 
	 */
	
}
