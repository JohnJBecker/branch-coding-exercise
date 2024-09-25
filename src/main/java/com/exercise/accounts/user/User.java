package com.exercise.accounts.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    private String userName;
    private String displayName;
    private String avatar;
    private String geoLocation;
    private String email;
    private String url;
    private LocalDateTime createdAt;
    private List<UserRepo> repos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userName.equals(user.userName) &&
                displayName.equals(user.displayName) &&
                avatar.equals(user.avatar) &&
                geoLocation.equals(user.geoLocation) &&
                email.equals(user.email) &&
                url.equals(user.url) &&
                createdAt.equals(user.createdAt) &&
                repos.equals(user.repos);
    }
}

