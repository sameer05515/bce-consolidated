package com.example.demo.repository;

import com.example.demo.pojo.TopicHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicHistoryRepository extends PagingAndSortingRepository<TopicHistory, Integer> {

    @Query("select th from TopicHistory th where topic_id= :topicId and action= :action")
    public List<TopicHistory> getTopicHistory(@Param("topicId")Integer topicId, @Param("action") String action);
}
