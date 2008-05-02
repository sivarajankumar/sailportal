package net.sf.sail.cms.curnit;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.sail.cms.curnit.impl.CurnitManagementImpl;
import net.sf.sail.cms.curnit.impl.CurnitOtmlImpl;
import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

public class Test {


	public static void main (String[] args) throws CreateCurnitException, RetrieveCurnitException, DeleteCurnitException, UpdateCurnitException{
		CurnitManagementImpl curMgr = new CurnitManagementImpl();
		
		CurnitOtmlImpl cur1 = new CurnitOtmlImpl();
		cur1.setUniqueKey("a-b-c");		
		cur1.setTitle("title");
		cur1.setComment("comment");
		cur1.setAuthor("author");
		cur1.setOtmlFile(new File("/Users/cfislotta/Desktop/pas-test.otml"));
		
		// add resources
		File res1 = new File("/Users/cfislotta/personal/Flickr pics/one.txt");
		File res2 = new File("/Users/cfislotta/personal/Flickr pics/rokham-graduation-hat.JPG");
		List<File> resources = new ArrayList<File>();
		resources.add(res1);
//		resources.add(res2);
		
		cur1.setOtmlResources(resources);
		
		CurnitOtmlImpl cur2 = new CurnitOtmlImpl();
		cur2.setUniqueKey("e-f-g");		
		cur2.setTitle("title2");
		cur2.setOtmlFile(new File("/Users/cfislotta/Desktop/pas-test.otml"));
		
//		CurnitOtmlImpl cur3 = new CurnitOtmlImpl();
//		cur3.setUniqueKey("/3");
//		cur3.setTitle("name3");
		
//		curMgr.createCurnit(cur1, "user", "password");
//		curMgr.createCurnit(cur2, "user", "password");
//		curMgr.createCurnit(cur3, "user", "password");
		
		Map<String, List<Float>> curnitsToRetrieve = new HashMap<String, List<Float>>();
		List<Float> versions =  new ArrayList<Float>();
		versions.add(new Float("-1"));
		curnitsToRetrieve.put(cur1.getUniqueKey(), versions);
		
			
//		curMgr.createCurnit(cur1, "user", "password");		
//		curMgr.retrieveCurnit(curnitsToRetrieve, "user", "password");
		
//	    curMgr.deleteCurnit(cur1, "username", "password");

		
		cur1.setUniqueKey("a-b-c");
		cur1.setAuthor("author3");
		cur1.setComment("comment3");
		cur1.setTitle("title3");
		cur1.setOtmlFile(new File("/Users/cfislotta/Desktop/pas-test.otml"));
		
		res1 = new File("/Users/cfislotta/personal/Flickr pics/two.txt");
		res2 = new File("/Users/cfislotta/personal/Flickr pics/rokham-graduation-hat-edited.jpg");
		File res3 = new File("/Users/cfislotta/personal/Flickr pics/blue man graffiti.jpg");
		resources = new ArrayList<File>();
		resources.add(res1);
		resources.add(res2);
		resources.add(res3);
		cur1.setOtmlResources(resources);
		
		curMgr.updateCurnit(cur1, "username", "password");

	}
}
