package com.sandi.testtech.model;

import com.google.gson.annotations.SerializedName;

public class DataExample {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @SerializedName("id")
    private Integer id;

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;
}
