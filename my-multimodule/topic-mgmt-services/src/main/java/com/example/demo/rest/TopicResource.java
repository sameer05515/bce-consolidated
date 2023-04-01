package com.example.demo.rest;


import com.example.demo.exception.ExceptionHandling;
import com.example.demo.exception.domain.TopicNotFoundException;
import com.example.demo.pojo.TopicHistory;
import com.example.demo.service.TopicHistoryService;
import com.example.demo.service.TopicService;
import com.example.demo.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class TopicResource extends ExceptionHandling {


    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicHistoryService topicHistoryService;

    @GetMapping("rest/topics")
    public List<TopicVO> getAll(){
        return topicService.getAll().stream().map(topic -> {
            TopicVO topicVO = new TopicVO();
            topicVO.setId(topic.getId());
            topicVO.setTitle(topic.getTitle());
            topicVO.setUniqueStrid(topic.getUniqueStrid());
            // topicVO.setDescription(topic.getDescription());
            return topicVO;
        }).collect(Collectors.toList());
    }

    @GetMapping("rest/topics/{id}")
    public TopicVO getById(@PathVariable Integer id) throws TopicNotFoundException {
       return topicService.get(id);
    }

    @GetMapping("rest/topics/{id}/history/{action}")
    public List<TopicHistory> getTopicHistory(@NonNull @PathVariable("id") int id, @NonNull @PathVariable("action") String action) {
        return topicHistoryService.getTopicHistory(id,action);
    }
    
    @GetMapping("rest/topics/find")
    public List<TopicVO> find(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return topicService.find(page,size).stream().map(topic -> {
            TopicVO topicVO = new TopicVO();
            topicVO.setId(topic.getId());
            topicVO.setTitle(topic.getTitle());
            topicVO.setUniqueStrid(topic.getUniqueStrid());
            return topicVO;
        }).collect(Collectors.toList());
    }
}
