package com.exercise.accounts.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRepo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
