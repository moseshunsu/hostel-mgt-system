package net.hostelHub.bookingreservationservice.dto;

import lombok.Builder;
import lombok.Data;
import net.hostelHub.bookingreservationservice.entity.State;

@Data @Builder
public class BookingRequest {
    private String hostelName;
    private String school;
    private State state;
    private String occupantCode;
    private String academicYear;
    private String roomNumber;
    private Double price;
    private String status;
}
