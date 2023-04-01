package com.p.service.dao;

import java.util.List;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.GroupViewRelation;

// TODO: Auto-generated Javadoc
/**
 * The Interface CoachingActionInterface.
 */
public interface GroupViewRelationSession {
	
	
	
	/**
	 * Adds the groups to views .
	 *
	 * @param groupIdList
	 * @param viewIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public int addGroupsToViews(List<Integer> groupIdList,List<Integer> viewIdList)
			throws RestServiceException;
	
	/**
	 * Adds the groups to views .
	 *
	 * @param groupIdList
	 * @param viewIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public List<GroupViewRelation> getAll() throws RestServiceException;

	/**
	 * Adds the groups to views .
	 *
	 * @param groupIdList
	 * @param viewIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public List<GroupViewRelation> getGroupViewRelationListForGroup(List<Integer> groupIdList) throws RestServiceException;

	/**
	 * Adds the groups to views .
	 *
	 * @param groupIdList
	 * @param viewIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public List<GroupViewRelation> getGroupViewRelationForView(List<Integer> viewIdList) throws RestServiceException;
	
//	/**
//	 * Gets the coaching sessions grid.
//	 *
//	 * @param accountId the account id
//	 * @param publishAccStatus the publish acc status
//	 * @param dateFrom the date from
//	 * @param dateTo the date to
//	 * @return the coaching sessions grid
//	 * @throws ZettaCoachingException the zetta coaching exception
//	 */ 	
// 	public List<Group> getAll() throws RestServiceException;
// 	
// 	/**
//	 * Gets the coaching sessions grid.
//	 *
//	 * @param accountId the account id
//	 * @param publishAccStatus the publish acc status
//	 * @param dateFrom the date from
//	 * @param dateTo the date to
//	 * @return the coaching sessions grid
//	 * @throws ZettaCoachingException the zetta coaching exception
//	 */ 
//	public int create(Group lob) throws RestServiceException ;
//
//	/**
//	 * Gets the coaching sessions grid.
//	 *
//	 * @param accountId the account id
//	 * @param publishAccStatus the publish acc status
//	 * @param dateFrom the date from
//	 * @param dateTo the date to
//	 * @return the coaching sessions grid
//	 * @throws ZettaCoachingException the zetta coaching exception
//	 */ 
//	public boolean update(Group lob) throws RestServiceException;
//
//	/**
//	 * Gets the coaching sessions grid.
//	 *
//	 * @param accountId the account id
//	 * @param publishAccStatus the publish acc status
//	 * @param dateFrom the date from
//	 * @param dateTo the date to
//	 * @return the coaching sessions grid
//	 * @throws ZettaCoachingException the zetta coaching exception
//	 */ 
//	public Group get(Integer id) throws RestServiceException; 	
	
	
	
}
