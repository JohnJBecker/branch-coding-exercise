package com.exercise.accounts.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class GithubRepoResponse implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("owner")
    private GithubRepoOwnerResponse owner;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("description")
    private String description;

    @JsonProperty("fork")
    private boolean fork;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("pushed_at")
    private LocalDateTime pushedAt;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("size")
    private int size;

    @JsonProperty("stargazers_count")
    private int stargazersCount;

    @JsonProperty("watchers_count")
    private int watchersCount;

    @JsonProperty("language")
    private String language;

    @JsonProperty("forks_count")
    private int forksCount;

    @JsonProperty("archived")
    private boolean archived;

    @JsonProperty("disabled")
    private boolean disabled;

    @JsonProperty("visibility")
    private String visibility;
}