package net.hostelHub.bookingreservationservice.repository;

import net.hostelHub.bookingreservationservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
