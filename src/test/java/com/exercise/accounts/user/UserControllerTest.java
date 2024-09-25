package com.exercise.accounts.user;

import com.exercise.accounts.exception.NotFoundException;
import com.exercise.accounts.exception.ServiceUnavailableErrorException;
import com.exercise.accounts.github.GithubClient;
import com.exercise.accounts.github.GithubRepoResponse;
import com.exercise.accounts.github.GithubUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GithubClient githubClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserSuccess() throws Exception {
        // Mock github user client call response

        GithubUserResponse githubUser = new GithubUserResponse("login", 1L, "http://avatar.com", "http://user.com", "name", "company", "blog", "location", "email@email.com", "bio", LocalDateTime.now(), LocalDateTime.now());
        when(githubClient.getUserByUsername(githubUser.getName())).thenReturn(githubUser);

        // Mock github repos client call response
        GithubRepoResponse githubUserRepo = new GithubRepoResponse("name", "http://repo.com");
        when(githubClient.getReposByUsername(githubUser.getName())).thenReturn(List.of(githubUserRepo));

        String expectedResponse = "{\"user_name\":\"login\",\"display_name\":\"name\",\"avatar\":\"http://avatar.com\",\"geo_location\":\"location\",\"email\":\"email@email.com\",\"url\":\"http://user.com\",\"created_at\":\"" + githubUser.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")) +"\",\"repos\":[{\"name\":\"name\",\"url\":\"http://repo.com\"}]}";

        mockMvc.perform(get("/v1/users/" + githubUser.getName()))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetUserInvalidUsername() throws Exception {
        String username = "invalid-user-name-";

        String expectedResponse = "{\"message\":\"invalid username: " + username + "\"}";

        mockMvc.perform(get("/v1/users/" + username))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetUserGithubUserNotFound() throws Exception {
        String username = "username";

        // Mock github user client call response
        when(githubClient.getUserByUsername(username)).thenThrow(new NotFoundException("github user not found"));

        String expectedResponse = "{\"message\":\"github user not found\"}";

        mockMvc.perform(get("/v1/users/" + username))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetUserGithubServiceUnavailableException() throws Exception {
        String username = "username";

        // Mock github user client call response
        when(githubClient.getUserByUsername(username)).thenThrow(new ServiceUnavailableErrorException("service unavailable"));

        String expectedResponse = "{\"message\":\"service unavailable\"}";

        mockMvc.perform(get("/v1/users/" + username))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetUserGithubServiceUnexpectedException() throws Exception {
        String username = "username";

        // Mock github user client call response
        when(githubClient.getUserByUsername(username)).thenThrow(new RuntimeException());

        String expectedResponse = "{\"message\":\"unexpected error\"}";

        mockMvc.perform(get("/v1/users/" + username))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(expectedResponse));
    }
}
