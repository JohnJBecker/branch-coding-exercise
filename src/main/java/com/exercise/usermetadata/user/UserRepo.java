package com.exercise.usermetadata.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRepo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
