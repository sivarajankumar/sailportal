package net.sf.sail.cms.curnit;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import net.sf.sail.cms.exceptions.CreateCurnitException;
import net.sf.sail.cms.exceptions.CurnitManagementExceptions;
import net.sf.sail.cms.exceptions.DeleteCurnitException;
import net.sf.sail.cms.exceptions.RetrieveCurnitException;
import net.sf.sail.cms.exceptions.UpdateCurnitException;

/**
 * The interface which allows outside parties to communicate with the
 * CMS infrastructure.
 * 
 * @author rokham
 *
 */
public interface CurnitManagement {
	
	/*
	 * Given a Curnit object, the object is versioned and persisted in the CMS.
	 */
	public CurnitManagementResponse createCurnit (CurnitOtmlImpl curnit) throws CreateCurnitException;
	
	/*
	 * Given a list of unique curnit numbers as well as the desired versions for each
	 * curnit (in a Map format), a Map is returned which contains the curnit key 
	 * (as the map key) and all the requested versions as a another Map. The second 
	 * map contains the version number as the key and the Curnit object as value of the map.
	 * 
	 * Note: To get the latest version of a curnit send '-1' as the curnit version
	 * To get all the versions of a curnit send a 'NULL' as the curnit version
	 * To get one or more specific versions of a curnit sent a list containing their version numbers.
	 */
	public Map<String, Map<BigInteger, ? extends Curnit>> retrieveCurnit (Map<String,List<BigInteger>> curnitMap)
		throws RetrieveCurnitException;

	/*
	 * Update the version of the curnit in the CMS with the given curnit values
	 */
	public CurnitManagementResponse updateCurnit(CurnitOtmlImpl curnit) throws UpdateCurnitException;
	
	/*
	 * Mark curnit deleted from CMS.
	 */
	public CurnitManagementResponse deleteCurnit(CurnitOtmlImpl curnit) throws DeleteCurnitException;
	
	/*
	 * Restore a curnit which was deleted earlier
	 */
	public CurnitManagementResponse restoreCurnit(CurnitOtmlImpl curnit);
	
	/*
	 * Permanently delete a curnit from CMS
	 */
	public CurnitManagementResponse purgeCurnit (CurnitOtmlImpl curnit);
}
