package com.exercise.accounts.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubService {

    @Autowired
    private GithubClient githubClient;

    @Cacheable(value = "githubUserCache", key = "#username")
    public GithubUserResponse getGithubUserByUsername(String username) {
        return githubClient.getUserByUsername(username);
    }

    @Cacheable(value = "githubReposCache", key = "#username")
    public List<GithubRepoResponse> getGithubReposByUsername(String username) {
        return githubClient.getReposByUsername(username);
    }
}
