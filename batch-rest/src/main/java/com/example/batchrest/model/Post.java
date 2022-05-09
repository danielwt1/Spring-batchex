package com.example.batchrest.model;

public class Post {
    private Long userId;
    private int id;
    private String tittle;
    private String body;

    public Post(Long userId, int id, String tittle, String body) {
        this.userId = userId;
        this.id = id;
        this.tittle = tittle;
        this.body = body;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", tittle='" + tittle + '\'' +
                ", body='" + body + '\'' +
                '}';
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
