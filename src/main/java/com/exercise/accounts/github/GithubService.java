package com.exercise.accounts.github;

import com.exercise.accounts.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GithubService {

    @Autowired
    private GithubClient githubClient;

    @Cacheable(value = "githubUserCache", key = "#username")
    public GithubUserResponse getGithubUserByUsername(String username) {
        try {
            return githubClient.getUserByUsername(username);
        } catch (NotFoundException ex) {
            throw new NotFoundException("github user not found");
        }
    }

    @Cacheable(value = "githubReposCache", key = "#username")
    public List<GithubRepoResponse> getGithubReposByUsername(String username) {
        try {
            return githubClient.getReposByUsername(username);
        } catch (NotFoundException ex) {
            throw new NotFoundException("github user's repos not found");
        }
    }
}
