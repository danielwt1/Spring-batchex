package com.example.batchrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDTO {
    private Long userId;
    private int id;
    private String title;
    private String body;

    public PostDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
