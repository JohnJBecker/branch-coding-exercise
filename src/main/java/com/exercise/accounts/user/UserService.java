package com.exercise.accounts.user;

import com.exercise.accounts.github.GithubRepoResponse;
import com.exercise.accounts.github.GithubService;
import com.exercise.accounts.github.GithubUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private GithubService githubService;

    public UserService(GithubService githubService) {
        this.githubService = githubService;
    }

    public User getUserByUsername(String username) {

        GithubUserResponse githubUser = githubService.getGithubUserByUsername(username);

        List<GithubRepoResponse> githubUserRepos = githubService.getGithubReposByUsername(username);

        return new User(
                githubUser.getLogin(),
                githubUser.getName(),
                githubUser.getAvatarUrl(),
                githubUser.getLocation(),
                githubUser.getEmail(),
                githubUser.getHtmlUrl(),
                githubUser.getCreatedAt(),
                githubUserRepos.stream()
                        .map(repo -> new UserRepo(repo.getName(), repo.getHtmlUrl()))
                        .toList()
        );
    }
}
