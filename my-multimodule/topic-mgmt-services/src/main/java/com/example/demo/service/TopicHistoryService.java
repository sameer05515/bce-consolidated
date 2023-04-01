package com.example.demo.service;

import com.example.demo.pojo.TopicHistory;

import java.util.List;

public interface TopicHistoryService {

    public List<TopicHistory> getTopicHistory(Integer topicId,  String action);
}
