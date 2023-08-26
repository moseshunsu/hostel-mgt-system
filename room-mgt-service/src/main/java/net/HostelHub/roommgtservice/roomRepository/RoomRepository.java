package net.HostelHub.roommgtservice.roomRepository;

import net.HostelHub.roommgtservice.entity.Room;
import net.HostelHub.roommgtservice.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    boolean existsByStatus(RoomStatus status);
    Room findByRoomType_HostelNameAndRoomType_SchoolName(String hostelName, String schoolName);
    boolean existsByRoomNumber(String roomNumber);
}