package com.exercise.accounts.user;

import com.exercise.accounts.exception.NotFoundException;
import com.exercise.accounts.github.GithubClient;
import com.exercise.accounts.github.GithubRepoResponse;
import com.exercise.accounts.github.GithubService;
import com.exercise.accounts.github.GithubUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private GithubClient githubClient;

    @InjectMocks
    private GithubService githubService;

    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(githubService);
    }

    @Test
    public void testUserServiceGetByUsername() {
        // Mock github user client call response
        GithubUserResponse githubUser = new GithubUserResponse("login", 1L, "http://avatar.com", "http://user.com", "name", "company", "blog", "location", "email@email.com", "bio", LocalDateTime.now(), LocalDateTime.now());
        when(githubClient.getUserByUsername(githubUser.getName())).thenReturn(githubUser);

        // Mock github repos client call response
        GithubRepoResponse githubUserRepo = new GithubRepoResponse("name", "http://repo.com");
        when(githubClient.getReposByUsername(githubUser.getName())).thenReturn(List.of(githubUserRepo));

        User got = userService.getUserByUsername(githubUser.getName());

        verify(githubClient, times(1)).getUserByUsername(githubUser.getName());
        verify(githubClient, times(1)).getReposByUsername(githubUser.getName());

        User want = new User(
                githubUser.getLogin(),
                githubUser.getName(),
                githubUser.getAvatarUrl(),
                githubUser.getLocation(),
                githubUser.getEmail(),
                githubUser.getHtmlUrl(),
                githubUser.getCreatedAt(),
                List.of(new UserRepo(githubUserRepo.getName(), githubUserRepo.getHtmlUrl()))
        );

        // validate results
        assertEquals(want, got);
    }

    @Test
    public void testUserServiceGetByUsernameUserNotFound() {
        // Mock github user client call response
        GithubUserResponse githubUser = new GithubUserResponse("login", 1L, "http://avatar.com", "http://user.com", "name", "company", "blog", "location", "email@email.com", "bio", LocalDateTime.now(), LocalDateTime.now());
        when(githubClient.getUserByUsername(githubUser.getName())).thenThrow(new NotFoundException("not found"));

        Exception thrown = assertThrows(NotFoundException.class, () -> userService.getUserByUsername(githubUser.getName()));

        verify(githubClient, times(1)).getUserByUsername(githubUser.getName());
        verify(githubClient, times(0)).getReposByUsername(githubUser.getName());

        // validate results
        assertEquals("not found", thrown.getMessage());
    }
}
