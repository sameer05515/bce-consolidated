package com.example.demo.rest;

import com.example.demo.pojo.TopicGroupRelation;
import com.example.demo.service.TopicGroupRelationService;
import com.example.demo.service.TopicService;
import com.example.demo.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TopicGroupRelationResource {

    @Autowired
    private TopicGroupRelationService topicGroupRelationService;

    @GetMapping("rest/tgservices/topics")
    public List<TopicGroupRelation> getTopicGroupRelationForTopic(@NonNull @RequestParam(value = "id") List<Integer> topicIdList){

        return topicGroupRelationService.getTopicGroupRelationForTopic(topicIdList);
    }

}
