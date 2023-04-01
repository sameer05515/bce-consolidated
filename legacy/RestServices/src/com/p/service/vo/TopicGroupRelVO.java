package com.p.service.vo;

public class TopicGroupRelVO {
	private int id;
	private TopicVO topic;
	private GroupVO group;

	public TopicGroupRelVO(int id, TopicVO topic, GroupVO group) {
		super();
		this.id = id;
		this.topic = topic;
		this.group = group;
	}

	/**
	 * @return the id
	 */

	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the topic
	 */
	public TopicVO getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(TopicVO topic) {
		this.topic = topic;
	}

	/**
	 * @return the group
	 */
	public GroupVO getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(GroupVO group) {
		this.group = group;
	}

}