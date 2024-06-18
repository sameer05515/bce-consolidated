package com.p.service.dao;

import java.util.List;

import com.p.service.exception.RestServiceException;
import com.p.service.pojo.TopicGroupRelation;

/**
 * The Interface CoachingActionInterface.
 */
public interface TopicGroupRelationSession {

	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId
	 *            the account id
	 * @param publishAccStatus
	 *            the publish acc status
	 * @param dateFrom
	 *            the date from
	 * @param dateTo
	 *            the date to
	 * @return the coaching sessions grid
	 * @throws RestServiceException
	 */
	public int getTopicCountForGroup(Integer groupId) throws RestServiceException;

	/**
	 * Gets the coaching sessions grid.
	 *
	 * @param accountId
	 *            the account id
	 * @param publishAccStatus
	 *            the publish acc status
	 * @param dateFrom
	 *            the date from
	 * @param dateTo
	 *            the date to
	 * @return the coaching sessions grid
	 * @throws RestServiceException
	 */
	public int getGroupCountForTopic(Integer topicId) throws RestServiceException;

	/**
	 * Gets the employees in lob.
	 *
	 * @param lobID
	 *            the lob id
	 * @return the employees in lob
	 * @throws RestServiceException
	 */
	public List<TopicGroupRelation> getTopicGroupRelationForTopic(List<Integer> topicIdList)
			throws RestServiceException;

	/**
	 * Gets the employees in lob.
	 *
	 * @param lobID
	 *            the lob id
	 * @return the employees in lob
	 * @throws RestServiceException
	 */
	public List<TopicGroupRelation> getTopicGroupRelationForGroup(List<Integer> groupIdList)
			throws RestServiceException;

	// /**
	// * Adds the topics to group.
	// *
	// * @param groupId
	// * @param topicIdList
	// * @return int status for success/failure for given operation
	// * @throws RestServiceException
	// */
	// public int addTopicsToGroup(int groupId,List<Integer> topicIdList) throws
	// RestServiceException;

	/**
	 * Adds the topics to groups.
	 *
	 * @param topicIdList
	 * @param groupIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public int addTopicsToGroups(List<Integer> topicIdList, List<Integer> groupIdList)
			throws RestServiceException;

	/**
	 * Removes the topics from group.
	 *
	 * @param groupId
	 *            the lob id
	 * @param topicIdList
	 * @return int status for success/failure for given operation
	 * @throws RestServiceException
	 */
	public int untagRelation(int groupId, int topicId) throws RestServiceException;

	public List<TopicGroupRelation> getAll() throws RestServiceException;
}
