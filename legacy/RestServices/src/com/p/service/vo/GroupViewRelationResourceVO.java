package com.p.service.vo;

import java.util.List;

public class GroupViewRelationResourceVO {
	
	List<Integer> groupIdList;
	List<Integer> viewIdList;
	/**
	 * @return the groupIdList
	 */
	public List<Integer> getGroupIdList() {
		return groupIdList;
	}
	/**
	 * @param groupIdList the groupIdList to set
	 */
	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}
	/**
	 * @return the viewIdList
	 */
	public List<Integer> getViewIdList() {
		return viewIdList;
	}
	/**
	 * @param viewIdList the viewIdList to set
	 */
	public void setViewIdList(List<Integer> viewIdList) {
		this.viewIdList = viewIdList;
	}
}
