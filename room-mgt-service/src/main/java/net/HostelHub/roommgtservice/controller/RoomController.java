package net.HostelHub.roommgtservice.controller;

import net.HostelHub.roommgtservice.dto.Response;
import net.HostelHub.roommgtservice.dto.RoomRequest;
import net.HostelHub.roommgtservice.dto.RoomTypeRequest;
import net.HostelHub.roommgtservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
