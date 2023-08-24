package net.HostelHub.roommgtservice.dto;

import lombok.Data;
import net.HostelHub.roommgtservice.entity.Sex;

@Data
public class RoomRequest {

    private String roomNumber;
    private String hostelName;
    private int numberInARoom;
    private String schoolName;
    private Sex sex;
    private String status;

}
