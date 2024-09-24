package com.exercise.accounts.user;

import com.exercise.accounts.github.GithubRepo;
import com.exercise.accounts.github.GithubService;
import com.exercise.accounts.github.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private GithubService githubService;

    public User getUserByUsername(String id) {
        // TODO handle errors
        GithubUser githubUser = githubService.getGithubUserByUsername(id);

        List<GithubRepo> githubUserRepos = githubService.getGithubReposByUsername(id);

        return newUserFromGithubResponses(githubUser, githubUserRepos);
    }

    private User newUserFromGithubResponses(GithubUser user, List<GithubRepo> repos) {
        User out = new User();

        out.setUserName(user.getLogin());
        out.setDisplayName(user.getName());
        out.setAvatar(user.getAvatarUrl());
        out.setGeoLocation(user.getLocation());
        out.setEmail(user.getEmail());
        out.setUrl(user.getHtmlUrl());
        out.setCreatedAt(user.getCreatedAt());

        List<UserRepo> userRepos = repos.stream().map(repo -> {
            UserRepo userRepo = new UserRepo();

            userRepo.setName(repo.getName());
            userRepo.setUrl(repo.getHtmlUrl());

            return userRepo;
        }).toList();

        out.setRepos(userRepos);

        return out;
    }
}
