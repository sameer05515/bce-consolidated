package com.example.demo.service.impl;


import com.example.demo.exception.domain.TopicNotFoundException;
import com.example.demo.pojo.Topic;
import com.example.demo.repository.TopicRepository;
import com.example.demo.service.TopicService;
import com.example.demo.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;
    @Override
    public List<Topic> find(int page, int size) {
        Page<Topic> topicPage = topicRepository.findAll(PageRequest.of(page, size));
        return topicPage.get().collect(Collectors.toList());

//        .map(topic -> {
//            TopicVO topicVO = new TopicVO();
//            topicVO.setId(topic.getId());
//            topicVO.setTitle(topic.getTitle());
//            return topicVO;
//        })
    }

    @Override
    public List<Topic> getAll() {
        List<Topic> topics= new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    @Override
    public int create(Topic lob) {
        return 0;
    }

    @Override
    public boolean update(Topic lob) {
        return false;
    }

    @Override
    public List<Topic> updateAll(List<Topic> lob) {
        List<Topic> topics=new ArrayList<>();
        //topicRepository.saveAll(lob).forEach(topics::add);
        return topics;
    }

    @Override
    public TopicVO get(Integer id) throws TopicNotFoundException {
        return topicRepository.findById(id).map(t->{
           return TopicVO.map(t);
        }).orElseThrow(()-> new TopicNotFoundException("No topic found for id"+id));
    }

    @Override
    public Topic get(String uniqueStrid) {
        return null;
    }
}
