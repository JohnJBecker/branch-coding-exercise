package com.exercise.accounts.github;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubRepoOwnerResponse implements Serializable {
    private String login;
    private Long id;
    private String avatarUrl;
    private String htmlUrl;
}
