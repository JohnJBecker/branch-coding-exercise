package com.exercise.accounts.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRepo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
