package com.example.demo.repository;


import com.example.demo.pojo.Topic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TopicRepository extends PagingAndSortingRepository<Topic, Integer> {
}
