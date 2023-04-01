package com.example.demo.service.impl;

import com.example.demo.pojo.TopicHistory;
import com.example.demo.repository.TopicHistoryRepository;
import com.example.demo.service.TopicHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicHistoryServiceImpl implements TopicHistoryService {

    @Autowired
    TopicHistoryRepository topicHistoryRepository;
    @Override
    public List<TopicHistory> getTopicHistory(Integer topicId, String action) {
        return topicHistoryRepository.getTopicHistory(topicId,action);
    }
}
