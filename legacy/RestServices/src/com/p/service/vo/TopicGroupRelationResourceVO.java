package com.p.service.vo;

import java.util.LinkedList;
import java.util.List;

public class TopicGroupRelationResourceVO {

	List<Integer> topicIdList=new LinkedList<Integer>();
	List<Integer> groupIdList=new LinkedList<Integer>();

	/**
	 * @return the topicIdList
	 */
	public List<Integer> getTopicIdList() {
		return topicIdList;
	}

	/**
	 * @param topicIdList
	 *            the topicIdList to set
	 */
//	public void setTopicIdList(List<Integer> topicIdList) {
//		this.topicIdList = topicIdList;
//	}

	/**
	 * @return the groupIdList
	 */
	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	/**
	 * @param groupIdList
	 *            the groupIdList to set
	 */
//	public void setGroupIdList(List<Integer> groupIdList) {
//		this.groupIdList = groupIdList;
//	}

}
