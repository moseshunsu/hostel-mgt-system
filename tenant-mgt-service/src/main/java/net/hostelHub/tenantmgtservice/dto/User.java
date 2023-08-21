package net.hostelHub.tenantmgtservice.dto;

import lombok.*;

@Getter @Setter @ToString
public class User {

    private Long id;
    private String name;
    private String username;
    private String email;

}
