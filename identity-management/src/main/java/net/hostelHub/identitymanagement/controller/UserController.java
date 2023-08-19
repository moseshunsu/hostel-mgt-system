package net.hostelHub.identitymanagement.controller;

import net.hostelHub.identitymanagement.dto.Response;
import net.hostelHub.identitymanagement.dto.UserRequest;
import net.hostelHub.identitymanagement.entity.User;
import net.hostelHub.identitymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Response> getUserDetails(@RequestBody UserRequest userRequest) {
        return userService.getUserDetails(userRequest);
    }

    @GetMapping("/{emailOrUsername}")
    public User fetchUser(@PathVariable String emailOrUsername) {
        return userService.fetchUser(emailOrUsername);
    }

}
