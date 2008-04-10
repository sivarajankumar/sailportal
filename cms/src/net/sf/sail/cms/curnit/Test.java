package net.sf.sail.cms.curnit;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;

public class Test {


	public static void main (String[] args) throws CreateCurnitException, RetrieveCurnitException, DeleteCurnitException{
		CurnitManagementImpl curMgr = new CurnitManagementImpl();
		
		CurnitOtmlImpl cur1 = new CurnitOtmlImpl();
		cur1.setName("/1");
		cur1.setTitle("name1");		
		
		CurnitOtmlImpl cur2 = new CurnitOtmlImpl();
		cur2.setName("/2");
		cur2.setTitle("name2");
		
		CurnitOtmlImpl cur3 = new CurnitOtmlImpl();
		cur3.setName("/3");
		cur3.setTitle("name3");
		
		curMgr.createCurnit(cur1, "user", "password");
		curMgr.createCurnit(cur2, "user", "password");
		curMgr.createCurnit(cur3, "user", "password");
		
		Map<String, List<Float>> curnitsToRetrieve = new HashMap<String, List<Float>>();
		curnitsToRetrieve.put(cur1.getName(), null);
		curnitsToRetrieve.put(cur3.getName(), null);
		
		curMgr.retrieveCurnit(curnitsToRetrieve, "user", "password");

		
		//curMgr.deleteCurnit(cur1, "username", "password");
		//curMgr.createCurnit(cur, "username", "password");
	}
}
