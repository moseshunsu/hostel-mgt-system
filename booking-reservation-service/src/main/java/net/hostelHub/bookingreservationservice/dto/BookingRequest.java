package net.hostelHub.bookingreservationservice.dto;

import lombok.Builder;
import lombok.Data;
import net.hostelHub.bookingreservationservice.utils.State;

@Data @Builder
public class BookingRequest {
    private String hostelName;
    private String schoolName;
    private String occupantCode;
    private String academicYear;
    private String roomNumber;
}
