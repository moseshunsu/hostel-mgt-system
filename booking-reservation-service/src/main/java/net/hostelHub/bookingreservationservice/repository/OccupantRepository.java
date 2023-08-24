package net.hostelHub.bookingreservationservice.repository;

import net.hostelHub.bookingreservationservice.entity.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupantRepository extends JpaRepository<Occupant, String> {
    boolean existsByOccupantCode(String occupantCode);
    Occupant findByOccupantCode(String occupantCode);
    boolean existsByEmailAndUsername(String email, String username);
}
