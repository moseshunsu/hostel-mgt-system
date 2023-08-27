package net.hostelHub.bookingreservationservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.hostelHub.bookingreservationservice.utils.State;
import net.hostelHub.bookingreservationservice.utils.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter @Getter @Table(name = "bookings")
@Entity @AllArgsConstructor @NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hostelName;

    @Column(nullable = false)
    private String school;

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupant_code", referencedColumnName = "occupant_code", nullable = false)
    @JsonIgnore
    private Occupant occupant;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(updatable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
