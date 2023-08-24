package net.HostelHub.roommgtservice.dto;

import lombok.Builder;
import lombok.Data;
import net.HostelHub.roommgtservice.entity.Room;

@Builder @Data
public class RoomDto {
    private String hostelName;
    private String schoolName;
    private String tenantCode;
    private Double pricePerBed;
    private String description;
    private Room roomNumber;
    private String sex;
}
