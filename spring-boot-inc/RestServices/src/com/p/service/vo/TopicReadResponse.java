package com.p.service.vo;

import java.util.ArrayList;
import java.util.List;

import com.p.service.pojo.Topic;
import com.p.service.pojo.TopicHistory;

public class TopicReadResponse {

	private int topicId;
	private Topic topic;
	private int count = 0;
	private List<TopicHistory> reads = new ArrayList<>();

	public TopicReadResponse(int topicId, Topic topic, List<TopicHistory> reads) {
		super();
		this.topicId = topicId;
		this.topic = topic;
		if (reads != null && reads.size() > 0) {
			this.count = reads.size();

			this.reads = reads;
		}
	}

	public TopicReadResponse() {
		super();
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getCount() {
		if (reads != null && reads.size() > 0)
			this.count=reads.size();
			
			return count;
	}

	// public void setCount(int count) {
	// this.count = count;
	// }
	public List<TopicHistory> getReads() {
		return reads;
	}

	public void setReads(List<TopicHistory> reads) {
		if (reads != null && reads.size() > 0)
			this.reads = reads;
	}

}
