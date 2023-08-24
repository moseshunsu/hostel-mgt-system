package net.hostelHub.bookingreservationservice.service;

import net.hostelHub.bookingreservationservice.dto.BookingRequest;
import net.hostelHub.bookingreservationservice.dto.Response;
import org.springframework.http.ResponseEntity;

public interface BookingService {
    ResponseEntity<Response> makeBooking(BookingRequest bookingRequest);
    ResponseEntity<SchoolRoom> searchSchool(SchoolRequest schoolRequest);
}
