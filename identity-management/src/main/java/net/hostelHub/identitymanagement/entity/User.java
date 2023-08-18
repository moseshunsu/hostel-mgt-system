package net.hostelHub.identitymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @Table(name = "users")
@AllArgsConstructor @NoArgsConstructor @Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

}
