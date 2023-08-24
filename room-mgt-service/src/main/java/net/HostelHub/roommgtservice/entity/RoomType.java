package net.HostelHub.roommgtservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Setter @Getter @Table(name = "room_types")
@AllArgsConstructor @NoArgsConstructor
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_in_a_room")
    private Integer numberInARoom;

    @Column(nullable = false)
    private String hostelName;

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = false)
    private String tenantCode;

    private Double pricePerBed;

    private String description;

}
