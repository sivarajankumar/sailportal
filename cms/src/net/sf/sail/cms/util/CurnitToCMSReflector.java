package net.sf.sail.cms.util;

import javax.jcr.Node;

import net.sf.sail.cms.curnit.Curnit;

public class CurnitToCMSReflector {
	
	public static Node CurnitToNode (Curnit curnit){
		
		try {
			Class curnitClass = Class.forName("CurnitOtmlImpl");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
