package com.exercise.usermetadata.github;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GithubService {

    private final RestTemplate restTemplate = new RestTemplate();

    // GitHub API base URL
    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public GithubUser getGithubUserById(String id) {
        String url = GITHUB_API_URL + id;

        return restTemplate.getForObject(url, GithubUser.class);
    }

    public List<GithubRepo> getGithubReposByUserId(String id) {
        String url = GITHUB_API_URL + id + "/repos";

        GithubRepo[] repos = restTemplate.getForObject(url, GithubRepo[].class);
        if (repos == null) {
            return new ArrayList<>();
        }

        return Arrays.asList(repos);
    }
}
