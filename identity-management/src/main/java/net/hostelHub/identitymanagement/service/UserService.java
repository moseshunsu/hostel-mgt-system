package net.hostelHub.identitymanagement.service;

import net.hostelHub.identitymanagement.dto.UserRequest;
import net.hostelHub.identitymanagement.dto.Response;
import net.hostelHub.identitymanagement.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<Response> getUserDetails(UserRequest userRequest);
    User fetchUser(String emailOrUsername);
//    List<ResponseEntity<Response>> allUsers();
//    ResponseEntity<Response> fetchUser(Long userId);

}
