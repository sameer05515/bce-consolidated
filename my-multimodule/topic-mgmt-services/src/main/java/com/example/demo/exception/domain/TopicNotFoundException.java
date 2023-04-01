package com.example.demo.exception.domain;

public class TopicNotFoundException extends Exception {
    public TopicNotFoundException(String message) {
        super(message);
    }
}