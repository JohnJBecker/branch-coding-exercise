package com.exercise.accounts.github;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "githubClient", url = "${github.api.url}", configuration = GithubClientConfig.class)
public interface GithubClient {

    @GetMapping("/users/{username}")
    GithubUserResponse getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/users/{username}/repos")
    List<GithubRepoResponse> getReposByUsername(@PathVariable("username") String username);
}