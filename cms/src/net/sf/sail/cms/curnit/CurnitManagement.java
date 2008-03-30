package net.sf.sail.cms.curnit;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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
	public CurnitManagementResponse createCurnit (Curnit curnit);
	
	/*
	 * Given a list of unique curnit numbers as well as the desired versions for each
	 * curnit (in a Map format), a Map is returned which contains the curnit key 
	 * (as the map key) and all the requested versions as a another Map. The second 
	 * map contains the version number as the key and the Curnit object as value of the map.
	 */
	public Map<String, Map<BigInteger, Curnit>> retrieveCurnit (Map<String,List> curnitMap);

	/*
	 * Update the version of the curnit in the CMS with the given curnit values
	 */
	public CurnitManagementResponse updateCurnit(Curnit curnit);
	
	/*
	 * Permanently remove the curnit from CMS.
	 */
	public CurnitManagementResponse deleteCurnit(Curnit curnit);
	
}
