package com.example.demo.service;

import com.example.demo.pojo.TopicGroupRelation;

import java.util.List;

public interface TopicGroupRelationService {

    public List<TopicGroupRelation> getTopicGroupRelationForTopic(List<Integer> topicIdList);
}
