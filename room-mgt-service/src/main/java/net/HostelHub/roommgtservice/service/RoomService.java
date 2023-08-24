package net.HostelHub.roommgtservice.service;

import net.HostelHub.roommgtservice.dto.Response;
import net.HostelHub.roommgtservice.dto.RoomRequest;
import net.HostelHub.roommgtservice.dto.RoomTypeRequest;
import org.springframework.http.ResponseEntity;

public interface RoomService {

    ResponseEntity<Response> addRoomType(RoomTypeRequest roomTypeRequest);
    ResponseEntity<Response> addRoom(RoomRequest roomRequest);

}
