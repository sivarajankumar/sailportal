package net.sf.sail.cms.curnit;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.TransientRepository;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

public class CurnitManagementImpl implements CurnitManagement {

	public CurnitManagementResponse createCurnit(CurnitOtmlImpl curnit)
			throws CreateCurnitException {
		
		Repository repository;
		try {
			repository = new TransientRepository();
			
			// Login to the default workspace as a dummy user
			Session session = repository.login(new SimpleCredentials("username",
						"password".toCharArray()));
			
			Node node = curnit.curnitToNode(session);
			session.save();
			node.checkin();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public CurnitManagementResponse deleteCurnit(CurnitOtmlImpl curnit)
			throws DeleteCurnitException {

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



}
