package curnitTestSuite;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import net.sf.sail.cms.curnit.CurnitManagement;
import net.sf.sail.cms.curnit.CurnitManagementResponse;
import net.sf.sail.cms.curnit.impl.CurnitManagementImpl;
import net.sf.sail.cms.curnit.impl.CurnitOtmlImpl;
import net.sf.sail.cms.exceptions.CreateCurnitException;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert.*;

public class CreateCurnitTests extends TestCase{

	private CurnitManagement curMng;
	
	@Before
	public void setUp() throws Exception {
		 curMng = new CurnitManagementImpl();
	}
	
	public void testOnlyUniqueKey(){
		CurnitOtmlImpl curnit = new CurnitOtmlImpl();
		curnit.setUniqueKey("a-b-c");
		curnit.setOtmlFile(new File("/workspace/cms/src/curnitTestSuite/pas-test.otml"));
		
		try {
			CurnitManagementResponse response = curMng.createCurnit(curnit, "user", "password");
			//TODO check response's returned code. I believe code should be an integer
		} catch (CreateCurnitException e) {
			e.printStackTrace();
			Assert.assertTrue(1 == 2);
		}		
	}
	
	
	
	
	private CurnitOtmlImpl constructCurnit(){
		CurnitOtmlImpl cur = new CurnitOtmlImpl();
		cur.setAuthor("author");
		cur.setComment("comment");
		cur.setUniqueKey("a-b-c");
		cur.setTitle("title");
		cur.setOtmlFile(new File("cms/src/curnitTestSuite/pas-test.otml"));
		
		File res1 = new File("cms/src/curnitTestSuite/one.txt");
		File res2 = new File("cms/src/curnitTestSuite/two.txt");
		List<File> resources = new ArrayList<File>();
		resources.add(res1);
		resources.add(res2);
		cur.setOtmlResources(resources);
		
		return cur;
	}
	

	@After
	public void tearDown() throws Exception {
	}	
}
