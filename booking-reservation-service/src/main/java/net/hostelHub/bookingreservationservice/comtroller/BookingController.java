package net.hostelHub.bookingreservationservice.comtroller;

import net.hostelHub.bookingreservationservice.dto.*;
import net.hostelHub.bookingreservationservice.service.BookingService;
import net.hostelHub.bookingreservationservice.service.OccupantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    OccupantService occupantService;

    @GetMapping("/school")
    public ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(@RequestBody SchoolRequest schoolRequest) {
        return bookingService.fetchAvailableRooms(schoolRequest);
    }

    @PostMapping("/occupants")
    public ResponseEntity<Response> updateDetails(@RequestBody OccupantRequest occupantRequest) {
        return occupantService.updateDetails(occupantRequest);
    }

    @PostMapping
    public ResponseEntity<Response> makeBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.makeBooking(bookingRequest);
    }
}
