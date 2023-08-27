package net.HostelHub.roommgtservice.service;

import net.HostelHub.roommgtservice.dto.Response;
import net.HostelHub.roommgtservice.dto.RoomResponseDto;
import net.HostelHub.roommgtservice.dto.RoomRequest;
import net.HostelHub.roommgtservice.dto.RoomTypeRequest;
import net.HostelHub.roommgtservice.entity.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    ResponseEntity<Response> addRoomType(RoomTypeRequest roomTypeRequest);
    ResponseEntity<Response> addRoom(RoomRequest roomRequest);
    ResponseEntity<RoomResponseDto> fetchRoomDetails(String schoolName, String hostelName, int numberInARoom);
    ResponseEntity<List<RoomResponseDto>> fetchAvailableRooms(String schoolName, String hostelName);

}
