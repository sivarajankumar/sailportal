package net.sf.sail.cms.curnit;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.jackrabbit.ocm.manager.ObjectContentManager;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.AnnotationMapperImpl;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

public class CurnitManagementImpl implements CurnitManagement {
	
	public CurnitManagementResponse createCurnit(CurnitOtmlImpl curnit)
			throws CreateCurnitException {
		
		ObjectContentManager ocm = init();
		
		ocm.insert(curnit);
		ocm.save();
		return null;
	}


	public CurnitManagementResponse deleteCurnit(CurnitOtmlImpl curnit)
			throws DeleteCurnitException {
		
		ObjectContentManager ocm = init();
		CurnitOtmlImpl cur = (CurnitOtmlImpl) ocm.getObject("/abcd-1234");
		cur.toString();

		return null;
	}

	public Map<String, Map<BigInteger, ? extends Curnit>> retrieveCurnit(Map<String, List<BigInteger>> curnitMap)
			throws RetrieveCurnitException {

		return null;
	}

	public CurnitManagementResponse updateCurnit(CurnitOtmlImpl curnit)
			throws UpdateCurnitException {

		return null;
	}

	public CurnitManagementResponse purgeCurnit(CurnitOtmlImpl curnit) {

		return null;
	}

	public CurnitManagementResponse restoreCurnit(CurnitOtmlImpl curnit) {

		return null;
	}

	
	private ObjectContentManager init() {
		Repository repository;
		Session session = null;
		ObjectContentManager ocm = null;
		
		try {
			repository = new TransientRepository();
			session = repository.login(new SimpleCredentials("username",
					"password".toCharArray()));
			
			List<Class> classes = new ArrayList<Class>();	
			classes.add(CurnitOtmlImpl.class); // Call this method for each persistent class
					
			Mapper mapper = new AnnotationMapperImpl(classes);
			ocm =  new ObjectContentManagerImpl(session, mapper);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}finally {
			session.logout();
		}
		return ocm;
		
	}


}
