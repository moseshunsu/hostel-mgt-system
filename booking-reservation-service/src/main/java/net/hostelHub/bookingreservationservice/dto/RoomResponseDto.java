package net.hostelHub.bookingreservationservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class RoomResponseDto {
    private String hostelName;
    private String schoolName;
    private String tenantCode;
    private Double pricePerBed;
    private String description;
    private String roomNumber;
    private String sex;
    private int numberInARoom;
    private int bedAvailable;
}
