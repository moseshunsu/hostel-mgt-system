package net.hostelHub.bookingreservationservice.service.client;

import net.hostelHub.bookingreservationservice.dto.RoomResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ROOM-SERVICE")
public interface RoomFeignClient {
    @GetMapping("api/v1/rooms")
    ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(@RequestParam String schoolName,
                                                              @RequestParam String hostelName);

    @GetMapping("api/v1/rooms/room")
    ResponseEntity<RoomResponseDto> fetchRoomDetails(@RequestParam String schoolName,
                                                     @RequestParam String hostelName,
                                                     @RequestParam String roomNumber);

}
