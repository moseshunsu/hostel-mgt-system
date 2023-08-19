package net.hostelHub.identitymanagement.dto;

import lombok.*;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserRequest {

    private String name;
    private String username;
    private String email;

}
