package com.exercise.usermetadata.user;

import com.exercise.usermetadata.github.GithubRepo;
import com.exercise.usermetadata.github.GithubService;
import com.exercise.usermetadata.github.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private GithubService githubService;

    public User getUserById(String id) {
        GithubUser githubUser = githubService.getGithubUserById(id);

        List<GithubRepo> githubUserRepos = githubService.getGithubReposByUserId(id);

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
        out.setCreatedAt(user.getCreateAt());

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
