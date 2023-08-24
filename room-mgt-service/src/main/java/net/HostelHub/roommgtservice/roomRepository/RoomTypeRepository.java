package net.HostelHub.roommgtservice.roomRepository;

import net.HostelHub.roommgtservice.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findByHostelNameAndNumberInARoomAndSchoolName(String hostelName, int numberInARoom, String schoolName);
}