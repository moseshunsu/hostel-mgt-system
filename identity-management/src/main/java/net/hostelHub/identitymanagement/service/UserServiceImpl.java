package net.hostelHub.identitymanagement.service;

import net.hostelHub.identitymanagement.dto.Data;
import net.hostelHub.identitymanagement.dto.Response;
import net.hostelHub.identitymanagement.dto.UserRequest;
import net.hostelHub.identitymanagement.entity.User;
import net.hostelHub.identitymanagement.repository.UserRepository;
import net.hostelHub.identitymanagement.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Response> getUserDetails(UserRequest userRequest) {

        String email = userRequest.getEmail();
        String username = userRequest.getUsername();

        boolean userExists = userRepository.existsByEmailOrUsername(email, username);

        if (userExists) {
            return ResponseEntity.badRequest().body(Response.builder()
                    .responseCode(ResponseUtils.USER_EXISTS_CODE)
                    .responseMessage(ResponseUtils.USER_EXISTS_MESSAGE)
                    .build());
        }

        User user = User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .username(userRequest.getName())
                .build();

        User saveduser = userRepository.save(user);

        return successfulResponse(saveduser);
    }

    @Override
    public User fetchUser(String emailOrUsername) {
        return userRepository.existsByEmailOrUsername(emailOrUsername, emailOrUsername)
                ? userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                : null;
    }

    private ResponseEntity<Response> successfulResponse(User user) {
        Data userData = Data.builder()
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(Response.builder()
                .responseCode(ResponseUtils.SUCCESS_CODE)
                .responseMessage(ResponseUtils.FETCHED_MESSAGE)
                .data(userData)
                .build());
    }

}
