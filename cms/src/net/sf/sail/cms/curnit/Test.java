package net.sf.sail.cms.curnit;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;

public class Test {


	public static void main (String[] args) throws CreateCurnitException, RetrieveCurnitException{
		CurnitManagementImpl curMgr = new CurnitManagementImpl();
		CurnitOtmlImpl cur = new CurnitOtmlImpl();
		cur.setCurnitName("my name is curnit");
		cur.setCurnitNumber("abcd-1234");
		curMgr.createCurnit(cur);
		curMgr.retrieveCurnit(null);
	}
}
