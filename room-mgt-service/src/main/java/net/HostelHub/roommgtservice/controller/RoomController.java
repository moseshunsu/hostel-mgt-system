package net.HostelHub.roommgtservice.controller;

import net.HostelHub.roommgtservice.dto.Response;
import net.HostelHub.roommgtservice.dto.RoomResponseDto;
import net.HostelHub.roommgtservice.dto.RoomRequest;
import net.HostelHub.roommgtservice.dto.RoomTypeRequest;
import net.HostelHub.roommgtservice.entity.Room;
import net.HostelHub.roommgtservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/room-types")
    public ResponseEntity<Response> addRoomType(@RequestBody RoomTypeRequest roomTypeRequest) {
        return roomService.addRoomType(roomTypeRequest);
    }

    @PostMapping("/room")
    public ResponseEntity<Response> addRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.addRoom(roomRequest);
    }
    @GetMapping("/room")
    public ResponseEntity<RoomResponseDto> fetchRoomDetails(@RequestParam String schoolName,
                                                            @RequestParam String hostelName,
                                                            @RequestParam int numberInARoom) {
        return roomService.fetchRoomDetails(schoolName, hostelName, numberInARoom);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(@RequestParam String schoolName,
                                                                    @RequestParam String hostelName) {
        return roomService.fetchAvailableRooms(schoolName, hostelName);
    }
}
