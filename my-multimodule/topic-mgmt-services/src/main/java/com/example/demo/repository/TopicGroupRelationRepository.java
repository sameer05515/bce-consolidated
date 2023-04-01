package com.example.demo.repository;

import com.example.demo.pojo.Topic;
import com.example.demo.pojo.TopicGroupRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicGroupRelationRepository extends PagingAndSortingRepository<TopicGroupRelation, Integer> {

    @Query("select tr from TopicGroupRelation tr where tr.topics.id in (:topicIdList)")
    public List<TopicGroupRelation> getTopicGroupRelationForTopic(@Param("topicIdList") List<Integer> topicIdList);
}
