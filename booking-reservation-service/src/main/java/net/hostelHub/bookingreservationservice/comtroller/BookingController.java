package net.hostelHub.bookingreservationservice.comtroller;

import net.hostelHub.bookingreservationservice.dto.RoomResponseDto;
import net.hostelHub.bookingreservationservice.dto.SchoolRequest;
import net.hostelHub.bookingreservationservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/school")
    public ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(@RequestBody SchoolRequest schoolRequest) {
        return bookingService.fetchAvailableRooms(schoolRequest);
    }

}
