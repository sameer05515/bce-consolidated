package com.example.demo.service.impl;

import com.example.demo.pojo.TopicGroupRelation;
import com.example.demo.repository.TopicGroupRelationRepository;
import com.example.demo.repository.TopicRepository;
import com.example.demo.service.TopicGroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicGroupRelationServiceImpl implements TopicGroupRelationService {

    @Autowired
    TopicGroupRelationRepository topicGroupRelationRepository;
    @Override
    public List<TopicGroupRelation> getTopicGroupRelationForTopic(List<Integer> topicIdList) {
        return topicGroupRelationRepository.getTopicGroupRelationForTopic(topicIdList);
    }
}
