package net.hostelHub.bookingreservationservice.service;

import net.hostelHub.bookingreservationservice.dto.BookingRequest;
import net.hostelHub.bookingreservationservice.dto.Response;
import net.hostelHub.bookingreservationservice.dto.RoomResponseDto;
import net.hostelHub.bookingreservationservice.dto.SchoolRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    ResponseEntity<Response> makeBooking(BookingRequest bookingRequest);
    ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(SchoolRequest schoolRequest);
}
