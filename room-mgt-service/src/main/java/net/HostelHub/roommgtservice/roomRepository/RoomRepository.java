package net.HostelHub.roommgtservice.roomRepository;

import net.HostelHub.roommgtservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}