package com.exercise.accounts.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubRepoResponse implements Serializable {
    private Long id;
    private String name;
    private String fullName;
    private GithubRepoOwnerResponse owner;
    private String htmlUrl;
    private String description;
    private boolean fork;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime pushedAt;
    private String homepage;
    private int size;
    private int stargazersCount;
    private int watchersCount;
    private String language;
    private int forksCount;
    private boolean archived;
    private boolean disabled;
    private String visibility;

    public GithubRepoResponse(String name, String htmlUrl) {
        this.name = name;
        this.htmlUrl = htmlUrl;
    }
}