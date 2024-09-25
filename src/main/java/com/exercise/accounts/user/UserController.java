package com.exercise.accounts.user;

import com.exercise.accounts.exception.InvalidUserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/v1/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to get user data by id
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        if (!UserValidation.isValidUsername(username)) {
            throw new InvalidUserNameException(username);
        }

        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
